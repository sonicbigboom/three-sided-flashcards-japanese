package com.potrt.flashcards;

/**
 * A {@link JapaneseWord} represents a Japanese word with it's kanji, furigana, and definition.
 */
public class JapaneseWord {
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
     * @param kanji The kanji
     * @param furigana The furigana
     * @param definition The definition
     */
    public JapaneseWord(String kanji, String furigana, String definition) {
        this.kanji = kanji;
        this.furigana = furigana;
        this.definition = definition;
    }

    /**
     * Child classes that have conjugation may not use a single string to represent kanji and furigana.
     */
    protected JapaneseWord() {}

    /**
     * Gets the kanji of the word.
     * @return The kanji.
     */
    public String getKanji() {
        return kanji;
    }

    /**
     * Sets the kanji of the word.
     * @param kanji The kanji
     */
    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    /**
     * Gets the furigana of the word.
     * @return The furigana.
     */
    public String getFurigana() {
        return furigana;
    }

    /**
     * Sets the furigana of the word.
     * @param furigana The furigana.
     */
    public void setFurigana(String furigana) {
        this.furigana = furigana;
    }

    /**
     * Gets the defintion of the word.
     * @return The defintion.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Sets the defintion of the word.
     * @param definition The defintion.
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
