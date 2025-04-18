package it.unibo.esiot.assignment03.controlunit.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.esiot.assignment03.controlunit.model.api.HistoryTracker;

/**
 * Class that keeps track of a history of values and provides.
 */
public final class HistoryTrackerImpl implements HistoryTracker {

    private static final int HISTORY_SIZE = 100;
    private final List<Pair<Long, Float>> history;
    private float sum;
    private float min;
    private float max;

    /**
     * Creates a new instance of HistoryTrackerImpl.
     */
    public HistoryTrackerImpl() {
        this.history = new ArrayList<>();
    }

    @Override
    public List<Pair<Long, Float>> getHistory() {
        return List.copyOf(this.history);
    }

    @Override
    public void addValue(final float value) {
        if (this.history.isEmpty() || value < this.min) {
            this.min = value;
        }
        if (this.history.isEmpty() || value > this.max) {
            this.max = value;
        }
        this.history.add(new Pair<>(System.currentTimeMillis(), value));
        this.sum += value;
        if (this.history.size() > HISTORY_SIZE) {
            this.sum -= this.history.remove(0).getY();
        }
    }

    @Override
    public void clearHistory() {
        this.history.clear();
        this.sum = 0;
        this.min = 0;
        this.max = 0;
    }

    @Override
    public float getAverage() {
        return this.history.isEmpty() ? 0 : this.sum / this.history.size();
    }

    @Override
    public float getMin() {
        return this.min;
    }

    @Override
    public float getMax() {
        return this.max;
    }
}
