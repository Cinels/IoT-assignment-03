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

void SystemInformations::switchMode(bool isFromDashboard) {
    if (isFromDashboard && this->mode == AUTOMATIC_MODE) this->mode = DASHBOARD_MODE;
    else if (this->mode == AUTOMATIC_MODE) this->mode = MANUAL_MODE;
    else this->mode = AUTOMATIC_MODE;
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
