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
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class BloodMagics
extends Spell {
    public BloodMagics() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100.0, true, 7.0, false, 20, false).makeDamagingSpell(2.0, true, 0.5, false).makeTickingSpell(this::cast, 3, false, 20, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        double size = this.getRange(castInformation);
        Location location = castInformation.getCastLocation();
        this.castBlood(castInformation, location, size, 1);
    }

    @ParametersAreNonnullByDefault
    private void castBlood(CastInformation castInformation, Location location, double size, int iteration) {
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB((int)190, (int)55, (int)80), 1.0f);
        Collection entities = location.getWorld().getNearbyEntities(location, size, size, size);
        for (Entity entity : entities) {
            if (!(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster()) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation));
            ParticleUtils.displayParticleEffect((Entity)livingEntity, 2.0, 10, dustOptions);
            if (iteration > 5 || !(livingEntity.getHealth() <= 0.0)) continue;
            GeneralUtils.pushEntity(castInformation.getCaster(), castInformation.getCastLocation(), entity, 5.0);
            ParticleUtils.displayParticleEffect((Entity)livingEntity, 4.0, 20, dustOptions);
            entity.remove();
            this.castBlood(castInformation, livingEntity.getLocation(), 2.0, iteration + 1);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.HISTORICAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Damages nearby entities. Dying entities will", "cause further damage."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "BLOOD_MAGICS";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.RED_DYE;
    }
}

