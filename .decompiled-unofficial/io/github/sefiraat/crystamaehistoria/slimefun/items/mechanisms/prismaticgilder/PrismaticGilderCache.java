/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.GildingUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PrismaticGilderCache
extends AbstractCache {
    protected static final String AMOUNT = "ch_amount";
    private final int maxVolume;
    private int fillAmount;

    @ParametersAreNonnullByDefault
    public PrismaticGilderCache(BlockMenu blockMenu, int maxVolume) {
        super(blockMenu);
        this.maxVolume = maxVolume;
        String activePlayerString = BlockStorage.getLocationInfo((Location)blockMenu.getLocation(), (String)"BS_CP_ACTIVE_PLAYER");
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    @ParametersAreNonnullByDefault
    public void consumeItems() {
        Location location = this.blockMenu.getLocation();
        Collection entitiesToPull = location.getWorld().getNearbyEntities(location.clone().add(0.5, 0.5, 0.5), 3.0, 3.0, 3.0, Item.class::isInstance);
        for (Entity entity : entitiesToPull) {
            Item item = (Item)entity;
            SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)item.getItemStack());
            if (slimefunItem == null || !slimefunItem.equals((Object)Materials.getPrismaticCrystal())) continue;
            GeneralUtils.pullEntity(location, entity, 0.5);
        }
        Collection entities = location.getWorld().getNearbyEntities(location.clone().add(0.5, 0.5, 0.5), 0.75, 0.75, 0.75, Item.class::isInstance);
        for (Entity entity : entities) {
            Item item = (Item)entity;
            SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)item.getItemStack());
            if (slimefunItem != null && slimefunItem.equals((Object)Materials.getPrismaticCrystal())) {
                this.addCrystamae(item);
            } else {
                this.rejectItem(item);
            }
            this.syncBlock();
        }
    }

    @ParametersAreNonnullByDefault
    public void gildItem(Block block, ItemStack heldItem, Player player) {
        Location location = block.getLocation().clone().add(0.5, 1.5, 0.5);
        if (heldItem.getType() != Material.AIR && StoryUtils.isStoried(heldItem) && !StoryUtils.hasRemainingStorySlots(heldItem) && !GildingUtils.isGilded(heldItem)) {
            BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(heldItem.getType());
            if (this.fillAmount >= definition.getBlockTier().tier) {
                ItemStack gildedStack = heldItem.asQuantity(1);
                this.fillAmount -= definition.getBlockTier().tier;
                GildingUtils.makeGilded(gildedStack);
                DisplayItem displayItem = new DisplayItem(gildedStack, location, "", item -> {
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.YELLOW, 1.0f);
                    ParticleUtils.displayParticleEffect((Entity)item, 1.5, 20, dustOptions);
                    location.getWorld().dropItem(location, gildedStack);
                });
                heldItem.setAmount(heldItem.getAmount() - 1);
                displayItem.registerRemoval(2000L);
                PlayerStatistics.unlockStoryGilded(player.getUniqueId(), definition);
            } else {
                ParticleUtils.displayParticleEffect(location, Particle.ENCHANTED_HIT, 1.0, 3);
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void rejectItem(Item item) {
        double velX = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        double velZ = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        item.setVelocity(new Vector(velX, 0.5, velZ));
    }

    @ParametersAreNonnullByDefault
    private void addCrystamae(Item item) {
        int numberInStack = item.getItemStack().getAmount();
        if (this.fillAmount >= this.maxVolume) {
            this.rejectItem(item);
        } else {
            this.fillAmount = Math.min(this.maxVolume, this.fillAmount + 1);
            if (numberInStack > 1) {
                CrystamaeHistoria.getSupportedPluginManager().setStackAmount(item, numberInStack - 1);
            } else {
                item.remove();
            }
        }
        ParticleUtils.displayParticleEffect(this.blockMenu.getLocation(), Particle.FLASH, 1.0, 5);
    }

    public void syncBlock() {
        BlockStorage.addBlockInfo((Block)this.blockMenu.getBlock(), (String)AMOUNT, (String)String.valueOf(this.fillAmount));
    }

    public int getFillAmount() {
        return this.fillAmount;
    }

    public void setFillAmount(int fillAmount) {
        this.fillAmount = fillAmount;
    }

    @Generated
    public int getMaxVolume() {
        return this.maxVolume;
    }
}

