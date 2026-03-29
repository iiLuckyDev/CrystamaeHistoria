/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.Projectile
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class MagicProjectile {
    private final UUID projectileUUID;
    private Consumer<MagicProjectile> consumer;

    @ParametersAreNonnullByDefault
    public MagicProjectile(Projectile projectile) {
        this.projectileUUID = projectile.getUniqueId();
    }

    @ParametersAreNonnullByDefault
    public void setVelocity(Location targetLocation, double speed) {
        Projectile projectile = (Projectile)Bukkit.getEntity((UUID)this.projectileUUID);
        Vector velocity = targetLocation.toVector().subtract(projectile.getLocation().toVector()).normalize();
        this.setVelocity(velocity, speed);
    }

    @ParametersAreNonnullByDefault
    public void setVelocity(Vector vector, double speed) {
        Projectile projectile = (Projectile)Bukkit.getEntity((UUID)this.projectileUUID);
        if (projectile instanceof Fireball) {
            ((Fireball)projectile).setDirection(vector);
        }
        projectile.setVelocity(vector.multiply(speed));
    }

    @Nonnull
    public Location getLocation() {
        Projectile projectile = (Projectile)Bukkit.getEntity((UUID)this.projectileUUID);
        return projectile.getLocation();
    }

    @Nullable
    public Projectile getProjectile() {
        return (Projectile)Bukkit.getEntity((UUID)this.projectileUUID);
    }

    @ParametersAreNonnullByDefault
    public boolean matches(Projectile projectile) {
        return this.projectileUUID.equals(projectile.getUniqueId());
    }

    public void disableGravity() {
        Projectile projectile = (Projectile)Bukkit.getEntity((UUID)this.projectileUUID);
        if (projectile != null) {
            projectile.setGravity(false);
        }
    }

    public void kill() {
        CrystamaeHistoria.getProjectileMap().remove(this);
        Projectile projectile = (Projectile)Bukkit.getEntity((UUID)this.projectileUUID);
        if (projectile != null) {
            projectile.remove();
        }
    }

    public void run() {
        if (this.consumer != null) {
            this.consumer.accept(this);
        }
    }

    @Generated
    public UUID getProjectileUUID() {
        return this.projectileUUID;
    }

    @Generated
    public Consumer<MagicProjectile> getConsumer() {
        return this.consumer;
    }

    @Generated
    public void setConsumer(Consumer<MagicProjectile> consumer) {
        this.consumer = consumer;
    }
}

