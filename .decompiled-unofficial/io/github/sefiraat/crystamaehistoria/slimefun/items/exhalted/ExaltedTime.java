/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedItem;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExaltedTime
extends ExaltedItem {
    private final int time;

    public ExaltedTime(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int time) {
        super(itemGroup, item, recipeType, recipe);
        this.time = time;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (Player player : location.getNearbyEntitiesByType(Player.class, 25.0, 25.0)) {
            player.setPlayerTime((long)this.time, false);
            CrystamaeHistoria.getSpellMemory().getPlayersWithFrozenTime().put(player.getUniqueId(), System.currentTimeMillis() + 2000L);
        }
    }
}

