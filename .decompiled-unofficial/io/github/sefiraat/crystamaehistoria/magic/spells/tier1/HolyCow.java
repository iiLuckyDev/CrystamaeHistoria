/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
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
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.HolyCowGoal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class HolyCow
extends Spell {
    public HolyCow() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 0.0, false, 25, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        for (int i = 0; i < castInformation.getStaveLevel(); ++i) {
            Location spawnLocation = location.clone().add(ThreadLocalRandom.current().nextDouble(-3.0, 3.0), 0.0, ThreadLocalRandom.current().nextDouble(-3.0, 3.0));
            SpellUtils.summonTemporaryMob(EntityType.COW, caster, spawnLocation, new HolyCowGoal(caster), 300, this::onTick);
        }
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicSummon magicSummon) {
        ParticleUtils.displayParticleEffect((Entity)magicSummon.getMob(), Particle.GLOW, 1.0, 4);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ANIMAL, StoryType.CELESTIAL, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons 1-5 cows to who will protect you", "with their lives."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "HOLY_COW";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.COW_SPAWN_EGG;
    }
}

