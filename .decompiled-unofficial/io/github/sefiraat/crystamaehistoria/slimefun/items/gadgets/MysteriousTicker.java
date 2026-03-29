/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class MysteriousTicker
extends SlimefunItem {
    private final Set<Material> materials;
    private final Map<Location, Integer> tickMap = new HashMap<Location, Integer>();
    private final int ticks;
    private final Consumer<Block> consumer;

    @ParametersAreNonnullByDefault
    public MysteriousTicker(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency) {
        this(category, item, recipeType, recipe, tickingMaterials, tickFrequency, null);
    }

    @ParametersAreNonnullByDefault
    public MysteriousTicker(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Set<Material> tickingMaterials, int tickFrequency, @Nullable Consumer<Block> consumer) {
        super(category, item, recipeType, recipe);
        this.materials = tickingMaterials;
        this.ticks = tickFrequency;
        this.consumer = consumer;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onTick()});
    }

    private BlockTicker onTick() {
        return new BlockTicker(){

            public boolean isSynchronized() {
                return true;
            }

            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                Integer currentTick;
                if (block.isEmpty()) {
                    BlockStorage.clearBlockInfo((Location)block.getLocation());
                }
                if ((currentTick = MysteriousTicker.this.tickMap.get(block.getLocation())) == null) {
                    currentTick = ThreadLocalRandom.current().nextInt(MysteriousTicker.this.ticks);
                }
                if (currentTick >= MysteriousTicker.this.ticks) {
                    currentTick = 0;
                    block.setType(MysteriousTicker.this.materials.toArray(new Material[0])[ThreadLocalRandom.current().nextInt(MysteriousTicker.this.materials.size())]);
                    if (MysteriousTicker.this.consumer != null) {
                        MysteriousTicker.this.consumer.accept(block);
                    }
                } else {
                    Integer n = currentTick;
                    currentTick = currentTick + 1;
                }
                MysteriousTicker.this.tickMap.put(block.getLocation(), currentTick);
            }
        };
    }
}

