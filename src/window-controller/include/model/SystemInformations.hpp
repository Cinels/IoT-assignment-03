#ifndef __SYSTEM_INFORMATIONS__
#define __SYSTEM_INFORMATIONS__

/// @brief Enumerates the possible modes of the window system.
typedef enum WindowMode {MANUAL_MODE, AUTOMATIC_MODE} WindowMode;

/// @brief Class that represents the system informations.
class SystemInformations {
private:
    float temperature;
    int windowOpening;
    WindowMode mode;
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
    void switchMode();

    /// @brief Returns the temperature value.
    /// @return the temperature value.
    float getTemperature();

    /// @brief Returns the window opening percentage.
    /// @return the window opening percentage.
    int getWindowOpening();

    /// @brief Returns the current mode of the system.
    /// @return the mode of the system {AUTOMATIC; MANUAL}.
    WindowMode getMode();
};

#endif