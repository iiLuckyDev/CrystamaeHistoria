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

public class DiscoBallEffect
extends Effect {
    public float sphereRadius = 0.6f;
    public int max = 15;
    public Particle sphereParticle = Particle.FLAME;
    public Particle lineParticle = Particle.REDSTONE;
    public Color sphereColor = null;
    public Color lineColor = null;
    public int maxLines = 7;
    public int lineParticles = 100;
    public int sphereParticles = 50;
    public Direction direction = Direction.DOWN;

    public DiscoBallEffect(EffectManager manager) {
        super(manager);
        this.type = EffectType.REPEATING;
        this.period = 7;
        this.iterations = 500;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        int mL = RandomUtils.random.nextInt(this.maxLines - 2) + 2;
        for (int m = 0; m < mL * 2; ++m) {
            double x = RandomUtils.random.nextInt(this.max - this.max * -1) + this.max * -1;
            double y = RandomUtils.random.nextInt(this.max - this.max * -1) + this.max * -1;
            double z = RandomUtils.random.nextInt(this.max - this.max * -1) + this.max * -1;
            if (this.direction == Direction.DOWN) {
                y = RandomUtils.random.nextInt(this.max * 2 - this.max) + this.max;
            } else if (this.direction == Direction.UP) {
                y = RandomUtils.random.nextInt(this.max * -1 - this.max * -2) + this.max * -2;
            }
            Location target = location.clone().subtract(x, y, z);
            if (target == null) {
                this.cancel();
                return;
            }
            Vector link = target.toVector().subtract(location.toVector());
            float length = (float)link.length();
            link.normalize();
            float ratio = length / (float)this.lineParticles;
            Vector v = link.multiply(ratio);
            Location loc = location.clone().subtract(v);
            for (int i = 0; i < this.lineParticles; ++i) {
                loc.add(v);
                this.display(this.lineParticle, loc, this.lineColor);
            }
        }
        for (int i = 0; i < this.sphereParticles; ++i) {
            Vector vector = RandomUtils.getRandomVector().multiply(this.sphereRadius);
            location.add(vector);
            this.display(this.sphereParticle, location, this.sphereColor);
            location.subtract(vector);
        }
    }

    public static enum Direction {
        UP,
        DOWN,
        BOTH;

    }
}

