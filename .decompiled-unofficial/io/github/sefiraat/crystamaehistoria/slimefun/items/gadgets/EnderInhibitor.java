/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  lombok.Generated
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Enderman
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.entity.Enderman;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class EnderInhibitor
extends SlimefunItem {
    private final double radius;

    @ParametersAreNonnullByDefault
    public EnderInhibitor(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double radius) {
        super(category, item, recipeType, recipe);
        this.radius = radius;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onBlockPlace(), this.onBlockBreak(), this.onTick()});
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(this, false){

            @ParametersAreNonnullByDefault
            public void onPlayerPlace(BlockPlaceEvent event) {
                BlockStorage.addBlockInfo((Block)event.getBlock(), (String)"CH_UUID", (String)event.getPlayer().getUniqueId().toString());
            }
        };
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(this, false, false){

            @ParametersAreNonnullByDefault
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                BlockStorage.clearBlockInfo((Block)blockBreakEvent.getBlock());
            }
        };
    }

    private BlockTicker onTick() {
        return new BlockTicker(){

            public boolean isSynchronized() {
                return true;
            }

            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                for (Enderman enderman : block.getWorld().getNearbyEntitiesByType(Enderman.class, block.getLocation(), EnderInhibitor.this.radius)) {
                    CrystamaeHistoria.getSpellMemory().getInhibitedEndermen().put(enderman.getUniqueId(), System.currentTimeMillis() + 2000L);
                }
            }
        };
    }

    @Generated
    public double getRadius() {
        return this.radius;
    }
}

