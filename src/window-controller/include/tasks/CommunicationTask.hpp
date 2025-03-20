#ifndef __COMMUNICATION_TASK__
#define __COMMUNICATION_TASK__

#include "Task.hpp"
#include "model/SystemInformations.hpp"

/// @brief Task that handles communication with the Control Unit.
class CommunicationTask : public Task {
private:
    SystemInformations* systemInformations;
public:
    /// @brief Creates a new Communication Task.
    /// @param systemInformations the system informations.
    CommunicationTask(SystemInformations* systemInformations);

    void tick();
};

#endif
