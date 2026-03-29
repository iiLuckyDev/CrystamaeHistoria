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
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 *  org.jetbrains.annotations.Nullable
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools.covers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BlockVeil
extends SlimefunItem {
    private final Class<? extends SlimefunItem>[] classToCover;

    @ParametersAreNonnullByDefault
    public BlockVeil(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput, Class<? extends SlimefunItem> ... classToCover) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        this.classToCover = classToCover;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onItemUse()});
    }

    private ItemUseHandler onItemUse() {
        return e -> {
            e.cancel();
            if (e.getClickedBlock().isPresent()) {
                Block block = (Block)e.getClickedBlock().get();
                SlimefunItem slimefunItem = BlockStorage.check((Block)block);
                ItemStack offhand = e.getPlayer().getInventory().getItemInOffHand();
                if (slimefunItem == null) {
                    return;
                }
                for (Class<? extends SlimefunItem> testClass : this.classToCover) {
                    if (!testClass.isInstance(slimefunItem)) continue;
                    if (offhand.getType() != Material.AIR && offhand.getType().isBlock() && this.materialIsValid(offhand.getType())) {
                        block.setType(offhand.getType());
                        e.getItem().setAmount(e.getItem().getAmount() - 1);
                    }
                    return;
                }
            }
        };
    }

    @ParametersAreNonnullByDefault
    public boolean materialIsValid(Material material) {
        return material != Material.SPAWNER && material.getHardness() != -1.0f && material.isSolid() && material.isOccluding();
    }
}

