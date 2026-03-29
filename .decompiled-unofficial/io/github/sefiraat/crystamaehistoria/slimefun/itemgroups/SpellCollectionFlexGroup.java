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

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCore;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
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
import java.util.stream.Collectors;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpellCollectionFlexGroup
extends FlexItemGroup {
    private static final int PAGE_SIZE = 36;
    private static final int GUIDE_BACK = 1;
    private static final int GUIDE_STATS = 7;
    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;
    private static final int[] HEADER = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int[] FOOTER = new int[]{45, 46, 47, 48, 49, 50, 51, 52, 53};
    private static final int SPELL = 19;
    private static final int[] RECIPE = new int[]{21, 22, 23};
    private static final int MECHANISM = 25;
    private static final int CRYSTA_COST = 37;
    private static final int CAST_TYPE = 38;
    private static final int VALUES = 39;
    private static final int RANGE = 40;
    private static final int KNOCK_BACK = 41;
    private static final int PROJECTILE_INFO = 42;
    private static final int EFFECTS = 43;

    public SpellCollectionFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        ChestMenu chestMenu = new ChestMenu(String.valueOf(ThemeType.MAIN.getColor()) + "Spell Compendium");
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
        List<SpellType> spellTypes = Arrays.asList(SpellType.getEnabledSpells());
        int numberOfBlocks = spellTypes.size();
        int totalPages = (int)Math.ceil((double)numberOfBlocks / 36.0);
        int start = (page - 1) * 36;
        int end = Math.min(start + 36, spellTypes.size());
        spellTypes.sort(Comparator.comparing(spellType -> spellType.getSpell().getId()));
        List<SpellType> spellTypeSubList = spellTypes.subList(start, end);
        this.reapplyFooter(player, profile, mode, menu, page, totalPages);
        menu.replaceExistingItem(1, ChestMenuUtils.getBackButton((Player)player, (String[])new String[]{Slimefun.getLocalization().getMessage("guide.back.guide")}));
        menu.addMenuClickHandler(1, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup((PlayerProfile)profile, (ItemGroup)ItemGroups.MAIN, (SlimefunGuideMode)mode, (int)1);
            return false;
        });
        menu.replaceExistingItem(7, this.getStatsStack(player));
        menu.addMenuClickHandler(7, (player1, slot, itemStack, clickAction) -> false);
        for (int i = 0; i < 36; ++i) {
            int slot2 = i + 9;
            if (i + 1 <= spellTypeSubList.size()) {
                SpellType spellType2 = spellTypeSubList.get(i);
                boolean researched = PlayerStatistics.hasUnlockedSpell(player, spellType2);
                if (mode == SlimefunGuideMode.CHEAT_MODE || researched) {
                    menu.replaceExistingItem(slot2, spellType2.getSpell().getThemedStack().item().clone());
                    menu.addMenuClickHandler(slot2, (player1, i1, itemStack1, clickAction) -> {
                        this.displayDefinition(player1, profile, mode, menu, page, spellType2);
                        return false;
                    });
                    continue;
                }
                menu.replaceExistingItem(slot2, GuiElements.getSpellNotUnlockedIcon(spellType2.getId()));
                menu.addMenuClickHandler(slot2, (player1, i1, itemStack1, clickAction) -> false);
                continue;
            }
            menu.replaceExistingItem(slot2, null);
            menu.addMenuClickHandler(slot2, (player1, i1, itemStack1, clickAction) -> false);
        }
    }

    @ParametersAreNonnullByDefault
    private void displayDefinition(Player p, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int returnPage, SpellType spellType) {
        Spell spell = spellType.getSpell();
        menu.replaceExistingItem(1, ChestMenuUtils.getBackButton((Player)p, (String[])new String[]{Slimefun.getLocalization().getMessage("guide.back.guide")}));
        menu.addMenuClickHandler(1, (player1, slot, itemStack, clickAction) -> {
            this.setupPage(player1, profile, mode, menu, returnPage);
            return false;
        });
        this.clearDisplay(menu);
        menu.replaceExistingItem(19, spellType.getSpell().getThemedStack().item().clone());
        menu.addMenuClickHandler(19, (player, i, itemStack, clickAction) -> false);
        for (int i2 = 0; i2 < RECIPE.length; ++i2) {
            int slot3 = RECIPE[i2];
            StoryType storyType = spellType.getSpell().getRecipe().getInput(i2);
            ItemStack stack = Materials.getDummyCrystalMap().get((Object)storyType).getItem();
            menu.replaceExistingItem(slot3, stack);
            menu.addMenuClickHandler(slot3, (player, slot2, itemStack, clickAction) -> false);
        }
        menu.replaceExistingItem(25, this.getMechanismStack());
        menu.addMenuClickHandler(25, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(37, this.getBasicStack(spell));
        menu.addMenuClickHandler(37, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(39, this.getValuesStack(spell));
        menu.addMenuClickHandler(39, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(38, this.getCastTypeStack(spell));
        menu.addMenuClickHandler(38, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(40, this.getRangeStack(spell));
        menu.addMenuClickHandler(40, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(41, this.getKnockBackStack(spell));
        menu.addMenuClickHandler(41, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(42, this.getProjectileStack(spell));
        menu.addMenuClickHandler(42, (player, i, itemStack, clickAction) -> false);
        menu.replaceExistingItem(43, this.getEffectsStack(spell));
        menu.addMenuClickHandler(43, (player, i, itemStack, clickAction) -> false);
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

    private ItemStack getMechanismStack() {
        List lore = Arrays.stream(new String[]{"Spells are created by combining", "liquid crystamae in a Liquefaction", "Basin.", "The highest 3 amounts of liquid", "determine the spell created (left).", "", "Throw in a Spell Plate when ready", "to create your spell."}).map(s -> String.valueOf(ThemeType.PASSIVE.getColor()) + s).collect(Collectors.toList());
        return CustomItemStack.create((Material)Material.CAULDRON, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Liquefaction Basin"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getBasicStack(Spell spell) {
        SpellCore spellCore = spell.getSpellCore();
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        String crysta = MessageFormat.format("{0}Crysta Cost per Cast: {1}{2}", color, passive, spellCore.getCrystaCost());
        String crystaMulti = MessageFormat.format("{0}Crysta cost {1} with stave tier", color, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");
        String cooldown = MessageFormat.format("{0}Cooldown (sec) on use: {1}{2}", color, passive, spell.getSpellCore().getCooldownSeconds());
        String cooldownDivided = MessageFormat.format("{0}Cooldown {1} with stave tier", color, spellCore.isDamageMultiplied() ? "isn't reduced" : "is reduced");
        return CustomItemStack.create((Material)Material.GLOW_BERRIES, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Basic Details"), (String[])new String[]{crysta, crystaMulti, cooldown, cooldownDivided});
    }

    @ParametersAreNonnullByDefault
    private ItemStack getValuesStack(Spell spell) {
        SpellCore spellCore = spell.getSpellCore();
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<Object> lore = new ArrayList<Object>();
        String damageMessage = MessageFormat.format("{0}Damage: {1}{2}", color, passive, spellCore.getDamageAmount());
        String damageMulti = MessageFormat.format("{0}Damage {1} with stave tier", color, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");
        String healMessage = MessageFormat.format("{0}Heal Amount: {1}{2}", color, passive, spellCore.getDamageAmount());
        String healMulti = MessageFormat.format("{0}Heal Amount {1} with stave tier", color, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");
        if (spellCore.isDamagingSpell()) {
            lore.add(damageMessage);
            lore.add(damageMulti);
        } else {
            lore.add(String.valueOf(passive) + "This spell does not damage.");
        }
        if (spellCore.isHealingSpell()) {
            lore.add(healMessage);
            lore.add(healMulti);
        } else {
            lore.add(String.valueOf(passive) + "This spell does not heal.");
        }
        return CustomItemStack.create((Material)Material.MAP, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Spell Values"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getCastTypeStack(Spell spell) {
        SpellCore spellCore = spell.getSpellCore();
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ChatColor notice = ThemeType.NOTICE.getColor();
        ArrayList<String> lore = new ArrayList<String>();
        int ticks = spellCore.getNumberOfTicks();
        String instantCast = MessageFormat.format("{0}Instant: {1}Fires immediately when cast", color, passive);
        String damagingSpell = MessageFormat.format("{0}Damaging: {1}Will cause damage and/or debuff", color, passive);
        String healingSpell = MessageFormat.format("{0}Healing: {1}Will heal and/or buff", color, passive);
        String effectingSpell = MessageFormat.format("{0}Effecting: {1}Will apply potion effects", color, passive);
        String tickingSpell1 = MessageFormat.format("{0}Ticking: {1}Effects of this spell will", color, passive);
        String tickingSpell2 = MessageFormat.format("{0}fire ({1}{2}{3}) times", passive, notice, ticks, passive);
        String tickingSpell3 = MessageFormat.format("{0}Tick number {1} with stave tier", passive, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");
        String projectileSpell1 = MessageFormat.format("{0}Projectile: {1}This spell fires a projectile", color, passive);
        String projectileSpell2 = MessageFormat.format("{0}which, when it hits, may induce", passive);
        String projectileSpell3 = MessageFormat.format("{0}further effects", passive);
        if (spellCore.isInstantCast()) {
            lore.add(instantCast);
        }
        if (spellCore.isDamagingSpell()) {
            lore.add(damagingSpell);
        }
        if (spellCore.isHealingSpell()) {
            lore.add(healingSpell);
        }
        if (spellCore.isEffectingSpell()) {
            lore.add(effectingSpell);
        }
        if (spellCore.isTickingSpell()) {
            lore.add(tickingSpell1);
            lore.add(tickingSpell2);
            lore.add(tickingSpell3);
        }
        if (spellCore.isProjectileSpell()) {
            lore.add(projectileSpell1);
            lore.add(projectileSpell2);
            lore.add(projectileSpell3);
        }
        return CustomItemStack.create((Material)Material.NAME_TAG, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Spell Cast Type(s)"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getRangeStack(Spell spell) {
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<Object> lore = new ArrayList<Object>();
        String message = MessageFormat.format("{0}Range: {1}{2}", color, passive, spell.getSpellCore().getRange());
        String multiMessage = MessageFormat.format("{0}Range {1} with stave tier", passive, spell.getSpellCore().isRangeMultiplied() ? "increases" : "doesn't increase");
        String noRange = String.valueOf(passive) + "Not effected by range";
        if (spell.getSpellCore().getKnockbackAmount() > 0.0) {
            lore.add(message);
            lore.add(multiMessage);
        } else {
            lore.add(noRange);
        }
        return CustomItemStack.create((Material)Material.TARGET, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Range"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getKnockBackStack(Spell spell) {
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<Object> lore = new ArrayList<Object>();
        String message = MessageFormat.format("{0}Knockback: {1}{2}", color, passive, spell.getSpellCore().getKnockbackAmount());
        String multiMessage = MessageFormat.format("{0}Amount {1} with stave tier", passive, spell.getSpellCore().isKnockbackMultiplied() ? "increases" : "doesn't increase");
        String noKnockback = String.valueOf(passive) + "No direct knockback";
        if (spell.getSpellCore().getKnockbackAmount() > 0.0) {
            lore.add(message);
            lore.add(multiMessage);
        } else {
            lore.add(noKnockback);
        }
        return CustomItemStack.create((Material)Material.SLIME_BLOCK, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Knockback"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getProjectileStack(Spell spell) {
        SpellCore spellCore = spell.getSpellCore();
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        String aoeMessage = MessageFormat.format("{0}Projectile AoE: {1}{2}", color, passive, spellCore.getProjectileAoeRange());
        String aoeMultiMessage = MessageFormat.format("{0}AoE {1} with stave tier", passive, spellCore.isProjectileAoeMultiplied() ? "increases" : "doesn't increase");
        String knockbackMessage = MessageFormat.format("{0}Projectile Knockback: {1}{2}", color, passive, spellCore.getKnockbackAmount());
        String knockbackMultiMessage = MessageFormat.format("{0}Knockback {1} with stave tier", passive, spellCore.isKnockbackMultiplied() ? "increases" : "doesn't increase");
        ArrayList<Object> lore = new ArrayList<Object>();
        if (spellCore.isProjectileSpell()) {
            lore.add(aoeMessage);
            lore.add(aoeMultiMessage);
            lore.add("");
            lore.add(knockbackMessage);
            lore.add(knockbackMultiMessage);
        } else {
            lore.add(String.valueOf(passive) + "Not a projectile spell");
        }
        return CustomItemStack.create((Material)Material.FIRE_CHARGE, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Projectile Information"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getEffectsStack(Spell spell) {
        SpellCore spellCore = spell.getSpellCore();
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<Object> lore = new ArrayList<Object>();
        if (spellCore.isEffectingSpell()) {
            String effectAmplification = MessageFormat.format("{0}Effect power {1} with stave tier", passive, spellCore.isAmplificationMultiplied() ? "increases" : "doesn't increase");
            String effectDuration = MessageFormat.format("{0}Effect duration {1} with stave tier", passive, spellCore.isEffectDurationMultiplied() ? "increases" : "doesn't increase");
            if (spellCore.getNegativeEffectPairMap().size() > 0) {
                lore.add(String.valueOf(color) + "Negative Effects:");
                spellCore.getNegativeEffectPairMap().forEach((type, pair) -> {
                    String negativeEffectMessage = MessageFormat.format("{0}{1}: {2}Power ({3}) - Duration ({4})", color, TextUtils.toTitleCase(type.getName()), passive, pair.getFirstValue(), pair.getSecondValue());
                    lore.add(negativeEffectMessage);
                });
            }
            if (spellCore.getPositiveEffectPairMap().size() > 0) {
                lore.add(String.valueOf(color) + "Positive Effects:");
                spellCore.getPositiveEffectPairMap().forEach((type, pair) -> {
                    String positiveEffectMessage = MessageFormat.format("{0}{1}: {2}Power ({3}) - Duration ({4})", color, TextUtils.toTitleCase(type.getName()), passive, pair.getFirstValue(), pair.getSecondValue());
                    lore.add(positiveEffectMessage);
                });
            }
            lore.add("");
            lore.add(effectAmplification);
            lore.add(effectDuration);
        } else {
            lore.add(String.valueOf(passive) + "Spell has no effects");
        }
        return CustomItemStack.create((Material)Material.BREWING_STAND, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Effects"), lore);
    }

    @ParametersAreNonnullByDefault
    private ItemStack getStatsStack(Player player) {
        ChatColor color = ThemeType.CLICK_INFO.getColor();
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<String> lore = new ArrayList<String>();
        SpellRank spellRank = PlayerStatistics.getSpellRank(player.getUniqueId());
        lore.add(MessageFormat.format("{0}Spells Unlocked: {1}{2}", color, passive, PlayerStatistics.getSpellsUnlocked(player.getUniqueId())));
        lore.add(MessageFormat.format("{0}Rank: {1}{2}", color, spellRank.getTheme().getColor(), spellRank.getTheme().getLoreLine()));
        return CustomItemStack.create((Material)Material.TARGET, (String)(String.valueOf(ThemeType.MAIN.getColor()) + "Spell Statistics"), lore);
    }
}

