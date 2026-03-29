/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
 *  lombok.Generated
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.common.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import lombok.Generated;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class CraftingBlockRecipe {
    private final ItemStack[] recipe;
    final ItemStack output;
    final SlimefunItem item;

    CraftingBlockRecipe(ItemStack output, ItemStack[] recipe) {
        this.output = output;
        this.recipe = ItemStackSnapshot.wrapArray((ItemStack[])recipe);
        this.item = SlimefunItem.getByItem((ItemStack)output);
    }

    boolean check(ItemStackSnapshot[] input) {
        for (int i = 0; i < this.recipe.length; ++i) {
            boolean similar = StackUtils.isSimilar((ItemStack)input[i], this.recipe[i]);
            if (similar && (this.recipe[i] == null || this.recipe[i].getAmount() <= input[i].getAmount())) continue;
            return false;
        }
        return true;
    }

    boolean check(Player p) {
        return this.item == null || this.item.canUse(p, true);
    }

    void consume(ItemStack[] input) {
        for (int i = 0; i < this.recipe.length; ++i) {
            if (this.recipe[i] == null) continue;
            ItemUtils.consumeItem((ItemStack)input[i], (int)this.recipe[i].getAmount(), (boolean)true);
        }
    }

    @Generated
    public ItemStack[] recipe() {
        return this.recipe;
    }

    @Generated
    public ItemStack output() {
        return this.output;
    }

    @Generated
    public SlimefunItem item() {
        return this.item;
    }
}

