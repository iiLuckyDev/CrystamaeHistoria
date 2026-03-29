/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import org.bukkit.Location;
import org.bukkit.Particle;

public class WarpEffect
extends Effect {
    public float radius = 1.0f;
    public int particles = 20;
    public Particle particle = Particle.FIREWORKS_SPARK;
    public float grow = 0.2f;
    public int rings = 12;
    protected int step = 0;

    public WarpEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = this.rings;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        if (this.step > this.rings) {
            this.step = 0;
        }
        double y = (float)this.step * this.grow;
        location.add(0.0, y, 0.0);
        for (int i = 0; i < this.particles; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)this.particles;
            double x = Math.cos(angle) * (double)this.radius;
            double z = Math.sin(angle) * (double)this.radius;
            location.add(x, 0.0, z);
            this.display(this.particle, location);
            location.subtract(x, 0.0, z);
        }
        location.subtract(0.0, y, 0.0);
        ++this.step;
    }
}

