package it.unibo.esiot.assignment03.controlunit.model.impl;

import java.io.Serializable;
import java.util.Objects;

/**
 * Implementation of Pair.
 * @param <X> the type of the first element.
 * @param <Y> the type of the second element.
 */
public final class Pair<X, Y> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final X x;
    private final Y y;

    /**
     * Constructor for a pair of elements.
     * @param x the first element.
     * @param y the second element.
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the first element of the pair.
     * @return the first element of the pair.
     */
    public X getX() {
        return this.x;
    }

    /**
     * Returns the second element of the pair.
     * @return the second element of the pair.
     */
    public Y getY() {
        return this.y;
    }

    /* Need to suppress rawtypes warnings due to the explicit cast from Object to Pair */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return Objects.equals(this.getX(), other.getX()) && Objects.equals(this.getY(), other.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
