package it.unibo.esiot.assignment03.controlunit.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.controlunit.controller.api.DashboardController;

/**
 * Http server that sends system informations.
 */
public final class DashboardCommunicationImpl extends Thread {
    private static final int BUFFER_SIZE = 1024;
    private static final int PORT = 8080;
    private static final String CRLF = "\r\n";
    private static final String SEND_MESSAGE_START = "Sys: ";
    private static final String RECEIVE_MESSAGE_START = "Das: ";
    private static final String SEPARATOR = ", ";
    private static final String PAIR_SEPARATOR = "; ";
    private static final String NEW_LINE = "\n";
    private static final String MANAGE_ALARM = "Alarm";
    private static final String SWITCH_MODE = "Mode";

    private final DashboardController controller;
    private final ServerSocket serverSocket;

    /**
     * Initializes the communicator for the dashboard.
     * @param controller the {@link DashboardController} used to communicate to system data.
     * @throws IOException if an error occurs during the initialization of the http socket.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the dashboard"
            + "need to store data and send stored data."
    )
    public DashboardCommunicationImpl(final DashboardController controller) throws IOException {
        this.controller = controller;
        this.serverSocket = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        try {
            while (this.serverSocket.isBound() && !this.serverSocket.isClosed()) {
                final Socket socket = serverSocket.accept();

                this.receive(socket);
                this.send(socket);

                socket.close();
            }
            this.serverSocket.close();
        } catch (IOException | InterruptedException e) {
            e.addSuppressed(e);
        }
    }

    private void receive(final Socket socket) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.defaultCharset()));
        String headerLine;
        int contentLength = 0;
        while ((headerLine = in.readLine()) != null && !headerLine.isEmpty()) { // NOPMD necessary assignment
            final String[] parts = headerLine.split(":", 2);
            if (parts.length == 2 && "Content-Length".equalsIgnoreCase(parts[0].trim())) {
                try {
                    contentLength = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    e.addSuppressed(e);
                }
            }
        }

        final StringBuilder requestBody = new StringBuilder();
        if (contentLength > 0) {
            final char[] buffer = new char[BUFFER_SIZE];
            int bytesRead;
            int totalBytesRead = 0;
            while (totalBytesRead < contentLength && (bytesRead = in.read(buffer, 0, // NOPMD necessary assignment
                Math.min(buffer.length, contentLength - totalBytesRead))) != -1) {
                requestBody.append(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            final String body = requestBody.toString();
            if (body.startsWith(RECEIVE_MESSAGE_START)) {
                final String alarm = body.substring(RECEIVE_MESSAGE_START.length(), body.indexOf(SEPARATOR));
                final String mode = body.substring(body.indexOf(SEPARATOR) + SEPARATOR.length(), body.lastIndexOf(SEPARATOR));
                final String opening = body.substring(body.lastIndexOf(SEPARATOR) + SEPARATOR.length());
                if (MANAGE_ALARM.equals(alarm)) {
                    this.controller.manageTemperature();
                }
                if (SWITCH_MODE.equals(mode)) {
                    this.controller.doSwitchMode();
                }
                if (!opening.isEmpty()) {
                    this.controller.setManualWindowOpening(Integer.parseInt(opening));
                }
            }
        }
    }

    private void send(final Socket socket) throws IOException, InterruptedException {
        final OutputStream outputStream = socket.getOutputStream();

        final StringBuilder builder = new StringBuilder();
        builder.append(SEND_MESSAGE_START + this.controller.getCurrentTemperature() + SEPARATOR // NOPMD needed non-literals
            + this.controller.getTemperatureState().toString() + SEPARATOR
            + this.controller.getAverage() + SEPARATOR
            + this.controller.getMin() + SEPARATOR
            + this.controller.getMax() + NEW_LINE);
        for (final var pair : this.controller.getHistory()) {
            builder.append("{" + pair.getX() + PAIR_SEPARATOR + pair.getY() + "}" + SEPARATOR); // NOPMD needed non-literals
        }
        builder.append(NEW_LINE + this.controller.getWindowMode().toString() + SEPARATOR // NOPMD needed non-literals
            + this.controller.getCurrentWindowOpening());

        final String response = "HTTP/1.1 200 OK" + CRLF
            + "Content-Lenght: " + builder.length() + CRLF + CRLF
            + builder.toString() + CRLF + CRLF;

        outputStream.write(response.getBytes(Charset.defaultCharset()));

        sleep(10);

        outputStream.close();
    }
}
