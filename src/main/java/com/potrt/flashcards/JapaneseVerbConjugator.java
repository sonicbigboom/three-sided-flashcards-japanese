package com.potrt.flashcards;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * A {@link JapaneseVerbConjugator} provides methods to conjugate Japanese verbs.
 */
public class JapaneseVerbConjugator {

    private static Map<JapaneseVerbForm, TranslationTable> translationTables = new HashMap<>();

    public static void getTranslationTables(String jsonPath) {
        throw new UnsupportedOperationException();
    }

    /**
     * Conjugates a godan verb based on the given form and ending. 
     * @param form The verb form.
     * @param ending The ending.
     * @return The conjugated ending.
     */
    public static String conjugateGodanVerb(JapaneseVerbForm form, JapaneseVerbEnding ending) {
        return translationTables.get(form).conjugateGodanVerb(ending);
    }

    /**
     * Conjugates a ichidan verb based on the given form. 
     * @param form The verb form.
     * @return The conjugated ending.
     */
    public static String conjugateIchidanVerb(JapaneseVerbForm form) {
        return translationTables.get(form).conjugateIchidanVerb();
    }

    /**
     * A {@link TranslationTable} represents a translation table that converts a verb's dictionary ending to a verb form's ending. 
     * 
     * @implNote The verb form is not stored in the {@link TranslationTable} as the {@link JapaneseVerbConjugator} will 
     * map the {@link JapaneseVerbForm}s with their respective {@link TranslationTable}.
     */
    private class TranslationTable {
        private EnumMap<JapaneseVerbEnding, String> godanVerbTranslationTable;
        private String ichidanVerbEnding;

        /**
         * Conjugates a godan verb based on the translation table and given ending. 
         * @param ending The ending.
         * @return The conjugated ending.
         */
        public String conjugateGodanVerb(JapaneseVerbEnding ending) {
            return godanVerbTranslationTable.get(ending);
        }

        /**
         * Conjugates a ichidan verb based on the translation table.
         * @return The conjugated ending.
         */
        public String conjugateIchidanVerb() {
            return ichidanVerbEnding;
        }
    }
}
