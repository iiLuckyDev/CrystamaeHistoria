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
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.potion.PotionEffectType
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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class Quake
extends Spell {
    public Quake() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100.0, true, 30.0, false, 20, true).makeDamagingSpell(2.0, true, 0.0, false).makeEffectingSpell(true, false).makeTickingSpell(this::onTick, 5, false, 20, false).addNegativeEffect(PotionEffectType.SLOWNESS, 1, 60);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Location castLocation = castInformation.getCastLocation().clone();
        double range = this.getRange(castInformation);
        int i = 0;
        while ((double)i < range) {
            double xOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1.0);
            double zOffset = ThreadLocalRandom.current().nextDouble(-range, range + 1.0);
            double directionalXOffset = ThreadLocalRandom.current().nextDouble(-0.5, 0.6);
            double directionalZOffset = ThreadLocalRandom.current().nextDouble(-0.5, 0.6);
            Location spawnLocation = new Location(castLocation.getWorld(), castLocation.getX() + xOffset, castLocation.getY(), castLocation.getZ() + zOffset);
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB((int)90, (int)100, (int)105), 2.0f);
            castLocation.getWorld().spawnParticle(Particle.DUST, spawnLocation, 1, directionalXOffset, 2.0, directionalZOffset, (Object)dustOptions);
            ++i;
        }
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2.0, range)) {
            if (!(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster()) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            this.applyNegativeEffects(livingEntity, castInformation);
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation));
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HISTORICAL, StoryType.HUMAN, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Creates a localized quake around the caster", "damaging and slowing."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "QUAKE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CRACKED_DEEPSLATE_BRICKS;
    }
}

