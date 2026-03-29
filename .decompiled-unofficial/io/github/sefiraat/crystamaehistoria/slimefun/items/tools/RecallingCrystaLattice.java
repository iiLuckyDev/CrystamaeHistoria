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
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  io.github.thebusybiscuit.slimefun4.utils.ChatUtils
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.event.player.PlayerTeleportEvent$TeleportCause
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataContainer
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.Waystone;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import java.text.MessageFormat;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class RecallingCrystaLattice
extends SlimefunItem {
    @ParametersAreNonnullByDefault
    public RecallingCrystaLattice(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onItemUse()});
    }

    private ItemUseHandler onItemUse() {
        return e -> {
            if (e.getPlayer().isSneaking()) {
                this.setLocation(e);
            } else {
                this.teleport(e);
            }
        };
    }

    @ParametersAreNonnullByDefault
    private void setLocation(PlayerRightClickEvent event) {
        Optional blockOptional = event.getClickedBlock();
        if (blockOptional.isPresent()) {
            Block block = (Block)blockOptional.get();
            SlimefunItem slimefunItem = BlockStorage.check((Block)block);
            Location location = block.getLocation();
            Player player = event.getPlayer();
            if (slimefunItem instanceof Waystone && GeneralUtils.hasPermission(player, location, Interaction.PLACE_BLOCK)) {
                ItemStack itemStack = event.getItem();
                ItemMeta itemMeta = itemStack.getItemMeta();
                PersistentDataContainer container = itemMeta.getPersistentDataContainer();
                container.set(Keys.newKey("location"), DataType.LOCATION, (Object)location);
                itemStack.setItemMeta(itemMeta);
                player.sendMessage(MessageFormat.format("{0}Type the name of this Waystone into chat.", ChatColor.LIGHT_PURPLE));
                ChatUtils.awaitInput((Player)player, s -> this.renameItem((String)s, itemStack));
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void teleport(PlayerRightClickEvent event) {
        ItemStack itemStack = event.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if (container.has(Keys.newKey("location"), DataType.LOCATION)) {
            Location location = (Location)container.get(Keys.newKey("location"), DataType.LOCATION);
            Block block = location.getBlock();
            SlimefunItem slimefunItem = BlockStorage.check((Block)block);
            if (slimefunItem instanceof Waystone && GeneralUtils.hasPermission(event.getPlayer(), location, Interaction.PLACE_BLOCK)) {
                event.getPlayer().teleportAsync(location.add(1.0, 1.0, 1.0), PlayerTeleportEvent.TeleportCause.PLUGIN);
            } else {
                event.getPlayer().sendActionBar(Component.text((String)"Waystone connection isn't functional").color(TextColor.color((int)200, (int)30, (int)40)));
            }
        } else {
            event.getPlayer().sendMessage(MessageFormat.format("{0}Bind the Lattice to a Waystone using Shift + Right Click.", ChatColor.RED));
        }
    }

    @ParametersAreNonnullByDefault
    private void renameItem(String s, @Nullable ItemStack itemStack) {
        if (itemStack != null) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(String.valueOf(ThemeType.TOOL.getColor()) + s);
            itemStack.setItemMeta(itemMeta);
        }
    }
}

