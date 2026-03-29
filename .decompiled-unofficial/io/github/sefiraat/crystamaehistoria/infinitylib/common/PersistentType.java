/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.configuration.InvalidConfigurationException
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataType
 *  org.jetbrains.annotations.NotNull
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.common;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ParametersAreNonnullByDefault
public final class PersistentType<T, Z>
implements PersistentDataType<T, Z> {
    private static final Logger log = LoggerFactory.getLogger(PersistentType.class);
    public static final PersistentDataType<byte[], ItemStack> ITEM_STACK = new PersistentType<byte[], ItemStack>(byte[].class, ItemStack.class, itemStack -> {
        try {
            Map m = itemStack.serialize();
            YamlConfiguration yml = new YamlConfiguration();
            yml.set("i", (Object)m);
            return yml.saveToString().getBytes(StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            log.error("Failed to serialize ItemStack.", (Throwable)e);
            return new byte[0];
        }
    }, arr -> {
        if (((byte[])arr).length == 0) {
            return CustomItemStack.create((Material)Material.STONE, (String)"&cERROR", (String[])new String[0]);
        }
        try {
            String s = new String((byte[])arr, StandardCharsets.UTF_8);
            YamlConfiguration yml = new YamlConfiguration();
            yml.loadFromString(s);
            Object raw = yml.get("i");
            if (!(raw instanceof Map)) {
                log.error("ItemStack YAML missing 'i' map or wrong type: {}", raw == null ? "null" : raw.getClass());
                return CustomItemStack.create((Material)Material.STONE, (String)"&cERROR", (String[])new String[0]);
            }
            Map m = (Map)raw;
            return ItemStack.deserialize((Map)m);
        }
        catch (InvalidConfigurationException ex) {
            log.error("Invalid YAML while deserializing ItemStack.", (Throwable)ex);
            return CustomItemStack.create((Material)Material.STONE, (String)"&cERROR", (String[])new String[0]);
        }
        catch (Exception e) {
            log.error("Failed to deserialize ItemStack.", (Throwable)e);
            return CustomItemStack.create((Material)Material.STONE, (String)"&cERROR", (String[])new String[0]);
        }
    });
    public static final PersistentDataType<byte[], List<ItemStack>> ITEM_STACK_LIST = new PersistentType<byte[], List>(byte[].class, List.class, list -> {
        try {
            YamlConfiguration yml = new YamlConfiguration();
            ArrayList<Map> serialized = new ArrayList<Map>(list.size());
            for (ItemStack item : list) {
                serialized.add(item.serialize());
            }
            yml.set("items", serialized);
            return yml.saveToString().getBytes(StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            log.error("Failed to serialize ItemStack list.", (Throwable)e);
            return new byte[0];
        }
    }, arr -> {
        ArrayList<ItemStack> out = new ArrayList<ItemStack>();
        if (((byte[])arr).length == 0) {
            return out;
        }
        try {
            String s = new String((byte[])arr, StandardCharsets.UTF_8);
            YamlConfiguration yml = new YamlConfiguration();
            yml.loadFromString(s);
            Object raw = yml.get("items");
            if (!(raw instanceof List)) {
                log.error("ItemStack list YAML missing 'items' list or wrong type: {}", raw == null ? "null" : raw.getClass());
                return out;
            }
            List rawList = (List)raw;
            for (Object o : rawList) {
                if (o instanceof Map) {
                    Map map;
                    Map cast = map = (Map)o;
                    out.add(ItemStack.deserialize((Map)cast));
                    continue;
                }
                log.warn("Skipping non-map element in 'items': {}", o == null ? "null" : o.getClass());
            }
        }
        catch (InvalidConfigurationException ex) {
            log.error("Invalid YAML while deserializing ItemStack list.", (Throwable)ex);
        }
        catch (Exception e) {
            log.error("Failed to deserialize ItemStack list.", (Throwable)e);
        }
        return out;
    });
    public static final PersistentDataType<long[], Location> LOCATION = new PersistentDataType<long[], Location>(){

        @NotNull
        public Class<long[]> getPrimitiveType() {
            return long[].class;
        }

        @NotNull
        public Class<Location> getComplexType() {
            return Location.class;
        }

        public long @NotNull [] toPrimitive(@NotNull Location loc, @NotNull PersistentDataAdapterContext ctx) {
            long x = Double.doubleToLongBits(loc.getX());
            long y = Double.doubleToLongBits(loc.getY());
            long z = Double.doubleToLongBits(loc.getZ());
            UUID uuid = loc.getWorld() == null ? null : loc.getWorld().getUID();
            long msb = uuid == null ? 0L : uuid.getMostSignificantBits();
            long lsb = uuid == null ? 0L : uuid.getLeastSignificantBits();
            return new long[]{x, y, z, msb, lsb};
        }

        @NotNull
        public Location fromPrimitive(long @NotNull [] data, @NotNull PersistentDataAdapterContext ctx) {
            if (data.length < 5) {
                return new Location(null, 0.0, 0.0, 0.0);
            }
            double x = Double.longBitsToDouble(data[0]);
            double y = Double.longBitsToDouble(data[1]);
            double z = Double.longBitsToDouble(data[2]);
            long msb = data[3];
            long lsb = data[4];
            World world = null;
            if (msb != 0L || lsb != 0L) {
                UUID uuid = new UUID(msb, lsb);
                world = Bukkit.getWorld((UUID)uuid);
            }
            return new Location(world, x, y, z);
        }
    };
    public static final PersistentDataType<byte[], List<String>> STRING_LIST = new PersistentType<byte[], List>(byte[].class, List.class, list -> {
        try {
            YamlConfiguration yml = new YamlConfiguration();
            yml.set("l", list);
            return yml.saveToString().getBytes(StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            log.error("Failed to serialize String list.", (Throwable)e);
            return new byte[0];
        }
    }, arr -> {
        if (((byte[])arr).length == 0) {
            return Collections.emptyList();
        }
        try {
            String s = new String((byte[])arr, StandardCharsets.UTF_8);
            YamlConfiguration yml = new YamlConfiguration();
            yml.loadFromString(s);
            return yml.getStringList("l");
        }
        catch (InvalidConfigurationException ex) {
            log.error("Invalid YAML while deserializing String list.", (Throwable)ex);
            return Collections.emptyList();
        }
        catch (Exception e) {
            log.error("Failed to deserialize String list.", (Throwable)e);
            return Collections.emptyList();
        }
    });
    private final Class<T> primitive;
    private final Class<Z> complex;
    private final Function<Z, T> toPrimitive;
    private final Function<T, Z> toComplex;

    @Nonnull
    public Class<T> getPrimitiveType() {
        return this.primitive;
    }

    @Nonnull
    public Class<Z> getComplexType() {
        return this.complex;
    }

    @Nonnull
    public T toPrimitive(Z complex, PersistentDataAdapterContext context) {
        return this.toPrimitive.apply(complex);
    }

    @Nonnull
    public Z fromPrimitive(T primitive, PersistentDataAdapterContext context) {
        return this.toComplex.apply(primitive);
    }

    @Generated
    public PersistentType(Class<T> primitive, Class<Z> complex, Function<Z, T> toPrimitive, Function<T, Z> toComplex) {
        this.primitive = primitive;
        this.complex = complex;
        this.toPrimitive = toPrimitive;
        this.toComplex = toComplex;
    }
}

