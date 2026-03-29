/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.block.data.Levelled
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.jetbrains.annotations.NotNull
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

public class BalmySponge
extends SlimefunItem {
    private static final NamespacedKey KEY = Keys.newKey("is_saturated");
    private static final String DISPLAY_NAME_SUFFIX = " (Saturated)";
    private final int range;

    @ParametersAreNonnullByDefault
    public BalmySponge(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(itemGroup, item, recipeType, recipe);
        this.range = range;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.getPlaceHandler(), this.getBreakHandler()});
    }

    @Nonnull
    public BlockPlaceHandler getPlaceHandler() {
        return new BlockPlaceHandler(false){

            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                Player player = e.getPlayer();
                ItemStack itemStack = e.getItemInHand();
                ItemMeta itemMeta = itemStack.getItemMeta();
                Block block = e.getBlock();
                if (BalmySponge.isSaturated(itemMeta)) {
                    boolean isCleaned = false;
                    for (BlockFace face : BlockFace.values()) {
                        Levelled levelled;
                        BlockData blockData;
                        Block checkBlock = block.getRelative(face);
                        if (checkBlock.getType() != Material.WATER || !GeneralUtils.hasPermission(player, checkBlock, Interaction.BREAK_BLOCK) || !((blockData = checkBlock.getBlockData()) instanceof Levelled) || (levelled = (Levelled)blockData).getLevel() != 0) continue;
                        checkBlock.setType(Material.OBSIDIAN);
                        isCleaned = true;
                    }
                    if (isCleaned) {
                        BalmySponge.changeSaturation(block, false);
                        block.setType(Material.DEAD_FIRE_CORAL_BLOCK);
                    } else {
                        BalmySponge.changeSaturation(block, true);
                        block.setType(Material.FIRE_CORAL_BLOCK);
                    }
                } else {
                    boolean hasAbsorbed = false;
                    for (Location possibleLocation : BalmySponge.getPossibleLocations(block.getLocation(), BalmySponge.this.range)) {
                        Block checkBlock = possibleLocation.getBlock();
                        if (checkBlock.getType() != Material.LAVA || !GeneralUtils.hasPermission(player, checkBlock, Interaction.BREAK_BLOCK)) continue;
                        checkBlock.setType(Material.AIR);
                        hasAbsorbed = true;
                    }
                    if (hasAbsorbed) {
                        BalmySponge.changeSaturation(block, true);
                        block.setType(Material.FIRE_CORAL_BLOCK);
                    } else {
                        BalmySponge.changeSaturation(block, false);
                        block.setType(Material.DEAD_FIRE_CORAL_BLOCK);
                    }
                }
            }
        };
    }

    @Nonnull
    public BlockBreakHandler getBreakHandler() {
        return new BlockBreakHandler(false, false){

            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                if (BalmySponge.isSaturated(e.getBlock())) {
                    ItemStack itemStack = BalmySponge.this.getItem().clone();
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    BalmySponge.changeSaturation(itemMeta, true);
                    itemMeta.setDisplayName(itemMeta.getDisplayName() + BalmySponge.DISPLAY_NAME_SUFFIX);
                    itemStack.setType(Material.FIRE_CORAL_BLOCK);
                    itemStack.setItemMeta(itemMeta);
                    drops.add(itemStack);
                } else {
                    drops.add(BalmySponge.this.getItem().clone());
                }
            }
        };
    }

    @NotNull
    public Collection<ItemStack> getDrops() {
        return Collections.emptyList();
    }

    private static void changeSaturation(@Nonnull Block block, boolean saturated) {
        BlockStorage.addBlockInfo((Block)block, (String)KEY.value(), (String)Boolean.toString(saturated));
    }

    private static void changeSaturation(@Nonnull ItemMeta itemMeta, boolean saturated) {
        PersistentDataAPI.setBoolean((PersistentDataHolder)itemMeta, (NamespacedKey)KEY, (boolean)saturated);
    }

    private static boolean isSaturated(@Nonnull Block block) {
        String string = BlockStorage.getLocationInfo((Location)block.getLocation(), (String)KEY.value());
        return Boolean.parseBoolean(string);
    }

    private static boolean isSaturated(@Nonnull ItemMeta itemMeta) {
        return PersistentDataAPI.getBoolean((PersistentDataHolder)itemMeta, (NamespacedKey)KEY);
    }

    public static List<Location> getPossibleLocations(@Nonnull Location spongeLocation, int range) {
        ArrayList<Location> circleBlocks = new ArrayList<Location>();
        int bx = spongeLocation.getBlockX();
        int by = spongeLocation.getBlockY();
        int bz = spongeLocation.getBlockZ();
        for (int x = bx - range; x <= bx + range; ++x) {
            for (int y = by - range; y <= by + range; ++y) {
                for (int z = bz - range; z <= bz + range; ++z) {
                    double distance = (bx - x) * (bx - x) + (bz - z) * (bz - z) + (by - y) * (by - y);
                    if (!(distance < (double)(range * range))) continue;
                    Location l = new Location(spongeLocation.getWorld(), (double)x, (double)y, (double)z);
                    circleBlocks.add(l);
                }
            }
        }
        return circleBlocks;
    }
}

