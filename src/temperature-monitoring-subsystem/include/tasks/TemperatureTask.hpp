#ifndef __TEMPERATURE_TASK__
#define __TEMPERATURE_TASK__

#include "tasks/Task.hpp"
#include "devices/TempSensor.hpp"
#include "devices/Led.hpp"

/// @brief Task to check the temperature of an object.
class TemperatureTask : public Task {
private:
    TempSensor* temperatureSensor;
    Led* errorLed;
    Led* noErrorLed;
    float temperature;
    void networkOk();
    void networkNotOk();
    void setupConnection();
    void callback(char* topic, byte* payload, unsigned int length);
public:
    /// @brief Task constructor, it creates a task with a base period.
    /// @param period the base period every which the task must be performed.
    TemperatureTask(int period);
    
    /// @brief The task to execute each period.
    void tick();
};


#endif
