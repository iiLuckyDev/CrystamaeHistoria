/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class CloudEffect
extends Effect {
    public Particle cloudParticle = Particle.CLOUD;
    public Color cloudColor = null;
    public float cloudSpeed = 0.0f;
    public int cloudParticles = 50;
    public Particle mainParticle = Particle.DRIP_WATER;
    public int mainParticles = 15;
    public float cloudSize = 0.7f;
    public float particleRadius = this.cloudSize - 0.1f;
    public double yOffset = 0.8;
    public boolean increaseHeight = true;

    public CloudEffect(EffectManager manager) {
        super(manager);
        this.type = EffectType.REPEATING;
        this.period = 5;
        this.iterations = 50;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        location.add(0.0, this.yOffset, 0.0);
        for (int i = 0; i < this.cloudParticles; ++i) {
            Vector v = RandomUtils.getRandomCircleVector().multiply(RandomUtils.random.nextDouble() * (double)this.cloudSize);
            this.display(this.cloudParticle, location.add(v), this.cloudColor, this.cloudSpeed, 1);
            location.subtract(v);
        }
        Location l = this.increaseHeight ? location.add(0.0, 0.2, 0.0) : location;
        for (int i = 0; i < this.mainParticles; ++i) {
            int r = RandomUtils.random.nextInt(2);
            double x = RandomUtils.random.nextDouble() * (double)this.particleRadius;
            double z = RandomUtils.random.nextDouble() * (double)this.particleRadius;
            l.add(x, 0.0, z);
            if (r != 1) {
                this.display(this.mainParticle, l);
            }
            l.subtract(x, 0.0, z);
            l.subtract(x, 0.0, z);
            if (r != 1) {
                this.display(this.mainParticle, l);
            }
            l.add(x, 0.0, z);
        }
    }
}

