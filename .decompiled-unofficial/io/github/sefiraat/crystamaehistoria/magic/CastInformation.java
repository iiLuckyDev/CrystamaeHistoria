/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CastInformation {
    private final UUID caster;
    private final int staveLevel;
    private final Location castLocation;
    private SpellType spellType;
    private Location damageLocation;
    private LivingEntity mainTarget;
    private Block hitBlock;
    private Block targetedBlockOnCast;
    private BlockFace targetedBlockFaceOnCast;
    private Location projectileLocation;
    private int currentTick = 1;
    private Consumer<CastInformation> beforeProjectileHitEvent;
    private Consumer<CastInformation> projectileHitEvent;
    private Consumer<CastInformation> afterProjectileHitEvent;
    private Consumer<CastInformation> projectileHitBlockEvent;
    private Consumer<CastInformation> tickEvent;
    private Consumer<CastInformation> afterTicksEvent;

    @ParametersAreNonnullByDefault
    public CastInformation(Player caster, int staveLevel) {
        this.caster = caster.getUniqueId();
        this.staveLevel = staveLevel;
        this.castLocation = caster.getLocation().clone();
        this.targetedBlockOnCast = caster.getTargetBlockExact(50);
        this.targetedBlockFaceOnCast = caster.getTargetBlockFace(50);
    }

    public Player getCasterAsPlayer() {
        return Bukkit.getPlayer((UUID)this.caster);
    }

    public void runPreAffectEvent() {
        if (this.beforeProjectileHitEvent != null) {
            this.beforeProjectileHitEvent.accept(this);
        }
    }

    public void runAffectEvent() {
        if (this.projectileHitEvent != null) {
            this.projectileHitEvent.accept(this);
        }
    }

    public void runPostAffectEvent() {
        if (this.afterProjectileHitEvent != null) {
            this.afterProjectileHitEvent.accept(this);
        }
    }

    public void runProjectileHitBlockEvent() {
        if (this.projectileHitBlockEvent != null) {
            this.projectileHitBlockEvent.accept(this);
        }
    }

    public void runTickEvent() {
        if (this.tickEvent != null) {
            this.tickEvent.accept(this);
        }
        ++this.currentTick;
    }

    public void runAfterTicksEvent() {
        if (this.afterTicksEvent != null) {
            this.afterTicksEvent.accept(this);
        }
    }

    @Generated
    public UUID getCaster() {
        return this.caster;
    }

    @Generated
    public int getStaveLevel() {
        return this.staveLevel;
    }

    @Generated
    public Location getCastLocation() {
        return this.castLocation;
    }

    @Generated
    public SpellType getSpellType() {
        return this.spellType;
    }

    @Generated
    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
    }

    @Generated
    public Location getDamageLocation() {
        return this.damageLocation;
    }

    @Generated
    public void setDamageLocation(Location damageLocation) {
        this.damageLocation = damageLocation;
    }

    @Generated
    public LivingEntity getMainTarget() {
        return this.mainTarget;
    }

    @Generated
    public void setMainTarget(LivingEntity mainTarget) {
        this.mainTarget = mainTarget;
    }

    @Generated
    public Block getHitBlock() {
        return this.hitBlock;
    }

    @Generated
    public void setHitBlock(Block hitBlock) {
        this.hitBlock = hitBlock;
    }

    @Generated
    public Block getTargetedBlockOnCast() {
        return this.targetedBlockOnCast;
    }

    @Generated
    public void setTargetedBlockOnCast(Block targetedBlockOnCast) {
        this.targetedBlockOnCast = targetedBlockOnCast;
    }

    @Generated
    public BlockFace getTargetedBlockFaceOnCast() {
        return this.targetedBlockFaceOnCast;
    }

    @Generated
    public void setTargetedBlockFaceOnCast(BlockFace targetedBlockFaceOnCast) {
        this.targetedBlockFaceOnCast = targetedBlockFaceOnCast;
    }

    @Generated
    public Location getProjectileLocation() {
        return this.projectileLocation;
    }

    @Generated
    public void setProjectileLocation(Location projectileLocation) {
        this.projectileLocation = projectileLocation;
    }

    @Generated
    public int getCurrentTick() {
        return this.currentTick;
    }

    @Generated
    public void setCurrentTick(int currentTick) {
        this.currentTick = currentTick;
    }

    @Generated
    public void setBeforeProjectileHitEvent(Consumer<CastInformation> beforeProjectileHitEvent) {
        this.beforeProjectileHitEvent = beforeProjectileHitEvent;
    }

    @Generated
    public void setProjectileHitEvent(Consumer<CastInformation> projectileHitEvent) {
        this.projectileHitEvent = projectileHitEvent;
    }

    @Generated
    public void setAfterProjectileHitEvent(Consumer<CastInformation> afterProjectileHitEvent) {
        this.afterProjectileHitEvent = afterProjectileHitEvent;
    }

    @Generated
    public void setProjectileHitBlockEvent(Consumer<CastInformation> projectileHitBlockEvent) {
        this.projectileHitBlockEvent = projectileHitBlockEvent;
    }

    @Generated
    public void setTickEvent(Consumer<CastInformation> tickEvent) {
        this.tickEvent = tickEvent;
    }

    @Generated
    public void setAfterTicksEvent(Consumer<CastInformation> afterTicksEvent) {
        this.afterTicksEvent = afterTicksEvent;
    }
}

