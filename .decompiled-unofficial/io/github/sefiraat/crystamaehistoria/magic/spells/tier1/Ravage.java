/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.RidableGroundGoal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class Ravage
extends Spell {
    public Ravage() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 0.0, false, 25, true).makeInstantSpell(this::cast).makeEffectingSpell(true, false).addPositiveEffect(PotionEffectType.RESISTANCE, 1, 300).addPositiveEffect(PotionEffectType.STRENGTH, 1, 300).addPositiveEffect(PotionEffectType.ABSORPTION, 1, 300);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        Location spawnLocation = location.clone().add(ThreadLocalRandom.current().nextDouble(-3.0, 3.0), 0.0, ThreadLocalRandom.current().nextDouble(-3.0, 3.0));
        MagicSummon magicSummon = SpellUtils.summonTemporaryMob(EntityType.RAVAGER, caster, spawnLocation, new RidableGroundGoal(caster), 300, this::onTick);
        this.applyPositiveEffects((LivingEntity)magicSummon.getMob(), castInformation);
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicSummon magicSummon) {
        ParticleUtils.displayParticleEffect((Entity)magicSummon.getMob(), Particle.ANGRY_VILLAGER, 1.0, 2);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ALCHEMICAL, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a tame ravager to your side.", "This spells effects and multipliers", "are applied to the ravager, not the", "caster."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "RAVAGE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.RAVAGER_SPAWN_EGG;
    }
}

