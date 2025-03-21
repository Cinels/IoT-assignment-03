package it.unibo.esiot.assignment03.controlunit.model.impl;

import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;
import it.unibo.esiot.assignment03.controlunit.model.states.TemperatureState;
import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * Implementation of kernel interface.
 */
public final class KernelImpl implements Kernel {
    private static final float T1 = 25;
    private static final float T2 = 30;
    private static final long ALARM_TIME = 10_000;
    private static final float F1 = 2.0f;
    private static final float F2 = 5.0f;
    private static final int MAX_PERCENTAGE = 100;
    private static final int MIN_PERCENTAGE = 1;

    private TemperatureState temperatureState;
    private WindowMode windowMode;
    private float currentTemperature;
    private int currentWindowOpening;
    private long ts;

    /**
     * Creates a kernel object.
     */
    public KernelImpl() {
        this.temperatureState = TemperatureState.NORMAL;
        this.windowMode = WindowMode.AUTOMATIC;
        this.currentTemperature = 0;
        this.currentWindowOpening = 0;
        this.ts = System.currentTimeMillis();
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
    public void manageTemperature() {
        if (this.temperatureState == TemperatureState.ALARM) {
            this.temperatureState = TemperatureState.NORMAL;
        }
    }

    @Override
    public void setWindowMode(final WindowMode mode) {
        this.windowMode = mode;
    }

    @Override
    public float getCurrentTemperature() {
        return this.currentTemperature;
    }

    @Override
    public int getCurrentWindowOpening() {
        return this.currentWindowOpening;
    }

    @Override
    public void setCurrentTemperature(final float temperature) {
        this.currentTemperature = temperature;
        if (temperature > T1 && temperature <= T2) {
            this.temperatureState = TemperatureState.HOT;
        } else if (temperature > T2 && System.currentTimeMillis() - this.ts < ALARM_TIME) {
            this.temperatureState = TemperatureState.TOO_HOT;
            this.ts = System.currentTimeMillis();
        } else if (temperature > T2 && System.currentTimeMillis() - this.ts >= ALARM_TIME) {
            this.temperatureState = TemperatureState.ALARM;
        } else {
            this.temperatureState = TemperatureState.NORMAL;
        }
    }

    @Override
    public void setCurrentWindowOpening(final int opening) {
        this.currentWindowOpening = opening;
    }

    @Override
    public int getNextOpening() {
        return switch (this.temperatureState) {
            case NORMAL -> 0;
            case HOT -> calculateWindowOpening();
            case TOO_HOT -> MAX_PERCENTAGE;
            case ALARM -> MAX_PERCENTAGE;
        };
    }

    @Override
    public float getSampleFrequency() {
        if (this.temperatureState == TemperatureState.NORMAL) {
            return F1;
        } else {
            return F2;
        }
    }

    private int calculateWindowOpening() {
        return (int) ((this.currentTemperature - T1) * (MAX_PERCENTAGE - MIN_PERCENTAGE) / (T2 - T1) + MIN_PERCENTAGE);
    }
}
