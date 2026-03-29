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

public class HelixEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public int strands = 8;
    public int particles = 80;
    public float radius = 10.0f;
    public float curve = 10.0f;
    public double rotation = 0.7853981633974483;

    public HelixEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 10;
        this.iterations = 8;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        for (int i = 1; i <= this.strands; ++i) {
            for (int j = 1; j <= this.particles; ++j) {
                float ratio = (float)j / (float)this.particles;
                double angle = (double)(this.curve * ratio * 2.0f) * Math.PI / (double)this.strands + Math.PI * 2 * (double)i / (double)this.strands + this.rotation;
                double x = Math.cos(angle) * (double)ratio * (double)this.radius;
                double z = Math.sin(angle) * (double)ratio * (double)this.radius;
                location.add(x, 0.0, z);
                this.display(this.particle, location);
                location.subtract(x, 0.0, z);
            }
        }
    }
}

