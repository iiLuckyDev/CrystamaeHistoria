/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Push
extends Spell {
    public Push() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100.0, true, 30.0, false, 5, true).makeDamagingSpell(0.0, false, 0.2, true).makeTickingSpell(this::onTick, 5, false, 20, false).addAfterTicksEvent(this::afterAllTicks);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        this.push(castInformation, this.getKnockback(castInformation));
    }

    @ParametersAreNonnullByDefault
    public void afterAllTicks(CastInformation castInformation) {
        this.push(castInformation, this.getKnockback(castInformation) * 3.0);
    }

    @ParametersAreNonnullByDefault
    private void push(CastInformation castInformation, double amount) {
        Location castLocation = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, 2.0, range)) {
            if (!(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster()) continue;
            GeneralUtils.pushEntity(castInformation.getCaster(), castLocation, entity, amount);
            ParticleUtils.displayParticleEffect(entity, Particle.CRIT, 1.0, 10);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HUMAN, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"In a jam? This spell gives you some room", "to breathe."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "PUSH";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.PISTON;
    }
}

