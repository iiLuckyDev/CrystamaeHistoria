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

public class GridEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public int rows = 5;
    public int columns = 10;
    public float widthCell = 1.0f;
    public float heightCell = 1.0f;
    public int particlesWidth = 4;
    public int particlesHeight = 3;
    public double rotation = 0.0;
    public double rotationX = 0.0;
    public double rotationZ = 0.0;
    public boolean center = false;

    public GridEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.INSTANT;
        this.period = 5;
        this.iterations = 50;
    }

    @Override
    public void onRun() {
        int j;
        int i;
        Location location = this.getLocation();
        Vector v = new Vector();
        for (i = 0; i <= this.rows + 1; ++i) {
            for (j = 0; j < this.particlesWidth * (this.columns + 1); ++j) {
                v.setY((float)i * this.heightCell);
                v.setX((float)j * this.widthCell / (float)this.particlesWidth);
                this.addParticle(location, v);
            }
        }
        for (i = 0; i <= this.columns + 1; ++i) {
            for (j = 0; j < this.particlesHeight * (this.rows + 1); ++j) {
                v.setX((float)i * this.widthCell);
                v.setY((float)j * this.heightCell / (float)this.particlesHeight);
                this.addParticle(location, v);
            }
        }
    }

    protected void addParticle(Location location, Vector v) {
        v.setZ(0);
        if (this.center) {
            v.setY(v.getY() + (double)(this.heightCell * (float)(-(this.rows + 1)) / 2.0f));
            v.setX(v.getX() + (double)(this.widthCell * (float)(-(this.columns + 1)) / 2.0f));
        }
        VectorUtils.rotateAroundAxisY(v, this.rotation);
        if (this.rotationX != 0.0) {
            VectorUtils.rotateAroundAxisX(v, this.rotationX);
        }
        if (this.rotationZ != 0.0) {
            VectorUtils.rotateAroundAxisZ(v, this.rotationZ);
        }
        location.add(v);
        this.display(this.particle, location);
        location.subtract(v);
    }
}

