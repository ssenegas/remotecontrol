#include <Arduino.h>
#include <IRremote.hpp>

class IRRemoteController {
public:
    IRRemoteController(int irPin, uint8_t deviceAddress)
        : _irPin(irPin), _deviceAddress(deviceAddress) {}

    void begin() {
        IrSender.begin(_irPin, ENABLE_LED_FEEDBACK);
    }

    void handleCommand(int command) {
        if (command < 0 || command >= NUM_BUTTONS) {
            Serial.println("Invalid command received");
            return;
        }

        uint8_t rc5Command = buttonToRC5Command(command);
        sendIRSignal(rc5Command);
    }

private:
    static constexpr int NUM_BUTTONS = 21;
    int _irPin;
    uint8_t _deviceAddress;

    void sendIRSignal(uint8_t rc5Command) {
        Serial.print("Sending RC5 signal - Address: ");
        Serial.print(_deviceAddress);
        Serial.print(", Command: ");
        Serial.println(rc5Command);

        IrSender.sendRC5(_deviceAddress, rc5Command, false);
    }

    uint8_t buttonToRC5Command(int buttonIndex) {
        static const uint8_t rc5Commands[NUM_BUTTONS] = {
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
            0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x14
        };
        return rc5Commands[buttonIndex];
    }
};
