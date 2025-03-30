package it.unibo.esiot.assignment03.dashboard.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.DashboardController;

/**
 * History graph panel.
 */
public final class HistoryBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final float MILLIS = 1000f;

    private final transient DashboardController controller;
    private final long initTime;
    private XYSeries series;

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
        this.initTime = System.currentTimeMillis();
        final var chart = new ChartPanel(this.createChart(this.createDataset()));
        chart.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(chart);
        this.updateDataset();
    }

    private JFreeChart createChart(final XYSeriesCollection dataset) {
        return ChartFactory.createXYLineChart(
            "History",
            "Time",
            "Temperature",
            dataset,
            PlotOrientation.VERTICAL,
            false,
            false,
            false
        );
    }

    private XYSeriesCollection createDataset() {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        series = new XYSeries("Temperature");
        dataset.addSeries(series);
        return dataset;
    }

    /**
     * Updates the chart dataset.
     */
    public void updateDataset() {
        final var history = this.controller.getTemperatureHistory();
        series.clear();
        for (final var pair : history) {
            series.add((pair.getX() - initTime) / MILLIS, pair.getY());
        }
    }
}
