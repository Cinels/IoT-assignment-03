package it.unibo.esiot.assignment03.controlunit.communication.impl;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.controlunit.communication.api.WindowCommunication;
import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;
import it.unibo.esiot.assignment03.controlunit.model.states.WindowMode;

/**
 * Implementation of the communication between the Control Unit and the Window controller.
 */
public final class WindowCommunicationImpl implements WindowCommunication, SerialPortEventListener {
    private static final String PORT = "/dev/ttyUSB0";
    private static final String SEND_MESSAGE_START = "Sys: ";
    private static final String RECEIVE_MESSAGE_START = "Win: ";
    private static final String SWITCH_MODE_MESSAGE = "Mode";
    private static final String SEPARATOR = ", ";
    private static final int OFFSET_FROM_SEPARATOR = 2;
    private static final int BAUD_RATE = 9600;

    private final Kernel kernel;
    private final SerialPort serialPort;

    /**
     * Creates a window communication object.
     * @param kernel the kernel of the Control Unit.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the window controller"
            + "need to store data and send stored data."
    )
    public WindowCommunicationImpl(final Kernel kernel) {
        this.kernel = kernel;
        serialPort = new SerialPort(PORT);
        try {
            serialPort.openPort();
            serialPort.setParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(this);
        } catch (SerialPortException e) {
            e.addSuppressed(e);
        }
    }

    @Override
    public void serialEvent(final SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                this.messageReceived(this.serialPort.readString());
                this.serialPort.writeString(this.messageToSend());
            } catch (SerialPortException e) {
                e.addSuppressed(e);
            }
        }
    }

    private String messageToSend() {
        final StringBuilder message = new StringBuilder(SEND_MESSAGE_START);
        if (this.kernel.isModeToSwitch()) {
            message.append(SWITCH_MODE_MESSAGE);
            this.kernel.modeSwitched();
        }
        message.append(SEPARATOR).append(Float.toString(this.kernel.getCurrentTemperature()))
            .append(SEPARATOR).append(Integer.toString(this.kernel.getNextOpening()));
        return message.toString();
    }

    private void messageReceived(final String msg) {
        if (msg.startsWith(RECEIVE_MESSAGE_START)) {
            final String opening = msg.substring(RECEIVE_MESSAGE_START.length(), msg.indexOf(SEPARATOR));
            final String mode = msg.substring(msg.indexOf(SEPARATOR) + OFFSET_FROM_SEPARATOR);
            this.kernel.setCurrentWindowOpening(Integer.parseInt(opening));
            this.kernel.setWindowMode(WindowMode.valueOf(mode));
        }
    }
}
