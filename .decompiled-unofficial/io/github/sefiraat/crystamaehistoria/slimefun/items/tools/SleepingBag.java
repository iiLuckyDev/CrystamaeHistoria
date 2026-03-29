/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SleepingBag
extends UnplaceableBlock {
    @ParametersAreNonnullByDefault
    public SleepingBag(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Player player = e.getPlayer();
            Location location = player.getLocation();
            Block block = location.getBlock();
            if (location.getWorld().isDayTime()) {
                player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "You can only use a sleeping bag at night.");
                return;
            }
            if (location.getBlock().isEmpty() && GeneralUtils.hasPermission(player, location, Interaction.PLACE_BLOCK)) {
                Location respawnLocation = player.getBedSpawnLocation();
                block.setType(Material.WHITE_BED);
                player.sleep(location, true);
                CrystamaeHistoria.getSpellMemory().getSleepingBags().put(player.getUniqueId(), location);
                player.sendMessage(String.valueOf(ThemeType.SUCCESS.getColor()) + "Respawn location reset to previous.");
                player.setBedSpawnLocation(respawnLocation, true);
            }
        };
    }
}

