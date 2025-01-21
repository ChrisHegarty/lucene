/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.misc.store;

import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static org.apache.lucene.store.ReadAdvice.RANDOM;
import static org.apache.lucene.store.ReadAdvice.SEQUENTIAL;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.SuppressForbidden;

@SuppressForbidden(reason = "just a benchmark")
public class TestDirectIODirectoryBench {

  static final int dims = 1024;
  static final int NUM_VECTORS = 5_000_000;
  static final int offset = 3; // allows for slice

  static final Path path = Path.of("float32_vectors.vec");
  static final Random random = new Random();

  public static void main(String... args) throws Exception {
    maybeWriteVectors();

    // Sequential exist just for informational purposes, not really part of the
    // experiment
//    for (int parallelism : List.of(1, 5)) {
//      System.out.println("---\nread sequentially - parallelism " + parallelism);
//      readSequential(parallelism);
//    }

    for (int parallelism : List.of(1, 2, 5, 10, 20, 50, 100)){
      System.out.println("---\nread randomly - parallelism " + parallelism);
      readRandomly(parallelism);
    }
  }

  static void readSequential(int parallelism) throws Exception {
    float f1 = 0, f2 = 0;
    for (int i = 0; i < 5; i++) {
      clearCache();
      try (var dir = getDirectIODirectory(Path.of("."))) {
        f1 += readVectorsSequentially(dir, parallelism);
      }
    }
    for (int i = 0; i < 5; i++) {
      clearCache();
      try (var dir = new MMapDirectory(Path.of("."))) {
        f2 += readVectorsSequentially(dir, parallelism);
      }
    }
    if (f1 != f2) {
      throw new AssertionError(f1 + " != " + f2);
    }
  }

  static float readRandomly(int parallelism) throws Exception {
    float f1 = 0, f2 = 0;
    for (int i = 0; i < 5; i++) {
      clearCache();
      try (var dir = getDirectIODirectory(Path.of("."))) {
        f1 += readVectorsRandomly(dir, parallelism);
      }
    }
    for (int i = 0; i < 5; i++) {
      clearCache();
      try (var dir = new MMapDirectory(Path.of("."))) {
        f2 += readVectorsRandomly(dir, parallelism);
      }
    }
    return f1 + f2;
  }

  static float readVectorsSequentially(Directory dir, int parallelism) throws Exception {
    float res;
    long startTime = System.nanoTime();
    if (parallelism > 1) {
      res = readVectorsSequentiallyWithParallelism(dir, parallelism);
    } else {
      res = readVectorsSequentially(dir);
    }
    long elapsed = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
    String dirType = dir instanceof MMapDirectory ? "mmap" : "dio ";
    System.out.println("seq " + dirType + ": " + elapsed);
    return res;
  }

  static float readVectorsRandomly(Directory dir, int parallelism) throws Exception {
    float res;
    long startTime = System.nanoTime();
    if (parallelism > 1) {
      res = readVectorsRandomlyWithParallelism(dir, parallelism);
    } else {
      res = readVectorsRandomly(dir);
    }
    long elapsed = Duration.ofNanos(System.nanoTime() - startTime).toMillis();
    String dirType = dir instanceof MMapDirectory ? "mmap" : "dio ";
    System.out.println("ran " + dirType + ": " + elapsed);
    return res;
  }

  static float readVectorsSequentially(Directory dir) throws IOException {
    float[] fa = new float[dims];
    float res = 0;
    try (var inBase =
        dir.openInput(
            path.getFileName().toString(), IOContext.DEFAULT.withReadAdvice(SEQUENTIAL))) {
      var in = inBase.slice("test-slice", offset, inBase.length() - offset);
      in.prefetch(0, in.length());
      for (int i = 0; i < NUM_VECTORS; i++) {
        assert in.getFilePointer() == ((long) i * dims * Float.BYTES);
        in.readFloats(fa, 0, dims);
        res += compute(fa);
      }
    }
    return res;
  }

