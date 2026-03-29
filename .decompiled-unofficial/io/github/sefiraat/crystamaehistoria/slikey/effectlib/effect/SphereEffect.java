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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class SphereEffect
extends Effect {
    public Particle particle = Particle.SPELL_MOB;
    public double radius = 0.6;
    public double yOffset = 0.0;
    public int particles = 50;
    public double radiusIncrease = 0.0;
    public int particleIncrease = 0;

    public SphereEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.iterations = 500;
        this.period = 1;
    }

    @Override
    public void onRun() {
        if (this.radiusIncrease != 0.0) {
            this.radius += this.radiusIncrease;
        }
        if (this.particleIncrease != 0) {
            this.particles += this.particleIncrease;
        }
        Location location = this.getLocation();
        location.add(0.0, this.yOffset, 0.0);
        for (int i = 0; i < this.particles; ++i) {
            Vector vector = RandomUtils.getRandomVector().multiply(this.radius);
            location.add(vector);
            this.display(this.particle, location);
            location.subtract(vector);
        }
    }
}

