#include "tasks/WindowTask.hpp"

WindowTask::WindowTask(UserPanel *userPanel, Window *window, SystemInformations *systemInformations) {
    this->userPanel = userPanel;
    this->window = window;
    this->systemInformations = systemInformations;
    this->prevOpening = -1;
    this->prevManualOpening = -1;
    this->prevTemperature = 100.0;
    this->prevMode = MANUAL_MODE;
}

void WindowTask::tick() {
    if (this->systemInformations->getMode() == DASHBOARD_MODE && this->prevManualOpening != this->userPanel->getWindowManualOpening()) {
        this->systemInformations->switchMode(MANUAL_MODE);
        this->prevManualOpening = this->userPanel->getWindowManualOpening();
    }
    this->window->setOpening(this->systemInformations->getMode() == MANUAL_MODE ?
        this->userPanel->getWindowManualOpening() :
        this->systemInformations->getWindowOpeningGoal()
    );
    this->displayData();
}

void WindowTask::displayData() {
    if (this->prevOpening != this->window->getOpening()) {
        this->userPanel->displayWindowOpening(this->window->getOpening());
        this->prevOpening = this->window->getOpening();
    }
    if (!(this->systemInformations->getMode() == AUTOMATIC_MODE) &&
    (this->prevTemperature != this->systemInformations->getTemperature() ||
    this->prevMode != this->systemInformations->getMode())) {
        this->userPanel->displayTemperature(this->systemInformations->getTemperature());
        this->prevTemperature = this->systemInformations->getTemperature();
    }
    if (this->prevMode != this->systemInformations->getMode()) {
        this->userPanel->displayMode(this->systemInformations->getMode());
        this->prevMode = this->systemInformations->getMode();
    }
}
