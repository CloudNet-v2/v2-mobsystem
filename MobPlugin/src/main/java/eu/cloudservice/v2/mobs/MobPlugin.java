package eu.cloudservice.v2.mobs;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketRC;
import eu.cloudservice.v2.mobs.packet.in.PacketInMobSelector;
import org.bukkit.plugin.java.JavaPlugin;

public class MobPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        CloudAPI.getInstance().getNetworkConnection().getPacketManager().registerHandler(PacketRC.SERVER_SELECTORS + 2,
            PacketInMobSelector.class);
    }

    @Override
    public void onDisable() {
        if (MobSelector.getInstance() != null) {
            MobSelector.getInstance().shutdown();
        }
    }

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }
}
