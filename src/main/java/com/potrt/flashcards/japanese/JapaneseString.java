package com.potrt.flashcards.japanese;

import java.util.ArrayList;
import java.util.List;

import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;

/**
 * Represents a string of Japanese characters.
 */
public class JapaneseString {
    /**
     * The list of kana.
     * @implNote Each object in this list is either a {@link JapaneseKanjiWithReading} or a {@link String}.
     */
    private List<Object> kanaList = new ArrayList<>();
    private List<JapaneseKanjiWithReading> kanjiList = new ArrayList<>();

    /**
     * Gets the string of kana made up of kanji and furigana.
     * @return A string of Japanese characters using both kanji and furigana.
     */
    public String getKanji() {
        StringBuilder out = new StringBuilder();
        for (Object kana : kanaList) {
            if (kana instanceof JapaneseKanjiWithReading) {
                JapaneseKanjiWithReading kanji = (JapaneseKanjiWithReading) kana;
                out.append(kanji.getKanji());
            } else {
                // Here 'characters' must be a string.
                out.append(kana);
            }
        }
        return out.toString();
    }

    /**
     * Gets the string of kana made up of only furigana.
     * @return A string of Japanese characters using only furigana.
     */
    public String getFurigana() {
        StringBuilder out = new StringBuilder();
        for (Object characters : kanaList) {
            if (characters instanceof JapaneseKanjiWithReading) {
                JapaneseKanjiWithReading kanji = (JapaneseKanjiWithReading) characters;
                out.append(kanji.getFurigana());
            } else {
                // Here 'characters' must be a string.
                out.append(characters);
            }
        }
        return out.toString();
    }

    /**
     * Adds a new kanji to the string.
     * @param kanji A {@link JapaneseKanjiWithReading} so that it is know how the kanji must be read.
     */
    public void add(JapaneseKanjiWithReading kanji) {
        kanaList.add(kanji);
        this.kanjiList.add(kanji);
    }
    
    /**
     * Adds furigana to the string.
     * @param furigana The furigana.
     */
    public void add(String furigana) {
        kanaList.add(furigana);
    }

    /**
     * Adds a new successful or failed attempt for each kanji.
     * @param succeeded If the attempt was successful.
     */
    public void attempt(boolean succeeded) {
        for (JapaneseKanjiWithReading kanji : kanjiList) {
            kanji.attempt(succeeded);
        }
    }

    /**
     * Assigns a {@link JapaneseWord} for each {@link Kanji}.
     * @param word The word.
     * @apiNote This should only be called once, and the {@link JapaneseString} should not be changed afterwards.
     */
    public void assignWord(JapaneseWord word) {
        for (JapaneseKanjiWithReading kanji : kanjiList) {
            kanji.assignWord(word);
        }
    }
}
