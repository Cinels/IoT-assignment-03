package it.unibo.esiot.assignment03.controlunit.communication;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.controlunit.controller.api.WindowController;
import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * Handles the communication between the Control Unit and the Window controller.
 */
public final class WindowCommunicationImpl extends Thread implements SerialPortEventListener {
    private static final String PORT = "COM9";
    private static final String SEND_MESSAGE_START = "Sys: ";
    private static final String RECEIVE_MESSAGE_START = "Win: ";
    private static final String SWITCH_MODE_MESSAGE = "Mode";
    private static final String SEPARATOR = ", ";
    private static final char END_OF_LINE = '\n';
    private static final int OFFSET_FROM_SEPARATOR = 2;
    private static final long SLEEP_TIME = 300;

    private final WindowController controller;
    private final SerialPort serialPort;
    private ByteArrayOutputStream receivedDataBuffer = new ByteArrayOutputStream();

    /**
     * Creates a window communication object.
     * @param controller the controller for the window communication.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the window controller"
            + "need to store data and send stored data."
    )
    public WindowCommunicationImpl(final WindowController controller) {
        this.controller = controller;
        this.serialPort = new SerialPort(PORT);
        try {
            this.serialPort.openPort();
            this.serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            this.serialPort.addEventListener(this);
        } catch (SerialPortException e) {
            e.addSuppressed(e);
        }
    }

    @Override
    public void serialEvent(final SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                synchronized (this.serialPort) {
                    this.receivedDataBuffer.write(this.serialPort.readBytes(event.getEventValue()));
                    this.serialPort.writeString(this.messageToSend());
                }
            } catch (SerialPortException | IOException e) {
                e.addSuppressed(e);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            messageReceived();
            try {
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.addSuppressed(e);
            }
        }
    }

    private String messageToSend() {
        final StringBuilder message = new StringBuilder(SEND_MESSAGE_START);
        if (this.controller.isModeToSwitch()) {
            message.append(SWITCH_MODE_MESSAGE);
            this.controller.modeSwitched();
        }
        message.append(SEPARATOR).append(this.controller.getCurrentTemperature())
            .append(SEPARATOR).append(this.controller.getNextOpening());
        return message.toString();
    }

    private void messageReceived() {
        final byte[] receivedBytes = receivedDataBuffer.toByteArray();
        String receivedLine = "";
        for (int i = 0; i < receivedBytes.length; i++) {
            if ((char) receivedBytes[i] == END_OF_LINE) {
                final byte[] lineBytes = new byte[i + 1];
                System.arraycopy(receivedBytes, 0, lineBytes, 0, i + 1);
                receivedLine = new String(lineBytes, Charset.defaultCharset()).trim();

                final ByteArrayOutputStream tempBuffer = new ByteArrayOutputStream();
                tempBuffer.write(receivedBytes, i + 1, receivedBytes.length - (i + 1));
                receivedDataBuffer = tempBuffer;
                break;
            }
        }

        if (receivedLine.startsWith(RECEIVE_MESSAGE_START)) {
            final String opening = receivedLine.substring(RECEIVE_MESSAGE_START.length(), receivedLine.indexOf(SEPARATOR));
            final String mode = receivedLine.substring(receivedLine.indexOf(SEPARATOR) + OFFSET_FROM_SEPARATOR);
            this.controller.setCurrentWindowOpening(Integer.parseInt(opening));
            if (Arrays.asList(WindowMode.values()).stream().anyMatch(t -> t.toString().equals(mode))) {
                this.controller.setWindowMode(WindowMode.valueOf(mode.toUpperCase(Locale.getDefault())));
            }
        }
    }
}
