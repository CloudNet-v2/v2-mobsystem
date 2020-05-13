package eu.cloudservice.cloudmob;

import de.dytanic.cloudnet.event.IEventListener;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketRC;
import de.dytanic.cloudnet.lib.server.ServerGroupMode;
import de.dytanic.cloudnetcore.api.CoreModule;
import de.dytanic.cloudnetcore.api.event.network.ChannelInitEvent;
import de.dytanic.cloudnetcore.api.event.network.UpdateAllEvent;
import de.dytanic.cloudnetcore.network.components.MinecraftServer;
import eu.cloudservice.cloudmob.in.PacketInAddMob;
import eu.cloudservice.cloudmob.in.PacketInRemoveMob;
import eu.cloudservice.cloudmob.listener.UpdateLobbiesListener;
import eu.cloudservice.cloudmob.listener.UpdateMobSelectorListener;
import eu.cloudservice.cloudmob.out.PacketOutMobSelector;

public class MobModule extends CoreModule {

    private static MobModule instance;
    private ConfigMobs configMobs;
    private MobDatabase mobDatabase;

    public static MobModule getInstance() {
        return instance;
    }

    public ConfigMobs getConfigMobs() {
        return configMobs;
    }

    public MobDatabase getMobDatabase() {
        return mobDatabase;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onBootstrap() {
        configMobs = new ConfigMobs();
        mobDatabase = new MobDatabase(getCloud().getDatabaseManager().getDatabase("cloud_internal_cfg"));

        if (getCloud().getPacketManager().buildHandlers(PacketRC.SERVER_SELECTORS + 3).size() == 0) {
            getCloud().getPacketManager().registerHandler(PacketRC.SERVER_SELECTORS + 3, PacketInAddMob.class);
        }
        if (getCloud().getPacketManager().buildHandlers(PacketRC.SERVER_SELECTORS + 4).size() == 0) {
            getCloud().getPacketManager().registerHandler(PacketRC.SERVER_SELECTORS + 4, PacketInRemoveMob.class);
        }

        getCloud().getEventManager().registerListener(this, new UpdateLobbiesListener(this));
        getCloud().getEventManager().registerListener(this, new UpdateMobSelectorListener(this));
    }


}
