
/* https://github.com/Arduino-IRremote/Arduino-IRremote
 * Version 4.4.1
 */

#include <IRremote.h>

/* https://llschall.github.io/ardwloop
 * Version 0.3.2
 */
#include <Ardwloop.h>


int INFRA_RED_PIN = 8;

void process(int cmd);

void setup() {

  // Here the baud value should be set to the same value as on the Java side
  ardw_setup(BAUD_9600);

  IrSender.begin(INFRA_RED_PIN, false, 0);
}

void loop() {
  ardw_loop();

  // Retreive the value sent by the Java program
  int cmd = ardw_r()->a.v;
  if (cmd != 1) process(cmd);

  delay(999);
}

// Sends the signal matching the command via the infra red led
void process(int cmd) {

  int repeats = 1;

  if (cmd == 7) {
    IrSender.sendRC5(0x1E, 0x7, repeats);
  }
  if (cmd == 8) {
    IrSender.sendRC5(0x1E, 0x8, repeats);
  }
}