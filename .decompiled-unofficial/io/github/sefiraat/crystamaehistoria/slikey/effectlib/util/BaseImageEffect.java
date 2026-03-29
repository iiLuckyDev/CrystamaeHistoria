/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ImageLoadCallback;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public abstract class BaseImageEffect
extends Effect {
    public Particle particle = Particle.REDSTONE;
    public String fileName = null;
    public boolean transparency = false;
    public int frameDelay = 5;
    public int stepX = 10;
    public int stepY = 10;
    public float size = 0.025f;
    public boolean enableRotation = true;
    public Vector rotation = null;
    public boolean orient = true;
    public boolean orientPitch = false;
    public Plane plane = Plane.XYZ;
    public double angularVelocityX = 0.015707963267948967;
    public double angularVelocityY = 0.018479956785822312;
    public double angularVelocityZ = 0.02026833970057931;
    protected BufferedImage[] images = null;
    protected int step = 0;
    protected int rotationStep = 0;
    protected int stepDelay = 0;
    protected ImageLoadCallback imageLoadCallback;

    public BaseImageEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = 200;
    }

    @Override
    public void reset() {
        this.step = 0;
        this.rotationStep = 0;
    }

    public void load(String fileName) {
        this.imageLoadCallback = new ImageLoadCallback(){

            @Override
            public void loaded(BufferedImage[] i) {
                BaseImageEffect.this.images = i;
                BaseImageEffect.this.imageLoadCallback = null;
            }
        };
        this.effectManager.loadImage(fileName, this.imageLoadCallback);
    }

    public void loadFile(File file) {
        this.load(file.getName());
    }

    @Override
    public void onRun() {
        if (this.images == null && this.imageLoadCallback != null) {
            return;
        }
        if (this.images == null && this.fileName != null) {
            this.load(this.fileName);
            return;
        }
        if (this.images == null || this.images.length == 0) {
            this.cancel();
            return;
        }
        if (this.stepDelay == this.frameDelay) {
            ++this.step;
            this.stepDelay = 0;
        }
        ++this.stepDelay;
        if (this.step >= this.images.length) {
            this.step = 0;
        }
        BufferedImage image = this.images[this.step];
        Location location = this.getLocation();
        for (int y = 0; y < image.getHeight(); y += this.stepY) {
            for (int x = 0; x < image.getWidth(); x += this.stepX) {
                Vector v = new Vector((float)image.getWidth() / 2.0f - (float)x, (float)image.getHeight() / 2.0f - (float)y, 0.0f).multiply(this.size);
                if (this.rotation != null) {
                    VectorUtils.rotateVector(v, this.rotation.getX() * 0.01745329238474369, this.rotation.getY() * 0.01745329238474369, this.rotation.getZ() * 0.01745329238474369);
                }
                if (this.orientPitch) {
                    VectorUtils.rotateAroundAxisX(v, Math.toRadians(location.getPitch()));
                }
                if (this.orient) {
                    VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
                }
                if (this.enableRotation) {
                    double rotX = 0.0;
                    double rotY = 0.0;
                    double rotZ = 0.0;
                    switch (this.plane) {
                        case X: {
                            rotX = this.angularVelocityX * (double)this.rotationStep;
                            break;
                        }
                        case Y: {
                            rotY = this.angularVelocityY * (double)this.rotationStep;
                            break;
                        }
                        case Z: {
                            rotZ = this.angularVelocityZ * (double)this.rotationStep;
                            break;
                        }
                        case XY: {
                            rotX = this.angularVelocityX * (double)this.rotationStep;
                            rotY = this.angularVelocityY * (double)this.rotationStep;
                            break;
                        }
                        case XZ: {
                            rotX = this.angularVelocityX * (double)this.rotationStep;
                            rotZ = this.angularVelocityZ * (double)this.rotationStep;
                            break;
                        }
                        case XYZ: {
                            rotX = this.angularVelocityX * (double)this.rotationStep;
                            rotY = this.angularVelocityY * (double)this.rotationStep;
                            rotZ = this.angularVelocityZ * (double)this.rotationStep;
                            break;
                        }
                        case YZ: {
                            rotY = this.angularVelocityY * (double)this.rotationStep;
                            rotZ = this.angularVelocityZ * (double)this.step;
                        }
                    }
                    VectorUtils.rotateVector(v, rotX, rotY, rotZ);
                }
                int pixel = image.getRGB(x, y);
                if (this.transparency && pixel >> 24 == 0) continue;
                this.display(image, v, location, pixel);
            }
        }
        ++this.rotationStep;
    }

    protected abstract void display(BufferedImage var1, Vector var2, Location var3, int var4);

    public static enum Plane {
        X,
        Y,
        Z,
        XY,
        XZ,
        XYZ,
        YZ;

    }
}

