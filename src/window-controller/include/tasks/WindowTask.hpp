#ifndef __WINDOW_TASK__
#define __WINDOW_TASK__

#include "Task.hpp"

/// @brief 
class WindowTask : public Task {
private:
public:
    /// @brief 
    WindowTask();

    /// @brief 
    void tick();
};

#endif