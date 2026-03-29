/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import org.bukkit.Location;
import org.bukkit.Particle;

public class MusicEffect
extends Effect {
    public Particle particle = Particle.NOTE;
    public double radialsPerStep = 0.39269908169872414;
    public float radius = 0.4f;
    protected float step = 0.0f;

    public MusicEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.iterations = 400;
        this.period = 1;
    }

    @Override
    public void reset() {
        this.step = 0.0f;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        location.add(0.0, (double)1.9f, 0.0);
        location.add(Math.cos(this.radialsPerStep * (double)this.step) * (double)this.radius, 0.0, Math.sin(this.radialsPerStep * (double)this.step) * (double)this.radius);
        this.display(this.particle, location);
        this.step += 1.0f;
    }
}

