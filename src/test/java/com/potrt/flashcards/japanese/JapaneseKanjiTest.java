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
        kanji = new JapaneseKanji(personKanji);
    }

    /**
     * Checks that the kanji has been created.
     */
    @Test
    public void createKanjiTest() {
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
    }

    /**
     * Checks that everything is empty before characters have been added.
     */
    @Test
    public void emptyKanjiTest() {
        assertThat(kanji.numPhrases()).isZero();
        assertThat(kanji.getPhrases()).isEmpty();
        assertThat(kanji.getReadings()).isEmpty();
    }

    /**
     * Check that empty lists and values are gotten when the reading doesn't exists.
     */
    @Test
    public void getNonexistantTest() {
        assertThat(kanji.getPhrases(personKanjiReadingOnePerson)).isEmpty();
        assertThat(kanji.numPhrases(personKanjiReadingOnePerson)).isZero();

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
        JapanesePhrase phrase = new JapanesePhrase(phraseOnePersonKanji, phraseOnePersonFurigana, phraseOnePersonDefinition);
        kanjiWithReading.updateWithPhrase(phrase);
        assertThat(kanji.getReadings()).hasSize(1);
        assertThat(kanji.getReadings().get(0)).isEqualTo(personKanjiReadingOnePerson);

        assertThat(kanji.numPhrases()).isEqualTo(1);
        assertThat(kanji.getPhrases()).contains(phrase);

        assertThat(kanji.numPhrases(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getPhrases(personKanjiReadingOnePerson)).contains(phrase);
    }

    /**
     * Checks that adding multiple readings appropriately populates the {@link JapaneseKanji} and orders them by count.
     */
    @Test
    public void addMultipleReadingsTest() {
        // First insert.
        JapaneseKanjiWithReading kanjiWithReadingOnePerson = kanji.withReading(personKanjiReadingOnePerson);
        JapanesePhrase phraseOnePerson = new JapanesePhrase(phraseOnePersonKanji, phraseOnePersonFurigana, phraseOnePersonDefinition);
        kanjiWithReadingOnePerson.updateWithPhrase(phraseOnePerson);

        assertThat(kanji.getReadings()).hasSize(1);
        assertThat(kanji.getReadings().get(0)).isEqualTo(personKanjiReadingOnePerson);

        assertThat(kanji.numPhrases()).isEqualTo(1);
        assertThat(kanji.getPhrases()).contains(phraseOnePerson);

        assertThat(kanji.numPhrases(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getPhrases(personKanjiReadingOnePerson)).contains(phraseOnePerson);


        // Second insert
        JapaneseKanjiWithReading kanjiWithReadingPerson = kanji.withReading(personKanjiReadingPerson);
        JapanesePhrase phrasePerson = new JapanesePhrase(phrasePersonKanji, phrasePersonFurigana, phrasePersonDefinition);
        kanjiWithReadingPerson.updateWithPhrase(phrasePerson);

        assertThat(kanji.getReadings()).hasSize(2);
        assertThat(kanji.getReadings()).contains(personKanjiReadingOnePerson).contains(personKanjiReadingPerson);

        assertThat(kanji.numPhrases()).isEqualTo(2);
        assertThat(kanji.getPhrases()).contains(phraseOnePerson).contains(phrasePerson);

        assertThat(kanji.numPhrases(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getPhrases(personKanjiReadingOnePerson)).contains(phraseOnePerson);
        assertThat(kanji.numPhrases(personKanjiReadingPerson)).isEqualTo(1);
        assertThat(kanji.getPhrases(personKanjiReadingPerson)).contains(phrasePerson);

        // Third insert
        JapaneseKanjiWithReading kanjiWithReadingAmPerson = kanji.withReading(personKanjiReadingPerson);
        JapanesePhrase phraseAmPerson = new JapanesePhrase(phraseAmPersonKanji, phraseAmPersonFurigana, phraseAmPersonDefinition);
        kanjiWithReadingAmPerson.updateWithPhrase(phraseAmPerson);

        assertThat(kanji.getReadings()).hasSize(2);
        assertThat(kanji.getReadings().get(0)).isEqualTo(personKanjiReadingPerson);
        assertThat(kanji.getReadings().get(1)).isEqualTo(personKanjiReadingOnePerson);

        assertThat(kanji.numPhrases()).isEqualTo(3);
        assertThat(kanji.getPhrases()).contains(phraseOnePerson).contains(phrasePerson).contains(phraseAmPerson);

        assertThat(kanji.numPhrases(personKanjiReadingOnePerson)).isEqualTo(1);
        assertThat(kanji.getPhrases(personKanjiReadingOnePerson)).contains(phraseOnePerson);
        assertThat(kanji.numPhrases(personKanjiReadingPerson)).isEqualTo(2);
        assertThat(kanji.getPhrases(personKanjiReadingPerson)).contains(phrasePerson).contains(phraseAmPerson);
    }
}
