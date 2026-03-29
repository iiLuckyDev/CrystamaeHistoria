/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

public class LineEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public boolean isZigZag = false;
    public int zigZags = 10;
    public Vector zigZagOffset = new Vector(0.0, 0.1, 0.0);
    public Vector zigZagRelativeOffset = new Vector(0, 0, 0);
    public int particles = 100;
    public double length = 0.0;
    public double maxLength = 0.0;
    private String subEffectAtEndClass = null;
    public ConfigurationSection subEffectAtEnd = null;
    private String subEffectAtEndCachedClass = null;
    public ConfigurationSection subEffectAtEndCached = null;
    protected boolean zag = false;
    protected int step = 0;
    protected Effect effectAtEndCached = null;

    public LineEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 1;
    }

    @Override
    public void reset() {
        this.step = 0;
        if (this.effectAtEndCached != null) {
            this.effectAtEndCached.cancel();
            this.effectAtEndCached = null;
        }
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        Location target = this.length > 0.0 ? location.clone().add(location.getDirection().normalize().multiply(this.length)) : this.getTarget();
        int amount = this.particles / this.zigZags;
        if (target == null) {
            this.cancel();
            return;
        }
        Vector link = target.toVector().subtract(location.toVector());
        float length = (float)link.length();
        if (this.maxLength > 0.0) {
            length = (float)Math.min((double)length, this.maxLength);
        }
        link.normalize();
        float ratio = length / (float)this.particles;
        Vector v = link.multiply(ratio);
        Location loc = location.clone().subtract(v);
        for (int i = 0; i < this.particles; ++i) {
            if (this.isZigZag) {
                Vector rel = VectorUtils.rotateVector(this.zigZagRelativeOffset, loc);
                if (this.zag) {
                    loc.add(rel);
                    loc.add(this.zigZagOffset);
                } else {
                    loc.subtract(rel);
                    loc.subtract(this.zigZagOffset);
                }
            }
            if (this.step >= amount) {
                this.zag = !this.zag;
                this.step = 0;
            }
            ++this.step;
            loc.add(v);
            this.display(this.particle, loc);
        }
        if (this.subEffectAtEndClass != null) {
            this.effectManager.start(this.subEffectAtEndClass, this.subEffectAtEnd, loc);
        }
        if (this.subEffectAtEndCachedClass != null && this.effectAtEndCached == null) {
            this.effectAtEndCached = this.effectManager.start(this.subEffectAtEndCachedClass, this.subEffectAtEndCached, loc);
        }
    }

    @Override
    protected void initialize() {
        super.initialize();
        if (this.subEffectAtEnd != null) {
            this.subEffectAtEndClass = this.subEffectAtEnd.getString("subEffectAtEndClass");
        }
        if (this.subEffectAtEndCached != null) {
            this.subEffectAtEndCachedClass = this.subEffectAtEndCached.getString("subEffectAtEndCachedClass");
        }
    }
}

