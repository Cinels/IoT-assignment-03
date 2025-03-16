#ifndef __WINDOW__
#define __WINDOW__

#include "ServoMotor.hpp"

#define WINDOW_OPEN (100)
#define WINDOW_CLOSED (0)

/// @brief Class to manage a window.
class Window {
private:
    ServoMotor* servoMotor;
public:
    /// @brief Window constructor, it creates an automatic window.
    /// @param pin the pin where the window is connected to be controlled.
    Window(int pin);
    
    /// @brief Opens the window to the given percentage.
    /// @param opening the opening percentage [0, 100] of the window.
    void setOpening(int opening);

    /// @brief Returns the opening percentage of the window.
    /// @return the opening percentage of the window. 
    int getOpening();
};

#endif
