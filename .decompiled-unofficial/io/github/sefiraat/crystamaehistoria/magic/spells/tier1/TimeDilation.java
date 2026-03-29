/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class TimeDilation
extends Spell {
    public TimeDilation() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40.0, true, 10.0, false, 25, true).makeTickingSpell(this::cast, 10, true, 20, false).makeEffectingSpell(true, false).addNegativeEffect(PotionEffectType.JUMP_BOOST, -2, 2).addNegativeEffect(PotionEffectType.SLOW_FALLING, 2, 2).addNegativeEffect(PotionEffectType.SLOWNESS, 2, 2).addNegativeEffect(PotionEffectType.MINING_FATIGUE, 2, 2);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB((int)120, (int)20, (int)200), 1.0f);
        Location location = castInformation.getCastLocation().clone().add(0.0, 1.0, 0.0);
        double range = this.getRange(castInformation);
        double effectRange = range * 0.75;
        int density = 30;
        for (double height = 0.0; height <= Math.PI; height += 0.10471975511965977) {
            double r = range * Math.sin(height);
            double y = range * Math.cos(height);
            for (double a = 0.0; a < Math.PI * 2; a += 0.10471975511965977) {
                double x = Math.cos(a) * r;
                double z = Math.sin(a) * r;
                Location point = location.clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, 0.1, 1, dustOptions);
            }
        }
        for (Entity entity : location.getWorld().getNearbyEntities(location, effectRange, effectRange, effectRange)) {
            if (!(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster()) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            this.applyNegativeEffects(livingEntity, castInformation);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HISTORICAL, StoryType.ANIMAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Makes all things around the caster", "shift into a different time-space."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "TIME_DILATION";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CUT_COPPER;
    }
}

