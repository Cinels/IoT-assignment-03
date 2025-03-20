#ifndef __WINDOW_TASK__
#define __WINDOW_TASK__

#include "Task.hpp"
#include "model/UserPanel.hpp"
#include "model/Window.hpp"
#include "model/SystemInformations.hpp"

/// @brief Enumerates the possible modes of the window system.
typedef enum WindowMode {MANUAL_MODE, AUTOMATIC_MODE} WindowMode;

/// @brief Task that controls the window.
class WindowTask : public Task {
private:
    WindowMode mode;
    UserPanel* userPanel;
    Window* window;
    SystemInformations* systemInformations;
    int prevOpening;
    float prevTemperature;
    void switchMode();
public:
    /// @brief Creates a new WindowTask.
    /// @param userPanel the user panel.
    /// @param window the window.
    /// @param systemInformations the system informations.
    WindowTask(UserPanel* userPanel, Window* window, SystemInformations* systemInformations);

    void tick();
};

#endif