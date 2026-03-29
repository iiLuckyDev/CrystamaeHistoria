/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  lombok.Generated
 *  org.bukkit.ChatColor
 *  org.bukkit.NamespacedKey
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

public final class GildingUtils {
    @ParametersAreNonnullByDefault
    public static boolean isGilded(ItemStack itemStack) {
        return PersistentDataAPI.hasBoolean((PersistentDataHolder)itemStack.getItemMeta(), (NamespacedKey)Keys.PDC_IS_GILDED);
    }

    @ParametersAreNonnullByDefault
    public static void makeGilded(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setBoolean((PersistentDataHolder)itemMeta, (NamespacedKey)Keys.PDC_IS_GILDED, (boolean)true);
        List lore = itemStack.getLore();
        lore.add("");
        lore.add(String.valueOf(ChatColor.YELLOW) + String.valueOf(ChatColor.BOLD) + "GILDED");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    @Generated
    private GildingUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

