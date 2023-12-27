package com.potrt.flashcards.japanese;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link JapaneseWordDictionary} represents a dicitonary of {@link JapaneseWord}s.
 */
public class JapaneseWordDictionary {
    private Map<String, JapaneseWord> wordMap = new HashMap<>();
    
    /**
     * Adds a new {@link JapaneseWord} to the dictionary, with its kanji as a key.
     * @param word The new {@link JapaneseWord}.
     */
    public void put(JapaneseWord word) {
        wordMap.put(word.getKanji(), word);
    }

    /**
     * Gets the {@link JapaneseWord} for the given kanji.
     * @param kanji The kanji for the {@link JapaneseWord}.
     * @return The associated {@link JapaneseWord}.
     */
    public JapaneseWord get(String kanji) {
        return wordMap.get(kanji);
    }
}
