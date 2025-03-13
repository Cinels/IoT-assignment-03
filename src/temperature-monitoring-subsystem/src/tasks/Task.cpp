#include "tasks/Task.hpp"

void Task::setPeriod(int period) {
    this->myPeriod = period;  
}

void Task::startTask() {
    this->taskRunning = true;
}

void Task::stopTask() {
    this->taskRunning = false;
}

bool Task::isRunning() {
    return this->taskRunning;
}
