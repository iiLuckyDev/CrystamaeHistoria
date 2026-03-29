/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 *  org.jetbrains.annotations.Nullable
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MysteriousTicker;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import java.util.Set;
import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MysteriousTickerNoInteraction
extends MysteriousTicker {
    public MysteriousTickerNoInteraction(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency) {
        super(category, item, recipeType, recipe, tickingMaterials, tickFrequency);
    }

    public MysteriousTickerNoInteraction(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency, @Nullable Consumer<Block> consumer) {
        super(category, item, recipeType, recipe, tickingMaterials, tickFrequency, consumer);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        this.addItemHandler(new ItemHandler[]{this.blockUseHandler()});
    }

    private BlockUseHandler blockUseHandler() {
        return PlayerRightClickEvent::cancel;
    }
}

