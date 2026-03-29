/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MysteriousTicker;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class Waystone
extends MysteriousTicker {
    @ParametersAreNonnullByDefault
    public Waystone(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency) {
        this(category, item, recipeType, recipe, tickingMaterials, tickFrequency, null);
    }

    @ParametersAreNonnullByDefault
    public Waystone(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency, @Nullable Consumer<Block> consumer) {
        super(category, item, recipeType, recipe, tickingMaterials, tickFrequency, consumer);
    }
}

