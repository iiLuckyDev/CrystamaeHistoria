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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.MathUtils;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class EarthEffect
extends Effect {
    public Particle particleLand = Particle.VILLAGER_HAPPY;
    public Particle particleOcean = Particle.DRIP_WATER;
    public Color colorLand = null;
    public Color colorOcean = null;
    public int particlesLand = 3;
    public int particlesOcean = 1;
    public float speedLand = 0.0f;
    public float speedOcean = 0.0f;
    public int precision = 100;
    public int particles = 500;
    public float radius = 1.0f;
    public float mountainHeight = 0.5f;
    protected boolean firstStep = true;
    protected final Set<Vector> cacheGreen;
    protected final Set<Vector> cacheBlue;

    public EarthEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 5;
        this.iterations = 200;
        this.cacheGreen = new HashSet<Vector>();
        this.cacheBlue = new HashSet<Vector>();
    }

    @Override
    public void reset() {
        this.firstStep = true;
    }

    public void invalidate() {
        this.firstStep = false;
        this.cacheGreen.clear();
        this.cacheBlue.clear();
        HashSet<Vector> cache = new HashSet<Vector>();
        int sqrtParticles = (int)Math.sqrt(this.particles);
        float theta = 0.0f;
        float thetaStep = (float)Math.PI / (float)sqrtParticles;
        float phiStep = (float)Math.PI * 2 / (float)sqrtParticles;
        for (int i = 0; i < sqrtParticles; ++i) {
            theta += thetaStep;
            float phi = 0.0f;
            for (int j = 0; j < sqrtParticles; ++j) {
                float x = this.radius * MathUtils.sin(theta) * MathUtils.cos(phi += phiStep);
                float y = this.radius * MathUtils.sin(theta) * MathUtils.sin(phi);
                float z = this.radius * MathUtils.cos(theta);
                cache.add(new Vector(x, y, z));
            }
        }
        float increase = this.mountainHeight / (float)this.precision;
        for (int i = 0; i < this.precision; ++i) {
            double r1 = RandomUtils.getRandomAngle();
            double r2 = RandomUtils.getRandomAngle();
            double r3 = RandomUtils.getRandomAngle();
            for (Vector v : cache) {
                if (v.getY() > 0.0) {
                    v.setY(v.getY() + (double)increase);
                } else {
                    v.setY(v.getY() - (double)increase);
                }
                if (i == this.precision - 1) continue;
                VectorUtils.rotateVector(v, r1, r2, r3);
            }
        }
        float minSquared = Float.POSITIVE_INFINITY;
        float maxSquared = Float.NEGATIVE_INFINITY;
        for (Vector current : cache) {
            float lengthSquared = (float)current.lengthSquared();
            if (minSquared > lengthSquared) {
                minSquared = lengthSquared;
            }
            if (!(maxSquared < lengthSquared)) continue;
            maxSquared = lengthSquared;
        }
        float average = (minSquared + maxSquared) / 2.0f;
        for (Vector v : cache) {
            float lengthSquared = (float)v.lengthSquared();
            if (lengthSquared >= average) {
                this.cacheGreen.add(v);
                continue;
            }
            this.cacheBlue.add(v);
        }
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        if (this.firstStep) {
            this.invalidate();
        }
        for (Vector v : this.cacheGreen) {
            this.display(this.particleLand, location.add(v), this.colorLand, this.speedLand, this.particlesLand);
            location.subtract(v);
        }
        for (Vector v : this.cacheBlue) {
            this.display(this.particleOcean, location.add(v), this.colorOcean, this.speedOcean, this.particlesOcean);
            location.subtract(v);
        }
    }
}

