package com.potrt.flashcards.japanese.string.verb;

import org.apache.commons.text.WordUtils;

/**
 * A {@link JapaneseVerbForm} represents a form/conjugation that a Japanese verb can be.
 */
public final class JapaneseVerbForm implements Comparable<JapaneseVerbForm> {
    private boolean isPlain;
    private boolean isPositive;
    private Form form;

    /**
     * Creates a new {@link JapaneseVerbForm}.
     * @param isPlain Is the verb plain or polite.
     * @param isPositive Is the verb positive or negative.
     * @param form The form.
     */
    public JapaneseVerbForm(boolean isPlain, boolean isPositive, String form) {
        this.isPlain = isPlain;
        this.isPositive = isPositive;
        this.form = Form.from(form);
    }

    /**
     * Returns if the verb form is in the plain form.
     * @return True if the verb form is plain.  False if the verb form is polite.
     */
    public boolean isPlain() {
        return isPlain;
    }

    /**
     * Returns if the verb form is in the positive form.
     * @return True if the verb form is positive.  False if the verb form is negative.
     */
    public boolean isPositive() {
        return isPositive;
    }

    /**
     * Returns the verb form (without politeness and postive/negative) of the verb form.
     * @return The verb form as a {@link JapaneseVerbForm.Form}.
     */
    public String getForm() {
        return form.display();
    }

    /**
     * A {@link JapaneseVerbForm.Form} represents the specific form of a Japanese verb.
     */
    private enum Form {
        PRESENT_INDICATIVE,
        PAST_INDICATIVE;

        /**
         * Converts the enum to a display {@link String}.
         * @return The text display.
         */
        public String display() {
            return WordUtils.capitalizeFully(this.toString().replace("_", " "));
        }

        /**
         * Converts a display {@link String} to an {@link Form}.
         * @param text The display text.
         * @return The enum.
         * @throws IllegalArgumentException If the text cannot be converted to {@link Form} enum.
         */
        public static Form from(String text) throws IllegalArgumentException {
            String enumText = text.replace(" ", "_").toUpperCase();
            return Form.valueOf(enumText);
        }
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) { return true; }
        if (!(obj instanceof JapaneseVerbForm)) { return false; }

        JapaneseVerbForm other = (JapaneseVerbForm) obj;
        return isPlain == other.isPlain && isPositive == other.isPositive && form.equals(other.form);
    }

    @Override
    public int hashCode(){
        int hash = form.ordinal();
        hash += isPlain ? 2 : 0;
        hash += isPositive ? 1 : 0;
        return hash;
    }

    @Override
    public int compareTo(JapaneseVerbForm other) {
        if (isPlain == other.isPlain && isPositive == other.isPositive && form.equals(other.form)) {
            return 0;
        } else if (form.compareTo(other.form) != 0) {
            return form.compareTo(other.form);
        } else if (isPlain != other.isPlain) {
            return isPlain ? 1 : -1;
        } else {
            return isPositive ? 1 : -1;
        }
    }

    @Override
    public String toString() {
        String out = "";
        if (!isPlain) { out += "Polite "; }
        if (!isPositive) { out += "Negative "; }
        return out + form.display();
    }
}   