#include "tasks/Task.hpp"
#include "Arduino.h"

void Task::setPeriod(int period) {
    this->period = period;  
}

void Task::startTask() {
    this->running = true;
    while(this->isRunning()) {
        this->tick();
        sleep(this->running);
    }
}

void Task::stopTask() {
    this->running = false;
}

bool Task::isRunning() {
    return this->running;
}
