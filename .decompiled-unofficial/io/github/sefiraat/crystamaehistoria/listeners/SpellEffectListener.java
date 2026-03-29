/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.LightningStrike
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.entity.WitherSkeleton
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityChangeBlockEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.event.entity.ProjectileHitEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.event.weather.LightningStrikeEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;

public class SpellEffectListener
implements Listener {
    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        Optional<MagicProjectile> optionalMagicProjectile = CrystamaeHistoria.getProjectileMap().keySet().stream().filter(magicProjectile1 -> magicProjectile1.matches(projectile)).findFirst();
        if (!optionalMagicProjectile.isPresent()) {
            return;
        }
        MagicProjectile magicProjectile = optionalMagicProjectile.get();
        CastInformation castInfo = CrystamaeHistoria.getProjectileCastInfo(magicProjectile);
        Entity hitEntity = event.getHitEntity();
        event.setCancelled(true);
        List passengers = magicProjectile.getProjectile().getPassengers();
        if (event.getHitEntity() != null && !passengers.isEmpty()) {
            for (Entity entity : passengers) {
                if (entity.getUniqueId() != event.getHitEntity().getUniqueId()) continue;
                return;
            }
        }
        castInfo.setProjectileLocation(magicProjectile.getLocation());
        if (this.entityHitAllowed(castInfo, hitEntity)) {
            castInfo.setMainTarget((LivingEntity)hitEntity);
            castInfo.setDamageLocation(hitEntity.getLocation());
            castInfo.runPreAffectEvent();
            castInfo.runAffectEvent();
            castInfo.runPostAffectEvent();
        }
        if (event.getHitBlock() != null) {
            castInfo.setHitBlock(event.getHitBlock());
            castInfo.setDamageLocation(event.getHitBlock().getLocation());
            castInfo.runProjectileHitBlockEvent();
        }
        magicProjectile.kill();
    }

    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onFallingBlockLands(EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof FallingBlock) {
            Optional<MagicFallingBlock> optionalMagicFallingBlock = CrystamaeHistoria.getFallingBlockMap().keySet().stream().filter(magicFallingBlock -> magicFallingBlock.matches((FallingBlock)entity)).findFirst();
            if (!optionalMagicFallingBlock.isPresent()) {
                return;
            }
            MagicFallingBlock magicFallingBlock2 = optionalMagicFallingBlock.get();
            CastInformation castInfo = CrystamaeHistoria.getFallingBlockCastInfo(magicFallingBlock2);
            event.setCancelled(true);
            castInfo.setProjectileLocation(magicFallingBlock2.getLocation());
            castInfo.setHitBlock(event.getBlock());
            castInfo.runProjectileHitBlockEvent();
            magicFallingBlock2.kill();
        }
    }

    private boolean entityHitAllowed(CastInformation castInformation, Entity hitEntity) {
        Interaction interaction;
        Player player = Bukkit.getPlayer((UUID)castInformation.getCaster());
        Interaction interaction2 = interaction = hitEntity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
        if (player != null && GeneralUtils.hasPermission(player, hitEntity.getLocation(), interaction)) {
            return hitEntity instanceof LivingEntity && hitEntity.getUniqueId() != castInformation.getCaster();
        }
        return false;
    }

    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        LightningStrike lightningStrike = event.getLightning();
        UUID uuid = lightningStrike.getUniqueId();
        if (CrystamaeHistoria.getStrikeMap().containsKey(uuid)) {
            CastInformation castInformation = CrystamaeHistoria.getStrikeCastInfo(uuid);
            Location location = event.getLightning().getLocation();
            castInformation.setDamageLocation(location);
            castInformation.runPreAffectEvent();
            castInformation.runAffectEvent();
            castInformation.runPostAffectEvent();
            event.setCancelled(true);
            CrystamaeHistoria.getStrikeMap().remove(uuid);
        }
    }

    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onInvulnerablePlayerDamaged(EntityDamageEvent event) {
        NamespacedKey key = Keys.PDC_IS_INVULNERABLE;
        EntityDamageEvent.DamageCause cause = event.getCause();
        if (event.getEntity() instanceof LivingEntity && PersistentDataAPI.hasLong((PersistentDataHolder)event.getEntity(), (NamespacedKey)key)) {
            LivingEntity livingEntity = (LivingEntity)event.getEntity();
            long expiry = PersistentDataAPI.getLong((PersistentDataHolder)livingEntity, (NamespacedKey)key);
            if (expiry >= System.currentTimeMillis()) {
                if (cause != EntityDamageEvent.DamageCause.CUSTOM && cause != EntityDamageEvent.DamageCause.SUICIDE) {
                    event.setCancelled(true);
                }
            } else {
                PersistentDataAPI.remove((PersistentDataHolder)livingEntity, (NamespacedKey)key);
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onWitherWeatherDeath(EntityDeathEvent event) {
        NamespacedKey key = Keys.PDC_IS_WEATHER_WITHER;
        if (event.getEntity() instanceof WitherSkeleton && PersistentDataAPI.getBoolean((PersistentDataHolder)event.getEntity(), (NamespacedKey)key)) {
            List itemStackList = event.getDrops();
            for (ItemStack itemStack : itemStackList) {
                if (itemStack.getType() != Material.WITHER_SKELETON_SKULL) continue;
                return;
            }
            itemStackList.add(new ItemStack(Material.WITHER_SKELETON_SKULL));
        }
    }

    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onMagicSummonDeath(EntityDeathEvent event) {
        NamespacedKey key = Keys.PDC_IS_SPAWN_OWNER;
        if (DataTypeMethods.hasCustom((PersistentDataHolder)event.getEntity(), key, PersistentUUIDDataType.TYPE)) {
            event.setCancelled(true);
            event.getEntity().remove();
        }
    }

    @EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
    public void onRideRavager(EntityChangeBlockEvent event) {
        NamespacedKey key = Keys.PDC_IS_SPAWN_OWNER;
        if (DataTypeMethods.hasCustom((PersistentDataHolder)event.getEntity(), key, PersistentUUIDDataType.TYPE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        CrystamaeHistoria.getSpellMemory().removeFlight(event.getPlayer());
    }
}

