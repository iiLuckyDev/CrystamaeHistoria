/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair
 *  lombok.Generated
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import java.util.Map;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.potion.PotionEffectType;

public class SpellCore {
    private final double cooldownSeconds;
    private final double range;
    private final int crystaCost;
    private final double damageAmount;
    private final double knockbackAmount;
    private final double healAmount;
    private final double projectileAoeRange;
    private final double projectileKnockbackAmount;
    private final int numberOfTicks;
    private final int tickInterval;
    private final boolean cooldownDivided;
    private final boolean rangeMultiplied;
    private final boolean crystaMultiplied;
    private final boolean damageMultiplied;
    private final boolean knockbackMultiplied;
    private final boolean healMultiplied;
    private final boolean projectileAoeMultiplied;
    private final boolean projectileKnockbackMultiplied;
    private final boolean numberOfTicksMultiplied;
    private final boolean tickIntervalMultiplied;
    private final boolean isInstantCast;
    private final Consumer<CastInformation> instantCastEvent;
    private final boolean isProjectileSpell;
    private final boolean isProjectileVsEntitySpell;
    private final boolean isProjectileVsBlockSpell;
    private final Consumer<CastInformation> fireProjectileEvent;
    private final Consumer<CastInformation> beforeProjectileHitEvent;
    private final Consumer<CastInformation> projectileHitEvent;
    private final Consumer<CastInformation> projectileHitBlockEvent;
    private final Consumer<CastInformation> afterProjectileHitEvent;
    private final boolean isTickingSpell;
    private final Consumer<CastInformation> tickEvent;
    private final Consumer<CastInformation> afterAllTicksEvent;
    private final boolean isDamagingSpell;
    private final boolean isHealingSpell;
    private final boolean isEffectingSpell;
    private final boolean amplificationMultiplied;
    private final boolean effectDurationMultiplied;
    private final Map<PotionEffectType, Pair<Integer, Integer>> positiveEffectPairMap;
    private final Map<PotionEffectType, Pair<Integer, Integer>> negativeEffectPairMap;

    @ParametersAreNonnullByDefault
    public SpellCore(SpellCoreBuilder spellCoreBuilder) {
        this.cooldownSeconds = spellCoreBuilder.getCooldownSeconds();
        this.range = spellCoreBuilder.getRange();
        this.crystaCost = spellCoreBuilder.getCrystaCost();
        this.damageAmount = spellCoreBuilder.getDamageAmount();
        this.knockbackAmount = spellCoreBuilder.getKnockbackAmount();
        this.healAmount = spellCoreBuilder.getHealAmount();
        this.projectileAoeRange = spellCoreBuilder.getProjectileAoeRange();
        this.projectileKnockbackAmount = spellCoreBuilder.getProjectileKnockbackAmount();
        this.numberOfTicks = spellCoreBuilder.getNumberOfTicks();
        this.tickInterval = spellCoreBuilder.getTickInterval();
        this.cooldownDivided = spellCoreBuilder.isCooldownDivided();
        this.rangeMultiplied = spellCoreBuilder.isRangeMultiplied();
        this.crystaMultiplied = spellCoreBuilder.isCrystaMultiplied();
        this.damageMultiplied = spellCoreBuilder.isDamageMultiplied();
        this.knockbackMultiplied = spellCoreBuilder.isKnockbackMultiplied();
        this.healMultiplied = spellCoreBuilder.isHealMultiplied();
        this.projectileAoeMultiplied = spellCoreBuilder.isProjectileAoeMultiplied();
        this.projectileKnockbackMultiplied = spellCoreBuilder.isProjectileKnockbackMultiplied();
        this.numberOfTicksMultiplied = spellCoreBuilder.isNumberOfTicksMultiplied();
        this.tickIntervalMultiplied = spellCoreBuilder.isTickIntervalMultiplied();
        this.isInstantCast = spellCoreBuilder.isInstantCast();
        this.instantCastEvent = spellCoreBuilder.getInstantCastEvent();
        this.isProjectileSpell = spellCoreBuilder.isProjectileSpell();
        this.isProjectileVsEntitySpell = spellCoreBuilder.isProjectileVsEntitySpell();
        this.isProjectileVsBlockSpell = spellCoreBuilder.isProjectileVsBlockSpell();
        this.fireProjectileEvent = spellCoreBuilder.getFireProjectileEvent();
        this.beforeProjectileHitEvent = spellCoreBuilder.getBeforeProjectileHitEvent();
        this.projectileHitEvent = spellCoreBuilder.getProjectileHitEvent();
        this.projectileHitBlockEvent = spellCoreBuilder.getProjectileHitBlockEvent();
        this.afterProjectileHitEvent = spellCoreBuilder.getAfterProjectileHitEvent();
        this.isTickingSpell = spellCoreBuilder.isTickingSpell();
        this.tickEvent = spellCoreBuilder.getTickEvent();
        this.afterAllTicksEvent = spellCoreBuilder.getAfterAllTicksEvent();
        this.isDamagingSpell = spellCoreBuilder.isDamagingSpell();
        this.isHealingSpell = spellCoreBuilder.isHealingSpell();
        this.isEffectingSpell = spellCoreBuilder.isEffectingSpell();
        this.amplificationMultiplied = spellCoreBuilder.isAmplificationMultiplied();
        this.effectDurationMultiplied = spellCoreBuilder.isEffectDurationMultiplied();
        this.positiveEffectPairMap = spellCoreBuilder.getPositiveEffectPairMap();
        this.negativeEffectPairMap = spellCoreBuilder.getNegativeEffectPairMap();
    }

