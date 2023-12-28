package com.potrt.flashcards.japanese;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.potrt.flashcards.japanese.JapaneseKanji.JapaneseKanjiWithReading;

/**
 * A {@link JapaneseWord} represents a Japanese word with it's kanji, furigana, and definition.
 * This is an abstract class and implementations will usually be created with {@link JapaneseWordBuilder}.
 */
public class JapaneseWord {
    /**
     * @implNote Used for default kanji getter.
     */
    protected String kanji;

    /**
     * @implNote Used for default furigana getter.
     */
    protected String furigana;

    /**
     * @implNote Used for default defintion getter.
     */
    protected String definition;

    /**
     * The list of kana.
     * @implNote Each object in this list is either a {@link JapaneseKanjiWithReading} or a {@link String}.
     */
    protected List<Object> kanaList = new ArrayList<>();

    /**
     * A list of all kanji.
     * @implNote This is just used as a shortcut for certain methods and it does not need to be in order.
     */
    protected List<JapaneseKanjiWithReading> kanjiList = new ArrayList<>();

    /**
     * Creates a {@link JapaneseWord} from a built Japanese word and its definition.
     * @param builder The {@link JapaneseWordBuilder} that has all the components of the word.
     * @param definition The meaning of the string.
     */
    public JapaneseWord(JapaneseWordBuilder builder, String definition) {
        this.kanji = builder.getKanji();
        this.furigana = builder.getFurigana();
        this.definition = definition;
        this.kanaList = builder.kanaList;
        this.kanjiList = builder.kanjiList;
    } 

    /**
     * Gets the kanji of the word.
     * @return The kanji.
     */
    public String getKanji() {
        return kanji;
    }

    /**
     * Gets the furigana of the word.
     * @return The furigana.
     */
    public String getFurigana() {
        return furigana;
    }

    /**
     * Gets the defintion of the word.
     * @return The defintion.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Returns an {@link JapaneseWord.Representation} of the {@link JapaneseWord} .
     * @return The associated {@link JapaneseWord.Representation}.
     */
    public Representation getRepresentation() {
        return new Representation(kanji, furigana, definition);
    }

    /**
     * Adds a new successful or failed attempt for each kanji.
     * @param succeeded If the attempt was successful.
     */
    public void attempt(boolean succeeded) {
        for (JapaneseKanjiWithReading k : kanjiList) {
            k.attempt(succeeded);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKanji(), getFurigana(), getDefinition());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JapaneseWord)) {
            return false;
        }   
        JapaneseWord other = (JapaneseWord) obj;

        return getKanji().equals(other.getKanji()) 
            && getFurigana().equals(other.getFurigana()) 
            && getDefinition().equals(other.getDefinition());
    }

    /**
     * Replaces the original word values with new word values.  Also unattaches the word with all prior kanji readings.
     * @param word The {@link JapaneseWord} values to replace with.
     * @apiNote This should be overriden by any sub-children to copy any additional relevant values.  The child can and should make a super call.
     */
    protected void replace(JapaneseWord word) {
        if (!getKanji().equals(word.getKanji())) {
            throw new IllegalArgumentException(
                String.format("Attempted to replace word with kanji '%s' with a word with kanji '%s'.  These must match.", 
                    getKanji(), 
                    word.getKanji()));
        }

        detachFromKanji();
        furigana = word.getFurigana();
        definition = word.getDefinition();
        kanaList = word.kanaList;
        kanjiList = word.kanjiList;
    }

    /**
     * Attaches the {@link JapaneseWord} to all of the {@link JapaneseKanji} it is made of.
     */
    void attachToKanji() {
        for (JapaneseKanjiWithReading reading : kanjiList) {
            reading.attachWord(this);
        }
    }

    /**
     * Detaches the {@link JapaneseWord} from all of the {@link JapaneseKanji} it is made of.
     */
    void detachFromKanji() {
        for (JapaneseKanjiWithReading reading : kanjiList) {
            reading.detachWord(this);
        }
    }

    /**
     * A representation of a {@link JapaneseWord} that holds the its kanji, furigana, and definition for the form it is taking like conjugation.
     */
    public class Representation {
        private String kanji;
        private String furigana;
        private String definition;

        /**
         * Creates a new {@link JapaneseWord.Representation} with the kanji, the furigana, and the definition.
         * @param kanji The kanji.
         * @param furigana The furigana.
         * @param definition The definition.
         */
        public Representation(String kanji, String furigana, String definition) {
            this.kanji = kanji;
            this.furigana = furigana;
            this.definition = definition;
        }

        /**
         * Gets the kanji of the representation.
         * @return The kanji.
         */
        public String getKanji() {
            return kanji;
        }

        /**
         * Gets the furigana of the representation.
         * @return The furigana.
         */
        public String getFurigana() {
            return furigana;
        }

        /**
         * Gets the defintion of the representation.
         * @return The defintion.
         */
        public String getDefinition() {
            return definition;
        }

        /**
         * Returns the {@link JapaneseWord} that this representation is based on.
         * @return The containing {@link JapaneseWord}.
         */
        public JapaneseWord getWord() {
            return JapaneseWord.this;
        }
    }
}
