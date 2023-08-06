package com.potrt.flashcards;

/**
 * A {@link Flashcard} repesents a flashcard that could have any number of sides.
 */
public class Flashcard {
    private int size;
    private String[] sides;

    /**
     * Creates an empty {@link Flashcard} with a given number of sides.
     * @param size The number of sides.
     */
    public Flashcard(int size) {
        this.size = size;
        sides = new String[size];
    }

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
     * Set a side's text.
     * @param i The index of the side.
     * @param side The side's new text.
     */
    public void setSide(int i, String side) {
        sides[i] = side;
    }

    /**
     * Get the number of sides.
     * @return The number of sides.
     */
    public int getSize() {
        return size;
    }
}
