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

// ANTLR GENERATED CODE: DO NOT EDIT.

package org.apache.lucene.expressions.js;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({
  "all",
  "warnings",
  "unchecked",
  "unused",
  "cast",
  "CheckReturnValue",
  "this-escape"
})
class JavascriptLexer extends Lexer {
  static {
    RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION);
  }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
  public static final int LP = 1,
      RP = 2,
      COMMA = 3,
      BOOLNOT = 4,
      BWNOT = 5,
      MUL = 6,
      DIV = 7,
      REM = 8,
      ADD = 9,
      SUB = 10,
      LSH = 11,
      RSH = 12,
      USH = 13,
      LT = 14,
      LTE = 15,
      GT = 16,
      GTE = 17,
      EQ = 18,
      NE = 19,
      BWAND = 20,
      BWXOR = 21,
      BWOR = 22,
      BOOLAND = 23,
      BOOLOR = 24,
      COND = 25,
      COLON = 26,
      WS = 27,
      VARIABLE = 28,
      OCTAL = 29,
      HEX = 30,
      DECIMAL = 31;
  public static String[] channelNames = {"DEFAULT_TOKEN_CHANNEL", "HIDDEN"};

  public static String[] modeNames = {"DEFAULT_MODE"};

  private static String[] makeRuleNames() {
    return new String[] {
      "LP",
      "RP",
      "COMMA",
      "BOOLNOT",
      "BWNOT",
      "MUL",
      "DIV",
      "REM",
      "ADD",
      "SUB",
      "LSH",
      "RSH",
      "USH",
      "LT",
      "LTE",
      "GT",
      "GTE",
      "EQ",
      "NE",
      "BWAND",
      "BWXOR",
      "BWOR",
      "BOOLAND",
      "BOOLOR",
      "COND",
      "COLON",
      "WS",
      "VARIABLE",
      "ARRAY",
      "ID",
      "STRING",
      "OCTAL",
      "HEX",
      "DECIMAL",
      "INTEGER"
    };
  }

  public static final String[] ruleNames = makeRuleNames();

  private static String[] makeLiteralNames() {
    return new String[] {
      null, null, null, null, null, null, null, null, null, null, null, "'<<'", "'>>'", "'>>>'",
      null, "'<='", null, "'>='", "'=='", "'!='", null, null, null, "'&&'", "'||'"
    };
  }

  private static final String[] _LITERAL_NAMES = makeLiteralNames();

  private static String[] makeSymbolicNames() {
    return new String[] {
      null,
      "LP",
      "RP",
      "COMMA",
      "BOOLNOT",
      "BWNOT",
      "MUL",
      "DIV",
      "REM",
      "ADD",
      "SUB",
      "LSH",
      "RSH",
      "USH",
      "LT",
      "LTE",
      "GT",
      "GTE",
      "EQ",
      "NE",
      "BWAND",
      "BWXOR",
      "BWOR",
      "BOOLAND",
      "BOOLOR",
      "COND",
      "COLON",
      "WS",
      "VARIABLE",
      "OCTAL",
      "HEX",
      "DECIMAL"
    };
  }

  private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated public static final String[] tokenNames;

  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override
  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  public JavascriptLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
  }

  @Override
  public String getGrammarFileName() {
    return "Javascript.g4";
  }

  @Override
  public String[] getRuleNames() {
    return ruleNames;
  }

  @Override
  public String getSerializedATN() {
    return _serializedATN;
  }

  @Override
  public String[] getChannelNames() {
    return channelNames;
  }

  @Override
  public String[] getModeNames() {
    return modeNames;
  }

  @Override
  public ATN getATN() {
    return _ATN;
  }

  public static final String _serializedATN =
      "\u0004\u0000\u001f\u00fc\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"
          + "\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"
          + "\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"
          + "\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"
          + "\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"
          + "\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"
          + "\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"
          + "\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"
          + "\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"
          + "\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"
          + "\u0002\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!"
          + "\u0007!\u0002\"\u0007\"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"
          + "\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"
          + "\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"
          + "\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"
          + "\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"
          + "\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001"
          + "\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"
          + "\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"
          + "\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001"
          + "\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"
          + "\u001a\u0004\u001a\u0087\b\u001a\u000b\u001a\f\u001a\u0088\u0001\u001a"
          + "\u0001\u001a\u0001\u001b\u0001\u001b\u0005\u001b\u008f\b\u001b\n\u001b"
          + "\f\u001b\u0092\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b"
          + "\u0097\b\u001b\n\u001b\f\u001b\u009a\t\u001b\u0005\u001b\u009c\b\u001b"
          + "\n\u001b\f\u001b\u009f\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0003"
          + "\u001c\u00a4\b\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0005"
          + "\u001d\u00aa\b\u001d\n\u001d\f\u001d\u00ad\t\u001d\u0001\u001e\u0001\u001e"
          + "\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u00b5\b\u001e"
          + "\n\u001e\f\u001e\u00b8\t\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"
          + "\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u00c1\b\u001e\n"
          + "\u001e\f\u001e\u00c4\t\u001e\u0001\u001e\u0003\u001e\u00c7\b\u001e\u0001"
          + "\u001f\u0001\u001f\u0004\u001f\u00cb\b\u001f\u000b\u001f\f\u001f\u00cc"
          + "\u0001 \u0001 \u0001 \u0004 \u00d2\b \u000b \f \u00d3\u0001!\u0001!\u0001"
          + "!\u0005!\u00d9\b!\n!\f!\u00dc\t!\u0003!\u00de\b!\u0001!\u0001!\u0004!"
          + "\u00e2\b!\u000b!\f!\u00e3\u0003!\u00e6\b!\u0001!\u0001!\u0003!\u00ea\b"
          + "!\u0001!\u0004!\u00ed\b!\u000b!\f!\u00ee\u0003!\u00f1\b!\u0001\"\u0001"
          + "\"\u0001\"\u0005\"\u00f6\b\"\n\"\f\"\u00f9\t\"\u0003\"\u00fb\b\"\u0002"
          + "\u00b6\u00c2\u0000#\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t"
          + "\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f"
          + "\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014"
          + ")\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u0000;\u0000"
          + "=\u0000?\u001dA\u001eC\u001fE\u0000\u0001\u0000#\u0001\u0000((\u0001\u0000"
          + "))\u0001\u0000,,\u0001\u0000!!\u0001\u0000~~\u0001\u0000**\u0001\u0000"
          + "//\u0001\u0000%%\u0001\u0000++\u0001\u0000--\u0001\u0000<<\u0001\u0000"
          + ">>\u0001\u0000&&\u0001\u0000^^\u0001\u0000||\u0001\u0000??\u0001\u0000"
          + "::\u0003\u0000\t\n\r\r  \u0001\u0000..\u0001\u0000[[\u0001\u0000]]\u0004"
          + "\u0000$$AZ__az\u0005\u0000$$09AZ__az\u0001\u0000\'\'\u0002\u0000\'\'\\"
          + "\\\u0001\u0000\"\"\u0002\u0000\"\"\\\\\u0001\u000000\u0001\u000007\u0002"
          + "\u0000XXxx\u0003\u000009AFaf\u0001\u000009\u0002\u0000EEee\u0002\u0000"
          + "++--\u0001\u000019\u010f\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"
          + "\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"
          + "\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"
          + "\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"
          + "\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"
          + "\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"
          + "\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"
          + "\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"
          + "\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"
          + "\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000"
          + ")\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001"
          + "\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000"
          + "\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u0000"
          + "7\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001"
          + "\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0001G\u0001\u0000\u0000"
          + "\u0000\u0003I\u0001\u0000\u0000\u0000\u0005K\u0001\u0000\u0000\u0000\u0007"
          + "M\u0001\u0000\u0000\u0000\tO\u0001\u0000\u0000\u0000\u000bQ\u0001\u0000"
          + "\u0000\u0000\rS\u0001\u0000\u0000\u0000\u000fU\u0001\u0000\u0000\u0000"
          + "\u0011W\u0001\u0000\u0000\u0000\u0013Y\u0001\u0000\u0000\u0000\u0015["
          + "\u0001\u0000\u0000\u0000\u0017^\u0001\u0000\u0000\u0000\u0019a\u0001\u0000"
          + "\u0000\u0000\u001be\u0001\u0000\u0000\u0000\u001dg\u0001\u0000\u0000\u0000"
          + "\u001fj\u0001\u0000\u0000\u0000!l\u0001\u0000\u0000\u0000#o\u0001\u0000"
          + "\u0000\u0000%r\u0001\u0000\u0000\u0000\'u\u0001\u0000\u0000\u0000)w\u0001"
          + "\u0000\u0000\u0000+y\u0001\u0000\u0000\u0000-{\u0001\u0000\u0000\u0000"
          + "/~\u0001\u0000\u0000\u00001\u0081\u0001\u0000\u0000\u00003\u0083\u0001"
          + "\u0000\u0000\u00005\u0086\u0001\u0000\u0000\u00007\u008c\u0001\u0000\u0000"
          + "\u00009\u00a0\u0001\u0000\u0000\u0000;\u00a7\u0001\u0000\u0000\u0000="
          + "\u00c6\u0001\u0000\u0000\u0000?\u00c8\u0001\u0000\u0000\u0000A\u00ce\u0001"
          + "\u0000\u0000\u0000C\u00e5\u0001\u0000\u0000\u0000E\u00fa\u0001\u0000\u0000"
          + "\u0000GH\u0007\u0000\u0000\u0000H\u0002\u0001\u0000\u0000\u0000IJ\u0007"
          + "\u0001\u0000\u0000J\u0004\u0001\u0000\u0000\u0000KL\u0007\u0002\u0000"
          + "\u0000L\u0006\u0001\u0000\u0000\u0000MN\u0007\u0003\u0000\u0000N\b\u0001"
          + "\u0000\u0000\u0000OP\u0007\u0004\u0000\u0000P\n\u0001\u0000\u0000\u0000"
          + "QR\u0007\u0005\u0000\u0000R\f\u0001\u0000\u0000\u0000ST\u0007\u0006\u0000"
          + "\u0000T\u000e\u0001\u0000\u0000\u0000UV\u0007\u0007\u0000\u0000V\u0010"
          + "\u0001\u0000\u0000\u0000WX\u0007\b\u0000\u0000X\u0012\u0001\u0000\u0000"
          + "\u0000YZ\u0007\t\u0000\u0000Z\u0014\u0001\u0000\u0000\u0000[\\\u0005<"
          + "\u0000\u0000\\]\u0005<\u0000\u0000]\u0016\u0001\u0000\u0000\u0000^_\u0005"
          + ">\u0000\u0000_`\u0005>\u0000\u0000`\u0018\u0001\u0000\u0000\u0000ab\u0005"
          + ">\u0000\u0000bc\u0005>\u0000\u0000cd\u0005>\u0000\u0000d\u001a\u0001\u0000"
          + "\u0000\u0000ef\u0007\n\u0000\u0000f\u001c\u0001\u0000\u0000\u0000gh\u0005"
          + "<\u0000\u0000hi\u0005=\u0000\u0000i\u001e\u0001\u0000\u0000\u0000jk\u0007"
          + "\u000b\u0000\u0000k \u0001\u0000\u0000\u0000lm\u0005>\u0000\u0000mn\u0005"
          + "=\u0000\u0000n\"\u0001\u0000\u0000\u0000op\u0005=\u0000\u0000pq\u0005"
          + "=\u0000\u0000q$\u0001\u0000\u0000\u0000rs\u0005!\u0000\u0000st\u0005="
          + "\u0000\u0000t&\u0001\u0000\u0000\u0000uv\u0007\f\u0000\u0000v(\u0001\u0000"
          + "\u0000\u0000wx\u0007\r\u0000\u0000x*\u0001\u0000\u0000\u0000yz\u0007\u000e"
          + "\u0000\u0000z,\u0001\u0000\u0000\u0000{|\u0005&\u0000\u0000|}\u0005&\u0000"
          + "\u0000}.\u0001\u0000\u0000\u0000~\u007f\u0005|\u0000\u0000\u007f\u0080"
          + "\u0005|\u0000\u0000\u00800\u0001\u0000\u0000\u0000\u0081\u0082\u0007\u000f"
          + "\u0000\u0000\u00822\u0001\u0000\u0000\u0000\u0083\u0084\u0007\u0010\u0000"
          + "\u0000\u00844\u0001\u0000\u0000\u0000\u0085\u0087\u0007\u0011\u0000\u0000"
          + "\u0086\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000"
          + "\u0088\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000"
          + "\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b\u0006\u001a\u0000\u0000"
          + "\u008b6\u0001\u0000\u0000\u0000\u008c\u0090\u0003;\u001d\u0000\u008d\u008f"
          + "\u00039\u001c\u0000\u008e\u008d\u0001\u0000\u0000\u0000\u008f\u0092\u0001"
          + "\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001"
          + "\u0000\u0000\u0000\u0091\u009d\u0001\u0000\u0000\u0000\u0092\u0090\u0001"
          + "\u0000\u0000\u0000\u0093\u0094\u0007\u0012\u0000\u0000\u0094\u0098\u0003"
          + ";\u001d\u0000\u0095\u0097\u00039\u001c\u0000\u0096\u0095\u0001\u0000\u0000"
          + "\u0000\u0097\u009a\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000"
          + "\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u009c\u0001\u0000\u0000"
          + "\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009b\u0093\u0001\u0000\u0000"
          + "\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000"
          + "\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e8\u0001\u0000\u0000\u0000"
          + "\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a3\u0007\u0013\u0000\u0000"
          + "\u00a1\u00a4\u0003=\u001e\u0000\u00a2\u00a4\u0003E\"\u0000\u00a3\u00a1"
          + "\u0001\u0000\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5"
          + "\u0001\u0000\u0000\u0000\u00a5\u00a6\u0007\u0014\u0000\u0000\u00a6:\u0001"
          + "\u0000\u0000\u0000\u00a7\u00ab\u0007\u0015\u0000\u0000\u00a8\u00aa\u0007"
          + "\u0016\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ad\u0001"
          + "\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001"
          + "\u0000\u0000\u0000\u00ac<\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000"
          + "\u0000\u0000\u00ae\u00b6\u0007\u0017\u0000\u0000\u00af\u00b0\u0005\\\u0000"
          + "\u0000\u00b0\u00b5\u0005\'\u0000\u0000\u00b1\u00b2\u0005\\\u0000\u0000"
          + "\u00b2\u00b5\u0005\\\u0000\u0000\u00b3\u00b5\b\u0018\u0000\u0000\u00b4"
          + "\u00af\u0001\u0000\u0000\u0000\u00b4\u00b1\u0001\u0000\u0000\u0000\u00b4"
          + "\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6"
          + "\u00b7\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7"
          + "\u00b9\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9"
          + "\u00c7\u0007\u0017\u0000\u0000\u00ba\u00c2\u0007\u0019\u0000\u0000\u00bb"
          + "\u00bc\u0005\\\u0000\u0000\u00bc\u00c1\u0005\"\u0000\u0000\u00bd\u00be"
          + "\u0005\\\u0000\u0000\u00be\u00c1\u0005\\\u0000\u0000\u00bf\u00c1\b\u001a"
          + "\u0000\u0000\u00c0\u00bb\u0001\u0000\u0000\u0000\u00c0\u00bd\u0001\u0000"
          + "\u0000\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000"
          + "\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000"
          + "\u0000\u0000\u00c3\u00c5\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000"
          + "\u0000\u0000\u00c5\u00c7\u0007\u0019\u0000\u0000\u00c6\u00ae\u0001\u0000"
          + "\u0000\u0000\u00c6\u00ba\u0001\u0000\u0000\u0000\u00c7>\u0001\u0000\u0000"
          + "\u0000\u00c8\u00ca\u0007\u001b\u0000\u0000\u00c9\u00cb\u0007\u001c\u0000"
          + "\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000"
          + "\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000"
          + "\u0000\u00cd@\u0001\u0000\u0000\u0000\u00ce\u00cf\u0007\u001b\u0000\u0000"
          + "\u00cf\u00d1\u0007\u001d\u0000\u0000\u00d0\u00d2\u0007\u001e\u0000\u0000"
          + "\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000"
          + "\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000"
          + "\u00d4B\u0001\u0000\u0000\u0000\u00d5\u00dd\u0003E\"\u0000\u00d6\u00da"
          + "\u0007\u0012\u0000\u0000\u00d7\u00d9\u0007\u001f\u0000\u0000\u00d8\u00d7"
          + "\u0001\u0000\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8"
          + "\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00de"
          + "\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00d6"
          + "\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00e6"
          + "\u0001\u0000\u0000\u0000\u00df\u00e1\u0007\u0012\u0000\u0000\u00e0\u00e2"
          + "\u0007\u001f\u0000\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3"
          + "\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e4"
          + "\u0001\u0000\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000\u0000\u00e5\u00d5"
          + "\u0001\u0000\u0000\u0000\u00e5\u00df\u0001\u0000\u0000\u0000\u00e6\u00f0"
          + "\u0001\u0000\u0000\u0000\u00e7\u00e9\u0007 \u0000\u0000\u00e8\u00ea\u0007"
          + "!\u0000\u0000\u00e9\u00e8\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000"
          + "\u0000\u0000\u00ea\u00ec\u0001\u0000\u0000\u0000\u00eb\u00ed\u0007\u001f"
          + "\u0000\u0000\u00ec\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000"
          + "\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000"
          + "\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000\u00f0\u00e7\u0001\u0000"
          + "\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1D\u0001\u0000\u0000"
          + "\u0000\u00f2\u00fb\u0007\u001b\u0000\u0000\u00f3\u00f7\u0007\"\u0000\u0000"
          + "\u00f4\u00f6\u0007\u001f\u0000\u0000\u00f5\u00f4\u0001\u0000\u0000\u0000"
          + "\u00f6\u00f9\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000\u0000"
          + "\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00fb\u0001\u0000\u0000\u0000"
          + "\u00f9\u00f7\u0001\u0000\u0000\u0000\u00fa\u00f2\u0001\u0000\u0000\u0000"
          + "\u00fa\u00f3\u0001\u0000\u0000\u0000\u00fbF\u0001\u0000\u0000\u0000\u0017"
          + "\u0000\u0088\u0090\u0098\u009d\u00a3\u00ab\u00b4\u00b6\u00c0\u00c2\u00c6"
          + "\u00cc\u00d3\u00da\u00dd\u00e3\u00e5\u00e9\u00ee\u00f0\u00f7\u00fa\u0001"
          + "\u0006\u0000\u0000";
  public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}
