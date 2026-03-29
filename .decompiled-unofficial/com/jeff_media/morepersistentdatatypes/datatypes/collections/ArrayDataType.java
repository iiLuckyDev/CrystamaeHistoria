/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.NamespacedKey
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.jetbrains.annotations.NotNull
 */
package com.jeff_media.morepersistentdatatypes.datatypes.collections;

import com.jeff_media.morepersistentdatatypes.DataType;
import java.lang.reflect.Array;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ArrayDataType<T>
implements PersistentDataType<PersistentDataContainer, T[]> {
    private static final String E_NOT_AN_ARRAY = "Not an array.";
    private static final NamespacedKey KEY_SIZE = DataType.Utils.getValueKey("s");
    private final Class<T[]> arrayClazz;
    private final Class<T> componentClazz;
    private final PersistentDataType<?, T> dataType;

    public ArrayDataType(@NotNull T[] array, @NotNull PersistentDataType<?, T> dataType) {
        this.arrayClazz = array.getClass();
        this.componentClazz = array.getClass().getComponentType();
        this.dataType = dataType;
    }

    @NotNull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @NotNull
    public Class<T[]> getComplexType() {
        return this.arrayClazz;
    }

    @NotNull
    public PersistentDataContainer toPrimitive(T[] array, @NotNull PersistentDataAdapterContext context) {
        PersistentDataContainer pdc = context.newPersistentDataContainer();
        pdc.set(KEY_SIZE, DataType.INTEGER, (Object)array.length);
        for (int i = 0; i < array.length; ++i) {
            T data = array[i];
            if (data == null) continue;
            pdc.set(DataType.Utils.getValueKey(i), this.dataType, data);
        }
        return pdc;
    }

    @NotNull
    public T[] fromPrimitive(@NotNull PersistentDataContainer pdc, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        Integer size = (Integer)pdc.get(KEY_SIZE, DataType.INTEGER);
        if (size == null) {
            throw new IllegalArgumentException(E_NOT_AN_ARRAY);
        }
        Object[] array = (Object[])Array.newInstance(this.componentClazz, (int)size);
        for (int i = 0; i < size; ++i) {
            array[i] = pdc.get(DataType.Utils.getValueKey(i), this.dataType);
        }
        return array;
    }
}

