/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockExplodeEvent
 *  org.bukkit.event.block.BlockFormEvent
 *  org.bukkit.event.player.PlayerBucketFillEvent
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.plugin.Plugin;

public class BlockRemovalListener
implements Listener {
    @EventHandler
    public void onRemovableBlockBreak(BlockBreakEvent event) {
        if (this.processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBucketUse(PlayerBucketFillEvent event) {
        if (this.processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        if (this.processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        if (this.processBlockRemoval(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    private boolean processBlockRemoval(Block block) {
        if (GeneralUtils.isRemovableBlock(block)) {
            block.removeMetadata("ch", (Plugin)CrystamaeHistoria.getInstance());
            block.setType(Material.AIR);
            CrystamaeHistoria.getSpellMemory().stopBlockRemoval(block);
            return true;
        }
        return false;
    }
}

