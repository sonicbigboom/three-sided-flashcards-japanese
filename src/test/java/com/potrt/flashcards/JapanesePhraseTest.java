package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JapanesePhraseTest implements TestingConstants {
    
    /**
     * Checks that a simple word works.
     */
    @Test
    public void basicWordTest() {
        JapanesePhrase word = new JapanesePhrase(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(word.getKanji()).contains(wordIKanji);
        assertThat(word.getFurigana()).contains(wordIFurigana);
        assertThat(word.getDefinition()).contains(wordIDefinition);
    }
}
