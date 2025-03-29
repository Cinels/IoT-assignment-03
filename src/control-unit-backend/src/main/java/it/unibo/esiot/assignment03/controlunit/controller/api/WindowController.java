package it.unibo.esiot.assignment03.controlunit.controller.api;

import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * Cotnroller for window communications.
 */
public interface WindowController {

    /**
     * Tells that the mode has switched.
     */
    void modeSwitched();

    /**
     * Returns if the window mode has to switch.
     * @return if the window mode has to switch.
     */
    boolean isModeToSwitch();

    /**
     * Returns the current temperature in the room.
     * @return the current temperature.
     */
    String getCurrentTemperature();

    /**
     * Returns the percentage to open the window.
     * @return the percentage to open the window.
     */
    String getNextOpening();

    /**
     * Updates the current opening percentage of the window.
     * @param opening the opening of the window.
     */
    void setCurrentWindowOpening(int opening);

    /**
     * Updates the window modality.
     * @param mode the modality of the window.
     */
    void setWindowMode(WindowMode mode);
}
