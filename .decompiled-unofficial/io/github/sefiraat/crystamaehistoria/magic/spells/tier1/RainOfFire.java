/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
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
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class RainOfFire
extends Spell {
    private static final int PROJECTILES_PER_WAVE = 5;

    public RainOfFire() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100.0, true, 20.0, false, 20, true).makeDamagingSpell(5.0, true, 0.5, false).makeProjectileSpell(this::fireProjectiles, 1.0, true, 0.5, true).makeProjectileVsEntitySpell(this::projectileHits).addBeforeProjectileHitEntityEvent(this::beforeProjectileHits).makeTickingSpell(this::fireProjectiles, 9, false, 10, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        for (int i = 0; i < 5 * castInformation.getStaveLevel(); ++i) {
            double range = this.getRange(castInformation);
            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1.0);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1.0);
            Location spawnLocation = new Location(location.getWorld(), location.getX() + xOffset, location.getY() + 20.0, location.getZ() + zOffset);
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.FIREBALL, spawnLocation);
            Location destination = spawnLocation.clone().subtract(0.0, 20.0, 0.0);
            magicProjectile.setVelocity(destination, 2.0);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHits(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation), castInformation.getDamageLocation(), this.getProjectileKnockback(castInformation));
        }
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHits(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            livingEntity.setFireTicks(80);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.HUMAN, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons an epic hellscape of raining", "fire."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "RAIN_OF_FIRE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.FIRE_CHARGE;
    }
}

