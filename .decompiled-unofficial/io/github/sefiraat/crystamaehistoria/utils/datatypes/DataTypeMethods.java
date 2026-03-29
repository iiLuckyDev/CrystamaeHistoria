/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.NamespacedKey
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

public final class DataTypeMethods {
    @Nonnull
    @ParametersAreNonnullByDefault
    public static <T, Z> Optional<Z> getOptionalCustom(PersistentDataHolder holder, NamespacedKey key, PersistentDataType<T, Z> type) {
        return Optional.ofNullable(DataTypeMethods.getCustom(holder, key, type));
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static <T, Z> Z getCustom(PersistentDataHolder holder, NamespacedKey key, PersistentDataType<T, Z> type) {
        return (Z)holder.getPersistentDataContainer().get(key, type);
    }

    @ParametersAreNonnullByDefault
    public static <T, Z> Z getCustom(PersistentDataHolder holder, NamespacedKey key, PersistentDataType<T, Z> type, Z defaultVal) {
        return (Z)holder.getPersistentDataContainer().getOrDefault(key, type, defaultVal);
    }

    @ParametersAreNonnullByDefault
    public static <T, Z> boolean hasCustom(PersistentDataHolder holder, NamespacedKey key, PersistentDataType<T, Z> type) {
        return holder.getPersistentDataContainer().has(key, type);
    }

    @ParametersAreNonnullByDefault
    public static <T, Z> void setCustom(PersistentDataHolder holder, NamespacedKey key, PersistentDataType<T, Z> type, Z obj) {
        holder.getPersistentDataContainer().set(key, type, obj);
    }

    @Generated
    private DataTypeMethods() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

