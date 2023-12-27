package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.potrt.flashcards.TestingConstants;

public class JapaneseKanjiDictionaryTest implements TestingConstants {
    private JapaneseKanjiDictionary kanjiDicitonary;

    @Before
    public void setup() {
        kanjiDicitonary = new JapaneseKanjiDictionary();
    }

    /**
     * Create a kanji for the dictionary.
     */
    @Test
    public void basicTest() {
        kanjiDicitonary.put(personKanji, personMeaning);
        JapaneseKanji kanji = kanjiDicitonary.get(personKanji);
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);
    }

    /**
     * Create multiple kanji for the dictionary.
     */
    @Test
    public void multiTest() {
        kanjiDicitonary.put(personKanji, personMeaning);
        kanjiDicitonary.put(IKanji, IMeaning);

        JapaneseKanji kanji = kanjiDicitonary.get(personKanji);
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);

        kanji = kanjiDicitonary.get(IKanji);
        assertThat(kanji.getKanji()).isEqualTo(IKanji);
        assertThat(kanji.getMeaning()).isEqualTo(IMeaning);
    }

    /**
     * Replace meaning for a kanji in the dictionary.
     */
    @Test
    public void replaceMeaning() {
        kanjiDicitonary.put(personKanji, personMeaning);
        JapaneseKanji kanji = kanjiDicitonary.get(personKanji);
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);

        kanjiDicitonary.put(personKanji, personMeaningAlternate);
        assertThat(kanji).isEqualTo(kanjiDicitonary.get(personKanji));
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaningAlternate);
    }


    /**
     * Gets a kanji that doesn't have a definition. Should work with no meaning.  Also should throw a warning, but not easily testable.
     */
    @Test
    public void emptyTest() {
        JapaneseKanji kanji = kanjiDicitonary.get(personKanji);
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo("?");
    }
}
