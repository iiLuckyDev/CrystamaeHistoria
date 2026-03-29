/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.ExpCollector;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder.PrismaticGilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class RefractingLensListener
implements Listener {
    @EventHandler(priority=EventPriority.LOW, ignoreCancelled=true)
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)player.getInventory().getItemInMainHand());
        Block block = e.getClickedBlock();
        if (block != null && e.getAction() == Action.RIGHT_CLICK_BLOCK && slimefunItem instanceof RefactingLens) {
            e.setCancelled(true);
            GeneralUtils.putOnCooldown(player.getInventory().getItemInMainHand(), 3);
            SlimefunItem item = BlockStorage.check((Block)block);
            if (item instanceof LiquefactionBasin) {
                this.liquefactionBasin(player, item, block);
            } else if (item instanceof ExpCollector) {
                this.expCollector(item, block);
            } else if (item instanceof PrismaticGilder) {
                this.prismaticGilder(item, block);
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void liquefactionBasin(Player player, SlimefunItem blockItem, Block clickedBlock) {
        Location location = clickedBlock.getLocation().add(0.5, 1.0, 0.5);
        LiquefactionBasin basin = (LiquefactionBasin)blockItem;
        Map<StoryType, Integer> cacheMap = basin.getCacheMap().get(clickedBlock.getLocation()).getContentMap();
        double space = 0.5;
        int numberToDisplay = cacheMap.size();
        int mod = numberToDisplay % 2;
        double evenOffset = mod == 0 ? 0.25 : 0.0;
        double xPos = 0.5 * (double)(numberToDisplay - mod);
        for (Map.Entry<StoryType, Integer> entry : cacheMap.entrySet()) {
            Integer integer = entry.getValue();
            StoryType storyType = entry.getKey();
            ItemStack itemStack = Materials.getDummyCrystalMap().get((Object)storyType).getItem().clone();
            double xOffset = 0.5 * xPos - evenOffset;
            Vector pointVector = new Vector(xOffset, 0.0, 0.0).rotateAroundY(-((double)player.getLocation().getYaw() * (Math.PI / 180)));
            DisplayItem displayItem = new DisplayItem(itemStack, location.clone().add(pointVector), String.valueOf(ThemeType.getByType(storyType).getColor()) + String.valueOf(integer), item -> {
                Particle.DustOptions dustOptions = ThemeType.getByType(storyType).getDustOptions(1.0f);
                ParticleUtils.displayParticleEffect((Entity)item, 0.3, 4, dustOptions);
            });
            displayItem.registerRemoval(3000L);
            xPos -= 1.0;
        }
    }

    private void expCollector(SlimefunItem blockItem, Block clickedBlock) {
        ExpCollector collector = (ExpCollector)blockItem;
        Location location = clickedBlock.getLocation();
        int volume = collector.getVolumeMap().get(clickedBlock.getLocation());
        ItemStack itemStack = new ItemStack(Material.EXPERIENCE_BOTTLE);
        DisplayItem displayItem = new DisplayItem(itemStack, location.clone().add(0.5, 1.0, 0.5), String.valueOf(ChatColor.GREEN) + String.valueOf(volume), item -> {
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GREEN, 1.0f);
            ParticleUtils.displayParticleEffect((Entity)item, 0.3, 4, dustOptions);
        });
        displayItem.registerRemoval(3000L);
    }

    private void prismaticGilder(SlimefunItem blockItem, Block clickedBlock) {
        PrismaticGilder gilder = (PrismaticGilder)blockItem;
        Location location = clickedBlock.getLocation();
        int volume = gilder.getCacheMap().get(location).getFillAmount();
        ItemStack itemStack = Skulls.CRYSTAL_PRISMATIC.getPlayerHead();
        DisplayItem displayItem = new DisplayItem(itemStack, location.clone().add(0.5, 1.5, 0.5), String.valueOf(ChatColor.LIGHT_PURPLE) + String.valueOf(volume), item -> {
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE, 1.0f);
            ParticleUtils.displayParticleEffect((Entity)item, 0.3, 4, dustOptions);
        });
        displayItem.registerRemoval(3000L);
    }
}

