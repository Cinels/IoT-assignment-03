#ifndef __USER_PANEL__
#define __USER_PANEL__

#include "devices/Button.hpp"
#include "devices/Display.hpp"
#include "devices/Potentiometer.hpp"

/// @brief Class that represents the user panel.
class UserPanel{
private:
    Button* button;
    Display* display;
    Potentiometer* potentiometer;
public:
    /// @brief Constructor of the class.
    UserPanel();
    
    /// @brief Returns the value of opening in manual mode.
    /// @return the percentage of opening.
    int getWindowManualOpening();
    
    /// @brief Displays the window opening.
    /// @param opening the percentage of opening.
    void displayWindowOpening(int opening);

    /// @brief Displays the mode.
    /// @param mode the mode to display.
    void displayMode(int mode);

    /// @brief Displays the temperature.
    /// @param temperature the temperature to display.
    void displayTemperature(float temperature);
};

#endif