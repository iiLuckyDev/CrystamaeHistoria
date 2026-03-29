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
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Particle;

public class SmokeEffect
extends Effect {
    public Particle particle = Particle.SMOKE_NORMAL;
    public int particles = 20;

    public SmokeEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 300;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        for (int i = 0; i < this.particles; ++i) {
            location.add(RandomUtils.getRandomCircleVector().multiply(RandomUtils.random.nextDouble() * 0.6));
            location.add(0.0, (double)(RandomUtils.random.nextFloat() * 2.0f), 0.0);
            this.display(this.particle, location);
        }
    }
}

