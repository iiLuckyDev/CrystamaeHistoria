/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Gravity
extends Spell {
    public Gravity() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(300.0, true, 0.0, false, 25, false).makeTickingSpell(this::tick, 1, false, 20, false).addAfterTicksEvent(this::afterAllTicks);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void tick(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        player.setVelocity(player.getLocation().getDirection().add(new Vector(0, 5, 0)));
        ParticleUtils.displayParticleEffect((Entity)player, Particle.GLOW_SQUID_INK, 2.0, 10);
    }

    @ParametersAreNonnullByDefault
    public void afterAllTicks(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        player.setAllowFlight(true);
        player.setFlying(true);
        long expiry = System.currentTimeMillis() + (long)castInformation.getStaveLevel() * 30L * 1000L;
        CrystamaeHistoria.getSpellMemory().getPlayersWithFlight().put(player.getUniqueId(), expiry);
        ParticleUtils.displayParticleEffect((Entity)player, Particle.FALLING_OBSIDIAN_TEAR, 2.0, 20);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ALCHEMICAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Allows you to use a temporary gravity", "pocket to fly."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "GRAVITY";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.FIREWORK_STAR;
    }
}

