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
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.LeechGoal;
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

public class LeechBomb
extends Spell {
    public LeechBomb() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 0.0, false, 25, true).makeProjectileSpell(this::fireProjectile, 0.0, false, 0.0, false).makeProjectileVsEntitySpell(this::eggHit).makeProjectileVsBlockSpell(this::eggHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0.0, 1.5, 0.0).add(location.getDirection().multiply(1));
        MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.EGG, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 1.5);
    }

    @ParametersAreNonnullByDefault
    public void eggHit(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getProjectileLocation();
        for (int i = 0; i < castInformation.getStaveLevel() * 2; ++i) {
            Location spawnLocation = location.clone().add(ThreadLocalRandom.current().nextDouble(-1.0, 1.0), 0.0, ThreadLocalRandom.current().nextDouble(-1.0, 1.0));
            SpellUtils.summonTemporaryMob(EntityType.SILVERFISH, caster, spawnLocation, new LeechGoal(caster), 60 * castInformation.getStaveLevel(), this::onTick);
        }
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicSummon magicSummon) {
        Mob mob = magicSummon.getMob();
        Player player = magicSummon.getPlayer();
        Block block = player.getLocation().subtract(0.0, 0.5, 0.0).getBlock();
        if (!player.getWorld().equals((Object)mob.getWorld()) || mob.getLocation().distance(player.getLocation()) > 50.0 && block.getType().isSolid()) {
            mob.teleport(player.getLocation());
        }
        ParticleUtils.displayParticleEffect((Entity)magicSummon.getMob(), Particle.SPORE_BLOSSOM_AIR, 1.0, 1);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HISTORICAL, StoryType.ANIMAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Throws an egg filled with viscous", "leeches that will attack your enemies."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "LEECH_BOMB";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CARVED_PUMPKIN;
    }
}

