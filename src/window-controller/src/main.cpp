#include <Arduino.h>
#include "tasks/Scheduler.hpp"
#include "tasks/CommunicationTask.hpp"
#include "tasks/WindowTask.hpp"
#include "model/SystemInformations.hpp"
#include "model/Window.hpp"
#include "model/UserPanel.hpp"

#define WINDOW_PIN 3
#define SCHEDULER_INTERVAL 150
#define WINDOW_TASK_INTERVAL 150
#define COMMUNICATION_TASK_INTERVAL 300

SystemInformations* systemInformations;
Window* window;
UserPanel* userPanel;
Scheduler* scheduler;
WindowTask* windowTask;
CommunicationTask* communicationTask;

void setup() {
  // create instances
  systemInformations = new SystemInformations();
  window = new Window(WINDOW_PIN);
  userPanel = new UserPanel(systemInformations);
  scheduler = new Scheduler();
  windowTask = new WindowTask(userPanel, window, systemInformations);
  communicationTask = new CommunicationTask(systemInformations, window);

  // initialize instances
  scheduler->init(SCHEDULER_INTERVAL);
  windowTask->init(WINDOW_TASK_INTERVAL);
  communicationTask->init(COMMUNICATION_TASK_INTERVAL);

  // add tasks to scheduler
  scheduler->addTask(windowTask);
  scheduler->addTask(communicationTask);
}

void loop() {
  scheduler->schedule();
}
