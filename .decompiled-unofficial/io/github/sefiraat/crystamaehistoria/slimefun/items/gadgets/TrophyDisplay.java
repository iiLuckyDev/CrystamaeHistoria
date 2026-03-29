/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.BlockRank;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Trophy;
import io.github.sefiraat.crystamaehistoria.slimefun.types.Stand;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TrophyDisplay
extends Stand {
    private Consumer<Location> locationConsumer = null;

    @ParametersAreNonnullByDefault
    public TrophyDisplay(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        this.addItemHandler(new ItemHandler[]{this::onRightClick});
    }

    @Override
    public void onRightClick(PlayerRightClickEvent e) {
        BlockRank blockRank;
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
            this.locationConsumer = null;
            return;
        }
        ItemStack itemStack = e.getItem();
        Material material = itemStack.getType();
        BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)itemStack);
        if (slimefunItem instanceof Trophy) {
            ItemStack clone = itemStack.asQuantity(1);
            Trophy trophy = (Trophy)slimefunItem;
            this.locationConsumer = trophy.getDisplayConsumer();
            itemStack.setAmount(itemStack.getAmount() - 1);
            this.addItem(blockClicked, clone, slimefunItem.getItemName());
        } else if (definition != null && material != Material.AIR && (blockRank = PlayerStatistics.getBlockRank(player.getUniqueId(), definition)) == BlockRank.SME) {
            ItemStack clone = itemStack.asQuantity(1);
            ItemMeta itemMeta = clone.getItemMeta();
            String materialName = TextUtils.toTitleCase(material.toString());
            itemStack.setAmount(itemStack.getAmount() - 1);
            itemMeta.setDisplayName(String.valueOf(ThemeType.RANK_BLOCK_SME.getColor()) + materialName + " Trophy");
            clone.setItemMeta(itemMeta);
            this.addItem(blockClicked, clone, String.valueOf(ThemeType.RANK_BLOCK_SME.getColor()) + materialName);
            this.locationConsumer = this::defaultConsumer;
        }
    }

    private void addItem(Block block, ItemStack itemStack, String name) {
        Location location = block.getLocation().add(0.5, 1.5, 0.5);
        Item item = GeneralUtils.spawnDisplayItem(itemStack.asQuantity(1), location, name);
        BlockStorage.addBlockInfo((Block)block, (String)"itemUUID", (String)item.getUniqueId().toString());
        this.itemMap.put(block.getLocation(), item.getUniqueId());
    }

    @Override
    public void afterTick(@Nonnull Item item, @Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        Trophy trophy;
        SlimefunItem trophyItem = SlimefunItem.getByItem((ItemStack)item.getItemStack());
        if (trophyItem instanceof Trophy && (trophy = (Trophy)trophyItem).getDisplayConsumer() != null) {
            trophy.getDisplayConsumer().accept(item.getLocation());
        }
    }

    public void defaultConsumer(@Nonnull Location location) {
        ParticleUtils.displayParticleEffect(location.add(0.0, 0.2, 0.0), Particle.WAX_ON, 0.2, 3);
    }
}

