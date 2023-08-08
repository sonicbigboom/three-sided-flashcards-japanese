package com.potrt.flashcards.japanese.verb.conjugation;

import static org.assertj.core.api.Assertions.assertThat;

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
        
        JapaneseVerb verb = new JapaneseVerb(godanVerbToDrinkKanjiBase, godanVerbToDrinkFuriganaBase, JapaneseVerbEnding.from(godanVerbToDrinkEnding), JapaneseVerbType.GODAN, godanVerbToDrinkDefinition);
        assertThat(verb.getKanjiBase()).contains(godanVerbToDrinkKanjiBase);
        assertThat(verb.getFuriganaBase()).contains(godanVerbToDrinkFuriganaBase);
        assertThat(verb.getEnding().toString()).contains(godanVerbToDrinkEnding);

        assertThat(verb.getKanji()).contains(godanVerbToDrinkKanjiBase + godanVerbToDrinkEnding);
        assertThat(verb.getFurigana()).contains(godanVerbToDrinkFuriganaBase + godanVerbToDrinkEnding);
        assertThat(verb.getDefinition()).contains(verb.getDefinition());
    }

    /**
     * Test conjugating a godan verb.
     */
    @Test
    public void conjugateGodanTest() {
        JapaneseVerb verb = new JapaneseVerb(godanVerbToDrinkKanjiBase, godanVerbToDrinkFuriganaBase, JapaneseVerbEnding.from(godanVerbToDrinkEnding), JapaneseVerbType.GODAN, godanVerbToDrinkDefinition);
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
        JapaneseVerb verb = new JapaneseVerb(ichidanVerbToSeeKanjiBase, ichidanVerbToSeeFuriganaBase, JapaneseVerbEnding.RU, JapaneseVerbType.ICHIDAN, ichidanVerbToSeeDefinition);
        JapanesePhrase conjugatedVerb = verb.conjugate(new JapaneseVerbForm(true, true, pastIdicative));

        assertThat(conjugatedVerb.getKanji()).contains(ichidanVerbToSeeKanjiBase + ichidanVerbToSeePastEnding);
        assertThat(conjugatedVerb.getFurigana()).contains(ichidanVerbToSeeFuriganaBase + ichidanVerbToSeePastEnding);
        assertThat(conjugatedVerb.getDefinition()).contains(ichidanVerbToSeeDefinition);
    }
}
