package com.potrt.flashcards.japanese.word.verb;

import com.potrt.flashcards.japanese.word.JapaneseWord;
import com.potrt.flashcards.japanese.word.JapaneseWordBuilder;
import com.potrt.flashcards.japanese.word.verb.conjugation.JapaneseVerbConjugator;

/**
 * A {@link JapaneseVerb} represents a Japanese verb that can be conjugated.
 */
public class JapaneseVerb extends JapaneseWord {
    private String kanjiBase;
    private String furiganaBase;

    /**
     * @implNote The ending is the same for both kanji and furigana.
     */
    private JapaneseVerbEnding ending;
    private JapaneseVerbType verbType;

    /**
     * Creates a new {@link JapaneseVerb} with the kanji, furigana, the ending, the verb type, and the definition.
     * @param builder The {@link JapaneseWordBuilder} that is ready to create the verb.
     * @param definition The meaning of the string.
     * @param verbType The verb's type: godan, ichidan, or irregular.
     */
    public JapaneseVerb(JapaneseWordBuilder builder, String definition, JapaneseVerbType verbType) {
        super(builder, definition);
        this.kanjiBase = kanji.substring(0, kanji.length()-1);
        this.furiganaBase = furigana.substring(0, furigana.length()-1);
        this.ending = JapaneseVerbEnding.from(kanji.substring(kanji.length()-1));
        this.verbType = verbType;
    }

    /**
     * Creates a {@link JapaneseWord.Representation} of the {@link JapaneseVerb} that is the relevant conjugated version.
     * @param form The {@link JapaneseVerbForm} that the verb is being conjugated to.
     * @return A {@link JapaneseWord} representing this verb conjugated to the given form.
     */
    public JapaneseWord.Representation conjugate(JapaneseVerbForm form) {
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
                String conjugatedKana = JapaneseVerbConjugator.conjugateIrregularVerb(form, getKanji());
                return new Representation(conjugatedKana, conjugatedKana, getDefinition() + " (" + form.toString() + ")");
        }
        return new Representation(kanjiBase + conjugatedEnding, furiganaBase + conjugatedEnding, getDefinition() + " (" + form.toString() + ")");
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

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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
