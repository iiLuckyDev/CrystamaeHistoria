/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  lombok.Generated
 *  net.md_5.bungee.api.chat.TextComponent
 *  org.bukkit.Material
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package io.github.sefiraat.crystamaehistoria.managers;

import com.google.common.base.Preconditions;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.BlockTier;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryChances;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StoriesManager {
    private final Map<Material, BlockDefinition> blockDefinitionMap = new EnumMap<Material, BlockDefinition>(Material.class);
    private final Map<Integer, BlockTier> blockTierMap = new HashMap<Integer, BlockTier>();
    private final Map<String, Story> storyMapCommon = new HashMap<String, Story>();
    private final Map<String, Story> storyMapUncommon = new HashMap<String, Story>();
    private final Map<String, Story> storyMapRare = new HashMap<String, Story>();
    private final Map<String, Story> storyMapEpic = new HashMap<String, Story>();
    private final Map<String, Story> storyMapMythical = new HashMap<String, Story>();
    private final Map<String, Story> storyMapUnique = new HashMap<String, Story>();

    public StoriesManager() {
        this.fillBlockTierMap();
        this.fillStories();
        this.fillBlockDefinitions();
    }

    private void fillBlockTierMap() {
        this.blockTierMap.put(1, new BlockTier(1, 700, 3, 1, new StoryChances(85, 15, 0, 0, 0)));
        this.blockTierMap.put(2, new BlockTier(2, 600, 3, 2, new StoryChances(70, 25, 5, 0, 0)));
        this.blockTierMap.put(3, new BlockTier(3, 500, 4, 2, new StoryChances(50, 35, 10, 5, 0)));
        this.blockTierMap.put(4, new BlockTier(4, 400, 5, 3, new StoryChances(25, 40, 20, 10, 5)));
        this.blockTierMap.put(5, new BlockTier(5, 300, 5, 4, new StoryChances(5, 30, 30, 20, 15)));
    }

    private void fillStories() {
        FileConfiguration stories = CrystamaeHistoria.getConfigManager().getStories();
        ConfigurationSection common = stories.getConfigurationSection("COMMON");
        Preconditions.checkNotNull((Object)common, (Object)"Common story configuration is not found, changed or deleted.");
        this.fillMap(this.storyMapCommon, common, StoryRarity.COMMON);
        ConfigurationSection uncommon = stories.getConfigurationSection("UNCOMMON");
        Preconditions.checkNotNull((Object)uncommon, (Object)"Uncommon story configuration is not found, changed or deleted.");
        this.fillMap(this.storyMapUncommon, uncommon, StoryRarity.UNCOMMON);
        ConfigurationSection rare = stories.getConfigurationSection("RARE");
        Preconditions.checkNotNull((Object)rare, (Object)"Rare story configuration is not found, changed or deleted.");
        this.fillMap(this.storyMapRare, rare, StoryRarity.RARE);
        ConfigurationSection epic = stories.getConfigurationSection("EPIC");
        Preconditions.checkNotNull((Object)epic, (Object)"Epic story configuration is not found, changed or deleted.");
        this.fillMap(this.storyMapEpic, epic, StoryRarity.EPIC);
        ConfigurationSection mythical = stories.getConfigurationSection("MYTHICAL");
        Preconditions.checkNotNull((Object)mythical, (Object)"Mythical story configuration is not found, changed or deleted.");
        this.fillMap(this.storyMapMythical, mythical, StoryRarity.MYTHICAL);
    }

    private void fillBlockDefinitions() {
        FileConfiguration blocks = CrystamaeHistoria.getConfigManager().getBlocks();
        for (String key : blocks.getKeys(false)) {
            ConfigurationSection wholeSection = blocks.getConfigurationSection(key);
            if (wholeSection == null) {
                CrystamaeHistoria.getInstance().getLogger().info(MessageFormat.format("Whole section missing for story -> {0}", key));
                continue;
            }
            ConfigurationSection storySection = wholeSection.getConfigurationSection("story");
            if (storySection == null) {
                CrystamaeHistoria.getInstance().getLogger().info(MessageFormat.format("Ignoring a block with a missing story section -> {0}", key));
                continue;
            }
            String name = storySection.getString("name");
            Material material = Material.getMaterial((String)key);
            if (name == null) {
                CrystamaeHistoria.getInstance().getLogger().info(MessageFormat.format("Ignoring a story without a name -> {0}", key));
                continue;
            }
            if (material == null) {
                CrystamaeHistoria.getInstance().getLogger().info(MessageFormat.format("Ignoring a story with an invalid material -> {0}", key));
                continue;
            }
            Story story = new Story(storySection, StoryRarity.UNIQUE);
            int tier = wholeSection.getInt("tier");
            List<StoryType> types = wholeSection.getStringList("elements").stream().map(StoryType::getByName).collect(Collectors.toList());
            for (StoryType storyType : types) {
                if (storyType != null) continue;
                CrystamaeHistoria.getInstance().getLogger().info(MessageFormat.format("A block has a badly typed element -> {0}", key));
            }
            BlockDefinition blockDefinition = new BlockDefinition(material, this.blockTierMap.get(tier), types, story);
            this.blockDefinitionMap.put(material, blockDefinition);
            this.storyMapUnique.put(story.getId(), story);
        }
        CrystamaeHistoria.getInstance().getLogger().info(MessageFormat.format("Loaded: {0} unique (block) stories.", this.blockDefinitionMap.size()));
    }

    @ParametersAreNonnullByDefault
    private void fillMap(Map<String, Story> map, ConfigurationSection section, StoryRarity rarity) {
        for (String key : section.getKeys(false)) {
            ConfigurationSection storySection = section.getConfigurationSection(key);
            Preconditions.checkNotNull((Object)storySection, (Object)"Section is null, this doesn't make sense so don't worry.");
            Story story = new Story(storySection, rarity);
            map.put(story.getId(), story);
        }
    }

    @ParametersAreNonnullByDefault
    public static void rebuildStoriedStack(ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        StoriesManager.setName(itemStack, im);
        ArrayList<String> lore = new ArrayList<String>();
        List<Story> storyList = StoryUtils.getAllStories(itemStack);
        for (Story story : storyList) {
            lore.add("");
            lore.add(story.getDisplayName());
            lore.addAll(story.getStoryLore());
        }
        im.setLore(lore);
        itemStack.setItemMeta(im);
    }

    @ParametersAreNonnullByDefault
    private static void setName(ItemStack itemStack, ItemMeta im) {
        TextComponent name = new TextComponent("Storied " + TextUtils.toTitleCase(itemStack.getType().toString()));
        name.setColor(ThemeType.MAIN.getColor());
        name.setBold(Boolean.valueOf(true));
        im.setDisplayName(name.toLegacyText());
    }

    @ParametersAreNonnullByDefault
    public Story getStory(String id, StoryRarity storyRarity) {
        switch (storyRarity) {
            case COMMON: {
                return this.storyMapCommon.get(id);
            }
            case UNCOMMON: {
                return this.storyMapUncommon.get(id);
            }
            case RARE: {
                return this.storyMapRare.get(id);
            }
            case EPIC: {
                return this.storyMapEpic.get(id);
            }
            case MYTHICAL: {
                return this.storyMapMythical.get(id);
            }
            case UNIQUE: {
                return this.storyMapUnique.get(id);
            }
        }
        throw new IllegalStateException("Unexpected value: " + String.valueOf((Object)storyRarity));
    }

    @Generated
    public Map<Material, BlockDefinition> getBlockDefinitionMap() {
        return this.blockDefinitionMap;
    }

    @Generated
    public Map<Integer, BlockTier> getBlockTierMap() {
        return this.blockTierMap;
    }

    @Generated
    public Map<String, Story> getStoryMapCommon() {
        return this.storyMapCommon;
    }

    @Generated
    public Map<String, Story> getStoryMapUncommon() {
        return this.storyMapUncommon;
    }

    @Generated
    public Map<String, Story> getStoryMapRare() {
        return this.storyMapRare;
    }

    @Generated
    public Map<String, Story> getStoryMapEpic() {
        return this.storyMapEpic;
    }

    @Generated
    public Map<String, Story> getStoryMapMythical() {
        return this.storyMapMythical;
    }

    @Generated
    public Map<String, Story> getStoryMapUnique() {
        return this.storyMapUnique;
    }
}

