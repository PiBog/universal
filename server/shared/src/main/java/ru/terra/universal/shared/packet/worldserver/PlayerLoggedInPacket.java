package ru.terra.universal.shared.packet.worldserver;

import ru.terra.universal.shared.annoations.Packet;
import ru.terra.universal.shared.constants.OpCodes;

/**
 * Created by Vadim_Korostelev on 11/15/2016.
 */
@Packet(opCode = OpCodes.WorldServer.PLAYER_IN)
public class PlayerLoggedInPacket extends AbstractPlayerLogPacket {

    public PlayerLoggedInPacket(Long sender, Long guid) {
        super(sender, guid);
    }
}
