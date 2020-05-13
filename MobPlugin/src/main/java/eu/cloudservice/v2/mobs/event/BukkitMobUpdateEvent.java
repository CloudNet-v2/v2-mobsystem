package eu.cloudservice.v2.mobs.event;

import de.dytanic.cloudnet.bridge.event.bukkit.BukkitCloudEvent;
import eu.cloudnetservice.v2.mobs.ServerMob;
import org.bukkit.event.HandlerList;

public final class BukkitMobUpdateEvent extends BukkitCloudEvent {

    private static HandlerList handlerList = new HandlerList();

    private ServerMob serverMob;

    public BukkitMobUpdateEvent(ServerMob serverMob) {
        this.serverMob = serverMob;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public ServerMob getServerMob() {
        return serverMob;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
