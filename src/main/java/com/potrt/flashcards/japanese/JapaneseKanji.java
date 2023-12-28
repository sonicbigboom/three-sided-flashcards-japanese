package com.potrt.flashcards.japanese;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.potrt.flashcards.Score;

/**
 * A {@link JapaneseKanji} holds information about a kanji and its readings.
 */
public class JapaneseKanji {
    private static Logger logger = Logger.getLogger(JapaneseKanji.class.getName());
    private Character kanji;
    private String meaning;
    /**
     * A map pairing furigana to {@link Reading}.
     */
    private Map<String, Reading> readingsMap = new HashMap<>();
    private Map<String, JapaneseWord> words = new HashMap<>();

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
     * Updates the meaning of the kanji.
     * @param meaning The updated meaning of the kanji.
     * @apiNote In theory the meaning of a kanji should not be changed.  If this method is called, a warning will be sent.
     */
    public void setMeaning(String meaning) {
        if (logger.isLoggable(Level.WARNING)) {
            logger.warning(String.format("Updated meaning of kanji '%c' from '%s' to '%s'.", kanji, this.meaning, meaning));
        }
        this.meaning = meaning;
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
     * Gets the set of {@link JapaneseWord}s that use this kanji.
     * @return The set of {@link JapaneseWord}s.
     */
    public Set<JapaneseWord> getWords() {
        return new HashSet<>(words.values());
    }

    /**
     * Gets the set of {@link JapaneseWord}s that use this kanji with a specfic reading.
     * @param reading The kanji's reading.
     * @return The set of {@link JapaneseWord}s.
     */
    public Set<JapaneseWord> getWords(String reading) {
        if (!readingsMap.containsKey(reading)) {
            return new HashSet<>();
        }
        return readingsMap.get(reading).getWords();
    }

    /**
     * Gets a list of readings, ordered by usage.
     * @return The list of furigana readings.
     */
    public List<String> getReadings() {
        return readingsMap.values().stream().sorted(Comparator.comparingInt(Reading::numWords).reversed()).map(Reading::getFurigana).collect(Collectors.toList());
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
        return new Score(readingsMap.values().stream().map(Reading::getScore).collect(Collectors.toList()));
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

        /**
         * Gets the {@link Score} for the reading.
         * @return The reading's {@link Score}.
         * @apiNote If no word has been created with this reading, an empty {@link Score} will be returned. 
         * But it will NOT be connected to this {@link JapaneseKanjiWithReading}.
         */
        public Score getScore() {
            return JapaneseKanji.this.getScore(furigana);
        }

        /**
         * Adds a new successful or failed attempt.
         * @param succeeded If the attempt was successful.
         * @apiNote If no word has been created with this reading, nothing will happen.
         */
        public void attempt(boolean succeeded) {
            Reading reading = readingsMap.get(furigana);
            if (reading == null) { return; }
            reading.attempt(succeeded);
        }

        /**
         * Gets the {@link JapaneseKanji}.
         * @return The {@link JapaneseKanji}.
         */
        public JapaneseKanji getJapaneseKanji() {
            return JapaneseKanji.this;
        }

        /**
         * Attached a {@link JapaneseWord} to the kanji and reading.
         * @param japaneseWord
         */
        void attachWord(JapaneseWord word) {
            Reading reading = readingsMap.computeIfAbsent(furigana, Reading::new);
            reading.attachWord(word);
        }
    }

    /**
     * A {@link Reading} holds information about a specific reading for a kanji.
     */
    private class Reading {
        private String furigana;
        private Map<String, JapaneseWord> words = new HashMap<>();
        private Score score = new Score();

        /**
         * Creates a new reading from the furigana reading.
         * @param reading The furigana reading.
         */
        Reading(String reading) {
            this.furigana = reading;
        }

        /**
         * Gets the furigana reading.
         * @return The furigana reading.
         */
        String getFurigana() {
            return furigana;
        }

        /**
         * Gets the number of words that use this reading.
         * @return The number of words.
         */
        int numWords() {
            return words.size();
        }

        /**
         * Gets the words that use this reading.
         * @return The set of {@link JapaneseWord}s.
         */
        Set<JapaneseWord> getWords() {
            return new HashSet<>(words.values());
        }

        /**
         * Attaches a word to this reading.
         * @param word The new word.
         */
        void attachWord(JapaneseWord word) {
            JapaneseKanji.this.words.put(word.getKanji(), word);
            words.put(word.getKanji(), word);
        }

        /**
         * Adds a new successful or failed attempt.
         * @param succeeded If the attempt was successful.
         */
        void attempt(boolean succeeded) {
            score.attempt(succeeded);
        }

        /**
         * Gets the {@link Score} for the reading.
         * @return The score.
         */
        Score getScore() {
            return score;
        }
    }
}
