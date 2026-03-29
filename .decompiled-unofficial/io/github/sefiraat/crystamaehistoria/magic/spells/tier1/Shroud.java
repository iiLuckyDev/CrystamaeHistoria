/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Shroud
extends Spell {
    public Shroud() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40.0, true, 10.0, true, 5, true).makeDamagingSpell(0.0, false, 0.0, false).makeInstantSpell(this::cast).makeEffectingSpell(true, false).addNegativeEffect(PotionEffectType.BLINDNESS, 1, 20).addNegativeEffect(PotionEffectType.WITHER, 1, 10);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
            Interaction interaction;
            if (!(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster()) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            Interaction interaction2 = interaction = livingEntity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
            if (!GeneralUtils.hasPermission(castInformation.getCaster(), entity.getLocation(), interaction)) continue;
            this.applyNegativeEffects(livingEntity, castInformation);
            ParticleUtils.displayParticleEffect((Entity)livingEntity, Particle.ITEM_SLIME, 2.0, 2);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.HUMAN, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a dark miasma around the player", "causing light damage and blinding those", "affected."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "SHROUD";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.BLACK_CANDLE;
    }
}

