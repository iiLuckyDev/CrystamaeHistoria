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
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonNova
extends Spell {
    public PoisonNova() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20.0, true, 20.0, true, 10, true).makeDamagingSpell(1.0, false, 0.0, false).makeProjectileSpell(this::fireProjectile, 0.0, false, 0.0, false).makeProjectileVsEntitySpell(this::projectileHit).addAfterProjectileHitEntityEvent(this::afterProjectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        double sizeEnd = this.getRange(castInformation);
        boolean sizeCast = true;
        int stepSize = 3;
        Location middle = castInformation.getCastLocation().clone().add(0.0, 1.0, 0.0);
        for (double i = 0.0; i < 360.0; i += (double)stepSize) {
            double angle = i * Math.PI / 180.0;
            double sx = (double)sizeCast * Math.cos(angle);
            double sz = (double)sizeCast * Math.sin(angle);
            double dx = sizeEnd * Math.cos(angle);
            double dz = sizeEnd * Math.sin(angle);
            Location spawn = middle.clone().add(sx, 0.0, sz);
            Location destination = middle.clone().add(dx, 1.0, dz);
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.ENDER_PEARL, spawn);
            magicProjectile.setVelocity(destination, 1.0);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        LivingEntity hit = castInformation.getMainTarget();
        if (hit.getHealth() == 1.0) {
            GeneralUtils.damageEntity(hit, castInformation.getCaster(), this.getDamage(castInformation));
        } else {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON, castInformation.getStaveLevel() * 100, castInformation.getStaveLevel());
            hit.addPotionEffect(potionEffect);
            this.setLastDamageToCaster(hit, castInformation);
        }
    }

    @ParametersAreNonnullByDefault
    public void afterProjectileHit(CastInformation castInformation) {
        ParticleUtils.displayParticleEffect((Entity)castInformation.getMainTarget(), Particle.CRIMSON_SPORE, 1.0, 10);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.HUMAN, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a nova of poisonous bullets that", "tear through enemies and cause them to", "get sick."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "POISON_NOVA";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.SLIME_BALL;
    }
}

