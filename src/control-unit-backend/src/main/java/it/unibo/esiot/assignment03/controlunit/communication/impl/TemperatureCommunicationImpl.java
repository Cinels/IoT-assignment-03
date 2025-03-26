package it.unibo.esiot.assignment03.controlunit.communication.impl;

import it.unibo.esiot.assignment03.controlunit.communication.api.TemperatureCommunication;
import it.unibo.esiot.assignment03.controlunit.model.api.HistoryTracker;
import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;

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
     * Initializes the MQTT client, history tracker, and kernel.
     * @param history the {@link HistoryTracker} instance used to track temperature history.
     * @param kernel the {@link Kernel} instance used to manage the system's state and temperature.
     * @throws MqttException if an error occurs during the initialization or connection of the MQTT client.
     */
    public TemperatureCommunicationImpl(final HistoryTracker history, final Kernel kernel) throws MqttException {
        this.client = new MqttClient(BROKER_ADDRESS, CLIENT_ID);
        final MqttConnectOptions options = new MqttConnectOptions();
        this.client.connect(options);
        this.client.setCallback(new MyCallback(this.client, history, kernel));
        this.client.subscribe(TEMPERATURE_TOPIC, QOS);
    }
}
