package it.unibo.esiot.assignment03.controlunit.communication.impl;

import it.unibo.esiot.assignment03.controlunit.communication.api.TemperatureCommunication;
import it.unibo.esiot.assignment03.controlunit.controller.api.TemperatureController;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Implementation of the {@link TemperatureCommunication} interface that handles
 * MQTT-based communication for temperature data and sample frequency updates.
 */
public final class TemperatureCommunicationImpl implements TemperatureCommunication {
    private static final String BROKER_ADDRESS = "tcp://broker.mqtt-dashboard.com:1883";
    private static final String TEMPERATURE_TOPIC = "esiot-2024-assignment03-temperature-topic";
    private static final String CLIENT_ID = "esiot_client";
    private static final int QOS = 1;

    private final MqttClient client;

    /**
     * Initializes the MQTT client and controller.
     * @param controller the {@link TemperatureController} used to communicate to system data.
     * @throws MqttException if an error occurs during the initialization or connection of the MQTT client.
     */
    public TemperatureCommunicationImpl(final TemperatureController controller) throws MqttException {
        this.client = new MqttClient(BROKER_ADDRESS, CLIENT_ID);
        this.connectWithRetry(controller);
    }

    private void connectWithRetry(final TemperatureController controller) throws MqttException {
        int attempts = 0;
        final int maxAttempts = 5;
        int delay = 1000;
        final MqttConnectOptions options = new MqttConnectOptions();

        while (attempts < maxAttempts) {
            try {
                this.client.connect(options);
                this.client.setCallback(new MyCallback(this.client, controller));
                this.client.subscribe(TEMPERATURE_TOPIC, QOS);
                return;
            } catch (MqttException e) {
                attempts++;
                if (attempts < maxAttempts) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    delay *= 2;
                } else {
                    throw new MqttException(e);
                }
            }
        }
    }
}
