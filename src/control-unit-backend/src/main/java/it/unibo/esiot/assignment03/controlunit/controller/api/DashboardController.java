package it.unibo.esiot.assignment03.controlunit.controller.api;

import java.util.List;

import it.unibo.esiot.assignment03.controlunit.model.impl.Pair;
import it.unibo.esiot.assignment03.controlunit.model.states.TemperatureState;
import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * Controller for dashboard communications.
 */
public interface DashboardController {

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
     * Sets the window opening if the window is in manual mode.
     * @param opening the opening of the window.
     */
    void setManualWindowOpening(int opening);

    /**
     * Tells that the mode has to switch.
     */
    void doSwitchMode();

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

    /**
     * Returns the last N values added to the history.
     * @return a list of the last N values added to the history with the corrispondent time in milliseconds.
     */
    List<Pair<Long, Float>> getHistory();
}
