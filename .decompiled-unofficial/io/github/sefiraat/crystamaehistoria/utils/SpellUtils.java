/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.entity.ai.MobGoals
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.Mob
 *  org.bukkit.entity.Projectile
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.projectiles.ProjectileSource
 */
package io.github.sefiraat.crystamaehistoria.utils;

import com.destroystokyo.paper.entity.ai.MobGoals;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Projectile;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.projectiles.ProjectileSource;

public final class SpellUtils {
    @ParametersAreNonnullByDefault
    public static <T extends Mob> MagicSummon summonTemporaryMob(EntityType entityType, UUID caster, Location location, @Nullable AbstractGoal<T> goal) {
        return SpellUtils.summonTemporaryMob(entityType, caster, location, goal, 30);
    }

    @ParametersAreNonnullByDefault
    public static <T extends Mob> MagicSummon summonTemporaryMob(EntityType entityType, UUID caster, Location location, @Nullable AbstractGoal<T> goal, int timeInSeconds) {
        return SpellUtils.summonTemporaryMob(entityType, caster, location, goal, (long)timeInSeconds * 1000L, null);
    }

    @ParametersAreNonnullByDefault
    private static <T extends Mob> MagicSummon summonTemporaryMob(EntityType entityType, UUID caster, Location location, @Nullable AbstractGoal<T> goal, long duration, @Nullable Consumer<MagicSummon> tickConsumer) {
        Mob mob = (Mob)location.getWorld().spawnEntity(location, entityType);
        MagicSummon magicSummon = new MagicSummon(mob.getUniqueId(), caster);
        MobGoals mobGoals = Bukkit.getMobGoals();
        if (tickConsumer != null) {
            magicSummon.setTickConsumer(tickConsumer);
        }
        CrystamaeHistoria.getSummonedEntityMap().put(magicSummon, System.currentTimeMillis() + duration);
        DataTypeMethods.setCustom((PersistentDataHolder)mob, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE, caster);
        mob.setCustomName(ThemeType.getRandomEggName());
        if (goal == null) {
            mobGoals.removeAllGoals(mob);
        } else {
            goal.setSelf(mob);
            mobGoals.addGoal(mob, 1, goal);
        }
        return magicSummon;
    }

    @ParametersAreNonnullByDefault
    public static <T extends Mob> MagicSummon summonTemporaryMob(EntityType entityType, UUID caster, Location location, @Nullable AbstractGoal<T> goal, Consumer<MagicSummon> tickConsumer) {
        return SpellUtils.summonTemporaryMob(entityType, caster, location, goal, 120, tickConsumer);
    }

    @ParametersAreNonnullByDefault
    public static <T extends Mob> MagicSummon summonTemporaryMob(EntityType entityType, UUID caster, Location location, @Nullable AbstractGoal<T> goal, int timeInSeconds, Consumer<MagicSummon> tickConsumer) {
        return SpellUtils.summonTemporaryMob(entityType, caster, location, goal, (long)timeInSeconds * 1000L, tickConsumer);
    }

    @ParametersAreNonnullByDefault
    public static MagicProjectile summonMagicProjectile(CastInformation castInformation, EntityType entityType, Location location) {
        return SpellUtils.summonMagicProjectile(castInformation, entityType, location, 5);
    }

    @ParametersAreNonnullByDefault
    public static MagicProjectile summonMagicProjectile(CastInformation castInformation, EntityType entityType, Location location, int timeInSeconds) {
        return SpellUtils.summonMagicProjectile(castInformation, entityType, location, (long)timeInSeconds * 1000L, null);
    }

    @ParametersAreNonnullByDefault
    private static MagicProjectile summonMagicProjectile(CastInformation castInformation, EntityType entityType, Location location, long duration, @Nullable Consumer<MagicProjectile> tickConsumer) {
        Projectile projectile = (Projectile)location.getWorld().spawnEntity(location, entityType);
        MagicProjectile magicProjectile = new MagicProjectile(projectile);
        projectile.setShooter((ProjectileSource)Bukkit.getPlayer((UUID)castInformation.getCaster()));
        projectile.setBounce(false);
        if (projectile instanceof Fireball) {
            Fireball fireball = (Fireball)projectile;
            fireball.setIsIncendiary(false);
            fireball.setYield(0.0f);
        }
        if (tickConsumer != null) {
            magicProjectile.setConsumer(tickConsumer);
        }
        CrystamaeHistoria.getProjectileMap().put(magicProjectile, (Pair<CastInformation, Long>)new Pair((Object)castInformation, (Object)(System.currentTimeMillis() + duration)));
        return magicProjectile;
    }

    @ParametersAreNonnullByDefault
    public static MagicProjectile summonMagicProjectile(CastInformation castInformation, EntityType entityType, Location location, Consumer<MagicProjectile> tickConsumer) {
        return SpellUtils.summonMagicProjectile(castInformation, entityType, location, 30, tickConsumer);
    }

    @ParametersAreNonnullByDefault
    public static MagicProjectile summonMagicProjectile(CastInformation castInformation, EntityType entityType, Location location, int timeInSeconds, Consumer<MagicProjectile> tickConsumer) {
        return SpellUtils.summonMagicProjectile(castInformation, entityType, location, (long)timeInSeconds * 1000L, tickConsumer);
    }

    @ParametersAreNonnullByDefault
    public static MagicFallingBlock summonMagicFallingBlock(CastInformation castInformation, Location location, Material material) {
        return SpellUtils.summonMagicFallingBlock(castInformation, location, material, 30);
    }

    @ParametersAreNonnullByDefault
    public static MagicFallingBlock summonMagicFallingBlock(CastInformation castInformation, Location location, Material material, int timeInSeconds) {
        return SpellUtils.summonMagicFallingBlock(castInformation, location, material, (long)timeInSeconds * 1000L);
    }

    @ParametersAreNonnullByDefault
    private static MagicFallingBlock summonMagicFallingBlock(CastInformation castInformation, Location location, Material material, long duration) {
        FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(location, material.createBlockData());
        MagicFallingBlock magicFallingBlock = new MagicFallingBlock(fallingBlock);
        CrystamaeHistoria.getFallingBlockMap().put(magicFallingBlock, (Pair<CastInformation, Long>)new Pair((Object)castInformation, (Object)(System.currentTimeMillis() + duration)));
        return magicFallingBlock;
    }

    @Generated
    private SpellUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

