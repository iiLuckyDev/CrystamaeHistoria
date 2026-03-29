/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataType
 *  org.jetbrains.annotations.NotNull
 */
package com.jeff_media.morepersistentdatatypes.datatypes;

import com.jeff_media.morepersistentdatatypes.DataType;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class BlockDataArrayDataType
implements PersistentDataType<byte[], BlockData[]> {
    @NotNull
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    public Class<BlockData[]> getComplexType() {
        return BlockData[].class;
    }

    @NotNull
    public byte[] toPrimitive(@NotNull BlockData[] blockData, @NotNull PersistentDataAdapterContext context) {
        return (byte[])DataType.STRING_ARRAY.toPrimitive((Object)((String[])Arrays.stream(blockData).map(BlockData::getAsString).toArray(String[]::new)), context);
    }

    @NotNull
    public BlockData[] fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext context) {
        return (BlockData[])Arrays.stream((String[])DataType.STRING_ARRAY.fromPrimitive((Object)bytes, context)).map(Bukkit::createBlockData).toArray(BlockData[]::new);
    }
}

