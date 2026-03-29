/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 *  org.bukkit.persistence.PersistentDataAdapterContext
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 */
package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PersistentStoryChunkDataType
implements PersistentDataType<PersistentDataContainer[], List<Story>> {
    public static final PersistentDataType<PersistentDataContainer[], List<Story>> TYPE = new PersistentStoryChunkDataType();

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
            container.set(Keys.RESOLUTION_STORY_LOCATION, PersistentDataType.LONG, (Object)story.getBlockPosition().getPosition());
            container.set(Keys.RESOLUTION_STORY_WORLD, PersistentUUIDDataType.TYPE, (Object)story.getBlockPosition().getWorld().getUID());
            container.set(Keys.STORY_IS_GILDED, DataType.BOOLEAN, (Object)story.isGilded());
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
            long locationLong = (Long)container.get(Keys.RESOLUTION_STORY_LOCATION, PersistentDataType.LONG);
            UUID worldUuid = (UUID)container.get(Keys.RESOLUTION_STORY_WORLD, PersistentUUIDDataType.TYPE);
            World world = Bukkit.getWorld((UUID)worldUuid);
            BlockPosition position = new BlockPosition(world, locationLong);
            Boolean gilded = (Boolean)container.get(Keys.STORY_IS_GILDED, DataType.BOOLEAN);
            Story story = CrystamaeHistoria.getStoriesManager().getStory(id, rarity).copy();
            story.setBlockPosition(position);
            story.setGilded(gilded != null && gilded != false);
            list.add(story);
        }
        return list;
    }
}

