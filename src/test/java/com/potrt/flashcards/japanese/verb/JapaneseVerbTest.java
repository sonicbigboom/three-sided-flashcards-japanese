package com.potrt.flashcards.japanese.verb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseKanji;
import com.potrt.flashcards.japanese.JapaneseWord;
import com.potrt.flashcards.japanese.JapaneseWordBuilder;
import com.potrt.flashcards.japanese.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseVerbTest implements TestingConstants {
    private JapaneseWordBuilder builder;

    @Before
    public void setup() {
        
        builder = new JapaneseWordBuilder();
    }

    

    /**
     * Creates a verb without explicitly specifying an ending.
     */
    @Test
    public void implicitEndingTest() {
        JapaneseKanji kanji = new JapaneseKanji(playKanji, playMeaning);
        builder.add(kanji.withReading(godanVerbToPlayFuriganaBase));
        builder.add(godanVerbToPlayEnding);
        JapaneseVerb verb = new JapaneseVerb(builder, godanVerbToPlayDefinition, JapaneseVerbType.GODAN);
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
        JapaneseVerb verb = new JapaneseVerb(builder, godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);
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
        JapaneseVerb verb = new JapaneseVerb(builder, ichidanVerbToSeeDefinition, JapaneseVerbType.ICHIDAN);
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
        JapaneseVerb verb = new JapaneseVerb(builder, irregularVerbToDoDefinition, JapaneseVerbType.IRREGULAR);
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
        JapaneseVerb verb = new JapaneseVerb(builder, godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);

        builder = new JapaneseWordBuilder();
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseWord duplicate = new JapaneseVerb(builder, godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);

        assertThat(verb).hasSameHashCodeAs(duplicate).isEqualTo(duplicate);
        assertThat(duplicate).hasSameHashCodeAs(verb).isEqualTo(verb);
    }

    /**
     * Check that hash inequality works.
     */
    @Test
    public void inequalityTest() {
        JapaneseKanji kanji = new JapaneseKanji(drinkKanji, drinkMeaning);
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseVerb verb = new JapaneseVerb(builder, godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);

        builder = new JapaneseWordBuilder();
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseWord invalidDuplicate = new JapaneseVerb(builder, godanVerbToDrinkDefinition, JapaneseVerbType.IRREGULAR);

        assertThat(verb).doesNotHaveSameHashCodeAs(invalidDuplicate).isNotEqualTo(invalidDuplicate);
        assertThat(invalidDuplicate).doesNotHaveSameHashCodeAs(verb).isNotEqualTo(verb);
    }

    /**
     * Check that hash inequality works between verb and word.
     */
    @Test
    public void wordInequalityTest() {
        JapaneseKanji kanji = new JapaneseKanji(drinkKanji, drinkMeaning);
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseVerb verb = new JapaneseVerb(builder, godanVerbToDrinkDefinition, JapaneseVerbType.GODAN);

        builder = new JapaneseWordBuilder();
        builder.add(kanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseWord duplicate = new JapaneseWord(builder, godanVerbToDrinkDefinition);

        assertThat(verb).doesNotHaveSameHashCodeAs(duplicate).isNotEqualTo(duplicate);
        // assertThat(duplicate).doesNotHaveSameHashCodeAs(verb).isNotEqualTo(verb);  // TODO: Is this actually expected behavior?
    }
}
