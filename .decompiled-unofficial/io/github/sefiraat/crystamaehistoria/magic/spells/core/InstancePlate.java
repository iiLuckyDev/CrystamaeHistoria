/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InstancePlate {
    private final int tier;
    private final SpellType storedSpell;
    private int crysta;
    private long cooldown = 0L;

    @ParametersAreNonnullByDefault
    public InstancePlate(int tier, SpellType storedSpell, int crysta) {
        this.tier = tier;
        this.storedSpell = storedSpell;
        this.crysta = crysta;
    }

    @ParametersAreNonnullByDefault
    public static void setPlateLore(ItemStack itemStack, @Nullable InstancePlate instancePlate) {
        String magic = instancePlate != null ? TextUtils.toTitleCase(instancePlate.storedSpell.getId()) : "None";
        String crysta = instancePlate != null ? String.valueOf(instancePlate.crysta) : "0";
        String[] lore = new String[]{"A magically charged plate storing magic", "potential.", "", String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Magic Framework : " + String.valueOf(ThemeType.NOTICE.getColor()) + magic, String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Stored Crysta : " + String.valueOf(ThemeType.NOTICE.getColor()) + crysta};
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        ArrayList<Object> finalLore = new ArrayList<Object>();
        ItemMeta itemMeta = itemStack.getItemMeta();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(String.valueOf(passiveColor) + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, ThemeType.CRAFTING.getLoreLine()));
        itemMeta.setLore(finalLore);
        itemStack.setItemMeta(itemMeta);
    }

    @ParametersAreNonnullByDefault
    public CastResult tryCastSpell(CastInformation castInformation) {
        Spell spell = this.storedSpell.getSpell();
        int crystaCost = spell.getCrystaCost(castInformation);
        if (!CrystamaeHistoria.getConfigManager().spellEnabled(spell)) {
            return CastResult.SPELL_DISABLED;
        }
        if (this.crysta < crystaCost) {
            return CastResult.CAST_FAIL_NO_CRYSTA;
        }
        if (this.cooldown > System.currentTimeMillis()) {
            return CastResult.ON_COOLDOWN;
        }
        castInformation.setSpellType(this.storedSpell);
        spell.castSpell(castInformation);
        this.crysta -= crystaCost;
        long cdSeconds = (long)(spell.getCooldownSeconds(castInformation) * 1000.0);
        this.cooldown = System.currentTimeMillis() + cdSeconds;
        PlayerStatistics.addUsage(castInformation.getCaster(), this.storedSpell);
        return CastResult.CAST_SUCCESS;
    }

    public void addCrysta(int amount) {
        this.crysta += amount;
    }

    public void removeCrysta(int amount) {
        this.crysta -= amount;
    }

    @Generated
    public int getTier() {
        return this.tier;
    }

    @Generated
    public SpellType getStoredSpell() {
        return this.storedSpell;
    }

    @Generated
    public int getCrysta() {
        return this.crysta;
    }

    @Generated
    public long getCooldown() {
        return this.cooldown;
    }

    @Generated
    public void setCrysta(int crysta) {
        this.crysta = crysta;
    }

    @Generated
    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}

