package com.potrt.flashcards.japanese;

import com.potrt.flashcards.Flashcard;
import com.potrt.flashcards.japanese.word.JapaneseWord;

/**
 * A {@link JapaneseFlashcard} represents a Japanese word with three descriptions: the kanji, the furigana, and the definition.
 */
public class JapaneseFlashcard extends Flashcard {
    protected JapaneseWord word;

    /**
     * Creates a new {@link JapaneseFlashcard} with a {@link JapaneseWord} and a {@link JapaneseWord.Representation} of that word.
     * @param word The word.
     */
    public JapaneseFlashcard(JapaneseWord.Representation representation) {
        super(representation.getKanji(), representation.getFurigana(), representation.getDefinition());
        this.word = representation.getWord();
    }

    /**
     * Creates a new {@link JapaneseFlashcard} with a {@link JapaneseWord}.
     * @param word The word.
     */
    public JapaneseFlashcard(JapaneseWord word) {
        this(word.getRepresentation());
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

    /**
     * Gets the associated {@link JapaneseWord}.
     * @return The associated {@link JapaneseWord}.
     */
    public JapaneseWord getJapaneseWord() {
        return word;
    }
}
