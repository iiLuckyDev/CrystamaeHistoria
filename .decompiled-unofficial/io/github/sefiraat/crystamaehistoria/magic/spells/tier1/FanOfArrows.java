/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
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
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class FanOfArrows
extends Spell {
    public FanOfArrows() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10.0, true, 20.0, true, 10, false).makeProjectileSpell(this::fireProjectiles, 0.0, false, 0.0, false).makeProjectileVsEntitySpell(this::projectileHit).makeDamagingSpell(1.0, true, 0.0, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        double sizeEnd = this.getRange(castInformation);
        int sizeCast = 3;
        int stepSize = 5;
        Location middle = castInformation.getCastLocation().clone().add(0.0, 1.0, 0.0);
        for (double i = 0.0; i < 360.0; i += (double)stepSize) {
            double angle = i * Math.PI / 180.0;
            int sx = (int)((double)sizeCast * Math.cos(angle));
            int sz = (int)((double)sizeCast * Math.sin(angle));
            int dx = (int)(sizeEnd * Math.cos(angle));
            int dz = (int)(sizeEnd * Math.sin(angle));
            Location spawn = middle.clone().add((double)sx, 0.0, (double)sz);
            Location destination = middle.clone().add((double)dx, 5.0, (double)dz);
            if (!spawn.getBlock().isEmpty()) continue;
            MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.ARROW, spawn);
            magicProjectile.setVelocity(destination, 1.0);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        GeneralUtils.damageEntity(castInformation.getMainTarget(), castInformation.getCaster(), this.getDamage(castInformation));
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HISTORICAL, StoryType.HUMAN);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a fan of arrows around you to slice", "through your opponents"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "FAN_OF_ARROWS";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.SPECTRAL_ARROW;
    }
}

