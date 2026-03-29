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
import io.github.sefiraat.crystamaehistoria.player.GildingRank;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
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
import java.util.Comparator;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GildedCollectionFlexGroup
extends FlexItemGroup {
    private static final int PAGE_SIZE = 36;
    private static final int GUIDE_BACK = 1;
    private static final int GUIDE_STATS = 7;
    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;
    private static final int[] HEADER = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int[] FOOTER = new int[]{45, 46, 47, 48, 49, 50, 51, 52, 53};

    @ParametersAreNonnullByDefault
    public GildedCollectionFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        ChestMenu chestMenu = new ChestMenu(String.valueOf(ThemeType.MAIN.getColor()) + "Crystamae Gilding Compendium");
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
                boolean researched = PlayerStatistics.hasUnlockedStoryGilded(player, definition2);
                if (mode == SlimefunGuideMode.CHEAT_MODE || researched) {
                    menu.replaceExistingItem(slot2, GuiElements.getBlockGildedIcon(definition2.getMaterial()));
                } else {
                    menu.replaceExistingItem(slot2, GuiElements.getBlockNotGildedIcon(definition2.getMaterial()));
                }
            } else {
                menu.replaceExistingItem(slot2, null);
            }
            menu.addMenuClickHandler(slot2, (player1, i1, itemStack1, clickAction) -> false);
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
    private ItemStack getPlayerInfoStack(Player player) {
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<String> lore = new ArrayList<String>();
        GildingRank gildingRank = PlayerStatistics.getGildingRank(player.getUniqueId());
        lore.add(MessageFormat.format("{0}Blocks Gilded: {1}{2}", color, passive, PlayerStatistics.getBlocksGilded(player.getUniqueId())));
        lore.add(MessageFormat.format("{0}Rank: {1}{2}", color, gildingRank.getTheme().getColor(), gildingRank.getTheme().getLoreLine()));
        return CustomItemStack.create((Material)Material.TARGET, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Gilding Statistics"), lore);
    }
}

