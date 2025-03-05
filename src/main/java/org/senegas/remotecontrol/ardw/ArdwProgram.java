package org.senegas.remotecontrol.ardw;

import org.llschall.ardwloop.IArdwProgram;
import org.llschall.ardwloop.structure.StructureTimer;
import org.llschall.ardwloop.value.SerialData;
import org.senegas.remotecontrol.model.RemoteButton;

public class ArdwProgram implements IArdwProgram {

    @Override
    public SerialData ardwSetup(SerialData serialData) {
        return new SerialData();
    }

    @Override
    public SerialData ardwLoop(SerialData serialData) {

        if (CommandQueue.get().isEmpty()) {
            StructureTimer.get().delayMs(99);
            return new SerialData();
        }

        RemoteButton button = CommandQueue.get().retrieveCommand();
        int code = mapCode(button);

        if (code==-1) {
            // ignore for now
            System.err.println("Unsupported button: "+button);
            return new SerialData();
        }

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
