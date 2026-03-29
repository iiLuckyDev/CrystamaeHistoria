/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Enderman
 *  org.bukkit.entity.Entity
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityTeleportEvent
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;

public class EndermanInhibitorListener
implements Listener {
    @EventHandler
    public void onInteract(EntityTeleportEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Enderman && CrystamaeHistoria.getSpellMemory().getInhibitedEndermen().containsKey(entity.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}

