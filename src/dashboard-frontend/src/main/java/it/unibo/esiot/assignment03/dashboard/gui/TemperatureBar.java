package it.unibo.esiot.assignment03.dashboard.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;

/**
 * Temperature management panel.
 */
public final class TemperatureBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String DEGREE_SYMBOL = "\u00B0";

    private final transient DashboardController controller;

    /**
     * Creates a new temperature management panel.
     * @param controller the controller that interacts with the system data.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public TemperatureBar(final DashboardController controller) {
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JLabel("Temperature: " + this.controller.getCurrentTemperature() + DEGREE_SYMBOL + "C"));
        this.add(new JLabel("State: " + this.controller.getTemperatureState()));
        this.add(new JLabel("Avg Temperature: " + this.controller.getAvgTemperature() + DEGREE_SYMBOL + "C"));
        this.add(new JLabel("Min Temperature: " + this.controller.getMinTemperature() + DEGREE_SYMBOL + "C"));
        this.add(new JLabel("Max Temperature: " + this.controller.getMaxTemperature() + DEGREE_SYMBOL + "C"));

        final var button = new JButton("Manage Alarm");
        button.addActionListener(e -> { });
        this.add(button);
    }
}
