package it.unibo.esiot.assignment03.dashboard.model.api;

import java.util.List;

import it.unibo.esiot.assignment03.dashboard.model.impl.Pair;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Interface to store all the system data.
 */
public interface SystemData {

    /**
     * Returns if the window mode has to switch.
     * @return true if the window mode has to switch, false otherwise.
     */
    boolean isModeToSwitch();

    /**
     * Tells that the mode has to switch.
     */
    void doSwitchMode();

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
    void doManageAlarm();

    /**
     * Tells that the alarm has been managed.
     */
    void alarmManaged();

    /**
     * Returns the opening value for the window set from the dasboard.
     * @return the window opening set from the dashboard.
     */
    int getOpeningFromDashboard();

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
