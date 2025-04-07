package it.unibo.esiot.assignment03.dashboard.gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Window management panel.
 */
public final class WindowBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Font FONT = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 18);
    private static final Font BOLD_FONT = new Font(Font.DIALOG, Font.BOLD, 18);
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int START = 0;

    private final transient DashboardController controller;
    private final JLabel modeLabel;
    private final JButton switchModeButton;
    private final JLabel openingLabel;
    private final JSlider openingSlider;
    private final JButton openingButton;

    /**
     * Creates a new window management panel.
     * @param controller the controller that interacts with the system data.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public WindowBar(final DashboardController controller) {
        this.controller = controller;
        this.setLayout(new FlowLayout());
        this.modeLabel = new JLabel();
        this.switchModeButton = new JButton("Switch Mode");
        this.openingLabel = new JLabel();
        this.openingSlider = new JSlider(MIN, MAX, START);
        this.openingButton = new JButton();
        this.setup();
        this.draw();
    }

    private void setup() {
        this.modeLabel.setFont(BOLD_FONT);
        this.switchModeButton.setFont(FONT);
        this.openingLabel.setFont(BOLD_FONT);
        this.openingSlider.setFont(FONT);
        this.switchModeButton.addActionListener(e -> this.controller.doSwitchMode());
        this.openingButton.setFont(FONT);
        this.openingButton.addActionListener(e -> this.controller.setOpeningFromDashboard(this.openingSlider.getValue()));

        this.add(this.modeLabel);
        this.add(this.switchModeButton);
        this.add(this.openingLabel);
        this.add(this.openingSlider);
        this.add(this.openingButton);
    }

    /**
     * Displays window informations.
     */
    public void draw() {
        this.modeLabel.setText("Mode: " + this.controller.getWindowMode());
        this.openingLabel.setText("Opening: " + this.controller.getWindowOpening() + "%");
        this.openingSlider.setEnabled(this.controller.getWindowMode().equals(WindowMode.MANUAL.toString()));
        this.openingButton.setText("Confirm Opening to: " + this.openingSlider.getValue() + "%");
        this.openingButton.setEnabled(this.controller.getWindowMode().equals(WindowMode.MANUAL.toString()));

        this.repaint();
        this.validate();
    }
}
