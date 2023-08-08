package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.japanese.verb.JapaneseVerbEnding;
import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;
import com.potrt.flashcards.japanese.verb.conjugation.JapaneseVerbConjugator;

public class AppTest implements TestingConstants {

    /**
     * Test for completeness.  Initializes main app class.
     */
    @Test
    public void initializeAppTest() {
        App app = new App();
        assertThat(app).isNotNull();
    }
    
    /**
     * Test that the verb conjugator has loaded.
     */
    @Test
    public void verbConjugatorLoadedTest() {
        App.main(null);
        assertThat(JapaneseVerbConjugator.conjugateGodanVerb(new JapaneseVerbForm(true, true, presentIdicative), JapaneseVerbEnding.SU)).isEqualTo(suDictionaryEnding);
    }
}
