package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;

public class JapaneseKanjiTest implements TestingConstants {
    
    JapaneseKanji kanji;

    @Before
    public void setup() {
        kanji = new JapaneseKanji(personKanji, personMeaning);
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
        assertThat(kanji.getWords(personKanjiReadingOnePerson)).isEmpty();
        assertThat(kanji.numWords(personKanjiReadingOnePerson)).isZero();

    }

    /**
     * Kanji with reading test.
     */
    @Test
    public void kanjiWithReadingTest() {
        JapaneseKanjiWithReading kanjiWithReading = kanji.withReading(personKanjiReadingOnePerson);
        assertThat(kanjiWithReading.getKanji()).isEqualTo(personKanji);
        assertThat(kanjiWithReading.getFurigana()).isEqualTo(personKanjiReadingOnePerson);
        assertThat(kanjiWithReading.getJapaneseKanji()).isEqualTo(kanji);
    }

    /**
     * Checks that adding a new reading appropriately populates the {@link JapaneseKanji}.
     */
    @Test
    public void addReadingTest() {
        JapaneseKanjiWithReading kanjiWithReading = kanji.withReading(personKanjiReadingOnePerson);
        JapaneseWord word = new JapaneseWord(wordOnePersonKanji, wordOnePersonFurigana, wordOnePersonDefinition);
        kanjiWithReading.assignWord(word);
        assertThat(kanji.getReadings()).hasSize(1);
        assertThat(kanji.getReadings().get(0)).isEqualTo(personKanjiReadingOnePerson);

        assertThat(kanji.numWords()).isEqualTo(1);
        assertThat(kanji.getWords()).contains(word);

        assertThat(kanji.numWords(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getWords(personKanjiReadingOnePerson)).contains(word);
    }

    /**
     * Checks that adding multiple readings appropriately populates the {@link JapaneseKanji} and orders them by count.
     */
    @Test
    public void addMultipleReadingsTest() {
        // First insert.
        JapaneseKanjiWithReading kanjiWithReadingOnePerson = kanji.withReading(personKanjiReadingOnePerson);
        JapaneseWord wordOnePerson = new JapaneseWord(wordOnePersonKanji, wordOnePersonFurigana, wordOnePersonDefinition);
        kanjiWithReadingOnePerson.assignWord(wordOnePerson);

        assertThat(kanji.getReadings()).hasSize(1);
        assertThat(kanji.getReadings().get(0)).isEqualTo(personKanjiReadingOnePerson);

        assertThat(kanji.numWords()).isEqualTo(1);
        assertThat(kanji.getWords()).contains(wordOnePerson);

        assertThat(kanji.numWords(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getWords(personKanjiReadingOnePerson)).contains(wordOnePerson);


        // Second insert
        JapaneseKanjiWithReading kanjiWithReadingPerson = kanji.withReading(personKanjiReadingPerson);
        JapaneseWord wordPerson = new JapaneseWord(wordPersonKanji, wordPersonFurigana, wordPersonDefinition);
        kanjiWithReadingPerson.assignWord(wordPerson);

        assertThat(kanji.getReadings()).hasSize(2);
        assertThat(kanji.getReadings()).contains(personKanjiReadingOnePerson).contains(personKanjiReadingPerson);

        assertThat(kanji.numWords()).isEqualTo(2);
        assertThat(kanji.getWords()).contains(wordOnePerson).contains(wordPerson);

        assertThat(kanji.numWords(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getWords(personKanjiReadingOnePerson)).contains(wordOnePerson);
        assertThat(kanji.numWords(personKanjiReadingPerson)).isEqualTo(1);
        assertThat(kanji.getWords(personKanjiReadingPerson)).contains(wordPerson);

        // Third insert
        JapaneseKanjiWithReading kanjiWithReadingAmPerson = kanji.withReading(personKanjiReadingPerson);
        JapaneseWord wordAmPerson = new JapaneseWord(wordAmPersonKanji, wordAmPersonFurigana, wordAmPersonDefinition);
        kanjiWithReadingAmPerson.assignWord(wordAmPerson);

        assertThat(kanji.getReadings()).hasSize(2);
        assertThat(kanji.getReadings().get(0)).isEqualTo(personKanjiReadingPerson);
        assertThat(kanji.getReadings().get(1)).isEqualTo(personKanjiReadingOnePerson);

        assertThat(kanji.numWords()).isEqualTo(3);
        assertThat(kanji.getWords()).contains(wordOnePerson).contains(wordPerson).contains(wordAmPerson);

        assertThat(kanji.numWords(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getWords(personKanjiReadingOnePerson)).contains(wordOnePerson);
        assertThat(kanji.numWords(personKanjiReadingPerson)).isEqualTo(2);
        assertThat(kanji.getWords(personKanjiReadingPerson)).contains(wordPerson).contains(wordAmPerson);
    }
}
