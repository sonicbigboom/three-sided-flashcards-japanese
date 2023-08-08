package com.potrt.flashcards.japanese;

import com.potrt.flashcards.Flashcard;
import com.potrt.flashcards.japanese.verb.JapaneseVerb;
import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;

/**
 * A {@link JapaneseFlashcard} represents a Japanese phrase with three descriptions: the kanji, the furigana, and the definition.
 */
public class JapaneseFlashcard extends Flashcard {
    /**
     * Creates a new {@link JapaneseFlashcard} with the kanji, the furigana, and the definition.
     * @param kanji The kanji for the phrase.
     * @param furigana The furigana for the phrase.
     * @param definition The definition for the phrase.
     */
    public JapaneseFlashcard(String kanji, String furigana, String definition) {
        super(kanji, furigana, definition);
    }

    /**
     * Creates a new {@link JapaneseFlashcard} with a {@link JapanesePhrase}.
     * @param phrase The phrase.
     */
    public JapaneseFlashcard(JapanesePhrase phrase) {
        this(phrase.getKanji(), phrase.getFurigana(), phrase.getDefinition());
    }

    /**
     * Creates a new {@link JapaneseFlashcard} with a {@link JapaneseVerb} conjugated to the given form.
     * @param verb The verb.
     * @param form The form that the verb will be conjugated to.
     */
    public JapaneseFlashcard(JapaneseVerb verb, JapaneseVerbForm form) {
        this(verb.conjugate(form));
        sides[2] += " (" + form.toString() + ")";
    }     

    /**
     * Gets the kanji of the phrase.
     * @return The kanji.
     */
    public String getKanji() {
        return getSide(0);
    }

    /**
     * Gets the furigana of the phrase.
     * @return The furigana.
     */
    public String getFurigana() {
        return getSide(1);
    }

    /**
     * Gets the definition of the phrase.
     * @return The defintion.
     */
    public String getDefinition() {
        return getSide(2);
    }
}
