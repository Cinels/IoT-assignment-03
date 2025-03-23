#ifndef __SYSTEM_INFORMATIONS__
#define __SYSTEM_INFORMATIONS__

/// @brief Enumerates the possible modes of the window system.
typedef enum WindowMode {AUTOMATIC_MODE, MANUAL_MODE, DASHBOARD_MODE} WindowMode;

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
    void setWindowOpeningGoal(int windowOpening);

    /// @brief Triggers the mode switch.
    void switchMode(bool isFromDashboard);

    /// @brief Returns the temperature value.
    /// @return the temperature value.
    float getTemperature();

    /// @brief Returns the window opening percentage goal.
    /// @return the window opening percentage goal.
    int getWindowOpeningGoal();

    /// @brief Returns the current mode of the system.
    /// @return the mode of the system.
    WindowMode getMode();
};

#endif