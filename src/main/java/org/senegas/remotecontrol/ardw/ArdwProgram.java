package org.senegas.remotecontrol.ardw;

import org.llschall.ardwloop.IArdwProgram;
import org.llschall.ardwloop.structure.StructureTimer;
import org.llschall.ardwloop.value.SerialData;
import org.senegas.remotecontrol.model.RemoteButton;

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

        // if there is no command, to be process, wait a little and return
        if (CommandQueue.get().isEmpty()) {
            StructureTimer.get().delayMs(99);
            return new SerialData();
        }

        // if there is one or more command, only process the first one,
        // and leave any other commands in the queue for the next calls
        RemoteButton button = CommandQueue.get().retrieveCommand();

        // find the code associated to the button, according to the mapping
        // that the logic in the Arduino will watch as well
        int code = mapCode(button);

        if (code==-1) {
            // ignore for now
            System.err.println("Unsupported button: "+button);
            return new SerialData();
        }

        // wrap the code in the message that will be sent to the Arduino board
        return new SerialData(code);
    }

    int mapCode(RemoteButton button) {
        switch (button) {
            case ON -> {
                return 7 ;}
            case OFF -> {return 8;}
        }
        return -1;
    }

}
