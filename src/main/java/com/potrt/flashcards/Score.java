package com.potrt.flashcards;

import java.util.List;

/**
 * A {@link Score) tracks scoring/success information about matterial.
 * @param succeeded
 */
public class Score {
    private long successes;
    private long attempts;

    /**
     * Creates a new score with no data.
     */
    public Score() {
        successes = 0;
        attempts = 0;
    }

    /**
     * Creates a new score from the total number of attempts, and the number of successes.
     * @param successes The total number of successes.
     * @param attempts The total number of attempts.
     */
    public Score(long successes, long attempts) {
        if (successes > attempts) { throw new IllegalArgumentException("There cannot be more successes than attempts."); }
        this.successes = successes;
        this.attempts = attempts;
    }

    /**
     * Creates a new score from multiple scores.  (To be used for aggregating scores.)
     * @param scores The scores.
     */
    public Score(Score... scores) {
        successes = 0;
        attempts = 0;
        for (Score score : scores) {
            successes += score.getSuccesses();
            attempts += score.getAttempts();
        }
    }

    /**
     * Creates a new score from multiple scores.  (To be used for aggregating scores.)
     * @param scores The scores.
     */
    public Score(List<Score> scores) {
        this(scores.toArray(new Score[scores.size()]));
    }

    /**
     * Adds a new successful or failed attempt.
     * @param succeeded If the attempt was successful.
     */
    public void attempt(boolean succeeded) {
        if (succeeded) { successes++; }
        attempts++;
    }

    /**
     * Gets the number of successes.
     * @return The number of successes.
     */
    public long getSuccesses() {
        return successes;
    }

    /**
     * Gets the number of failures.
     * @return
     */
    public long getFailures() {
        return attempts - successes;
    }

    /**
     * Gets the total number of attempts.
     * @return The total number of attempts.
     */
    public long getAttempts() {
        return attempts;
    }

    /**
     * Gets the success rate.
     * @return The success rate from ranging from 0.00 to 1.00.
     */
    public double getSuccessRate() {
        return (double) successes / (double) attempts;
    }

    /**
     * Gets the success rate display.
     * @return The percentage as 'XX.XX%'.
     */
    public String getDisplaySuccessRate() {
        return String.format("%.2f", getSuccessRate() * 100) + "%";
    }
}
