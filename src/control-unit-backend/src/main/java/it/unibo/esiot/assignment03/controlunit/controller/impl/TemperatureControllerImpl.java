package it.unibo.esiot.assignment03.controlunit.controller.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.controlunit.controller.api.TemperatureController;
import it.unibo.esiot.assignment03.controlunit.model.api.HistoryTracker;
import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;

/**
 * Implementation of {@link TemperatureController}.
 */
public final class TemperatureControllerImpl implements TemperatureController {

    private final Kernel kernel;
    private final HistoryTracker history;

    /**
     * Creates a controller for temperature communications.
     * @param kernel  the kernel instance to manage the system's state and operations.
     * @param history the history tracker to store temperature values.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the temperature controller"
            + "need to store data and send stored data."
    )
    public TemperatureControllerImpl(final Kernel kernel, final HistoryTracker history) {
        this.kernel = kernel;
        this.history = history;
    }

    @Override
    public void setCurrentTemperature(final float temperature) {
        this.kernel.setCurrentTemperature(temperature);
    }

    @Override
    public float getSampleFrequency() {
        return this.kernel.getSampleFrequency();
    }

    @Override
    public void addValueToHistory(final float value) {
        this.history.addValue(value);
    }

}
