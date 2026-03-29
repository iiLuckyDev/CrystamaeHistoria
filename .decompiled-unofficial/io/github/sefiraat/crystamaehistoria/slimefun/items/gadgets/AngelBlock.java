/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 *  org.jetbrains.annotations.Nullable
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class AngelBlock
extends SlimefunItem {
    public AngelBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onItemUse()});
    }

    @Nonnull
    private ItemUseHandler onItemUse() {
        return e -> {
            Location location = e.getPlayer().getEyeLocation();
            Block block = location.add(location.getDirection()).getBlock();
            if (block.isEmpty() && block.getLocation().getY() <= (double)block.getWorld().getMaxHeight() && block.getLocation().getY() >= (double)block.getWorld().getMinHeight() && GeneralUtils.hasPermission(e.getPlayer(), block, Interaction.PLACE_BLOCK)) {
                block.setType(e.getItem().getType());
                BlockStorage.store((Block)block, (ItemStack)e.getItem());
                e.getItem().setAmount(e.getItem().getAmount() - 1);
            }
            e.cancel();
        };
    }
}

