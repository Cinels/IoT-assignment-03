package it.unibo.esiot.assignment03.controlunit.controller.api;

/**
 * Controller for temperature communications.
 */
public interface TemperatureController {

    /**
     * Updates the current temperature.
     * @param temperature the temperature of the room.
     */
    void setCurrentTemperature(float temperature);

    /**
     * Returns the frequency to sample the temperature.
     * @return the sampling frequency.
     */
    float getSampleFrequency();

    /**
     * Adds a value to the history.
     * @param value the value to add to the history.
     */
    void addValueToHistory(float value);
}
