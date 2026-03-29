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
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class TornadoEffect
extends Effect {
    public Particle tornadoParticle = Particle.FLAME;
    public Color tornadoColor = null;
    public Particle cloudParticle = Particle.CLOUD;
    public Color cloudColor = null;
    public float cloudSpeed = 0.0f;
    public float cloudSize = 2.5f;
    public double yOffset = 0.8;
    public float tornadoHeight = 5.0f;
    public float maxTornadoRadius = 5.0f;
    public boolean showCloud = true;
    public boolean showTornado = true;
    public double distance = 0.375;
    public int circleParticles = 64;
    public int cloudParticles = 100;
    public double circleHeight = 0.0;
    protected int step = 0;

    public TornadoEffect(EffectManager manager) {
        super(manager);
        this.type = EffectType.REPEATING;
        this.period = 5;
        this.iterations = 20;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        Location l = this.getLocation().add(0.0, this.yOffset, 0.0);
        int i = 0;
        while ((float)i < (float)this.cloudParticles * this.cloudSize) {
            Vector v = RandomUtils.getRandomCircleVector().multiply(RandomUtils.random.nextDouble() * (double)this.cloudSize);
            if (this.showCloud) {
                this.display(this.cloudParticle, l.add(v), this.cloudColor, this.cloudSpeed, 1);
                l.subtract(v);
            }
            ++i;
        }
        Location t = l.clone().add(0.0, 0.2, 0.0);
        double r = 0.45 * ((double)this.maxTornadoRadius * (2.35 / (double)this.tornadoHeight));
        for (double y = 0.0; y < (double)this.tornadoHeight; y += this.distance) {
            double fr = r * y;
            if (fr > (double)this.maxTornadoRadius) {
                fr = this.maxTornadoRadius;
            }
            for (Vector v : this.createCircle(y, fr)) {
                if (!this.showTornado) continue;
                if (this.circleHeight > 0.0) {
                    v.setY(v.getY() + RandomUtils.random.nextDouble() * this.circleHeight / 2.0 - this.circleHeight / 2.0);
                }
                this.display(this.tornadoParticle, t.add(v), this.tornadoColor);
                t.subtract(v);
                ++this.step;
            }
        }
        l.subtract(0.0, this.yOffset, 0.0);
    }

    public List<Vector> createCircle(double y, double radius) {
        double amount = radius * (double)this.circleParticles;
        double inc = Math.PI * 2 / amount;
        ArrayList<Vector> vecs = new ArrayList<Vector>();
        int i = 0;
        while ((double)i < amount) {
            double angle = (double)i * inc;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            Vector v = new Vector(x, y, z);
            vecs.add(v);
            ++i;
        }
        return vecs;
    }
}

