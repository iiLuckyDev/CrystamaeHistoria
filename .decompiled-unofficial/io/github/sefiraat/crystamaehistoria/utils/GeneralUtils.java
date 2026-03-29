/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.block.Block
 *  org.bukkit.block.TileState
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public final class GeneralUtils {
    public static boolean testChance(int chance, int upper) {
        return GeneralUtils.roll(upper, true) <= chance;
    }

    public static int roll(int upper, boolean upLimit) {
        if (upLimit) {
            ++upper;
        }
        return ThreadLocalRandom.current().nextInt(1, upper);
    }

    public static boolean testChance(double chance, double upper) {
        return GeneralUtils.roll(upper, true) <= chance;
    }

    public static double roll(double upper, boolean upLimit) {
        if (upLimit) {
            upper += 1.0;
        }
        return ThreadLocalRandom.current().nextDouble(1.0, upper);
    }

    public static int roll(int upper) {
        return GeneralUtils.roll(upper, true);
    }

    public static double roll(double upper) {
        return GeneralUtils.roll(upper, true);
    }

    @ParametersAreNonnullByDefault
    public static void markBlockForRemoval(Block block, int secondsUntilRemoval) {
        long timeUntilRemoval = (long)secondsUntilRemoval * 1000L;
        block.setMetadata("ch", (MetadataValue)new FixedMetadataValue((Plugin)CrystamaeHistoria.getInstance(), (Object)"y"));
        long removalTime = System.currentTimeMillis() + timeUntilRemoval;
        CrystamaeHistoria.getSpellMemory().getBlocksToRemove().put(new BlockPosition(block), removalTime);
    }

    @ParametersAreNonnullByDefault
    public static boolean isRemovableBlock(Block block) {
        return block.hasMetadata("ch");
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(Player player, Block block, Interaction interaction) {
        return GeneralUtils.hasPermission(player.getUniqueId(), block.getLocation(), interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(UUID player, Location location, Interaction interaction) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer((UUID)player);
        if (interaction == Interaction.ATTACK_PLAYER && !location.getWorld().getPVP()) {
            return false;
        }
        return Slimefun.getProtectionManager().hasPermission(offlinePlayer, location, interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(Player player, Location location, Interaction interaction) {
        return GeneralUtils.hasPermission(player.getUniqueId(), location, interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean pullEntity(UUID caster, Location pullToLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector().subtract(pullToLocation.toVector()).normalize().multiply(-force);
        return GeneralUtils.pushEntity(caster, vector, pushed);
    }

    @ParametersAreNonnullByDefault
    public static void pullEntity(Location pullToLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector().subtract(pullToLocation.toVector()).normalize().multiply(-force);
        GeneralUtils.pushEntity(vector, pushed);
    }

    @ParametersAreNonnullByDefault
    public static boolean pushEntity(UUID caster, Vector vector, Entity pushed) {
        Interaction interaction;
        Interaction interaction2 = interaction = pushed instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.INTERACT_ENTITY;
        if (GeneralUtils.hasPermission(caster, pushed.getLocation(), interaction)) {
            pushed.setVelocity(vector);
            return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public static void pushEntity(Vector vector, Entity pushed) {
        pushed.setVelocity(vector);
    }

    @ParametersAreNonnullByDefault
    public static boolean pushEntity(UUID caster, Vector pushVector, Entity pushed, double force) {
        return GeneralUtils.pushEntity(caster, pushVector.multiply(force), pushed);
    }

    @ParametersAreNonnullByDefault
    public static boolean tryBreakBlock(UUID caster, Block block) {
        Player player = Bukkit.getPlayer((UUID)caster);
        if (GeneralUtils.blockCanBeBroken(caster, block) && player != null) {
            block.breakNaturally(player.getInventory().getItemInMainHand());
            return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public static boolean blockCanBeBroken(UUID caster, Block block) {
        return GeneralUtils.hasPermission(caster, block, Interaction.BREAK_BLOCK) && !BlockStorage.hasBlockInfo((Block)block) && !(block.getState() instanceof TileState) && block.getType().getHardness() != -1.0f && !block.getType().isAir();
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(UUID player, Block block, Interaction interaction) {
        return GeneralUtils.hasPermission(player, block.getLocation(), interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity, UUID caster, double damage) {
        return GeneralUtils.damageEntity(livingEntity, caster, damage, null, 0.0);
    }

    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity, UUID caster, double damage, @Nullable Location knockbackOrigin, double knockbackForce) {
        Interaction interaction;
        Interaction interaction2 = interaction = livingEntity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
        if (GeneralUtils.hasPermission(caster, livingEntity.getLocation(), interaction)) {
            Player player = Bukkit.getPlayer((UUID)caster);
            livingEntity.damage(damage, (Entity)player);
            if (knockbackOrigin != null && knockbackForce > 0.0) {
                GeneralUtils.pushEntity(caster, knockbackOrigin, (Entity)livingEntity, knockbackForce);
            }
            return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public static boolean pushEntity(UUID caster, Location pushFromLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector().subtract(pushFromLocation.toVector()).normalize().multiply(force);
        return GeneralUtils.pushEntity(caster, vector, pushed);
    }

    @ParametersAreNonnullByDefault
    public static void healEntity(LivingEntity livingEntity, double healAmount) {
        AttributeInstance attribute = livingEntity.getAttribute(Attribute.MAX_HEALTH);
        if (attribute != null) {
            livingEntity.setHealth(Math.min(attribute.getValue(), livingEntity.getHealth() + healAmount));
        }
    }

    @ParametersAreNonnullByDefault
    public static Item spawnDisplayItem(ItemStack stack, Location location, String name) {
        Item item = location.getWorld().dropItem(location, stack);
        PersistentDataAPI.setBoolean((PersistentDataHolder)item, (NamespacedKey)Keys.PDC_IS_DISPLAY_ITEM, (boolean)true);
        item.setCustomName(name);
        item.setCustomNameVisible(true);
        item.setGravity(false);
        item.setVelocity(new Vector(0, 0, 0));
        item.setCanPlayerPickup(false);
        item.setPickupDelay(Integer.MAX_VALUE);
        return item;
    }

    @ParametersAreNonnullByDefault
    public static void putOnCooldown(ItemStack itemStack, int durationInSeconds) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            PersistentDataAPI.setLong((PersistentDataHolder)itemMeta, (NamespacedKey)Keys.PDC_ON_COOLDOWN, (long)(System.currentTimeMillis() + (long)durationInSeconds * 1000L));
            itemStack.setItemMeta(itemMeta);
        }
    }

    @ParametersAreNonnullByDefault
    public static boolean isOnCooldown(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            long cooldownUntil = PersistentDataAPI.getLong((PersistentDataHolder)itemMeta, (NamespacedKey)Keys.PDC_ON_COOLDOWN, (long)0L);
            return System.currentTimeMillis() < cooldownUntil;
        }
        return false;
    }

    @Nonnull
    public static ItemStack getPreEnchantedItemStack(Material material) {
        return GeneralUtils.getPreEnchantedItemStack(material, true, new Pair((Object)Enchantment.LURE, (Object)1));
    }

    @Nonnull
    @SafeVarargs
    public static ItemStack getPreEnchantedItemStack(Material material, boolean hide, Pair<Enchantment, Integer> ... enchantments) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (Pair<Enchantment, Integer> pair : enchantments) {
            itemMeta.addEnchant((Enchantment)pair.getFirstValue(), ((Integer)pair.getSecondValue()).intValue(), true);
        }
        if (hide) {
            itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Generated
    private GeneralUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

