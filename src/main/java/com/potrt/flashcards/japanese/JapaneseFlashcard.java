package com.potrt.flashcards.japanese;

import com.potrt.flashcards.Flashcard;
import com.potrt.flashcards.japanese.verb.JapaneseVerb;
import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;

/**
 * A {@link JapaneseFlashcard} represents a Japanese word with three descriptions: the kanji, the furigana, and the definition.
 */
public class JapaneseFlashcard extends Flashcard {
    /**
     * Creates a new {@link JapaneseFlashcard} with the kanji, the furigana, and the definition.
     * @param kanji The kanji for the word.
     * @param furigana The furigana for the word.
     * @param definition The definition for the word.
     */
    public JapaneseFlashcard(String kanji, String furigana, String definition) {
        super(kanji, furigana, definition);
    }

    /**
     * Creates a new {@link JapaneseFlashcard} with a {@link JapaneseWord}.
     * @param word The word.
     */
    public JapaneseFlashcard(JapaneseWord word) {
        this(word.getKanji(), word.getFurigana(), word.getDefinition());
    }

    /**
     * Creates a new {@link JapaneseFlashcard} with a {@link JapaneseVerb} conjugated to the given form.
     * @param verb The verb.
     * @param form The form that the verb will be conjugated to.
     */
    public JapaneseFlashcard(JapaneseVerb verb, JapaneseVerbForm form) {
        this(verb.conjugate(form));
    }     

    /**
     * Gets the kanji of the word.
     * @return The kanji.
     */
    public String getKanji() {
        return getSide(0);
    }

    /**
     * Gets the furigana of the word.
     * @return The furigana.
     */
    public String getFurigana() {
        return getSide(1);
    }

    /**
     * Gets the definition of the word.
     * @return The defintion.
     */
    public String getDefinition() {
        return getSide(2);
    }
}
