/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import com.google.common.base.Preconditions;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public final class PersistentUUIDDataType
implements PersistentDataType<int[], UUID> {
    public static final PersistentDataType<int[], UUID> TYPE = new PersistentUUIDDataType();

    private PersistentUUIDDataType() {
    }

    @Nonnull
    public Class<int[]> getPrimitiveType() {
        return int[].class;
    }

    @Nonnull
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public int[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
        return PersistentUUIDDataType.toIntArray(complex);
    }

    @Nonnull
    public static int[] toIntArray(@Nonnull UUID uuid) {
        Preconditions.checkNotNull((Object)uuid, (Object)"The provided uuid cannot be null!");
        long mostSig = uuid.getMostSignificantBits();
        long leastSig = uuid.getLeastSignificantBits();
        return new int[]{(int)(mostSig >> 32), (int)mostSig, (int)(leastSig >> 32), (int)leastSig};
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public UUID fromPrimitive(int[] primitive, PersistentDataAdapterContext context) {
        return PersistentUUIDDataType.fromIntArray(primitive);
    }

    @Nonnull
    public static UUID fromIntArray(int[] ints) {
        Preconditions.checkNotNull((Object)ints, (Object)"The provided integer array cannot be null!");
        Preconditions.checkArgument((ints.length == 4 ? 1 : 0) != 0, (Object)"The integer array must have a length of 4.");
        return new UUID((long)ints[0] << 32 | (long)ints[1] & 0xFFFFFFFFL, (long)ints[2] << 32 | (long)ints[3] & 0xFFFFFFFFL);
    }
}

