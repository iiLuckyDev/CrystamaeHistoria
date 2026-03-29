/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Mob
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public abstract class AbstractRidableGoal<T extends Mob>
extends AbstractGoal<T> {
    protected AbstractRidableGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void tick() {
        if (!this.self.getPassengers().isEmpty()) {
            Player player = Bukkit.getPlayer((UUID)this.owner);
            Vector eyeDirection = player.getEyeLocation().getDirection();
            Location destination = player.getLocation().clone().add(eyeDirection);
            if (this.getCanFly()) {
                this.self.setVelocity(eyeDirection.clone().multiply(this.getSpeed()));
            } else {
                this.self.getPathfinder().moveTo(this.self.getLocation().add(0.0, 0.0, 1.0));
            }
        } else {
            super.tick();
        }
    }

    public boolean getCanFly() {
        return false;
    }

    public double getSpeed() {
        return 1.0;
    }
}

