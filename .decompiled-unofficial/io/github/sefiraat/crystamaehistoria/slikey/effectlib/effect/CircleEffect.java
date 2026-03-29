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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class CircleEffect
extends Effect {
    public boolean orient = false;
    public Particle particle = Particle.VILLAGER_HAPPY;
    public double xRotation;
    public double yRotation;
    public double zRotation = 0.0;
    public double angularVelocityX = 0.015707963267948967;
    public double angularVelocityY = 0.018479956785822312;
    public double angularVelocityZ = 0.02026833970057931;
    public float radius = 0.4f;
    public double maxAngle = Math.PI * 2;
    public boolean resetCircle = false;
    protected float step = 0.0f;
    public double xSubtract;
    public double ySubtract;
    public double zSubtract;
    public boolean enableRotation = true;
    public int particles = 20;
    public boolean wholeCircle = false;

    public CircleEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = 50;
    }

    @Override
    public void reset() {
        this.step = 0.0f;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        location.subtract(this.xSubtract, this.ySubtract, this.zSubtract);
        double inc = this.maxAngle / (double)this.particles;
        int steps = this.wholeCircle ? this.particles : 1;
        for (int i = 0; i < steps; ++i) {
            double angle = (double)this.step * inc;
            Vector v = new Vector();
            v.setX(Math.cos(angle) * (double)this.radius);
            v.setZ(Math.sin(angle) * (double)this.radius);
            VectorUtils.rotateVector(v, this.xRotation, this.yRotation, this.zRotation);
            VectorUtils.rotateAroundAxisX(v, location.getPitch() * ((float)Math.PI / 180));
            VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
            if (this.enableRotation) {
                VectorUtils.rotateVector(v, this.angularVelocityX * (double)this.step, this.angularVelocityY * (double)this.step, this.angularVelocityZ * (double)this.step);
            }
            if (this.orient) {
                v = VectorUtils.rotateVector(v, location);
            }
            this.display(this.particle, location.clone().add(v));
            this.step += 1.0f;
        }
        if (this.resetCircle) {
            this.step = 0.0f;
        }
    }
}

