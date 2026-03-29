/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Player
 *  org.bukkit.event.player.PlayerTeleportEvent$TeleportCause
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Hearthstone
extends Spell {
    public Hearthstone() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(300.0, true, 0.0, false, 25, false).makeTickingSpell(this::tick, 10, false, 10, false).addAfterTicksEvent(this::afterAllTicks);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void tick(CastInformation castInformation) {
        int sizeCast = 2;
        int stepSize = 3;
        Location middle = castInformation.getCasterAsPlayer().getLocation().add(0.0, 1.0, 0.0);
        for (double i = 0.0; i < 360.0; i += (double)stepSize) {
            double angle = i * Math.PI / 180.0;
            int sx = (int)((double)sizeCast * Math.cos(angle));
            int sz = (int)((double)sizeCast * Math.sin(angle));
            Location spawn = middle.clone().add((double)sx, 0.0, (double)sz);
            ParticleUtils.displayParticleEffect(spawn, Particle.DRIPPING_HONEY, 0.1, 1);
        }
    }

    @ParametersAreNonnullByDefault
    public void afterAllTicks(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer((UUID)castInformation.getCaster());
        Location location = caster.getBedSpawnLocation();
        if (location == null) {
            Location casterLocation = caster.getLocation();
            ParticleUtils.displayParticleEffect(casterLocation.add(casterLocation.getDirection()), Particle.ANGRY_VILLAGER, 1.0, 10);
        } else {
            caster.teleportAsync(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HUMAN, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Sends you back to your last bed if", "possible. Takes time to cast, movement", "stops the cast."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "HEARTHSTONE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.RED_BED;
    }
}

