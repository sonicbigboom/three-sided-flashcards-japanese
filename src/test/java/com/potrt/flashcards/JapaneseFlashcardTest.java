package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JapaneseFlashcardTest implements TestingConstants {
    
    /**
     * Creates a simple flashcard.
     */
    @Test
    public void basicTest() {
        JapaneseFlashcard flashcard = new JapaneseFlashcard(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(flashcard.getKanji()).contains(wordIKanji);
        assertThat(flashcard.getFurigana()).contains(wordIFurigana);
        assertThat(flashcard.getDefinition()).contains(wordIDefinition);
    }

    /**
     * Creates a flashcard from a {@link JapanesePhrase}.
     */
    @Test
    public void flashcardFromPhraseTest() {
        JapanesePhrase phrase = new JapanesePhrase(phraseOnePersonKanji, phraseOnePersonFurigana, phraseOnePersonDefinition);
        JapaneseFlashcard flashcard = new JapaneseFlashcard(phrase);
        assertThat(flashcard.getKanji()).contains(phraseOnePersonKanji);
        assertThat(flashcard.getFurigana()).contains(phraseOnePersonFurigana);
        assertThat(flashcard.getDefinition()).contains(phraseOnePersonDefinition);
    }
}
