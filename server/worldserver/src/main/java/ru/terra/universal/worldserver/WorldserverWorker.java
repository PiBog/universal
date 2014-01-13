package ru.terra.universal.worldserver;

import org.apache.log4j.Logger;
import ru.terra.universal.interserver.network.netty.InterserverWorker;
import ru.terra.universal.shared.constants.OpCodes;
import ru.terra.universal.shared.constants.OpCodes.InterServer;
import ru.terra.universal.shared.entity.PlayerInfo;
import ru.terra.universal.shared.packet.AbstractPacket;
import ru.terra.universal.shared.packet.client.MovementPacket;
import ru.terra.universal.shared.packet.interserver.CharInWorldPacket;
import ru.terra.universal.shared.packet.interserver.HelloPacket;
import ru.terra.universal.shared.packet.interserver.RegisterPacket;
import ru.terra.universal.shared.packet.server.WorldStatePacket;
import ru.terra.universal.worldserver.world.WorldWorker;

public class WorldserverWorker extends InterserverWorker {

    private Logger log = Logger.getLogger(this.getClass());
    private WorldWorker worldWorker = WorldWorker.getInstance();


    @Override
    public void disconnectedFromChannel() {
        log.info("Frontend disconnected us");
    }

    @Override
    public void acceptPacket(AbstractPacket packet) {
        switch (packet.getOpCode()) {
            case InterServer.ISMSG_HELLO: {
                HelloPacket helloPacket = new HelloPacket();
                helloPacket.setHello("world server");
                RegisterPacket registerPacket = new RegisterPacket();
                registerPacket.setStartRange(OpCodes.WorldOpcodeStart);
                registerPacket.setEndRange(OpCodes.WorldOpcodeEnd);
                networkManager.sendPacket(helloPacket);
                networkManager.sendPacket(registerPacket);
            }
            break;
            case InterServer.ISMSG_CHAR_IN_WORLD: {
                log.info("Character " + packet.getSender() + " is in world now!");
                PlayerInfo playerInfo = ((CharInWorldPacket) packet).getPlayerInfo();
                worldWorker.getPlayers().add(playerInfo);
                log.info("Character " + playerInfo + " is in world now");
                WorldStatePacket worldStatePacket = new WorldStatePacket(worldWorker.getEntities(), worldWorker.getPlayers());
                worldStatePacket.setSender(packet.getSender());
                networkManager.sendPacket(worldStatePacket);
            }
            break;
            case InterServer.ISMSG_UNREG_CHAR: {
                log.info("Unregistering char with uid = " + packet.getSender());
                PlayerInfo pi = null;
                for (PlayerInfo playerInfo : worldWorker.getPlayers()) {
                    if (Long.parseLong(playerInfo.getUID()) == packet.getSender())
                        pi = playerInfo;
                }
                if (pi != null)
                    worldWorker.getPlayers().remove(pi);
            }
            break;
            case OpCodes.Client.Movement.CMSG_MOVE: {
                worldWorker.playerMove((MovementPacket) packet);
            }
            break;
            case OpCodes.Client.Movement.CMSG_MOVE_START: {
            }
            break;
            case OpCodes.Client.Movement.CMSG_MOVE_STOP: {
            }
            break;
        }
    }

}
