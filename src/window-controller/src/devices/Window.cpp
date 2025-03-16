#include "devices/Window.hpp"
#include <Arduino.h>

Window::Window(int pin) {
    this->servoMotor = new ServoMotor(pin);
}

void Window::setOpening(int opening) {
    int angle = map(opening, 0, 100, 0, 90);
    this->servoMotor->setPosition(angle);
}

int Window::getOpening() {
    return map(this->servoMotor->getPosition(), 0, 90, 0, 100);
}