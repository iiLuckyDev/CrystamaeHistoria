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
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCore;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.potion.PotionEffectType;

public class SpellCoreBuilder {
    private final double cooldownSeconds;
    private final double range;
    private final int crystaCost;
    private final boolean cooldownDivided;
    private final boolean rangeMultiplied;
    private final boolean crystaMultiplied;
    private final Map<PotionEffectType, Pair<Integer, Integer>> positiveEffectPairMap = new HashMap<PotionEffectType, Pair<Integer, Integer>>();
    private final Map<PotionEffectType, Pair<Integer, Integer>> negativeEffectPairMap = new HashMap<PotionEffectType, Pair<Integer, Integer>>();
    private int particleNumber = 1;
    private boolean isInstantCast;
    private Consumer<CastInformation> instantCastEvent;
    private boolean isProjectileSpell;
    private boolean isProjectileVsEntitySpell;
    private boolean isProjectileVsBlockSpell;
    private double projectileAoeRange;
    private double projectileKnockbackAmount;
    private boolean projectileAoeMultiplied;
    private boolean projectileKnockbackMultiplied;
    private Consumer<CastInformation> fireProjectileEvent;
    private Consumer<CastInformation> beforeProjectileHitEvent;
    private Consumer<CastInformation> projectileHitEvent;
    private Consumer<CastInformation> projectileHitBlockEvent;
    private Consumer<CastInformation> afterProjectileHitEvent;
    private boolean isTickingSpell;
    private int numberOfTicks;
    private int tickInterval;
    private boolean numberOfTicksMultiplied;
    private boolean tickIntervalMultiplied;
    private Consumer<CastInformation> tickEvent;
    private Consumer<CastInformation> afterAllTicksEvent;
    private boolean isDamagingSpell;
    private double damageAmount;
    private boolean damageMultiplied;
    private double knockbackAmount;
    private boolean knockbackMultiplied;
    private boolean isHealingSpell;
    private double healAmount;
    private boolean healMultiplied;
    private boolean isEffectingSpell;
    private boolean amplificationMultiplied;
    private boolean effectDurationMultiplied;

    public SpellCoreBuilder(double cooldownSeconds, boolean cooldownDivided, double range, boolean rangeMultiplied, int crystaCost, boolean crystaMultiplied) {
        this.cooldownSeconds = cooldownSeconds;
        this.cooldownDivided = cooldownDivided;
        this.range = range;
        this.rangeMultiplied = rangeMultiplied;
        this.crystaCost = crystaCost;
        this.crystaMultiplied = crystaMultiplied;
    }

