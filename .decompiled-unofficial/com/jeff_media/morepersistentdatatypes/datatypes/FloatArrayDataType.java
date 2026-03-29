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

public class FloatArrayDataType
implements PersistentDataType<byte[], float[]> {
    @NotNull
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    public Class<float[]> getComplexType() {
        return float[].class;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public byte[] toPrimitive(float[] floats, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();){
            byte[] byArray;
            try (DataOutputStream dos = new DataOutputStream(bos);){
                dos.writeInt(floats.length);
                for (float number : floats) {
                    dos.writeFloat(number);
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
    public float[] fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);){
            float[] fArray;
            try (DataInputStream dis = new DataInputStream(bis);){
                float[] floats = new float[dis.readInt()];
                for (int i = 0; i < floats.length; ++i) {
                    floats[i] = dis.readFloat();
                }
                fArray = floats;
            }
            return fArray;
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

