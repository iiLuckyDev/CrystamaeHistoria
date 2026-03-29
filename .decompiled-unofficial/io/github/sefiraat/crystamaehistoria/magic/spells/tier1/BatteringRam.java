/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Mob
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.BatteringRamGoal;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.util.Vector;

public class BatteringRam
extends Spell {
    public BatteringRam() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 3.0, true, 25, true).makeInstantSpell(this::cast).makeEffectingSpell(true, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        Vector direction = location.getDirection().clone();
        int range = (int)this.getRange(castInformation);
        direction.setY(0);
        Location spawnLocation = location.clone().add(location.getDirection().clone().add(new Vector(0, 1, 0)));
        MagicSummon magicSummon = SpellUtils.summonTemporaryMob(EntityType.GOAT, caster, spawnLocation, new BatteringRamGoal(caster), range);
        Mob mob = magicSummon.getMob();
        mob.setGravity(false);
        mob.setVelocity(location.getDirection().multiply(2));
        mob.setInvulnerable(true);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.ALCHEMICAL, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a battering ram to decimate", "all in your way."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "BATTERING_RAM";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.GOAT_SPAWN_EGG;
    }
}

