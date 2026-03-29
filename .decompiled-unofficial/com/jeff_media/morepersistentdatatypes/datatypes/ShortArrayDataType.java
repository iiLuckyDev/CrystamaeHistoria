/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataType
 *  org.jetbrains.annotations.NotNull
 */
package com.jeff_media.morepersistentdatatypes.datatypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class ShortArrayDataType
implements PersistentDataType<byte[], short[]> {
    @NotNull
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    public Class<short[]> getComplexType() {
        return short[].class;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public byte[] toPrimitive(short[] shorts, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();){
            byte[] byArray;
            try (DataOutputStream dos = new DataOutputStream(bos);){
                dos.writeInt(shorts.length);
                for (short number : shorts) {
                    dos.writeShort(number);
                }
                dos.flush();
                byArray = bos.toByteArray();
            }
            return byArray;
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public short[] fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);){
            short[] sArray;
            try (DataInputStream dis = new DataInputStream(bis);){
                short[] shorts = new short[dis.readInt()];
                for (int i = 0; i < shorts.length; ++i) {
                    shorts[i] = dis.readShort();
                }
                sArray = shorts;
            }
            return sArray;
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

