/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
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
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class StarFall
extends Spell {
    public StarFall() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100.0, true, 7.0, true, 20, true).makeDamagingSpell(2.0, true, 0.5, false).makeProjectileSpell(this::fireProjectiles, 1.0, true, 0.0, false).makeProjectileVsEntitySpell(this::projectileHits).makeProjectileVsBlockSpell(this::projectileHits).makeTickingSpell(this::fireProjectiles, 9, false, 10, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double size = this.getRange(castInformation);
        Collection entities = location.getWorld().getNearbyEntities(location, size, size, size);
        for (Entity entity : entities) {
            if (!GeneralUtils.testChance(1, 5) || !(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster()) continue;
            Location spawnLocation = entity.getLocation().clone().add(0.0, 100.0, 0.0);
            Location destination = spawnLocation.clone().subtract(0.0, 100.0, 0.0);
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.TRIDENT, spawnLocation, this::onTick);
            magicProjectile.setVelocity(destination, 2.0);
            magicProjectile.disableGravity();
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHits(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation));
        }
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicProjectile magicProjectile) {
        Location location = magicProjectile.getProjectile().getLocation();
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB((int)135, (int)50, (int)235), 2.0f);
        location.getWorld().spawnParticle(Particle.DUST, location, 10, (Object)dustOptions);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.CELESTIAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Rains celestial beings from the skies", "to decimate your opponents."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "STAR_FALL";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.NETHER_STAR;
    }
}

