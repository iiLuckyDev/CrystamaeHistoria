/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  lombok.Generated
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.block.BlockFace
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.utils.theme;

import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import java.text.MessageFormat;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

public final class GuiElements {
    public static final ItemStack MENU_BACKGROUND = ChestMenuUtils.getBackground();
    public static final ItemStack MENU_BACKGROUND_INPUT = CustomItemStack.create((Material)Material.LIGHT_BLUE_STAINED_GLASS_PANE, (String)(String.valueOf(ChatColor.BLUE) + "Input"), (String[])new String[0]);
    public static final ItemStack MENU_STAVE_INPUT = CustomItemStack.create((Material)Material.LIGHT_BLUE_STAINED_GLASS_PANE, (String)(String.valueOf(ChatColor.BLUE) + "Stave Input"), (String[])new String[0]);
    public static final ItemStack MENU_REMOVE_PLATES = CustomItemStack.create((Material)Material.ORANGE_STAINED_GLASS_PANE, (String)(String.valueOf(ChatColor.BLUE) + "Remove Plates"), (String[])new String[0]);
    public static final ItemStack MENU_SAVE_STAVE = CustomItemStack.create((Material)Material.GREEN_STAINED_GLASS_PANE, (String)(String.valueOf(ChatColor.BLUE) + "Save Stave Config"), (String[])new String[0]);
    public static final ItemStack MENU_BACKGROUND_OUTPUT = CustomItemStack.create((Material)Material.ORANGE_STAINED_GLASS_PANE, (String)(String.valueOf(ChatColor.RED) + "Output"), (String[])new String[0]);
    public static final ItemStack MENU_DIVIDER = CustomItemStack.create((Material)Material.LIME_STAINED_GLASS_PANE, (String)" ", (String[])new String[0]);
    public static final ItemStack TIER_INDICATOR_1 = CustomItemStack.create((ItemStack)Skulls.GUI_TIER_NUMBER_1.getPlayerHead(), (String)(String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Tier 1"), (String[])new String[0]);
    public static final ItemStack TIER_INDICATOR_2 = CustomItemStack.create((ItemStack)Skulls.GUI_TIER_NUMBER_2.getPlayerHead(), (String)(String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Tier 2"), (String[])new String[0]);
    public static final ItemStack TIER_INDICATOR_3 = CustomItemStack.create((ItemStack)Skulls.GUI_TIER_NUMBER_3.getPlayerHead(), (String)(String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Tier 3"), (String[])new String[0]);
    public static final ItemStack TIER_INDICATOR_4 = CustomItemStack.create((ItemStack)Skulls.GUI_TIER_NUMBER_4.getPlayerHead(), (String)(String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Tier 4"), (String[])new String[0]);
    public static final ItemStack TIER_INDICATOR_5 = CustomItemStack.create((ItemStack)Skulls.GUI_TIER_NUMBER_5.getPlayerHead(), (String)(String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Tier 5"), (String[])new String[0]);

    @ParametersAreNonnullByDefault
    public static ItemStack getUniqueStoryIcon(Material material) {
        return ThemeType.themedItemStack(material, ThemeType.RARITY_UNIQUE, TextUtils.toTitleCase(material.toString()), "This unique story has been", "chronicled.");
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getStoryNotUnlockedIcon(Material material) {
        return ThemeType.themedItemStack(Material.BARRIER, ThemeType.RESEARCH, TextUtils.toTitleCase(material.toString()), MessageFormat.format("{0}{1}LOCKED", ThemeType.RESEARCH.getColor(), ChatColor.BOLD), "This unique story is not yet", "unlocked.", "Stories are unlocked the first time", "you chronicle the required block.");
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getSpellNotUnlockedIcon(String id) {
        return ThemeType.themedItemStack(Material.BARRIER, ThemeType.RESEARCH, TextUtils.toTitleCase(id), MessageFormat.format("{0}{1}LOCKED", ThemeType.RESEARCH.getColor(), ChatColor.BOLD), "This spell is not yet unlocked.", "Spells are unlocked the first time", "you charge a plate with it in the", "liquefaction basin.");
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getBlockGildedIcon(Material material) {
        return ThemeType.themedItemStack(material, ThemeType.RARITY_UNIQUE, TextUtils.toTitleCase(material.toString()), "This block has been gilded.");
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getBlockNotGildedIcon(Material material) {
        return ThemeType.themedItemStack(Material.BARRIER, ThemeType.RESEARCH, TextUtils.toTitleCase(material.toString()), MessageFormat.format("{0}{1}LOCKED", ThemeType.RESEARCH.getColor(), ChatColor.BOLD), "This block has not yet been gilded.");
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getSpellSlotPane(SpellSlot spellSlot) {
        return CustomItemStack.create((Material)Material.RED_STAINED_GLASS_PANE, (String)(String.valueOf(ChatColor.GRAY) + "Spell Slot : " + TextUtils.toTitleCase(spellSlot.name())), (String[])new String[0]);
    }

    @ParametersAreNonnullByDefault
    public static ItemStack getDirectionalSlotPane(BlockFace blockFace, boolean active) {
        Material material = active ? Material.RED_STAINED_GLASS_PANE : Material.GREEN_STAINED_GLASS_PANE;
        return CustomItemStack.create((Material)material, (String)(String.valueOf(ChatColor.GRAY) + "Set direction: " + blockFace.name()), (String[])new String[0]);
    }

    @Generated
    private GuiElements() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

