/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 */
package io.github.sefiraat.crystamaehistoria.utils;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public final class WorldUtils {
    @Nonnull
    @ParametersAreNonnullByDefault
    public static List<Entity> getNearbyEntities(Block block, double x, double y, double z) {
        return (List)block.getWorld().getNearbyEntities(block.getLocation(), x, y, z);
    }

    @Generated
    private WorldUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

