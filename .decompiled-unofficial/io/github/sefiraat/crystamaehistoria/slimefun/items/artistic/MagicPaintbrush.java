/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag
 *  org.bukkit.DyeColor
 *  org.bukkit.Keyed
 *  org.bukkit.Material
 *  org.bukkit.Tag
 *  org.bukkit.block.Block
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.entity.Axolotl
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Parrot
 *  org.bukkit.entity.Sheep
 *  org.bukkit.entity.Shulker
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.PotionMeta
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PaintProfile;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.DyeColor;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public interface MagicPaintbrush {
    public static ItemStack getTippedBrush(DyeColor dyeColor) {
        return MagicPaintbrush.getTippedBrush(dyeColor, false);
    }

    public static ItemStack getTippedBrush(DyeColor dyeColor, boolean enchanted) {
        ItemStack itemStack = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta potionMeta = (PotionMeta)itemStack.getItemMeta();
        potionMeta.setColor(dyeColor.getColor());
        if (enchanted) {
            potionMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
        }
        potionMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ADDITIONAL_TOOLTIP});
        itemStack.setItemMeta((ItemMeta)potionMeta);
        return itemStack;
    }

    default public boolean tryPaint(PlayerRightClickEvent event, PaintProfile profile, boolean allowEntities) {
        Block block = event.getPlayer().getTargetBlockExact(100);
        Entity entity = event.getPlayer().getTargetEntity(100);
        if (allowEntities && entity != null && GeneralUtils.hasPermission(event.getPlayer(), entity.getLocation(), Interaction.INTERACT_ENTITY)) {
            return this.tryPaintEntity(profile, entity);
        }
        if (block != null && GeneralUtils.hasPermission(event.getPlayer(), block, Interaction.PLACE_BLOCK)) {
            return this.tryPaintBlock(profile, block);
        }
        return false;
    }

    default public boolean tryPaintEntity(PaintProfile profile, Entity entity) {
        Axolotl axolotl;
        EntityType entityType = entity.getType();
        if (entityType == EntityType.SHULKER) {
            Shulker shulker = (Shulker)entity;
            if (shulker.getColor() != profile.getDyeColor()) {
                shulker.setColor(profile.getDyeColor());
                return true;
            }
        } else if (entityType == EntityType.SHEEP) {
            Sheep sheep = (Sheep)entity;
            if (sheep.getColor() != profile.getDyeColor()) {
                sheep.setColor(profile.getDyeColor());
                return true;
            }
        } else if (entityType == EntityType.PARROT && profile.getParrotVariant() != null) {
            Parrot parrot = (Parrot)entity;
            if (parrot.getVariant() != profile.getParrotVariant()) {
                parrot.setVariant(profile.getParrotVariant());
                return true;
            }
        } else if (entityType == EntityType.AXOLOTL && profile.getAxolotlVariant() != null && (axolotl = (Axolotl)entity).getVariant() != profile.getAxolotlVariant()) {
            axolotl.setVariant(profile.getAxolotlVariant());
            return true;
        }
        return false;
    }

    default public boolean tryPaintBlock(PaintProfile profile, Block block) {
        Material material = block.getType();
        if (Tag.WOOL.isTagged((Keyed)material) && block.getType() != profile.getMaterialWool()) {
            block.setType(profile.getMaterialWool());
            return true;
        }
        if (SlimefunTag.TERRACOTTA.isTagged(material) && block.getType() != profile.getMaterialTerracotta()) {
            block.setType(profile.getMaterialTerracotta());
            return true;
        }
        if (CrystaTag.GLAZED_TERRACOTTA.isTagged(material) && block.getType() != profile.getMaterialGlazedTerracotta()) {
            block.setType(profile.getMaterialGlazedTerracotta());
            return true;
        }
        if (SlimefunTag.CONCRETE_POWDERS.isTagged(material) && block.getType() != profile.getMaterialConcretePowder()) {
            block.setType(profile.getMaterialConcretePowder());
            return true;
        }
        if (CrystaTag.CONCRETE_BLOCKS.isTagged(material) && block.getType() != profile.getMaterialConcrete()) {
            block.setType(profile.getMaterialConcrete());
            return true;
        }
        if (Tag.CARPETS.isTagged((Keyed)material) && block.getType() != profile.getMaterialCarpet()) {
            block.setType(profile.getMaterialCarpet());
            return true;
        }
        if (SlimefunTag.GLASS_BLOCKS.isTagged(material) && block.getType() != profile.getMaterialGlass()) {
            block.setType(profile.getMaterialGlass());
            return true;
        }
        if (SlimefunTag.GLASS_PANES.isTagged(material) && block.getType() != profile.getMaterialGlassPane()) {
            block.setType(profile.getMaterialGlassPane());
            return true;
        }
        if (Tag.SHULKER_BOXES.isTagged((Keyed)material) && block.getType() != profile.getMaterialShulker()) {
            block.setType(profile.getMaterialShulker());
            return true;
        }
        return false;
    }
}