  static float readVectorsRandomly(Directory dir) throws IOException {
    float[] fa = new float[dims];
    float res = 0;
    try (var inBase = dir.openInput(path.getFileName().toString(), IOContext.DEFAULT)) {
      var in = inBase.slice("test-slice", offset, inBase.length() - offset);
      in.updateReadAdvice(RANDOM);
      for (int i = 0; i < NUM_VECTORS / 10; i++) {
        long vecId = random.nextLong(NUM_VECTORS);
        in.seek(vecId * dims * Float.BYTES);
        in.readFloats(fa, 0, dims);
        res += compute(fa);
      }
    }
    return res;
  }

  static float readVectorsSequentiallyWithParallelism(Directory dir, int parallelism)
      throws Exception {
    List<float[]> floats = IntStream.range(0, parallelism).mapToObj(i -> new float[1024]).toList();
    float res = 0;
    try (var inBase = dir.openInput(path.getFileName().toString(), IOContext.DEFAULT);
        var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      var in = inBase.slice("test-slice", offset, inBase.length() - offset);
      in.updateReadAdvice(SEQUENTIAL);
      List<IndexInput> inputs = IntStream.range(0, parallelism).mapToObj(i -> in.clone()).toList();
      List<Callable<float[]>> tasks = new ArrayList<>(parallelism);
      IntStream.range(0, parallelism).forEach(k -> tasks.add(null)); // initialize each element
      for (int i = 0; i < NUM_VECTORS; i += parallelism) {
        final int idx = i;
        IntStream.range(0, Math.min(NUM_VECTORS - i, parallelism))
            .forEach(
                k -> {
                  long filePos = (long) (idx + k) * dims * Float.BYTES;
                  tasks.set(k, new ReadFloatsTask(inputs.get(k), floats.get(k), filePos));
                });
        var futures = tasks.stream().map(executor::submit).toList();
        for (var future : futures) {
          // futures can be completed in any order, check against known order in floats
          future.get();
        }
        for (int j = 0; j < futures.size(); j++) {
          res += compute(floats.get(j));
        }
      }
    }
    return res;
  }

  static float readVectorsRandomlyWithParallelism(Directory dir, int parallelism) throws Exception {
    List<float[]> floats = IntStream.range(0, parallelism).mapToObj(i -> new float[1024]).toList();
    float res = 0;
    try (var inBase = dir.openInput(path.getFileName().toString(), IOContext.DEFAULT);
        var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      var in = inBase.slice("test-slice", offset, inBase.length() - offset);
      in.updateReadAdvice(RANDOM);
      List<IndexInput> inputs = IntStream.range(0, parallelism).mapToObj(i -> in.clone()).toList();
      List<Callable<float[]>> tasks = new ArrayList<>(parallelism);
      IntStream.range(0, parallelism).forEach(k -> tasks.add(null)); // initialize each element
      for (int i = 0; i < NUM_VECTORS / (10 * parallelism); i++) {
        IntStream.range(0, parallelism)
            .forEach(
                k -> {
                  long filePos = random.nextLong(NUM_VECTORS) * dims * Float.BYTES;
                  tasks.set(k, new ReadFloatsTask(inputs.get(k), floats.get(k), filePos));
                });
        var futures = tasks.stream().map(executor::submit).toList();
        for (var future : futures) {
          res += compute(future.get());
        }
      }
    }
    return res;
  }

  static class ReadFloatsTask implements Callable<float[]> {
    final IndexInput in;
    final float[] fa;
    final long filePos;

    ReadFloatsTask(IndexInput in, float[] fa, long filePos) {
      this.in = in;
      this.fa = fa;
      this.filePos = filePos;
    }

    @Override
    public float[] call() throws Exception {
      in.seek(filePos);
      in.readFloats(fa, 0, dims);
      return fa;
    }
  }

  static float compute(float[] fa) {
    float res = 0;
    for (float v : fa) {
      res += v;
    }
    return res;
  }

