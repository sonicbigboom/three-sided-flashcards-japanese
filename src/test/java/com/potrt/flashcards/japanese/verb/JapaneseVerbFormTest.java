package com.potrt.flashcards.japanese.verb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import org.junit.Test;

import com.potrt.flashcards.Flashcard;
import com.potrt.flashcards.TestingConstants;

public class JapaneseVerbFormTest implements TestingConstants {
    
    /**
     * Checks that a simple polite form works.
     */
    @Test
    public void politeFormTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, presentIdicative);
        assertThat(dictionaryForm.isPlain()).isTrue();
        assertThat(dictionaryForm.isPositive()).isTrue();
        assertThat(dictionaryForm.getForm()).isEqualTo(presentIdicative);
    }

    /**
     * Ensures that the JapaneseVerbForm throws an error when called with a non-existant form.
     */
    @Test
    public void failNewTest() {
        assertThatThrownBy(() -> new JapaneseVerbForm(true, true, "Invalid Form")).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks that two different objects with the same values are still equal.
     */
    @Test
    public void equalTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, presentIdicative);
        assertThat(dictionaryForm).isEqualTo(verbFormDictionary);
    }

    /**
     * Checks that different values are not equal.
     */
    @Test
    public void notEqualTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, pastIdicative);
        assertThat(pastForm).isNotEqualTo(verbFormDictionary);
    }

    /**
     * Checks that the same object is equal.
     */
    @Test
    public void sameObjectEqualityTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, presentIdicative);
        assertThat(dictionaryForm).isEqualTo(dictionaryForm);
    }
    
    /**
     * Checks that a non-{@link JapaneseVerbForm} is not equal.
     */
    @Test
    public void nonJapaneseVerbFormEqualityTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, pastIdicative);
        assertThat(pastForm).isNotEqualTo(new Flashcard("a", "b"));
    }

    /**
     * Checks that two different objects with the same values are equal.
     */
    @Test 
    public void sameHashesTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, presentIdicative);
        int expectedHash = verbFormDictionary.hashCode();
        assertThat(dictionaryForm.hashCode()).isEqualTo(expectedHash);
    }

    /**
     * Checks that slightly different verb forms do not have the same hash.
     */
    @Test
    public void uniqueHashesTest() {
        int unexpectedHash = verbFormDictionary.hashCode();
        assertThat(new JapaneseVerbForm(false, true, presentIdicative).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(true, false, presentIdicative).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(false, false, pastIdicative).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(true, true, pastIdicative).hashCode()).isNotEqualTo(unexpectedHash);
    }

    /**
     * Checks that two objects with the same values are equal using {@code compareTo}.
     */
    @Test
    public void equalCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, presentIdicative);
        assertThat(dictionaryForm).isEqualByComparingTo(verbFormDictionary);
    }

    /**
     * Checks that two objects with differnt forms are not equal.
     */
    @Test
    public void notEqualFormCompareToTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, pastIdicative);
        assertThat(pastForm).isNotEqualByComparingTo(verbFormDictionary);
    }

    /**
     * Checks that plain verb forms are greater than polite verbs.
     */
    @Test 
    public void politenessOrderingCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, presentIdicative);
        JapaneseVerbForm politePresentForm = new JapaneseVerbForm(false, true, presentIdicative);
        assertThat(dictionaryForm).isGreaterThan(politePresentForm); 
        assertThat(politePresentForm).isLessThan(dictionaryForm);
    }

    /**
     * Checks that positive verb forms are greater than negative verb form.
     */
    @Test
    public void affirmativeOrderingCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, presentIdicative);
        JapaneseVerbForm negativeDictionaryForm = new JapaneseVerbForm(true, false, presentIdicative);
        assertThat(dictionaryForm).isGreaterThan(negativeDictionaryForm); 
        assertThat(negativeDictionaryForm).isLessThan(dictionaryForm);
    }

    /**
     * Checks that plain/polite is greater than positive/negative. 
     */
    @Test
    public void comboOrderingCompareToTest() {
        JapaneseVerbForm negativeDictionaryForm = new JapaneseVerbForm(true, false, presentIdicative);
        JapaneseVerbForm politePresentForm = new JapaneseVerbForm(false, true, presentIdicative);
        assertThat(negativeDictionaryForm).isGreaterThan(politePresentForm);
        assertThat(politePresentForm).isLessThan(negativeDictionaryForm); 
    }

    /**
     * Checks that the toString creates a proper display.
     */
    @Test
    public void toStringTest() {
        assertThat(new JapaneseVerbForm(true, true, presentIdicative)).hasToString(definitionPresentIndicative);
        assertThat(new JapaneseVerbForm(true, false, presentIdicative)).hasToString(definitionPresentIndicativeNegative);
        assertThat(new JapaneseVerbForm(false, true, presentIdicative)).hasToString(definitionPresentIndicativePolite);
        assertThat(new JapaneseVerbForm(false, false, presentIdicative)).hasToString(definitionPresentIndicativePoliteNegative);
        assertThat(new JapaneseVerbForm(true, true, pastIdicative)).hasToString(definitionPastIndicative);
    }
}
