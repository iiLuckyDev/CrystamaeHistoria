/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityCombustByBlockEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class CrystaDowngradeListener
implements Listener {
    @EventHandler
    public void onInteract(EntityCombustByBlockEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Item)) {
            return;
        }
        Item item = (Item)entity;
        ItemStack itemStack = item.getItemStack();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)itemStack);
        if (slimefunItem instanceof Crystal) {
            Crystal crystal = (Crystal)slimefunItem;
            int id = crystal.getRarity().getId();
            StoryType type = crystal.getType();
            if (id != 6 && id > 1) {
                StoryRarity rarity = StoryRarity.getById(id - 1);
                ItemStack newItemStack = Materials.getCrystalMap().get((Object)rarity).get((Object)type).getItem().clone();
                double velX = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
                double velZ = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
                e.setCancelled(true);
                newItemStack.setAmount(itemStack.getAmount());
                item.setItemStack(newItemStack);
                item.setVelocity(new Vector(velX, 0.5, velZ));
            }
        }
    }
}

