package it.unibo.esiot.assignment03.dashboard;

import it.unibo.esiot.assignment03.dashboard.communication.ControlUnitCommunicationImpl;
import it.unibo.esiot.assignment03.dashboard.controller.impl.CommunicationControllerImpl;
import it.unibo.esiot.assignment03.dashboard.controller.impl.DashboardControllerImpl;
import it.unibo.esiot.assignment03.dashboard.gui.Dashboard;
import it.unibo.esiot.assignment03.dashboard.model.impl.SystemDataImpl;

/**
 * The main class of the application, which starts its execution.
 */
public final class App {
    private App() { }

    /**
     * The main method of the application, from which execution starts.
     * @param args are ignored.
     */
    public static void main(final String[] args) {
        final var data = new SystemDataImpl();
        new ControlUnitCommunicationImpl(new CommunicationControllerImpl(data)).start();
        new Dashboard(new DashboardControllerImpl(data)).start();
    }
}
