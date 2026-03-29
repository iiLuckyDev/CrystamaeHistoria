/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EndermansVeil
extends Spell {
    public EndermansVeil() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20.0, true, 20.0, false, 20, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer((UUID)castInformation.getCaster());
        if (caster != null) {
            Location teleportToLocation = this.getTeleportLocation(caster, this.getRange(castInformation));
            if (teleportToLocation != null) {
                caster.teleport(teleportToLocation);
                ParticleUtils.displayParticleEffect((Entity)caster, Particle.END_ROD, 1.0, 10);
            } else {
                caster.sendMessage(CrystamaeHistoria.getInstance().getConfig().getString("messages.spells.teleport_no_suitable_location"));
            }
        }
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private Location getTeleportLocation(Player player, double range) {
        Location location = player.getLocation().add(ThreadLocalRandom.current().nextDouble(-range, range), 0.0, ThreadLocalRandom.current().nextDouble(-range, range));
        if (location.getBlock().getType() == Material.AIR) {
            return location.add(0.0, 1.0, 0.0);
        }
        return this.tryIncreaseY(location, 1);
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private Location tryIncreaseY(Location location, int attemptNumber) {
        if (attemptNumber >= 10) {
            return null;
        }
        Location newLocation = location.clone().add(0.0, 1.0, 0.0);
        if (newLocation.getBlock().getType() == Material.AIR) {
            return location;
        }
        return this.tryIncreaseY(newLocation, attemptNumber + 1);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.HISTORICAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Teleports the caster to a random nearby", "location."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "ENDERMANS_VEIL";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.ENDER_PEARL;
    }
}

