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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class DnaEffect
extends Effect {
    public Particle particleHelix = Particle.FLAME;
    public Color colorHelix = null;
    public Particle particleBase1 = Particle.WATER_WAKE;
    public Color colorBase1 = null;
    public Particle particleBase2 = Particle.REDSTONE;
    public Color colorBase2 = null;
    public double radials = 0.10471975511965977;
    public float radius = 1.5f;
    public int particlesHelix = 3;
    public int particlesBase = 15;
    public float length = 15.0f;
    public float grow = 0.2f;
    public float baseInterval = 10.0f;
    protected int step = 0;

    public DnaEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 500;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        for (int j = 0; j < this.particlesHelix; ++j) {
            int i;
            if ((float)this.step * this.grow > this.length) {
                this.step = 0;
            }
            for (i = 0; i < 2; ++i) {
                double angle = (double)this.step * this.radials + Math.PI * (double)i;
                Vector v = new Vector(Math.cos(angle) * (double)this.radius, (double)((float)this.step * this.grow), Math.sin(angle) * (double)this.radius);
                this.drawParticle(location, v, this.particleHelix, this.colorHelix);
            }
            if ((float)this.step % this.baseInterval == 0.0f) {
                for (i = -this.particlesBase; i <= this.particlesBase; ++i) {
                    if (i == 0) continue;
                    Particle particle = this.particleBase1;
                    Color color = this.colorBase1;
                    if (i < 0) {
                        particle = this.particleBase2;
                        color = this.colorBase2;
                    }
                    double angle = (double)this.step * this.radials;
                    Vector v = new Vector(Math.cos(angle), 0.0, Math.sin(angle)).multiply(this.radius * (float)i / (float)this.particlesBase).setY((float)this.step * this.grow);
                    this.drawParticle(location, v, particle, color);
                }
            }
            ++this.step;
        }
    }

    protected void drawParticle(Location location, Vector v, Particle particle, Color color) {
        VectorUtils.rotateAroundAxisX(v, (location.getPitch() + 90.0f) * ((float)Math.PI / 180));
        VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
        location.add(v);
        this.display(particle, location, color);
        location.subtract(v);
    }
}

