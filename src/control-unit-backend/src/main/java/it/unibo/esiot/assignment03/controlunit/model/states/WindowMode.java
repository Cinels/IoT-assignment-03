package it.unibo.esiot.assignment03.controlunit.model.states;

/**
 * Enumeration of the possible modalities of the window.
 */
public enum WindowMode {
    
    /** The window is in manual mode. */
    MANUAL("Manual"),
    
    /** The window is in automatic mode. */
    AUTOMATIC("Automatic");

    private final String state;

    private WindowMode(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
