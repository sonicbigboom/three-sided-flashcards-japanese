package com.potrt.flashcards.japanese;

import java.util.ArrayList;
import java.util.List;

import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;

/**
 * A {@link JapaneseWordBuilder} is used to create a {@link JapaneseWord}.
 */
public class JapaneseWordBuilder {
    /**
     * The list of kana.
     * @implNote Each object in this list is either a {@link JapaneseKanjiWithReading} or a {@link String}.
     */
    List<Object> kanaList = new ArrayList<>();
    /**
     * A list of all kanji.
     * @implNote This is just used as a shortcut for certain methods.
     */
    List<JapaneseKanjiWithReading> kanjiList = new ArrayList<>();
    
    /**
     * Adds a new kanji to the string.
     * @param kanji A {@link JapaneseKanjiWithReading} so that it is known how the kanji must be read.
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
     * Generates the {@link JapaneseWord} with a definition.
     * @param definition The meaning of the string.
     * @return The {@link JapaneseWord}.
     */
    public JapaneseWord getJapaneseWord(String definition) {
        return new JapaneseWord(this, definition);
    }

    /**
     * Gets the string of kana made up of kanji and furigana.
     * @return A string of Japanese characters using both kanji and furigana.
     */
    String getKanji() {
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
    String getFurigana() {
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
}
