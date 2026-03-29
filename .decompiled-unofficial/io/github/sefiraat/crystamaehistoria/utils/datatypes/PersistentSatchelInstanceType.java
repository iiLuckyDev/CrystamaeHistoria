/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.NamespacedKey
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.satchel.SatchelInstance;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import javax.annotation.Nonnull;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PersistentSatchelInstanceType
implements PersistentDataType<PersistentDataContainer, SatchelInstance> {
    public static final PersistentDataType<PersistentDataContainer, SatchelInstance> TYPE = new PersistentSatchelInstanceType();
    public static final NamespacedKey SATCHEL_ID = Keys.newKey("satchel_id");
    public static final NamespacedKey SATCHEL_TIER = Keys.newKey("satchel_tier");
    public static final NamespacedKey SATCHEL_LAST_USER = Keys.newKey("satchel_last_user");
    public static final NamespacedKey UNIQUE = Keys.newKey("amount_unique");
    public static final NamespacedKey COMMON = Keys.newKey("amount_common");
    public static final NamespacedKey UNCOMMON = Keys.newKey("amount_uncommon");
    public static final NamespacedKey RARE = Keys.newKey("amount_rare");
    public static final NamespacedKey EPIC = Keys.newKey("amount_epic");
    public static final NamespacedKey MYTHICAL = Keys.newKey("amount_mythical");

    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Nonnull
    public Class<SatchelInstance> getComplexType() {
        return SatchelInstance.class;
    }

    @Nonnull
    public PersistentDataContainer toPrimitive(@Nonnull SatchelInstance complex, @Nonnull PersistentDataAdapterContext context) {
        PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(SATCHEL_ID, DataType.LONG, (Object)complex.getId());
        container.set(SATCHEL_TIER, DataType.INTEGER, (Object)complex.getTier());
        container.set(SATCHEL_LAST_USER, DataType.STRING, (Object)complex.getLastUser());
        container.set(UNIQUE, DataType.INTEGER_ARRAY, (Object)complex.getAmounts().get((Object)StoryRarity.UNIQUE));
        container.set(COMMON, DataType.INTEGER_ARRAY, (Object)complex.getAmounts().get((Object)StoryRarity.COMMON));
        container.set(UNCOMMON, DataType.INTEGER_ARRAY, (Object)complex.getAmounts().get((Object)StoryRarity.UNCOMMON));
        container.set(RARE, DataType.INTEGER_ARRAY, (Object)complex.getAmounts().get((Object)StoryRarity.RARE));
        container.set(EPIC, DataType.INTEGER_ARRAY, (Object)complex.getAmounts().get((Object)StoryRarity.EPIC));
        container.set(MYTHICAL, DataType.INTEGER_ARRAY, (Object)complex.getAmounts().get((Object)StoryRarity.MYTHICAL));
        return container;
    }

    @Nonnull
    public SatchelInstance fromPrimitive(@Nonnull PersistentDataContainer primitive, @Nonnull PersistentDataAdapterContext context) {
        long id = (Long)primitive.get(SATCHEL_ID, DataType.LONG);
        int tier = (Integer)primitive.get(SATCHEL_TIER, DataType.INTEGER);
        String name = (String)primitive.get(SATCHEL_LAST_USER, DataType.STRING);
        SatchelInstance instance = new SatchelInstance(id, tier);
        instance.setLastUser(name);
        instance.setAmounts(StoryRarity.UNIQUE, (int[])primitive.get(UNIQUE, DataType.INTEGER_ARRAY));
        instance.setAmounts(StoryRarity.COMMON, (int[])primitive.get(COMMON, DataType.INTEGER_ARRAY));
        instance.setAmounts(StoryRarity.UNCOMMON, (int[])primitive.get(UNCOMMON, DataType.INTEGER_ARRAY));
        instance.setAmounts(StoryRarity.RARE, (int[])primitive.get(RARE, DataType.INTEGER_ARRAY));
        instance.setAmounts(StoryRarity.EPIC, (int[])primitive.get(EPIC, DataType.INTEGER_ARRAY));
        instance.setAmounts(StoryRarity.MYTHICAL, (int[])primitive.get(MYTHICAL, DataType.INTEGER_ARRAY));
        return instance;
    }
}

