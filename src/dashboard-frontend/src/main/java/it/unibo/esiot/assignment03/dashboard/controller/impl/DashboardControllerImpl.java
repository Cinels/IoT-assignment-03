package it.unibo.esiot.assignment03.dashboard.controller.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;
import it.unibo.esiot.assignment03.dashboard.model.api.SystemData;
import it.unibo.esiot.assignment03.dashboard.model.impl.Pair;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Implementation of {@link DashboardController}.
 */
public final class DashboardControllerImpl implements DashboardController {

    private final SystemData systemData;

    /**
     * Creates a dashboard controller.
     * @param data the system data.
     */
	@SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public DashboardControllerImpl(final SystemData data) {
        this.systemData = data;
    }

    @Override
    public void doSwitchMode() {
        this.systemData.doSwitchMode();
    }

    @Override
    public void doManageAlarm() {
        this.systemData.doManageAlarm();
    }

    @Override
    public void setOpeningFromDashboard(final int opening) {
        this.systemData.setOpeningFromDashboard(opening);
    }

    @Override
    public float getCurrentTemperature() {
         return this.systemData.getCurrentTemperature();
    }

    @Override
    public float getMaxTemperature() {
        return this.systemData.getMaxTemperature();
    }

    @Override
    public float getAvgTemperature() {
        return this.systemData.getAvgTemperature();
    }

    @Override
    public float getMinTemperature() {
        return this.systemData.getMinTemperature();
    }

    @Override
    public List<Pair<Long, Float>> getTemperatureHistory() {
        return this.systemData.getTemperatureHistory();
    }

    @Override
    public TemperatureState getTemperatureState() {
        return this.systemData.getTemperatureState();
    }

    @Override
    public WindowMode getWindowMode() {
        return this.systemData.getWindowMode();
    }

    @Override
    public int getWindowOpening() {
        return this.systemData.getWindowOpening();
    }
}
