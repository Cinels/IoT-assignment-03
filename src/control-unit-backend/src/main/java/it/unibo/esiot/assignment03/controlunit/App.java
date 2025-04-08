package it.unibo.esiot.assignment03.controlunit;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;

import it.unibo.esiot.assignment03.controlunit.communication.DashboardCommunicationImpl;
import it.unibo.esiot.assignment03.controlunit.communication.TemperatureCommunicationImpl;
import it.unibo.esiot.assignment03.controlunit.communication.WindowCommunicationImpl;
import it.unibo.esiot.assignment03.controlunit.controller.impl.DashboardControllerImpl;
import it.unibo.esiot.assignment03.controlunit.controller.impl.TemperatureControllerImpl;
import it.unibo.esiot.assignment03.controlunit.controller.impl.WindowControllerImpl;
import it.unibo.esiot.assignment03.controlunit.model.impl.HistoryTrackerImpl;
import it.unibo.esiot.assignment03.controlunit.model.impl.KernelImpl;

/**
 * The main class of the application, which starts its execution.
 */
public final class App {
    private App() { }

    /**
     * The main method of the application, from which execution starts.
     * @param args are ignored.
     * @throws IOException if an error occurs during the initialization of the http socket.
     * @throws MqttException if an error occurs during the initialization or connection of the MQTT client.
     */
    public static void main(final String[] args) throws IOException, MqttException, InterruptedException {
        final var kernel = new KernelImpl();
        final var history = new HistoryTrackerImpl();
        new TemperatureCommunicationImpl(new TemperatureControllerImpl(kernel, history));
        new WindowCommunicationImpl(new WindowControllerImpl(kernel)).start();
        new DashboardCommunicationImpl(new DashboardControllerImpl(kernel, history)).start();
    }
}
