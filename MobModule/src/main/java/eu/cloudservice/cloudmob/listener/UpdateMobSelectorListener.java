package eu.cloudservice.cloudmob.listener;

import de.dytanic.cloudnet.event.IEventListener;
import de.dytanic.cloudnet.lib.server.ServerGroupMode;
import de.dytanic.cloudnetcore.api.event.network.ChannelInitEvent;
import de.dytanic.cloudnetcore.network.components.MinecraftServer;
import eu.cloudservice.cloudmob.MobModule;
import eu.cloudservice.cloudmob.out.PacketOutMobSelector;

public class UpdateMobSelectorListener implements IEventListener<ChannelInitEvent> {

    private final MobModule mobModule;

    public UpdateMobSelectorListener(final MobModule mobModule) {
        this.mobModule = mobModule;
    }

    @Override
    public void onCall(ChannelInitEvent event) {
        if (event.getINetworkComponent() instanceof MinecraftServer) {
            MinecraftServer minecraftServer = (MinecraftServer) event.getINetworkComponent();

            if (minecraftServer.getGroupMode().equals(ServerGroupMode.LOBBY) || minecraftServer.getGroupMode()
                                                                                               .equals(ServerGroupMode.STATIC_LOBBY)) {
                minecraftServer.sendPacket(new PacketOutMobSelector(mobModule.getConfigMobs().load(),
                    mobModule.getMobDatabase().loadAll()));
            }
        }
    }
}
