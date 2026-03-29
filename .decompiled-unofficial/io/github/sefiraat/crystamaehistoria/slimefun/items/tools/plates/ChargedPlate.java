/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.slimefun.Tools;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates.MagicalPlate;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

public class ChargedPlate
extends MagicalPlate {
    @ParametersAreNonnullByDefault
    public ChargedPlate(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int tier) {
        super(itemGroup, item, recipeType, recipe, tier);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack getChargedPlate(int tier, SpellType spellType, int crysta) {
        InstancePlate instancePlate = new InstancePlate(tier, spellType, crysta);
        return ChargedPlate.getChargedPlate(instancePlate);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack getChargedPlate(InstancePlate instancePlate) {
        ItemStack newPlate = Tools.getChargedPlate().getItem().clone();
        InstancePlate.setPlateLore(newPlate, instancePlate);
        ItemMeta itemMeta = newPlate.getItemMeta();
        DataTypeMethods.setCustom((PersistentDataHolder)itemMeta, Keys.PDC_PLATE_STORAGE, PersistentPlateDataType.TYPE, instancePlate);
        newPlate.setItemMeta(itemMeta);
        return newPlate;
    }
}

