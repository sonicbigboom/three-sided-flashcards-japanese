package com.potrt.flashcards;

/**
 * A {@link Flashcard} repesents a flashcard that could have any number of sides.
 */
public class Flashcard {
    protected int size;
    protected String[] sides;

    /**
     * Creates a {@link Flashcard} with defined sides.
     * @param sides An array of {@link String}s that each represent a side's text of the {@link Flashcard}.
     */
    public Flashcard(String... sides) {
        size = sides.length;
        this.sides = sides;
    }

    /**
     * Get a side's text.
     * @param i The index of the side.
     * @return The side's text.
     */
    public String getSide(int i) {
        return sides[i];
    }

    /**
     * Get the number of sides.
     * @return The number of sides.
     */
    public int getSize() {
        return size;
    }
}
