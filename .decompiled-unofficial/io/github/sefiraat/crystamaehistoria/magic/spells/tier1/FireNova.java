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

public class FireNova
extends Spell {
    public FireNova() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20.0, true, 10.0, false, 10, false).makeDamagingSpell(3.0, true, 1.0, false).makeProjectileSpell(this::fireProjectiles, 2.0, false, 1.0, false).makeProjectileVsEntitySpell(this::projectileHit).addAfterProjectileHitEntityEvent(this::afterProjectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        double sizeEnd = this.getRange(castInformation);
        int sizeCast = 2;
        int stepSize = 3;
        Location middle = castInformation.getCastLocation().clone().add(0.0, 1.0, 0.0);
        for (double i = 0.0; i < 360.0; i += (double)stepSize) {
            double angle = i * Math.PI / 180.0;
            int sx = (int)((double)sizeCast * Math.cos(angle));
            int sz = (int)((double)sizeCast * Math.sin(angle));
            int dx = (int)(sizeEnd * Math.cos(angle));
            int dz = (int)(sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add((double)sx, 0.0, (double)sz);
            Location destination = middle.clone().add((double)dx, 0.0, (double)dz);
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SMALL_FIREBALL, spawn);
            magicProjectile.setVelocity(destination, 1.0);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation));
        }
    }

    @ParametersAreNonnullByDefault
    public void afterProjectileHit(CastInformation castInformation) {
        ParticleUtils.displayParticleEffect((Entity)castInformation.getMainTarget(), Particle.EXPLOSION, 1.0, 5);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.CELESTIAL, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a massive nova of fireballs", "around you to incinerate foes."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "FIRE_NOVA";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.FIRE_CHARGE;
    }
}

