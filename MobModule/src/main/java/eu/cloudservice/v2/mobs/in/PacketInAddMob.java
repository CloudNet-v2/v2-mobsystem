package eu.cloudservice.v2.mobs.in;

import de.dytanic.cloudnet.lib.network.protocol.packet.PacketInHandler;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketSender;
import de.dytanic.cloudnet.lib.utility.document.Document;
import eu.cloudnetservice.v2.mobs.ServerMob;
import eu.cloudservice.v2.mobs.MobModule;

public class PacketInAddMob extends PacketInHandler {

    public void handleInput(Document packet, PacketSender packetSender) {
        ServerMob serverMob = packet.getObject("mob", ServerMob.TYPE);
        MobModule.getInstance().getMobDatabase().append(serverMob);
    }
}
