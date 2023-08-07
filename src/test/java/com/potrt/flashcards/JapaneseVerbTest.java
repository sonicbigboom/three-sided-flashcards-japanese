package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.JapaneseVerb.JapaneseVerbType;

public class JapaneseVerbTest implements TestingConstants {

    /**
     * Creates a basic verb.
     */
    @Test
    public void basicVerbTest() {
        
        JapaneseVerb verb = new JapaneseVerb(verbToDrinkKanjiBase, verbToDrinkFuriganaBase, verbToDrinkEnding, JapaneseVerbType.U, verbToDrinkDefinition);
        assertThat(verb.getKanjiBase()).contains(verbToDrinkKanjiBase);
        assertThat(verb.getFuriganaBase()).contains(verbToDrinkFuriganaBase);
        assertThat(verb.getEnding()).contains(verbToDrinkEnding);

        assertThat(verb.getKanji()).contains(verbToDrinkKanjiBase + verbToDrinkEnding);
        assertThat(verb.getFurigana()).contains(verbToDrinkFuriganaBase + verbToDrinkEnding);
        assertThat(verb.getDefinition()).contains(verb.getDefinition());
    }
}
