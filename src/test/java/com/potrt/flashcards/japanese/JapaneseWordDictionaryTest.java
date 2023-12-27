package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;

public class JapaneseWordDictionaryTest implements TestingConstants {
    private JapaneseWordDictionary wordDicitonary;
    private JapaneseKanjiDictionary kanjiDictionary;
    private JapaneseWordBuilder builder;
    private JapaneseKanji kanjiOne;
    private JapaneseKanji kanjiPerson;
    private JapaneseWord wordOne;
    private JapaneseWord wordAmPerson;
    private JapaneseWord wordOnePerson;

    @Before
    public void setup() {
        kanjiDictionary = new JapaneseKanjiDictionary();
        wordDicitonary = new JapaneseWordDictionary(kanjiDictionary);

        kanjiOne = kanjiDictionary.create(oneKanji, oneMeaning);
        kanjiPerson = kanjiDictionary.create(personKanji, personMeaning);

        builder = new JapaneseWordBuilder();
        builder.add(kanjiOne.withReading(wordOneFurigana));
        wordOne = new JapaneseWord(builder, wordOneDefinition);

        builder = new JapaneseWordBuilder();
        builder.add(kanjiPerson.withReading(wordAmPersonFurigana));
        builder.add(desu);
        wordAmPerson = new JapaneseWord(builder, wordAmPersonDefinition);

        builder = new JapaneseWordBuilder();
        builder.add(kanjiOne.withReading(wordOnePersonOneFurigana));
        builder.add(kanjiPerson.withReading(wordOnePersonPersonFurigana));
        wordOnePerson = new JapaneseWord(builder, wordOnePersonDefinition);
    }

    /**
     * Tests adding and getting items from the dicitonary.
     */
    @Test
    public void basicDictionaryPutGet() {
        wordDicitonary.put(wordOne);
        wordDicitonary.put(wordOnePerson);
        wordDicitonary.put(wordAmPerson);

        assertThat(wordDicitonary.get(wordOne.getKanji())).isEqualTo(wordOne);
        assertThat(wordDicitonary.get(wordOnePerson.getKanji())).isEqualTo(wordOnePerson);
        assertThat(wordDicitonary.get(wordAmPerson.getKanji())).isEqualTo(wordAmPerson);
    }

    /**
     * Test null.
     */
    @Test
    public void unfoundTest() {
        assertThat(wordDicitonary.get(wordOne.getKanji())).isNull();
    }


    /**
     * Test attempting to put the wrong word into a dictionary.
     */
    @Test
    public void basicInvalidPutTest() {
        wordDicitonary.put(wordOne);
        wordDicitonary.put(wordOnePerson);
        wordDicitonary.put(wordAmPerson);

        builder = new JapaneseWordBuilder();
        builder.add(kanjiOne.withReading(wordOneAlternateFurigana));
        JapaneseWord invalidDuplicateWordOne = new JapaneseWord(builder, wordOneDefinition);

        assertThatThrownBy(() -> wordDicitonary.put(invalidDuplicateWordOne)).isInstanceOf(IllegalStateException.class);
    }

    /**
     * Test inputing word built with wrong kanji.
     */
    @Test
    public void wrongKanjiTest() {
        JapaneseKanji nonassociatedOneKanji = new JapaneseKanji(oneKanji, oneMeaning);
        builder = new JapaneseWordBuilder();
        builder.add(nonassociatedOneKanji.withReading(wordOneFurigana));
        JapaneseWord nonassociatedWordOne = new JapaneseWord(builder, wordOneDefinition);
        assertThatThrownBy(() -> wordDicitonary.put(nonassociatedWordOne)).isInstanceOf(IllegalArgumentException.class);
    }
}
