package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;

public class JapaneseStringTest implements TestingConstants {
    JapaneseKanji oneJapaneseKanji;
    JapaneseKanji personJapaneseKanji;
    JapaneseKanji toDrinkJapaneseKanji;
    JapaneseKanji IJapaneseKanji;
    JapaneseString kana;

    @Before
    public void setup() {
        oneJapaneseKanji = new JapaneseKanji(oneKanji, oneMeaning);
        personJapaneseKanji = new JapaneseKanji(personKanji, personMeaning);
        toDrinkJapaneseKanji = new JapaneseKanji(toDrinkKanji, godanVerbToDrinkDefinition);
        IJapaneseKanji = new JapaneseKanji(IKanji, wordIDefinition);
        kana = new JapaneseString();
    }

    /**
     * Test a single kanji.
     */
    @Test
    public void singleKanjiTest() {
        kana.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        assertThat(kana.getKanji()).isEqualTo(String.valueOf(personKanji));
        assertThat(kana.getFurigana()).isEqualTo(personKanjiReadingPerson);
    }

    /**
     * Test a single furigana.
     */
    @Test
    public void singleFurigana() {
        JapaneseString kana = new JapaneseString();
        kana.add(irregularVerbToComeKana);
        assertThat(kana.getKanji()).isEqualTo(irregularVerbToComeKana);
        assertThat(kana.getFurigana()).isEqualTo(irregularVerbToComeKana);

    }

    /**
     * Test a mix of kanji and furigana.
     */
    @Test
    public void mixedKanaTest() {
        kana.add(toDrinkJapaneseKanji.withReading(godanVerbToDrinkFuriganaBase));
        kana.add(godanVerbToDrinkEnding);
        assertThat(kana.getKanji()).isEqualTo(godanVerbToDrinkKanjiBase + godanVerbToDrinkEnding);
        assertThat(kana.getFurigana()).isEqualTo(godanVerbToDrinkFuriganaBase + godanVerbToDrinkEnding);
    }

    /**
     * Tests a Japanese sentence.
     */
    @Test
    public void sentenceTest() {
        kana.add(IJapaneseKanji.withReading(wordIFurigana));
        kana.add(particleTopic);
        kana.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        kana.add(desu);
        kana.add(japanesePeriod);
        assertThat(kana.getKanji()).isEqualTo(wordIKanji + particleTopic + wordPersonKanji + desu + japanesePeriod);
        assertThat(kana.getFurigana()).isEqualTo(wordIFurigana + particleTopic + wordPersonFurigana + desu + japanesePeriod);
    }

    /**
     * Test assigning a word.
     */
    @Test
    public void assignWordTest() {
        kana.add(oneJapaneseKanji.withReading(wordOneFurigana));
        kana.add(personJapaneseKanji.withReading(personKanjiReadingOnePerson));
        JapaneseWord word = new JapaneseWord(kana.getKanji(), kana.getFurigana(), wordOnePersonDefinition);
        kana.assignWord(word);
        assertThat(oneJapaneseKanji.getWords(wordOneFurigana)).contains(word);
        assertThat(personJapaneseKanji.getWords(personKanjiReadingOnePerson)).contains(word);
    }

    /**
     * Test changing score.
     */
    @Test
    public void attemptTest() {
        kana.add(oneJapaneseKanji.withReading(wordOneFurigana));
        kana.add(personJapaneseKanji.withReading(personKanjiReadingOnePerson));
        JapaneseWord word = new JapaneseWord(kana.getKanji(), kana.getFurigana(), wordOnePersonDefinition);
        kana.assignWord(word);
        kana.attempt(true);
        kana.attempt(false);
        kana.attempt(false);
        kana.attempt(true);
        kana.attempt(true);
        assertThat(oneJapaneseKanji.getScore().getSuccessRate()).isEqualTo(0.6);
        assertThat(personJapaneseKanji.getScore().getSuccessRate()).isEqualTo(0.6);
    }
}
