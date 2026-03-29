/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Bat
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
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
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.FlyingBatGoal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class PhantomsFlight
extends Spell {
    public PhantomsFlight() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 0.0, false, 30, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        Location spawnLocation = location.clone().add(ThreadLocalRandom.current().nextDouble(-3.0, 3.0), 0.0, ThreadLocalRandom.current().nextDouble(-3.0, 3.0));
        MagicSummon magicSummon = SpellUtils.summonTemporaryMob(EntityType.BAT, caster, spawnLocation, new FlyingBatGoal(caster), castInformation.getStaveLevel() * 300, this::onTick);
        Bat bat = (Bat)magicSummon.getMob();
        bat.setInvisible(true);
        bat.setInvulnerable(true);
        bat.addPassenger((Entity)castInformation.getCasterAsPlayer());
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicSummon magicSummon) {
        ParticleUtils.displayParticleEffect((Entity)magicSummon.getMob(), Particle.SPORE_BLOSSOM_AIR, 1.0, 2);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HUMAN, StoryType.ANIMAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a dragon to ride.", "Getting off the dragon will make", "it fly away."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "PHANTOMS_FLIGHT";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.DRAGON_EGG;
    }
}

