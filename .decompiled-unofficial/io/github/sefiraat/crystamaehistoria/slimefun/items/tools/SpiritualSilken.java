/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.MinecraftVersion
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemSetting
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools;

import io.github.sefiraat.crystamaehistoria.slimefun.types.RefillableUseItem;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class SpiritualSilken
extends RefillableUseItem {
    private static final NamespacedKey KEY = Keys.newKey("uses");
    private static final Set<Material> VALID_MATERIALS = new HashSet<Material>();
    private final Map<Material, ItemSetting<Boolean>> settings = new EnumMap<Material, ItemSetting<Boolean>>(Material.class);

    @ParametersAreNonnullByDefault
    public SpiritualSilken(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amount) {
        super(itemGroup, item, recipeType, recipe);
        for (Material material : VALID_MATERIALS) {
            ItemSetting setting = new ItemSetting((SlimefunItem)this, material.name(), (Object)true);
            this.addItemSetting(new ItemSetting[]{setting});
            this.settings.put(material, (ItemSetting<Boolean>)setting);
        }
        this.setMaxUseCount(amount);
    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Optional blockOptional = e.getClickedBlock();
            if (blockOptional.isPresent()) {
                Block block = (Block)blockOptional.get();
                Material material = block.getType();
                ItemSetting<Boolean> setting = this.settings.get(material);
                if (!Slimefun.getProtectionManager().hasPermission((OfflinePlayer)e.getPlayer(), block, Interaction.BREAK_BLOCK)) {
                    e.getPlayer().sendMessage(String.valueOf(ChatColor.RED) + "You do not have permission!");
                    return;
                }
                if (VALID_MATERIALS.contains(material) && ((Boolean)setting.getValue()).booleanValue()) {
                    block.setType(Material.AIR);
                    block.getState().update(true, true);
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(material));
                    this.damageItem(e.getPlayer(), e.getItem());
                } else {
                    e.getPlayer().sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "This block doesn't seem to react.");
                }
            }
        };
    }

    @Nonnull
    protected NamespacedKey getStorageKey() {
        return KEY;
    }

    static {
        VALID_MATERIALS.add(Material.DIRT_PATH);
        VALID_MATERIALS.add(Material.INFESTED_COBBLESTONE);
        VALID_MATERIALS.add(Material.INFESTED_DEEPSLATE);
        VALID_MATERIALS.add(Material.INFESTED_STONE);
        VALID_MATERIALS.add(Material.INFESTED_CHISELED_STONE_BRICKS);
        VALID_MATERIALS.add(Material.INFESTED_CRACKED_STONE_BRICKS);
        VALID_MATERIALS.add(Material.INFESTED_MOSSY_STONE_BRICKS);
        VALID_MATERIALS.add(Material.INFESTED_STONE_BRICKS);
        VALID_MATERIALS.add(Material.LARGE_FERN);
        VALID_MATERIALS.add(Material.TALL_GRASS);
        VALID_MATERIALS.add(Material.BUDDING_AMETHYST);
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            VALID_MATERIALS.add(Material.REINFORCED_DEEPSLATE);
        }
    }
}

