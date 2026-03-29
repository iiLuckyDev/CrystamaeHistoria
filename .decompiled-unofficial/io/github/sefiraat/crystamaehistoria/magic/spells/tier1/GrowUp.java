/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Ageable
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Phantom
 *  org.bukkit.entity.Slime
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
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;

public class GrowUp
extends Spell {
    public GrowUp() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(360.0, false, 5.0, true, 50, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location casterLocation = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        for (Entity entity : casterLocation.getWorld().getNearbyEntities(casterLocation, range, range, range)) {
            if (entity instanceof Ageable) {
                Ageable ageable = (Ageable)entity;
                if (ageable.isAdult()) continue;
                ageable.setAdult();
                ParticleUtils.displayParticleEffect(entity, Particle.SCRAPE, 1.0, 3);
                continue;
            }
            if (entity instanceof Slime) {
                Slime slime = (Slime)entity;
                slime.setSize(slime.getSize() + 1);
                continue;
            }
            if (!(entity instanceof Phantom)) continue;
            Phantom phantom = (Phantom)entity;
            phantom.setSize(phantom.getSize() + 1);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ANIMAL, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Makes things grow up, and not just babies!"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "GROW_UP";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.SLIME_BLOCK;
    }
}

