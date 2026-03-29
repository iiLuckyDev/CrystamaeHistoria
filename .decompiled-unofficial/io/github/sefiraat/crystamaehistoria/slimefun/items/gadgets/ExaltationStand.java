/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedItem;
import io.github.sefiraat.crystamaehistoria.slimefun.types.Stand;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExaltationStand
extends Stand {
    public ExaltationStand(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void onRightClick(PlayerRightClickEvent e) {
        Player player = e.getPlayer();
        Optional optionalBlock = e.getClickedBlock();
        if (!optionalBlock.isPresent()) {
            return;
        }
        e.cancel();
        Block blockClicked = (Block)optionalBlock.get();
        if (!Slimefun.getProtectionManager().hasPermission((OfflinePlayer)player, blockClicked, Interaction.BREAK_BLOCK)) {
            return;
        }
        UUID currentItemUuid = (UUID)this.itemMap.get(blockClicked.getLocation());
        if (currentItemUuid != null) {
            Item currentItem = (Item)Bukkit.getEntity((UUID)currentItemUuid);
            if (currentItem != null) {
                ItemStack itemStack = currentItem.getItemStack();
                HashMap rejected = player.getInventory().addItem(new ItemStack[]{itemStack});
                if (!rejected.isEmpty()) {
                    blockClicked.getWorld().dropItem(blockClicked.getLocation().clone().add(0.5, 1.5, 0.5), itemStack);
                }
                currentItem.remove();
            }
            BlockStorage.addBlockInfo((Block)blockClicked, (String)"itemUUID", null);
            this.itemMap.remove(blockClicked.getLocation());
            return;
        }
        ItemStack itemStack = e.getItem();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)itemStack);
        if (slimefunItem instanceof ExaltedItem) {
            Location location = blockClicked.getLocation().add(0.5, 1.5, 0.5);
            Item item = GeneralUtils.spawnDisplayItem(itemStack.asQuantity(1), location, "");
            itemStack.setAmount(itemStack.getAmount() - 1);
            BlockStorage.addBlockInfo((Block)blockClicked, (String)"itemUUID", (String)item.getUniqueId().toString());
            this.itemMap.put(blockClicked.getLocation(), item.getUniqueId());
        }
    }

    @Override
    public void afterTick(@Nonnull Item item, @Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        SlimefunItem itemOnStand = SlimefunItem.getByItem((ItemStack)item.getItemStack());
        if (itemOnStand instanceof ExaltedItem) {
            ExaltedItem exaltedItem = (ExaltedItem)itemOnStand;
            exaltedItem.onExalt(exaltedItem, item.getLocation().clone());
        }
    }
}

