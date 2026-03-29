/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.common.StackUtils;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MachineInput;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.inventory.ItemStack;

final class MachineBlockRecipe {
    private final String[] strings;
    private final int[] amounts;
    final ItemStack output;
    private Map<String, MachineInput> lastMatch;

    MachineBlockRecipe(ItemStack output, ItemStack[] input) {
        this.output = output;
        HashMap<String, Integer> strings = new HashMap<String, Integer>();
        for (ItemStack item : input) {
            if (item == null || item.getType().isAir()) continue;
            String string = StackUtils.getId(item);
            if (string == null) {
                string = item.getType().name();
            }
            strings.compute(string, (k, v) -> v == null ? item.getAmount() : v + item.getAmount());
        }
        this.strings = strings.keySet().toArray(new String[0]);
        this.amounts = strings.values().stream().mapToInt(i -> i).toArray();
    }

    boolean check(Map<String, MachineInput> map) {
        for (int i = 0; i < this.strings.length; ++i) {
            MachineInput input = map.get(this.strings[i]);
            if (input != null && input.amount >= this.amounts[i]) continue;
            return false;
        }
        this.lastMatch = map;
        return true;
    }

    void consume() {
        block0: for (int i = 0; i < this.strings.length; ++i) {
            int consume = this.amounts[i];
            for (ItemStack item : this.lastMatch.get((Object)this.strings[i]).items) {
                int amt = item.getAmount();
                if (amt >= consume) {
                    ItemUtils.consumeItem((ItemStack)item, (int)consume, (boolean)true);
                    continue block0;
                }
                ItemUtils.consumeItem((ItemStack)item, (int)amt, (boolean)true);
                consume -= amt;
            }
        }
    }
}

