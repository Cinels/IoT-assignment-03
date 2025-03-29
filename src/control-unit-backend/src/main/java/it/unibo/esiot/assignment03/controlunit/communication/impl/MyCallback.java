package it.unibo.esiot.assignment03.controlunit.communication.impl;

import java.nio.charset.Charset;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.esiot.assignment03.controlunit.controller.api.TemperatureController;

/**
 * Implementation for {@link MqttCallback}.
 */
public final class MyCallback implements MqttCallback {
    private static final String FREQUENCY_TOPIC = "esiot-2024-assignment03-frequency-topic";
    private static final String SEND_MESSAGE_START = "Sys: ";
    private static final String RECEIVE_MESSAGE_START = "Tmp: ";
    private static final int QOS = 1;

    private final MqttClient client;
    private final TemperatureController controller;

    /**
     * Constructs a new MyCallback instance.
     * @param client  the MQTT client used for communication.
     * @param controller the {@link TemperatureController} used to communicate to system data.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2", "EI_EXPOSE_REP"},
        justification = "The communications between the control unit and the temperature controller"
            + "need to store data and send stored data."
    )
    public MyCallback(final MqttClient client, final TemperatureController controller) {
        this.client = client;
        this.controller = controller;
    }

    @Override
    public void connectionLost(final Throwable cause) {
        try {
            client.reconnect();
        } catch (MqttException e) {
            e.addSuppressed(e);
        }
    }

    @Override
    public void messageArrived(final String topic, final MqttMessage message) {
        final String msg = message.toString();
        if (msg.startsWith(RECEIVE_MESSAGE_START)) {
            final Float temperature = Float.parseFloat(msg.substring(RECEIVE_MESSAGE_START.length()));
            this.controller.setCurrentTemperature(temperature);
            this.controller.addValueToHistory(temperature);
        }
        this.sendMessage();
    }

    @Override
    public void deliveryComplete(final IMqttDeliveryToken token) { }

    private void sendMessage() {
        final StringBuilder msg = new StringBuilder(SEND_MESSAGE_START);
        msg.append(this.controller.getSampleFrequency());
        final MqttMessage message = new MqttMessage(msg.toString().getBytes(Charset.defaultCharset()));
        message.setQos(QOS);
        try {
            client.publish(FREQUENCY_TOPIC, message);
        } catch (MqttException e) {
            e.addSuppressed(e);
        }
    }
}
