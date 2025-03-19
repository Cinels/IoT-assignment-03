#ifndef __COMMUNICATION_TASK__
#define __COMMUNICATION_TASK__

#include "Task.hpp"

/// @brief Task that handles communication with the Control Unit.
class CommunicationTask : public Task {
private:
    /* data */
public:
    /// @brief 
    CommunicationTask();

    void tick();
};

#endif
