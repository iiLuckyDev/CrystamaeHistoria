/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.ThaumaturgicSalt;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ThaumaturgicSaltsListener
implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack heldStack = player.getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)heldStack);
        Block block = e.getClickedBlock();
        if (block != null && e.getAction() == Action.RIGHT_CLICK_BLOCK && slimefunItem instanceof ThaumaturgicSalt) {
            e.setCancelled(true);
            SlimefunItem item = BlockStorage.check((Block)block);
            if (item instanceof LiquefactionBasin) {
                this.liquefactionBasin(player, heldStack, item, block);
            }
        }
    }

    private void liquefactionBasin(Player player, ItemStack heldItem, SlimefunItem blockItem, Block clickedBlock) {
        if (GeneralUtils.hasPermission(player, clickedBlock, Interaction.BREAK_BLOCK)) {
            LiquefactionBasin basin = (LiquefactionBasin)blockItem;
            LiquefactionBasinCache cache = basin.getCacheMap().get(clickedBlock.getLocation());
            cache.emptyBasin();
            heldItem.setAmount(heldItem.getAmount() - 1);
        }
    }
}

