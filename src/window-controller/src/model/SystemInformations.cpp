#include "model/SystemInformations.hpp"

SystemInformations::SystemInformations() {
    this->temperature = 0.0;
    this->windowOpening = 0;
    this->switchMode = false;
}

void SystemInformations::setTemperature(float temperature) {
    this->temperature = temperature;
}

void SystemInformations::setWindowOpening(int windowOpening) {
    this->windowOpening = windowOpening;
}

void SystemInformations::doSwitchMode() {
    this->switchMode = true;
}

void SystemInformations::modeSwitched() {
    this->switchMode = false;
}

float SystemInformations::getTemperature() {
    return this->temperature;
}

int SystemInformations::getWindowOpening() {
    return this->windowOpening;
}

bool SystemInformations::getSwitchMode() {
    return this->switchMode;
}
