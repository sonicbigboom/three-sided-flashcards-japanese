package com.potrt.flashcards.japanese.verb.conjugation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;

import com.potrt.flashcards.japanese.verb.JapaneseVerbEnding;

public class JapaneseVerbEndingTest {
    
    /**
     * Checks that furigana can be retrieved from a verb ending.
     */
    @Test
    public void checkFurigana() {
        assertThat(JapaneseVerbEnding.KU.getFurigana()).isEqualTo("く");
        assertThat(JapaneseVerbEnding.SU.getFurigana()).isEqualTo("す");
    }

    /**
     * Checks that the toString gives the furigana as well.
     */
    @Test
    public void checkToString() {
        assertThat(JapaneseVerbEnding.NU).hasToString("ぬ");
        assertThat(JapaneseVerbEnding.BU).hasToString("ぶ");
    }

    /**
     * Checks that the right {@link JapaneseVerbEnding} is generated from a {@link String}.
     */
    @Test
    public void checkFrom() {
        assertThat(JapaneseVerbEnding.from("う")).isEqualTo(JapaneseVerbEnding.U);
        assertThat(JapaneseVerbEnding.from("る")).isEqualTo(JapaneseVerbEnding.RU);
    }

    /**
     * Checks that an invalid ending will return null.
     */
    @Test
    public void checkInvalidFrom() {
        try {
            JapaneseVerbEnding.from("み");
            fail("Didn't throw exception when getting an invalid value.");
        } catch (IllegalArgumentException e) { }
    }
}