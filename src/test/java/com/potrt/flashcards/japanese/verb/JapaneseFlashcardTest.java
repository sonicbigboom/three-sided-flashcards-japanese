package com.potrt.flashcards.japanese.verb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseFlashcard;
import com.potrt.flashcards.japanese.JapaneseWord;
import com.potrt.flashcards.japanese.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseFlashcardTest implements TestingConstants {
    
    /**
     * Creates a simple flashcard.
     */
    @Test
    public void basicTest() {
        JapaneseFlashcard flashcard = new JapaneseFlashcard(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(flashcard.getKanji()).isEqualTo(wordIKanji);
        assertThat(flashcard.getFurigana()).isEqualTo(wordIFurigana);
        assertThat(flashcard.getDefinition()).isEqualTo(wordIDefinition);
    }

    /**
     * Creates a flashcard from a {@link JapaneseWord}.
     */
    @Test
    public void flashcardFromWordTest() {
        JapaneseWord word = new JapaneseWord(wordOnePersonKanji, wordOnePersonFurigana, wordOnePersonDefinition);
        JapaneseFlashcard flashcard = new JapaneseFlashcard(word);
        assertThat(flashcard.getKanji()).isEqualTo(wordOnePersonKanji);
        assertThat(flashcard.getFurigana()).isEqualTo(wordOnePersonFurigana);
        assertThat(flashcard.getDefinition()).isEqualTo(wordOnePersonDefinition);
    }

    /**
     * Creates a flashcard from a {@link JapaneseVerb} and a {@link JapaneseVerbFrom}.
     */
    @Test
    public void flashcardFromVerbTest() {
        JapaneseVerb verb = new JapaneseVerb(godanVerbToPlayKanjiBase, godanVerbToPlayFuriganaBase, JapaneseVerbEnding.from(godanVerbToPlayEnding), godanVerbToPlayDefinition, JapaneseVerbType.GODAN);
        JapaneseFlashcard flashcardPolite = new JapaneseFlashcard(verb, new JapaneseVerbForm(false, true, presentIdicative));
        assertThat(flashcardPolite.getKanji()).isEqualTo(godanVerbToPlayKanjiBase + godanVerbToPlayPoliteEnding);
        assertThat(flashcardPolite.getFurigana()).isEqualTo(godanVerbToPlayFuriganaBase + godanVerbToPlayPoliteEnding);
        assertThat(flashcardPolite.getDefinition()).isEqualTo(godanVerbToPlayDefinition + " (" + definitionPresentIndicativePolite + ")");

        JapaneseFlashcard flashcardNegative = new JapaneseFlashcard(verb, new JapaneseVerbForm(true, false, presentIdicative));
        assertThat(flashcardNegative.getKanji()).isEqualTo(godanVerbToPlayKanjiBase + godanVerbToPlayNegativeEnding);
        assertThat(flashcardNegative.getFurigana()).isEqualTo(godanVerbToPlayFuriganaBase + godanVerbToPlayNegativeEnding);
        assertThat(flashcardNegative.getDefinition()).isEqualTo(godanVerbToPlayDefinition + " (" + definitionPresentIndicativeNegative + ")");
    }
}
