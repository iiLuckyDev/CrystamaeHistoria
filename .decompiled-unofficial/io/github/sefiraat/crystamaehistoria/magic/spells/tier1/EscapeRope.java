/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Player
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
import org.bukkit.entity.Player;

public class EscapeRope
extends Spell {
    public EscapeRope() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(3600.0, true, 0.0, false, 30, false).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        Location location = player.getLocation();
        Location teleportTo = location.getWorld().getHighestBlockAt(location).getLocation().add(0.0, 2.0, 0.0);
        player.teleportAsync(teleportTo);
        ParticleUtils.displayParticleEffect(location, Particle.NOTE, 1.0, 5);
        ParticleUtils.displayParticleEffect(teleportTo, Particle.NOTE, 1.0, 5);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HISTORICAL, StoryType.CELESTIAL, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Teleports you to the highest possible", "point if possible."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "ESCAPE_ROPE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.LEAD;
    }
}

