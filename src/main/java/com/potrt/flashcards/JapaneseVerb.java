package com.potrt.flashcards;

/**
 * A {@link JapaneseVerb} represents a Japanese verb that can be conjugated.
 */
public class JapaneseVerb extends JapaneseFlashcard {
    private String kanjiBase;
    private String furiganaBase;

    /**
     * @implNote The ending is the same for both kanji and furigana.
     */
    private String ending;

    /**
     * Creates a new {@link JapaneseVerb} with the kanji and furigana bases, the ending, and the definition.
     * @param kanjiBase The kanji base.
     * @param furiganaBase The furigana base.
     * @param ending The dictionary form ending of the verb. This should be in furigana.
     * @param definition The definition
     */
    public JapaneseVerb(String kanjiBase, String furiganaBase, String ending, String definition) {
        super(kanjiBase + ending, furiganaBase + ending, definition);
        this.kanjiBase = kanjiBase;
        this.furiganaBase = furiganaBase;
        this.ending = ending;
    }

    /**
     * Creates a new {@link JapanesePhrase} that is a conjugated version of this verb to the given form.
     * @param form The {@link JapaneseVerbForm} that the verb is being conjugated to.
     * @return A {@link JapanesePhrase} representing this verb conjugated to the given form.
     */
    public JapanesePhrase conjugate(JapaneseVerbForm form) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the kanji base of the verb.
     * @return The kanji base.
     */
    public String getKanjiBase() {
        return kanjiBase;
    }

    /**
     * Gets the furigana base of the verb.
     * @return The furigana base.
     */
    public String getFuriganaBase() {
        return furiganaBase;
    }

    /**
     * Gets the dictionary form ending of the verb.  This should be in furigana.
     * @return The ending.
     */
    public String getEnding() {
        return ending;
    }

    /**
     * A {@link JapaneseVerbForm} represents a form/conjugation that a Japanese verb can be.
     */
    public enum JapaneseVerbForm {
    
    }    
}
