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
 *  io.github.thebusybiscuit.slimefun4.utils.ChatUtils
 *  org.bukkit.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.CompassMeta
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import java.text.MessageFormat;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class ConnectingCompass
extends SlimefunItem {
    @ParametersAreNonnullByDefault
    public ConnectingCompass(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onItemUse()});
    }

    private ItemUseHandler onItemUse() {
        return e -> {
            Player player = e.getPlayer();
            ItemStack itemStack = e.getItem();
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (!(itemMeta instanceof CompassMeta)) {
                return;
            }
            CompassMeta compassMeta = (CompassMeta)itemMeta;
            if (player.isSneaking()) {
                player.sendMessage(MessageFormat.format("{0}Type a name for this location in chat.", ChatColor.LIGHT_PURPLE));
                ChatUtils.awaitInput((Player)player, s -> this.nameAndSet((String)s, itemStack, player.getEyeLocation()));
            } else {
                if (!compassMeta.hasLodestone()) {
                    return;
                }
                Location location = player.getEyeLocation();
                Location pointToLocation = compassMeta.getLodestone();
                if (location.getWorld().equals((Object)pointToLocation.getWorld())) {
                    Vector vector = ConnectingCompass.getVector(location, compassMeta.getLodestone());
                    ParticleUtils.drawLine(Particle.DUST, location, location.clone().add(vector.multiply(5)), 0.5, new Particle.DustOptions(Color.RED, 1.0f));
                }
            }
        };
    }

    @ParametersAreNonnullByDefault
    private void nameAndSet(String s, @Nullable ItemStack itemStack, Location location) {
        ItemMeta itemMeta;
        if (itemStack != null && (itemMeta = itemStack.getItemMeta()) instanceof CompassMeta) {
            CompassMeta compassMeta = (CompassMeta)itemMeta;
            compassMeta.setLodestone(location);
            compassMeta.setLodestoneTracked(false);
            compassMeta.setDisplayName(String.valueOf(ThemeType.TOOL.getColor()) + s);
            itemStack.setItemMeta((ItemMeta)compassMeta);
        }
    }

    @ParametersAreNonnullByDefault
    public static Vector getVector(Location a, Location b) {
        double dX = a.getX() - b.getX();
        double dY = a.getY() - b.getY();
        double dZ = a.getZ() - b.getZ();
        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);
        return new Vector(x, z, y);
    }
}

