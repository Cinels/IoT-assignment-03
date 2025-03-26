#include "tasks/TemperatureTask.hpp"
#include <Arduino.h>
#include <WiFi.h>
#include <PubSubClient.h>

#define MSG_BUFFER_SIZE 20
#define TEMPERATURE_SENSOR_PIN 4
#define ERROR_LED_PIN 1
#define NO_ERROR_LED_PIN 2
#define CONNECTION_DELAY 500
#define ONE_SECOND_PERIOD 1000.0
#define PORT 1883
#define SSID ""
#define PASSWORD ""
#define MQTT_SERVER "broker.mqtt-dashboard.com"
#define TEMPERATURE_TOPIC "esiot-2024-assignment03-temperature-topic"
#define FREQUENCY_TOPIC "esiot-2024-assignment03-frequency-topic"
#define SEND_MESSAGE_START "Tmp: "
#define RECEIVE_MESSAGE_START "Sys: "
#define RECEIVE_MESSAGE_LENGHT 5
#define END_CHARACTER '\0'

WiFiClient espClient;
PubSubClient client(espClient);

TemperatureTask::TemperatureTask(int period) {
    randomSeed(micros());
    this->temperatureSensor = new TempSensor(TEMPERATURE_SENSOR_PIN);
    this->errorLed = new Led(ERROR_LED_PIN);
    this->noErrorLed = new Led(NO_ERROR_LED_PIN);
    this->temperature = this->temperatureSensor->getTemperature();
    this->setPeriod(period);
    this->networkNotOk();
    this->setupConnection();
}

void TemperatureTask::tick() {
    this->temperature = this->temperatureSensor->getTemperature();
    if(WiFi.status() != WL_CONNECTED || !client.connected()) {
        this->networkNotOk();
        this->setupConnection();       
    } else {
        this->networkOk();
        client.loop();
        char msg[MSG_BUFFER_SIZE];
        String message = SEND_MESSAGE_START;
        message += this->temperature;
        snprintf(msg, MSG_BUFFER_SIZE, message.c_str());
        client.publish(TEMPERATURE_TOPIC, msg);  
    }
}

void TemperatureTask::networkOk() {
    this->errorLed->switchOff();
    this->noErrorLed->switchOn();
}

void TemperatureTask::networkNotOk() {
    this->errorLed->switchOn();
    this->noErrorLed->switchOff();
}

void TemperatureTask::setupConnection() {
    if (!WiFi.isConnected()) {
        WiFi.begin(SSID, PASSWORD);
        delay(CONNECTION_DELAY);
    }
    if (WiFi.isConnected() && !client.connected()) {
        client.setServer(MQTT_SERVER, PORT);
        auto callbackFunction = [this](char* topic, uint8_t* payload, unsigned int length) {
            this->callback(topic, payload, length);
        };
        client.setCallback(callbackFunction);
        String clientId = String("esiot-2024-client-")+String(random(0xffff), HEX);
        if (client.connect(clientId.c_str())) {
            client.subscribe(FREQUENCY_TOPIC);
        }
    }
}

void TemperatureTask::callback(char* topic, byte* payload, unsigned int length) {
    char msg[length + 1];
    memcpy(msg, payload, length);
    msg[length] = END_CHARACTER;
    String message = String(msg);
    if (message.startsWith(RECEIVE_MESSAGE_START)) {
        float freq = message.substring(RECEIVE_MESSAGE_LENGHT).toFloat();
        this->setPeriod(ONE_SECOND_PERIOD / freq);
    }
}