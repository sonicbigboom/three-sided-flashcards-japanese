package com.potrt.flashcards;

import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;

public interface TestingConstants {
    public static final String wordIKanji = "私";
    public static final String wordIFurigana = "わたし";
    public static final String wordIDefinition = "I";
    public static final String phraseOnePersonKanji = "一人";
    public static final String phraseOnePersonFurigana = "ひとり";
    public static final String phraseOnePersonDefinition = "one person";
    public static final String godanVerbToDrinkKanjiBase = "飲";
    public static final String godanVerbToDrinkFuriganaBase = "の";
    public static final String godanVerbToDrinkEnding = "む";
    public static final String godanVerbToDrinkPoliteNegativeEnding = "みません";
    public static final String godanVerbToDrinkDefinition = "to drink";
    public static final String godanVerbToPlayKanjiBase = "遊";
    public static final String godanVerbToPlayFuriganaBase = "あそ";
    public static final String godanVerbToPlayEnding = "ぶ";
    public static final String godanVerbToPlayPoliteEnding = "びます";
    public static final String godanVerbToPlayNegativeEnding = "ばない";
    public static final String godanVerbToPlayDefinition = "to play";
    public static final String ichidanVerbToSeeKanjiBase = "見";
    public static final String ichidanVerbToSeeFuriganaBase = "み";
    public static final String ichidanVerbToSeePastEnding = "た";
    public static final String ichidanVerbToSeeDefinition = "to see";
    public static final String irregularVerbToComeKana = "くる";
    public static final String irregularVerbToComePoliteNegativeKana = "きません";
    public static final String irregularVerbToComeDefinition = "to come";
    public static final String irregularVerbToDoKana = "する";
    public static final String irregularVerbToDoPolite = "します";
    public static final String irregularVerbToDoDefinition = "to do";
    public static final String presentIdicative = "Present Indicative";
    public static final String pastIdicative = "Past Indicative";
    public static final String definitionPresentIndicative = presentIdicative;   
    public static final String definitionPresentIndicativePolite = "Polite " + presentIdicative;  
    public static final String definitionPresentIndicativeNegative = "Negative " + presentIdicative;
    public static final String definitionPresentIndicativePoliteNegative = "Polite Negative " + presentIdicative;
    public static final String definitionPastIndicative = pastIdicative;   
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
