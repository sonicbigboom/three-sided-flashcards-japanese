package com.potrt.flashcards.japanese.verb;

import com.potrt.flashcards.japanese.JapaneseFlashcard;
import com.potrt.flashcards.japanese.JapanesePhrase;
import com.potrt.flashcards.japanese.verb.conjugation.JapaneseVerbConjugator;

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
     * Creates a new {@link JapaneseVerb} with the kanji and furigana bases, the ending, the verb type, and the definition.
     * @param kanjiBase The kanji base.
     * @param furiganaBase The furigana base.
     * @param ending The dictionary form ending of the verb.
     * @param definition The definition.
     * @param verbType The verb's type: godan, ichidan, or irregular.
     * @apiNote Irregular verbs are probably best created with one of the other constructors.
     */
    public JapaneseVerb(String kanjiBase, String furiganaBase, JapaneseVerbEnding ending, String definition, JapaneseVerbType verbType) {
        super(kanjiBase + ending, furiganaBase + ending, definition);
        this.kanjiBase = kanjiBase;
        this.furiganaBase = furiganaBase;
        this.ending = ending;
        this.verbType = verbType;
    }

    /**
     * Creates a new {@link JapaneseVerb} with the kanji, furigana, the ending, the verb type, and the definition.
     * @param kanji The kanji.
     * @param furigana The furigana.
     * @param definition The definition.
     * @param verbType The verb's type: godan, ichidan, or irregular.
     * @throws IllegalArgumentException Thrown if the kanji and furigana endings do not match, or it is not a valid verb ending.
     */
    public JapaneseVerb(String kanji, String furigana, String definition, JapaneseVerbType verbType) {
        super(kanji, furigana, definition);
        this.kanjiBase = kanji.substring(0, kanji.length()-1);
        this.furiganaBase = furigana.substring(0, furigana.length()-1);
        if (kanji.charAt(kanji.length()-1) != furigana.charAt(furigana.length()-1)) { throw new IllegalArgumentException("Kanji and furigana verb ending must be the same."); }
        this.ending = JapaneseVerbEnding.from(kanji.substring(kanji.length()-1));
        this.verbType = verbType;
    }

    /**
     * Creates a new {@link JapaneseVerb} with the {@link JapanesePhrase} and the verb type.
     * @param verb The verb as a {@link JapanesePhrase}.
     * @param verbType The verb's type: godan, ichidan, or irregular.
     * @throws IllegalArgumentException Thrown if the kanji and furigana endings do not match, or it is not a valid verb ending.
     */
    public JapaneseVerb(JapanesePhrase verb, JapaneseVerbType verbType) {
        this(verb.getKanji(), verb.getFurigana(), verb.getDefinition(), verbType);
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
                String conjugatedKana = JapaneseVerbConjugator.conjugateIrregularVerb(form, getKanji());
                return new JapanesePhrase(conjugatedKana, conjugatedKana, getDefinition() + " (" + form.toString() + ")");
        }
        return new JapanesePhrase(kanjiBase + conjugatedEnding, furiganaBase + conjugatedEnding, getDefinition() + " (" + form.toString() + ")");
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
