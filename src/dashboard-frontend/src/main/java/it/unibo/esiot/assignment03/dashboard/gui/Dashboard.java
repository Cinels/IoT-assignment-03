package it.unibo.esiot.assignment03.dashboard.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;

/**
 * The dashboard of the temperature monitoring system.
 */
public final class Dashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 800;

    /**
     * Creates a new dashboard.
     * @param controller the controller that interacts with the system data.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public Dashboard(final DashboardController controller) {
        this.setLayout(new BorderLayout());
        this.add(new TemperatureBar(controller), BorderLayout.WEST);
        this.add(new HistoryBar(controller), BorderLayout.CENTER);
        this.add(new WindowBar(controller), BorderLayout.SOUTH);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
