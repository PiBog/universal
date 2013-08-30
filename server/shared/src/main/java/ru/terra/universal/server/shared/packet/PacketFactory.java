package ru.terra.universal.server.shared.packet;

import ru.terra.universal.server.shared.constants.OpCodes;
import ru.terra.universal.server.shared.constants.OpCodes.InterServer;
import ru.terra.universal.server.shared.packet.client.LoginPacket;
import ru.terra.universal.server.shared.packet.interserver.HelloPacket;
import ru.terra.universal.server.shared.packet.interserver.RegisterPacket;
import ru.terra.universal.server.shared.packet.server.OkPacket;

public class PacketFactory {
    private static PacketFactory instance = new PacketFactory();

    private PacketFactory() {
    }

    public static PacketFactory getInstance() {
        return instance;
    }

    public Packet getPacket(int opCode, long sender) {
        switch (opCode) {
            case InterServer.ISMSG_HELLO: {
                return new HelloPacket(sender);
            }
            case InterServer.ISMSG_REG: {
                return new RegisterPacket(sender);
            }
            case InterServer.ISMSG_UNREG: {
            }
            case OpCodes.Server.SMSG_OK: {
                return new OkPacket(sender);
            }
            case OpCodes.Client.Login.CMSG_LOGIN: {
                return new LoginPacket(sender);
            }
        }
        return null;
    }
}
