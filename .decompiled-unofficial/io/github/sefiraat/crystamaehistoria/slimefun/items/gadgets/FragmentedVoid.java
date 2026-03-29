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
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow
 *  org.bukkit.Location
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Particle
 *  org.bukkit.Vibration
 *  org.bukkit.Vibration$Destination
 *  org.bukkit.Vibration$Destination$BlockDestination
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Vibration;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class FragmentedVoid
extends SlimefunItem {
    private static final int[] OUTPUT_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private final int range;

    @ParametersAreNonnullByDefault
    public FragmentedVoid(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(category, item, recipeType, recipe);
        this.range = range;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onTick(), this.onBreak()});
    }

    private BlockTicker onTick() {
        return new BlockTicker(){

            public boolean isSynchronized() {
                return true;
            }

            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                Location location = block.getLocation().clone().add(0.5, 0.5, 0.5);
                Collection itemsToConsume = location.getWorld().getNearbyEntitiesByType(Item.class, location, 1.25);
                BlockMenu blockMenu = BlockStorage.getInventory((Block)block);
                for (Object item : itemsToConsume) {
                    if (item.getPickupDelay() > 0 || SlimefunUtils.hasNoPickupFlag((Item)item)) continue;
                    ItemStack itemStack = item.getItemStack();
                    HashMap leftovers = blockMenu.toInventory().addItem(new ItemStack[]{itemStack});
                    Optional possibleItem = leftovers.values().stream().findFirst();
                    if (possibleItem.isPresent()) {
                        item.setItemStack((ItemStack)possibleItem.get());
                        continue;
                    }
                    item.remove();
                }
                Collection items = location.getWorld().getNearbyEntitiesByType(Item.class, location, (double)FragmentedVoid.this.range);
                for (Item item : items) {
                    if (item.getPickupDelay() > 0 || SlimefunUtils.hasNoPickupFlag((Item)item)) continue;
                    GeneralUtils.pullEntity(location, (Entity)item, 0.5);
                }
                for (int i = 0; i < 4; ++i) {
                    double xOffset = ThreadLocalRandom.current().nextDouble(-3.0, 3.0);
                    double yOffset = ThreadLocalRandom.current().nextDouble(-3.0, 3.0);
                    double zOffset = ThreadLocalRandom.current().nextDouble(-3.0, 3.0);
                    Location spawnLocation = location.clone().add(xOffset, yOffset, zOffset);
                    Vibration vibration = new Vibration(spawnLocation, (Vibration.Destination)new Vibration.Destination.BlockDestination(location), 20);
                    location.getWorld().spawnParticle(Particle.VIBRATION, spawnLocation, 1, (Object)vibration);
                }
            }
        };
    }

    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(this, false, false){

            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                BlockMenu blockMenu = BlockStorage.getInventory((Block)e.getBlock());
                blockMenu.dropItems(blockMenu.getLocation(), OUTPUT_SLOTS);
            }
        };
    }

    public void postRegister() {
        new BlockMenuPreset(this, this.getId(), this.getItemName()){

            public void init() {
                this.setSize(9);
            }

            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return Slimefun.getProtectionManager().hasPermission((OfflinePlayer)player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.WITHDRAW) {
                    return OUTPUT_SLOTS;
                }
                return new int[0];
            }
        };
    }
}

