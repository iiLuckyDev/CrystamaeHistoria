/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataType
 *  org.jetbrains.annotations.NotNull
 */
package com.jeff_media.morepersistentdatatypes.datatypes;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CharArrayDataType
implements PersistentDataType<int[], char[]> {
    @NotNull
    public Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @NotNull
    public Class<char[]> getComplexType() {
        return char[].class;
    }

    @NotNull
    public int[] toPrimitive(char[] chars, @NotNull PersistentDataAdapterContext context) {
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; ++i) {
            ints[i] = chars[i];
        }
        return ints;
    }

    @NotNull
    public char[] fromPrimitive(int[] ints, @NotNull PersistentDataAdapterContext context) {
        char[] chars = new char[ints.length];
        for (int i = 0; i < ints.length; ++i) {
            chars[i] = (char)ints[i];
        }
        return chars;
    }
}

