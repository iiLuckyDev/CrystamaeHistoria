/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder.PrismaticGilderCache;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PrismaticGilder
extends TickingMenuBlock {
    protected static final int[] BACKGROUND_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    protected final Map<Location, PrismaticGilderCache> cacheMap = new HashMap<Location, PrismaticGilderCache>();

    @ParametersAreNonnullByDefault
    public PrismaticGilder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onBlockPlace(), this.onBlockUse()});
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false){

            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                Location location = event.getBlockPlaced().getLocation();
                PrismaticGilderCache cache = new PrismaticGilderCache(BlockStorage.getInventory((Location)location), 5);
                cache.setActivePlayer(event.getPlayer());
                PrismaticGilder.this.cacheMap.put(location, cache);
            }
        };
    }

    private BlockUseHandler onBlockUse() {
        return e -> {
            Block block = e.getClickedBlock().orElse(null);
            if (block == null) {
                return;
            }
            PrismaticGilderCache cache = this.cacheMap.get(block.getLocation());
            Player player = e.getPlayer();
            ItemStack heldItem = player.getInventory().getItemInMainHand();
            if (cache != null && heldItem.getType() != Material.AIR) {
                cache.gildItem(block, heldItem, player);
            }
        };
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        PrismaticGilderCache cache = this.cacheMap.get(block.getLocation());
        if (cache != null) {
            cache.consumeItems();
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.addMenuOpeningHandler(HumanEntity::closeInventory);
        blockMenuPreset.drawBackground(BACKGROUND_SLOTS);
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
    protected void onBreak(BlockBreakEvent event, BlockMenu blockMenu) {
        super.onBreak(event, blockMenu);
        Location location = blockMenu.getLocation();
        PrismaticGilderCache prismaticGilderCache = this.cacheMap.remove(location);
        boolean punish = false;
        if (prismaticGilderCache != null) {
            prismaticGilderCache.kill(location);
            boolean bl = punish = prismaticGilderCache.getFillAmount() > 0;
        }
        if (punish) {
            blockMenu.getLocation().getWorld().createExplosion(event.getBlock().getLocation(), 2.0f, true, false);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        if (!this.cacheMap.containsKey(blockMenu.getLocation())) {
            PrismaticGilderCache cache = new PrismaticGilderCache(blockMenu, 5);
            String s = BlockStorage.getLocationInfo((Location)blockMenu.getLocation(), (String)"ch_amount");
            if (s != null) {
                cache.setFillAmount(Integer.parseInt(s));
            }
            this.cacheMap.put(blockMenu.getLocation(), cache);
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

    @Generated
    public Map<Location, PrismaticGilderCache> getCacheMap() {
        return this.cacheMap;
    }
}

