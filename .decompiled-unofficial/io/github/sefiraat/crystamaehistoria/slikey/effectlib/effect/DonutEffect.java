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

public class DonutEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public int particlesCircle = 10;
    public int circles = 36;
    public float radiusDonut = 2.0f;
    public float radiusTube = 0.5f;
    public double xRotation;
    public double yRotation;
    public double zRotation = 0.0;

    public DonutEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 10;
        this.iterations = 20;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        Vector v = new Vector();
        for (int i = 0; i < this.circles; ++i) {
            double theta = Math.PI * 2 * (double)i / (double)this.circles;
            for (int j = 0; j < this.particlesCircle; ++j) {
                double phi = Math.PI * 2 * (double)j / (double)this.particlesCircle;
                double cosPhi = Math.cos(phi);
                v.setX(((double)this.radiusDonut + (double)this.radiusTube * cosPhi) * Math.cos(theta));
                v.setY(((double)this.radiusDonut + (double)this.radiusTube * cosPhi) * Math.sin(theta));
                v.setZ((double)this.radiusTube * Math.sin(phi));
                VectorUtils.rotateVector(v, this.xRotation, this.yRotation, this.zRotation);
                VectorUtils.rotateAroundAxisX(v, (location.getPitch() + 90.0f) * ((float)Math.PI / 180));
                VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
                this.display(this.particle, location.add(v));
                location.subtract(v);
            }
        }
    }
}

