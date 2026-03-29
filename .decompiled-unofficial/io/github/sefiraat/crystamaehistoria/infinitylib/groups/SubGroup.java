/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.groups;

import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@ParametersAreNonnullByDefault
public final class SubGroup
extends ItemGroup {
    public SubGroup(String key, ItemStack item) {
        this(key, item, 3);
    }

    public SubGroup(String key, ItemStack item, int tier) {
        super(AbstractAddon.createKey(key), item, tier);
    }

    public boolean isHidden(Player p) {
        return true;
    }
}

