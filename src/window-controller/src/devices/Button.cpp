#define EI_ARDUINO_INTERRUPTED_PIN
#include <EnableInterrupt.h>
#include "devices/Button.hpp"
#include <Arduino.h>

Button::Button(int pin) {
    this->pin = pin;
    pinMode(this->pin, INPUT);
}

void Button::attachInterrupt() {
    enableInterrupt(this->pin, interruptHandler, FALLING);
}

static void interruptHandler() {

}
