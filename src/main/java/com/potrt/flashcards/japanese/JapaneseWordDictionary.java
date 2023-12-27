package com.potrt.flashcards.japanese;

import java.util.HashMap;
import java.util.Map;

import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;

/**
 * A {@link JapaneseWordDictionary} represents a dicitonary of {@link JapaneseWord}s.
 */
public class JapaneseWordDictionary {
    private Map<String, JapaneseWord> wordMap = new HashMap<>();
    private JapaneseKanjiDictionary kanjiDictionary;
    
    /**
     * Creates a dictionary for Japanese words with a reference to a {@link KanjiDictionary}.  
     * This way no words can be added that are using kanji not within the given dictionary.
     * @param kanjiDictionary The {@link KanjiDictionary} to associate.
     */
    public JapaneseWordDictionary(JapaneseKanjiDictionary kanjiDictionary) {
        this.kanjiDictionary = kanjiDictionary;
    }

    /**
     * Adds a new {@link JapaneseWord} to the dictionary, with its kanji as a key.
     * @param word The new {@link JapaneseWord}.
     * @throws IllegalArgumentException Thrown if a word has kanji not found in the associated kanji dictionary.
     */
    public void put(JapaneseWord word) {
        for (JapaneseKanjiWithReading reading : word.kanjiList) {
            if (!kanjiDictionary.contains(reading.getJapaneseKanji())) {
                throw new IllegalArgumentException(
                    String.format("Tried to add word '%s' with kanji '%c' that is not found in the associated KanjiDictionary.", 
                        word.getKanji(), 
                        reading.getKanji()));
            }
        }

        if (wordMap.containsKey(word.getKanji())) {
            throw new IllegalStateException(
                String.format("Tried to add word '%s' when a word with this kanji already exists in the dictionary.", 
                    word.getKanji()));
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
     * Checks whether the dictionary contains a word with the given kanji.
     * @param kanji The kanji to check.
     * @return Whether the dictionary has a word with this kanji.
     */
    public boolean contains(String kanji) {
        return wordMap.containsKey(kanji);
    }
}
