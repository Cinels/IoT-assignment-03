#include "tasks/Task.hpp"
#include "tasks/taskUtils.hpp"

#define BUFFER_SIZE 2048
#define PRIORITY 1

void Task::setPeriod(int period) {
    this->period = period;
}

void Task::startTask() {
    xTaskCreate(taskLoop, "TemperatureTask", BUFFER_SIZE, (void*)this, PRIORITY, this->taskHandler);
}

int Task::getPeriod() {
    return this->period;
}
