package it.unibo.esiot.assignment03.controlunit.model.api;

import it.unibo.esiot.assignment03.controlunit.model.states.TemperatureState;
import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * The Kernel interface represents the core of the Control Unit.
 * It is responsible for establishing the frequency of the data acquisition
 * and the managing of the opening of the window.
 */
public interface Kernel {

    /**
     * Returns the current temperature state.
     * @return the temperature state of the system.
     */
    TemperatureState getTemperatureState();

    /**
     * Returns the current modality of the window {AUTOMATIC, MANUAL}.
     * @return the current modality of the window.
     */
    WindowMode getWindowMode();

    /**
     * Returns the current temperature in the room.
     * @return the current temperature.
     */
    float getCurrentTemperature();

    /**
     * Returns the current opening percentage of the window.
     * @return the opening percentage of the window.
     */
    int getCurrentWindowOpening();

    /**
     * Manages the system when it is in ALLARM mode.
     */
    void manageTemperature();

    /**
     * Updates the window modality.
     * @param mode the modality of the window.
     */
    void setWindowMode(WindowMode mode);

    /**
     * Updates the current temperature.
     * @param temperature the temperature of the room.
     */
    void setCurrentTemperature(float temperature);

    /**
     * Updates the current opening percentage of the window.
     * @param opening the opening of the window.
     */
    void setCurrentWindowOpening(int opening);

    /**
     * Returns the percentage to open the window.
     * @return the percentage to open the window.
     */
    int getNextOpening();

    /**
     * Returns the frequency to sample the temperature.
     * @return the sampling frequency.
     */
    float getSampleFrequency();

    /**
     * Tells that the mode has to switch.
     */
    void doSwitchMode();

    /**
     * Tells that the mode has switched.
     */
    void modeSwitched();

    /**
     * Returns if the window mode has to switch.
     * @return if the window mode has to switch.
     */
    boolean isModeToSwitch();
}
