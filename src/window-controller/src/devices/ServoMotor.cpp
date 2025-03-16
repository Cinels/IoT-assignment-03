#include "devices/ServoMotor.hpp"
#include <Arduino.h>
#include <Servo.h>

#define MOVING_TIME 70.0
#define DEFAULT_POSITION 180

ServoMotor::ServoMotor(int pin) {
    this->pin = pin;
    this->on();
    delay(MOVING_TIME);
    this->motor.write(DEFAULT_POSITION);
    delay(MOVING_TIME);
    this->off();
}

void ServoMotor::on(){
  this->motor.attach(this->pin);
}

void ServoMotor::setPosition(int angle){
  this->on();
  delay(MOVING_TIME);
  this->motor.write(angle);
  delay(MOVING_TIME);
  this->off();
}

void ServoMotor::off(){
  this->motor.detach();    
}

int ServoMotor::getPosition() {
  return this->motor.read();
}