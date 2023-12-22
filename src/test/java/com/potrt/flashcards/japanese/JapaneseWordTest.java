package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;

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

    /**
     * Checks that a basic representation word works.
     */
    @Test
    public void representationTest() {
        JapaneseWord word = new JapaneseWord(wordIKanji, wordIFurigana, wordIDefinition);
        JapaneseWord.Representation rep = word.getRepresentation();
        assertThat(rep.getKanji()).isEqualTo(wordIKanji);
        assertThat(rep.getFurigana()).isEqualTo(wordIFurigana);
        assertThat(rep.getDefinition()).isEqualTo(wordIDefinition);
        assertThat(rep.getWord()).isEqualTo(word);
    }

    /**
     * Check that hash equality works.
     */
    @Test
    public void equalityTest() {
        JapaneseWord word = new JapaneseWord(wordIKanji, wordIFurigana, wordIDefinition);
        JapaneseWord word2 = new JapaneseWord(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(word).hasSameHashCodeAs(word2).isEqualTo(word2);
    }

    /**
     * Check that hash inequality works.
     */
    @Test
    public void inequalityTest() {
        JapaneseWord word = new JapaneseWord(wordIKanji, wordIFurigana, wordIDefinition);
        JapaneseWord word2 = new JapaneseWord(wordOneKanji, wordOneFurigana, wordOneDefinition);
        assertThat(word).doesNotHaveSameHashCodeAs(word2).isNotEqualTo(word2);
    }
}
