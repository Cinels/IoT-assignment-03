#ifndef __TASK__
#define __TASK__

/// @brief Abstract task.
class Task {
private:
    int period;
    bool running = false;
public:
    /// @brief Initialize the task setting the base period.
    /// @param period the base period every which the task must be performed.
    void setPeriod(int period);
    
    /// @brief This method starts the task.
    void startTask();

    /// @brief This method stops the task.
    void stopTask();

    /// @brief Returns if the task is running or not.
    /// @return true if the task is running, false otherwise.
    bool isRunning();

    /// @brief The task to execute each period.
    virtual void tick() = 0;
};

#endif