  static void clearCache() throws Exception {
    var name = System.getProperty("os.name");
    var command = name.startsWith("Mac")
            ? new String[] {"purge"}
            : new String[] {"sysctl", "-w", "vm.drop_caches=3"};
    var pb = new ProcessBuilder(command);
    pb.redirectErrorStream(true);
    var process = pb.start();
    int ec = process.waitFor();
    String result = new String(process.getInputStream().readAllBytes());
    if (ec != 0) {
      System.out.println(result);
      throw new AssertionError(result.contains("permitted") ? "sudo?" : "");
    }
  }

  static DirectIODirectory getDirectIODirectory(Path path) throws IOException {
    return new DirectIODirectory(FSDirectory.open(path), 8192, 0L) {
      @Override
      protected boolean useDirectIO(String name, IOContext context, OptionalLong fileLength) {
        return true;
      }
    };
  }

  static void maybeWriteVectors() throws IOException {
    if (Files.exists(path)) {
      long fileSize = Files.size(path);
      if (fileSize != ((long) dims * Float.BYTES * NUM_VECTORS) + offset) {
        var msg = fileSize + " != " + ((long) dims * Float.BYTES * NUM_VECTORS) + offset;
        throw new AssertionError(msg);
      }
      return;
    }

    System.out.println("writing vectors...");
    Files.createFile(path);
    byte[] ba = new byte[dims * Float.BYTES];
    FloatBuffer fb = ByteBuffer.wrap(ba).order(LITTLE_ENDIAN).asFloatBuffer();
    //try (var out = Files.newOutputStream(path, com.sun.nio.file.ExtendedOpenOption.DIRECT)) {
    try (var out = Files.newOutputStream(path)) {
      if (offset > 0) {
        out.write(new byte[offset]); // just some offset for slicing
      }
      for (int i = 0; i < NUM_VECTORS; i++) {
        randomFloat32Vector(fb);
        out.write(ba);
      }
    }
    System.out.println("writing vectors... complete");
  }

  static void randomFloat32Vector(FloatBuffer fb) {
    fb.clear();
    for (int i = 0; i < dims; i++) {
      fb.put(random.nextFloat());
    }
  }

  // --- sanity
  // checks that overlapping reads in DIO are fine, and also that the
  // parallel impl returns same results as that of the sequential
  public static void main2(String... args) throws Exception {
    final int parallelism = 10;
    final int vector_count = 1_000_000;
    float[][] faa1 = new float[vector_count][dims];

    try (var dir = getDirectIODirectory(Path.of("."));
        var inBase = dir.openInput(path.getFileName().toString(), IOContext.DEFAULT);
        var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      var in = inBase.slice("test-slice", offset, inBase.length() - offset);
      var in1 = in.clone();

      // sequential
      float[] fa = new float[dims];
      for (int i = 0; i < vector_count; i++) {
        assert in1.getFilePointer() == ((long) i * dims * Float.BYTES);
        in1.readFloats(fa, 0, dims);
        faa1[i] = fa.clone();
      }

      // parallel
      List<float[]> floats =
          IntStream.range(0, parallelism).mapToObj(i -> new float[dims]).toList();
      List<IndexInput> inputs = IntStream.range(0, parallelism).mapToObj(i -> in.clone()).toList();
      List<Callable<float[]>> tasks = new ArrayList<>(parallelism);
      IntStream.range(0, parallelism).forEach(k -> tasks.add(null)); // initialize each element
      for (int i = 0; i < vector_count; i += parallelism) {
        final int idx = i;
        IntStream.range(0, parallelism)
            .forEach(
                k -> {
                  long filePos = (long) (idx + k) * dims * Float.BYTES;
                  tasks.set(k, new ReadFloatsTask(inputs.get(k), floats.get(k), filePos));
                });
        var futures = tasks.stream().map(executor::submit).toList();
        for (var future : futures) {
          // futures can be completed in any order, check against known order in floats
          future.get();
        }
        for (int j = 0; j < parallelism; j++) {
          if (!Arrays.equals(faa1[i + j], floats.get(j))) {
            throw new AssertionError("arrays not equal");
          }
        }
      }
    }
  }
}
