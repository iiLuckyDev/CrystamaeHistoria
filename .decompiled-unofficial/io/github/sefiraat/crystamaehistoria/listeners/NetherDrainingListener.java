/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityPortalEnterEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.inventory.ItemStack;

public class NetherDrainingListener
implements Listener {
    @EventHandler(priority=EventPriority.LOWEST)
    public void portalDraining(EntityPortalEnterEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Item)) {
            return;
        }
        Item item = (Item)entity;
        ItemStack itemStack = item.getItemStack();
        for (Map.Entry<ItemStack, ItemStack> entry : CrystaRecipeTypes.getDrainingRecipes().entrySet()) {
            ItemStack recipeStack = entry.getKey();
            if (recipeStack == null || recipeStack.getType() == Material.AIR || !SlimefunUtils.isItemSimilar((ItemStack)recipeStack, (ItemStack)itemStack, (boolean)true, (boolean)false)) continue;
            item.setItemStack(entry.getValue().asQuantity(itemStack.getAmount()));
            break;
        }
    }
}

