package com.potrt.flashcards.japanese.string;

import java.util.ArrayList;
import java.util.List;

import com.potrt.flashcards.japanese.JapaneseWord;
import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;

/**
 * Represents a string of Japanese characters making up a Japanese word.  Should not ever be a sentence.
 */
public class JapaneseString extends JapaneseWord {
    /**
     * A list of all kanji.
     * @implNote This is just used as a shortcut for certain methods and it does not need to be in order.
     */
    private List<JapaneseKanjiWithReading> kanjiList = new ArrayList<>();
    

    /**
     * Creates a {@link JapaneseString} from a list of kana and a list of kanji.
     * @param builder The {@link JapaneseStringBuilder} that is ready to create the string.
     * @param definition The meaning of the string.
     * @apiNote This method should only be called from within {@link JapaneseStringBuilder}.
     */
    JapaneseString(JapaneseStringBuilder builder, String definition) {
        super(builder.getKanji(), builder.getFurigana(), definition);
        this.kanjiList = builder.kanjiList;
    } 

    /**
     * Adds a new successful or failed attempt for each kanji.
     * @param succeeded If the attempt was successful.
     */
    public void attempt(boolean succeeded) {
        for (JapaneseKanjiWithReading kanji : kanjiList) {
            kanji.attempt(succeeded);
        }
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
