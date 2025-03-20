#include <Arduino.h>
#include "tasks/Scheduler.hpp"
#include "tasks/CommunicationTask.hpp"
#include "tasks/WindowTask.hpp"
#include "model/SystemInformations.hpp"
#include "model/Window.hpp"
#include "model/UserPanel.hpp"

SystemInformations* systemInformations;
Window* window;
UserPanel* userPanel;
Scheduler* scheduler;
WindowTask* windowTask;
CommunicationTask* communicationTask;

void setup() {
  Serial.begin(9600);
  systemInformations = new SystemInformations();
  window = new Window(3);
  userPanel = new UserPanel(systemInformations);
  scheduler = new Scheduler();
  windowTask = new WindowTask(userPanel, window, systemInformations);
  communicationTask = new CommunicationTask(systemInformations);

  scheduler->init(150);
  windowTask->init(150);
  communicationTask->init(300);

  scheduler->addTask(windowTask);
  scheduler->addTask(communicationTask);

  Serial.println("Setup done!");
}

void loop() {
  scheduler->schedule();
}
