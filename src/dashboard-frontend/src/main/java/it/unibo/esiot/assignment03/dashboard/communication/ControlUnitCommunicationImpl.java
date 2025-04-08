package it.unibo.esiot.assignment03.dashboard.communication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.dashboard.controller.api.CommunicationController;
import it.unibo.esiot.assignment03.dashboard.model.impl.Pair;
import it.unibo.esiot.assignment03.dashboard.model.states.TemperatureState;
import it.unibo.esiot.assignment03.dashboard.model.states.WindowMode;

/**
 * Handles communications between dashboard system and control unit.
 */
public final class ControlUnitCommunicationImpl extends Thread {
    private static final int SLEEP_TIME = 500;
    private static final String MY_URI = "http://localhost:8080";
    private static final String SEND_MESSAGE_START = "Das: ";
    private static final String RECEIVE_MESSAGE_START = "Sys: ";
    private static final String SEPARATOR = ", ";
    private static final String PAIR_START = "{";
    private static final String PAIR_SEPARATOR = "; ";
    private static final String PAIR_END = "}";
    private static final String NEW_LINE = "\n";
    private static final String MANAGE_ALARM = "Alarm";
    private static final String SWITCH_MODE = "Mode";

    private final CommunicationController controller;

    /**
     * Initializes the communicator for the control unit.
     * @param controller the {@link CommunicationController} used to communicate to system data.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public ControlUnitCommunicationImpl(final CommunicationController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            this.httpRequest();

            try {
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.addSuppressed(e);
            }
        }
    }

    private void httpRequest() {
        final StringBuilder requestString = new StringBuilder();
        requestString.append(SEND_MESSAGE_START);
        if (this.controller.isAlarmToManage()) {
            requestString.append(MANAGE_ALARM);
            this.controller.alarmManaged();
        }
        requestString.append(SEPARATOR);
        if (this.controller.isModeToSwitch()) {
            requestString.append(SWITCH_MODE);
            this.controller.modeSwitched();
        }
        requestString.append(SEPARATOR);
        if (this.controller.isOpeningSetFromDashboard()) {
            requestString.append(this.controller.getOpeningFromDashboard()); // NOPMD needed non-literals
            this.controller.openingSetFromDashboard();
        }

        final var uri = URI.create(MY_URI);
        final var client = HttpClient.newHttpClient();
        final var request = HttpRequest
            .newBuilder(uri)
            .POST(HttpRequest.BodyPublishers.ofString(requestString.toString()))
            .build();
        try {
            final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            this.parseResponse(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parseResponse(final String message) {
        if (message.startsWith(RECEIVE_MESSAGE_START)) {
            final var lines = message.split(NEW_LINE);

            final var temperature = lines[0].substring(RECEIVE_MESSAGE_START.length()).split(SEPARATOR);

            this.controller.setCurrentTemperature(Float.parseFloat(temperature[0]));
            this.controller.setTemperatureState(TemperatureState.valueOf(temperature[1].toUpperCase(Locale.getDefault())));
            this.controller.setAvgTemperature(Float.parseFloat(temperature[2]));
            this.controller.setMinTemperature(Float.parseFloat(temperature[3]));
            this.controller.setMaxTemperature(Float.parseFloat(temperature[4]));

            if (!lines[1].isEmpty()) {
                final var history = lines[1].split(SEPARATOR);
                final List<Pair<Long, Float>> list = new ArrayList<>();
                for (final String string : history) {
                    final var str = string.substring(string.indexOf(PAIR_START) + 1, string.indexOf(PAIR_END));
                    final var pair = str.split(PAIR_SEPARATOR);
                    list.add(new Pair<>(Long.parseLong(pair[0]), Float.parseFloat(pair[1])));
                }
                this.controller.setTemperatureHistory(list);
            }

            final var opening = lines[2].split(SEPARATOR);
            this.controller.setWindowMode(WindowMode.valueOf(opening[0].toUpperCase(Locale.getDefault())));
            this.controller.setWindowOpening(Integer.parseInt(opening[1].replace("\r", "")));
        }
    }

}
