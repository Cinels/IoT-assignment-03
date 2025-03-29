package it.unibo.esiot.assignment03.controlunit.controller.impl;

import it.unibo.esiot.assignment03.controlunit.controller.api.TemperatureController;
import it.unibo.esiot.assignment03.controlunit.model.api.HistoryTracker;
import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;

public class TemperatureControllerImpl implements TemperatureController {

    private final Kernel kernel;
    private final HistoryTracker history;
    
    public TemperatureControllerImpl(final Kernel kernel, final HistoryTracker history) {
        this.kernel = kernel;
        this.history = history;
    }
    
    @Override
    public void setCurrentTemperature(float temperature) {
        this.kernel.setCurrentTemperature(temperature);
    }

    @Override
    public float getSampleFrequency() {
        return this.kernel.getSampleFrequency();
    }

    @Override
    public void addValueToHistory(float value) {
        this.history.addValue(value);
    }
    
}
