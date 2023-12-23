package com.potrt.flashcards.japanese.string.verb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseKanji;
import com.potrt.flashcards.japanese.JapaneseWord;
import com.potrt.flashcards.japanese.JapaneseWordTestImplementation;
import com.potrt.flashcards.japanese.string.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseVerbTest implements TestingConstants {
    private JapaneseVerbBuilder builder;

    @Before
    public void setup() {
        
        builder = new JapaneseVerbBuilder();
    }

    

    /**
     * Creates a verb without explicitly specifying an ending.
     */
    @Test
    public void implicitEndingTest() {
        JapaneseKanji kanji = new JapaneseKanji(playKanji, playMeaning);
        builder.add(kanji.withReading(godanVerbToPlayFuriganaBase));
        builder.add(godanVerbToPlayEnding);
        JapaneseVerb verb = builder.getJapaneseVerb(godanVerbToPlayDefinition, JapaneseVerbType.GODAN);
        assertThat(verb.getKanjiBase()).isEqualTo(godanVerbToPlayKanjiBase);
        assertThat(verb.getFuriganaBase()).isEqualTo(godanVerbToPlayFuriganaBase);
        assertThat(verb.getEnding()).hasToString(godanVerbToPlayEnding);

        assertThat(verb.getKanji()).isEqualTo(godanVerbToPlayKanjiBase + godanVerbToPlayEnding);
        assertThat(verb.getFurigana()).isEqualTo(godanVerbToPlayFuriganaBase + godanVerbToPlayEnding);
        assertThat(verb.getDefinition()).isEqualTo(verb.getDefinition());
    }

    /**
     * Test conjugating a godan verb.
     */
    @Test
    public void conjugateGodanTest() {
        JapaneseKanji kanji = new JapaneseKanji(drinkKanji, drinkMeaning);
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseVerb verb = builder.getJapaneseVerb(godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
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
        JapaneseKanji kanji = new JapaneseKanji(seeKanji, seeMeaning);
        builder.add(kanji.withReading(ichidanVerbToSeeFuriganaBase));
        builder.add(ichidanDictionaryEnding);
        JapaneseVerb verb = builder.getJapaneseVerb(ichidanVerbToSeeDefinition, JapaneseVerbType.ICHIDAN);
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
        builder.add(irregularVerbToDoKana);
        JapaneseVerb verb = builder.getJapaneseVerb(irregularVerbToDoDefinition, JapaneseVerbType.IRREGULAR);
        JapaneseWord.Representation conjugatedVerb = verb.conjugate(new JapaneseVerbForm(false, true, presentIdicative));

        assertThat(conjugatedVerb.getKanji()).isEqualTo(irregularVerbToDoPolite);
        assertThat(conjugatedVerb.getFurigana()).isEqualTo(irregularVerbToDoPolite);
        assertThat(conjugatedVerb.getDefinition()).isEqualTo(irregularVerbToDoDefinition + " (" + definitionPresentIndicativePolite + ")");
    }

    /**
     * Check that hash equality works.
     */
    @Test
    public void equalityTest() {
        JapaneseKanji kanji = new JapaneseKanji(drinkKanji, drinkMeaning);
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseVerb verb = builder.getJapaneseVerb(godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
        JapaneseWord word = new JapaneseWordTestImplementation(godanVerbToDrinkKanjiBase + godanVerbToDrinkEnding, 
                                                               godanVerbToDrinkFuriganaBase + godanVerbToDrinkEnding, 
                                                               godanVerbToDrinkDefinition);
        assertThat(verb).hasSameHashCodeAs(word).isEqualTo(word);
        assertThat(word).hasSameHashCodeAs(verb).isEqualTo(verb);
    }

    /**
     * Check that hash inequality works.
     */
    @Test
    public void inequalityTest() {
        JapaneseKanji kanji = new JapaneseKanji(drinkKanji, drinkMeaning);
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseVerb verb = builder.getJapaneseVerb(godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
        JapaneseWord word = new JapaneseWordTestImplementation(godanVerbToPlayKanjiBase + godanVerbToPlayEnding, 
                                             godanVerbToPlayKanjiBase + godanVerbToPlayEnding, 
                                             godanVerbToPlayDefinition);
        assertThat(verb).doesNotHaveSameHashCodeAs(word).isNotEqualTo(word);
        assertThat(word).doesNotHaveSameHashCodeAs(verb).isNotEqualTo(verb);
    }
}
