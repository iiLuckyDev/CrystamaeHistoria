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

public class FountainEffect
extends Effect {
    public Particle particle = Particle.WATER_SPLASH;
    public int strands = 10;
    public int particlesStrand = 150;
    public int particlesSpout = 200;
    public float radius = 5.0f;
    public float radiusSpout = 0.1f;
    public float height = 3.0f;
    public float heightSpout = 7.0f;
    public double rotation = 0.7853981633974483;

    public FountainEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = 100;
    }

    @Override
    public void onRun() {
        int i;
        Location location = this.getLocation();
        for (i = 1; i <= this.strands; ++i) {
            double angle = (double)(2 * i) * Math.PI / (double)this.strands + this.rotation;
            for (int j = 1; j <= this.particlesStrand; ++j) {
                float ratio = (float)j / (float)this.particlesStrand;
                double x = Math.cos(angle) * (double)this.radius * (double)ratio;
                double y = Math.sin(Math.PI * (double)j / (double)this.particlesStrand) * (double)this.height;
                double z = Math.sin(angle) * (double)this.radius * (double)ratio;
                location.add(x, y, z);
                this.display(this.particle, location);
                location.subtract(x, y, z);
            }
        }
        for (i = 0; i < this.particlesSpout; ++i) {
            Vector v = RandomUtils.getRandomCircleVector().multiply(RandomUtils.random.nextFloat() * this.radius * this.radiusSpout);
            v.setY(RandomUtils.random.nextFloat() * this.heightSpout);
            location.add(v);
            this.display(this.particle, location);
            location.subtract(v);
        }
    }
}

