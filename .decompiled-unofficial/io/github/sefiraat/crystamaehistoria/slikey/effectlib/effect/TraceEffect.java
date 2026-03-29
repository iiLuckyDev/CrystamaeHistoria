/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.World
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class TraceEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public int refresh = 5;
    public int maxWayPoints = 30;
    protected final List<Vector> wayPoints = new ArrayList<Vector>();
    protected int step = 0;
    protected World world;

    public TraceEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 600;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onRun() {
        Location location = this.getLocation();
        if (this.world == null) {
            this.world = location.getWorld();
        } else if (!location.getWorld().equals(this.world)) {
            this.cancel();
            return;
        }
        List<Vector> list = this.wayPoints;
        synchronized (list) {
            if (this.wayPoints.size() >= this.maxWayPoints) {
                this.wayPoints.remove(0);
            }
        }
        this.wayPoints.add(location.toVector());
        ++this.step;
        if (this.step % this.refresh != 0) {
            return;
        }
        list = this.wayPoints;
        synchronized (list) {
            for (Vector position : this.wayPoints) {
                Location particleLocation = new Location(this.world, position.getX(), position.getY(), position.getZ());
                this.display(this.particle, particleLocation);
            }
        }
    }
}

