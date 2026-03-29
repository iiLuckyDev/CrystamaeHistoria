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
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class CylinderEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public float radius = 1.0f;
    public float height = 3.0f;
    public double angularVelocityX = 0.015707963267948967;
    public double angularVelocityY = 0.018479956785822312;
    public double angularVelocityZ = 0.02026833970057931;
    public double rotationX;
    public double rotationY;
    public double rotationZ;
    public int particles = 100;
    public boolean enableRotation = true;
    public boolean solid = false;
    protected int step = 0;
    protected float sideRatio = 0.0f;
    public boolean orient = false;

    public CylinderEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = 200;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        if (this.sideRatio == 0.0f) {
            this.calculateSideRatio();
        }
        Random r = RandomUtils.random;
        double xRotation = this.rotationX;
        double yRotation = this.rotationY;
        double zRotation = this.rotationZ;
        if (this.orient) {
            xRotation = Math.toRadians(90.0f - location.getPitch()) + this.rotationX;
            yRotation = Math.toRadians(180.0f - location.getYaw()) + this.rotationY;
        }
        if (this.enableRotation) {
            xRotation += (double)this.step * this.angularVelocityX;
            yRotation += (double)this.step * this.angularVelocityY;
            zRotation += (double)this.step * this.angularVelocityZ;
        }
        for (int i = 0; i < this.particles; ++i) {
            float multi = this.solid ? r.nextFloat() : 1.0f;
            Vector v = RandomUtils.getRandomCircleVector().multiply(this.radius);
            if (r.nextFloat() <= this.sideRatio) {
                v.multiply(multi);
                v.setY((r.nextFloat() * 2.0f - 1.0f) * (this.height / 2.0f));
            } else {
                v.multiply(r.nextFloat());
                if ((double)r.nextFloat() < 0.5) {
                    v.setY(multi * (this.height / 2.0f));
                } else {
                    v.setY(-multi * (this.height / 2.0f));
                }
            }
            if (this.enableRotation || this.orient) {
                VectorUtils.rotateVector(v, xRotation, yRotation, zRotation);
            }
            this.display(this.particle, location.add(v));
            location.subtract(v);
        }
        this.display(this.particle, location);
        ++this.step;
    }

    protected void calculateSideRatio() {
        float grounds = 9.869605f * this.radius * 2.0f;
        float side = (float)Math.PI * 2 * this.radius * this.height;
        this.sideRatio = side / (side + grounds);
    }
}

