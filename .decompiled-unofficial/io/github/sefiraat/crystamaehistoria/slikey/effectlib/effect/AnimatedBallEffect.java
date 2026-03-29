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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.MathUtils;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class AnimatedBallEffect
extends Effect {
    public Particle particle = Particle.SPELL_WITCH;
    public int particles = 150;
    public int particlesPerIteration = 10;
    public float size = 1.0f;
    public float xFactor = 1.0f;
    public float yFactor = 2.0f;
    public float zFactor = 1.0f;
    public float xOffset;
    public float yOffset = 0.8f;
    public float zOffset;
    public double xRotation;
    public double yRotation;
    public double zRotation = 0.0;
    protected int step = 0;

    public AnimatedBallEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.iterations = 500;
        this.period = 1;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Vector vector = new Vector();
        Location location = this.getLocation();
        for (int i = 0; i < this.particlesPerIteration; ++i) {
            ++this.step;
            float t = (float)Math.PI / (float)this.particles * (float)this.step;
            float r = MathUtils.sin(t) * this.size;
            float s = (float)Math.PI * 2 * t;
            vector.setX(this.xFactor * r * MathUtils.cos(s) + this.xOffset);
            vector.setZ(this.zFactor * r * MathUtils.sin(s) + this.zOffset);
            vector.setY(this.yFactor * this.size * MathUtils.cos(t) + this.yOffset);
            VectorUtils.rotateVector(vector, this.xRotation, this.yRotation, this.zRotation);
            this.display(this.particle, location.add(vector));
            location.subtract(vector);
        }
    }
}

