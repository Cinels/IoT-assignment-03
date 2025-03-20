#include "tasks/CommunicationTask.hpp"
#include <Arduino.h>

CommunicationTask::CommunicationTask(SystemInformations* systemInformations, Window* window) {
    Serial.begin(9600);
    this->systemInformations = systemInformations;
    this->window = window;
}

void CommunicationTask::tick() {

}
