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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

public class ConfigurationSerializableDataType<T extends ConfigurationSerializable>
implements PersistentDataType<byte[], T> {
    private final Class<T> type;

    public ConfigurationSerializableDataType(Class<T> type) {
        this.type = type;
    }

    @NotNull
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @NotNull
    public Class<T> getComplexType() {
        return this.type;
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public byte[] toPrimitive(@NotNull T serializable, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();){
            byte[] byArray;
            try (BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream((OutputStream)outputStream);){
                bukkitObjectOutputStream.writeObject(serializable);
                byArray = outputStream.toByteArray();
            }
            return byArray;
        }
        catch (IOException e) {
            throw new UncheckedIOException(ConfigurationSerializableDataType.getExceptionMessage(this.type, SerializationType.SERIALIZATION), e);
        }
    }

    /*
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public T fromPrimitive(@NotNull byte[] bytes, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);){
            ConfigurationSerializable configurationSerializable;
            try (BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream((InputStream)inputStream);){
                configurationSerializable = (ConfigurationSerializable)bukkitObjectInputStream.readObject();
            }
            return (T)configurationSerializable;
        }
        catch (IOException e) {
            throw new UncheckedIOException(ConfigurationSerializableDataType.getExceptionMessage(this.type, SerializationType.DESERIALIZATION), e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(ConfigurationSerializableDataType.getExceptionMessage(this.type, SerializationType.DESERIALIZATION), e);
        }
    }

    private static boolean isBukkitClass(Class<?> clazz) {
        return clazz.getPackage().getName().startsWith("org.bukkit.");
    }

    static String getExceptionMessage(Class<? extends ConfigurationSerializable> type, SerializationType serializationType) {
        String msg = "Could not " + (Object)((Object)serializationType) + " object of type " + type.getName() + ".";
        if (!ConfigurationSerializableDataType.isBukkitClass(type)) {
            msg = msg + " This is not a bug in MorePersistentDataTypes, but a bug in your " + (Object)((Object)serializationType) + ".";
            if (serializationType == SerializationType.DESERIALIZATION) {
                msg = msg + " Make sure that your class is properly registered for deserialization using org.bukkit.configuration.serialization.ConfigurationSerialization#registerClass(Class).";
            }
        }
        return msg;
    }

    static enum SerializationType {
        SERIALIZATION("serialization"),
        DESERIALIZATION("deserialization");

        private final String fancyName;

        private SerializationType(String fancyName) {
            this.fancyName = fancyName;
        }

        public String toString() {
            return this.fancyName;
        }
    }
}

