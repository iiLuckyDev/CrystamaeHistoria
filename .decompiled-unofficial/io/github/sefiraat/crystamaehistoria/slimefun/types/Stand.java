/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Item
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.types;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.TickingBlockNoGui;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Stand
extends TickingBlockNoGui {
    protected static final String PDC_ITEM = "itemUUID";
    protected final Map<Location, UUID> itemMap = new HashMap<Location, UUID>();
    protected final Map<Location, Integer> currentTickMap = new HashMap<Location, Integer>();

    @ParametersAreNonnullByDefault
    protected Stand(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        this.addItemHandler(new ItemHandler[]{this::onRightClick});
    }

    public abstract void onRightClick(PlayerRightClickEvent var1);

    @Override
    @ParametersAreNonnullByDefault
    protected void onFirstTick(Block block, SlimefunItem slimefunItem, Config config) {
        Location blockLocation = block.getLocation();
        String itemUuidString = BlockStorage.getLocationInfo((Location)block.getLocation(), (String)PDC_ITEM);
        if (itemUuidString != null) {
            this.itemMap.put(block.getLocation(), UUID.fromString(itemUuidString));
        }
        this.currentTickMap.put(blockLocation, ThreadLocalRandom.current().nextInt(3, 7));
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onTick(Block block, SlimefunItem slimefunItem, Config config) {
        Location blockLocation = block.getLocation();
        UUID currentItemUuid = this.itemMap.get(blockLocation);
        if (currentItemUuid != null) {
            int currentTick = this.currentTickMap.get(blockLocation);
            Item currentItem = (Item)Bukkit.getEntity((UUID)currentItemUuid);
            if (currentItem == null) {
                return;
            }
            if (currentTick <= 0 && this.itemMap.containsKey(blockLocation)) {
                Location desiredLocation;
                Location itemLocation = currentItem.getLocation();
                if (itemLocation.distance(desiredLocation = blockLocation.clone().add(0.5, 1.5, 0.5)) > 0.3) {
                    ItemStack itemStack = currentItem.getItemStack();
                    blockLocation.getWorld().dropItemNaturally(blockLocation, itemStack);
                    BlockStorage.addBlockInfo((Block)block, (String)PDC_ITEM, null);
                    this.itemMap.remove(blockLocation);
                    currentItem.remove();
                }
                this.currentTickMap.put(blockLocation, ThreadLocalRandom.current().nextInt(4, 8));
            } else {
                this.currentTickMap.put(blockLocation, --currentTick);
            }
            this.afterTick(currentItem, block, slimefunItem, config);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onPlace(BlockPlaceEvent event) {
        this.currentTickMap.put(event.getBlock().getLocation(), ThreadLocalRandom.current().nextInt(3, 7));
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
        Location location = blockBreakEvent.getBlock().getLocation();
        UUID currentItemUuid = this.itemMap.get(location);
        if (currentItemUuid != null) {
            Item currentItem = (Item)Bukkit.getEntity((UUID)currentItemUuid);
            if (currentItem != null) {
                ItemStack displayStack = currentItem.getItemStack();
                location.getWorld().dropItemNaturally(location, displayStack);
                currentItem.remove();
            }
            BlockStorage.clearBlockInfo((Location)location);
        }
    }

    @ParametersAreNonnullByDefault
    public abstract void afterTick(Item var1, Block var2, SlimefunItem var3, Config var4);

    @Generated
    public Map<Location, UUID> getItemMap() {
        return this.itemMap;
    }

    @Generated
    public Map<Location, Integer> getCurrentTickMap() {
        return this.currentTickMap;
    }
}

