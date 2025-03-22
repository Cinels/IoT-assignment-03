#include "tasks/CommunicationTask.hpp"
#include <Arduino.h>

#define SEND_MESSAGE_START "Win: "
#define RECEIVE_MESSAGE_START "Sys: "
#define START_DATA_INDEX 5
#define SEPARATOR ","
#define OFFSET_FROM_SEPARATOR 2
#define MANUAL_STRING " Manual"
#define AUTOMATIC_STRING " Automatic"

CommunicationTask::CommunicationTask(SystemInformations* systemInformations, Window* window) {
    Serial.begin(9600);
    this->systemInformations = systemInformations;
    this->window = window;
}

void CommunicationTask::tick() {
    this->receiveMessage();
    this->sendMessage();
}

void CommunicationTask::receiveMessage() {
    if (Serial.available()) {
        String message = Serial.readString();
        if (message.startsWith(RECEIVE_MESSAGE_START)) {
            float temperature = message.substring(START_DATA_INDEX, message.indexOf(SEPARATOR)).toFloat();
            int opening = message.substring(message.indexOf(SEPARATOR) + OFFSET_FROM_SEPARATOR).toInt();
            this->systemInformations->setTemperature(temperature);
            this->systemInformations->setWindowOpening(opening);
        }
    }
}

void CommunicationTask::sendMessage() {
    if (Serial.availableForWrite()) {
        String message = SEND_MESSAGE_START;
        message.concat(String(this->window->getOpening()));
        message.concat(SEPARATOR);
        message.concat(this->systemInformations->getMode() == MANUAL_MODE ? MANUAL_STRING : AUTOMATIC_STRING);
        Serial.println(message);
    }
}
