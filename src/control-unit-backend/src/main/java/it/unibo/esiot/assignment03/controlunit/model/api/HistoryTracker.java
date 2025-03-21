package it.unibo.esiot.assignment03.controlunit.model.api;

import java.util.List;

/**
 * Interface for a class that keeps track of a history of values and provides.
 */
public interface HistoryTracker {

    /**
     * Returns the last N values added to the history.
     * @return a list of the last N values added to the history.
     */
    List<Float> getHistory();

    /**
     * Adds a value to the history.
     * @param value the value to add to the history.
     */
    void addValue(float value);

    /**
     * Clears the history.
     */
    void clearHistory();

    /**
     * Returns the average of the values in the history.
     * @return the average of the values in the history.
     */
    float getAverage();

    /**
     * Returns the minimum value in the history.
     * @return the minimum value in the history.
     */
    float getMin();

    /**
     * Returns the maximum value in the history.
     * @return the maximum value in the history.
     */
    float getMax();
}
