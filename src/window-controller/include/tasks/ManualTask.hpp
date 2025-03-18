#ifndef __MANUAL_TASK__
#define __MANUAL_TASK__

#include "Task.hpp"

/// @brief 
class ManualTask : public Task {
private:
    /* data */
public:
    /// @brief 
    ManualTask();

    /// @brief 
    void tick();
};

#endif