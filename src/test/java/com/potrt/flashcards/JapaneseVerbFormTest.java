package com.potrt.flashcards;

import com.potrt.flashcards.JapaneseVerbForm.Form;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JapaneseVerbFormTest implements TestingConstants {
    
    /**
     * Checks that a simple polite form works.
     */
    @Test
    public void politeFormTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm.isPlain()).isTrue();
        assertThat(dictionaryForm.isPositive()).isTrue();
        assertThat(dictionaryForm.getForm()).isEqualTo(JapaneseVerbForm.Form.PRESENT_INDICATIVE);
    }

    /**
     * Checks that two different objects with the same values are still equal.
     */
    @Test
    public void equalTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isEqualTo(verbFormDictionary);
    }

    /**
     * Checks that different values are not equal.
     */
    @Test
    public void notEqualTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PAST_INDICATIVE);
        assertThat(pastForm).isNotEqualTo(verbFormDictionary);
    }

    /**
     * Checks that the same object is equal.
     */
    @Test
    public void sameObjectEqualityTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isEqualTo(dictionaryForm);
    }
    
    /**
     * Checks that a non-{@link JapaneseVerbForm} is not equal.
     */
    @Test
    public void nonJapaneseVerbFormEqualityTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PAST_INDICATIVE);
        assertThat(pastForm).isNotEqualTo(new Flashcard("a", "b"));
    }

    /**
     * Checks that two different objects with the same values are equal.
     */
    @Test 
    public void sameHashesTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        int expectedHash = verbFormDictionary.hashCode();
        assertThat(dictionaryForm.hashCode()).isEqualTo(expectedHash);
    }

    /**
     * Checks that slightly different verb forms do not have the same hash.
     */
    @Test
    public void uniqueHashesTest() {
        int unexpectedHash = verbFormDictionary.hashCode();
        assertThat(new JapaneseVerbForm(false, true, Form.PRESENT_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(true, false, Form.PRESENT_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(false, false, Form.PRESENT_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(true, true, Form.PAST_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
    }

    /**
     * Checks that two objects with the same values are equal using {@code compareTo}.
     */
    @Test
    public void equalCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isEqualByComparingTo(verbFormDictionary);
    }

    /**
     * Checks that two objects with differnt forms are not equal.
     */
    @Test
    public void notEqualFormCompareToTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PAST_INDICATIVE);
        assertThat(pastForm).isNotEqualByComparingTo(verbFormDictionary);
    }

    /**
     * Checks that plain verb forms are greater than polite verbs.
     */
    @Test 
    public void politenessOrderingCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        JapaneseVerbForm politePresentForm = new JapaneseVerbForm(false, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isGreaterThan(politePresentForm); 
        assertThat(politePresentForm).isLessThan(dictionaryForm);
    }

    /**
     * Checks that positive verb forms are greater than negative verb form.
     */
    @Test
    public void affirmativeOrderingCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        JapaneseVerbForm negativeDictionaryForm = new JapaneseVerbForm(true, false, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isGreaterThan(negativeDictionaryForm); 
        assertThat(negativeDictionaryForm).isLessThan(dictionaryForm);
    }

    /**
     * Checks that plain/polite is greater than positive/negative. 
     */
    @Test
    public void comboOrderingCompareToTest() {
        JapaneseVerbForm negativeDictionaryForm = new JapaneseVerbForm(true, false, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        JapaneseVerbForm politePresentForm = new JapaneseVerbForm(false, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(negativeDictionaryForm).isGreaterThan(politePresentForm);
        assertThat(politePresentForm).isLessThan(negativeDictionaryForm); 
    }
}
