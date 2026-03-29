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
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class DragonEffect
extends Effect {
    protected final List<Float> rndF;
    protected final List<Double> rndAngle;
    public Particle particle = Particle.FLAME;
    public float pitch = 0.1f;
    public int arcs = 20;
    public int particles = 30;
    public int stepsPerIteration = 2;
    public float length = 4.0f;
    protected int step = 0;

    public DragonEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = 200;
        this.rndF = new ArrayList<Float>(this.arcs);
        this.rndAngle = new ArrayList<Double>(this.arcs);
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        for (int j = 0; j < this.stepsPerIteration; ++j) {
            if (this.step % this.particles == 0) {
                this.rndF.clear();
                this.rndAngle.clear();
            }
            while (this.rndF.size() < this.arcs) {
                this.rndF.add(Float.valueOf(RandomUtils.random.nextFloat()));
            }
            while (this.rndAngle.size() < this.arcs) {
                this.rndAngle.add(RandomUtils.getRandomAngle());
            }
            for (int i = 0; i < this.arcs; ++i) {
                float pitch = this.rndF.get(i).floatValue() * 2.0f * this.pitch - this.pitch;
                float x = (float)(this.step % this.particles) * this.length / (float)this.particles;
                float y = (float)((double)pitch * Math.pow(x, 2.0));
                Vector v = new Vector(x, y, 0.0f);
                VectorUtils.rotateAroundAxisX(v, this.rndAngle.get(i));
                VectorUtils.rotateAroundAxisZ(v, -location.getPitch() * ((float)Math.PI / 180));
                VectorUtils.rotateAroundAxisY(v, -(location.getYaw() + 90.0f) * ((float)Math.PI / 180));
                this.display(this.particle, location.add(v));
                location.subtract(v);
            }
            ++this.step;
        }
    }
}

