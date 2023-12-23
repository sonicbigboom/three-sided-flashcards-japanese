package com.potrt.flashcards.japanese.word.verb;

import com.potrt.flashcards.japanese.word.JapaneseWordBuilder;
import com.potrt.flashcards.japanese.word.verb.JapaneseVerb.JapaneseVerbType;

public class JapaneseVerbBuilder extends JapaneseWordBuilder {
    /**
     * Generates the {@link JapaneseVerb} with a definition, from the verb type.
     * @param definition The meaning of the string.
     * @param verbType The verb's type: godan, ichidan, or irregular.
     * @return The {@link JapaneseVerb}.
     */
    public JapaneseVerb getJapaneseVerb(String definition, JapaneseVerbType verbType) {
        return new JapaneseVerb(this, definition, verbType);
    }
}