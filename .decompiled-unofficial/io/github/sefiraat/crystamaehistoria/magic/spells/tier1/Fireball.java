/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Fireball
extends Spell {
    public Fireball() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(0.5, false, 0.0, false, 1, true).makeDamagingSpell(1.0, true, 0.5, false).makeProjectileSpell(this::fireProjectile, 1.0, false, 1.0, false).makeProjectileVsEntitySpell(this::projectileHit).addBeforeProjectileHitEntityEvent(this::beforeProjectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0.0, 1.5, 0.0).add(location.getDirection().multiply(1));
        MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SMALL_FIREBALL, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 1.5);
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation), castInformation.getProjectileLocation(), this.getKnockback(castInformation));
        }
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            Interaction interaction;
            Interaction interaction2 = interaction = livingEntity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
            if (!GeneralUtils.hasPermission(castInformation.getCaster(), livingEntity.getLocation(), interaction)) continue;
            livingEntity.setFireTicks(80);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.HUMAN, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Shoots a fireball in the direction you are", "looking at."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "FIREBALL";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.FIRE_CHARGE;
    }
}

