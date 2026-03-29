package com.jeff_media.morepersistentdatatypes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class DataType {

    public static final PersistentDataType<Byte, Boolean> BOOLEAN = new BooleanType();
    public static final PersistentDataType<byte[], double[]> DOUBLE_ARRAY = new DoubleArrayType();
    public static final PersistentDataType<Integer, Integer> INTEGER = PersistentDataType.INTEGER;
    public static final PersistentDataType<int[], int[]> INTEGER_ARRAY = PersistentDataType.INTEGER_ARRAY;
    public static final PersistentDataType<String, Location> LOCATION = new LocationType();
    public static final PersistentDataType<Long, Long> LONG = PersistentDataType.LONG;
    public static final PersistentDataType<String, String> STRING = PersistentDataType.STRING;

    private DataType() {
        throw new IllegalStateException("Utility class");
    }

    private static final class BooleanType implements PersistentDataType<Byte, Boolean> {

        @Override
        @Nonnull
        public Class<Byte> getPrimitiveType() {
            return Byte.class;
        }

        @Override
        @Nonnull
        public Class<Boolean> getComplexType() {
            return Boolean.class;
        }

        @Override
        @Nonnull
        public Byte toPrimitive(@Nonnull Boolean complex, @Nonnull PersistentDataAdapterContext context) {
            return (byte) (complex ? 1 : 0);
        }

        @Override
        @Nonnull
        public Boolean fromPrimitive(@Nonnull Byte primitive, @Nonnull PersistentDataAdapterContext context) {
            return primitive != 0;
        }
    }

    private static final class DoubleArrayType implements PersistentDataType<byte[], double[]> {

        @Override
        @Nonnull
        public Class<byte[]> getPrimitiveType() {
            return byte[].class;
        }

        @Override
        @Nonnull
        public Class<double[]> getComplexType() {
            return double[].class;
        }

        @Override
        @Nonnull
        public byte[] toPrimitive(@Nonnull double[] complex, @Nonnull PersistentDataAdapterContext context) {
            ByteBuffer buffer = ByteBuffer.allocate(complex.length * Double.BYTES);
            for (double value : complex) {
                buffer.putDouble(value);
            }
            return buffer.array();
        }

        @Override
        @Nonnull
        public double[] fromPrimitive(@Nonnull byte[] primitive, @Nonnull PersistentDataAdapterContext context) {
            if (primitive.length % Double.BYTES != 0) {
                throw new IllegalArgumentException("Invalid double array payload length: " + primitive.length);
            }

            ByteBuffer buffer = ByteBuffer.wrap(primitive);
            double[] values = new double[primitive.length / Double.BYTES];
            for (int i = 0; i < values.length; i++) {
                values[i] = buffer.getDouble();
            }
            return values;
        }
    }

    private static final class LocationType implements PersistentDataType<String, Location> {

        @Override
        @Nonnull
        public Class<String> getPrimitiveType() {
            return String.class;
        }

        @Override
        @Nonnull
        public Class<Location> getComplexType() {
            return Location.class;
        }

        @Override
        @Nonnull
        public String toPrimitive(@Nonnull Location complex, @Nonnull PersistentDataAdapterContext context) {
            World world = complex.getWorld();
            String worldId = world == null ? "null" : world.getUID().toString();
            return worldId
                + ";"
                + complex.getX()
                + ";"
                + complex.getY()
                + ";"
                + complex.getZ()
                + ";"
                + complex.getYaw()
                + ";"
                + complex.getPitch();
        }

        @Override
        @Nonnull
        public Location fromPrimitive(@Nonnull String primitive, @Nonnull PersistentDataAdapterContext context) {
            String[] parts = primitive.split(";", 6);
            if (parts.length != 6) {
                throw new IllegalArgumentException("Invalid location payload: " + primitive);
            }

            World world = "null".equals(parts[0]) ? null : Bukkit.getWorld(UUID.fromString(parts[0]));
            return new Location(
                world,
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Float.parseFloat(parts[4]),
                Float.parseFloat(parts[5])
            );
        }
    }
}
