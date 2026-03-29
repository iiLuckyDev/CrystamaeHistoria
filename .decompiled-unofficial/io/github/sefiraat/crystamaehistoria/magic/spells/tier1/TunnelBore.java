/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Endermite
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.event.entity.CreatureSpawnEvent$SpawnReason
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.runnables.spells.TunnelBoreRunnable;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class TunnelBore
extends Spell {
    public TunnelBore() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(50.0, true, 1.0, true, 30, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        Vector direction = location.getDirection().clone();
        int range = (int)this.getRange(castInformation);
        direction.setY(0);
        Location spawnLocation = location.clone().add(0.0, (double)range, 0.0);
        Endermite bore = (Endermite)spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.ENDERMITE, CreatureSpawnEvent.SpawnReason.COMMAND, entity -> {
            Endermite mite = (Endermite)entity;
            mite.setGravity(false);
            mite.setInvulnerable(true);
            mite.setInvisible(true);
            mite.setVelocity(location.getDirection().multiply(2));
        });
        TunnelBoreRunnable runnable = new TunnelBoreRunnable((LivingEntity)bore, range, caster, range * 20);
        runnable.runTaskTimer((Plugin)CrystamaeHistoria.getInstance(), 0L, 1L);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.ALCHEMICAL, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Uses powerful magics to bore a tunnel", "in the direction you're facing.", "Does not drop items."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "TUNNEL_BORE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.GOAT_SPAWN_EGG;
    }
}

