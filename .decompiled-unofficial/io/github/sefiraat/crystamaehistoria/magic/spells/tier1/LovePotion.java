/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Animals
 *  org.bukkit.entity.Breedable
 *  org.bukkit.entity.Entity
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
import org.bukkit.entity.Animals;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;

public class LovePotion
extends Spell {
    public LovePotion() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, true, 5.0, true, 25, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location casterLocation = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        for (Entity entity : casterLocation.getWorld().getNearbyEntities(casterLocation, range, range, range, Breedable.class::isInstance)) {
            Animals animals = (Animals)entity;
            if (!animals.isAdult() || !animals.canBreed()) continue;
            animals.setLoveModeTicks(120);
            ParticleUtils.displayParticleEffect(entity, Particle.HEART, 1.0, 5);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.ANIMAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"All nearby breedable entities get... friendly"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "LOVE_POTION";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.POTION;
    }
}

