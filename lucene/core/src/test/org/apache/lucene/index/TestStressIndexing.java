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
package org.apache.lucene.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.tests.analysis.MockAnalyzer;
import org.apache.lucene.tests.store.MockDirectoryWrapper;
import org.apache.lucene.tests.util.English;
import org.apache.lucene.tests.util.LuceneTestCase;

public class TestStressIndexing extends LuceneTestCase {
  private abstract static class TimedThread extends Thread {
    volatile boolean failed;
    private static final int RUN_ITERATIONS = TEST_NIGHTLY ? atLeast(100) : atLeast(20);
    private final TimedThread[] allThreads;

    public abstract void doWork() throws Throwable;

    TimedThread(TimedThread[] threads) {
      this.allThreads = threads;
    }

    @Override
    public void run() {
      int iterations = 0;
      try {
        do {
          if (anyErrors()) break;
          doWork();
        } while (++iterations < RUN_ITERATIONS);
      } catch (Throwable e) {
        System.out.println(Thread.currentThread() + ": exc");
        e.printStackTrace(System.out);
        failed = true;
      }
    }

    private boolean anyErrors() {
      for (TimedThread thread : allThreads) {
        if (thread != null && thread.failed) return true;
      }
      return false;
    }
  }

  private static class IndexerThread extends TimedThread {
    IndexWriter writer;
    int nextID;

    public IndexerThread(IndexWriter writer, TimedThread[] threads) {
      super(threads);
      this.writer = writer;
    }

    @Override
    public void doWork() throws Exception {
      // Add 10 docs:
      for (int j = 0; j < 10; j++) {
        Document d = new Document();
        int n = random().nextInt();
        d.add(newStringField("id", Integer.toString(nextID++), Field.Store.YES));
        d.add(newTextField("contents", English.intToEnglish(n), Field.Store.NO));
        writer.addDocument(d);
      }

      // Delete 5 docs:
      int deleteID = nextID - 1;
      for (int j = 0; j < 5; j++) {
        writer.deleteDocuments(new Term("id", "" + deleteID));
        deleteID -= 2;
      }
    }
  }

  private static class SearcherThread extends TimedThread {
    private final Directory directory;

    public SearcherThread(Directory directory, TimedThread[] threads) {
      super(threads);
      this.directory = directory;
    }

    @Override
    public void doWork() throws Throwable {
      for (int i = 0; i < 100; i++) {
        IndexReader ir = DirectoryReader.open(directory);
        newSearcher(ir);
        ir.close();
      }
    }
  }

  /*
    Run one indexer and 2 searchers against single index as
    stress test.
  */
  public void runStressTest(Directory directory, MergeScheduler mergeScheduler) throws Exception {
    IndexWriter modifier =
        new IndexWriter(
            directory,
            newIndexWriterConfig(new MockAnalyzer(random()))
                .setOpenMode(OpenMode.CREATE)
                .setMaxBufferedDocs(10)
                .setMergeScheduler(mergeScheduler));
    modifier.commit();

    TimedThread[] threads = new TimedThread[4];
    int numThread = 0;

    // One modifier that writes 10 docs then removes 5, over
    // and over:
    IndexerThread indexerThread = new IndexerThread(modifier, threads);
    threads[numThread++] = indexerThread;
    indexerThread.start();

    IndexerThread indexerThread2 = new IndexerThread(modifier, threads);
    threads[numThread++] = indexerThread2;
    indexerThread2.start();

    // Two searchers that constantly just re-instantiate the
    // searcher:
    SearcherThread searcherThread1 = new SearcherThread(directory, threads);
    threads[numThread++] = searcherThread1;
    searcherThread1.start();

    SearcherThread searcherThread2 = new SearcherThread(directory, threads);
    threads[numThread++] = searcherThread2;
    searcherThread2.start();

    for (int i = 0; i < numThread; i++) threads[i].join();

    modifier.close();

    for (int i = 0; i < numThread; i++) assertFalse(threads[i].failed);

    // System.out.println("    Writer: " + indexerThread.count + " iterations");
    // System.out.println("Searcher 1: " + searcherThread1.count + " searchers created");
    // System.out.println("Searcher 2: " + searcherThread2.count + " searchers created");
  }

  /* */
  public void testStressIndexAndSearching() throws Exception {
    final Directory directory;
    if (TEST_NIGHTLY) {
      directory = newMaybeVirusCheckingDirectory();
    } else {
      directory = new MockDirectoryWrapper(random(), new ByteBuffersDirectory());
    }
    if (directory instanceof MockDirectoryWrapper) {
      ((MockDirectoryWrapper) directory).setAssertNoUnrefencedFilesOnClose(true);
    }

    runStressTest(directory, new ConcurrentMergeScheduler());
    directory.close();
  }
}
