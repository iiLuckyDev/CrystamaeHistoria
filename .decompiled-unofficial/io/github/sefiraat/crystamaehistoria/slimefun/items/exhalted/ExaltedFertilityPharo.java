/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  org.bukkit.Location
 *  org.bukkit.entity.Animals
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedItem;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.inventory.ItemStack;

public class ExaltedFertilityPharo
extends ExaltedItem {
    private final int range;

    public ExaltedFertilityPharo(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(itemGroup, item, recipeType, recipe);
        this.range = range;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        List animals = location.getNearbyEntitiesByType(Animals.class, (double)this.range, (double)this.range).stream().toList();
        if (!animals.isEmpty()) {
            int rnd = ThreadLocalRandom.current().nextInt(0, animals.size());
            ((Animals)animals.get(rnd)).setLoveModeTicks(100);
        }
    }
}

