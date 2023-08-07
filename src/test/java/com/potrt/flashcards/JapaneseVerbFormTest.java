package com.potrt.flashcards;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.potrt.flashcards.JapaneseVerbForm.Form;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JapaneseVerbFormTest implements TestingConstants {
    
    @Test
    public void politeFormTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm.isPlain()).isTrue();
        assertThat(dictionaryForm.isPositive()).isTrue();
        assertThat(dictionaryForm.getForm()).isEqualTo(JapaneseVerbForm.Form.PRESENT_INDICATIVE);
    }

    @Test
    public void equalTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isEqualTo(verbFormDictionary);
    }

    @Test
    public void notEqualTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PAST_INDICATIVE);
        assertThat(pastForm).isNotEqualTo(verbFormDictionary);
    }

    @Test
    public void sameObjectEqualityTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isEqualTo(dictionaryForm);
    }
    
    @Test
    public void nonJapaneseVerbFormEqualityTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PAST_INDICATIVE);
        assertThat(pastForm).isNotEqualTo(new Flashcard("a", "b"));
    }

    @Test 
    public void sameHashesTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        int expectedHash = verbFormDictionary.hashCode();
        assertThat(dictionaryForm.hashCode()).isEqualTo(expectedHash);
    }

    @Test
    public void uniqueHashesTest() {
        int unexpectedHash = verbFormDictionary.hashCode();
        assertThat(new JapaneseVerbForm(false, true, Form.PRESENT_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(true, false, Form.PRESENT_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(false, false, Form.PRESENT_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
        assertThat(new JapaneseVerbForm(true, true, Form.PAST_INDICATIVE).hashCode()).isNotEqualTo(unexpectedHash);
    }

    @Test
    public void equalCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isEqualByComparingTo(verbFormDictionary);
    }

    @Test
    public void notEqualFormCompareToTest() {
        JapaneseVerbForm pastForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PAST_INDICATIVE);
        assertThat(pastForm).isNotEqualByComparingTo(verbFormDictionary);
    }

    @Test 
    public void politenessOrderingCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        JapaneseVerbForm politePresentForm = new JapaneseVerbForm(false, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isGreaterThan(politePresentForm); 
        assertThat(politePresentForm).isLessThan(dictionaryForm);
    }

    @Test
    public void affirmativeOrderingCompareToTest() {
        JapaneseVerbForm dictionaryForm = new JapaneseVerbForm(true, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        JapaneseVerbForm negativeDictionaryForm = new JapaneseVerbForm(true, false, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(dictionaryForm).isGreaterThan(negativeDictionaryForm); 
        assertThat(negativeDictionaryForm).isLessThan(dictionaryForm);
    }

    @Test
    public void comboOrderingCompareToTest() {
        JapaneseVerbForm negativeDictionaryForm = new JapaneseVerbForm(true, false, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        JapaneseVerbForm politePresentForm = new JapaneseVerbForm(false, true, JapaneseVerbForm.Form.PRESENT_INDICATIVE);
        assertThat(negativeDictionaryForm).isGreaterThan(politePresentForm);
        assertThat(politePresentForm).isLessThan(negativeDictionaryForm); 
    }
}
