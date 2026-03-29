/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
 *  lombok.Generated
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.materials;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.inventory.ItemStack;

public class Crystal
extends UnplaceableBlock {
    protected static final Map<StoryRarity, Integer> RARITY_VALUE_MAP = new EnumMap<StoryRarity, Integer>(StoryRarity.class);
    private final StoryType type;
    private final StoryRarity rarity;

    @ParametersAreNonnullByDefault
    public Crystal(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, StoryRarity rarity, StoryType type) {
        super(itemGroup, item, recipeType, recipe);
        this.type = type;
        this.rarity = rarity;
    }

    public static Map<StoryRarity, Integer> getRarityValueMap() {
        return RARITY_VALUE_MAP;
    }

    @Generated
    public StoryType getType() {
        return this.type;
    }

    @Generated
    public StoryRarity getRarity() {
        return this.rarity;
    }

    static {
        RARITY_VALUE_MAP.put(StoryRarity.COMMON, 1);
        RARITY_VALUE_MAP.put(StoryRarity.UNCOMMON, 3);
        RARITY_VALUE_MAP.put(StoryRarity.RARE, 10);
        RARITY_VALUE_MAP.put(StoryRarity.EPIC, 25);
        RARITY_VALUE_MAP.put(StoryRarity.MYTHICAL, 50);
        RARITY_VALUE_MAP.put(StoryRarity.UNIQUE, 2);
    }
}

