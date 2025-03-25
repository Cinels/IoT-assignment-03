#include <Arduino.h>
#include "tasks/taskUtils.hpp"
#include "tasks/Task.hpp"

void startTask(void *parameter) {
    Task* task = ((Task*)parameter);
    while(true) {
        task->tick();
        vTaskDelay(task->getPeriod() / portTICK_PERIOD_MS);
    }
}