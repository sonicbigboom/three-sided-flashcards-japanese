package com.potrt.flashcards.japanese.verb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseWord;
import com.potrt.flashcards.japanese.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseVerbTest implements TestingConstants {

    /**
     * Creates a basic verb.
     */
    @Test
    public void basicVerbTest() {
        
        JapaneseVerb verb = new JapaneseVerb(godanVerbToDrinkKanjiBase, godanVerbToDrinkFuriganaBase, JapaneseVerbEnding.from(godanVerbToDrinkEnding), godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
        assertThat(verb.getKanjiBase()).isEqualTo(godanVerbToDrinkKanjiBase);
        assertThat(verb.getFuriganaBase()).isEqualTo(godanVerbToDrinkFuriganaBase);
        assertThat(verb.getEnding()).hasToString(godanVerbToDrinkEnding);

        assertThat(verb.getKanji()).isEqualTo(godanVerbToDrinkKanjiBase + godanVerbToDrinkEnding);
        assertThat(verb.getFurigana()).isEqualTo(godanVerbToDrinkFuriganaBase + godanVerbToDrinkEnding);
        assertThat(verb.getDefinition()).isEqualTo(verb.getDefinition());
    }

    /**
     * Creates a verb without explicitly specifying an ending.
     */
    @Test
    public void implicitEndingTest() {
        JapaneseVerb verb = new JapaneseVerb(godanVerbToPlayKanjiBase + godanVerbToPlayEnding, godanVerbToPlayFuriganaBase + godanVerbToPlayEnding, godanVerbToPlayDefinition, JapaneseVerbType.GODAN);
        assertThat(verb.getKanjiBase()).isEqualTo(godanVerbToPlayKanjiBase);
        assertThat(verb.getFuriganaBase()).isEqualTo(godanVerbToPlayFuriganaBase);
        assertThat(verb.getEnding()).hasToString(godanVerbToPlayEnding);

        assertThat(verb.getKanji()).isEqualTo(godanVerbToPlayKanjiBase + godanVerbToPlayEnding);
        assertThat(verb.getFurigana()).isEqualTo(godanVerbToPlayFuriganaBase + godanVerbToPlayEnding);
        assertThat(verb.getDefinition()).isEqualTo(verb.getDefinition());
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
     * Creates a verb from a {@link JapaneseWord}.
     */
    @Test
    public void fromWordTest() {
        JapaneseWord word = new JapaneseWord(ichidanVerbToSeeKanjiBase + ichidanDictionaryEnding, ichidanVerbToSeeFuriganaBase + ichidanDictionaryEnding, ichidanVerbToSeeDefinition);
        JapaneseVerb verb = new JapaneseVerb(word, JapaneseVerbType.ICHIDAN);
        assertThat(verb.getKanjiBase()).isEqualTo(ichidanVerbToSeeKanjiBase);
        assertThat(verb.getFuriganaBase()).isEqualTo(ichidanVerbToSeeFuriganaBase);
        assertThat(verb.getEnding()).hasToString(ichidanDictionaryEnding);

        assertThat(verb.getKanji()).isEqualTo(ichidanVerbToSeeKanjiBase + ichidanDictionaryEnding);
        assertThat(verb.getFurigana()).isEqualTo(ichidanVerbToSeeFuriganaBase + ichidanDictionaryEnding);
        assertThat(verb.getDefinition()).isEqualTo(ichidanVerbToSeeDefinition);
    }

    /**
     * Test conjugating a godan verb.
     */
    @Test
    public void conjugateGodanTest() {
        JapaneseVerb verb = new JapaneseVerb(godanVerbToDrinkKanjiBase, godanVerbToDrinkFuriganaBase, JapaneseVerbEnding.from(godanVerbToDrinkEnding), godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
        JapaneseWord.Representation conjugatedVerb = verb.conjugate(new JapaneseVerbForm(false, false, presentIdicative));

        assertThat(conjugatedVerb.getKanji()).isEqualTo(godanVerbToDrinkKanjiBase + godanVerbToDrinkPoliteNegativeEnding);
        assertThat(conjugatedVerb.getFurigana()).isEqualTo(godanVerbToDrinkFuriganaBase + godanVerbToDrinkPoliteNegativeEnding);
        assertThat(conjugatedVerb.getDefinition()).isEqualTo(godanVerbToDrinkDefinition + " (" + definitionPresentIndicativePoliteNegative + ")");
    }

    /**
     * Test conjugating a ichidan verb.
     */
    @Test
    public void conjugateIchidanTest() {
        JapaneseVerb verb = new JapaneseVerb(ichidanVerbToSeeKanjiBase, ichidanVerbToSeeFuriganaBase, JapaneseVerbEnding.RU, ichidanVerbToSeeDefinition, JapaneseVerbType.ICHIDAN);
        JapaneseWord.Representation conjugatedVerb = verb.conjugate(new JapaneseVerbForm(true, true, pastIdicative));

        assertThat(conjugatedVerb.getKanji()).isEqualTo(ichidanVerbToSeeKanjiBase + ichidanVerbToSeePastEnding);
        assertThat(conjugatedVerb.getFurigana()).isEqualTo(ichidanVerbToSeeFuriganaBase + ichidanVerbToSeePastEnding);
        assertThat(conjugatedVerb.getDefinition()).isEqualTo(ichidanVerbToSeeDefinition + " (" + definitionPastIndicative + ")");
    }

    /**
     * Test conjugating a irregular verb.
     */
    @Test
    public void conjugateIrregularTest() {
        JapaneseVerb verb = new JapaneseVerb(irregularVerbToDoKana, irregularVerbToDoKana, irregularVerbToDoDefinition, JapaneseVerbType.IRREGULAR);
        JapaneseWord.Representation conjugatedVerb = verb.conjugate(new JapaneseVerbForm(false, true, presentIdicative));

        assertThat(conjugatedVerb.getKanji()).isEqualTo(irregularVerbToDoPolite);
        assertThat(conjugatedVerb.getFurigana()).isEqualTo(irregularVerbToDoPolite);
        assertThat(conjugatedVerb.getDefinition()).isEqualTo(irregularVerbToDoDefinition + " (" + definitionPresentIndicativePolite + ")");
    }
}
