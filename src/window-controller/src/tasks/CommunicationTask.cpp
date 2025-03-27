#include "tasks/CommunicationTask.hpp"
#include <Arduino.h>

#define BAUD_RATE 115200
#define SEND_MESSAGE_START "Win: "
#define RECEIVE_MESSAGE_START "Sys: "
#define START_DATA_INDEX 5
#define SEPARATOR ", "
#define OFFSET_FROM_SEPARATOR 2
#define MANUAL_STRING "Manual"
#define AUTOMATIC_STRING "Automatic"
#define SWITCH_MODE_STRING "Mode"

CommunicationTask::CommunicationTask(SystemInformations* systemInformations, Window* window) {
    Serial.begin(BAUD_RATE);
    this->systemInformations = systemInformations;
    this->window = window;
    this->prevOpening = -1;
}

void CommunicationTask::tick() {
    this->receiveMessage();
    this->sendMessage();
}

void CommunicationTask::receiveMessage() {
    if (Serial.available()) {
        String message = Serial.readString();
        if (message.startsWith(RECEIVE_MESSAGE_START)) {
            String changeMode = message.substring(START_DATA_INDEX, message.indexOf(SEPARATOR));
            float temperature = message.substring(message.indexOf(SEPARATOR) + OFFSET_FROM_SEPARATOR, message.lastIndexOf(SEPARATOR)).toFloat();
            int opening = message.substring(message.lastIndexOf(SEPARATOR) + OFFSET_FROM_SEPARATOR).toInt();
            if (changeMode.equals(SWITCH_MODE_STRING)) {
                this->systemInformations->switchMode(true);
            }
            if (this->systemInformations->getMode() == MANUAL_MODE && this->prevOpening != opening) {
                this->systemInformations->switchMode(true);
            }
            this->systemInformations->setTemperature(temperature);
            this->systemInformations->setWindowOpeningGoal(opening);
            this->prevOpening = opening;
        }
    }
}

void CommunicationTask::sendMessage() {
    if (Serial.availableForWrite()) {
        String message = SEND_MESSAGE_START;
        message.concat(String(this->window->getOpening()));
        message.concat(SEPARATOR);
        message.concat(this->systemInformations->getMode() == AUTOMATIC_MODE ? AUTOMATIC_STRING : MANUAL_STRING);
        Serial.println(message);
    }
}
