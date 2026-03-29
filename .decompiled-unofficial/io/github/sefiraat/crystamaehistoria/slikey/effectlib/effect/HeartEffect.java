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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.MathUtils;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class HeartEffect
extends Effect {
    public Particle particle = Particle.CRIT_MAGIC;
    public int particles = 50;
    public double xRotation;
    public double yRotation;
    public double zRotation = 0.0;
    public double yFactor = 1.0;
    public double xFactor = 1.0;
    public double factorInnerSpike = 0.8;
    public double compressYFactorTotal = 2.0;
    public float compilation = 2.0f;

    public HeartEffect(EffectManager effectManager) {
        super(effectManager);
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        Vector vector = new Vector();
        for (int i = 0; i < this.particles; ++i) {
            float alpha = (float)Math.PI / this.compilation / (float)this.particles * (float)i;
            double phi = Math.pow((double)Math.abs(MathUtils.sin(2.0f * this.compilation * alpha)) + this.factorInnerSpike * (double)Math.abs(MathUtils.sin(this.compilation * alpha)), 1.0 / this.compressYFactorTotal);
            vector.setY(phi * (double)(MathUtils.sin(alpha) + MathUtils.cos(alpha)) * this.yFactor);
            vector.setZ(phi * (double)(MathUtils.cos(alpha) - MathUtils.sin(alpha)) * this.xFactor);
            VectorUtils.rotateVector(vector, this.xRotation, this.yRotation, this.zRotation);
            this.display(this.particle, location.add(vector));
            location.subtract(vector);
        }
    }
}

