/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.AutoDisenchantEvent
 *  io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.event.entity.EntityShootBowEvent
 *  org.bukkit.event.inventory.CraftItemEvent
 *  org.bukkit.event.player.PlayerBedLeaveEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.MagicPaintbrush;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.LuminescenceScoop;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.covers.BlockVeil;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.events.AutoDisenchantEvent;
import io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MiscListener
implements Listener {
    @EventHandler
    public void onPlaceStoriedBlock(BlockPlaceEvent e) {
        ItemStack itemStack = e.getItemInHand();
        if (itemStack.getType() != Material.AIR && StoryUtils.isStoried(itemStack)) {
            Player player = e.getPlayer();
            player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "This block has been saturated with Crysta and can no longer be placed.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlacerStoriedBlock(BlockPlacerPlaceEvent e) {
        ItemStack itemStack = e.getItemStack();
        if (itemStack.getType() != Material.AIR && StoryUtils.isStoried(itemStack)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onShootPaintbrush(EntityShootBowEvent e) {
        ItemStack itemStack = e.getConsumable();
        if (SlimefunItem.getByItem((ItemStack)itemStack) instanceof MagicPaintbrush) {
            e.setCancelled(true);
            LivingEntity entity = e.getEntity();
            if (entity instanceof Player) {
                entity.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "You can't shoot a Paintbrush!");
            }
        }
    }

    @EventHandler
    public void onTryCraft(CraftItemEvent e) {
        for (ItemStack item : e.getInventory().getMatrix()) {
            if (item == null || item.getType() == Material.AIR || !StoryUtils.isStoried(item)) continue;
            e.setCancelled(true);
            for (HumanEntity viewer : e.getInventory().getViewers()) {
                viewer.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "You cannot craft using this!");
            }
            return;
        }
    }

    @EventHandler
    public void onTryCraft(AutoDisenchantEvent e) {
        ItemStack itemStack = e.getItem();
        if (itemStack.getType() != Material.AIR && StoryUtils.isStoried(itemStack)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlaceCover(BlockPlaceEvent e) {
        ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
        if (SlimefunItem.getByItem((ItemStack)itemStack) instanceof BlockVeil) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.LOWEST)
    public void checkCooldown(PlayerInteractEvent event) {
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (itemStack.getType() != Material.AIR && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && GeneralUtils.isOnCooldown(itemStack)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.LOWEST)
    public void leaveSleepingBag(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        Location location = CrystamaeHistoria.getSpellMemory().getSleepingBags().remove(player.getUniqueId());
        if (location != null) {
            location.getBlock().setType(Material.AIR);
        }
    }

    @EventHandler(priority=EventPriority.LOWEST)
    public void onUseScoop(PlayerInteractEvent event) {
        LuminescenceScoop scoop;
        Player player = event.getPlayer();
        SlimefunItem item = SlimefunItem.getByItem((ItemStack)player.getInventory().getItemInMainHand());
        if (item instanceof LuminescenceScoop && (scoop = (LuminescenceScoop)item).isAdjustable()) {
            scoop.adjustLight(player);
        }
    }
}

