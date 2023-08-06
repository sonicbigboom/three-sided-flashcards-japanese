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
     * @param ending The ending for both kanji and furigana. This should be in furigana.
     * @param definition The definition
     */
    public JapaneseVerb(String kanjiBase, String furiganaBase, String ending, String definition) {
        super(kanjiBase + ending, furiganaBase + ending, definition);
        this.kanjiBase = kanjiBase;
        this.furiganaBase = furiganaBase;
    }

    /**
     * Creates a new {@link JapaneseWord} that is a conjugated version of this verb to the given form.
     * @param form The {@link JapaneseVerbForm} that the verb is being conjugated to.
     * @return A {@link JapaneseWord} representing this verb conjugated to the given form.
     */
    public JapaneseWord conjugate(JapaneseVerbForm form) {
        throw new UnsupportedOperationException();
    }

    /**
     * A {@link JapaneseVerbForm} represents a form/conjugation that a Japanese verb can be.
     */
    public enum JapaneseVerbForm {
    
    }    
}
