package com.potrt.flashcards.japanese.verb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapanesePhrase;

public class JapanesePhraseTest implements TestingConstants {
    
    /**
     * Checks that a simple word works.
     */
    @Test
    public void basicWordTest() {
        JapanesePhrase word = new JapanesePhrase(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(word.getKanji()).isEqualTo(wordIKanji);
        assertThat(word.getFurigana()).isEqualTo(wordIFurigana);
        assertThat(word.getDefinition()).isEqualTo(wordIDefinition);
    }
}
