/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.ItemStack;

final class MachineInput {
    final List<ItemStack> items = new ArrayList<ItemStack>(2);
    int amount;

    MachineInput(ItemStack item) {
        this.add(item);
    }

    MachineInput add(ItemStack item) {
        this.items.add(item);
        this.amount += item.getAmount();
        return this;
    }
}

