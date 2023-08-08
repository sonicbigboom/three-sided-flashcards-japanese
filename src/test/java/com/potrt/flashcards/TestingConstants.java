package com.potrt.flashcards;

import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;

public interface TestingConstants {
    public static final String wordIKanji = "私";
    public static final String wordIFurigana = "わたし";
    public static final String wordIDefinition = "I";
    public static final String phraseOnePersonKanji = "一人";
    public static final String phraseOnePersonFurigana = "ひとり";
    public static final String phraseOnePersonDefinition = "one person";
    public static final String verbToDrinkKanjiBase = "飲";
    public static final String verbToDrinkFuriganaBase = "の";
    public static final String verbToDrinkEnding = "む";
    public static final String verbToDrinkDefinition = "to drink";
    public static final String presentIdicative = "Present Indicative";
    public static final String pastIdicative = "Past Indicative";
    public static final String nuDictionaryEnding = "ぬ";
    public static final String guDictionaryEnding = "ぐ";
    public static final String suDictionaryEnding = "す";
    public static final String suPresentIndicativePoliteEnding = "します";
    public static final String tsuDictionaryEnding = "つ";
    public static final String tsuPresentIndicativeNegativeEnding = "たない";
    public static final String ichidanDictionaryEnding = "る";
    public static final String ichidanPastIndicative = "た";
    public static final JapaneseVerbForm verbFormDictionary = new JapaneseVerbForm(true, true, presentIdicative);
}
