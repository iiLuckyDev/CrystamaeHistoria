/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  org.bukkit.NamespacedKey
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.ItemDespawnEvent
 *  org.bukkit.event.inventory.InventoryPickupItemEvent
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.persistence.PersistentDataHolder;

public class DisplayItemListener
implements Listener {
    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent e) {
        if (PersistentDataAPI.hasBoolean((PersistentDataHolder)e.getItem(), (NamespacedKey)Keys.PDC_IS_DISPLAY_ITEM)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent e) {
        if (PersistentDataAPI.hasBoolean((PersistentDataHolder)e.getEntity(), (NamespacedKey)Keys.PDC_IS_DISPLAY_ITEM)) {
            e.setCancelled(true);
            e.getEntity().setTicksLived(1);
        }
    }
}

