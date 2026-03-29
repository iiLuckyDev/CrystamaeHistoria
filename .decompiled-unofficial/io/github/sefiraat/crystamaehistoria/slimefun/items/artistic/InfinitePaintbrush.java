/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  net.kyori.adventure.text.Component
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.PotionMeta
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.MagicPaintbrush;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PaintProfile;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import javax.annotation.ParametersAreNonnullByDefault;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataHolder;

public class InfinitePaintbrush
extends SlimefunItem
implements MagicPaintbrush {
    @ParametersAreNonnullByDefault
    public InfinitePaintbrush(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onItemUse()});
    }

    public ItemUseHandler onItemUse() {
        return e -> {
            ItemStack itemStack = e.getItem();
            if (itemStack.getType() != Material.AIR) {
                PotionMeta potionMeta = (PotionMeta)itemStack.getItemMeta();
                int currentSelection = PersistentDataAPI.getInt((PersistentDataHolder)potionMeta, (NamespacedKey)Keys.PDC_PAINT_TYPE, (int)0);
                if (e.getPlayer().isSneaking()) {
                    if (++currentSelection >= PaintProfile.getCachedValues().length) {
                        currentSelection = 0;
                    }
                    PersistentDataAPI.setInt((PersistentDataHolder)potionMeta, (NamespacedKey)Keys.PDC_PAINT_TYPE, (int)currentSelection);
                    PaintProfile profile = PaintProfile.getCachedValues()[currentSelection];
                    e.getPlayer().sendActionBar(Component.text((String)("Now painting in " + TextUtils.toTitleCase(profile.name()))).color(profile.getTextColor()));
                    potionMeta.setColor(profile.getDyeColor().getColor());
                    itemStack.setItemMeta((ItemMeta)potionMeta);
                } else {
                    this.tryPaint(e, PaintProfile.getCachedValues()[currentSelection], true);
                }
            }
        };
    }
}

