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

import java.util.List;
import org.apache.lucene.codecs.Codec;
import org.apache.lucene.index.VectorEncoding;
import org.apache.lucene.tests.index.BaseKnnVectorsFormatTestCase;
import org.apache.lucene.tests.util.LuceneTestCase;
import org.apache.lucene.tests.util.TestUtil;
import org.junit.BeforeClass;

@LuceneTestCase.AwaitsFix(bugUrl = "")
public class TestCuVSVectorsFormat extends BaseKnnVectorsFormatTestCase {

  @BeforeClass
  public static void beforeClass() {
    assumeTrue("cuvs is not supported", isSupported());
  }

  static boolean isSupported() {
    try {
      var format = new CuVSVectorsFormat();
      format.checkSupported();
      return true;
    } catch (UnsupportedOperationException e) {
      return false;
    }
  }

  @Override
  protected Codec getCodec() {
    return TestUtil.alwaysKnnVectorsFormat(new CuVSVectorsFormat());
  }

  @Override
  protected List<VectorEncoding> supportedVectorEncodings() {
    return List.of(VectorEncoding.FLOAT32);
  }
}
