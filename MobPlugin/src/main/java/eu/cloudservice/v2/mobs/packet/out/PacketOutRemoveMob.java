package eu.cloudservice.v2.mobs.packet.out;

import de.dytanic.cloudnet.lib.network.protocol.packet.Packet;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketRC;
import de.dytanic.cloudnet.lib.utility.document.Document;
import eu.cloudnetservice.v2.mobs.ServerMob;

public class PacketOutRemoveMob extends Packet {

    public PacketOutRemoveMob(ServerMob mob) {
        super(PacketRC.SERVER_SELECTORS + 4, new Document("mob", mob));
    }
}
