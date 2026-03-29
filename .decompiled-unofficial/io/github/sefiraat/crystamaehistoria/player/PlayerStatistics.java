/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.player.BlockRank;
import io.github.sefiraat.crystamaehistoria.player.GildingRank;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import java.text.MessageFormat;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PlayerStatistics {
    @ParametersAreNonnullByDefault
    public static void unlockSpell(Player player, SpellType spellType) {
        PlayerStatistics.unlockSpell(player.getUniqueId(), spellType);
    }

    @ParametersAreNonnullByDefault
    public static void unlockSpell(UUID player, SpellType spellType) {
        String path = MessageFormat.format("{0}.{1}.{2}.UNLOCKED", new Object[]{player, StatType.SPELL, spellType.getId()});
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, (Object)true);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedSpell(Player player, SpellType spellType) {
        return PlayerStatistics.hasUnlockedSpell(player.getUniqueId(), spellType);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedSpell(UUID player, SpellType spellType) {
        String path = MessageFormat.format("{0}.{1}.{2}.UNLOCKED", new Object[]{player, StatType.SPELL, spellType.getId()});
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(path);
    }

    @ParametersAreNonnullByDefault
    public static void addUsage(Player player, SpellType spellType) {
        PlayerStatistics.addUsage(player.getUniqueId(), spellType);
    }

    @ParametersAreNonnullByDefault
    public static void addUsage(UUID player, SpellType spellType) {
        int uses = PlayerStatistics.getUsages(player, spellType);
        String path = MessageFormat.format("{0}.{1}.{2}.TIMES_CAST", new Object[]{player, StatType.SPELL, spellType.getId()});
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, (Object)(++uses));
    }

    @ParametersAreNonnullByDefault
    public static int getUsages(UUID player, SpellType spellType) {
        String path = MessageFormat.format("{0}.{1}.{2}.TIMES_CAST", new Object[]{player, StatType.SPELL, spellType.getId()});
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getInt(path);
    }

    @ParametersAreNonnullByDefault
    public static int getUsages(Player player, SpellType spellType) {
        return PlayerStatistics.getUsages(player.getUniqueId(), spellType);
    }

    @ParametersAreNonnullByDefault
    public static void unlockUniqueStory(Player player, BlockDefinition definition) {
        PlayerStatistics.unlockUniqueStory(player.getUniqueId(), definition);
    }

    @ParametersAreNonnullByDefault
    public static void unlockUniqueStory(UUID player, BlockDefinition definition) {
        String path = MessageFormat.format("{0}.{1}.{2}.UNLOCKED", new Object[]{player, StatType.STORY, definition.getMaterial()});
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, (Object)true);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedUniqueStory(Player player, BlockDefinition definition) {
        return PlayerStatistics.hasUnlockedUniqueStory(player.getUniqueId(), definition);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedUniqueStory(UUID player, BlockDefinition definition) {
        return PlayerStatistics.hasUnlockedUniqueStory(player, definition.getMaterial());
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedUniqueStory(UUID player, Material material) {
        String path = MessageFormat.format("{0}.{1}.{2}.UNLOCKED", new Object[]{player, StatType.STORY, material});
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(path);
    }

    @ParametersAreNonnullByDefault
    public static void unlockStoryGilded(UUID player, BlockDefinition definition) {
        String path = MessageFormat.format("{0}.{1}.{2}.GILDED", new Object[]{player, StatType.STORY, definition.getMaterial()});
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, (Object)true);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedStoryGilded(Player player, BlockDefinition definition) {
        return PlayerStatistics.hasUnlockedStoryGilded(player.getUniqueId(), definition);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedStoryGilded(UUID player, BlockDefinition definition) {
        return PlayerStatistics.hasUnlockedStoryGilded(player, definition.getMaterial());
    }

    @ParametersAreNonnullByDefault
    public static boolean hasUnlockedStoryGilded(UUID player, Material material) {
        String path = MessageFormat.format("{0}.{1}.{2}.GILDED", new Object[]{player, StatType.STORY, material});
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(path);
    }

    @ParametersAreNonnullByDefault
    public static void addChronicle(Player player, BlockDefinition definition) {
        PlayerStatistics.addChronicle(player.getUniqueId(), definition);
    }

    @ParametersAreNonnullByDefault
    public static void addChronicle(UUID player, BlockDefinition definition) {
        int uses = PlayerStatistics.getChronicle(player, definition);
        String path = MessageFormat.format("{0}.{1}.{2}.TIMES_CHRONICLED", new Object[]{player, StatType.STORY, definition.getMaterial()});
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, (Object)(++uses));
    }

    @ParametersAreNonnullByDefault
    public static int getChronicle(UUID player, BlockDefinition definition) {
        String path = MessageFormat.format("{0}.{1}.{2}.TIMES_CHRONICLED", new Object[]{player, StatType.STORY, definition.getMaterial()});
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getInt(path);
    }

    @ParametersAreNonnullByDefault
    public static int getChronicle(Player player, BlockDefinition definition) {
        return PlayerStatistics.getChronicle(player.getUniqueId(), definition);
    }

    @ParametersAreNonnullByDefault
    public static void addRealisation(Player player, BlockDefinition definition) {
        PlayerStatistics.addChronicle(player.getUniqueId(), definition);
    }

    @ParametersAreNonnullByDefault
    public static void addRealisation(UUID player, BlockDefinition definition) {
        int uses = PlayerStatistics.getRealisation(player, definition);
        String path = MessageFormat.format("{0}.{1}.{2}.TIMES_REALISED", new Object[]{player, StatType.STORY, definition.getMaterial()});
        CrystamaeHistoria.getConfigManager().getPlayerStats().set(path, (Object)(++uses));
    }

    @ParametersAreNonnullByDefault
    public static int getRealisation(UUID player, BlockDefinition definition) {
        String path = MessageFormat.format("{0}.{1}.{2}.TIMES_REALISED", new Object[]{player, StatType.STORY, definition.getMaterial()});
        return CrystamaeHistoria.getConfigManager().getPlayerStats().getInt(path);
    }

    @ParametersAreNonnullByDefault
    public static int getRealisation(Player player, BlockDefinition definition) {
        return PlayerStatistics.getChronicle(player.getUniqueId(), definition);
    }

    @ParametersAreNonnullByDefault
    public static BlockRank getBlockRank(UUID uuid, BlockDefinition definition) {
        int chronicleAmount = Math.min(PlayerStatistics.getChronicle(uuid, definition), 100);
        int realisedAmount = Math.min(PlayerStatistics.getRealisation(uuid, definition), 100);
        int blockValue = (chronicleAmount + realisedAmount) / 2;
        return BlockRank.getByAmount(blockValue);
    }

    @ParametersAreNonnullByDefault
    public static StoryRank getStoryRank(UUID uuid) {
        int total = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().size();
        int unlocked = PlayerStatistics.getStoriesUnlocked(uuid);
        return StoryRank.getByPercent((double)unlocked / (double)total * 100.0);
    }

    @ParametersAreNonnullByDefault
    public static int getStoriesUnlocked(UUID uuid) {
        String path = MessageFormat.format("{0}.{1}", new Object[]{uuid, StatType.STORY});
        ConfigurationSection section = CrystamaeHistoria.getConfigManager().getPlayerStats().getConfigurationSection(path);
        if (section == null) {
            return 0;
        }
        int unlocked = 0;
        for (String story : section.getKeys(false)) {
            String storyPath = MessageFormat.format("{0}.{1}.{2}.UNLOCKED", new Object[]{uuid, StatType.STORY, story});
            if (!CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(storyPath)) continue;
            ++unlocked;
        }
        return unlocked;
    }

    @ParametersAreNonnullByDefault
    public static String getStoryRankString(UUID uuid) {
        int total = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().size();
        int unlocked = PlayerStatistics.getStoriesUnlocked(uuid);
        StoryRank storyRank = StoryRank.getByPercent((double)unlocked / (double)total * 100.0);
        return MessageFormat.format("{0}Chronicler Rank: {1}{2}{0} ({3}/{4})", ThemeType.PASSIVE.getColor(), storyRank.getTheme().getColor(), storyRank.getTheme().getLoreLine(), unlocked, total);
    }

    @ParametersAreNonnullByDefault
    public static SpellRank getSpellRank(UUID uuid) {
        int total = SpellType.getEnabledSpells().length;
        int unlocked = PlayerStatistics.getSpellsUnlocked(uuid);
        return SpellRank.getByPercent((double)unlocked / (double)total * 100.0);
    }

    @ParametersAreNonnullByDefault
    public static int getSpellsUnlocked(UUID uuid) {
        String path = MessageFormat.format("{0}.{1}", new Object[]{uuid, StatType.SPELL});
        ConfigurationSection section = CrystamaeHistoria.getConfigManager().getPlayerStats().getConfigurationSection(path);
        if (section == null) {
            return 0;
        }
        int unlocked = 0;
        for (String spell : section.getKeys(false)) {
            String storyPath = MessageFormat.format("{0}.{1}.{2}.UNLOCKED", new Object[]{uuid, StatType.SPELL, spell});
            if (!CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(storyPath)) continue;
            ++unlocked;
        }
        return unlocked;
    }

    @ParametersAreNonnullByDefault
    public static String getSpellRankString(UUID uuid) {
        int total = SpellType.getEnabledSpells().length;
        int unlocked = PlayerStatistics.getSpellsUnlocked(uuid);
        SpellRank spellRank = SpellRank.getByPercent((double)unlocked / (double)total * 100.0);
        return MessageFormat.format("{0}Crystamae Rank: {1}{2}{0} ({3}/{4})", ThemeType.PASSIVE.getColor(), spellRank.getTheme().getColor(), spellRank.getTheme().getLoreLine(), unlocked, total);
    }

    @ParametersAreNonnullByDefault
    public static GildingRank getGildingRank(UUID uuid) {
        int total = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().size();
        int unlocked = PlayerStatistics.getBlocksGilded(uuid);
        return GildingRank.getByPercent((double)unlocked / (double)total * 100.0);
    }

    @ParametersAreNonnullByDefault
    public static int getBlocksGilded(UUID uuid) {
        String path = MessageFormat.format("{0}.{1}", new Object[]{uuid, StatType.STORY});
        ConfigurationSection section = CrystamaeHistoria.getConfigManager().getPlayerStats().getConfigurationSection(path);
        if (section == null) {
            return 0;
        }
        int unlocked = 0;
        for (String story : section.getKeys(false)) {
            String storyPath = MessageFormat.format("{0}.{1}.{2}.GILDED", new Object[]{uuid, StatType.STORY, story});
            if (!CrystamaeHistoria.getConfigManager().getPlayerStats().getBoolean(storyPath)) continue;
            ++unlocked;
        }
        return unlocked;
    }

    @ParametersAreNonnullByDefault
    public static String getGildingRankString(UUID uuid) {
        int total = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().size();
        int unlocked = PlayerStatistics.getBlocksGilded(uuid);
        GildingRank gildingRank = GildingRank.getByPercent((double)unlocked / (double)total * 100.0);
        return MessageFormat.format("{0}Gilding Rank: {1}{2}{0} ({3}/{4})", ThemeType.PASSIVE.getColor(), gildingRank.getTheme().getColor(), gildingRank.getTheme().getLoreLine(), unlocked, total);
    }

    static enum StatType {
        SPELL,
        STORY;

    }
}

