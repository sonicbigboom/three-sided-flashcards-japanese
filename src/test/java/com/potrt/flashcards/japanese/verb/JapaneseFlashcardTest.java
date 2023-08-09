package com.potrt.flashcards.japanese.verb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseFlashcard;
import com.potrt.flashcards.japanese.JapanesePhrase;
import com.potrt.flashcards.japanese.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseFlashcardTest implements TestingConstants {
    
    /**
     * Creates a simple flashcard.
     */
    @Test
    public void basicTest() {
        JapaneseFlashcard flashcard = new JapaneseFlashcard(wordIKanji, wordIFurigana, wordIDefinition);
        assertThat(flashcard.getKanji()).contains(wordIKanji);
        assertThat(flashcard.getFurigana()).contains(wordIFurigana);
        assertThat(flashcard.getDefinition()).contains(wordIDefinition);
    }

    /**
     * Creates a flashcard from a {@link JapanesePhrase}.
     */
    @Test
    public void flashcardFromPhraseTest() {
        JapanesePhrase phrase = new JapanesePhrase(phraseOnePersonKanji, phraseOnePersonFurigana, phraseOnePersonDefinition);
        JapaneseFlashcard flashcard = new JapaneseFlashcard(phrase);
        assertThat(flashcard.getKanji()).contains(phraseOnePersonKanji);
        assertThat(flashcard.getFurigana()).contains(phraseOnePersonFurigana);
        assertThat(flashcard.getDefinition()).contains(phraseOnePersonDefinition);
    }

    /**
     * Creates a flashcard from a {@link JapaneseVerb} and a {@link JapaneseVerbFrom}.
     */
    @Test
    public void flashcardFromVerbTest() {
        JapaneseVerb verb = new JapaneseVerb(godanVerbToPlayKanjiBase, godanVerbToPlayFuriganaBase, JapaneseVerbEnding.from(godanVerbToPlayEnding), godanVerbToPlayDefinition, JapaneseVerbType.GODAN);
        JapaneseFlashcard flashcardPolite = new JapaneseFlashcard(verb, new JapaneseVerbForm(false, true, presentIdicative));
        assertThat(flashcardPolite.getKanji()).contains(godanVerbToPlayKanjiBase + godanVerbToPlayPoliteEnding);
        assertThat(flashcardPolite.getFurigana()).contains(godanVerbToPlayFuriganaBase + godanVerbToPlayPoliteEnding);
        assertThat(flashcardPolite.getDefinition()).contains(godanVerbToPlayDefinition + "(" + definitionPresentIndicativePolite + ")");

        JapaneseFlashcard flashcardNegative = new JapaneseFlashcard(verb, new JapaneseVerbForm(true, false, presentIdicative));
        assertThat(flashcardNegative.getKanji()).contains(godanVerbToPlayKanjiBase + godanVerbToPlayNegativeEnding);
        assertThat(flashcardNegative.getFurigana()).contains(godanVerbToPlayFuriganaBase + godanVerbToPlayNegativeEnding);
        assertThat(flashcardNegative.getDefinition()).contains(godanVerbToPlayDefinition + "(" + definitionPresentIndicativeNegative + ")");
    }
}
