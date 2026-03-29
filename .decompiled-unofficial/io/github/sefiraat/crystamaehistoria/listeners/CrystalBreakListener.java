/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPistonExtendEvent
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar.RealisationAltarCache;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import java.util.List;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class CrystalBreakListener
implements Listener {
    @EventHandler(ignoreCancelled=true)
    public void onBreakCrystal(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LARGE_AMETHYST_BUD) {
            this.handleCrystal(event, block, true);
        } else if (block.getRelative(BlockFace.UP).getType() == Material.LARGE_AMETHYST_BUD) {
            this.handleCrystal(event, block.getRelative(BlockFace.UP), false);
        }
    }

    @EventHandler(ignoreCancelled=true)
    public void onBreakCrystal(BlockPistonExtendEvent event) {
        List blocks = event.getBlocks();
        for (Block block : blocks) {
            if (block.getType() == Material.LARGE_AMETHYST_BUD) {
                this.handleCrystal(block, false);
                continue;
            }
            if (block.getRelative(BlockFace.UP).getType() != Material.LARGE_AMETHYST_BUD) continue;
            this.handleCrystal(block.getRelative(BlockFace.UP), false);
        }
    }

    private void handleCrystal(@Nonnull BlockBreakEvent event, @Nonnull Block block, boolean forceStopDrops) {
        if (this.handleCrystal(block, true) && forceStopDrops) {
            event.setCancelled(true);
            block.setType(Material.AIR);
        }
    }

    private boolean handleCrystal(@Nonnull Block block, boolean manual) {
        BlockPosition blockPosition = new BlockPosition(block);
        for (RealisationAltarCache cache : RealisationAltar.getCaches().values()) {
            RealisationAltarCache.RealisedCrystalState state = cache.getCrystalStoryMap().remove(blockPosition);
            if (state == null) continue;
            StoryRarity rarity = state.getStoryRarity();
            String id = state.getStoryId();
            Story story = CrystamaeHistoria.getStoriesManager().getStory(id, rarity);
            story.getStoryShardProfile().dropShards(rarity, block.getLocation(), state.isGilded() && manual);
            cache.saveMap();
            return true;
        }
        return false;
    }
}

