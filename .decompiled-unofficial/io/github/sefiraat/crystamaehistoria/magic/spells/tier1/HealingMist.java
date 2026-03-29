/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
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
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class HealingMist
extends Spell {
    public HealingMist() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(100.0, true, 10.0, false, 10, true).makeInstantSpell(this::cast).makeEffectingSpell(true, false).addPositiveEffect(PotionEffectType.REGENERATION, 1, 10);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range, Player.class::isInstance)) {
            Player player = (Player)entity;
            this.applyPositiveEffects((LivingEntity)player, castInformation);
            ParticleUtils.displayParticleEffect((Entity)player, Particle.HEART, 2.0, 5);
            ParticleUtils.displayParticleEffect((Entity)player, Particle.CLOUD, 2.0, 2);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.CELESTIAL, StoryType.VOID, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Calls fourth a mist of healing energy", "around the caster. Gives regen to all", "players affected."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "HEALING_MIST";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.GOLDEN_APPLE;
    }
}

