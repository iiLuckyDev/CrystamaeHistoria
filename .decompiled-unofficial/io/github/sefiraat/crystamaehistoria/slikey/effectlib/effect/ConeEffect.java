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
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class ConeEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public float lengthGrow = 0.05f;
    public double angularVelocity = 0.19634954084936207;
    public int particles = 10;
    public float radiusGrow = 0.006f;
    public int particlesCone = 180;
    public double rotation = 0.0;
    public boolean randomize = false;
    protected int step = 0;

    public ConeEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 200;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        for (int x = 0; x < this.particles; ++x) {
            if (this.step > this.particlesCone) {
                this.step = 0;
            }
            if (this.randomize && this.step == 0) {
                this.rotation = RandomUtils.getRandomAngle();
            }
            double angle = (double)this.step * this.angularVelocity + this.rotation;
            float radius = (float)this.step * this.radiusGrow;
            float length = (float)this.step * this.lengthGrow;
            Vector v = new Vector(Math.cos(angle) * (double)radius, (double)length, Math.sin(angle) * (double)radius);
            VectorUtils.rotateAroundAxisX(v, (location.getPitch() + 90.0f) * ((float)Math.PI / 180));
            VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
            location.add(v);
            this.display(this.particle, location);
            location.subtract(v);
            ++this.step;
        }
    }
}

