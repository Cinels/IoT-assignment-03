package it.unibo.esiot.assignment03.controlunit.communication.impl;

import it.unibo.esiot.assignment03.controlunit.communication.api.TemperatureCommunication;
import it.unibo.esiot.assignment03.controlunit.model.api.HistoryTracker;
import it.unibo.esiot.assignment03.controlunit.model.api.Kernel;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.mqtt.MqttClient;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.AbstractVerticle;

/**
 * Implementation of the {@link TemperatureCommunication} interface that handles
 * MQTT-based communication for temperature data and sample frequency updates.
 */
public final class TemperatureCommunicationImpl extends AbstractVerticle implements TemperatureCommunication {
    private static final String BROKER_ADDRESS = "broker.mqtt-dashboard.com";
    private static final String TEMPERATURE_TOPIC = "esiot-2024";
    private static final String FREQUENCY_TOPIC = "esiot-2024";
    private static final String SEND_MESSAGE_START = "Sys: ";
    private static final String RECEIVE_MESSAGE_START = "Tmp: ";
    private static final int PORT = 1883;

    private final MqttClient client;
    private final HistoryTracker history;
    private final Kernel kernel;

    /**
     * Initializes the MQTT client, history tracker, and kernel.
     * @param history the {@link HistoryTracker} instance used to track temperature history.
     * @param kernel the {@link Kernel} instance used to manage the system's state and temperature.
     */
    public TemperatureCommunicationImpl(final HistoryTracker history, final Kernel kernel) {
        this.client = MqttClient.create(vertx);
        this.history = history;
        this.kernel = kernel;
    }

    @Override
    public void start() {
        this.client.connect(PORT, BROKER_ADDRESS);

        this.client.publishHandler(s -> {
            final String message =  s.payload().toString();
            if (message.startsWith(RECEIVE_MESSAGE_START)) {
                this.kernel.setCurrentTemperature(Float.parseFloat(
                    message.substring(RECEIVE_MESSAGE_START.length())));
                this.history.addValue(this.kernel.getCurrentTemperature());

                this.sendMessage();
            }
        }).subscribe(TEMPERATURE_TOPIC, MqttQoS.AT_LEAST_ONCE.value());
    }

    private void sendMessage() {
        final StringBuilder message = new StringBuilder(SEND_MESSAGE_START);
        message.append(this.kernel.getSampleFrequency());

        this.client.publish(FREQUENCY_TOPIC,
            Buffer.buffer(message.toString()),
            MqttQoS.AT_LEAST_ONCE,
            false,
            false
        );
    }
}
