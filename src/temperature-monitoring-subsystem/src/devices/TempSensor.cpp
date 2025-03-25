#include <Arduino.h>
#include "devices/TempSensor.hpp"

#define TENSION_ZERO_DEGREES 0.5
#define STEP_VOLT_PER_DEGREE 0.01
#define ADC_VREF 3.3
#define ADC_RESOLUTION 4095.0

TempSensor::TempSensor(int pin) {
    this->pin = pin;
    pinMode(this->pin, INPUT);
}

float TempSensor::getTemperature() {
    int adcVal = analogRead(pin);
    float voltage = adcVal * (ADC_VREF / ADC_RESOLUTION);
    float tempC = (voltage - TENSION_ZERO_DEGREES) / STEP_VOLT_PER_DEGREE;
    return tempC;
}
