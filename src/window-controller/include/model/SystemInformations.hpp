#ifndef __SYSTEM_INFORMATIONS__
#define __SYSTEM_INFORMATIONS__

/// @brief Class that represents the Control Unit informations.
class SystemInformations {
private:
    float temperature;
    int windowOpening;
    bool switchMode;
public:
    /// @brief Create a new SystemInformations object.
    SystemInformations();

    /// @brief Sets the temperature.
    /// @param temperature the temperature to set.
    void setTemperature(float temperature);

    /// @brief Sets the window opening percentage.
    /// @param windowOpening the window opening percentage to set.
    void setWindowOpening(int windowOpening);

    /// @brief Triggers the mode switch.
    void doSwitchMode();

    /// @brief Resets the mode switch.
    void modeSwitched();

    /// @brief Returns the temperature value.
    /// @return the temperature value.
    float getTemperature();

    /// @brief Returns the window opening percentage.
    /// @return the window opening percentage.
    int getWindowOpening();

    /// @brief Returns if the mode must switch.
    /// @return true if the mode must switch, false otherwise.
    bool getSwitchMode();
};

#endif