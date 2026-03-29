/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.serialization.ConfigurationSerializable
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.util.io.BukkitObjectInputStream
 *  org.bukkit.util.io.BukkitObjectOutputStream
 *  org.jetbrains.annotations.NotNull
 */
package com.jeff_media.morepersistentdatatypes.datatypes.serializable;

import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableDataType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

public class ConfigurationSerializableArrayDataType<T extends ConfigurationSerializable>
implements PersistentDataType<byte[], T[]> {
    private final Class<T> type;
    private final Class<T[]> types;

    public ConfigurationSerializableArrayDataType(Class<T[]> types) {
        this.type = types.getComponentType();
        this.types = types;
    }

    @NotNull
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    public Class<T[]> getComplexType() {
        return this.types;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public byte[] toPrimitive(@NotNull T[] serializable, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();){
            byte[] byArray;
            try (BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream((OutputStream)outputStream);){
                bukkitObjectOutputStream.writeInt(serializable.length);
                for (T t : serializable) {
                    bukkitObjectOutputStream.writeObject(t);
                }
                byArray = outputStream.toByteArray();
            }
            return byArray;
        }
        catch (IOException e) {
            throw new UncheckedIOException(ConfigurationSerializableDataType.getExceptionMessage(this.type, ConfigurationSerializableDataType.SerializationType.SERIALIZATION), e);
        }
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public T[] fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);){
            ConfigurationSerializable[] configurationSerializableArray;
            try (BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream((InputStream)inputStream);){
                ConfigurationSerializable[] ts = (ConfigurationSerializable[])Array.newInstance(this.type, bukkitObjectInputStream.readInt());
                for (int i = 0; i < ts.length; ++i) {
                    ts[i] = (ConfigurationSerializable)bukkitObjectInputStream.readObject();
                }
                configurationSerializableArray = ts;
            }
            return configurationSerializableArray;
        }
        catch (EOFException e) {
            return (ConfigurationSerializable[])Array.newInstance(this.getComplexType().getComponentType(), 0);
        }
        catch (IOException e) {
            throw new UncheckedIOException(ConfigurationSerializableDataType.getExceptionMessage(this.type, ConfigurationSerializableDataType.SerializationType.DESERIALIZATION), e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(ConfigurationSerializableDataType.getExceptionMessage(this.type, ConfigurationSerializableDataType.SerializationType.DESERIALIZATION), e);
        }
    }
}

