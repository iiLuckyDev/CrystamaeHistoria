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

public class ShieldEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public double radius = 3.0;
    public int particles = 50;
    public boolean sphere = false;
    public boolean reverse = false;

    public ShieldEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.iterations = 500;
        this.period = 1;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        for (int i = 0; i < this.particles; ++i) {
            Vector vector = RandomUtils.getRandomVector().multiply(this.radius);
            if (!this.sphere) {
                if (this.reverse) {
                    vector.setY(Math.abs(vector.getY()) * -1.0);
                } else {
                    vector.setY(Math.abs(vector.getY()));
                }
            }
            location.add(vector);
            this.display(this.particle, location);
            location.subtract(vector);
        }
    }
}

