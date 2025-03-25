#include <Arduino.h>
#include "tasks/taskUtils.hpp"
#include "tasks/TemperatureTask.hpp"

TaskHandle_t* taskHandler;
TemperatureTask* task;

void setup() {
  Serial.begin(9600);
  task = new TemperatureTask(500);
  xTaskCreate(startTask, "TemperatureTask", 2048, task, 1, taskHandler);
}

void loop() {
  delay(1000);
}
