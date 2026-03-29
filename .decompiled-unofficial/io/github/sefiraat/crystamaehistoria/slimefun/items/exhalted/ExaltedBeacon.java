/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedItem;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class ExaltedBeacon
extends ExaltedItem {
    private static final Set<PotionEffectType> EFFECT_TYPES = new HashSet<PotionEffectType>();
    private final int amplification;

    public ExaltedBeacon(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amplification) {
        super(itemGroup, item, recipeType, recipe);
        this.amplification = amplification;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (Player player : location.getNearbyEntitiesByType(Player.class, 25.0, 25.0)) {
            for (PotionEffectType effectType : EFFECT_TYPES) {
                player.addPotionEffect(effectType.createEffect(40, this.amplification));
            }
        }
    }

    static {
        EFFECT_TYPES.add(PotionEffectType.SPEED);
        EFFECT_TYPES.add(PotionEffectType.ABSORPTION);
        EFFECT_TYPES.add(PotionEffectType.HASTE);
        EFFECT_TYPES.add(PotionEffectType.SATURATION);
        EFFECT_TYPES.add(PotionEffectType.RESISTANCE);
        EFFECT_TYPES.add(PotionEffectType.REGENERATION);
    }
}

