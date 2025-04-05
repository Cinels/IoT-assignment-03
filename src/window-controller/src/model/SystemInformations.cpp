#include "model/SystemInformations.hpp"

SystemInformations::SystemInformations() {
    this->temperature = 0.0;
    this->windowOpening = 0;
    this->mode = AUTOMATIC_MODE;
}

void SystemInformations::setTemperature(float temperature) {
    this->temperature = temperature;
}

void SystemInformations::setWindowOpeningGoal(int windowOpening) {
    this->windowOpening = windowOpening;
}

void SystemInformations::switchMode(WindowMode newMode) {
    this->mode = newMode;
}

float SystemInformations::getTemperature() {
    return this->temperature;
}

int SystemInformations::getWindowOpeningGoal() {
    return this->windowOpening;
}

WindowMode SystemInformations::getMode() {
    return this->mode;
}
