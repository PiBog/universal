package ru.terra.universal.shared.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import ru.terra.universal.shared.annoations.Packet;
import ru.terra.universal.shared.constants.OpCodes;
import ru.terra.universal.shared.packet.AbstractPacket;

@Packet(opCode = OpCodes.Client.Char.CSMG_BOOT_ME)
public class BootMePacket extends AbstractPacket {
    @Override
    public void get(ChannelBuffer buffer) {

    }

    @Override
    public void send(ChannelBuffer buffer) {

    }
}
