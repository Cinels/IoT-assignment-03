package it.unibo.esiot.assignment03.dashboard.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;

/**
 * Window management panel.
 */
public final class WindowBar extends JPanel {
    private static final long serialVersionUID = 1L;

    private final transient DashboardController controller;

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

        this.add(new JLabel("Opening: " + this.controller.getWindowOpening() + "%"));
        this.add(new JLabel("Mode: " + this.controller.getWindowMode()));

        final var button = new JButton("Switch Mode");
        final var slider = new JSlider();
        button.addActionListener(e1 -> { });
        this.add(button);

        final var sliderValue = new JLabel();
        sliderValue.setText(Integer.toString(slider.getValue()) + "%");
        slider.addChangeListener(e2 -> {
            sliderValue.setText(Integer.toString(slider.getValue()) + "%");
        });
        this.add(slider);
        this.add(sliderValue);
    }
}
