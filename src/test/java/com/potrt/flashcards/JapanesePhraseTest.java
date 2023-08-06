package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JapanesePhraseTest implements TestingConstants {
    
    /**
     * Checks that a simple word works.
     */
    @Test
    public void japaneseWordTest() {
        JapanesePhrase word = new JapanesePhrase(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(word.getKanji()).contains(wordIKanji);
        assertThat(word.getFurigana()).contains(wordIFurigana);
        assertThat(word.getDefinition()).contains(wordIDefinition);
    }

    /**
     * Checks that a phrase with no initial values works.
     */
    @Test
    public void changeJapanesePhraseTest() {
        JapanesePhrase phrase = new JapanesePhrase();
        phrase.setKanji(phraseOnePersonKanji);
        phrase.setFurigana(phraseOnePersonFurigana);
        phrase.setDefinition(phraseOnePersonDefinition);
        assertThat(phrase.getKanji()).contains(phraseOnePersonKanji);
        assertThat(phrase.getFurigana()).contains(phraseOnePersonFurigana);
        assertThat(phrase.getDefinition()).contains(phraseOnePersonDefinition);
    }
}
