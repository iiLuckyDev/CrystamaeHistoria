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

public class DoubleArrayDataType
implements PersistentDataType<byte[], double[]> {
    @NotNull
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    public Class<double[]> getComplexType() {
        return double[].class;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public byte[] toPrimitive(double[] doubles, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();){
            byte[] byArray;
            try (DataOutputStream dos = new DataOutputStream(bos);){
                dos.writeInt(doubles.length);
                for (double number : doubles) {
                    dos.writeDouble(number);
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
    public double[] fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);){
            double[] dArray;
            try (DataInputStream dis = new DataInputStream(bis);){
                double[] doubles = new double[dis.readInt()];
                for (int i = 0; i < doubles.length; ++i) {
                    doubles[i] = dis.readDouble();
                }
                dArray = doubles;
            }
            return dArray;
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

