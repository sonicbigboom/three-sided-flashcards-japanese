package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.verb.JapaneseVerb;
import com.potrt.flashcards.japanese.verb.JapaneseVerbBuilder;
import com.potrt.flashcards.japanese.verb.JapaneseVerbForm;
import com.potrt.flashcards.japanese.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseFlashcardTest implements TestingConstants {
    /**
     * Creates a flashcard from a {@link JapaneseWord}.
     */
    @Test
    public void flashcardFromWordTest() {
        JapaneseKanji oneJapaneseKanji = new JapaneseKanji(oneKanji, oneMeaning);
        JapaneseKanji personJapaneseKanji = new JapaneseKanji(personKanji, personMeaning);
        JapaneseWordBuilder builder = new JapaneseWordBuilder();
        builder.add(oneJapaneseKanji.withReading(wordOnePersonOneFurigana));
        builder.add(personJapaneseKanji.withReading(wordOnePersonPersonFurigana));

        JapaneseWord word = builder.getJapaneseWord(wordOnePersonDefinition);
        JapaneseFlashcard flashcard = new JapaneseFlashcard(word);
        assertThat(flashcard.getKanji()).isEqualTo(wordOnePersonKanji);
        assertThat(flashcard.getFurigana()).isEqualTo(wordOnePersonFurigana);
        assertThat(flashcard.getDefinition()).isEqualTo(wordOnePersonDefinition);
        assertThat(flashcard.getJapaneseWord()).isEqualTo(word);
    }

    /**
     * Creates a flashcard from a {@link JapaneseVerb} and a {@link JapaneseVerbFrom}.
     */
    @Test
    public void flashcardFromVerbTest() {
        JapaneseVerbBuilder builder = new JapaneseVerbBuilder();
        JapaneseKanji kanji = new JapaneseKanji(playKanji, playMeaning);
        builder.add(kanji.withReading(godanVerbToPlayFuriganaBase));
        builder.add(godanVerbToPlayEnding);
        JapaneseVerb verb = builder.getJapaneseVerb(godanVerbToPlayDefinition, JapaneseVerbType.GODAN);

        JapaneseFlashcard flashcardPolite = new JapaneseFlashcard(verb.conjugate(new JapaneseVerbForm(false, true, presentIdicative)));
        assertThat(flashcardPolite.getKanji()).isEqualTo(godanVerbToPlayKanjiBase + godanVerbToPlayPoliteEnding);
        assertThat(flashcardPolite.getFurigana()).isEqualTo(godanVerbToPlayFuriganaBase + godanVerbToPlayPoliteEnding);
        assertThat(flashcardPolite.getDefinition()).isEqualTo(godanVerbToPlayDefinition + " (" + definitionPresentIndicativePolite + ")");
        assertThat(flashcardPolite.getJapaneseWord()).isEqualTo(verb);

        JapaneseFlashcard flashcardNegative = new JapaneseFlashcard(verb.conjugate(new JapaneseVerbForm(true, false, presentIdicative)));
        assertThat(flashcardNegative.getKanji()).isEqualTo(godanVerbToPlayKanjiBase + godanVerbToPlayNegativeEnding);
        assertThat(flashcardNegative.getFurigana()).isEqualTo(godanVerbToPlayFuriganaBase + godanVerbToPlayNegativeEnding);
        assertThat(flashcardNegative.getDefinition()).isEqualTo(godanVerbToPlayDefinition + " (" + definitionPresentIndicativeNegative + ")");
        assertThat(flashcardNegative.getJapaneseWord()).isEqualTo(verb);
    }
}
