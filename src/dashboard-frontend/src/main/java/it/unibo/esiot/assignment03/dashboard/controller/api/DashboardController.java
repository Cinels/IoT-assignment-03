package it.unibo.esiot.assignment03.dashboard.controller.api;

import java.util.List;

import it.unibo.esiot.assignment03.dashboard.model.impl.Pair;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Controller for the dashboard.
 */
public interface DashboardController {

    /**
     * Tells that the mode has to switch.
     */
    void doSwitchMode();

    /**
     * Tells that the alarm has to be managed.
     */
    void doManageAlarm();

    /**
     * Sets the window opening from the dashboard.
     * @param opening the window opening.
     */
    void setOpeningFromDashboard(int opening);

    /**
     * Returns the current temperature.
     * @return the current temperature.
     */
    float getCurrentTemperature();

    /**
     * Returns the maximum temperature.
     * @return the maximum temperature.
     */
    float getMaxTemperature();

    /**
     * Returns the average temperature.
     * @return the average temperature.
     */
    float getAvgTemperature();

    /**
     * Returns the minimum temperature.
     * @return the minimum temperature.
     */
    float getMinTemperature();

    /**
     * Returns a list with the history of the temperatures.
     * @return a list of pairs time-temperature.
     */
    List<Pair<Long, Float>> getTemperatureHistory();

    /**
     * Returns the temperature state.
     * @return the temperature state.
     */
    TemperatureState getTemperatureState();

    /**
     * Returns the window mode.
     * @return the window mode.
     */
    WindowMode getWindowMode();

    /**
     * Returns the window opening.
     * @return the window opening.
     */
    int getWindowOpening();
}
