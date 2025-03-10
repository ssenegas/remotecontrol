package org.senegas.remotecontrol.remote;

import org.llschall.ardwloop.IArdwProgram;
import org.llschall.ardwloop.structure.StructureTimer;
import org.llschall.ardwloop.value.SerialData;
import org.senegas.remotecontrol.model.Command;
import org.senegas.remotecontrol.model.RemoteControlButton;

public class ArdwProgram implements IArdwProgram {

    /*
     * Called when the ardw_setup() method is executed by the Arduino board,
     * typically when the Arduino board runs the setup() method of the Arduino API.
     */
    @Override
    public SerialData ardwSetup(SerialData serialData) {
        return new SerialData();
    }

    /*
     * Called each time the ardw_loop() method is executed by the Arduino board,
     * typically each time the Arduino board runs the loop() method of the Arduino API.
     */
    @Override
    public SerialData ardwLoop(SerialData serialData) {
        CommandQueue queue = CommandQueue.INSTANCE;

        // if there is no command to be process, wait a little and return
        if (queue.isEmpty()) {
            StructureTimer.get().delayMs(99);
            return new SerialData();
        }

        // if there is one or more command, only process the first one,
        // and leave any other commands in the queue for the next calls
        Command command = queue.retrieveCommand();
        System.out.println("Retrieved command for button: " + command.getButton().getDisplayName());

        // find the code associated to the button, according to the mapping
        // that the logic in the Arduino will watch as well
        int code = mapCode(command.getButton());

        if (code == -1) {
            // ignore for now
            System.err.println("Unsupported button: " + command.getButton().getDisplayName());
            return new SerialData();
        }

        // wrap the code in the message that will be sent to the Arduino board
        return new SerialData(
                1, // ardw_r()->a.v
                code // ardw_r()->a.w
        );
    }

    // the mapping must match the process() method in the Arduino code
    int mapCode(RemoteControlButton button) {
        switch (button) {
            case ON -> {
                return 7;
            }
            case OFF -> {
                return 8;
            }
        }
        return -1;
    }

}
