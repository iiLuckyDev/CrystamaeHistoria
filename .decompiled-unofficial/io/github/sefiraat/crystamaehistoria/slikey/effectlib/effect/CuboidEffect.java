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
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class CuboidEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public int particles = 8;
    public double xLength = 0.0;
    public double yLength = 0.0;
    public double zLength = 0.0;
    public double padding = 0.0;
    public boolean blockSnap = false;
    private double useXLength = 0.0;
    private double useYLength = 0.0;
    private double useZLength = 0.0;
    protected Location minCorner;
    protected boolean initialized;

    public CuboidEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 5;
        this.iterations = 200;
    }

    @Override
    public void reloadParameters() {
        this.initialized = false;
    }

    @Override
    public void onRun() {
        Location target = this.getTarget();
        Location location = this.getLocation();
        if (target == null || location == null) {
            return;
        }
        if (!this.initialized) {
            if (this.blockSnap) {
                target = target.getBlock().getLocation();
                this.minCorner = location.getBlock().getLocation();
            } else {
                this.minCorner = location.clone();
            }
            if (this.xLength == 0.0 && this.yLength == 0.0 && this.zLength == 0.0) {
                if (target == null || !target.getWorld().equals(location.getWorld())) {
                    this.cancel();
                    return;
                }
                if (target.getX() < this.minCorner.getX()) {
                    this.minCorner.setX(target.getX());
                }
                if (target.getY() < this.minCorner.getY()) {
                    this.minCorner.setY(target.getY());
                }
                if (target.getZ() < this.minCorner.getZ()) {
                    this.minCorner.setZ(target.getZ());
                }
                this.useXLength = Math.abs(location.getX() - target.getX());
                this.useYLength = Math.abs(location.getY() - target.getY());
                this.useZLength = Math.abs(location.getZ() - target.getZ());
            } else {
                this.useXLength = this.xLength;
                this.useYLength = this.yLength;
                this.useZLength = this.zLength;
            }
            double extra = this.padding * 2.0;
            if (this.blockSnap) {
                extra += 1.0;
            }
            this.useXLength += extra;
            this.useYLength += extra;
            this.useZLength += extra;
            if (this.padding != 0.0) {
                this.minCorner.add(-this.padding, -this.padding, -this.padding);
            }
            this.initialized = true;
        }
        this.drawOutline();
    }

    private void drawOutline() {
        Vector v = new Vector();
        for (int i = 0; i < this.particles; ++i) {
            this.drawEdge(v, i, 0, 2, 2);
            this.drawEdge(v, i, 0, 1, 2);
            this.drawEdge(v, i, 0, 1, 1);
            this.drawEdge(v, i, 0, 2, 1);
            this.drawEdge(v, i, 2, 0, 2);
            this.drawEdge(v, i, 1, 0, 2);
            this.drawEdge(v, i, 1, 0, 1);
            this.drawEdge(v, i, 2, 0, 1);
            this.drawEdge(v, i, 2, 2, 0);
            this.drawEdge(v, i, 1, 2, 0);
            this.drawEdge(v, i, 1, 1, 0);
            this.drawEdge(v, i, 2, 1, 0);
        }
    }

    private void drawEdge(Vector v, int i, int dx, int dy, int dz) {
        if (dx == 0) {
            v.setX(this.useXLength * (double)i / (double)this.particles);
        } else {
            v.setX(this.useXLength * (double)(dx - 1));
        }
        if (dy == 0) {
            v.setY(this.useYLength * (double)i / (double)this.particles);
        } else {
            v.setY(this.useYLength * (double)(dy - 1));
        }
        if (dz == 0) {
            v.setZ(this.useZLength * (double)i / (double)this.particles);
        } else {
            v.setZ(this.useZLength * (double)(dz - 1));
        }
        this.display(this.particle, this.minCorner.add(v));
        this.minCorner.subtract(v);
    }
}

