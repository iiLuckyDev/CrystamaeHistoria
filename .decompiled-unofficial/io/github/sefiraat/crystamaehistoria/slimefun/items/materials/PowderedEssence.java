/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem
 *  org.bukkit.NamespacedKey
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.materials;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class PowderedEssence
extends LimitedUseItem {
    private static final NamespacedKey key = Keys.newKey("uses");

    @ParametersAreNonnullByDefault
    public PowderedEssence(ItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        super(group, item, recipeType, recipe);
        this.setMaxUseCount(amount);
    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional optionalBlock = e.getClickedBlock();
            if (!optionalBlock.isPresent()) {
                return;
            }
            Block block = (Block)optionalBlock.get();
            if (block.applyBoneMeal(e.getClickedFace())) {
                this.damageItem(e.getPlayer(), e.getItem());
            }
        };
    }

    @Nonnull
    protected NamespacedKey getStorageKey() {
        return key;
    }
}

