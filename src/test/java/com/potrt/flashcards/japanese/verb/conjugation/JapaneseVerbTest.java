package com.potrt.flashcards.japanese.verb.conjugation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapanesePhrase;
import com.potrt.flashcards.japanese.verb.JapaneseVerb;
import com.potrt.flashcards.japanese.verb.JapaneseVerb.JapaneseVerbType;
import com.potrt.flashcards.japanese.verb.JapaneseVerbEnding;
import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;

public class JapaneseVerbTest implements TestingConstants {

    /**
     * Creates a basic verb.
     */
    @Test
    public void basicVerbTest() {
        
        JapaneseVerb verb = new JapaneseVerb(godanVerbToDrinkKanjiBase, godanVerbToDrinkFuriganaBase, JapaneseVerbEnding.from(godanVerbToDrinkEnding), godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
        assertThat(verb.getKanjiBase()).contains(godanVerbToDrinkKanjiBase);
        assertThat(verb.getFuriganaBase()).contains(godanVerbToDrinkFuriganaBase);
        assertThat(verb.getEnding().toString()).contains(godanVerbToDrinkEnding);

        assertThat(verb.getKanji()).contains(godanVerbToDrinkKanjiBase + godanVerbToDrinkEnding);
        assertThat(verb.getFurigana()).contains(godanVerbToDrinkFuriganaBase + godanVerbToDrinkEnding);
        assertThat(verb.getDefinition()).contains(verb.getDefinition());
    }

    /**
     * Creates a verb without explicitly specifying an ending.
     */
    @Test
    public void implicitEndingTest() {
        JapaneseVerb verb = new JapaneseVerb(godanVerbToPlayKanjiBase + godanVerbToPlayEnding, godanVerbToPlayFuriganaBase + godanVerbToPlayEnding, godanVerbToPlayDefinition, JapaneseVerbType.GODAN);
        assertThat(verb.getKanjiBase()).contains(godanVerbToPlayKanjiBase);
        assertThat(verb.getFuriganaBase()).contains(godanVerbToPlayFuriganaBase);
        assertThat(verb.getEnding().toString()).contains(godanVerbToPlayEnding);

        assertThat(verb.getKanji()).contains(godanVerbToPlayKanjiBase + godanVerbToPlayEnding);
        assertThat(verb.getFurigana()).contains(godanVerbToPlayFuriganaBase + godanVerbToPlayEnding);
        assertThat(verb.getDefinition()).contains(verb.getDefinition());
    }
    
    /**
     * Test a verb throws an error with mismatched endings.
     */
    @Test
    public void mismatchedEndingTest() {
        assertThatThrownBy(() -> 
            new JapaneseVerb(
                godanVerbToPlayKanjiBase + godanVerbToPlayEnding, 
                godanVerbToPlayFuriganaBase + godanVerbToDrinkEnding, 
                godanVerbToPlayDefinition, 
                JapaneseVerbType.GODAN
            )).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Creates a verb from a {@link JapanesePhrase}.
     */
    @Test
    public void fromWordTest() {
        JapanesePhrase word = new JapanesePhrase(ichidanVerbToSeeKanjiBase + ichidanDictionaryEnding, ichidanVerbToSeeFuriganaBase + ichidanDictionaryEnding, ichidanVerbToSeeDefinition);
        JapaneseVerb verb = new JapaneseVerb(word, JapaneseVerbType.ICHIDAN);
        assertThat(verb.getKanjiBase()).contains(ichidanVerbToSeeKanjiBase);
        assertThat(verb.getFuriganaBase()).contains(ichidanVerbToSeeFuriganaBase);
        assertThat(verb.getEnding().toString()).contains(ichidanDictionaryEnding);

        assertThat(verb.getKanji()).contains(ichidanVerbToSeeKanjiBase + ichidanDictionaryEnding);
        assertThat(verb.getFurigana()).contains(ichidanVerbToSeeFuriganaBase + ichidanDictionaryEnding);
        assertThat(verb.getDefinition()).contains(ichidanVerbToSeeDefinition);
    }

    /**
     * Test conjugating a godan verb.
     */
    @Test
    public void conjugateGodanTest() {
        JapaneseVerb verb = new JapaneseVerb(godanVerbToDrinkKanjiBase, godanVerbToDrinkFuriganaBase, JapaneseVerbEnding.from(godanVerbToDrinkEnding), godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
        JapanesePhrase conjugatedVerb = verb.conjugate(new JapaneseVerbForm(false, false, presentIdicative));

        assertThat(conjugatedVerb.getKanji()).contains(godanVerbToDrinkKanjiBase + godanVerbToDrinkPoliteNegativeEnding);
        assertThat(conjugatedVerb.getFurigana()).contains(godanVerbToDrinkFuriganaBase + godanVerbToDrinkPoliteNegativeEnding);
        assertThat(conjugatedVerb.getDefinition()).contains(godanVerbToDrinkDefinition);
    }

    /**
     * Test conjugating a ichidan verb.
     */
    @Test
    public void conjugateIchidanTest() {
        JapaneseVerb verb = new JapaneseVerb(ichidanVerbToSeeKanjiBase, ichidanVerbToSeeFuriganaBase, JapaneseVerbEnding.RU, ichidanVerbToSeeDefinition, JapaneseVerbType.ICHIDAN);
        JapanesePhrase conjugatedVerb = verb.conjugate(new JapaneseVerbForm(true, true, pastIdicative));

        assertThat(conjugatedVerb.getKanji()).contains(ichidanVerbToSeeKanjiBase + ichidanVerbToSeePastEnding);
        assertThat(conjugatedVerb.getFurigana()).contains(ichidanVerbToSeeFuriganaBase + ichidanVerbToSeePastEnding);
        assertThat(conjugatedVerb.getDefinition()).contains(ichidanVerbToSeeDefinition);
    }

    /**
     * Test conjugating a irregular verb.
     */
    @Test
    public void conjugateIrregularTest() {
        JapaneseVerb verb = new JapaneseVerb(irregularVerbToDoKana, irregularVerbToDoKana, irregularVerbToDoDefinition, JapaneseVerbType.IRREGULAR);
        JapanesePhrase conjugatedVerb = verb.conjugate(new JapaneseVerbForm(false, true, presentIdicative));

        assertThat(conjugatedVerb.getKanji()).contains(irregularVerbToDoPolite);
        assertThat(conjugatedVerb.getFurigana()).contains(irregularVerbToDoPolite);
        assertThat(conjugatedVerb.getDefinition()).contains(irregularVerbToDoDefinition);
    }
}
