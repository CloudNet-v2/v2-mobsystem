package eu.cloudservice.cloudmob.listener;

import de.dytanic.cloudnet.event.IEventListener;
import de.dytanic.cloudnetcore.api.event.network.UpdateAllEvent;
import eu.cloudservice.cloudmob.MobModule;
import eu.cloudservice.cloudmob.out.PacketOutMobSelector;

public class UpdateLobbiesListener implements IEventListener<UpdateAllEvent> {

    private final MobModule mobModule;

    public UpdateLobbiesListener(final MobModule mobModule) {
        this.mobModule = mobModule;
    }

    @Override
    public void onCall(final UpdateAllEvent event) {
        if (event.isOnlineCloudNetworkUpdate()) {
            mobModule.getCloud().getNetworkManager().sendToLobbys(new PacketOutMobSelector(mobModule.getConfigMobs().load(),
                mobModule.getMobDatabase().loadAll()));
        }
    }
}
