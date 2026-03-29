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
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.MagicPaintbrush;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PaintProfile;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class BasicPaintbrush
extends LimitedUseItem
implements MagicPaintbrush {
    private static final NamespacedKey key = Keys.newKey("uses");
    private final PaintProfile profile;

    @ParametersAreNonnullByDefault
    public BasicPaintbrush(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, PaintProfile profile, int amount) {
        super(itemGroup, item, recipeType, recipe);
        this.profile = profile;
        this.setMaxUseCount(amount);
    }

    @Nonnull
    protected NamespacedKey getStorageKey() {
        return key;
    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (this.tryPaint(e, this.profile, false)) {
                this.damageItem(e.getPlayer(), e.getItem());
            }
        };
    }
}

