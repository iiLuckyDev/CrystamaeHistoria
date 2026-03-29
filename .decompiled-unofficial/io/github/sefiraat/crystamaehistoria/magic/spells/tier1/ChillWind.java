/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class ChillWind
extends Spell {
    public ChillWind() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(120.0, true, 7.0, false, 25, true).makeTickingSpell(this::cast, 20, true, 5, false).makeEffectingSpell(false, false).addNegativeEffect(PotionEffectType.SLOWNESS, 4, 1).addNegativeEffect(PotionEffectType.MINING_FATIGUE, 4, 1);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCasterAsPlayer().getLocation().clone().add(0.0, 1.0, 0.0);
        double range = this.getRange(castInformation);
        double effectRange = range * 0.75;
        int density = 20;
        for (double height = 0.0; height <= Math.PI; height += 0.15707963267948966) {
            double r = range * Math.sin(height);
            double y = range * Math.cos(height);
            for (double a = 0.0; a < Math.PI * 2; a += 0.15707963267948966) {
                double x = Math.cos(a) * r;
                double z = Math.sin(a) * r;
                Location point = location.clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, Particle.END_ROD, 0.1, 1);
            }
        }
        for (Entity entity : location.getWorld().getNearbyEntities(location, effectRange, effectRange, effectRange)) {
            if (!(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster()) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            livingEntity.setFreezeTicks(Math.min(livingEntity.getMaxFreezeTicks(), livingEntity.getFreezeTicks() + 20));
            if (livingEntity.getFreezeTicks() != livingEntity.getMaxFreezeTicks()) continue;
            this.applyPositiveEffects(livingEntity, castInformation);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.ALCHEMICAL, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Calls a chill wind around the caster that", "will slowly chill and eventually freeze", "nearby creatures."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "CHILL_WIND";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.BLUE_ICE;
    }
}

