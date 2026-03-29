/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class DummyGuideOnly {
    public static final SlimefunItemStack STACK = ThemeType.themedSlimefunItemStack("CRY_GUIDE_DUMMY", new ItemStack(Material.BOOK), ThemeType.MECHANISM, "Guide", "This is a guide only.", "Some crafts in Crystamae are very", "different compared to other addons", "so these are here just for clarification.");
    public static final RecipeType TYPE = new RecipeType(Keys.GUIDE_ONLY, STACK, new String[0]);

    @Generated
    private DummyGuideOnly() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

