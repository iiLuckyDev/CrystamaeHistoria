/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Zombie
 *  org.bukkit.entity.ZombieVillager
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieVillager;

public class CurificationRitual
extends Spell {
    public CurificationRitual() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, false, 10.0, true, 25, true).makeInstantSpell(this::cast).makeDamagingSpell(2.0, true, 0.0, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        double range = this.getRange(castInformation);
        Player caster = castInformation.getCasterAsPlayer();
        Collection zombies = caster.getWorld().getNearbyEntities(caster.getLocation(), range, range, range);
        for (Entity entity : zombies) {
            if (entity instanceof ZombieVillager) {
                ParticleUtils.displayParticleEffect(entity, Particle.WAX_OFF, 2.0, 10);
                ZombieVillager zombieVillager = (ZombieVillager)entity;
                zombieVillager.setConversionTime(100);
                zombieVillager.setConversionPlayer((OfflinePlayer)caster);
                continue;
            }
            if (!(entity instanceof Zombie)) continue;
            Zombie zombie = (Zombie)entity;
            GeneralUtils.damageEntity((LivingEntity)zombie, castInformation.getCaster(), this.getDamage(castInformation));
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HUMAN, StoryType.ANIMAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Cures nearby zombie villagers while", "damaging other zombies in the process."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "CURIFICATION_RITUAL";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.GOLDEN_APPLE;
    }
}

