/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.block.Block
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.CauldronLevelChangeEvent
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CauldronLevelChangeEvent;

public class MaintenanceListener
implements Listener {
    @EventHandler
    public void onRemovableBlockBreak(CauldronLevelChangeEvent event) {
        if (BlockStorage.check((Block)event.getBlock()) instanceof LiquefactionBasin) {
            event.setCancelled(true);
        }
    }
}

