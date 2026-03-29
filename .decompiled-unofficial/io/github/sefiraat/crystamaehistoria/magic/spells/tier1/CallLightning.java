/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.LightningStrike
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CallLightning
extends Spell {
    public CallLightning() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(50.0, true, 100.0, false, 5, true).makeDamagingSpell(2.0, true, 0.0, false).makeProjectileSpell(this::fireProjectiles, 2.0, false, 0.5, true).makeProjectileVsEntitySpell(this::projectileHit).addBeforeProjectileHitEntityEvent(this::beforeProjectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Block block;
        Player player = Bukkit.getPlayer((UUID)castInformation.getCaster());
        if (player != null && (block = player.getTargetBlockExact((int)this.getRange(castInformation))) != null) {
            Location location = block.getLocation();
            LightningStrike lightningStrike = location.getWorld().strikeLightning(location);
            this.registerLightningStrike(lightningStrike, castInformation);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), this.getDamage(castInformation), castInformation.getDamageLocation(), this.getKnockback(castInformation));
        }
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : this.getTargets(castInformation, this.getProjectileAoe(castInformation), true)) {
            Interaction interaction;
            Interaction interaction2 = interaction = livingEntity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
            if (!GeneralUtils.hasPermission(castInformation.getCasterAsPlayer(), livingEntity.getLocation(), interaction)) continue;
            livingEntity.setFireTicks(40);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.MECHANICAL, StoryType.HISTORICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Calls a lightning bolt down where you are looking"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "CALL_LIGHTNING";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.LIGHTNING_ROD;
    }
}

