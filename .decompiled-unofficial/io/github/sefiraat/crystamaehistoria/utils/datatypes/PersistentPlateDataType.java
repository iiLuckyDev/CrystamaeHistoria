/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PersistentPlateDataType
implements PersistentDataType<PersistentDataContainer, InstancePlate> {
    public static final PersistentDataType<PersistentDataContainer, InstancePlate> TYPE = new PersistentPlateDataType();

    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Nonnull
    public Class<InstancePlate> getComplexType() {
        return InstancePlate.class;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer toPrimitive(InstancePlate complex, PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(Keys.PLATE_TIER, PersistentDataType.INTEGER, (Object)complex.getTier());
        container.set(Keys.PLATE_SPELL, PersistentDataType.STRING, (Object)complex.getStoredSpell().getId());
        container.set(Keys.PLATE_CHARGES, PersistentDataType.INTEGER, (Object)complex.getCrysta());
        container.set(Keys.PLATE_COOLDOWN, PersistentDataType.LONG, (Object)complex.getCooldown());
        return container;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public InstancePlate fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        InstancePlate instancePlate = new InstancePlate((Integer)primitive.get(Keys.PLATE_TIER, PersistentDataType.INTEGER), SpellType.valueOf((String)primitive.get(Keys.PLATE_SPELL, PersistentDataType.STRING)), (Integer)primitive.get(Keys.PLATE_CHARGES, PersistentDataType.INTEGER));
        instancePlate.setCooldown((Long)primitive.get(Keys.PLATE_COOLDOWN, PersistentDataType.LONG));
        return instancePlate;
    }
}

