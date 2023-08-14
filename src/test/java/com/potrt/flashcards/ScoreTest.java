package com.potrt.flashcards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.Test;

public class ScoreTest {
    
    /**
     * Tests a single success.
     */
    @Test
    public void oneSuccessTest() {
        Score score = new Score();
        score.attempt(true);
        assertThat(score.getAttempts()).isEqualTo(1);
        assertThat(score.getSuccesses()).isEqualTo(1);
        assertThat(score.getFailures()).isZero();
        assertThat(score.getSuccessRate()).isEqualTo(1);
    }

    /**
     * Tests a single failure.
     */
    @Test
    public void oneFailureTest() {
        Score score = new Score();
        score.attempt(false);
        assertThat(score.getAttempts()).isEqualTo(1);
        assertThat(score.getSuccesses()).isZero();
        assertThat(score.getFailures()).isEqualTo(1);
        assertThat(score.getSuccessRate()).isZero();
    }

    /**
     * Tests multiple successes and failures.
     */
    @Test
    public void multipleScoreTest() {
        Score score = new Score();
        score.attempt(true);
        score.attempt(false);
        score.attempt(true);
        score.attempt(false);
        score.attempt(true);

        assertThat(score.getAttempts()).isEqualTo(5);
        assertThat(score.getSuccesses()).isEqualTo(3);
        assertThat(score.getFailures()).isEqualTo(2);
        assertThat(score.getSuccessRate()).isEqualTo(0.6);
    }

    /**
     * Tests multiple successes and failures display percentage.
     */
    @Test
    public void displaySuccessRateTest() {
        Score score = new Score();
        score.attempt(true);
        score.attempt(false);
        score.attempt(true);

        assertThat(score.getDisplaySuccessRate()).isEqualTo("66.67%");
    }

    /**
     * Tests the creation of a score with data.
     */
    @Test
    public void scoreWithDataTest() {
        Score score = new Score(7, 10);
        assertThat(score.getAttempts()).isEqualTo(10);
        assertThat(score.getSuccesses()).isEqualTo(7);
        assertThat(score.getFailures()).isEqualTo(3);
        assertThat(score.getSuccessRate()).isEqualTo(0.7);
    }

    /**
     * Test invalid score data.
     */
    @Test
    public void scoreWithInvalidDataTest() {
        assertThatThrownBy(() -> new Score(11, 9)).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test score aggregation.
     */
    @Test
    public void scoreAggregationTest() {
        Score scoreA = new Score(3, 10);
        Score scoreB = new Score(9, 10);
        Score scoreC = new Score(6, 10);
        Score score = new Score(scoreA, scoreB, scoreC);

        assertThat(score.getAttempts()).isEqualTo(30);
        assertThat(score.getSuccesses()).isEqualTo(18);
        assertThat(score.getFailures()).isEqualTo(12);
        assertThat(score.getSuccessRate()).isEqualTo(0.6);
    }
}
