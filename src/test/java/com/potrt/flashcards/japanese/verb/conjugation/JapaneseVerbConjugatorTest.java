package com.potrt.flashcards.japanese.verb.conjugation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.IOException;

import org.junit.Test;

import com.opencsv.exceptions.CsvValidationException;
import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.verb.JapaneseVerbEnding;
import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;


public class JapaneseVerbConjugatorTest implements TestingConstants {

    /**
     * Checks that godan dictionary form converts back to itself.
     */
    @Test
    public void dictionaryFormGodanConjugationTest() {
        assertThat(JapaneseVerbConjugator.conjugateGodanVerb(new JapaneseVerbForm(true, true, presentIdicative), JapaneseVerbEnding.NU)).isEqualTo(nuDictionaryEnding);
        assertThat(JapaneseVerbConjugator.conjugateGodanVerb(new JapaneseVerbForm(true, true, presentIdicative), JapaneseVerbEnding.GU)).isEqualTo(guDictionaryEnding);
    }

    /**
     * Checks that ichidan dictionary form converts back to itself.
     */
    @Test
    public void dictionaryFormIchidanConjugationTest() {
        assertThat(JapaneseVerbConjugator.conjugateIchidanVerb(new JapaneseVerbForm(true, true, presentIdicative))).isEqualTo(ichidanDictionaryEnding);
    }

    /**
     * Checks that godan endings convert to other endings.
     */
    @Test
    public void differentGodanConjugationTest() {
        assertThat(JapaneseVerbConjugator.conjugateGodanVerb(new JapaneseVerbForm(false, true, presentIdicative), JapaneseVerbEnding.SU)).isEqualTo(suPresentIndicativePoliteEnding);
        assertThat(JapaneseVerbConjugator.conjugateGodanVerb(new JapaneseVerbForm(true, false, presentIdicative), JapaneseVerbEnding.TSU)).isEqualTo(tsuPresentIndicativeNegativeEnding);
    }

    /**
     * Checks that ichidan ending converts to other endings.
     */
    @Test
    public void differentIchidanConjugationTest() {
        assertThat(JapaneseVerbConjugator.conjugateIchidanVerb(new JapaneseVerbForm(true, true, pastIdicative))).isEqualTo(ichidanPastIndicative);
    }

    /**
     * Checks that a bad path throws an error.
     */
    @Test
    public void badPath() throws CsvValidationException {
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> JapaneseVerbConjugator.addTranslationTables("not/a/real/path/file.csv"));        
    }
}
