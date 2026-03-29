/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PersistentStoriesDataType
implements PersistentDataType<PersistentDataContainer[], List<Story>> {
    public static final PersistentDataType<PersistentDataContainer[], List<Story>> TYPE = new PersistentStoriesDataType();

    @Nonnull
    public Class<PersistentDataContainer[]> getPrimitiveType() {
        return PersistentDataContainer[].class;
    }

    @Nonnull
    public Class<List<Story>> getComplexType() {
        return List.class;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer[] toPrimitive(List<Story> complex, PersistentDataAdapterContext context) {
        PersistentDataContainer[] containers = new PersistentDataContainer[complex.size()];
        int i = 0;
        for (Story story : complex) {
            PersistentDataContainer container = context.newPersistentDataContainer();
            container.set(Keys.STORY_ID, PersistentDataType.STRING, (Object)story.getId());
            container.set(Keys.STORY_RARITY, PersistentDataType.INTEGER, (Object)story.getRarity().getId());
            containers[i] = container;
            ++i;
        }
        return containers;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public List<Story> fromPrimitive(PersistentDataContainer[] primitive, PersistentDataAdapterContext context) {
        ArrayList<Story> list = new ArrayList<Story>();
        for (PersistentDataContainer container : primitive) {
            String id = (String)container.get(Keys.STORY_ID, PersistentDataType.STRING);
            StoryRarity rarity = StoryRarity.getById((Integer)container.get(Keys.STORY_RARITY, PersistentDataType.INTEGER));
            list.add(CrystamaeHistoria.getStoriesManager().getStory(id, rarity));
        }
        return list;
    }
}

