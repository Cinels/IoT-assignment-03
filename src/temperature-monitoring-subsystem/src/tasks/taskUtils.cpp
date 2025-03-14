#include <Arduino.h>
#include "tasks/taskUtils.hpp"
#include "tasks/Task.hpp"

void taskLoop(void *parameter) {
    auto task = ((Task*)parameter);
    while(true) {
        task->tick();
        delay(task->getPeriod());
    }
}