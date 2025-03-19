#include "tasks/WindowTask.hpp"

WindowTask::WindowTask(UserPanel *userPanel, Window *window, SystemInformations *systemInformations) {
    this->userPanel = userPanel;
    this->window = window;
    this->systemInformations = systemInformations;
    this->mode = MANUAL_MODE;
    this->prevOpening = -1;
    this->prevTemperature = 100.0;
    this->switchMode();
}

void WindowTask::tick() {
    if (this->systemInformations->getSwitchMode()) this->switchMode();
    this->window->setOpening(this->mode == AUTOMATIC_MODE ?
        this->systemInformations->getWindowOpening() :
        this->userPanel->getWindowManualOpening());
    if (this->prevOpening != this->window->getOpening()) {
        this->userPanel->displayWindowOpening(this->window->getOpening());
        this->prevOpening = this->window->getOpening();
    }
    if (this->mode == MANUAL_MODE && this->prevTemperature != this->systemInformations->getTemperature()) {
        this->userPanel->displayTemperature(this->systemInformations->getTemperature());
        this->prevTemperature = this->systemInformations->getTemperature();
    }
}

void WindowTask::switchMode() {
    if (this->mode == AUTOMATIC_MODE) this->mode = MANUAL_MODE;
    else this->mode = AUTOMATIC_MODE;
    this->systemInformations->modeSwitched();
    this->userPanel->displayMode(this->mode);
}
