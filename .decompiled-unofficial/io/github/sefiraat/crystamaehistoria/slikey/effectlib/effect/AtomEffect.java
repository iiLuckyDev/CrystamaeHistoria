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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class AtomEffect
extends Effect {
    public Particle particleNucleus = Particle.DRIP_WATER;
    public Color colorNucleus = null;
    public Particle particleOrbital = Particle.DRIP_LAVA;
    public Color colorOrbital = null;
    public double radius = 3.0;
    public float radiusNucleus = 0.2f;
    public int particlesNucleus = 10;
    public int particlesOrbital = 10;
    public int orbitals = 3;
    public double rotation = 0.0;
    public boolean orient = false;
    public double angularVelocity = 0.039269908169872414;
    protected int step = 0;

    public AtomEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = 200;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        int i;
        Location location = this.getLocation();
        for (i = 0; i < this.particlesNucleus; ++i) {
            Vector v = RandomUtils.getRandomVector().multiply(this.radius * (double)this.radiusNucleus);
            if (this.orient) {
                v = VectorUtils.rotateVector(v, location);
            }
            location.add(v);
            this.display(this.particleNucleus, location, this.colorNucleus);
            location.subtract(v);
        }
        for (i = 0; i < this.particlesOrbital; ++i) {
            double angle = (double)this.step * this.angularVelocity;
            for (int j = 0; j < this.orbitals; ++j) {
                double xRotation = Math.PI / (double)this.orbitals * (double)j;
                Vector v = new Vector(Math.cos(angle), Math.sin(angle), 0.0).multiply(this.radius);
                VectorUtils.rotateAroundAxisX(v, xRotation);
                VectorUtils.rotateAroundAxisY(v, this.rotation);
                if (this.orient) {
                    v = VectorUtils.rotateVector(v, location);
                }
                location.add(v);
                this.display(this.particleOrbital, location, this.colorOrbital);
                location.subtract(v);
            }
            ++this.step;
        }
    }
}

