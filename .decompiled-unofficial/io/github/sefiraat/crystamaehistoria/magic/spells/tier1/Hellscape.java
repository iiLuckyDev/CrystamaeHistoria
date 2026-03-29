/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class Hellscape
extends Spell {
    public Hellscape() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20.0, true, 10.0, false, 10, false).makeDamagingSpell(1.0, true, 1.0, false).makeTickingSpell(this::fireProjectiles, 10, true, 5, false).makeProjectileSpell(this::fireProjectiles, 1.0, false, 1.0, false).makeProjectileVsEntitySpell(this::projectileHit).addAfterProjectileHitEntityEvent(this::afterProjectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Location middle = castInformation.getCasterAsPlayer().getLocation().clone().add(0.0, 1.0, 0.0);
        for (double angle = 0.0; angle < Math.PI * 2; angle += 0.15707963267948966) {
            double rotated = angle + (double)(castInformation.getCurrentTick() * 10);
            double x = Math.cos(rotated) * 3.0;
            double z = Math.sin(rotated) * 3.0;
            double dx = Math.cos(rotated) * 4.0;
            double dz = Math.sin(rotated) * 4.0;
            Location spawn = middle.clone().add(x, 0.0, z);
            Location destination = middle.clone().add(dx, 0.0, dz);
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SNOWBALL, spawn, this::onTick);
            magicProjectile.disableGravity();
            magicProjectile.setVelocity(destination, 0.2);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation), castInformation.getCasterAsPlayer().getLocation(), this.getKnockback(castInformation));
        }
    }

    @ParametersAreNonnullByDefault
    public void afterProjectileHit(CastInformation castInformation) {
        ParticleUtils.displayParticleEffect((Entity)castInformation.getMainTarget(), Particle.ANGRY_VILLAGER, 1.0, 5);
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicProjectile magicProjectile) {
        ParticleUtils.displayParticleEffect((Entity)magicProjectile.getProjectile(), Particle.SWEEP_ATTACK, 0.5, 1);
        ParticleUtils.displayParticleEffect((Entity)magicProjectile.getProjectile(), Particle.ANGRY_VILLAGER, 0.5, 1);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.ALCHEMICAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a fiery spiral to ravage foes."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "HELLSCAPE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.FLINT_AND_STEEL;
    }
}

