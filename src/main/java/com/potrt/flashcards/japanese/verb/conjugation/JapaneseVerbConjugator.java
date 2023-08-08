package com.potrt.flashcards.japanese.verb.conjugation;

import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.potrt.flashcards.japanese.verb.JapaneseVerbEnding;
import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;

/**
 * A {@link JapaneseVerbConjugator} provides methods to conjugate Japanese verbs.
 */
public final class JapaneseVerbConjugator {

    private static Map<JapaneseVerbForm, TranslationTable> translationTables = new HashMap<>();

    /**
     * Adds verb dictionary ending to conjugated ending translation tables from a given csv file.
     * <p>
     * The first 3 values in a row must be its form (e.g. Present Indicative), 
     * whether it is plain (TRUE) or polite (FALSE), 
     * and whether it positive (TRUE) or negative (FALSE).
     * <p>
     * There must be a header row that notes where each ending/irregular verb is denoted with the right furigana, or Ichidan for ichidan verb.
     * @param csvPath The path to the file.
     */
    public static void addTranslationTables(String csvPath) {
        try(CSVReader reader = new CSVReader(new FileReader(csvPath))) {
            String[] header = reader.readNext();
            for (String[] line : reader) {
                boolean plain = line[0].equals("TRUE");
                boolean positive = line[1].equals("TRUE");
                JapaneseVerbForm verbForm = new JapaneseVerbForm(plain, positive, line[2]);

                TranslationTable translationTable = new TranslationTable(header, line);

                translationTables.put(verbForm, translationTable);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
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
    private static class TranslationTable {
        private EnumMap<JapaneseVerbEnding, String> godanTranslationTable;
        private String ichidanTranslation;

        /**
         * Generates a new {@link TranslationTable} from a header and its respective translation values.
         * @param header An array that represent the colunm of each value.  
         *               This includes each dictionary ending (godan and ichidan) and each irregular verb.
         * @param values An array mapped from the header that represents each value.  
         *               Each ending and irregular verb is the after-form-translation version.
         * @apiNote The first 3 values of each array is assumed to be verb form information.
         */
        public TranslationTable(String[] header, String[] values) {
            for (int i = 3; i < header.length; i++) {
                try {
                    JapaneseVerbEnding ending = JapaneseVerbEnding.from(header[i]);
                    godanTranslationTable.put(ending, values[i]);
                } catch (IllegalArgumentException e) { 
                    if (header[i].equals("Ichidan")) {
                        ichidanTranslation = values[i];
                    }
                    // TODO: Add irregular verb handling.
                }
            }
        }

        /**
         * Conjugates a godan verb based on the translation table and given ending. 
         * @param ending The ending.
         * @return The conjugated ending.
         */
        public String conjugateGodanVerb(JapaneseVerbEnding ending) {
            return godanTranslationTable.get(ending);
        }

        /**
         * Conjugates a ichidan verb based on the translation table.
         * @return The conjugated ending.
         */
        public String conjugateIchidanVerb() {
            return ichidanTranslation;
        }
    }

    /**
     * Private constructor to prevent initilaization.
     */
    private JapaneseVerbConjugator () {}
}
