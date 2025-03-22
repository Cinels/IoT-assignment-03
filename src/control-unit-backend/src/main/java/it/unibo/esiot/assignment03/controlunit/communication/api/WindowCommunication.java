package it.unibo.esiot.assignment03.controlunit.communication.api;

/**
 * Interface for the communication between the Control Unit and the Window controller.
 */
public interface WindowCommunication {

    /**
     * Creates the message to send.
     * @return the message to send.
     */
    String messageToSend();

    /**
     * Parses the received message.
     * @param msg the received message.
     */
    void messageReceived(String msg);
}
