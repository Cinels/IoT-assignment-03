#include "model/UserPanel.hpp"
#include <Arduino.h>
#include "tasks/WindowTask.hpp"

#define POTENTIOMETER_PIN A0
#define BUTTON_PIN 4

UserPanel::UserPanel(SystemInformations* systemInformations) {
    this->button = new Button(BUTTON_PIN);
    this->potentiometer = new Potentiometer(POTENTIOMETER_PIN);
    this->display = new Display();
    this->display->init();
    this->button->attachInterrupt(systemInformations);
    this->setupDisplay();
}

int UserPanel::getWindowManualOpening() {
    return this->potentiometer->mappedValue(0, 100);
}

void UserPanel::setupDisplay() {
    this->display->setText(0, 1, "Window opening: ");
    this->display->setText(19, 1, "%");

    this->display->setText(0, 2, "Mode: ");
}

void UserPanel::displayWindowOpening(int opening) {
    this->display->setText(16, 1, "   ");
    this->display->setText(16, 1, String(opening));
}

void UserPanel::displayMode(int mode) {
    this->display->setText(6, 2, "         ");
    if (mode == AUTOMATIC_MODE) {
        this->display->setText(6, 2, "Automatic");
        this->display->setText(0, 3, "                    ");
    } else {
        this->display->setText(6, 2, "Manual");
        this->display->setText(0, 3, "Temperature: ");
        this->display->setText(19, 3, "C");    
    }
}

void UserPanel::displayTemperature(float temperature) {
    this->display->setText(13, 3, "     ");
    this->display->setText(13, 3, String(temperature));
}
