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

public class ArcEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public float height = 2.0f;
    public int particles = 100;
    protected int step = 0;

    public ArcEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 200;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        Location target = this.getTarget();
        if (target == null) {
            this.cancel();
            return;
        }
        Vector link = target.toVector().subtract(location.toVector());
        float length = (float)link.length();
        float pitch = (float)((double)(4.0f * this.height) / Math.pow(length, 2.0));
        for (int i = 0; i < this.particles; ++i) {
            Vector v = link.clone().normalize().multiply(length * (float)i / (float)this.particles);
            float x = (float)i / (float)this.particles * length - length / 2.0f;
            float y = (float)((double)(-pitch) * Math.pow(x, 2.0) + (double)this.height);
            location.add(v).add(0.0, (double)y, 0.0);
            this.display(this.particle, location);
            location.subtract(0.0, (double)y, 0.0).subtract(v);
            ++this.step;
        }
    }
}

