/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PersistentStaveDataType
implements PersistentDataType<PersistentDataContainer[], Map<SpellSlot, InstancePlate>> {
    public static final PersistentDataType<PersistentDataContainer[], Map<SpellSlot, InstancePlate>> TYPE = new PersistentStaveDataType();

    @Nonnull
    public Class<PersistentDataContainer[]> getPrimitiveType() {
        return PersistentDataContainer[].class;
    }

    @Nonnull
    public Class<Map<SpellSlot, InstancePlate>> getComplexType() {
        return Map.class;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer[] toPrimitive(Map<SpellSlot, InstancePlate> complex, PersistentDataAdapterContext context) {
        PersistentDataContainer[] containers = new PersistentDataContainer[complex.size()];
        int i = 0;
        for (Map.Entry<SpellSlot, InstancePlate> spellTypeEntry : complex.entrySet()) {
            PersistentDataContainer container = context.newPersistentDataContainer();
            container.set(Keys.STAVE_SLOT, PersistentDataType.STRING, (Object)spellTypeEntry.getKey().toString());
            container.set(Keys.STAVE_PLATE, PersistentPlateDataType.TYPE, (Object)spellTypeEntry.getValue());
            containers[i] = container;
            ++i;
        }
        return containers;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public Map<SpellSlot, InstancePlate> fromPrimitive(PersistentDataContainer[] primitive, PersistentDataAdapterContext context) {
        EnumMap<SpellSlot, InstancePlate> plateStorageMap = new EnumMap<SpellSlot, InstancePlate>(SpellSlot.class);
        for (PersistentDataContainer container : primitive) {
            SpellSlot spellSlot = SpellSlot.valueOf((String)container.get(Keys.STAVE_SLOT, PersistentDataType.STRING));
            InstancePlate instancePlate = (InstancePlate)container.get(Keys.STAVE_PLATE, PersistentPlateDataType.TYPE);
            plateStorageMap.put(spellSlot, instancePlate);
        }
        return plateStorageMap;
    }
}

