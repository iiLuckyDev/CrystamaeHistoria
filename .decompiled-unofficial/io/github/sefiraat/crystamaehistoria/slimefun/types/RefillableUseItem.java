/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors
 *  io.github.thebusybiscuit.slimefun4.utils.LoreBuilder
 *  io.github.thebusybiscuit.slimefun4.utils.PatternUtils
 *  org.bukkit.NamespacedKey
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.slimefun.types;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.PatternUtils;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public abstract class RefillableUseItem
extends LimitedUseItem {
    @ParametersAreNonnullByDefault
    protected RefillableUseItem(ItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(group, item, recipeType, recipe);
    }

    @ParametersAreNonnullByDefault
    protected void refillItem(ItemStack itemStack) {
        this.refillItem(itemStack, 1);
    }

    @ParametersAreNonnullByDefault
    protected void refillItem(ItemStack item, int refillAmount) {
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = this.getStorageKey();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        int usesLeft = (Integer)pdc.getOrDefault(key, PersistentDataType.INTEGER, (Object)this.getMaxUseCount());
        usesLeft = Math.min(usesLeft + refillAmount, this.getMaxUseCount());
        pdc.set(key, PersistentDataType.INTEGER, (Object)usesLeft);
        this.updateLore(item, meta, usesLeft);
    }

    @ParametersAreNonnullByDefault
    protected void updateLore(ItemStack item, ItemMeta meta, int usesLeft) {
        List lore = meta.getLore();
        String newLine = ChatColors.color((String)LoreBuilder.usesLeft((int)usesLeft));
        if (lore != null && !lore.isEmpty()) {
            for (int i = 0; i < lore.size(); ++i) {
                if (!PatternUtils.USES_LEFT_LORE.matcher((CharSequence)lore.get(i)).matches()) continue;
                lore.set(i, newLine);
                meta.setLore(lore);
                item.setItemMeta(meta);
                return;
            }
        } else {
            meta.setLore(Collections.singletonList(newLine));
            item.setItemMeta(meta);
        }
    }
}

