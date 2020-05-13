package eu.cloudservice.v2.mobs.out;

import de.dytanic.cloudnet.lib.network.protocol.packet.Packet;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketRC;
import de.dytanic.cloudnet.lib.utility.document.Document;
import eu.cloudnetservice.v2.mobs.MobConfig;
import eu.cloudnetservice.v2.mobs.ServerMob;

import java.util.Map;
import java.util.UUID;


public final class PacketOutMobSelector extends Packet {

    public PacketOutMobSelector(MobConfig mobConfig, Map<UUID, ServerMob> mobs) {
        super(PacketRC.SERVER_SELECTORS + 2, new Document("mobConfig", mobConfig).append("mobs", mobs));
    }
}
