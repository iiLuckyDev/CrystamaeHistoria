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

public class HillEffect
extends Effect {
    public Particle particle = Particle.FLAME;
    public float height = 2.5f;
    public float particles = 30.0f;
    public float edgeLength = 6.5f;
    public double yRotation = 0.4487989505128276;

    public HillEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 10;
        this.iterations = 20;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        Vector v = new Vector();
        int x = 0;
        while ((float)x <= this.particles) {
            double y1 = Math.sin(Math.PI * (double)x / (double)this.particles);
            int z = 0;
            while ((float)z <= this.particles) {
                double y2 = Math.sin(Math.PI * (double)z / (double)this.particles);
                v.setX(this.edgeLength * (float)x / this.particles).setZ(this.edgeLength * (float)z / this.particles);
                v.setY((double)this.height * y1 * y2);
                VectorUtils.rotateAroundAxisY(v, this.yRotation);
                this.display(this.particle, location.add(v));
                location.subtract(v);
                ++z;
            }
            ++x;
        }
    }
}

