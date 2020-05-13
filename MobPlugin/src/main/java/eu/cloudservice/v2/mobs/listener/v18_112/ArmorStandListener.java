package eu.cloudservice.v2.mobs.listener.v18_112;

import eu.cloudservice.v2.mobs.Mob;
import eu.cloudservice.v2.mobs.MobSelector;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public final class ArmorStandListener implements Listener {

    @EventHandler
    public void handle(PlayerArmorStandManipulateEvent e) {
        Mob mob = MobSelector.getInstance().getMobs().get(e.getRightClicked().getUniqueId());
        if (mob != null) {
            e.setCancelled(true);
        }
    }

    /*@EventHandler
    public void handle(ItemDespawnEvent e) {
        Mob mob = null;
        for (final Mob mobImpl : MobSelector.getInstance().getMobs().values()) {
            if (mobImpl.getDisplayMessage().getPassenger() != null &&
                mobImpl.getDisplayMessage().getPassenger().getEntityId() == e.getEntity().getEntityId()) {
                mob = mobImpl;
            }
        }
        if (mob != null) {
            e.setCancelled(true);
        }
    }*/
}
