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
        Flashcard flashcard = new Flashcard(wordOnePersonKanji, wordOnePersonFurigana, wordOnePersonDefinition);
        assertThat(flashcard.getSide(0)).isEqualTo(wordOnePersonKanji);
        assertThat(flashcard.getSide(1)).isEqualTo(wordOnePersonFurigana);
        assertThat(flashcard.getSide(2)).isEqualTo(wordOnePersonDefinition);
        assertThat(flashcard.getSize()).isEqualTo(3);
    }
}
