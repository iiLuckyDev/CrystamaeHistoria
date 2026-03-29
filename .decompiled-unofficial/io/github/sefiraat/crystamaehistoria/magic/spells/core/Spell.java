/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair
 *  lombok.Generated
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LightningStrike
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCore;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTickRunnable;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class Spell {
    private SpellCore spellCore;
    private boolean enabled;

    @Nonnull
    public abstract RecipeSpell getRecipe();

    @Nonnull
    public SlimefunItemStack getThemedStack() {
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        ArrayList<Object> finalLore = new ArrayList<Object>();
        for (String s : this.getLore()) {
            finalLore.add(String.valueOf(passiveColor) + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, "Spell"));
        SlimefunItemStack stack = new SlimefunItemStack(this.getId(), this.getMaterial(), ThemeType.applyThemeToString(ThemeType.SPELL, TextUtils.toTitleCase(this.getId())), finalLore.toArray(new String[finalLore.size() - 1]));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ADDITIONAL_TOOLTIP});
        stack.setItemMeta(itemMeta);
        return stack;
    }

    @Nonnull
    public abstract String[] getLore();

    @Nonnull
    public abstract String getId();

    @Nonnull
    public abstract Material getMaterial();

    @ParametersAreNonnullByDefault
    public void castSpell(CastInformation castInformation) {
        if (this.spellCore.isInstantCast()) {
            this.spellCore.getInstantCastEvent().accept(castInformation);
        }
        if (this.spellCore.isProjectileSpell()) {
            this.spellCore.getFireProjectileEvent().accept(castInformation);
            if (this.spellCore.isProjectileVsEntitySpell()) {
                castInformation.setBeforeProjectileHitEvent(this.spellCore.getBeforeProjectileHitEvent());
                castInformation.setProjectileHitEvent(this.spellCore.getProjectileHitEvent());
                castInformation.setAfterProjectileHitEvent(this.spellCore.getAfterProjectileHitEvent());
            }
            if (this.spellCore.isProjectileVsBlockSpell()) {
                castInformation.setProjectileHitBlockEvent(this.spellCore.getProjectileHitBlockEvent());
            }
        }
        if (this.spellCore.isTickingSpell()) {
            this.registerTicker(castInformation, this.spellCore.getTickInterval(), this.spellCore.getNumberOfTicks());
        }
    }

    @ParametersAreNonnullByDefault
    protected void registerTicker(CastInformation castInformation, long period, int tickAmount) {
        tickAmount = this.spellCore.isNumberOfTicksMultiplied() ? tickAmount * castInformation.getStaveLevel() : tickAmount;
        period = this.spellCore.isTickIntervalMultiplied() ? period * (long)castInformation.getStaveLevel() : period;
        castInformation.setTickEvent(this.spellCore.getTickEvent());
        castInformation.setAfterTicksEvent(this.spellCore.getAfterAllTicksEvent());
        SpellTickRunnable ticker = new SpellTickRunnable(castInformation, tickAmount);
        CrystamaeHistoria.getSpellMemory().getTickingCastables().put(ticker, tickAmount);
        ticker.runTaskTimer((Plugin)CrystamaeHistoria.getInstance(), 0L, period);
    }

    @ParametersAreNonnullByDefault
    public double getCooldownSeconds(CastInformation castInformation) {
        return this.spellCore.isCooldownDivided() ? this.spellCore.getCooldownSeconds() / (double)castInformation.getStaveLevel() : this.spellCore.getCooldownSeconds();
    }

    @ParametersAreNonnullByDefault
    public double getRange(CastInformation castInformation) {
        return this.spellCore.isRangeMultiplied() ? this.spellCore.getRange() * (double)castInformation.getStaveLevel() : this.spellCore.getRange();
    }

    @ParametersAreNonnullByDefault
    public int getCrystaCost(CastInformation castInformation) {
        return this.spellCore.isCrystaMultiplied() ? this.spellCore.getCrystaCost() * castInformation.getStaveLevel() : this.spellCore.getCrystaCost();
    }

    @ParametersAreNonnullByDefault
    public double getDamage(CastInformation castInformation) {
        return this.spellCore.isDamageMultiplied() ? this.spellCore.getDamageAmount() * (double)castInformation.getStaveLevel() : this.spellCore.getDamageAmount();
    }

    @ParametersAreNonnullByDefault
    public double getHealAmount(CastInformation castInformation) {
        return this.spellCore.isHealMultiplied() ? this.spellCore.getHealAmount() * (double)castInformation.getStaveLevel() : this.spellCore.getHealAmount();
    }

    @ParametersAreNonnullByDefault
    public double getKnockback(CastInformation castInformation) {
        return this.spellCore.isKnockbackMultiplied() ? this.spellCore.getKnockbackAmount() * (double)castInformation.getStaveLevel() : this.spellCore.getKnockbackAmount();
    }

    @ParametersAreNonnullByDefault
    public double getProjectileKnockback(CastInformation castInformation) {
        return this.spellCore.isProjectileKnockbackMultiplied() ? this.spellCore.getProjectileKnockbackAmount() * (double)castInformation.getStaveLevel() : this.spellCore.getProjectileKnockbackAmount();
    }

    @ParametersAreNonnullByDefault
    public double getProjectileAoe(CastInformation castInformation) {
        return this.spellCore.isProjectileAoeMultiplied() ? this.spellCore.getProjectileAoeRange() * (double)castInformation.getStaveLevel() : this.spellCore.getProjectileAoeRange();
    }

    @ParametersAreNonnullByDefault
    protected void setLastDamageToCaster(LivingEntity damagedEntity, CastInformation castInformation) {
        this.setLastDamageToCaster(damagedEntity, castInformation.getCaster());
    }

    @ParametersAreNonnullByDefault
    protected void setLastDamageToCaster(@Nonnull LivingEntity damagedEntity, @Nonnull UUID casterUUID) {
        Player player = Bukkit.getPlayer((UUID)casterUUID);
        if (player != null) {
            EntityDamageByEntityEvent e = new EntityDamageByEntityEvent((Entity)player, (Entity)damagedEntity, EntityDamageEvent.DamageCause.MAGIC, 0.0);
            damagedEntity.setLastDamageCause((EntityDamageEvent)e);
        }
    }

    @ParametersAreNonnullByDefault
    protected void registerLightningStrike(LightningStrike lightningStrike, CastInformation castInformation) {
        castInformation.setBeforeProjectileHitEvent(this.spellCore.getBeforeProjectileHitEvent());
        castInformation.setProjectileHitEvent(this.spellCore.getProjectileHitEvent());
        castInformation.setAfterProjectileHitEvent(this.spellCore.getAfterProjectileHitEvent());
        Long expiry = System.currentTimeMillis() + 1000L;
        CrystamaeHistoria.getSpellMemory().getStrikeMap().put(lightningStrike.getUniqueId(), (Pair<CastInformation, Long>)new Pair((Object)castInformation, (Object)expiry));
    }

    @ParametersAreNonnullByDefault
    protected void applyPositiveEffects(LivingEntity livingEntity, CastInformation castInformation) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : this.spellCore.getPositiveEffectPairMap().entrySet()) {
            int duration = (Integer)entry.getValue().getSecondValue();
            int amplification = (Integer)entry.getValue().getFirstValue();
            duration = this.spellCore.isEffectDurationMultiplied() ? duration * castInformation.getStaveLevel() : duration;
            amplification = this.spellCore.isAmplificationMultiplied() ? amplification * castInformation.getStaveLevel() : amplification;
            PotionEffect potionEffect = new PotionEffect(entry.getKey(), duration, amplification - 1);
            livingEntity.addPotionEffect(potionEffect);
        }
    }

    @ParametersAreNonnullByDefault
    protected void applyNegativeEffects(LivingEntity livingEntity, CastInformation castInformation) {
        for (Map.Entry<PotionEffectType, Pair<Integer, Integer>> entry : this.spellCore.getNegativeEffectPairMap().entrySet()) {
            int duration = (Integer)entry.getValue().getSecondValue();
            int amplification = (Integer)entry.getValue().getFirstValue();
            duration = this.spellCore.isEffectDurationMultiplied() ? duration * castInformation.getStaveLevel() : duration;
            amplification = this.spellCore.isAmplificationMultiplied() ? amplification * castInformation.getStaveLevel() : amplification;
            PotionEffect potionEffect = new PotionEffect(entry.getKey(), duration, amplification - 1);
            livingEntity.addPotionEffect(potionEffect);
        }
    }

    @ParametersAreNonnullByDefault
    protected Set<LivingEntity> getTargets(CastInformation castInformation, double range) {
        return this.getTargets(castInformation, range, false);
    }

    @ParametersAreNonnullByDefault
    protected Set<LivingEntity> getTargets(CastInformation castInformation, double range, boolean includeMain) {
        HashSet<LivingEntity> livingEntities = new HashSet<LivingEntity>();
        Location location = castInformation.getDamageLocation();
        if (range > 0.0) {
            for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
                if (!(entity instanceof LivingEntity) || entity.getUniqueId() == castInformation.getCaster() || entity == castInformation.getMainTarget() && !includeMain) continue;
                livingEntities.add((LivingEntity)entity);
            }
        }
        return livingEntities;
    }

    @Generated
    public SpellCore getSpellCore() {
        return this.spellCore;
    }

    @Generated
    public void setSpellCore(SpellCore spellCore) {
        this.spellCore = spellCore;
    }

    @Generated
    public boolean isEnabled() {
        return this.enabled;
    }

    @Generated
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

