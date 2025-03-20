#include "tasks/CommunicationTask.hpp"
#include <Arduino.h>

CommunicationTask::CommunicationTask(SystemInformations* SystemInformations) {
    this->systemInformations = systemInformations;
    Serial.begin(9600);
}

void CommunicationTask::tick() {

}
