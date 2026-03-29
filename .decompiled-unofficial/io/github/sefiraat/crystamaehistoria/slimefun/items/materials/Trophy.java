/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler
 *  org.bukkit.Location
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 *  org.jetbrains.annotations.NotNull
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.materials;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Trophy
extends SlimefunItem {
    private final Consumer<Location> displayConsumer;

    @ParametersAreNonnullByDefault
    public Trophy(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Consumer<Location> displayConsumer) {
        super(itemGroup, item, recipeType, recipe);
        this.displayConsumer = displayConsumer;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onBlockPlace()});
        this.addItemHandler(new ItemHandler[]{this.onConsume()});
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(this, false){

            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                e.setCancelled(true);
            }
        };
    }

    private ItemConsumptionHandler onConsume() {
        return (e, p, item) -> e.setCancelled(true);
    }

    public Consumer<Location> getDisplayConsumer() {
        return this.displayConsumer;
    }
}

