/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar.RealisationAltarCache;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class RealisationAltar
extends TickingMenuBlock {
    protected static final int[] BACKGROUND_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    protected static final int[] BACKGROUND_INPUT = new int[]{12, 13, 14, 21, 23, 30, 31, 32};
    protected static final int INPUT_SLOT = 22;
    protected static final Map<Location, RealisationAltarCache> CACHES = new HashMap<Location, RealisationAltarCache>();
    private final int tier;

    @ParametersAreNonnullByDefault
    public RealisationAltar(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int tier) {
        super(itemGroup, item, recipeType, recipe);
        this.tier = tier;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onBlockPlace()});
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false){

            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                Location location = event.getBlockPlaced().getLocation();
                RealisationAltarCache cache = new RealisationAltarCache(BlockStorage.getInventory((Location)location), RealisationAltar.this.tier);
                cache.setActivePlayer(event.getPlayer());
                CACHES.put(location, cache);
            }
        };
    }

    public static Map<Location, RealisationAltarCache> getCaches() {
        return CACHES;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        RealisationAltarCache cache = CACHES.get(block.getLocation());
        if (cache != null) {
            cache.process();
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND, BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND_INPUT, BACKGROUND_INPUT);
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        if (!CACHES.containsKey(blockMenu.getLocation())) {
            RealisationAltarCache cache = new RealisationAltarCache(blockMenu, this.tier);
            cache.loadMap();
            CACHES.put(blockMenu.getLocation(), cache);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent event, BlockMenu blockMenu) {
        super.onBreak(event, blockMenu);
        Location location = blockMenu.getLocation();
        RealisationAltarCache realisationAltarCache = CACHES.remove(location);
        if (realisationAltarCache != null) {
            realisationAltarCache.kill();
        }
        blockMenu.dropItems(location, new int[]{22});
    }

    @Generated
    public int getTier() {
        return this.tier;
    }
}

