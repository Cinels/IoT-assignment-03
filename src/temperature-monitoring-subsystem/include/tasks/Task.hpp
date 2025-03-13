#ifndef __TASK__
#define __TASK__

/// @brief Abstract task.
class Task {
private:  
    int myPeriod;
    bool taskRunning = false;
public:
    /// @brief Initialize the task setting the base period.
    /// @param period the base period every which the task must be performed.
    virtual void setPeriod(int period);
    
    /// @brief this method starts the task.
    virtual void startTask();

    /// @brief this method stops the task.
    virtual void stopTask();

    /// @brief Returns if the task is running or not.
    /// @return true if the task is running, false otherwise.
    virtual bool isRunning();
};

#endif
