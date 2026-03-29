/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.LightningStrike
 *  org.bukkit.entity.LivingEntity
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;

public class Tempest
extends Spell {
    private static final double PROJECTILE_NUMBER = 5.0;

    public Tempest() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(200.0, true, 20.0, false, 10, true).makeDamagingSpell(2.0, true, 0.0, false).makeProjectileSpell(this::fireProjectiles, 2.0, false, 2.0, false).makeProjectileVsEntitySpell(this::onProjectileHit).addBeforeProjectileHitEntityEvent(this::beforeProjectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        int i = 0;
        while ((double)i < 5.0 * (double)castInformation.getStaveLevel()) {
            double range = this.getRange(castInformation);
            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1.0);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1.0);
            double x = location.getX() + xOffset;
            double z = location.getZ() + zOffset;
            Location spawnLocation = new Location(location.getWorld(), location.getX() + xOffset, (double)location.getWorld().getHighestBlockYAt((int)x, (int)z), location.getZ() + zOffset);
            LightningStrike lightningStrike = spawnLocation.getWorld().strikeLightning(spawnLocation);
            this.registerLightningStrike(lightningStrike, castInformation);
            ++i;
        }
    }

    @ParametersAreNonnullByDefault
    public void onProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation), castInformation.getDamageLocation(), this.getKnockback(castInformation));
        }
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            livingEntity.setFireTicks(40);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.MECHANICAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a tempest of lightning around the", "caster causing damage and knockback."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "TEMPEST";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.END_ROD;
    }
}

