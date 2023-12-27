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
     * @throws IllegalArgumentException There is a prior {{@link JapaneseWord} with the same kanji but different furigana.
     * @apiNote If there is a {@link JapaneseWord} with the same kanji but different furigana, it must first be destroyed. 
     */
    public void put(JapaneseWord word) {
        if (wordMap.containsKey(word.getKanji())) {
            JapaneseWord original = wordMap.get(word.getKanji());
            if (!original.getFurigana().equals(word.getFurigana())) {
                throw new IllegalArgumentException(
                    String.format("Attempted to put a word with kanji '%s' and furigana '%s' when such a word exists with different furigana '%s'."
                                + "If this word should be replaced, call destroy first and rebuild the kanji.",
                                word.getKanji(), word.getFurigana(), original.getFurigana()));
            }
        }
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

    /**
     * Removes and destroys a {@link JapaneseWord}.  This means it is also removed from the associated {@link JapaneseKanji} and should never be used again. 
     * @param kanji The kanji for the word to be destroyed.
     */
    public void destroy(String kanji) {
        JapaneseWord word = wordMap.remove(kanji);
        if (word == null) { return; }
        word.destroy();
    }
}
