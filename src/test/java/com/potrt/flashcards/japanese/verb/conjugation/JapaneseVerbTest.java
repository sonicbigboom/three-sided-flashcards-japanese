package com.potrt.flashcards.japanese.verb.conjugation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.verb.JapaneseVerb;
import com.potrt.flashcards.japanese.verb.JapaneseVerbEnding;
import com.potrt.flashcards.japanese.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseVerbTest implements TestingConstants {

    /**
     * Creates a basic verb.
     */
    @Test
    public void basicVerbTest() {
        
        JapaneseVerb verb = new JapaneseVerb(verbToDrinkKanjiBase, verbToDrinkFuriganaBase, JapaneseVerbEnding.from(verbToDrinkEnding), JapaneseVerbType.GODAN, verbToDrinkDefinition);
        assertThat(verb.getKanjiBase()).contains(verbToDrinkKanjiBase);
        assertThat(verb.getFuriganaBase()).contains(verbToDrinkFuriganaBase);
        assertThat(verb.getEnding().toString()).contains(verbToDrinkEnding);

        assertThat(verb.getKanji()).contains(verbToDrinkKanjiBase + verbToDrinkEnding);
        assertThat(verb.getFurigana()).contains(verbToDrinkFuriganaBase + verbToDrinkEnding);
        assertThat(verb.getDefinition()).contains(verb.getDefinition());
    }
}
