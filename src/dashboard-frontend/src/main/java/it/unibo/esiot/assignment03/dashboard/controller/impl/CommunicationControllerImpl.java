package it.unibo.esiot.assignment03.dashboard.controller.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.CommunicationController;
import it.unibo.esiot.assignment03.dashboard.model.api.SystemData;
import it.unibo.esiot.assignment03.dashboard.model.impl.Pair;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Implementation of {@link CommunicationController}.
 */
public final class CommunicationControllerImpl implements CommunicationController {

    private final SystemData systemData;

    /**
     * Creates a communication controller.
     * @param data the system data.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public CommunicationControllerImpl(final SystemData data) {
        this.systemData = data;
    }

    @Override
    public boolean isModeToSwitch() {
        return this.systemData.isModeToSwitch();
    }

    @Override
    public void modeSwitched() {
        this.systemData.modeSwitched();
    }

    @Override 
    public boolean isAlarmToManage() {
        return this.systemData.isAlarmToManage();
    }

    @Override
    public void alarmManaged() {
        this.systemData.alarmManaged();
    }

    @Override
    public int getOpeningFromDashboard() {
        return this.systemData.getOpeningFromDashboard();
    }

    @Override
    public void setCurrentTemperature(final float temperature) {
        this.systemData.setCurrentTemperature(temperature);
    }

    @Override
    public void setMaxTemperature(final float temperature) {
        this.systemData.setMaxTemperature(temperature);
    }

    @Override
    public void setAvgTemperature(final float temperature) {
        this.systemData.setAvgTemperature(temperature);
    }

    @Override
    public void setMinTemperature(final float temperature) {
        this.systemData.setMinTemperature(temperature);
    }

    @Override
    public void setTemperatureHistory(final List<Pair<Long, Float>> history) {
        this.systemData.setTemperatureHistory(history);
    }

    @Override
    public void setTemperatureState(final TemperatureState state) {
        this.systemData.setTemperatureState(state);
    }

    @Override
    public void setWindowMode(final WindowMode mode) {
        this.systemData.setWindowMode(mode);
    }

    @Override
    public void setWindowOpening(final int opening) {
        this.systemData.setWindowOpening(opening);
    }
}
