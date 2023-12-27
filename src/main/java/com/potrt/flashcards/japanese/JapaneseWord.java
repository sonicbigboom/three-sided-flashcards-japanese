package com.potrt.flashcards.japanese;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;

/**
 * A {@link JapaneseWord} represents a Japanese word with it's kanji, furigana, and definition.
 * This is an abstract class and implementations will usually be created with {@link JapaneseWordBuilder}.
 */
public class JapaneseWord {
    /**
     * @implNote Used for default kanji getter.
     */
    protected String kanji;

    /**
     * @implNote Used for default furigana getter.
     */
    protected String furigana;

    /**
     * @implNote Used for default defintion getter.
     */
    protected String definition;

    /**
     * The list of kana.
     * @implNote Each object in this list is either a {@link JapaneseKanjiWithReading} or a {@link String}.
     */
    protected List<Object> kanaList = new ArrayList<>();

    /**
     * A list of all kanji.
     * @implNote This is just used as a shortcut for certain methods and it does not need to be in order.
     */
    protected List<JapaneseKanjiWithReading> kanjiList = new ArrayList<>();

    /**
     * Creates a {@link JapaneseWord} from a list of kana and a list of kanji.
     * @param builder The {@link JapaneseWordBuilder} that is ready to create the string.
     * @param definition The meaning of the string.
     * @apiNote This method should only be called from within {@link JapaneseWordBuilder}.
     */
    protected JapaneseWord(JapaneseWordBuilder builder, String definition) {
        this.kanji = builder.getKanji();
        this.furigana = builder.getFurigana();
        this.definition = definition;
        this.kanaList = builder.kanaList;
        this.kanjiList = builder.kanjiList;

        for (JapaneseKanjiWithReading reading : kanjiList) {
            reading.associate(this);
        }
    } 

    /**
     * Gets the kanji of the word.
     * @return The kanji.
     */
    public String getKanji() {
        return kanji;
    }

    /**
     * Gets the furigana of the word.
     * @return The furigana.
     */
    public String getFurigana() {
        return furigana;
    }

    /**
     * Gets the defintion of the word.
     * @return The defintion.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Returns an {@link JapaneseWord.Representation} of the {@link JapaneseWord} .
     * @return The associated {@link JapaneseWord.Representation}.
     */
    public Representation getRepresentation() {
        return new Representation(kanji, furigana, definition);
    }

    /**
     * Adds a new successful or failed attempt for each kanji.
     * @param succeeded If the attempt was successful.
     */
    public void attempt(boolean succeeded) {
        for (JapaneseKanjiWithReading k : kanjiList) {
            k.attempt(succeeded);
        }
    }

    /**
     * Removes the words associations to {@link JapaneseKanji}.
     * @apiNote An instance that called this method should no longer be used.
     */
    public void destroy() {
        for (JapaneseKanjiWithReading k : kanjiList) {
            k.disassociate(this.getKanji());
        }
        kanaList = null;
        kanjiList = null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getKanji());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JapaneseWord)) {
            return false;
        }   
        JapaneseWord other = (JapaneseWord) obj;

        return this.getKanji().equals(other.getKanji());
    }

    public class Representation {
        private String kanji;
        private String furigana;
        private String definition;

        /**
         * Creates a new {@link JapaneseWord.Representation} with the kanji, the furigana, and the definition.
         * @param kanji The kanji.
         * @param furigana The furigana.
         * @param definition The definition.
         */
        public Representation(String kanji, String furigana, String definition) {
            this.kanji = kanji;
            this.furigana = furigana;
            this.definition = definition;
        }

        /**
         * Gets the kanji of the representation.
         * @return The kanji.
         */
        public String getKanji() {
            return kanji;
        }

        /**
         * Gets the furigana of the representation.
         * @return The furigana.
         */
        public String getFurigana() {
            return furigana;
        }

        /**
         * Gets the defintion of the representation.
         * @return The defintion.
         */
        public String getDefinition() {
            return definition;
        }

        /**
         * Returns the {@link JapaneseWord} that this representation is based on.
         * @return The containing {@link JapaneseWord}.
         */
        public JapaneseWord getWord() {
            return JapaneseWord.this;
        }
    }
}
