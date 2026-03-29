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
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Monster
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
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
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class MobLamp
extends SlimefunItem {
    private final double radius;
    private final double force;

    @ParametersAreNonnullByDefault
    public MobLamp(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, final double radius, final double force) {
        super(category, item, recipeType, recipe);
        this.radius = radius;
        this.force = force;
        this.addItemHandler(new ItemHandler[]{new BlockPlaceHandler(this, false){

            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                BlockStorage.addBlockInfo((Block)event.getBlock(), (String)"CH_UUID", (String)event.getPlayer().getUniqueId().toString());
            }
        }, new BlockBreakHandler(this, false, false){

            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                BlockStorage.clearBlockInfo((Block)blockBreakEvent.getBlock());
            }
        }, new BlockTicker(this){

            public boolean isSynchronized() {
                return true;
            }

            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                for (Monster monster : block.getWorld().getNearbyEntitiesByType(Monster.class, block.getLocation(), radius)) {
                    UUID uuid = UUID.fromString(BlockStorage.getLocationInfo((Location)block.getLocation(), (String)"CH_UUID"));
                    GeneralUtils.pushEntity(uuid, block.getLocation().clone().add(0.5, 0.5, 0.5), (Entity)monster, force);
                }
            }
        }});
    }

    @Generated
    public double getRadius() {
        return this.radius;
    }

    @Generated
    public double getForce() {
        return this.force;
    }
}

