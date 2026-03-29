/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.ThrownPotion
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.PotionMeta
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.potion.PotionType
 *  org.bukkit.projectiles.ProjectileSource
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.projectiles.ProjectileSource;

public class AntiPrism
extends Spell {
    public AntiPrism() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10.0, true, 0.0, false, 50, true).makeProjectileSpell(this::cast, 3.0, false, 0.0, false).makeProjectileVsEntitySpell(this::projectileHit).makeProjectileVsBlockSpell(this::projectileHit).makeEffectingSpell(true, true).addNegativeEffect(PotionEffectType.BAD_OMEN, 1, 30).addNegativeEffect(PotionEffectType.BLINDNESS, 1, 30).addNegativeEffect(PotionEffectType.NAUSEA, 1, 30).addNegativeEffect(PotionEffectType.INSTANT_DAMAGE, 1, 30).addNegativeEffect(PotionEffectType.HUNGER, 1, 30).addNegativeEffect(PotionEffectType.POISON, 1, 30).addNegativeEffect(PotionEffectType.SLOWNESS, 1, 30).addNegativeEffect(PotionEffectType.MINING_FATIGUE, 1, 30).addNegativeEffect(PotionEffectType.UNLUCK, 1, 30).addNegativeEffect(PotionEffectType.WEAKNESS, 1, 30).addNegativeEffect(PotionEffectType.WITHER, 1, 30);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0.0, 1.5, 0.0).add(location.getDirection().multiply(2));
        ThrownPotion tp = (ThrownPotion)location.getWorld().spawn(aimLocation, ThrownPotion.class, thrownPotion -> {
            ItemStack splash = new ItemStack(Material.SPLASH_POTION);
            PotionMeta meta = (PotionMeta)splash.getItemMeta();
            meta.setBasePotionType(PotionType.WATER);
            splash.setItemMeta((ItemMeta)meta);
            thrownPotion.setItem(splash);
            thrownPotion.setShooter((ProjectileSource)castInformation.getCasterAsPlayer());
            thrownPotion.setVelocity(location.getDirection().multiply(0.5));
        });
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        double range = this.getProjectileAoe(castInformation);
        for (double height = 0.0; height <= Math.PI; height += 0.3141592653589793) {
            double r = range * Math.sin(height);
            double y = range * Math.cos(height);
            for (double a = 0.0; a < Math.PI * 2; a += 0.3141592653589793) {
                double x = Math.cos(a) * r;
                double z = Math.sin(a) * r;
                Location point = castInformation.getProjectileLocation().clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, Particle.SOUL, 0.1, 1);
            }
        }
        Player player = castInformation.getCasterAsPlayer();
        for (LivingEntity entity : this.getTargets(castInformation, range, true)) {
            Interaction interaction;
            Interaction interaction2 = interaction = entity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
            if (!GeneralUtils.hasPermission(player, entity.getLocation(), interaction)) continue;
            if (PersistentDataAPI.getBoolean((PersistentDataHolder)entity, (NamespacedKey)Keys.newKey("PRISM"))) {
                entity.damage(200.0);
            }
            PersistentDataAPI.setBoolean((PersistentDataHolder)entity, (NamespacedKey)Keys.newKey("ANTIPRISM"), (boolean)true);
            this.applyNegativeEffects(entity, castInformation);
            ParticleUtils.displayParticleEffect((Entity)entity, Particle.CRIMSON_SPORE, range, 20);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.HISTORICAL, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Applies a myriad of negative effects to", "enemies hit."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "ANTI_PRISM";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.POTION;
    }
}

