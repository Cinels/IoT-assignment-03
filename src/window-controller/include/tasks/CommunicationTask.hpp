#ifndef __COMMUNICATION_TASK__
#define __COMMUNICATION_TASK__

#include "Task.hpp"

/// @brief 
class CommunicationTask : public Task {
private:
    /* data */
public:
    /// @brief 
    CommunicationTask();

    /// @brief 
    void tick();
};

#endif