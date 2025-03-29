package it.unibo.esiot.assignment03.dashboard.controller.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;
import it.unibo.esiot.assignment03.dashboard.model.api.SystemData;
import it.unibo.esiot.assignment03.dashboard.model.impl.Pair;

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
    public String getCurrentTemperature() {
        return Float.toString(this.systemData.getCurrentTemperature());
    }

    @Override
    public String getMaxTemperature() {
        return Float.toString(this.systemData.getMaxTemperature());
    }

    @Override
    public String getAvgTemperature() {
        return Float.toString(this.systemData.getAvgTemperature());
    }

    @Override
    public String getMinTemperature() {
        return Float.toString(this.systemData.getMinTemperature());
    }

    @Override
    public List<Pair<Long, Float>> getTemperatureHistory() {
        return this.systemData.getTemperatureHistory();
    }

    @Override
    public String getTemperatureState() {
        return this.systemData.getTemperatureState().toString();
    }

    @Override
    public String getWindowMode() {
        return this.systemData.getWindowMode().toString();
    }

    @Override
    public String getWindowOpening() {
        return Integer.toString(this.systemData.getWindowOpening());
    }
}
