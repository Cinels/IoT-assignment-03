#ifndef __BUTTON__
#define __BUTTON__

/// @brief Class to use buttons by polling
class Button {
private:
    int pin;
public:
    /// @brief Button constructor, it creates a button and sets the pin in INPUT mode.
    /// @param pin the input pin for the button.
    Button(int pin);
    
    /// @brief attaches an interrupt handler to the button.
    void attachInterrupt();
};

/// @brief Handler for the button interrupt.
static void buttonHandler();

#endif
