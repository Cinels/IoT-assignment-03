#define EI_ARDUINO_INTERRUPTED_PIN
#include <EnableInterrupt.h>
#include "devices/Button.hpp"
#include <Arduino.h>

static SystemInformations* sysInfo;

Button::Button(int pin) {
    this->pin = pin;
    pinMode(this->pin, INPUT);
}

void Button::attachInterrupt(SystemInformations* systemInformations) {
    sysInfo = systemInformations;
    enableInterrupt(this->pin, buttonHandler, FALLING);
}

void Button::buttonHandler() {
    if (sysInfo->getMode() == AUTOMATIC_MODE) sysInfo->switchMode(USER_MODE);
    else sysInfo->switchMode(AUTOMATIC_MODE);
}
