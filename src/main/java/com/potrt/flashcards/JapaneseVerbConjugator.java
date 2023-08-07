package com.potrt.flashcards;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class JapaneseVerbConjugator {

    private static Map<JapaneseVerbForm, TranslationTable> translationTables = new HashMap<>();

    public static void getTranslationTables(String jsonPath) {
        throw new UnsupportedOperationException();
    }

    public static String conjugateUVerb(JapaneseVerbForm form, String ending) {
        return translationTables.get(form).conjugateUVerb(ending);
    }

    public static String conjugateRuVerb(JapaneseVerbForm form) {
        return translationTables.get(form).conjugateRuVerb();
    }

    /**
     * A {@link JapaneseVerbConjugator} represents a translation table that converts a verb's dictionary ending to a given verb form's ending. 
     */
    private class TranslationTable {
        private Map<String, String> uVerbTranslationTable;
        private String ruVerbEnding;
        
        public TranslationTable(Map<String, String> uVerbTranslationTable, String ruVerbEnding) {
            this.uVerbTranslationTable = uVerbTranslationTable;
            this.ruVerbEnding = ruVerbEnding;
        }

        public String conjugateUVerb(String ending) {
            return uVerbTranslationTable.get(ending);
        }

        public String conjugateRuVerb() {
            return ruVerbEnding;
        }
    }


}
