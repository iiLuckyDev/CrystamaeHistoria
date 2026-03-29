/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  org.bukkit.Color
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityPickupItemEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.satchel.CrystamageSatchel;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.awt.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class SatchelListener
implements Listener {
    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onInteract(EntityPickupItemEvent e) {
        LivingEntity entity = e.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player)entity;
        Item item = e.getItem();
        ItemStack itemStack = item.getItemStack();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)itemStack);
        if (slimefunItem instanceof Crystal) {
            Crystal crystal = (Crystal)slimefunItem;
            for (ItemStack possibleSatchel : player.getInventory().getContents()) {
                SlimefunItem satchelSfItem = SlimefunItem.getByItem((ItemStack)possibleSatchel);
                if (!(satchelSfItem instanceof CrystamageSatchel)) continue;
                CrystamageSatchel crystamageSatchel = (CrystamageSatchel)satchelSfItem;
                if (possibleSatchel.getAmount() > 1) {
                    player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "You have stacked Crystamae Satchels. They will not work until unstacked.");
                    return;
                }
                if (crystamageSatchel.tryAddItem(possibleSatchel, itemStack, crystal)) {
                    Color baseColor = ThemeType.getByType(crystal.getType()).getColor().getColor();
                    org.bukkit.Color color = org.bukkit.Color.fromRGB((int)baseColor.getRed(), (int)baseColor.getGreen(), (int)baseColor.getBlue());
                    Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1.0f);
                    ParticleUtils.displayParticleEffect((Entity)item, 0.4, 10, dustOptions);
                    item.remove();
                    e.setCancelled(true);
                    return;
                }
                player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "You have a Crystamae Satchel that has not yet been setup. Open the satchel first.");
            }
        }
    }
}

