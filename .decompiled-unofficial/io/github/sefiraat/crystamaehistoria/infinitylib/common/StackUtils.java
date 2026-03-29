/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  lombok.Generated
 *  net.kyori.adventure.text.Component
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.common;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

@ParametersAreNonnullByDefault
public final class StackUtils {
    private static final NamespacedKey ID_KEY = Slimefun.getItemDataService().getKey();

    @Nullable
    public static String getId(@Nullable ItemStack item) {
        if (item == null || item.getType().isAir()) {
            return null;
        }
        SlimefunItem sf = SlimefunItem.getByItem((ItemStack)item);
        return sf != null ? sf.getId() : null;
    }

    @Nullable
    public static String getId(ItemMeta meta) {
        return (String)meta.getPersistentDataContainer().get(ID_KEY, PersistentDataType.STRING);
    }

    @Nonnull
    public static String getIdOrType(@Nullable ItemStack item) {
        if (item == null || item.getType().isAir()) {
            return "AIR";
        }
        SlimefunItem sf = SlimefunItem.getByItem((ItemStack)item);
        return sf != null ? sf.getId() : item.getType().name();
    }

    @Nullable
    public static ItemStack itemById(String id) {
        SlimefunItem item = SlimefunItem.getById((String)id);
        return item == null ? null : item.getItem().clone();
    }

    @Nonnull
    public static ItemStack itemByIdOrType(String idOrType) {
        SlimefunItem item = SlimefunItem.getById((String)idOrType);
        return item == null ? new ItemStack(Material.valueOf((String)idOrType)) : item.getItem().clone();
    }

    public static boolean isSimilar(@Nullable ItemStack first, @Nullable ItemStack second) {
        if (first == null || first.getType().isAir()) {
            return second == null || second.getType().isAir();
        }
        if (second == null || second.getType().isAir()) {
            return false;
        }
        if (first.hasItemMeta()) {
            if (second.hasItemMeta()) {
                ItemMeta firstMeta = first.getItemMeta();
                ItemMeta secondMeta = second.getItemMeta();
                String firstId = StackUtils.getId(firstMeta);
                if (firstId == null) {
                    if (StackUtils.getId(secondMeta) == null) {
                        if (first.getType() == second.getType()) {
                            boolean bHas;
                            boolean aHas = firstMeta.hasDisplayName();
                            if (aHas != (bHas = secondMeta.hasDisplayName())) {
                                return false;
                            }
                            if (!aHas) {
                                return true;
                            }
                            Component a = firstMeta.displayName();
                            Component b = secondMeta.displayName();
                            return Objects.equals(a, b);
                        }
                        return false;
                    }
                    return false;
                }
                return firstId.equals(StackUtils.getId(secondMeta));
            }
            return false;
        }
        if (second.hasItemMeta()) {
            return false;
        }
        return first.getType() == second.getType();
    }

    @Generated
    private StackUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

