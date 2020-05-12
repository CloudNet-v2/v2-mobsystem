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
import eu.cloudservice.cloudmob.out.PacketOutMobSelector;

public class MobModule extends CoreModule implements IEventListener<UpdateAllEvent> {

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

        getCloud().getEventManager().registerListener(this, this);
        getCloud().getEventManager().registerListener(this, new ListenerImpl());
    }

    @Override
    public void onCall(UpdateAllEvent event) {
        if (event.isOnlineCloudNetworkUpdate()) {
            getCloud().getNetworkManager().sendToLobbys(new PacketOutMobSelector(configMobs.load(), mobDatabase.loadAll()));
        }
    }

    private class ListenerImpl implements IEventListener<ChannelInitEvent> {

        @Override
        public void onCall(ChannelInitEvent event) {
            if (event.getINetworkComponent() instanceof MinecraftServer) {
                MinecraftServer minecraftServer = (MinecraftServer) event.getINetworkComponent();

                if (minecraftServer.getGroupMode().equals(ServerGroupMode.LOBBY) || minecraftServer.getGroupMode()
                                                                                                   .equals(ServerGroupMode.STATIC_LOBBY)) {
                    minecraftServer.sendPacket(new PacketOutMobSelector(configMobs.load(), mobDatabase.loadAll()));
                }
            }
        }
    }

}
