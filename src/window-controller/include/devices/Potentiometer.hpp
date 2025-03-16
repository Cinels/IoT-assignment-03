#ifndef __POTENTIOMETER__
#define __POTENTIOMETER__

/// @brief Class to manage a potentiometer.
class Potentiometer {
private:
    int pin;
public:
    /// @brief Creates a potentiometer in the given pin.
    /// @param pin is the input pin for the potentiometer.
    Potentiometer(int pin);

    /// @brief Returns the value [0, 1023] read from the potentiometer.
    /// @return the value read from the potentiometer.
    int readValue();

    /// @brief Returns the value read from the potentiometer mapped into min and max values.
    /// @param min the minimum value.
    /// @param max the maximum value.
    /// @return the value mapped in [min, max].
    int mappedValue(int min, int max);
};

#endif