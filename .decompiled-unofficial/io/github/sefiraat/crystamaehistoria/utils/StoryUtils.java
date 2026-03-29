/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.utils;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.BlockTier;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryChances;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStoriesDataType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

public final class StoryUtils {
    private static final Set<Material> metaBypass = EnumSet.of(Material.BEE_NEST, new Material[]{Material.BEEHIVE, Material.ENCHANTED_BOOK, Material.FILLED_MAP, Material.FIREWORK_ROCKET, Material.FIREWORK_STAR, Material.LINGERING_POTION, Material.PLAYER_HEAD, Material.POTION, Material.SPLASH_POTION, Material.SPAWNER, Material.SUSPICIOUS_STEW, Material.TIPPED_ARROW, Material.WRITTEN_BOOK, Material.AXOLOTL_BUCKET, Material.COD_BUCKET, Material.PUFFERFISH_BUCKET, Material.SALMON_BUCKET, Material.TROPICAL_FISH_BUCKET});

    @ParametersAreNonnullByDefault
    public static boolean canBeStoried(ItemStack itemStack, int tier) {
        Material material = itemStack.getType();
        BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);
        return definition != null && definition.getBlockTier().tier <= tier && StoryUtils.isAllowed(itemStack);
    }

    @ParametersAreNonnullByDefault
    private static boolean isAllowed(ItemStack itemStack) {
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)itemStack);
        if (slimefunItem == null) {
            return metaBypass.contains(itemStack.getType()) || StoryUtils.isStoried(itemStack) || !itemStack.hasItemMeta();
        }
        return itemStack.getType() == Material.SPAWNER;
    }

    @ParametersAreNonnullByDefault
    public static boolean isStoried(@Nonnull ItemStack itemStack) {
        return PersistentDataAPI.hasBoolean((PersistentDataHolder)itemStack.getItemMeta(), (NamespacedKey)Keys.PDC_IS_STORIED);
    }

    @ParametersAreNonnullByDefault
    public static void makeStoried(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setBoolean((PersistentDataHolder)itemMeta, (NamespacedKey)Keys.PDC_IS_STORIED, (boolean)true);
        StoryUtils.setStoryLimits(itemMeta, StoryUtils.getStoryLimits(itemStack));
        itemStack.setItemMeta(itemMeta);
    }

    @ParametersAreNonnullByDefault
    private static void setStoryLimits(ItemMeta itemMeta, JsonObject jsonObject) {
        PersistentDataAPI.setJsonObject((PersistentDataHolder)itemMeta, (NamespacedKey)Keys.PDC_POTENTIAL_STORIES, (JsonObject)jsonObject);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static JsonObject getStoryLimits(ItemStack itemStack) {
        return PersistentDataAPI.getJsonObject((PersistentDataHolder)itemStack.getItemMeta(), (NamespacedKey)Keys.PDC_POTENTIAL_STORIES, (JsonObject)StoryUtils.getInitialStoryLimits(itemStack));
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static JsonObject getInitialStoryLimits(ItemStack itemStack) {
        Material material = itemStack.getType();
        BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);
        Preconditions.checkNotNull((Object)definition, (Object)"The selected material does not have a story definition. This shouldn't happen, SefiDumb\u2122");
        int availableStoryCount = ThreadLocalRandom.current().nextInt(definition.getBlockTier().minStories, definition.getBlockTier().maxStories + 1);
        int tier = definition.getBlockTier().tier;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("JS_S_AS", (JsonElement)new JsonPrimitive((Number)availableStoryCount));
        jsonObject.add("JS_S_T", (JsonElement)new JsonPrimitive((Number)tier));
        return jsonObject;
    }

    @ParametersAreNonnullByDefault
    public static boolean hasRemainingStorySlots(ItemStack itemStack) {
        return StoryUtils.getRemainingStoryAmount(itemStack) > 0;
    }

    @ParametersAreNonnullByDefault
    public static int getRemainingStoryAmount(ItemStack itemStack) {
        return StoryUtils.getMaxStoryAmount(itemStack) - StoryUtils.getStoryAmount(itemStack.getItemMeta());
    }

    @ParametersAreNonnullByDefault
    public static int getMaxStoryAmount(ItemStack itemStack) {
        return StoryUtils.getStoryLimits(itemStack).get("JS_S_AS").getAsInt();
    }

    @ParametersAreNonnullByDefault
    public static int getStoryAmount(ItemMeta itemMeta) {
        return PersistentDataAPI.getInt((PersistentDataHolder)itemMeta, (NamespacedKey)Keys.PDC_CURRENT_NUMBER_OF_STORIES, (int)0);
    }

    @ParametersAreNonnullByDefault
    public static void requestNewStory(ItemStack itemstack) {
        StoriesManager manager = CrystamaeHistoria.getStoriesManager();
        BlockDefinition definition = manager.getBlockDefinitionMap().get(itemstack.getType());
        BlockTier tier = definition.getBlockTier();
        StoryChances chance = tier.storyChances;
        List<StoryType> pool = definition.getPools();
        int rnd = ThreadLocalRandom.current().nextInt(1, 101);
        if (rnd <= chance.getMythical()) {
            StoryUtils.addStory(itemstack, pool, manager.getStoryMapMythical());
        } else if (rnd <= chance.getEpic()) {
            StoryUtils.addStory(itemstack, pool, manager.getStoryMapEpic());
        } else if (rnd <= chance.getRare()) {
            StoryUtils.addStory(itemstack, pool, manager.getStoryMapRare());
        } else if (rnd <= chance.getUncommon()) {
            StoryUtils.addStory(itemstack, pool, manager.getStoryMapUncommon());
        } else {
            StoryUtils.addStory(itemstack, pool, manager.getStoryMapCommon());
        }
    }

    @ParametersAreNonnullByDefault
    public static void addStory(ItemStack itemStack, List<StoryType> p, Map<String, Story> storyList) {
        StoryType st = p.get(ThreadLocalRandom.current().nextInt(0, p.size()));
        List availableStories = storyList.values().stream().filter(t -> t.getType() == st).collect(Collectors.toList());
        Story story = (Story)availableStories.get(ThreadLocalRandom.current().nextInt(0, availableStories.size()));
        StoryUtils.applyStory(itemStack, story);
        StoryUtils.incrementStoryAmount(itemStack);
    }

    @ParametersAreNonnullByDefault
    public static void applyStory(ItemStack itemStack, Story story) {
        ItemMeta im = itemStack.getItemMeta();
        List<Story> storyList = StoryUtils.getAllStories(itemStack);
        if (storyList == null) {
            storyList = new ArrayList<Story>();
        }
        storyList.add(story);
        DataTypeMethods.setCustom((PersistentDataHolder)im, Keys.PDC_STORIES, PersistentStoriesDataType.TYPE, storyList);
        itemStack.setItemMeta(im);
    }

    @ParametersAreNonnullByDefault
    public static void incrementStoryAmount(ItemStack itemStack) {
        StoryUtils.setStoryAmount(itemStack, StoryUtils.getStoryAmount(itemStack.getItemMeta()) + 1);
    }

    @ParametersAreNonnullByDefault
    @Nullable
    public static List<Story> getAllStories(ItemStack itemStack) {
        return DataTypeMethods.getCustom((PersistentDataHolder)itemStack.getItemMeta(), Keys.PDC_STORIES, PersistentStoriesDataType.TYPE);
    }

    @ParametersAreNonnullByDefault
    public static void setStoryAmount(ItemStack itemStack, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setInt((PersistentDataHolder)itemMeta, (NamespacedKey)Keys.PDC_CURRENT_NUMBER_OF_STORIES, (int)amount);
        if (amount >= StoryUtils.getMaxStoryAmount(itemStack)) {
            itemMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
            itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }
        itemStack.setItemMeta(itemMeta);
    }

    @ParametersAreNonnullByDefault
    public static void requestUniqueStory(ItemStack itemstack) {
        StoriesManager m = CrystamaeHistoria.getStoriesManager();
        BlockDefinition s = m.getBlockDefinitionMap().get(itemstack.getType());
        Story unique = s.getUnique();
        StoryUtils.applyStory(itemstack, unique);
    }

    @ParametersAreNonnullByDefault
    public static int removeStory(ItemStack itemStack, Story story) {
        ItemMeta im = itemStack.getItemMeta();
        List<Story> storyList = StoryUtils.getAllStories(itemStack);
        Preconditions.checkNotNull(storyList, (Object)"No storyList found when trying to remove.");
        storyList.remove(story);
        DataTypeMethods.setCustom((PersistentDataHolder)im, Keys.PDC_STORIES, PersistentStoriesDataType.TYPE, storyList);
        itemStack.setItemMeta(im);
        return storyList.size();
    }

    @Generated
    private StoryUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

