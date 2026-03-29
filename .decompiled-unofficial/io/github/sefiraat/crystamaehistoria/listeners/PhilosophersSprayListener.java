/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.papermc.paper.event.block.BlockFailedDispenseEvent
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.block.Block
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.PhilosophersSpray;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.papermc.paper.event.block.BlockFailedDispenseEvent;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PhilosophersSprayListener
implements Listener {
    @EventHandler
    public void onInteract(BlockFailedDispenseEvent e) {
        Block block = e.getBlock();
        SlimefunItem item = BlockStorage.check((Block)block);
        if (item instanceof PhilosophersSpray) {
            PhilosophersSpray.triggerChange(block);
        }
    }
}

