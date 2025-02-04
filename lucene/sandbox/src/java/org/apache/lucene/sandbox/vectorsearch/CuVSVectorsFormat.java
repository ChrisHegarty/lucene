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
package org.apache.lucene.sandbox.vectorsearch;

import com.nvidia.cuvs.CuVSResources;
import com.nvidia.cuvs.LibraryException;
import java.io.IOException;
import org.apache.lucene.codecs.KnnVectorsFormat;
import org.apache.lucene.index.SegmentReadState;
import org.apache.lucene.index.SegmentWriteState;
import org.apache.lucene.sandbox.vectorsearch.CuVSVectorsWriter.MergeStrategy;

/** CuVS based KnnVectorsFormat for GPU acceleration */
public class CuVSVectorsFormat extends KnnVectorsFormat {

  public static final String VECTOR_DATA_CODEC_NAME = "Lucene99CagraVectorsFormatData";
  public static final String VECTOR_DATA_EXTENSION = "cag";
  public static final String META_EXTENSION = "cagmf";
  public static final int VERSION_CURRENT = 0;
  public static final int MAX_DIMENSIONS = 4096;

  public static final int DEFAULT_WRITER_THREADS = 1;
  public static final int DEFAULT_INTERMEDIATE_GRAPH_DEGREE = 128;
  public static final int DEFAULT_GRAPH_DEGREE = 64;

  final int cuvsWriterThreads;
  final int intGraphDegree;
  final int graphDegree;
  final MergeStrategy mergeStrategy;
  final CuVSResources resources;

  public CuVSVectorsFormat() {
    this(
        DEFAULT_WRITER_THREADS,
        DEFAULT_INTERMEDIATE_GRAPH_DEGREE,
        DEFAULT_GRAPH_DEGREE,
        MergeStrategy.NON_TRIVIAL_MERGE);
  }

  public CuVSVectorsFormat(
      int cuvsWriterThreads, int intGraphDegree, int graphDegree, MergeStrategy mergeStrategy)
      throws LibraryException {
    super("CuVSVectorsFormat");
    this.mergeStrategy = mergeStrategy;
    this.cuvsWriterThreads = cuvsWriterThreads;
    this.intGraphDegree = intGraphDegree;
    this.graphDegree = graphDegree;
    resources = cuVSResourcesOrNull();
  }

  private static CuVSResources cuVSResourcesOrNull() {
    try {
      return CuVSResources.create();
    } catch (UnsupportedOperationException e) {
      return null;
    } catch (LibraryException ex) {
      // TODO: log and/or return null
      throw ex;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Tells whether the platform supports cuvs.
   *
   * @throws UnsupportedOperationException if not supported
   */
  public void checkSupported() {
    if (resources == null) {
      throw new UnsupportedOperationException();
    }
  }

  @Override
  public CuVSVectorsWriter fieldsWriter(SegmentWriteState state) throws IOException {
    checkSupported();
    return new CuVSVectorsWriter(
        state, cuvsWriterThreads, intGraphDegree, graphDegree, mergeStrategy, resources);
  }

  @Override
  public CuVSVectorsReader fieldsReader(SegmentReadState state) throws IOException {
    checkSupported();
    try {
      return new CuVSVectorsReader(state, resources);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int getMaxDimensions(String fieldName) {
    return MAX_DIMENSIONS;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("CuVSVectorsFormat(");
    sb.append("cuvsWriterThreads=").append(cuvsWriterThreads);
    sb.append("intGraphDegree=").append(intGraphDegree);
    sb.append("graphDegree=").append(graphDegree);
    sb.append("mergeStrategy=").append(mergeStrategy);
    sb.append("resources=").append(resources);
    sb.append(")");
    return sb.toString();
  }
}
