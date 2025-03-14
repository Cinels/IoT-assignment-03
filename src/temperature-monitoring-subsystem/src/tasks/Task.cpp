#include <Arduino.h>
#include "tasks/Task.hpp"
#include "tasks/taskUtils.hpp"

void Task::setPeriod(int period) {
    this->period = period;
}

void Task::startTask() {
    xTaskCreatePinnedToCore(taskLoop, "MessageTask", 2048, this, 1, NULL, 1);
}

int Task::getPeriod() {
    return this->period;
}
