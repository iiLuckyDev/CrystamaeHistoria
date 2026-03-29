/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.block.Block
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.block.data.type.Light
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.sefiraat.crystamaehistoria.slimefun.types.RefillableUseItem;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class LuminescenceScoop
extends RefillableUseItem {
    private static final NamespacedKey key = Keys.newKey("uses");
    private final boolean adjustable;

    @ParametersAreNonnullByDefault
    public LuminescenceScoop(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        this(itemGroup, item, recipeType, recipe, amount, false);
    }

    @ParametersAreNonnullByDefault
    public LuminescenceScoop(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount, boolean adjustable) {
        super(itemGroup, item, recipeType, recipe);
        this.setMaxUseCount(amount);
        this.adjustable = adjustable;
    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return event -> {
            event.cancel();
            if (event.getPlayer().isSneaking()) {
                this.removeLight(event);
            } else {
                this.setLight(event);
            }
        };
    }

    public void adjustLight(@Nonnull Player player) {
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection();
        for (int i = 1; i < 6; ++i) {
            Light light;
            Block checkBlock = start.add(direction.multiply(i)).getBlock();
            if (checkBlock.getType() != Material.LIGHT || !GeneralUtils.hasPermission(player, checkBlock, Interaction.INTERACT_BLOCK)) continue;
            light.setLevel((light = (Light)checkBlock.getBlockData()).getLevel() == light.getMaximumLevel() ? 0 : light.getLevel() + 1);
            checkBlock.setBlockData((BlockData)light);
        }
    }

    private void removeLight(PlayerRightClickEvent event) {
        Player player = event.getPlayer();
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection();
        for (int i = 1; i < 6; ++i) {
            Block checkBlock = start.add(direction.multiply(i)).getBlock();
            if (checkBlock.getType() != Material.LIGHT || !GeneralUtils.hasPermission(player, checkBlock, Interaction.BREAK_BLOCK)) continue;
            checkBlock.setType(Material.AIR);
            this.refillItem(event.getItem());
        }
    }

    private void setLight(PlayerRightClickEvent event) {
        Block block;
        Block possibleLight;
        if (event.getClickedBlock().isPresent() && (possibleLight = (block = (Block)event.getClickedBlock().get()).getRelative(event.getClickedFace())).isEmpty() && GeneralUtils.hasPermission(event.getPlayer(), possibleLight, Interaction.BREAK_BLOCK)) {
            possibleLight.setType(Material.LIGHT);
            this.damageItem(event.getPlayer(), event.getItem());
        }
    }

    @Nonnull
    protected NamespacedKey getStorageKey() {
        return key;
    }

    public boolean isAdjustable() {
        return this.adjustable;
    }
}

