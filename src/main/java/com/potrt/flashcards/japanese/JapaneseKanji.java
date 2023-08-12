package com.potrt.flashcards.japanese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A {@link JapaneseKanji} holds information about a kanji and it's readings.
 */
public class JapaneseKanji {
    private Character kanji;
    private List<Reading> readings = new LinkedList<>();
    private Map<String, Reading> readingsMap = new HashMap<>();
    private List<JapanesePhrase> phrases = new ArrayList<>();

    /**
     * Creates a new {@link JapaneseKanji} with the kanji character.
     * @param kanji The kanji.
     */
    public JapaneseKanji (Character kanji) {
        this.kanji = kanji;
    }

    /**
     * Gets the kanji character.
     * @return The kanji.
     */
    public Character getKanji() {
        return kanji;
    }

    /**
     * Gets the number of phrases using this kanji.
     * @return The number of phrases.
     */
    public int numPhrases() {
        return phrases.size();
    }

    /**
     * Get the number of phrases using this kanji with a specific reading.
     * @param reading The kanji's reading.
     * @return The number of phrases.
     */
    public int numPhrases(String reading) {
        if (!readingsMap.containsKey(reading)) {
            return 0;
        }
        return readingsMap.get(reading).numPhrases();
    }

    /**
     * Gets the list of phrases that use this kanji.
     * @return The list of phrases.
     */
    public List<JapanesePhrase> getPhrases() {
        return phrases;
    }

    /**
     * Gets the list of phrases that use this kanji with a specfic reading.
     * @param reading The kanji's reading.
     * @return The list of phrases.
     */
    public List<JapanesePhrase> getPhrases(String reading) {
        if (!readingsMap.containsKey(reading)) {
            return new ArrayList<>();
        }
        return readingsMap.get(reading).getPhrases();
    }

    /**
     * Gets a list of readings, ordered by usage.
     * @return The list of furigana readings.
     */
    public List<String> getReadings() {
        return readings.stream().map(Reading::getFurigana).collect(Collectors.toList());
    }

    /**
     * Gets a {@link JapaneseKanjiWithReading} that represent a kanji and a specific reading.
     * @param reading The furigana reading.
     * @return The {@link JapaneseKanjiWithReading}.
     */
    public JapaneseKanjiWithReading withReading(String reading) {
        return new JapaneseKanjiWithReading(reading);
    }

    /**
     * A {@link JapaneseKanjiWithReading} represent a specfic reading of a kanji.
     * @apiNote When creating a new {@link JapanesePhrase}, the function {@code updateWithPhrase()} should be used.
     */
    public class JapaneseKanjiWithReading {
        private String furigana;

        /**
         * Creates a new {@link JapaneseKanjiWithReading} from the reading.
         * @param reading The furigana reading.
         */
        private JapaneseKanjiWithReading(String reading) {
            this.furigana = reading;
        }

        /**
         * Gets the kanji.
         * @return The kanji.
         */
        public Character getKanji() {
            return kanji;
        }

        /**
         * Gets the reading.
         * @return The furigana reading.
         */
        public String getFurigana() {
            return furigana;
        }

        /**
         * Updates the {@link JapaneseKanji} with the phrase it is used in.
         * @param phrase The phrase.
         */
        public void updateWithPhrase(JapanesePhrase phrase) {
            // Gets the reading.
            int i;
            Reading reading;
            if (readingsMap.containsKey(furigana)) {
                reading = readingsMap.get(furigana);
                i = readings.indexOf(reading);
                readings.remove(reading);
            } else {
                reading = new Reading(furigana);
                readingsMap.put(furigana, reading);
                i = readings.size();
            }
            
            // Adds the phrase to the kanji and the reading.
            phrases.add(phrase);
            reading.addPhrase(phrase);

            // Resort the readings.
            while (i != 0 && readings.get(i - 1).numPhrases() < reading.numPhrases()) {
                i--;
            }
            readings.add(i, reading);
        }

        /**
         * Gets the {@link JapaneseKanji}.
         * @return The {@link JapaneseKanji}.
         */
        public JapaneseKanji getJapaneseKanji() {
            return JapaneseKanji.this;
        }
    }

    /**
     * A {@link Reading} holds information about a specific reading for a kanji.
     */
    private class Reading {
        private String furigana;
        private List<JapanesePhrase> phrases = new ArrayList<>();

        public Reading(String furigana) {
            this.furigana = furigana;
        }

        public String getFurigana() {
            return furigana;
        }

        public int numPhrases() {
            return phrases.size();
        }

        public List<JapanesePhrase> getPhrases() {
            return phrases;
        }

        public void addPhrase(JapanesePhrase phrase) {
            phrases.add(phrase);
        }
    }
}
