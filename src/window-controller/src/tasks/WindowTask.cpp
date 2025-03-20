#include "tasks/WindowTask.hpp"

WindowTask::WindowTask(UserPanel *userPanel, Window *window, SystemInformations *systemInformations) {
    this->userPanel = userPanel;
    this->window = window;
    this->systemInformations = systemInformations;
    this->prevOpening = -1;
    this->prevTemperature = 100.0;
}

void WindowTask::tick() {
    this->window->setOpening(this->systemInformations->getMode() == AUTOMATIC_MODE ?
        this->systemInformations->getWindowOpening() :
        this->userPanel->getWindowManualOpening());
    if (this->prevOpening != this->window->getOpening()) {
        this->userPanel->displayWindowOpening(this->window->getOpening());
        this->prevOpening = this->window->getOpening();
    }
    if (this->systemInformations->getMode() == MANUAL_MODE &&
    this->prevTemperature != this->systemInformations->getTemperature()) {
        this->userPanel->displayTemperature(this->systemInformations->getTemperature());
        this->prevTemperature = this->systemInformations->getTemperature();
    }
}
