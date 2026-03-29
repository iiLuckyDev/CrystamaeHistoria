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

public class CubeEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public float edgeLength = 3.0f;
    public double angularVelocityX = 0.015707963267948967;
    public double angularVelocityY = 0.018479956785822312;
    public double angularVelocityZ = 0.02026833970057931;
    public int particles = 8;
    public boolean enableRotation = true;
    public boolean outlineOnly = true;
    public boolean orient = false;
    protected int step = 0;

    public CubeEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 5;
        this.iterations = 200;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        if (this.outlineOnly) {
            this.drawCubeOutline(location);
        } else {
            this.drawCubeWalls(location);
        }
        ++this.step;
    }

    private void drawCubeOutline(Location location) {
        double xRotation = 0.0;
        double yRotation = 0.0;
        double zRotation = 0.0;
        if (this.enableRotation) {
            xRotation = (double)this.step * this.angularVelocityX;
            yRotation = (double)this.step * this.angularVelocityY;
            zRotation = (double)this.step * this.angularVelocityZ;
        }
        float a = this.edgeLength / 2.0f;
        Vector v = new Vector();
        for (int i = 0; i < 4; ++i) {
            double angleY = (double)i * Math.PI / 2.0;
            for (int j = 0; j < 2; ++j) {
                double angleX = (double)j * Math.PI;
                for (int p = 0; p <= this.particles; ++p) {
                    v.setX(a).setY(a);
                    v.setZ(this.edgeLength * (float)p / (float)this.particles - a);
                    VectorUtils.rotateAroundAxisX(v, angleX);
                    VectorUtils.rotateAroundAxisY(v, angleY);
                    if (this.enableRotation) {
                        VectorUtils.rotateVector(v, xRotation, yRotation, zRotation);
                    }
                    if (this.orient) {
                        this.rotateLocation(location, v);
                    }
                    this.display(this.particle, location.add(v));
                    location.subtract(v);
                }
            }
            for (int p = 0; p <= this.particles; ++p) {
                v.setX(a).setZ(a);
                v.setY(this.edgeLength * (float)p / (float)this.particles - a);
                VectorUtils.rotateAroundAxisY(v, angleY);
                if (this.enableRotation) {
                    VectorUtils.rotateVector(v, xRotation, yRotation, zRotation);
                }
                if (this.orient) {
                    this.rotateLocation(location, v);
                }
                this.display(this.particle, location.add(v));
                location.subtract(v);
            }
        }
    }

    private void drawCubeWalls(Location location) {
        double xRotation = 0.0;
        double yRotation = 0.0;
        double zRotation = 0.0;
        if (this.enableRotation) {
            xRotation = (double)this.step * this.angularVelocityX;
            yRotation = (double)this.step * this.angularVelocityY;
            zRotation = (double)this.step * this.angularVelocityZ;
        }
        float a = this.edgeLength / 2.0f;
        for (int x = 0; x <= this.particles; ++x) {
            float posX = this.edgeLength * ((float)x / (float)this.particles) - a;
            for (int y = 0; y <= this.particles; ++y) {
                float posY = this.edgeLength * ((float)y / (float)this.particles) - a;
                for (int z = 0; z <= this.particles; ++z) {
                    if (x != 0 && x != this.particles && y != 0 && y != this.particles && z != 0 && z != this.particles) continue;
                    float posZ = this.edgeLength * ((float)z / (float)this.particles) - a;
                    Vector v = new Vector(posX, posY, posZ);
                    if (this.enableRotation) {
                        VectorUtils.rotateVector(v, xRotation, yRotation, zRotation);
                    }
                    if (this.orient) {
                        this.rotateLocation(location, v);
                    }
                    this.display(this.particle, location.add(v));
                    location.subtract(v);
                }
            }
        }
    }

    private void rotateLocation(Location location, Vector v) {
        VectorUtils.rotateAroundAxisX(v, location.getPitch() * ((float)Math.PI / 180));
        VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
    }
}

