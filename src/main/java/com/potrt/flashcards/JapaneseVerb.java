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
    private JapaneseVerbEnding ending;
    private JapaneseVerbType verbType;

    /**
     * Creates a new {@link JapaneseVerb} with the kanji and furigana bases, the ending, and the definition.
     * @param kanjiBase The kanji base.
     * @param furiganaBase The furigana base.
     * @param ending The dictionary form ending of the verb.
     * @param verbType The verb's type: U, RU, or IRREGULAR.
     * @param definition The definition
     */
    public JapaneseVerb(String kanjiBase, String furiganaBase, JapaneseVerbEnding ending, JapaneseVerbType verbType, String definition) {
        super(kanjiBase + ending, furiganaBase + ending, definition);
        this.kanjiBase = kanjiBase;
        this.furiganaBase = furiganaBase;
        this.ending = ending;
        this.verbType = verbType;
    }

    /**
     * Creates a new {@link JapanesePhrase} that is a conjugated version of this verb to the given form.
     * @param form The {@link JapaneseVerbForm} that the verb is being conjugated to.
     * @return A {@link JapanesePhrase} representing this verb conjugated to the given form.
     */
    public JapanesePhrase conjugate(JapaneseVerbForm form) {
        String conjugatedEnding;
        switch (verbType) {
            case GODAN:
                conjugatedEnding = JapaneseVerbConjugator.conjugateGodanVerb(form, ending);
                break;
            case ICHIDAN:
                conjugatedEnding = JapaneseVerbConjugator.conjugateIchidanVerb(form);
                break;
            case IRREGULAR:
            default:
                throw new UnsupportedOperationException();
        }
        return new JapanesePhrase(kanjiBase + conjugatedEnding, furiganaBase + conjugatedEnding, getDefinition() + "(" + form.toString() + ")");
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
    public JapaneseVerbEnding getEnding() {
        return ending;
    } 

    /**
     * Represents a Japanese verb type.
     */
    public enum JapaneseVerbType {
        GODAN,
        ICHIDAN,
        IRREGULAR
    }
}
