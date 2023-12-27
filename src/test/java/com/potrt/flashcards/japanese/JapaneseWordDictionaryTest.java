package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;

public class JapaneseWordDictionaryTest implements TestingConstants {
    private JapaneseWordDictionary wordDicitonary;
    private JapaneseWordBuilder builder;
    private JapaneseKanji kanjiOne;
    private JapaneseKanji kanjiPerson;
    private JapaneseWord wordOne;
    private JapaneseWord wordAmPerson;
    private JapaneseWord wordOnePerson;

    @Before
    public void setup() {
        wordDicitonary = new JapaneseWordDictionary();

        kanjiOne = new JapaneseKanji(oneKanji, oneMeaning);
        kanjiPerson = new JapaneseKanji(personKanji, personMeaning);

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
     * Tests adding and getting items from the dicitonary.
     */
    @Test
    public void basicReplaceTest() {
        wordDicitonary.put(wordOne);
        wordDicitonary.put(wordOnePerson);
        wordDicitonary.put(wordAmPerson);

        assertThat(wordDicitonary.get(wordOne.getKanji())).isEqualTo(wordOne);
        assertThat(wordDicitonary.get(wordOnePerson.getKanji())).isEqualTo(wordOnePerson);
        assertThat(wordDicitonary.get(wordAmPerson.getKanji())).isEqualTo(wordAmPerson);
    }
}
