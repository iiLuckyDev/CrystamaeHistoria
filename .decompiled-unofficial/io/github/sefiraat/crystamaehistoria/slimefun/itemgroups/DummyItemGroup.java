/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DummyItemGroup
extends ItemGroup {
    @ParametersAreNonnullByDefault
    public DummyItemGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @ParametersAreNonnullByDefault
    public boolean isHidden(Player p) {
        return true;
    }
}

