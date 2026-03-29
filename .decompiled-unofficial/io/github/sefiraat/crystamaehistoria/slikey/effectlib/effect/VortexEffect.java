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

public class VortexEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public float radius = 2.0f;
    public float grow = 0.05f;
    public double radials = 0.19634954084936207;
    public int circles = 3;
    public int helixes = 4;
    protected int step = 0;

    public VortexEffect(EffectManager effectManager) {
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
        for (int x = 0; x < this.circles; ++x) {
            for (int i = 0; i < this.helixes; ++i) {
                double angle = (double)this.step * this.radials + Math.PI * 2 * (double)i / (double)this.helixes;
                Vector v = new Vector(Math.cos(angle) * (double)this.radius, (double)((float)this.step * this.grow), Math.sin(angle) * (double)this.radius);
                VectorUtils.rotateAroundAxisX(v, (location.getPitch() + 90.0f) * ((float)Math.PI / 180));
                VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
                location.add(v);
                this.display(this.particle, location);
                location.subtract(v);
            }
            ++this.step;
        }
    }
}

