/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Gyroscopic
extends Spell {
    public Gyroscopic() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40.0, true, 15.0, false, 25, true).makeTickingSpell(this::cast, 20, true, 5, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCasterAsPlayer().getLocation().clone().add(0.0, 1.0, 0.0);
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
                ParticleUtils.displayParticleEffect(point, Particle.GLOW, 0.1, 1);
            }
        }
        for (Entity entity : location.getWorld().getNearbyEntities(location, effectRange, effectRange, effectRange)) {
            if (!(entity instanceof LivingEntity) || !GeneralUtils.hasPermission(castInformation.getCaster(), entity.getLocation(), Interaction.INTERACT_ENTITY) || entity.getUniqueId() == castInformation.getCaster()) continue;
            Location newLocation = entity.getLocation().clone();
            newLocation.setYaw(entity.getLocation().getYaw() + 10.0f);
            entity.teleport(newLocation);
            ParticleUtils.displayParticleEffect(entity, Particle.ENTITY_EFFECT, 1.0, 1);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ANIMAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"You spin me right round baby..."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "GYROSCOPIC";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.MUSIC_DISC_CAT;
    }
}

