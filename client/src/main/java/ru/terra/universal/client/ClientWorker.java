package ru.terra.universal.client;

import ru.terra.universal.interserver.network.netty.InterserverWorker;
import ru.terra.universal.server.shared.constants.OpCodes;
import ru.terra.universal.server.shared.packet.Packet;
import ru.terra.universal.server.shared.packet.client.LoginPacket;

/**
 * User: Vadim Korostelev
 * Date: 30.08.13
 * Time: 16:15
 */
public class ClientWorker extends InterserverWorker {
    @Override
    public void disconnectedFromChannel() {
    }

    @Override
    public void acceptPacket(Packet packet) {
        switch (packet.getOpCode()) {
            case OpCodes.Server.SMSG_OK: {
                getChannel().write(new LoginPacket(packet.getSender()));
            }
            break;
        }
    }
}