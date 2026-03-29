/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.Fireball
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;

public class MagicFallingBlock {
    private final UUID fallingBlockUUID;

    @ParametersAreNonnullByDefault
    public MagicFallingBlock(FallingBlock fallingBlock) {
        this.fallingBlockUUID = fallingBlock.getUniqueId();
    }

    @ParametersAreNonnullByDefault
    public void setVelocity(Location targetLocation, double speed) {
        FallingBlock fallingBlock = (FallingBlock)Bukkit.getEntity((UUID)this.fallingBlockUUID);
        Vector velocity = targetLocation.toVector().subtract(fallingBlock.getLocation().toVector()).normalize();
        this.setVelocity(velocity, speed);
    }

    @ParametersAreNonnullByDefault
    public void setVelocity(Vector vector, double speed) {
        FallingBlock fallingBlock = (FallingBlock)Bukkit.getEntity((UUID)this.fallingBlockUUID);
        if (fallingBlock instanceof Fireball) {
            ((Fireball)fallingBlock).setDirection(vector);
        }
        fallingBlock.setVelocity(vector.multiply(speed));
    }

    @Nonnull
    public Location getLocation() {
        FallingBlock fallingBlock = (FallingBlock)Bukkit.getEntity((UUID)this.fallingBlockUUID);
        return fallingBlock.getLocation();
    }

    @Nullable
    public FallingBlock getFallingBlock() {
        return (FallingBlock)Bukkit.getEntity((UUID)this.fallingBlockUUID);
    }

    @ParametersAreNonnullByDefault
    public boolean matches(FallingBlock fallingBlock) {
        return this.fallingBlockUUID.equals(fallingBlock.getUniqueId());
    }

    public void disableGravity() {
        FallingBlock fallingBlock = (FallingBlock)Bukkit.getEntity((UUID)this.fallingBlockUUID);
        if (fallingBlock != null) {
            fallingBlock.setGravity(false);
        }
    }

    public void kill() {
        CrystamaeHistoria.getFallingBlockMap().remove(this);
        FallingBlock fallingBlock = (FallingBlock)Bukkit.getEntity((UUID)this.fallingBlockUUID);
        if (fallingBlock != null) {
            fallingBlock.remove();
        }
    }

    @Generated
    public UUID getFallingBlockUUID() {
        return this.fallingBlockUUID;
    }
}

