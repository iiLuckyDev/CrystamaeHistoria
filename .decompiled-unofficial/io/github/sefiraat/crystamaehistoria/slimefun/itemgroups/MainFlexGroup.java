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
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.TextComponent$Builder
 *  net.kyori.adventure.text.event.ClickEvent
 *  net.kyori.adventure.text.event.ClickEvent$Action
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainFlexGroup
extends FlexItemGroup {
    private static final ItemStack DOCS_ITEM_STACK = ThemeType.themedItemStack(Material.BOOK, ThemeType.GUIDE, "Documentation Wiki", "Click to get the link to the", "documentation Wiki for Crystamae", "and other Sefiraat addons.");
    private static final int PAGE_SIZE = 36;
    private static final int GUIDE_BACK = 1;
    private static final int DOCS = 9;
    private static final int MECHANISMS = 10;
    private static final int CRYSTALS = 11;
    private static final int TOOLS = 12;
    private static final int GADGETS = 13;
    private static final int PAINTBRUSHES = 14;
    private static final int EXALTED = 15;
    private static final int UNIQUES = 16;
    private static final int RUNES = 17;
    private static final int MATERIALS = 18;
    private static final int GUIDE = 19;
    private static final int STORY = 20;
    private static final int SPELL = 21;
    private static final int GILDING = 22;
    private static final int[] HEADER = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int[] FOOTER = new int[]{45, 46, 47, 48, 49, 50, 51, 52, 53};

    public MainFlexGroup(NamespacedKey key, ItemStack item) {
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
        this.setupPage(p, profile, mode, chestMenu);
        chestMenu.open(new Player[]{p});
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu) {
        for (int slot2 : FOOTER) {
            menu.replaceExistingItem(slot2, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot2, (player1, i, itemStack, clickAction) -> false);
        }
        menu.replaceExistingItem(1, ChestMenuUtils.getBackButton((Player)player, (String[])new String[]{Slimefun.getLocalization().getMessage("guide.back.guide")}));
        menu.addMenuClickHandler(1, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openMainMenu((PlayerProfile)profile, (SlimefunGuideMode)mode, (int)1);
            return false;
        });
        menu.replaceExistingItem(9, DOCS_ITEM_STACK);
        menu.addMenuClickHandler(9, (player1, i1, itemStack1, clickAction) -> {
            TextComponent link = (TextComponent)((TextComponent.Builder)((TextComponent.Builder)Component.text().content("To access the documentation Wiki, please click here").color(TextColor.color((int)175, (int)200, (int)60))).clickEvent(ClickEvent.clickEvent((ClickEvent.Action)ClickEvent.Action.OPEN_URL, (String)"https://sefiraat.dev/"))).build();
            player1.sendMessage((Component)link);
            return false;
        });
        menu.replaceExistingItem(10, ItemGroups.MECHANISMS.getItem(player));
        menu.addMenuClickHandler(10, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.MECHANISMS, mode, 1));
        menu.replaceExistingItem(11, ItemGroups.CRYSTALS.getItem(player));
        menu.addMenuClickHandler(11, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.CRYSTALS, mode, 1));
        menu.replaceExistingItem(12, ItemGroups.TOOLS.getItem(player));
        menu.addMenuClickHandler(12, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.TOOLS, mode, 1));
        menu.replaceExistingItem(13, ItemGroups.GADGETS.getItem(player));
        menu.addMenuClickHandler(13, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.GADGETS, mode, 1));
        menu.replaceExistingItem(14, ItemGroups.ARTISTIC.getItem(player));
        menu.addMenuClickHandler(14, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.ARTISTIC, mode, 1));
        menu.replaceExistingItem(15, ItemGroups.EXALTED.getItem(player));
        menu.addMenuClickHandler(15, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.EXALTED, mode, 1));
        menu.replaceExistingItem(16, ItemGroups.UNIQUES.getItem(player));
        menu.addMenuClickHandler(16, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.UNIQUES, mode, 1));
        menu.replaceExistingItem(17, ItemGroups.RUNES.getItem(player));
        menu.addMenuClickHandler(17, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.RUNES, mode, 1));
        menu.replaceExistingItem(18, ItemGroups.MATERIALS.getItem(player));
        menu.addMenuClickHandler(18, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.MATERIALS, mode, 1));
        menu.replaceExistingItem(19, ItemGroups.GUIDE.getItem(player));
        menu.addMenuClickHandler(19, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, ItemGroups.GUIDE, mode, 1));
        menu.replaceExistingItem(20, ItemGroups.STORY_COLLECTION.getItem(player));
        menu.addMenuClickHandler(20, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, (ItemGroup)ItemGroups.STORY_COLLECTION, mode, 1));
        menu.replaceExistingItem(21, ItemGroups.SPELL_COLLECTION.getItem(player));
        menu.addMenuClickHandler(21, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, (ItemGroup)ItemGroups.SPELL_COLLECTION, mode, 1));
        menu.replaceExistingItem(22, ItemGroups.GILDING_COLLECTION.getItem(player));
        menu.addMenuClickHandler(22, (player1, i1, itemStack1, clickAction) -> this.openPage(profile, (ItemGroup)ItemGroups.GILDING_COLLECTION, mode, 1));
    }

    @ParametersAreNonnullByDefault
    public boolean openPage(PlayerProfile profile, ItemGroup itemGroup, SlimefunGuideMode mode, int page) {
        profile.getGuideHistory().add((ItemGroup)this, 1);
        SlimefunGuide.openItemGroup((PlayerProfile)profile, (ItemGroup)itemGroup, (SlimefunGuideMode)mode, (int)page);
        return false;
    }
}

