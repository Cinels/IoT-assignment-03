package it.unibo.esiot.assignment03.dashboard.controller.api;

import java.util.List;

import it.unibo.esiot.assignment03.dashboard.model.impl.Pair;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Controller for the communication with the control unit.
 */
public interface CommunicationController {

    /**
     * Returns if the window mode has to switch.
     * @return true if the window mode has to switch, false otherwise.
     */
    boolean isModeToSwitch();

    /**
     * Tells that the mode has switched.
     */
    void modeSwitched();

    /**
     * Returns if the alarm is to manage.
     * @return true if the alarm has to be managed, false otherwise.
     */
    boolean isAlarmToManage();

    /**
     * Tells that the alarm has to be managed.
     */
    void alarmManaged();

    /**
     * Returns if the opening of the window is to set from dashboard.
     * @return true if the opening is to set from the dashboard, false otherwise.
     */
    boolean isOpeningSetFromDashboard();

    /**
     * Tells that the opening from dashboard has been set.
     */
    void openingSetFromDashboard();

    /**
     * Returns the opening value for the window set from the dasboard.
     * @return the window opening set from the dashboard.
     */
    int getOpeningFromDashboard();

    /**
     * Sets the current temperature. 
     * @param temperature the current temperature.
     */
    void setCurrentTemperature(float temperature);

    /**
     * Sets the maximum temperature.
     * @param temperature the max temperature.
     */
    void setMaxTemperature(float temperature);

    /**
     * Sets the average temperature.
     * @param temperature the average temperature.
     */
    void setAvgTemperature(float temperature);

    /**
     * Sets the minimum temperature.
     * @param temperature the minimum temperature.
     */
    void setMinTemperature(float temperature);

    /**
     * Sets the temperature history list.
     * @param history the history list.
     */
    void setTemperatureHistory(List<Pair<Long, Float>> history);

    /**
     * Sets the temperature state.
     * @param state the temperature state.
     */
    void setTemperatureState(TemperatureState state);

    /**
     * Sets the window mode.
     * @param mode the window mode.
     */
    void setWindowMode(WindowMode mode);

    /**
     * Sets the current window opening. 
     * @param opening the window opening.
     */
    void setWindowOpening(int opening);
}
