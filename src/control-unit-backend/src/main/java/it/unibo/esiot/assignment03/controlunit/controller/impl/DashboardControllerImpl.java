package it.unibo.esiot.assignment03.controlunit.controller.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.controlunit.controller.api.DashboardController;
import it.unibo.esiot.assignment03.controlunit.model.api.HistoryTracker;
import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;
import it.unibo.esiot.assignment03.controlunit.model.impl.Pair;
import it.unibo.esiot.assignment03.controlunit.model.states.TemperatureState;
import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * Implementation of {@link DashboardController}.
 */
public final class DashboardControllerImpl implements DashboardController {

    private final Kernel kernel;
    private final HistoryTracker history;

    /**
     * Creates a controller for dashboard communications.
     * @param kernel  the kernel instance to manage the system's state and operations.
     * @param history the history tracker to store temperature values.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the temperature controller"
            + "need to store data and send stored data."
    )
    public DashboardControllerImpl(final Kernel kernel, final HistoryTracker history) {
        this.kernel = kernel;
        this.history = history;
    }

    @Override
    public TemperatureState getTemperatureState() {
        return this.kernel.getTemperatureState();
    }

    @Override
    public WindowMode getWindowMode() {
        return this.kernel.getWindowMode();
    }

    @Override
    public float getCurrentTemperature() {
        return this.kernel.getCurrentTemperature();
    }

    @Override
    public int getCurrentWindowOpening() {
        return this.kernel.getCurrentWindowOpening();
    }

    @Override
    public void manageTemperature() {
        this.kernel.manageTemperature();
    }

    @Override
    public void setManualWindowOpening(final int opening) {
        this.kernel.setManualWindowOpening(opening);
    }

    @Override
    public void doSwitchMode() {
        this.kernel.doSwitchMode();
    }

    @Override
    public float getAverage() {
        return this.history.getAverage();
    }

    @Override
    public float getMin() {
        return this.history.getMin();
    }

    @Override
    public float getMax() {
        return this.history.getMax();
    }

    @Override
    public List<Pair<Long, Float>> getHistory() {
        return this.history.getHistory();
    }
}
