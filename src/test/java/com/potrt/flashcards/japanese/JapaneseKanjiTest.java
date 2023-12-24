package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.Score;
import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;
import com.potrt.flashcards.japanese.word.JapaneseWord;
import com.potrt.flashcards.japanese.word.JapaneseWordBuilder;

public class JapaneseKanjiTest implements TestingConstants {
    private JapaneseKanji kanji;
    private JapaneseWordBuilder builder;

    @Before
    public void setup() {
        kanji = new JapaneseKanji(personKanji, personMeaning);
        builder = new JapaneseWordBuilder();
    }

    /**
     * Checks that the kanji has been created.
     */
    @Test
    public void createKanjiTest() {
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);
    }

    /**
     * Checks that changing the kanji meaning works.
     */
    @Test
    public void changeMeaningTest() {
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);

        kanji.setMeaning(personMeaningAlternate);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaningAlternate);
    }

    /**
     * Checks that everything is empty before characters have been added.
     */
    @Test
    public void emptyKanjiTest() {
        assertThat(kanji.numWords()).isZero();
        assertThat(kanji.getWords()).isEmpty();
        assertThat(kanji.getReadings()).isEmpty();
    }

    /**
     * Check that empty lists and values are gotten when the reading doesn't exists.
     */
    @Test
    public void getNonexistantTest() {
        assertThat(kanji.getWords(wordOnePersonPersonFurigana)).isEmpty();
        assertThat(kanji.numWords(wordOnePersonPersonFurigana)).isZero();

    }

    /**
     * Kanji with reading test.
     */
    @Test
    public void kanjiWithReadingTest() {
        JapaneseKanjiWithReading kanjiWithReading = kanji.withReading(wordOnePersonPersonFurigana);
        assertThat(kanjiWithReading.getKanji()).isEqualTo(personKanji);
        assertThat(kanjiWithReading.getFurigana()).isEqualTo(wordOnePersonPersonFurigana);
        assertThat(kanjiWithReading.getJapaneseKanji()).isEqualTo(kanji);
    }

    /**
     * Checks that adding a new reading appropriately populates the {@link JapaneseKanji}.
     */
    @Test
    public void addReadingTest() {
        JapaneseKanjiWithReading kanjiWithReading = kanji.withReading(wordOnePersonPersonFurigana);
        builder.add(kanjiWithReading);
        JapaneseWord word = builder.getJapaneseWord(wordOnePersonDefinition);
        assertThat(kanji.getReadings()).hasSize(1);
        assertThat(kanji.getReadings().get(0)).isEqualTo(wordOnePersonPersonFurigana);

        assertThat(kanji.numWords()).isEqualTo(1);
        assertThat(kanji.getWords()).contains(word);

        assertThat(kanji.numWords(wordOnePersonPersonFurigana)).isEqualTo(1);
        assertThat(kanji.getWords(wordOnePersonPersonFurigana)).contains(word);
    }

    /**
     * Checks that adding multiple readings appropriately populates the {@link JapaneseKanji} and orders them by count.
     */
    @Test
    public void addMultipleReadingsTest() {
        // First insert.
        JapaneseKanjiWithReading kanjiWithReadingOnePerson = kanji.withReading(wordOnePersonPersonFurigana);
        builder.add(kanjiWithReadingOnePerson);
        JapaneseWord wordOnePerson = builder.getJapaneseWord(wordOnePersonDefinition);

        assertThat(kanji.getReadings()).hasSize(1);
        assertThat(kanji.getReadings().get(0)).isEqualTo(wordOnePersonPersonFurigana);

        assertThat(kanji.numWords()).isEqualTo(1);
        assertThat(kanji.getWords()).contains(wordOnePerson);

        assertThat(kanji.numWords(wordOnePersonPersonFurigana)).isEqualTo(1);
        assertThat(kanji.getWords(wordOnePersonPersonFurigana)).contains(wordOnePerson);


        // Second insert
        JapaneseKanjiWithReading kanjiWithReadingPerson = kanji.withReading(personKanjiReadingPerson);
        builder = new JapaneseWordBuilder();
        builder.add(kanjiWithReadingPerson);
        JapaneseWord wordPerson = builder.getJapaneseWord(wordPersonDefinition);

        assertThat(kanji.getReadings()).hasSize(2);
        assertThat(kanji.getReadings()).contains(wordOnePersonPersonFurigana).contains(personKanjiReadingPerson);

        assertThat(kanji.numWords()).isEqualTo(2);
        assertThat(kanji.getWords()).contains(wordOnePerson).contains(wordPerson);

        assertThat(kanji.numWords(wordOnePersonPersonFurigana)).isEqualTo(1);
        assertThat(kanji.getWords(wordOnePersonPersonFurigana)).contains(wordOnePerson);
        assertThat(kanji.numWords(personKanjiReadingPerson)).isEqualTo(1);
        assertThat(kanji.getWords(personKanjiReadingPerson)).contains(wordPerson);

        // Third insert
        JapaneseKanjiWithReading kanjiWithReadingAmPerson = kanji.withReading(personKanjiReadingPerson);
        builder = new JapaneseWordBuilder();
        builder.add(kanjiWithReadingAmPerson);
        builder.add(desu);
        JapaneseWord wordAmPerson = builder.getJapaneseWord(wordAmPersonDefinition);

        assertThat(kanji.getReadings()).hasSize(2);
        assertThat(kanji.getReadings().get(0)).isEqualTo(personKanjiReadingPerson);
        assertThat(kanji.getReadings().get(1)).isEqualTo(wordOnePersonPersonFurigana);

        assertThat(kanji.numWords()).isEqualTo(3);
        assertThat(kanji.getWords()).contains(wordOnePerson).contains(wordPerson).contains(wordAmPerson);

        assertThat(kanji.numWords(wordOnePersonPersonFurigana)).isEqualTo(1);
        assertThat(kanji.getWords(wordOnePersonPersonFurigana)).contains(wordOnePerson);
        assertThat(kanji.numWords(personKanjiReadingPerson)).isEqualTo(2);
        assertThat(kanji.getWords(personKanjiReadingPerson)).contains(wordPerson).contains(wordAmPerson);
    }

    /**
     * Test different scores and aggregation.
     */
    @Test
    public void scoresTest() {
        // First insert.
        JapaneseKanjiWithReading kanjiWithReadingOnePerson = kanji.withReading(wordOnePersonPersonFurigana);
        builder.add(kanjiWithReadingOnePerson);
        JapaneseWord wordOnePerson = builder.getJapaneseWord(wordOnePersonDefinition);

        kanjiWithReadingOnePerson.attempt(false);
        kanjiWithReadingOnePerson.attempt(false);
        kanjiWithReadingOnePerson.attempt(true);
        kanjiWithReadingOnePerson.attempt(true);
        kanjiWithReadingOnePerson.attempt(true);

        Score onePersonScore = kanjiWithReadingOnePerson.getScore();
        assertThat(onePersonScore.getSuccessRate()).isEqualTo(0.6);
        assertThat(kanji.getScore(wordOnePersonPersonFurigana)).isEqualTo(onePersonScore);
        assertThat(kanji.getScore().getSuccessRate()).isEqualTo(0.6);

        // Second insert
        JapaneseKanjiWithReading kanjiWithReadingPerson = kanji.withReading(personKanjiReadingPerson);
        builder = new JapaneseWordBuilder();
        builder.add(kanjiWithReadingPerson);
        JapaneseWord wordPerson = builder.getJapaneseWord(wordPersonDefinition);

        kanjiWithReadingPerson.attempt(true);
        kanjiWithReadingPerson.attempt(true);
        kanjiWithReadingPerson.attempt(true);
        kanjiWithReadingPerson.attempt(true);
        kanjiWithReadingPerson.attempt(true);

        Score personScore = kanjiWithReadingPerson.getScore();
        assertThat(personScore.getSuccessRate()).isEqualTo(1);
        assertThat(kanji.getScore(personKanjiReadingPerson)).isEqualTo(personScore);
        assertThat(kanji.getScore().getSuccessRate()).isEqualTo(0.8);

        // Third insert
        JapaneseKanjiWithReading kanjiWithReadingAmPerson = kanji.withReading(personKanjiReadingPerson);
        builder = new JapaneseWordBuilder();
        builder.add(kanjiWithReadingAmPerson);
        JapaneseWord wordAmPerson = builder.getJapaneseWord(wordAmPersonDefinition);

        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(false);
        kanjiWithReadingAmPerson.attempt(true);
        kanjiWithReadingAmPerson.attempt(true);

        Score amPersonScore = kanjiWithReadingAmPerson.getScore();
        assertThat(amPersonScore.getDisplaySuccessRate()).isEqualTo("46.67%");
        assertThat(amPersonScore).isEqualTo(personScore);
        assertThat(kanji.getScore().getSuccessRate()).isEqualTo(0.5);
    }

    /**
     * Test non-existant score.
     */
    @Test
    public void nonExistantScore() {
        assertThat(kanji.getScore(wordOnePersonPersonFurigana).getAttempts()).isZero();
    }

    /**
     * Test attempting a reading without a associated word.
     */
    @Test
    public void noWordAttemptTest() {
        JapaneseKanjiWithReading kanjiWithReadingPerson = kanji.withReading(personKanjiReadingPerson);
        assertThatThrownBy(() -> kanjiWithReadingPerson.attempt(true)).isInstanceOf(IllegalStateException.class);
    }
}
