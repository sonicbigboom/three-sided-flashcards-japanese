package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FlashcardTest implements TestingConstants {
    
    /**
     * Checks that a basic two sided flashcard works.
     */
    @Test
    public void twoSidedFlashcardTest() {
        Flashcard flashcard = new Flashcard(wordIFurigana, wordIDefinition);
        assertThat(flashcard.getSide(0)).isEqualTo(wordIFurigana);
        assertThat(flashcard.getSide(1)).isEqualTo(wordIDefinition);
        assertThat(flashcard.getSize()).isEqualTo(2);
    }

    /**
     * Checks that a three sided flashcard works.
     */
    @Test
    public void threeSidedFlashcardTest() {
        Flashcard flashcard = new Flashcard(phraseOnePersonKanji, phraseOnePersonFurigana, phraseOnePersonDefinition);
        assertThat(flashcard.getSide(0)).isEqualTo(phraseOnePersonKanji);
        assertThat(flashcard.getSide(1)).isEqualTo(phraseOnePersonFurigana);
        assertThat(flashcard.getSide(2)).isEqualTo(phraseOnePersonDefinition);
        assertThat(flashcard.getSize()).isEqualTo(3);
    }
}
