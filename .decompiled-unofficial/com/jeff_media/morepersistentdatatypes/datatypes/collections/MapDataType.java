/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.NamespacedKey
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package com.jeff_media.morepersistentdatatypes.datatypes.collections;

import com.jeff_media.morepersistentdatatypes.DataType;
import java.util.Map;
import java.util.function.Supplier;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MapDataType<M extends Map<K, V>, K, V>
implements PersistentDataType<PersistentDataContainer, M> {
    private static final String E_KEY_MUST_NOT_BE_NULL = "Maps stored in a PersistentDataContainer must not contain any null keys.";
    private static final String E_NOT_A_MAP = "Not a map.";
    private static final NamespacedKey KEY_SIZE = DataType.Utils.getKeyKey("s");
    private final Class<M> mapClazz;
    private final Supplier<? extends M> mapSupplier;
    private final PersistentDataType<?, K> keyDataType;
    private final PersistentDataType<?, V> valueDataType;

    public MapDataType(@NotNull Supplier<? extends M> mapSupplier, @NotNull PersistentDataType<?, K> keyDataType, @NotNull PersistentDataType<?, V> valueDataType) {
        this.mapSupplier = mapSupplier;
        this.mapClazz = ((Map)mapSupplier.get()).getClass();
        this.keyDataType = keyDataType;
        this.valueDataType = valueDataType;
    }

    public static Builder<Map<Object, Object>, Object, Object> builder() {
        return new Builder<Map<Object, Object>, Object, Object>();
    }

    @NotNull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @NotNull
    public Class<M> getComplexType() {
        return this.mapClazz;
    }

    @NotNull
    public PersistentDataContainer toPrimitive(@NotNull M map, @NotNull PersistentDataAdapterContext context) {
        PersistentDataContainer pdc = context.newPersistentDataContainer();
        int index = 0;
        int size = map.size();
        pdc.set(KEY_SIZE, DataType.INTEGER, (Object)size);
        for (Object key : map.keySet()) {
            if (key == null) {
                throw new IllegalArgumentException(E_KEY_MUST_NOT_BE_NULL);
            }
            Object value = map.get(key);
            if (value != null) {
                pdc.set(DataType.Utils.getValueKey(index), this.valueDataType, value);
            }
            pdc.set(DataType.Utils.getKeyKey(index++), this.keyDataType, key);
        }
        return pdc;
    }

    @NotNull
    public M fromPrimitive(@NotNull PersistentDataContainer pdc, @NotNull PersistentDataAdapterContext context) {
        Map map = (Map)this.mapSupplier.get();
        Integer size = (Integer)pdc.get(KEY_SIZE, DataType.INTEGER);
        if (size == null) {
            throw new IllegalArgumentException(E_NOT_A_MAP);
        }
        for (int i = 0; i < size; ++i) {
            Object key = pdc.get(DataType.Utils.getKeyKey(i), this.keyDataType);
            map.put(key, pdc.get(DataType.Utils.getValueKey(i), this.valueDataType));
        }
        return (M)map;
    }

    public static class Builder<M extends Map<K, V>, K, V> {
        private PersistentDataType<?, K> keyDataType;
        private PersistentDataType<?, V> valueDataType;

        @Contract(value="_ -> this")
        public <K1 extends K> Builder<?, K1, V> keyDataType(@NotNull PersistentDataType<?, K1> keyDataType) {
            this.keyDataType = keyDataType;
            return this;
        }

        @Contract(value="_ -> this")
        public <V1 extends V> Builder<?, K, V1> valueDataType(@NotNull PersistentDataType<?, V1> valueDataType) {
            this.valueDataType = valueDataType;
            return this;
        }

        @Contract(value="_ -> new")
        public <M1 extends M> MapDataType<M1, K, V> build(@NotNull Supplier<? extends M1> mapSupplier) {
            if (this.keyDataType == null) {
                throw new IllegalStateException("keyDataType must not be null");
            }
            if (this.valueDataType == null) {
                throw new IllegalStateException("valueDataType must not be null");
            }
            return new MapDataType<M1, K, V>(mapSupplier, this.keyDataType, this.valueDataType);
        }
    }
}

