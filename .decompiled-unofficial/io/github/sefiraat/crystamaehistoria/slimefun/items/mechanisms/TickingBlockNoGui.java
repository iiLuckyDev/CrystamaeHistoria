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
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public abstract class TickingBlockNoGui
extends SlimefunItem {
    protected final Map<Location, Boolean> firstTickMap = new HashMap<Location, Boolean>();

    @ParametersAreNonnullByDefault
    protected TickingBlockNoGui(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void preRegister() {
        this.addItemHandler(this.blockTicker());
    }

    @ParametersAreNonnullByDefault
    protected TickingBlockNoGui(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    @ParametersAreNonnullByDefault
    protected abstract void onFirstTick(Block var1, SlimefunItem var2, Config var3);

    @ParametersAreNonnullByDefault
    protected abstract void onTick(Block var1, SlimefunItem var2, Config var3);

    @ParametersAreNonnullByDefault
    protected abstract void onPlace(BlockPlaceEvent var1);

    @ParametersAreNonnullByDefault
    protected abstract void onBreak(BlockBreakEvent var1, ItemStack var2, List<ItemStack> var3);

    protected ItemHandler[] blockTicker() {
        return new ItemHandler[]{new BlockTicker(){

            public boolean isSynchronized() {
                return true;
            }

            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                if (!TickingBlockNoGui.this.firstTickMap.containsKey(block.getLocation())) {
                    TickingBlockNoGui.this.onFirstTick(block, slimefunItem, config);
                    TickingBlockNoGui.this.firstTickMap.put(block.getLocation(), true);
                }
                TickingBlockNoGui.this.onTick(block, slimefunItem, config);
            }
        }, new BlockPlaceHandler(false){

            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                TickingBlockNoGui.this.onPlace(event);
            }
        }, new BlockBreakHandler(false, false){

            public void onPlayerBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
                TickingBlockNoGui.this.onBreak(blockBreakEvent, itemStack, list);
            }
        }};
    }
}

