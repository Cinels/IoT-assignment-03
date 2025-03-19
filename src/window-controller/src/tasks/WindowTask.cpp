#include "tasks/WindowTask.hpp"

WindowTask::WindowTask(UserPanel *userPanel, Window *window, SystemInformations *systemInformations) {
    this->userPanel = userPanel;
    this->window = window;
    this->systemInformations = systemInformations;
    this->mode = AUTOMATIC_MODE;
}

void WindowTask::tick() {
    if (this->systemInformations->getSwitchMode()) this->switchMode();
    switch (this->mode){
        case AUTOMATIC_MODE: this->automaticMode(); break;
        case MANUAL_MODE: this->manualMode(); break;
        default: break;
    }    
}

void WindowTask::manualMode() {
    this->window->setOpening(this->userPanel->getWindowManualOpening());
    this->userPanel->displayWindowOpening(this->window->getOpening());
    this->userPanel->displayMode(this->mode);
    this->userPanel->displayTemperature(this->systemInformations->getTemperature());
}

void WindowTask::automaticMode() {
    this->window->setOpening(this->systemInformations->getWindowOpening());
    this->userPanel->displayWindowOpening(this->window->getOpening());
    this->userPanel->displayMode(this->mode);
}

void WindowTask::switchMode() {
    if (this->mode == AUTOMATIC_MODE) this->mode = MANUAL_MODE;
    else this->mode = AUTOMATIC_MODE;
}
