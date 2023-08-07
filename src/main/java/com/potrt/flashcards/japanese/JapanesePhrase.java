package com.potrt.flashcards.japanese;

/**
 * A {@link JapanesePhrase} represents a Japanese phrase with it's kanji, furigana, and definition.
 */
public class JapanesePhrase {
    
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
     * Creates a new {@link JapanesePhrase} with the kanji, the furigana, and the definition.
     * @param kanji The kanji.
     * @param furigana The furigana.
     * @param definition The definition.
     */
    public JapanesePhrase(String kanji, String furigana, String definition) {
        this.kanji = kanji;
        this.furigana = furigana;
        this.definition = definition;
    }

    /**
     * Gets the kanji of the phrase.
     * @return The kanji.
     */
    public String getKanji() {
        return kanji;
    }

    /**
     * Gets the furigana of the phrase.
     * @return The furigana.
     */
    public String getFurigana() {
        return furigana;
    }

    /**
     * Gets the defintion of the phrase.
     * @return The defintion.
     */
    public String getDefinition() {
        return definition;
    }
}
