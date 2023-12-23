package com.potrt.flashcards.japanese.string.verb;

import java.util.HashMap;
import java.util.Map;

/**
 * An {@link JapaneseVerbEnding} represents a dictionary verb's ending.
 */
public enum JapaneseVerbEnding {
    U("う"),
    TSU("つ"),
    RU("る"),
    BU("ぶ"),
    MU("む"),
    NU("ぬ"),
    KU("く"),
    GU("ぐ"),
    SU("す");

    // Links each enumerable value with its furigana.
    private final String furigana;
    private JapaneseVerbEnding(String furigana) {
        this.furigana = furigana;
    }

    /**
     * Gets the furigana of the ending.
     * @return The furigana.
     */
    public String getFurigana() {
        return furigana;
    }

    // Prepares a map that contains all convertions from strings to endings. 
    private static final Map<String, JapaneseVerbEnding> stringToEndings;
    static {
        stringToEndings = new HashMap<>();
        for (JapaneseVerbEnding ending : JapaneseVerbEnding.values()) {
            stringToEndings.put(ending.getFurigana(), ending);
        }
    }

    /**
     * Generates a {@link JapaneseVerbEnding} from a {@link String}.
     * @param ending The ending as a {@link String}.
     * @return The ending as an {@link JapaneseVerbEnding}.
     */
    public static JapaneseVerbEnding from(String ending) throws IllegalArgumentException {
        JapaneseVerbEnding out = stringToEndings.get(ending);
        if (out == null) { throw new IllegalArgumentException("The text '" + ending + "' is not a valid verb ending."); } 
        return out;
    }

    @Override
    public String toString() {
        return furigana;
    }
}
