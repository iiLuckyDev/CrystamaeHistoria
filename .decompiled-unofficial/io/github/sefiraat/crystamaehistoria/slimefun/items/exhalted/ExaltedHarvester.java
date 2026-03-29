/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.block.data.Ageable
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedItem;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;

public class ExaltedHarvester
extends ExaltedItem {
    private final int range;

    public ExaltedHarvester(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(itemGroup, item, recipeType, recipe);
        this.range = range;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (int i = 0; i < this.range; ++i) {
            int z;
            int x = ThreadLocalRandom.current().nextInt(-this.range, this.range + 1);
            Block block = location.add((double)x, -1.5, (double)(z = ThreadLocalRandom.current().nextInt(-this.range, this.range + 1))).getBlock();
            if (!(block.getBlockData() instanceof Ageable)) continue;
            Ageable ageable = (Ageable)block.getBlockData();
            Material material = block.getType();
            if (ageable.getAge() != ageable.getMaximumAge()) continue;
            block.breakNaturally();
            block.setType(material);
        }
    }
}

