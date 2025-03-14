#ifndef __TASK__
#define __TASK__

/// @brief Abstract task.
class Task {
private:
    int period;
public:
    /// @brief Initialize the task setting the base period.
    /// @param period the base period every which the task must be performed.
    void setPeriod(int period);
    
    /// @brief This method starts the task.
    void startTask();

    /// @brief The task to execute each period.
    virtual void tick() = 0;

    /// @brief Returns the period of the task.a
    /// @return the period of the task.
    int getPeriod();
};

#endif
