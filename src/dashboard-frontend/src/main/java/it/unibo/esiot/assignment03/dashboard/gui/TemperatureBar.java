package it.unibo.esiot.assignment03.dashboard.gui;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;

/**
 * Temperature management panel.
 */
public final class TemperatureBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String DEGREE_SYMBOL = "\u00B0";
    private static final Font FONT = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 18);
    private static final Font BOLD_FONT = new Font(Font.DIALOG, Font.BOLD, 18);

    private final transient DashboardController controller;
    private final JLabel curTemperatureLabel;
    private final JLabel stateLabel;
    private final JLabel avgTemperatureLabel;
    private final JLabel minTemperatureLabel;
    private final JLabel maxTemperatureLabel;
    private final JButton alarmButton;


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
        this.curTemperatureLabel = new JLabel();
        this.stateLabel = new JLabel();
        this.avgTemperatureLabel = new JLabel();
        this.minTemperatureLabel = new JLabel();
        this.maxTemperatureLabel = new JLabel();
        this.alarmButton = new JButton("Manage Alarm");
        this.setup();
        this.draw();
    }

    private void setup() {
        this.curTemperatureLabel.setFont(BOLD_FONT);
        this.stateLabel.setFont(BOLD_FONT);
        this.avgTemperatureLabel.setFont(FONT);
        this.minTemperatureLabel.setFont(FONT);
        this.maxTemperatureLabel.setFont(FONT);
        this.alarmButton.setFont(FONT);
        this.alarmButton.addActionListener(e -> this.controller.doManageAlarm());

        this.add(this.curTemperatureLabel);
        this.add(this.stateLabel);
        this.add(this.avgTemperatureLabel);
        this.add(this.minTemperatureLabel);
        this.add(this.maxTemperatureLabel);
        this.add(this.alarmButton);
    }

    /**
     * Displays temperature informations.
     */
    public void draw() {
        this.curTemperatureLabel.setText("Temperature: " + this.controller.getCurrentTemperature() + DEGREE_SYMBOL + "C");
        this.stateLabel.setText("State: " + this.controller.getTemperatureState());
        this.avgTemperatureLabel.setText("Avg Temperature: " + this.controller.getAvgTemperature() + DEGREE_SYMBOL + "C");
        this.minTemperatureLabel.setText("Min Temperature: " + this.controller.getMinTemperature() + DEGREE_SYMBOL + "C");
        this.maxTemperatureLabel.setText("Max Temperature: " + this.controller.getMaxTemperature() + DEGREE_SYMBOL + "C");
        this.alarmButton.setEnabled(this.controller.getTemperatureState().equals(TemperatureState.ALARM.toString()));

        this.repaint();
        this.validate();
    }
}
