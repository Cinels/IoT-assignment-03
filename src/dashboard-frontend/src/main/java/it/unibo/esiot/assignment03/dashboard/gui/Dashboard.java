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
    private static final int SLEEP_TIME = 100;

    private final TemperatureBar temperatureBar;
    private final HistoryBar historyBar;
    private final WindowBar windowBar;

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
        this.temperatureBar = new TemperatureBar(controller);
        this.historyBar = new HistoryBar(controller);
        this.windowBar = new WindowBar(controller);
        this.add(this.temperatureBar, BorderLayout.WEST);
        this.add(this.historyBar, BorderLayout.CENTER);
        this.add(this.windowBar, BorderLayout.SOUTH);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Starts the updating of data.
     */
    public void start() {
        new Thread(this::run).start();
    }

    private void run() {
        while (true) {
            this.historyBar.updateDataset();

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.addSuppressed(e);
            }
        }
    }
}
