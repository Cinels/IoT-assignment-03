package it.unibo.esiot.assignment03.dashboard.gui;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;

/**
 * History graph panel.
 */
public final class HistoryBar extends JPanel {
    private static final long serialVersionUID = 1L;

    private final transient DashboardController controller;

    /**
     * Creates a new history graph panel.
     * @param controller the controller that interacts with the system data.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public HistoryBar(final DashboardController controller) {
        this.controller = controller;
        this.controller.setOpeningFromDashboard(0);
    }
}
