/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.attributes.NotConfigurable
 *  io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.Displacer;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotConfigurable;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

public class PhilosophersSpray
extends SlimefunItem
implements NotHopperable,
NotConfigurable {
    @ParametersAreNonnullByDefault
    public PhilosophersSpray(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{new BlockUseHandler(this){

            public void onRightClick(PlayerRightClickEvent e) {
                e.cancel();
            }
        }});
    }

    public static void triggerChange(@Nonnull Block block) {
        Block blockToChange = block.getRelative(BlockFace.UP);
        Displacer.convertBlock(blockToChange);
    }
}

