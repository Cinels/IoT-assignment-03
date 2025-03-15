#include <Arduino.h>
#include "devices/TempSensor.hpp"

#define TENSION_ZERO_DEGREES 0.5
#define STEP_VOLT_PER_DEGREE 0.01
#define ANALOGUE_PRECISION 0.0032

TempSensor::TempSensor(int pin) {
    this->pin = pin;
    pinMode(this->pin, INPUT);
}

float TempSensor::getTemperature() {
    int value = analogRead(pin);
    float valueInCelsius = ((value * ANALOGUE_PRECISION) - TENSION_ZERO_DEGREES) / STEP_VOLT_PER_DEGREE;
    return valueInCelsius;
}
