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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class StarEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public int particles = 50;
    public float spikeHeight = 3.5f;
    public int spikesHalf = 3;
    public float innerRadius = 0.5f;

    public StarEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 4;
        this.iterations = 50;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        float radius = 3.0f * this.innerRadius / 1.73205f;
        for (int i = 0; i < this.spikesHalf * 2; ++i) {
            double xRotation = (double)i * Math.PI / (double)this.spikesHalf;
            for (int x = 0; x < this.particles; ++x) {
                double angle = Math.PI * 2 * (double)x / (double)this.particles;
                float height = RandomUtils.random.nextFloat() * this.spikeHeight;
                Vector v = new Vector(Math.cos(angle), 0.0, Math.sin(angle));
                v.multiply((this.spikeHeight - height) * radius / this.spikeHeight);
                v.setY(this.innerRadius + height);
                VectorUtils.rotateAroundAxisX(v, xRotation);
                location.add(v);
                this.display(this.particle, location);
                location.subtract(v);
                VectorUtils.rotateAroundAxisX(v, Math.PI);
                VectorUtils.rotateAroundAxisY(v, 1.5707963267948966);
                location.add(v);
                this.display(this.particle, location);
                location.subtract(v);
            }
        }
    }
}

