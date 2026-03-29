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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import java.util.Collection;
import java.util.HashSet;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class WaveEffect
extends Effect {
    public Particle particle = Particle.DRIP_WATER;
    public Particle cloudParticle = Particle.CLOUD;
    public Color cloudColor = null;
    public Vector velocity = new Vector();
    protected final Collection<Vector> waterCache;
    protected final Collection<Vector> cloudCache;
    public int particlesFront = 10;
    public int particlesBack = 10;
    public int rows = 20;
    public float lengthFront = 1.5f;
    public float lengthBack = 3.0f;
    public float depthFront = 1.0f;
    public float heightBack = 0.5f;
    public float height = 2.0f;
    public float width = 5.0f;
    protected boolean firstStep = true;

    public WaveEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 5;
        this.iterations = 50;
        this.waterCache = new HashSet<Vector>();
        this.cloudCache = new HashSet<Vector>();
    }

    @Override
    public void reset() {
        this.firstStep = true;
    }

    public void invalidate(Location location) {
        Vector vec;
        float z;
        int j;
        Vector v;
        float y;
        float x;
        float ratio;
        int i;
        this.firstStep = false;
        this.waterCache.clear();
        this.cloudCache.clear();
        Vector s1 = new Vector(-this.lengthFront, 0.0f, 0.0f);
        Vector s2 = new Vector(this.lengthBack, 0.0f, 0.0f);
        Vector h = new Vector(-0.5 * (double)this.lengthFront, (double)this.height, 0.0);
        Vector s1ToH = h.clone().subtract(s1);
        Vector c1 = s1.clone().add(s1ToH.clone().multiply(0.5));
        float len_s1ToH = (float)s1ToH.length();
        Vector n_s1ToH = s1ToH.clone().multiply(1.0f / len_s1ToH);
        Vector n1 = new Vector(s1ToH.getY(), -s1ToH.getX(), 0.0).normalize();
        if (n1.getX() < 0.0) {
            n1.multiply(-1);
        }
        Vector s2ToH = h.clone().subtract(s2);
        Vector c2 = s2.clone().add(s2ToH.clone().multiply(0.5));
        float len_s2ToH = (float)s2ToH.length();
        Vector n_s2ToH = s2ToH.clone().multiply(1.0f / len_s2ToH);
        Vector n2 = new Vector(s2ToH.getY(), -s2ToH.getX(), 0.0).normalize();
        if (n2.getX() < 0.0) {
            n2.multiply(-1);
        }
        float yaw = (-location.getYaw() + 90.0f) * ((float)Math.PI / 180);
        for (i = 0; i < this.particlesFront; ++i) {
            ratio = (float)i / (float)this.particlesFront;
            x = (ratio - 0.5f) * len_s1ToH;
            y = (float)((double)(-this.depthFront) / Math.pow(len_s1ToH / 2.0f, 2.0) * Math.pow(x, 2.0) + (double)this.depthFront);
            v = c1.clone();
            v.add(n_s1ToH.clone().multiply(x));
            v.add(n1.clone().multiply(y));
            for (j = 0; j < this.rows; ++j) {
                z = ((float)j / (float)this.rows - 0.5f) * this.width;
                vec = v.clone().setZ(v.getZ() + (double)z);
                VectorUtils.rotateAroundAxisY(vec, yaw);
                if (i == 0 || i == this.particlesFront - 1) {
                    this.cloudCache.add(vec);
                    continue;
                }
                this.waterCache.add(vec);
            }
        }
        for (i = 0; i < this.particlesBack; ++i) {
            ratio = (float)i / (float)this.particlesBack;
            x = (ratio - 0.5f) * len_s2ToH;
            y = (float)((double)(-this.heightBack) / Math.pow(len_s2ToH / 2.0f, 2.0) * Math.pow(x, 2.0) + (double)this.heightBack);
            v = c2.clone();
            v.add(n_s2ToH.clone().multiply(x));
            v.add(n2.clone().multiply(y));
            for (j = 0; j < this.rows; ++j) {
                z = ((float)j / (float)this.rows - 0.5f) * this.width;
                vec = v.clone().setZ(v.getZ() + (double)z);
                VectorUtils.rotateAroundAxisY(vec, yaw);
                if (i == this.particlesFront - 1) {
                    this.cloudCache.add(vec);
                    continue;
                }
                this.waterCache.add(vec);
            }
        }
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        if (this.firstStep) {
            this.velocity.copy(location.getDirection().setY(0).normalize().multiply(0.2));
            this.invalidate(location);
        }
        location.add(this.velocity);
        for (Vector v : this.cloudCache) {
            location.add(v);
            this.display(this.cloudParticle, location, this.cloudColor, 0.0f, 1);
            location.subtract(v);
        }
        for (Vector v : this.waterCache) {
            location.add(v);
            this.display(this.particle, location);
            location.subtract(v);
        }
    }
}

