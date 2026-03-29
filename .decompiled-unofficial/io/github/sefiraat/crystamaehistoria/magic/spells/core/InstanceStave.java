/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

public class InstanceStave {
    public final ItemStack itemStack;
    private final Map<SpellSlot, InstancePlate> spellInstanceMap = new EnumMap<SpellSlot, InstancePlate>(SpellSlot.class);

    public InstanceStave(@Nonnull ItemStack itemStack) {
        this.itemStack = itemStack;
        Map<SpellSlot, InstancePlate> map = DataTypeMethods.getCustom((PersistentDataHolder)itemStack.getItemMeta(), Keys.PDC_STAVE_STORAGE, PersistentStaveDataType.TYPE);
        if (map != null) {
            this.spellInstanceMap.putAll(map);
        }
    }

    public void buildLore() {
        String[] lore = new String[]{"A stave with the ability to hold", "magically charged plates."};
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        ArrayList<Object> finalLore = new ArrayList<Object>();
        for (String string : lore) {
            finalLore.add(String.valueOf(passiveColor) + string);
        }
        for (SpellSlot spellSlot : SpellSlot.getCashedValues()) {
            InstancePlate instancePlate = this.spellInstanceMap.get((Object)spellSlot);
            if (instancePlate == null) continue;
            finalLore.add("");
            String magic = TextUtils.toTitleCase(instancePlate.getStoredSpell().getId());
            String crysta = String.valueOf(instancePlate.getCrysta());
            finalLore.add(String.valueOf(ThemeType.RARITY_MYTHICAL.getColor()) + TextUtils.toTitleCase(spellSlot.name()));
            finalLore.add(String.valueOf(ThemeType.PASSIVE.getColor()) + "Spell: " + String.valueOf(ThemeType.NOTICE.getColor()) + magic);
            finalLore.add(String.valueOf(ThemeType.PASSIVE.getColor()) + "Crysta: " + String.valueOf(ThemeType.NOTICE.getColor()) + crysta);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, ThemeType.STAVE.getLoreLine()));
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(finalLore);
        this.itemStack.setItemMeta(itemMeta);
    }

    @ParametersAreNonnullByDefault
    public void setSlot(SpellSlot spellSlot, InstancePlate instancePlate) {
        this.spellInstanceMap.put(spellSlot, instancePlate);
    }

    @ParametersAreNonnullByDefault
    public CastResult tryCastSpell(SpellSlot slot, CastInformation castInformation) {
        InstancePlate instancePlate = this.spellInstanceMap.get((Object)slot);
        if (instancePlate != null) {
            return instancePlate.tryCastSpell(castInformation);
        }
        return CastResult.CAST_FAIL_SLOT_EMPTY;
    }

    @Generated
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    @Generated
    public Map<SpellSlot, InstancePlate> getSpellInstanceMap() {
        return this.spellInstanceMap;
    }
}

