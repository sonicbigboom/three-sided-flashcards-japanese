package com.potrt.flashcards.japanese;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A {@link JapaneseKanjiDictionary} represents a dictionary of {@link JapaneseKanji}. 
 */
public class JapaneseKanjiDictionary  {
    private static Logger logger = Logger.getLogger(JapaneseKanjiDictionary.class.getName());
    private Map<Character, JapaneseKanji> kanjiMap = new HashMap<>();

    /**
     * Creates and adds a new kanji to the dictionary.
     * @param kanji The kanji.
     * @param meaning The meaning.
     * @return The create {@link JapaneseKanji}.
     * @apiNote If this kanji is already in the dictionary, it's meaning will be replaced.
     *          In theory this should not happen, so a warning will be sent as well.
     */
    public JapaneseKanji create(Character kanji, String meaning) {
        if (kanjiMap.containsKey(kanji)) {
            JapaneseKanji original = kanjiMap.get(kanji);
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Kanji '%c' has already been created.", kanji));
            }
            if (!original.getMeaning().equals(meaning)) {
                original.setMeaning(meaning);
            }
            return original;
        }

        JapaneseKanji japaneseKanji = new JapaneseKanji(kanji, meaning);
        kanjiMap.put(kanji, japaneseKanji);
        return japaneseKanji;
    }

    /**
     * Returns the {@link JapaneseKanji} for the given kanji character.
     * @param kanji The kanji.
     * @return The associated {@link JapaneseKanji}.
     * @apiNote If the reading doesn't exist, a new kanji with a meaning of '?' is created and a warning is sent.
     */
    public JapaneseKanji get(Character kanji) {
        if (!kanjiMap.containsKey(kanji)) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Kanji '%c' does not yet exist, so its default meaning is set to '?'.", kanji));
            }
            create(kanji, "?");
        }

        return kanjiMap.get(kanji);
    }

    /**
     * Checks if the dictionary contains the same {@link JapaneseKanji}.
     * @param japaneseKanji The {@link JapaneseKanji} to check.
     * @return Whether the dictionary contains the refrence.
     */
    public boolean contains(JapaneseKanji japaneseKanji) {
        return japaneseKanji == kanjiMap.get(japaneseKanji.getKanji());
    }
}
