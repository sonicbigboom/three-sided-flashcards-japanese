package com.potrt.flashcards.japanese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.potrt.flashcards.Score;

/**
 * A {@link JapaneseKanji} holds information about a kanji and it's readings.
 */
public class JapaneseKanji {
    private Character kanji;
    private String meaning;
    private List<Reading> readings = new LinkedList<>();
    private Map<String, Reading> readingsMap = new HashMap<>();
    private List<JapaneseWord> words = new ArrayList<>();

    /**
     * Creates a new {@link JapaneseKanji} with the kanji character and its meaning.
     * @param kanji The kanji.
     * @param meaing The meaning of the kanji, though it's definition may change depending on the context and {@link JapaneseWord}.
     */
    public JapaneseKanji (Character kanji, String meaning) {
        this.kanji = kanji;
        this.meaning = meaning;
    }

    /**
     * Gets the kanji character.
     * @return The kanji.
     */
    public Character getKanji() {
        return kanji;
    }

    /**
     * Gets the meaning of the kanji, though it's definition may change depending on the context and {@link JapaneseWord}.
     * @return The meaning of the kanji.
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * Gets the number of words using this kanji.
     * @return The number of words.
     */
    public int numWords() {
        return words.size();
    }

    /**
     * Get the number of words using this kanji with a specific reading.
     * @param reading The kanji's reading.
     * @return The number of words.
     */
    public int numWords(String reading) {
        if (!readingsMap.containsKey(reading)) {
            return 0;
        }
        return readingsMap.get(reading).numWords();
    }

    /**
     * Gets the list of words that use this kanji.
     * @return The list of words.
     */
    public List<JapaneseWord> getWords() {
        return words;
    }

    /**
     * Gets the list of words that use this kanji with a specfic reading.
     * @param reading The kanji's reading.
     * @return The list of words.
     */
    public List<JapaneseWord> getWords(String reading) {
        if (!readingsMap.containsKey(reading)) {
            return new ArrayList<>();
        }
        return readingsMap.get(reading).getWords();
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
     * Gets the aggregated {@link Score} for the kanji.
     * @return The aggregated {@link Score}.
     * @apiNote This {@link Score} should only be used for getting information.  Any edits will not impact this kanji's score.
     */
    public Score getScore() {
        return new Score(readings.stream().map(Reading::getScore).collect(Collectors.toList()));
    }

    /**
     * Gets the {@link Score} for a reading of this kanji.
     * @param reading The furigana reading.
     * @return The {@link Score} for this reading.
     * @apiNote If the score does not exist, an empty {@link Score} will be returned, but it will NOT be connected to this {@link JapaneseKanji}.
     */
    public Score getScore(String reading) {
        if (!readingsMap.containsKey(reading)) {
            return new Score();
        }
        return readingsMap.get(reading).getScore();
    }

    /**
     * A {@link JapaneseKanjiWithReading} represent a specfic reading of a kanji.
     * @apiNote When creating a new {@link JapaneseWord}, the function {@code updateWithWord()} should be used.
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

        // TODO: Get {@link Reading} pointer at creation. Replace all reading getters with using that pointer.
        /**
         * Updates the {@link JapaneseKanji} with the word it is used in.
         * @param word The word.
         */
        public void assignWord(JapaneseWord word) {
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
            
            // Adds the word to the kanji and the reading.
            words.add(word);
            reading.addWord(word);

            // Resort the readings.
            while (i != 0 && readings.get(i - 1).numWords() < reading.numWords()) {
                i--;
            }
            readings.add(i, reading);
        }

        /**
         * Gets the {@link Score} for the reading.
         * @return The reading's {@link Score}.
         */
        public Score getScore() {
            return JapaneseKanji.this.getScore(furigana);
        }

        /**
         * Adds a new successful or failed attempt.
         * @param succeeded If the attempt was successful.
         */
        public void attempt(boolean succeeded) {
            readingsMap.get(furigana).attempt(succeeded);
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
        private List<JapaneseWord> words = new ArrayList<>();
        private Score score = new Score();

        /**
         * Creates a new reading from the furigana reading.
         * @param reading The furigana reading.
         */
        public Reading(String reading) {
            this.furigana = reading;
        }

        /**
         * Gets the furigana reading.
         * @return The furigana reading.
         */
        public String getFurigana() {
            return furigana;
        }

        /**
         * Gets the number of words that use this reading.
         * @return The number of words.
         */
        public int numWords() {
            return words.size();
        }

        /**
         * Gets the words that use this reading.
         * @return The list of words.
         */
        public List<JapaneseWord> getWords() {
            return words;
        }

        /**
         * Adds a new word for this reading.
         * @param word The new word.
         */
        public void addWord(JapaneseWord word) {
            words.add(word);
        }

        /**
         * Adds a new successful or failed attempt.
         * @param succeeded If the attempt was successful.
         */
        public void attempt(boolean succeeded) {
            score.attempt(succeeded);
        }

        /**
         * Gets the {@link Score} for the reading.
         * @return The score.
         */
        public Score getScore() {
            return score;
        }
    }
}
