/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class PyramidEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public int particles = 8;
    public double radius = 0.0;

    public PyramidEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 5;
        this.iterations = 200;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        this.drawOutline(location);
    }

    private void drawOutline(Location location) {
        Vector v = new Vector();
        for (int i = 0; i < this.particles; ++i) {
            this.drawEdge(location, v, i, 0, 0, -1);
            this.drawEdge(location, v, i, 0, 0, 1);
            this.drawEdge(location, v, i, -1, 0, 0);
            this.drawEdge(location, v, i, 1, 0, 0);
            this.drawEdge(location, v, i, -1, 1, -1);
            this.drawEdge(location, v, i, -1, 1, 1);
            this.drawEdge(location, v, i, 1, 1, -1);
            this.drawEdge(location, v, i, 1, 1, 1);
        }
    }

    private void drawEdge(Location center, Vector v, int i, int dx, int dy, int dz) {
        double ratio = (double)i / (double)this.particles;
        if (dy == 1) {
            v.setY(ratio);
            if (dx < 0) {
                v.setX(ratio - 1.0);
            } else {
                v.setX(1.0 - ratio);
            }
            if (dz < 0) {
                v.setZ(ratio - 1.0);
            } else {
                v.setZ(1.0 - ratio);
            }
        } else {
            v.setY(0);
            if (dx == 0) {
                v.setX(ratio * 2.0 - 1.0);
            } else {
                v.setX(dx);
            }
            if (dz == 0) {
                v.setZ(ratio * 2.0 - 1.0);
            } else {
                v.setZ(dz);
            }
        }
        this.display(this.particle, center.add(v.multiply(this.radius)));
        center.subtract(v);
    }
}

