#include "devices/Potentiometer.hpp"
#include <Arduino.h>

Potentiometer::Potentiometer(int pin) {
    this->pin = pin;
}

int Potentiometer::readValue() {
    return analogRead(this->pin);
}

int Potentiometer::mappedValue(int min, int max) {
    return map(this->readValue(), 0, 1023, min, max + 1);
}
