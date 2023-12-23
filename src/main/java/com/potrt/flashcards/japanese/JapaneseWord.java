package com.potrt.flashcards.japanese;

import java.util.Objects;

/**
 * A {@link JapaneseWord} represents a Japanese word with it's kanji, furigana, and definition.
 */
public abstract class JapaneseWord {
    /**
     * @implNote Used for default kanji getter and setter.
     */
    protected String kanji;

    /**
     * @implNote Used for default furigana getter and setter.
     */
    protected String furigana;

    /**
     * @implNote Used for default defintion getter and setter.
     */
    protected String definition;

    /**
     * Creates a new {@link JapaneseWord} with the kanji, the furigana, and the definition.
     * @param kanji The kanji.
     * @param furigana The furigana.
     * @param definition The definition.
     */
    protected JapaneseWord(String kanji, String furigana, String definition) {
        this.kanji = kanji;
        this.furigana = furigana;
         this.definition = definition;
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
