/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
 *  io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
 *  io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideImplementation
 *  io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
 *  org.bukkit.ChatColor
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.groups;

import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideImplementation;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@ParametersAreNonnullByDefault
public final class MultiGroup
extends FlexItemGroup {
    private final ItemGroup[] subGroups;
    private final String name;

    public MultiGroup(String key, ItemStack item, ItemGroup ... subGroups) {
        this(key, item, 3, subGroups);
    }

    public MultiGroup(String key, ItemStack item, int tier, ItemGroup ... subGroups) {
        super(AbstractAddon.createKey(key), item, tier);
        Arrays.sort(subGroups, Comparator.comparingInt(ItemGroup::getTier));
        this.subGroups = subGroups;
        this.name = ItemUtils.getItemName((ItemStack)item);
    }

    public boolean isVisible(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        return mode == SlimefunGuideMode.SURVIVAL_MODE;
    }

    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        this.openGuide(p, profile, mode, 1);
    }

    private void openGuide(Player p, PlayerProfile profile, SlimefunGuideMode mode, int page) {
        SlimefunGuideImplementation guide = Slimefun.getRegistry().getSlimefunGuide(mode);
        profile.getGuideHistory().add((ItemGroup)this, page);
        ChestMenu menu = new ChestMenu(this.name);
        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(pl -> pl.playSound(pl.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 1.0f));
        for (int i = 0; i < 9; ++i) {
            menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }
        String back = String.valueOf(ChatColor.GRAY) + Slimefun.getLocalization().getMessage(p, "guide.back.guide");
        menu.addItem(1, ChestMenuUtils.getBackButton((Player)p, (String[])new String[]{"", back}));
        menu.addMenuClickHandler(1, (pl, s, is, action) -> {
            profile.getGuideHistory().goBack(guide);
            return false;
        });
        int target = 36 * (page - 1) - 1;
        for (int index = 9; target < this.subGroups.length - 1 && index < 45; ++index) {
            ItemGroup category = this.subGroups[++target];
            menu.addItem(index, category.getItem(p));
            menu.addMenuClickHandler(index, (pl, slot, item, action) -> {
                SlimefunGuide.openItemGroup((PlayerProfile)profile, (ItemGroup)category, (SlimefunGuideMode)mode, (int)1);
                return false;
            });
        }
        int pages = target == this.subGroups.length - 1 ? page : (this.subGroups.length - 1) / 36 + 1;
        for (int i = 45; i < 54; ++i) {
            menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }
        menu.addItem(46, ChestMenuUtils.getPreviousButton((Player)p, (int)page, (int)pages));
        menu.addMenuClickHandler(46, (pl, slot, item, action) -> {
            int next = page - 1;
            if (next > 0) {
                this.openGuide(p, profile, mode, next);
            }
            return false;
        });
        menu.addItem(52, ChestMenuUtils.getNextButton((Player)p, (int)page, (int)pages));
        menu.addMenuClickHandler(52, (pl, slot, item, action) -> {
            int next = page + 1;
            if (next <= pages) {
                this.openGuide(p, profile, mode, next);
            }
            return false;
        });
        menu.open(new Player[]{p});
    }

    public void register(SlimefunAddon addon) {
        super.register(addon);
        for (ItemGroup category : this.subGroups) {
            if (category.isRegistered()) continue;
            category.register(addon);
        }
    }
}

