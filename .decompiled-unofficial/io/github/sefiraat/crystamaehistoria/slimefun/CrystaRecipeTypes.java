/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.bukkit.inventory.ItemStack;

public final class CrystaRecipeTypes {
    private static final Map<ItemStack, ItemStack> DRAINING_RECIPES = new HashMap<ItemStack, ItemStack>();
    public static final RecipeType LIQUEFACTION_CRAFTING = new RecipeType(Keys.LIQUEFACTION_CRAFTING_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_LIQUEFACTION_CRAFTING);
    public static final RecipeType LIQUEFACTION_SPELL = new RecipeType(Keys.LIQUEFACTION_SPELL_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_LIQUEFACTION_SPELL);
    public static final RecipeType NETHER_DRAINING = new RecipeType(Keys.NETHER_DRAINING_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_NETHER_DRAINING);
    public static final RecipeType REALISATION_ALTAR_NORMAL = new RecipeType(Keys.REALISATION_ALTAR_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_REALISATION_ALTAR_NORMAL);
    public static final RecipeType REALISATION_ALTAR_SIGIL = new RecipeType(Keys.REALISATION_ALTAR_RECIPE_TYPE, CrystaStacks.RECIPE_TYPE_REALISATION_ALTAR_SIGIL);

    public static Map<ItemStack, ItemStack> getDrainingRecipes() {
        return DRAINING_RECIPES;
    }

    public static ItemStack[] getDummyRecipe(@Nonnull ItemStack output) {
        ItemStack[] itemStacks = new ItemStack[9];
        int i = 0;
        for (Map.Entry<StoryType, SlimefunItem> entry : Materials.getCrystalMap().get((Object)StoryRarity.MYTHICAL).entrySet()) {
            ItemStack itemStack = entry.getValue().getItem();
            DRAINING_RECIPES.put(itemStack, output);
            itemStacks[i] = itemStack;
            ++i;
        }
        return itemStacks;
    }

    @Generated
    private CrystaRecipeTypes() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

