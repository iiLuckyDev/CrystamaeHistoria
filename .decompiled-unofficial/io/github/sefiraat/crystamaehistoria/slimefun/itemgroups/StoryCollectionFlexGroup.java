/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
 *  io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
 *  io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.BlockRank;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StoryCollectionFlexGroup
extends FlexItemGroup {
    private static final int PAGE_SIZE = 36;
    private static final int GUIDE_BACK = 1;
    private static final int GUIDE_STATS = 7;
    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;
    private static final int[] HEADER = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int[] FOOTER = new int[]{45, 46, 47, 48, 49, 50, 51, 52, 53};
    private static final int CHRONICLING_SLOT = 20;
    private static final int TIER_SLOT = 22;
    private static final int STATS_SLOT = 24;
    private static final int UNIQUE_SLOT = 40;
    private static final int[] DIVIDER = new int[]{36, 37, 38, 39, 41, 42, 43, 44};
    private static final int[] CRYSTAMAE = new int[]{45, 46, 47, 48, 49, 50, 51, 52, 53};

    @ParametersAreNonnullByDefault
    public StoryCollectionFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        ChestMenu chestMenu = new ChestMenu(String.valueOf(ThemeType.MAIN.getColor()) + "Crystamae Magic Compendium");
        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }
        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }
        chestMenu.setEmptySlotsClickable(false);
        this.setupPage(p, profile, mode, chestMenu, 1);
        chestMenu.open(new Player[]{p});
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page) {
        ArrayList<BlockDefinition> blockDefinitions = new ArrayList<BlockDefinition>(CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().values());
        int numberOfBlocks = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().size();
        int totalPages = (int)Math.ceil((double)numberOfBlocks / 36.0);
        int start = (page - 1) * 36;
        int end = Math.min(start + 36, blockDefinitions.size());
        blockDefinitions.sort(Comparator.comparing(definition -> definition.getMaterial().name()));
        List blockDefinitionSubList = blockDefinitions.subList(start, end);
        this.reapplyFooter(player, profile, mode, menu, page, totalPages);
        menu.replaceExistingItem(1, ChestMenuUtils.getBackButton((Player)player, (String[])new String[]{Slimefun.getLocalization().getMessage("guide.back.guide")}));
        menu.addMenuClickHandler(1, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup((PlayerProfile)profile, (ItemGroup)ItemGroups.MAIN, (SlimefunGuideMode)mode, (int)1);
            return false;
        });
        menu.replaceExistingItem(7, this.getPlayerInfoStack(player));
        menu.addMenuClickHandler(7, (player1, slot, itemStack, clickAction) -> false);
        for (int i = 0; i < 36; ++i) {
            int slot2 = i + 9;
            if (i + 1 <= blockDefinitionSubList.size()) {
                BlockDefinition definition2 = (BlockDefinition)blockDefinitionSubList.get(i);
                boolean researched = PlayerStatistics.hasUnlockedUniqueStory(player, definition2);
                if (mode == SlimefunGuideMode.CHEAT_MODE || researched) {
                    menu.replaceExistingItem(slot2, GuiElements.getUniqueStoryIcon(definition2.getMaterial()));
                    menu.addMenuClickHandler(slot2, (player1, i1, itemStack1, clickAction) -> {
                        this.displayDefinition(player1, profile, mode, menu, page, definition2);
                        return false;
                    });
                    continue;
                }
                menu.replaceExistingItem(slot2, GuiElements.getStoryNotUnlockedIcon(definition2.getMaterial()));
                menu.addMenuClickHandler(slot2, (player1, i1, itemStack1, clickAction) -> false);
                continue;
            }
            menu.replaceExistingItem(slot2, null);
            menu.addMenuClickHandler(slot2, (player1, i1, itemStack1, clickAction) -> false);
        }
    }

    @ParametersAreNonnullByDefault
    private void displayDefinition(Player p, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int returnPage, BlockDefinition definition) {
        menu.replaceExistingItem(1, ChestMenuUtils.getBackButton((Player)p, (String[])new String[]{Slimefun.getLocalization().getMessage("guide.back.guide")}));
        menu.addMenuClickHandler(1, (player1, slot, itemStack, clickAction) -> {
            this.setupPage(player1, profile, mode, menu, returnPage);
            return false;
        });
        this.clearDisplay(menu);
        for (int slot2 : DIVIDER) {
            menu.replaceExistingItem(slot2, GuiElements.MENU_DIVIDER);
        }
        for (int slot2 : CRYSTAMAE) {
            menu.replaceExistingItem(slot2, ChestMenuUtils.getBackground());
        }
        menu.replaceExistingItem(20, this.getPoolsItemStack(definition));
        menu.addMenuClickHandler(20, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(22, this.getTierItemStack(definition));
        menu.addMenuClickHandler(22, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(24, this.getStatsStack(p.getUniqueId(), definition));
        menu.addMenuClickHandler(24, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(40, this.getUniqueStoryItemStack(definition));
        menu.addMenuClickHandler(40, (player, i, itemStack, clickAction) -> false);
        Object object = definition.getUnique().getStoryShardProfile().shardMap.entrySet().iterator();
        while (object.hasNext()) {
            Map.Entry entry = (Map.Entry)object.next();
            int amount = (Integer)entry.getValue();
            if (amount <= 0) continue;
            StoryType type = (StoryType)((Object)entry.getKey());
            ItemStack itemStack2 = Materials.getDummyCrystalMap().get((Object)type).getItem().clone();
            itemStack2.setAmount(((Integer)entry.getValue()).intValue());
            menu.replaceExistingItem(CRYSTAMAE[type.getId() - 1], itemStack2);
        }
    }

    @ParametersAreNonnullByDefault
    private void clearDisplay(ChestMenu menu) {
        for (int i = 0; i < 45; ++i) {
            int slot = i + 9;
            menu.replaceExistingItem(slot, null);
            menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
        }
    }

    @ParametersAreNonnullByDefault
    private void reapplyFooter(Player p, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page, int totalPages) {
        for (int slot2 : FOOTER) {
            menu.replaceExistingItem(slot2, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot2, (player, i, itemStack, clickAction) -> false);
        }
        menu.replaceExistingItem(46, ChestMenuUtils.getPreviousButton((Player)p, (int)page, (int)totalPages));
        menu.addMenuClickHandler(46, (player1, slot, itemStack, clickAction) -> {
            int previousPage = page - 1;
            if (previousPage >= 1) {
                this.setupPage(player1, profile, mode, menu, previousPage);
            }
            return false;
        });
        menu.replaceExistingItem(52, ChestMenuUtils.getNextButton((Player)p, (int)page, (int)totalPages));
        menu.addMenuClickHandler(52, (player1, slot, itemStack, clickAction) -> {
            int nextPage = page + 1;
            if (nextPage <= totalPages) {
                this.setupPage(player1, profile, mode, menu, nextPage);
            }
            return false;
        });
    }

    @ParametersAreNonnullByDefault
    private ItemStack getPoolsItemStack(BlockDefinition definition) {
        List<StoryType> storyTypes = definition.getPools();
        List lore = Arrays.stream(new String[]{"When chronicling this item, you", "can draw latent stories from the", "following story pools.", ""}).map(s -> String.valueOf(ThemeType.PASSIVE.getColor()) + s).collect(Collectors.toList());
        for (StoryType storyType : storyTypes) {
            lore.add(String.valueOf(ThemeType.CLICK_INFO.getColor()) + TextUtils.toTitleCase(storyType.toString()));
        }
        return CustomItemStack.create((Material)Material.DEEPSLATE_BRICK_SLAB, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Chronicling Results"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getUniqueStoryItemStack(BlockDefinition definition) {
        return CustomItemStack.create((Material)definition.getMaterial(), (String)(String.valueOf(ThemeType.MAIN.getColor()) + definition.getUnique().getId()), definition.getUnique().getStoryLore());
    }

    @ParametersAreNonnullByDefault
    private ItemStack getTierItemStack(BlockDefinition definition) {
        switch (definition.getBlockTier().tier) {
            case 1: {
                return GuiElements.TIER_INDICATOR_1;
            }
            case 2: {
                return GuiElements.TIER_INDICATOR_2;
            }
            case 3: {
                return GuiElements.TIER_INDICATOR_3;
            }
            case 4: {
                return GuiElements.TIER_INDICATOR_4;
            }
            case 5: {
                return GuiElements.TIER_INDICATOR_5;
            }
        }
        throw new IllegalStateException("Inapplicable tier provided: " + definition.getBlockTier().tier);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getStatsStack(UUID player, BlockDefinition definition) {
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<String> lore = new ArrayList<String>();
        BlockRank blockRank = PlayerStatistics.getBlockRank(player, definition);
        int timesChronicled = PlayerStatistics.getChronicle(player, definition);
        int timesRealised = PlayerStatistics.getRealisation(player, definition);
        String chronicleCap = timesChronicled > 100 ? "(Capped at 100)" : "";
        String realisationCap = timesRealised > 100 ? "(Capped at 100)" : "";
        lore.add(MessageFormat.format("{0}Rank: {1}{2}", color, blockRank.getTheme().getColor(), blockRank.getTheme().getLoreLine()));
        lore.add("");
        lore.add(MessageFormat.format("{0}Times Chronicled: {1}{2} {3}", color, passive, timesChronicled, chronicleCap));
        lore.add(MessageFormat.format("{0}Times Realised: {1}{2} {3}", color, passive, timesRealised, realisationCap));
        return CustomItemStack.create((Material)Material.TARGET, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Item Statistics"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getPlayerInfoStack(Player player) {
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<String> lore = new ArrayList<String>();
        StoryRank storyRank = PlayerStatistics.getStoryRank(player.getUniqueId());
        lore.add(MessageFormat.format("{0}Stories Chronicled: {1}{2}", color, passive, PlayerStatistics.getStoriesUnlocked(player.getUniqueId())));
        lore.add(MessageFormat.format("{0}Rank: {1}{2}", color, storyRank.getTheme().getColor(), storyRank.getTheme().getLoreLine()));
        return CustomItemStack.create((Material)Material.TARGET, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Story Statistics"), lore);
    }
}

