package eu.cloudservice.cloudmob.packet.in;

import com.google.gson.reflect.TypeToken;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.network.packet.PacketInHandlerDefault;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketSender;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.dytanic.cloudnet.lib.utility.document.Document;
import eu.cloudnetservice.cloudmob.MobConfig;
import eu.cloudnetservice.cloudmob.ServerMob;
import eu.cloudservice.cloudmob.Mob;
import eu.cloudservice.cloudmob.MobSelector;
import org.bukkit.Bukkit;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class PacketInMobSelector extends PacketInHandlerDefault {

    private static final Type UUID_SERVERMOB_MAP_TYPE = TypeToken.getParameterized(Map.class, UUID.class, ServerMob.class).getType();
    private static final Type MOBCONFIG_TYPE = TypeToken.get(MobConfig.class).getType();

    public void handleInput(Document packet, PacketSender packetSender) {
        Map<UUID, ServerMob> mobMap = packet.getObject("mobs", UUID_SERVERMOB_MAP_TYPE);
        MobConfig mobConfig = packet.getObject("mobConfig", MOBCONFIG_TYPE);
        final String group = CloudAPI.getInstance().getGroup();

        mobMap.entrySet().removeIf(entry -> !entry.getValue().getPosition().getGroup().equals(group));

        if (MobSelector.getInstance() != null) {
            MobSelector.getInstance().shutdown();
            MobSelector.getInstance().setMobConfig(mobConfig);
            MobSelector.getInstance().setMobs(new HashMap<>());
            runBukkitTask(mobMap, mobConfig);

            Bukkit.getScheduler().runTaskAsynchronously(CloudServer.getInstance().getPlugin(), () -> {
                for (ServerInfo serverInfo : MobSelector.getInstance().getServers().values()) {
                    MobSelector.getInstance().handleUpdate(serverInfo);
                }
            });
        } else {
            MobSelector mobSelector = new MobSelector(mobConfig);
            MobSelector.getInstance().setMobs(new HashMap<>());
            runBukkitTask(mobMap, mobConfig);
            mobSelector.init();
        }
    }

    private static void runBukkitTask(final Map<UUID, ServerMob> mobMap, final MobConfig mobConfig) {
        Bukkit.getScheduler().runTask(CloudServer.getInstance().getPlugin(), () -> {
            Map<UUID, Mob> mobImplementationMap = new HashMap<>();

            mobMap.forEach((uuid, serverMob) -> {
                Mob mob = MobSelector.getInstance().spawnMob(mobConfig, uuid, serverMob);
                if (mob == null) {
                    return;
                }

                mobImplementationMap.put(uuid, mob);
            });

            MobSelector.getInstance().setMobs(mobImplementationMap);
        });
    }

}
