package eu.cloudservice.cloudmob.packet.out;

import de.dytanic.cloudnet.lib.network.protocol.packet.Packet;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketRC;
import de.dytanic.cloudnet.lib.utility.document.Document;
import eu.cloudnetservice.cloudmob.ServerMob;

public class PacketOutRemoveMob extends Packet {

    public PacketOutRemoveMob(ServerMob mob) {
        super(PacketRC.SERVER_SELECTORS + 4, new Document("mob", mob));
    }
}
