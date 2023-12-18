package com.potrt.flashcards.japanese;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        kanjiDicitonary.addKanji(personKanji, personMeaning);
        JapaneseKanji kanji = kanjiDicitonary.getJapaneseKanji(personKanji);
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);
    }


    /**
     * Create multiple kanji for the dictionary.
     */
    @Test
    public void multiTest() {
        kanjiDicitonary.addKanji(personKanji, personMeaning);
        kanjiDicitonary.addKanji(IKanji, IMeaning);

        JapaneseKanji kanji = kanjiDicitonary.getJapaneseKanji(personKanji);
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);

        kanji = kanjiDicitonary.getJapaneseKanji(IKanji);
        assertThat(kanji.getKanji()).isEqualTo(IKanji);
        assertThat(kanji.getMeaning()).isEqualTo(IMeaning);
    }

    /**
     * Replace meaning for a kanji in the dictionary.
     */
    @Test
    public void replaceMeaning() {
        kanjiDicitonary.addKanji(personKanji, personMeaning);
        JapaneseKanji kanji = kanjiDicitonary.getJapaneseKanji(personKanji);
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaning);

        kanjiDicitonary.addKanji(personKanji, personMeaningAlternate);
        assertThat(kanji).isEqualTo(kanjiDicitonary.getJapaneseKanji(personKanji));
        assertThat(kanji.getKanji()).isEqualTo(personKanji);
        assertThat(kanji.getMeaning()).isEqualTo(personMeaningAlternate);
    }


}
