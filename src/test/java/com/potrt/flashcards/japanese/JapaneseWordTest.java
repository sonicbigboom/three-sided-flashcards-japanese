package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;

public class JapaneseWordTest implements TestingConstants {
    private JapaneseKanji oneJapaneseKanji;
    private JapaneseKanji personJapaneseKanji;
    private JapaneseKanji toDrinkJapaneseKanji;
    private JapaneseKanji IJapaneseKanji;
    private JapaneseWordBuilder builder;

    @Before
    public void setup() {
        oneJapaneseKanji = new JapaneseKanji(oneKanji, oneMeaning);
        personJapaneseKanji = new JapaneseKanji(personKanji, personMeaning);
        toDrinkJapaneseKanji = new JapaneseKanji(drinkKanji, drinkMeaning);
        IJapaneseKanji = new JapaneseKanji(IKanji, IMeaning);
        builder = new JapaneseWordBuilder();
    }

    /**
     * Test a single kanji.
     */
    @Test
    public void singleKanjiTest() {
        builder.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        JapaneseWord string = new JapaneseWord(builder, personMeaning);
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
        JapaneseWord string = new JapaneseWord(builder, irregularVerbToComeDefinition);
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
        JapaneseWord string = new JapaneseWord(builder, godanVerbToDrinkDefinition);
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
        JapaneseWord string = new JapaneseWord(builder, sentenceIAmAHuman);
        assertThat(string.getKanji()).isEqualTo(wordIKanji + particleTopic + wordPersonKanji + desu + japanesePeriod);
        assertThat(string.getFurigana()).isEqualTo(wordIFurigana + particleTopic + wordPersonFurigana + desu + japanesePeriod);
        assertThat(string.getDefinition()).isEqualTo(sentenceIAmAHuman);
    }

    /**
     * Checks that a basic representation word works.
     */
    @Test
    public void representationTest() {
        builder.add(IJapaneseKanji.withReading(wordIFurigana));
        JapaneseWord word = new JapaneseWord(builder, wordIDefinition);
        JapaneseWord.Representation rep = word.getRepresentation();
        assertThat(rep.getKanji()).isEqualTo(wordIKanji);
        assertThat(rep.getFurigana()).isEqualTo(wordIFurigana);
        assertThat(rep.getDefinition()).isEqualTo(wordIDefinition);
        assertThat(rep.getWord()).isEqualTo(word);
    }

    /**
     * Test changing score.
     */
    @Test
    public void attemptTest() {
        builder.add(oneJapaneseKanji.withReading(wordOneFurigana));
        builder.add(personJapaneseKanji.withReading(wordOnePersonPersonFurigana));
        JapaneseWord word = new JapaneseWord(builder, wordOnePersonDefinition);
        word.attachToKanji();

        word.attempt(true);
        word.attempt(false);
        word.attempt(false);
        word.attempt(true);
        word.attempt(true);

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
        JapaneseWord builtSentence = new JapaneseWord(builder, sentenceIAmAHuman);

        builder = new JapaneseWordBuilder();
        builder.add(IJapaneseKanji.withReading(wordIFurigana));
        builder.add(particleTopic);
        builder.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        builder.add(desu);
        builder.add(japanesePeriod);
        JapaneseWord duplicateSentence = new JapaneseWord(builder, sentenceIAmAHuman);
        
        assertThat(builtSentence).hasSameHashCodeAs(duplicateSentence).isEqualTo(duplicateSentence);
        assertThat(duplicateSentence).hasSameHashCodeAs(builtSentence).isEqualTo(builtSentence);
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
        JapaneseWord builtSentence = new JapaneseWord(builder, sentenceIAmAHuman);

        builder = new JapaneseWordBuilder();
        builder.add(IJapaneseKanji.withReading(wordIFurigana));
        builder.add(particleTopic);
        builder.add(personJapaneseKanji.withReading(personKanjiReadingPerson));
        builder.add(desu);
        builder.add(japaneseQuestionMark);
        JapaneseWord changedSentence = new JapaneseWord(builder, sentenceIAmAHuman);

        assertThat(builtSentence).doesNotHaveSameHashCodeAs(changedSentence).isNotEqualTo(changedSentence);
        assertThat(changedSentence).doesNotHaveSameHashCodeAs(builtSentence).isNotEqualTo(builtSentence);
        
        assertThat(builtSentence).isNotEqualTo(sentenceIAmAHuman);
    }
}
