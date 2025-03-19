#include "tasks/TemperatureTask.hpp"
#include <Arduino.h>
#include <WiFi.h>

#define SSID ""
#define PASSWORD ""
#define TEMPERATURE_SENSOR_PIN 4
#define ERROR_LED_PIN 1
#define NO_ERROR_LED_PIN 2

TemperatureTask::TemperatureTask(int period) {
    this->temperatureSensor = new TempSensor(TEMPERATURE_SENSOR_PIN);
    this->errorLed = new Led(ERROR_LED_PIN);
    this->noErrorLed = new Led(NO_ERROR_LED_PIN);
    this->temperature = this->temperatureSensor->getTemperature();
    this->setPeriod(period);
    this->networkNotOk();
    WiFi.begin(SSID, PASSWORD);
}

void TemperatureTask::tick() {
    this->temperature = this->temperatureSensor->getTemperature();
    if(WiFi.status() != WL_CONNECTED) {
        this->networkNotOk();
        WiFi.begin(SSID, PASSWORD);        
    } else {
        this->networkOk();
        Serial.println("connesso");
        //send in mqtt
    }
    Serial.println(this->temperature);
}

void TemperatureTask::networkOk() {
    this->errorLed->switchOff();
    this->noErrorLed->switchOn();
}

void TemperatureTask::networkNotOk() {
    this->errorLed->switchOn();
    this->noErrorLed->switchOff();
}
