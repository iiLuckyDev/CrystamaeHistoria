/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Mob
 *  org.bukkit.entity.Player
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
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.GolemGoal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class SummonGolem
extends Spell {
    public SummonGolem() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 0.0, false, 15, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        Location spawnLocation = location.clone().add(ThreadLocalRandom.current().nextDouble(-3.0, 3.0), 0.0, ThreadLocalRandom.current().nextDouble(-3.0, 3.0));
        SpellUtils.summonTemporaryMob(EntityType.IRON_GOLEM, caster, spawnLocation, new GolemGoal(caster), 200 + 100 * castInformation.getStaveLevel(), this::onTick);
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicSummon magicSummon) {
        Mob mob = magicSummon.getMob();
        Player player = magicSummon.getPlayer();
        Block block = player.getLocation().subtract(0.0, 0.5, 0.0).getBlock();
        if (!player.getWorld().equals((Object)mob.getWorld()) || mob.getLocation().distance(player.getLocation()) > 50.0 && block.getType().isSolid()) {
            mob.teleport(player.getLocation());
        }
        ParticleUtils.displayParticleEffect((Entity)magicSummon.getMob(), Particle.ITEM_SLIME, 1.0, 3);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HUMAN, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a golem that will follow you", "and protect you.", "Golems will follow you nearly anywhere."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "SUMMON_GOLEM";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CARVED_PUMPKIN;
    }
}

