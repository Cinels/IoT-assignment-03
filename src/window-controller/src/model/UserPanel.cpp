#include "model/UserPanel.hpp"
#include <Arduino.h>
#include "tasks/WindowTask.hpp"

#define POTENTIOMETER_PIN A0
#define BUTTON_PIN 4

UserPanel::UserPanel() {
    this->button = new Button(BUTTON_PIN);
    this->potentiometer = new Potentiometer(POTENTIOMETER_PIN);
    this->display = new Display();
    this->display->init();
    this->button->attachInterrupt();
}

int UserPanel::getWindowManualOpening() {
    return this->potentiometer->mappedValue(0, 100);
}

void UserPanel::displayWindowOpening(int opening) {
    this->display->setText(1, 0, "Window opening: ");
    this->display->setText(1, 16, String(opening));
    this->display->setText(1, 19, "%");
}

void UserPanel::displayMode(int mode) {
    this->display->setText(2, 0, "Mode: ");
    if (mode == AUTOMATIC_MODE) {
        this->display->setText(2, 6, "Automatic");
    } else {
        this->display->setText(2, 6, "Manual");
    }
}

void UserPanel::displayTemperature(float temperature) {
    this->display->setText(3, 0, "Temperature: ");
    this->display->setText(3, 13, String(temperature));
    this->display->setText(3, 18, "°C");
}
