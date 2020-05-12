package eu.cloudservice.cloudmob.event;

import de.dytanic.cloudnet.bridge.event.bukkit.BukkitCloudEvent;
import eu.cloudservice.cloudmob.Mob;
import org.bukkit.event.HandlerList;

public class BukkitMobInitEvent extends BukkitCloudEvent {

    private static HandlerList handlerList = new HandlerList();

    private Mob mob;

    public BukkitMobInitEvent(Mob mob) {
        this.mob = mob;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Mob getMob() {
        return mob;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
