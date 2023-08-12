package com.potrt.flashcards.japanese.verb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseWord;

public class JapaneseWordTest implements TestingConstants {
    
    /**
     * Checks that a simple word works.
     */
    @Test
    public void basicWordTest() {
        JapaneseWord word = new JapaneseWord(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(word.getKanji()).isEqualTo(wordIKanji);
        assertThat(word.getFurigana()).isEqualTo(wordIFurigana);
        assertThat(word.getDefinition()).isEqualTo(wordIDefinition);
    }
}
