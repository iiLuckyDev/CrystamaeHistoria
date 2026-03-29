/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.FireworkEffect
 *  org.bukkit.Location
 *  org.bukkit.NamespacedKey
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.attribute.AttributeModifier
 *  org.bukkit.block.banner.Pattern
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.serialization.ConfigurationSerializable
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.util.BlockVector
 *  org.bukkit.util.BoundingBox
 *  org.bukkit.util.Vector
 *  org.jetbrains.annotations.NotNull
 */
package com.jeff_media.morepersistentdatatypes;

import com.jeff_media.morepersistentdatatypes.datatypes.BlockDataArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.BooleanArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.CharArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.DoubleArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.FileConfigurationDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.FloatArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.GenericDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.ShortArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.StringArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.UuidDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.ArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.CollectionDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.collections.MapDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableDataType;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface DataType {
    public static final PersistentDataType<byte[], AttributeModifier> ATTRIBUTE_MODIFIER = new ConfigurationSerializableDataType<AttributeModifier>(AttributeModifier.class);
    public static final PersistentDataType<byte[], AttributeModifier[]> ATTRIBUTE_MODIFIER_ARRAY = new ConfigurationSerializableArrayDataType(AttributeModifier[].class);
    public static final PersistentDataType<String, BlockData> BLOCK_DATA = new GenericDataType<String, BlockData>(String.class, BlockData.class, Bukkit::createBlockData, BlockData::getAsString);
    public static final PersistentDataType<byte[], BlockData[]> BLOCK_DATA_ARRAY = new BlockDataArrayDataType();
    public static final PersistentDataType<byte[], BlockVector> BLOCK_VECTOR = new ConfigurationSerializableDataType<BlockVector>(BlockVector.class);
    public static final PersistentDataType<byte[], BlockVector[]> BLOCK_VECTOR_ARRAY = new ConfigurationSerializableArrayDataType(BlockVector[].class);
    public static final PersistentDataType<byte[], BoundingBox> BOUNDING_BOX = new ConfigurationSerializableDataType<BoundingBox>(BoundingBox.class);
    public static final PersistentDataType<byte[], BoundingBox[]> BOUNDING_BOX_ARRAY = new ConfigurationSerializableArrayDataType(BoundingBox[].class);
    public static final PersistentDataType<byte[], Color> COLOR = new ConfigurationSerializableDataType<Color>(Color.class);
    public static final PersistentDataType<byte[], Color[]> COLOR_ARRAY = new ConfigurationSerializableArrayDataType(Color[].class);
    public static final PersistentDataType<byte[], ConfigurationSerializable> CONFIGURATION_SERIALIZABLE = new ConfigurationSerializableDataType<ConfigurationSerializable>(ConfigurationSerializable.class);
    public static final PersistentDataType<byte[], ConfigurationSerializable[]> CONFIGURATION_SERIALIZABLE_ARRAY = new ConfigurationSerializableArrayDataType(ConfigurationSerializable[].class);
    public static final PersistentDataType<Long, Date> DATE = new GenericDataType<Long, Date>(Long.class, Date.class, Date::new, Date::getTime);
    public static final PersistentDataType<String, FileConfiguration> FILE_CONFIGURATION = new FileConfigurationDataType();
    public static final PersistentDataType<byte[], FireworkEffect> FIREWORK_EFFECT = new ConfigurationSerializableDataType<FireworkEffect>(FireworkEffect.class);
    public static final PersistentDataType<byte[], FireworkEffect[]> FIREWORK_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType(FireworkEffect[].class);
    public static final PersistentDataType<byte[], ItemMeta> ITEM_META = new ConfigurationSerializableDataType<ItemMeta>(ItemMeta.class);
    public static final PersistentDataType<byte[], ItemMeta[]> ITEM_META_ARRAY = new ConfigurationSerializableArrayDataType(ItemMeta[].class);
    public static final PersistentDataType<byte[], ItemStack> ITEM_STACK = new ConfigurationSerializableDataType<ItemStack>(ItemStack.class);
    public static final PersistentDataType<byte[], ItemStack[]> ITEM_STACK_ARRAY = new ConfigurationSerializableArrayDataType(ItemStack[].class);
    public static final PersistentDataType<byte[], Location> LOCATION = new ConfigurationSerializableDataType<Location>(Location.class);
    public static final PersistentDataType<byte[], Location[]> LOCATION_ARRAY = new ConfigurationSerializableArrayDataType(Location[].class);
    public static final PersistentDataType<byte[], OfflinePlayer> OFFLINE_PLAYER = new ConfigurationSerializableDataType<OfflinePlayer>(OfflinePlayer.class);
    public static final PersistentDataType<byte[], OfflinePlayer[]> OFFLINE_PLAYER_ARRAY = new ConfigurationSerializableArrayDataType(OfflinePlayer[].class);
    public static final PersistentDataType<byte[], Pattern> PATTERN = new ConfigurationSerializableDataType<Pattern>(Pattern.class);
    public static final PersistentDataType<byte[], Pattern[]> PATTERN_ARRAY = new ConfigurationSerializableArrayDataType(Pattern[].class);
    public static final PersistentDataType<byte[], Player> PLAYER = new ConfigurationSerializableDataType<Player>(Player.class);
    public static final PersistentDataType<byte[], Player[]> PLAYER_ARRAY = new ConfigurationSerializableArrayDataType(Player[].class);
    public static final PersistentDataType<byte[], PotionEffect> POTION_EFFECT = new ConfigurationSerializableDataType<PotionEffect>(PotionEffect.class);
    public static final PersistentDataType<byte[], PotionEffect[]> POTION_EFFECT_ARRAY = new ConfigurationSerializableArrayDataType(PotionEffect[].class);
    public static final PersistentDataType<byte[], UUID> UUID = new UuidDataType();
    public static final PersistentDataType<byte[], Vector> VECTOR = new ConfigurationSerializableDataType<Vector>(Vector.class);
    public static final PersistentDataType<byte[], Vector[]> VECTOR_ARRAY = new ConfigurationSerializableArrayDataType(Vector[].class);
    public static final PersistentDataType<Byte, Boolean> BOOLEAN = new GenericDataType<Byte, Boolean>(Byte.class, Boolean.class, aByte -> aByte == 1, aBoolean -> aBoolean != false ? (byte)1 : (byte)0);
    public static final PersistentDataType<byte[], boolean[]> BOOLEAN_ARRAY = new BooleanArrayDataType();
    public static final PersistentDataType<Integer, Character> CHARACTER = new GenericDataType<Integer, Character>(Integer.class, Character.class, integer -> Character.valueOf((char)integer.intValue()), character -> character.charValue());
    public static final PersistentDataType<int[], char[]> CHARACTER_ARRAY = new CharArrayDataType();
    public static final PersistentDataType<byte[], double[]> DOUBLE_ARRAY = new DoubleArrayDataType();
    public static final PersistentDataType<byte[], float[]> FLOAT_ARRAY = new FloatArrayDataType();
    public static final PersistentDataType<byte[], short[]> SHORT_ARRAY = new ShortArrayDataType();
    public static final PersistentDataType<byte[], String[]> STRING_ARRAY = new StringArrayDataType(StandardCharsets.UTF_8);
    public static final PersistentDataType<Byte, Byte> BYTE = PersistentDataType.BYTE;
    public static final PersistentDataType<byte[], byte[]> BYTE_ARRAY = PersistentDataType.BYTE_ARRAY;
    public static final PersistentDataType<Double, Double> DOUBLE = PersistentDataType.DOUBLE;
    public static final PersistentDataType<Float, Float> FLOAT = PersistentDataType.FLOAT;
    public static final PersistentDataType<Integer, Integer> INTEGER = PersistentDataType.INTEGER;
    public static final PersistentDataType<int[], int[]> INTEGER_ARRAY = PersistentDataType.INTEGER_ARRAY;
    public static final PersistentDataType<Long, Long> LONG = PersistentDataType.LONG;
    public static final PersistentDataType<long[], long[]> LONG_ARRAY = PersistentDataType.LONG_ARRAY;
    public static final PersistentDataType<Short, Short> SHORT = PersistentDataType.SHORT;
    public static final PersistentDataType<String, String> STRING = PersistentDataType.STRING;
    public static final PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = PersistentDataType.TAG_CONTAINER;
    public static final PersistentDataType<PersistentDataContainer[], PersistentDataContainer[]> TAG_CONTAINER_ARRAY = PersistentDataType.TAG_CONTAINER_ARRAY;

    public static <E extends Enum<E>> PersistentDataType<String, E> asEnum(@NotNull Class<E> enumClazz) {
        return new GenericDataType<String, Enum>(String.class, enumClazz, s -> Enum.valueOf(enumClazz, s), Enum::name);
    }

    public static <T> ArrayDataType<T> asArray(@NotNull T[] array, @NotNull PersistentDataType<?, T> dataType) {
        return new ArrayDataType<T>(array, dataType);
    }

    public static <C extends Collection<T>, T> CollectionDataType<C, T> asGenericCollection(@NotNull Supplier<C> supplier, @NotNull PersistentDataType<?, T> type) {
        return new CollectionDataType<C, T>(supplier, type);
    }

    public static <T> CollectionDataType<List<T>, T> asList(@NotNull PersistentDataType<?, T> type) {
        return DataType.asGenericCollection(ArrayList::new, type);
    }

    public static <T> CollectionDataType<ArrayList<T>, T> asArrayList(@NotNull PersistentDataType<?, T> type) {
        return DataType.asGenericCollection(ArrayList::new, type);
    }

    public static <T> CollectionDataType<LinkedList<T>, T> asLinkedList(@NotNull PersistentDataType<?, T> type) {
        return DataType.asGenericCollection(LinkedList::new, type);
    }

    public static <T> CollectionDataType<Set<T>, T> asSet(@NotNull PersistentDataType<?, T> type) {
        return DataType.asGenericCollection(HashSet::new, type);
    }

    public static <T> CollectionDataType<HashSet<T>, T> asHashSet(@NotNull PersistentDataType<?, T> type) {
        return DataType.asGenericCollection(HashSet::new, type);
    }

    public static <T> CollectionDataType<CopyOnWriteArrayList<T>, T> asCopyOnWriteArrayList(@NotNull PersistentDataType<?, T> type) {
        return DataType.asGenericCollection(CopyOnWriteArrayList::new, type);
    }

    public static <T> CollectionDataType<CopyOnWriteArraySet<T>, T> asCopyOnWriteArraySet(@NotNull PersistentDataType<?, T> type) {
        return DataType.asGenericCollection(CopyOnWriteArraySet::new, type);
    }

    public static <E extends Enum<E>> CollectionDataType<EnumSet<E>, E> asEnumSet(@NotNull Class<E> enumClazz) {
        return DataType.asGenericCollection(() -> EnumSet.noneOf(enumClazz), DataType.asEnum(enumClazz));
    }

    public static <M extends Map<K, V>, K, V> MapDataType<M, K, V> asGenericMap(@NotNull Supplier<M> supplier, @NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return new MapDataType<M, K, V>(supplier, keyType, valueType);
    }

    public static <K, V> MapDataType<Map<K, V>, K, V> asMap(@NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(HashMap::new, keyType, valueType);
    }

    public static <K, V> MapDataType<HashMap<K, V>, K, V> asHashMap(@NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(HashMap::new, keyType, valueType);
    }

    public static <K, V> MapDataType<ConcurrentHashMap<K, V>, K, V> asConcurrentHashMap(@NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(ConcurrentHashMap::new, keyType, valueType);
    }

    public static <K, V> MapDataType<IdentityHashMap<K, V>, K, V> asIdentityHashMap(@NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(IdentityHashMap::new, keyType, valueType);
    }

    public static <K, V> MapDataType<LinkedHashMap<K, V>, K, V> asLinkedHashMap(@NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(LinkedHashMap::new, keyType, valueType);
    }

    public static <K, V> MapDataType<TreeMap<K, V>, K, V> asTreeMap(@NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(TreeMap::new, keyType, valueType);
    }

    public static <K, V> MapDataType<Hashtable<K, V>, K, V> asHashtable(@NotNull PersistentDataType<?, K> keyType, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(Hashtable::new, keyType, valueType);
    }

    public static <E extends Enum<E>, V> MapDataType<EnumMap<E, V>, E, V> asEnumMap(@NotNull Class<E> enumClazz, @NotNull PersistentDataType<?, V> valueType) {
        return DataType.asGenericMap(() -> new EnumMap(enumClazz), DataType.asEnum(enumClazz), valueType);
    }

    public static class Utils {
        private static final Map<String, NamespacedKey> KEY_KEYS = new HashMap<String, NamespacedKey>();
        private static final Map<String, NamespacedKey> VALUE_KEYS = new HashMap<String, NamespacedKey>();

        private Utils() {
        }

        public static NamespacedKey getKeyKey(int index) {
            return Utils.getKeyKey(String.valueOf(index));
        }

        public static NamespacedKey getKeyKey(String name) {
            return KEY_KEYS.computeIfAbsent(name, __ -> NamespacedKey.fromString((String)("k:" + name)));
        }

        public static NamespacedKey getValueKey(int index) {
            return Utils.getValueKey(String.valueOf(index));
        }

        public static NamespacedKey getValueKey(String name) {
            return VALUE_KEYS.computeIfAbsent(name, __ -> NamespacedKey.fromString((String)("v:" + name)));
        }

        static {
            IntStream.range(0, 100).forEach(number -> {
                Utils.getValueKey(number);
                Utils.getKeyKey(number);
            });
        }
    }
}

