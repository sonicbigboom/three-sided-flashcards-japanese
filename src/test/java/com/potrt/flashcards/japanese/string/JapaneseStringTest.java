package com.potrt.flashcards.japanese.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;
import com.potrt.flashcards.japanese.JapaneseKanji;
import com.potrt.flashcards.japanese.JapaneseWord;
import com.potrt.flashcards.japanese.JapaneseWordTestImplementation;

public class JapaneseStringTest implements TestingConstants {
    private JapaneseKanji oneJapaneseKanji;
    private JapaneseKanji personJapaneseKanji;
    private JapaneseKanji toDrinkJapaneseKanji;
    private JapaneseKanji IJapaneseKanji;
    private JapaneseStringBuilder builder;

    @Before
    public void setup() {
        oneJapaneseKanji = new JapaneseKanji(oneKanji, oneMeaning);
        personJapaneseKanji = new JapaneseKanji(personKanji, personMeaning);
        toDrinkJapaneseKanji = new JapaneseKanji(toDrinkKanji, godanVerbToDrinkDefinition);
        IJapaneseKanji = new JapaneseKanji(IKanji, IMeaning);
        builder = new JapaneseStringBuilder();
    }

    /**
     * Test a single kanji.
     */
    @Test
    public void singleKanjiTest() {
        builder.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        JapaneseString string = builder.getJapaneseString(personMeaning);
        assertThat(string.getKanji()).isEqualTo(String.valueOf(personKanji));
        assertThat(string.getFurigana()).isEqualTo(personKanjiReadingPerson);
        assertThat(string.getDefinition()).isEqualTo(personMeaning);
    }

    /**
     * Test a single furigana.
     */
    @Test
    public void singleFurigana() {
        builder.add(irregularVerbToComeKana);
        JapaneseString string = builder.getJapaneseString(irregularVerbToComeDefinition);
        assertThat(string.getKanji()).isEqualTo(irregularVerbToComeKana);
        assertThat(string.getFurigana()).isEqualTo(irregularVerbToComeKana);
        assertThat(string.getDefinition()).isEqualTo(irregularVerbToComeDefinition);
    }

    /**
     * Test a mix of kanji and furigana.
     */
    @Test
    public void mixedKanaTest() {
        builder.add(toDrinkJapaneseKanji.withReading(godanVerbToDrinkFuriganaBase));
        builder.add(godanVerbToDrinkEnding);
        JapaneseString string = builder.getJapaneseString(godanVerbToDrinkDefinition);
        assertThat(string.getKanji()).isEqualTo(godanVerbToDrinkKanjiBase + godanVerbToDrinkEnding);
        assertThat(string.getFurigana()).isEqualTo(godanVerbToDrinkFuriganaBase + godanVerbToDrinkEnding);
        assertThat(string.getDefinition()).isEqualTo(godanVerbToDrinkDefinition);
    }

    /**
     * Tests a Japanese sentence.
     * Note that this should not ever be done, but is useful for testing.
     */
    @Test
    public void sentenceTest() {
        builder.add(IJapaneseKanji.withReading(wordIFurigana));
        builder.add(particleTopic);
        builder.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        builder.add(desu);
        builder.add(japanesePeriod);
        JapaneseString string = builder.getJapaneseString(sentenceIAmAHuman);
        assertThat(string.getKanji()).isEqualTo(wordIKanji + particleTopic + wordPersonKanji + desu + japanesePeriod);
        assertThat(string.getFurigana()).isEqualTo(wordIFurigana + particleTopic + wordPersonFurigana + desu + japanesePeriod);
        assertThat(string.getDefinition()).isEqualTo(sentenceIAmAHuman);
    }

    /**
     * Test changing score.
     */
    @Test
    public void attemptTest() {
        builder.add(oneJapaneseKanji.withReading(wordOneFurigana));
        builder.add(personJapaneseKanji.withReading(personKanjiReadingOnePerson));
        JapaneseString string = builder.getJapaneseString(wordOnePersonDefinition);
        string.attempt(true);
        string.attempt(false);
        string.attempt(false);
        string.attempt(true);
        string.attempt(true);
        assertThat(oneJapaneseKanji.getScore().getSuccessRate()).isEqualTo(0.6);
        assertThat(personJapaneseKanji.getScore().getSuccessRate()).isEqualTo(0.6);
    }

    /**
     * Check that hash equality works.
     */
    @Test
    public void equalityTest() {
        builder.add(IJapaneseKanji.withReading(wordIFurigana));
        builder.add(particleTopic);
        builder.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        builder.add(desu);
        builder.add(japanesePeriod);
        JapaneseWord builtSentence = builder.getJapaneseString(sentenceIAmAHuman);
        JapaneseWord directSentence = new JapaneseWordTestImplementation(wordIKanji + particleTopic + personKanji + desu + japanesePeriod, 
                                                                         wordIFurigana + particleTopic + personKanjiReadingPerson + desu + japanesePeriod, 
                                                                         sentenceIAmAHuman);
        assertThat(builtSentence).hasSameHashCodeAs(directSentence).isEqualTo(directSentence);
        assertThat(directSentence).hasSameHashCodeAs(builtSentence).isEqualTo(builtSentence);
    }

    /**
     * Check that inequality equality works.
     */
    @Test
    public void inequalityTest() {
        builder.add(IJapaneseKanji.withReading(wordIFurigana));
        builder.add(particleTopic);
        builder.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        builder.add(desu);
        builder.add(japanesePeriod);
        JapaneseWord builtSentence = builder.getJapaneseString(sentenceIAmAHuman);
        JapaneseWord directSentence = new JapaneseWordTestImplementation(wordIKanji + particleTopic + personKanji + desu + japaneseQuestionMark, 
                                                                         wordIFurigana + particleTopic + personKanjiReadingPerson + desu + japaneseQuestionMark, 
                                                                         sentenceIAmAHuman);
        assertThat(builtSentence).doesNotHaveSameHashCodeAs(directSentence).isNotEqualTo(directSentence);
        assertThat(directSentence).doesNotHaveSameHashCodeAs(builtSentence).isNotEqualTo(builtSentence);
    }
}
