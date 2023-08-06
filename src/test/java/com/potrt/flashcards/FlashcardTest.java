package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FlashcardTest {
    
    @Test
    public void twoSidedFlashcardTest() {
        Flashcard flashcard = new Flashcard("A", "B");
        assertThat(flashcard.getSide(0)).contains("A");
        assertThat(flashcard.getSide(1)).contains("B");
        assertThat(flashcard.getSize()).isEqualTo(2);
    }

    @Test
    public void threeSidedFlashcardTest() {
        Flashcard flashcard = new Flashcard("A", "B", "C");
        assertThat(flashcard.getSide(0)).contains("A");
        assertThat(flashcard.getSide(1)).contains("B");
        assertThat(flashcard.getSide(2)).contains("C");
        assertThat(flashcard.getSize()).isEqualTo(3);
    }

    @Test
    public void emptyFlashcardTest() {
        Flashcard flashcard = new Flashcard(2);
        flashcard.setSide(0, "A");
        flashcard.setSide(1, "B");
        assertThat(flashcard.getSide(0)).contains("A");
        assertThat(flashcard.getSide(1)).contains("B");
        assertThat(flashcard.getSize()).isEqualTo(2);
    }
}
