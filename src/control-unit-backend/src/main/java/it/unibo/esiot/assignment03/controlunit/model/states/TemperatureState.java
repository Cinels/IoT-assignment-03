package it.unibo.esiot.assignment03.controlunit.model.states;

/**
 * Enumeration of the possible states of the system due to the temperature.
 */
public enum TemperatureState {
    
    /** The temperature is normal. */
    NORMAL("Normal"),
    
    /** The temperature is hot. */
    HOT("Hot"),
    
    /** The temperature is too hot. */
    TOO_HOT("Too Hot"),
    
    /** The temperature was too hot for too long. */
    ALARM("Alarm");

    private final String state;

    private TemperatureState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