    @Nonnull
    @CheckReturnValue
    public SpellCoreBuilder makeHealingSpell(double healAmount, boolean healMultiplied) {
        this.isHealingSpell = true;
        this.healAmount = healAmount;
        this.healMultiplied = healMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    public SpellCoreBuilder makeEffectingSpell(boolean effectAmplificationMultiplied, boolean effectDurationMultiplied) {
        this.isEffectingSpell = true;
        this.amplificationMultiplied = effectAmplificationMultiplied;
        this.effectDurationMultiplied = effectDurationMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    public SpellCoreBuilder makeDamagingSpell(double damageAmount, boolean damagePowerMultiplied, double knockbackAmount, boolean knockbackMultiplied) {
        this.isDamagingSpell = true;
        this.damageAmount = damageAmount;
        this.damageMultiplied = damagePowerMultiplied;
        this.knockbackAmount = knockbackAmount;
        this.knockbackMultiplied = knockbackMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeInstantSpell(Consumer<CastInformation> instantCastMethod) {
        this.isInstantCast = true;
        this.instantCastEvent = instantCastMethod;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeProjectileSpell(Consumer<CastInformation> fireProjectileConsumer, double projectileAoeRange, boolean projectileAoeMultiplied, double projectileKnockbackAmount, boolean projectileKnockbackMultiplied) {
        this.isProjectileSpell = true;
        this.fireProjectileEvent = fireProjectileConsumer;
        this.projectileAoeRange = projectileAoeRange;
        this.projectileAoeMultiplied = projectileAoeMultiplied;
        this.projectileKnockbackAmount = projectileKnockbackAmount;
        this.projectileKnockbackMultiplied = projectileKnockbackMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeProjectileVsEntitySpell(Consumer<CastInformation> projectileHitConsumer) {
        this.isProjectileVsEntitySpell = true;
        this.projectileHitEvent = projectileHitConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeProjectileVsBlockSpell(Consumer<CastInformation> projectileHitConsumer) {
        this.isProjectileVsBlockSpell = true;
        this.projectileHitBlockEvent = projectileHitConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addBeforeProjectileHitEntityEvent(Consumer<CastInformation> spellCastInformationConsumer) {
        this.beforeProjectileHitEvent = spellCastInformationConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addAfterProjectileHitEntityEvent(Consumer<CastInformation> spellCastInformationConsumer) {
        this.afterProjectileHitEvent = spellCastInformationConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addProjectileHitBlockEvent(Consumer<CastInformation> spellCastInformationConsumer) {
        this.projectileHitBlockEvent = spellCastInformationConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder makeTickingSpell(Consumer<CastInformation> spellCastInformationConsumer, int numberOfTicks, boolean ticksMultiplied, int tickInterval, boolean tickIntervalMultiplied) {
        this.tickEvent = spellCastInformationConsumer;
        this.isTickingSpell = true;
        this.numberOfTicks = numberOfTicks;
        this.numberOfTicksMultiplied = ticksMultiplied;
        this.tickInterval = tickInterval;
        this.tickIntervalMultiplied = tickIntervalMultiplied;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addAfterTicksEvent(Consumer<CastInformation> spellCastInformationConsumer) {
        this.afterAllTicksEvent = spellCastInformationConsumer;
        return this;
    }

    @Nonnull
    @CheckReturnValue
    public SpellCoreBuilder setNumberOfParticles(int particleNumber) {
        this.particleNumber = particleNumber;
        return this;
    }

    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addPositiveEffect(PotionEffectType potionEffectType, int level, int durationInSeconds) {
        this.positiveEffectPairMap.put(potionEffectType, (Pair<Integer, Integer>)new Pair((Object)level, (Object)(durationInSeconds *= 20)));
        return this;
    }

    @ParametersAreNonnullByDefault
    public SpellCoreBuilder addNegativeEffect(PotionEffectType potionEffectType, int level, int durationInSeconds) {
        this.negativeEffectPairMap.put(potionEffectType, (Pair<Integer, Integer>)new Pair((Object)level, (Object)(durationInSeconds *= 20)));
        return this;
    }

    @Nonnull
    public SpellCore build() {
        return new SpellCore(this);
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
    public Map<PotionEffectType, Pair<Integer, Integer>> getPositiveEffectPairMap() {
        return this.positiveEffectPairMap;
    }

    @Generated
    public Map<PotionEffectType, Pair<Integer, Integer>> getNegativeEffectPairMap() {
        return this.negativeEffectPairMap;
    }

    @Generated
    public int getParticleNumber() {
        return this.particleNumber;
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
    public double getProjectileAoeRange() {
        return this.projectileAoeRange;
    }

    @Generated
    public double getProjectileKnockbackAmount() {
        return this.projectileKnockbackAmount;
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
    public int getNumberOfTicks() {
        return this.numberOfTicks;
    }

    @Generated
    public int getTickInterval() {
        return this.tickInterval;
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
    public double getDamageAmount() {
        return this.damageAmount;
    }

    @Generated
    public boolean isDamageMultiplied() {
        return this.damageMultiplied;
    }

    @Generated
    public double getKnockbackAmount() {
        return this.knockbackAmount;
    }

    @Generated
    public boolean isKnockbackMultiplied() {
        return this.knockbackMultiplied;
    }

    @Generated
    public boolean isHealingSpell() {
        return this.isHealingSpell;
    }

    @Generated
    public double getHealAmount() {
        return this.healAmount;
    }

    @Generated
    public boolean isHealMultiplied() {
        return this.healMultiplied;
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
}

