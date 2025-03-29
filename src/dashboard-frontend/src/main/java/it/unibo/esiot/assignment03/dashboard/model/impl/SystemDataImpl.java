package it.unibo.esiot.assignment03.dashboard.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.model.api.SystemData;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Implementation of {@link SystemData}.
 */
public final class SystemDataImpl implements SystemData {
    private static final float DEFAULT_TEMPERATURE = 0.0f;

    private float currentTemperature;
    private float maxTemperature;
    private float avgTemperature;
    private float minTemperature;
    private List<Pair<Long, Float>> history;
    private TemperatureState temperatureState;
    private WindowMode windowMode;
    private int windowOpening;
    private int windowOpeningFromDashboard;
    private boolean switchMode;
    private boolean manageAlarm;

    /**
     * Constructor of StstemData.
     */
    public SystemDataImpl() {
        this.currentTemperature = DEFAULT_TEMPERATURE;
        this.maxTemperature = DEFAULT_TEMPERATURE;
        this.avgTemperature = DEFAULT_TEMPERATURE;
        this.minTemperature = DEFAULT_TEMPERATURE;
        this.history = new ArrayList<>();
        this.temperatureState = TemperatureState.NORMAL;
        this.windowMode = WindowMode.AUTOMATIC;
        this.windowOpening = 0;
        this.windowOpeningFromDashboard = 0;
        this.switchMode = false;
        this.manageAlarm = false;
    }

    @Override
    public boolean isModeToSwitch() {
        return this.switchMode;
    }

    @Override
    public void doSwitchMode() {
        this.switchMode = true;
    }

    @Override
    public void modeSwitched() {
        this.switchMode = false;
    }

    @Override
    public boolean isAlarmToManage() {
        return this.manageAlarm;
    }

    @Override
    public void doManageAlarm() {
        this.manageAlarm = true;
    }

    @Override
    public void alarmManaged() {
        this.manageAlarm = false;
    }

    @Override
    public int getOpeningFromDashboard() {
        return this.windowOpeningFromDashboard;
    }

    @Override
    public void setOpeningFromDashboard(final int opening) {
        this.windowOpeningFromDashboard = opening;
    }

    @Override
    public float getCurrentTemperature() {
        return this.currentTemperature;
    }

    @Override
    public float getMaxTemperature() {
        return this.maxTemperature;
    }

    @Override
    public float getAvgTemperature() {
        return this.avgTemperature;
    }

    @Override
    public float getMinTemperature() {
        return this.minTemperature;
    }

    @Override
    public List<Pair<Long, Float>> getTemperatureHistory() {
        return List.copyOf(this.history);
    }

    @Override
    public TemperatureState getTemperatureState() {
        return this.temperatureState;
    }

    @Override
    public WindowMode getWindowMode() {
        return this.windowMode;
    }

    @Override
    public int getWindowOpening() {
        return this.windowOpening;
    }

    @Override
    public void setCurrentTemperature(final float temperature) {
        this.currentTemperature = temperature;
    }

    @Override
    public void setMaxTemperature(final float temperature) {
        this.maxTemperature = temperature;
    }

    @Override
    public void setAvgTemperature(final float temperature) {
        this.avgTemperature = temperature;
    }

    @Override
    public void setMinTemperature(final float temperature) {
        this.minTemperature = temperature;
    }

    @Override
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The history list has to be updated each time is sent"
    )
    public void setTemperatureHistory(final List<Pair<Long, Float>> history) {
        this.history = history;
    }

    @Override
    public void setTemperatureState(final TemperatureState state) {
        this.temperatureState = state;
    }

    @Override
    public void setWindowMode(final WindowMode mode) {
        this.windowMode = mode;
    }

    @Override
    public void setWindowOpening(final int opening) {
        this.windowOpening = opening;
    }
}