    @Generated
    public double getCooldownSeconds() {
        return this.cooldownSeconds;
    }

    @Generated
    public double getRange() {
        return this.range;
    }

    @Generated
    public int getCrystaCost() {
        return this.crystaCost;
    }

    @Generated
    public double getDamageAmount() {
        return this.damageAmount;
    }

    @Generated
    public double getKnockbackAmount() {
        return this.knockbackAmount;
    }

    @Generated
    public double getHealAmount() {
        return this.healAmount;
    }

    @Generated
    public double getProjectileAoeRange() {
        return this.projectileAoeRange;
    }

    @Generated
    public double getProjectileKnockbackAmount() {
        return this.projectileKnockbackAmount;
    }

    @Generated
    public int getNumberOfTicks() {
        return this.numberOfTicks;
    }

    @Generated
    public int getTickInterval() {
        return this.tickInterval;
    }

    @Generated
    public boolean isCooldownDivided() {
        return this.cooldownDivided;
    }

    @Generated
    public boolean isRangeMultiplied() {
        return this.rangeMultiplied;
    }

    @Generated
    public boolean isCrystaMultiplied() {
        return this.crystaMultiplied;
    }

    @Generated
    public boolean isDamageMultiplied() {
        return this.damageMultiplied;
    }

    @Generated
    public boolean isKnockbackMultiplied() {
        return this.knockbackMultiplied;
    }

    @Generated
    public boolean isHealMultiplied() {
        return this.healMultiplied;
    }

    @Generated
    public boolean isProjectileAoeMultiplied() {
        return this.projectileAoeMultiplied;
    }

    @Generated
    public boolean isProjectileKnockbackMultiplied() {
        return this.projectileKnockbackMultiplied;
    }

    @Generated
    public boolean isNumberOfTicksMultiplied() {
        return this.numberOfTicksMultiplied;
    }

    @Generated
    public boolean isTickIntervalMultiplied() {
        return this.tickIntervalMultiplied;
    }

    @Generated
    public boolean isInstantCast() {
        return this.isInstantCast;
    }

    @Generated
    public Consumer<CastInformation> getInstantCastEvent() {
        return this.instantCastEvent;
    }

    @Generated
    public boolean isProjectileSpell() {
        return this.isProjectileSpell;
    }

    @Generated
    public boolean isProjectileVsEntitySpell() {
        return this.isProjectileVsEntitySpell;
    }

    @Generated
    public boolean isProjectileVsBlockSpell() {
        return this.isProjectileVsBlockSpell;
    }

    @Generated
    public Consumer<CastInformation> getFireProjectileEvent() {
        return this.fireProjectileEvent;
    }

    @Generated
    public Consumer<CastInformation> getBeforeProjectileHitEvent() {
        return this.beforeProjectileHitEvent;
    }

    @Generated
    public Consumer<CastInformation> getProjectileHitEvent() {
        return this.projectileHitEvent;
    }

    @Generated
    public Consumer<CastInformation> getProjectileHitBlockEvent() {
        return this.projectileHitBlockEvent;
    }

    @Generated
    public Consumer<CastInformation> getAfterProjectileHitEvent() {
        return this.afterProjectileHitEvent;
    }

    @Generated
    public boolean isTickingSpell() {
        return this.isTickingSpell;
    }

    @Generated
    public Consumer<CastInformation> getTickEvent() {
        return this.tickEvent;
    }

    @Generated
    public Consumer<CastInformation> getAfterAllTicksEvent() {
        return this.afterAllTicksEvent;
    }

    @Generated
    public boolean isDamagingSpell() {
        return this.isDamagingSpell;
    }

    @Generated
    public boolean isHealingSpell() {
        return this.isHealingSpell;
    }

    @Generated
    public boolean isEffectingSpell() {
        return this.isEffectingSpell;
    }

    @Generated
    public boolean isAmplificationMultiplied() {
        return this.amplificationMultiplied;
    }

    @Generated
    public boolean isEffectDurationMultiplied() {
        return this.effectDurationMultiplied;
    }

    @Generated
    public Map<PotionEffectType, Pair<Integer, Integer>> getPositiveEffectPairMap() {
        return this.positiveEffectPairMap;
    }

    @Generated
    public Map<PotionEffectType, Pair<Integer, Integer>> getNegativeEffectPairMap() {
        return this.negativeEffectPairMap;
    }
}

