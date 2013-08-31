package ru.terra.universal.worldserver;

import org.apache.log4j.Logger;
import ru.terra.universal.interserver.network.NetworkManager;
import ru.terra.universal.interserver.network.netty.InterserverWorker;
import ru.terra.universal.shared.constants.OpCodes;
import ru.terra.universal.shared.constants.OpCodes.InterServer;
import ru.terra.universal.shared.packet.AbstractPacket;
import ru.terra.universal.shared.packet.interserver.HelloPacket;
import ru.terra.universal.shared.packet.interserver.RegisterPacket;

public class WorldserverWorker extends InterserverWorker {

    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void disconnectedFromChannel() {
        log.info("Frontend disconnected us");
    }

    @Override
    public void acceptPacket(AbstractPacket packet) {
        switch (packet.getOpCode()) {
            case InterServer.ISMSG_HELLO: {
                HelloPacket helloPacket = new HelloPacket(0);
                helloPacket.setHello("world server");
                RegisterPacket registerPacket = new RegisterPacket(0);
                registerPacket.setStartRange(OpCodes.WorldOpcodeStart);
                registerPacket.setEndRange(OpCodes.WorldOpcodeEnd);
                NetworkManager.getInstance().getChannel().write(helloPacket);
                NetworkManager.getInstance().getChannel().write(registerPacket);
            }
            break;
        }
    }

}
