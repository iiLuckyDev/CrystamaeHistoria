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

public class IconEffect
extends Effect {
    public Particle particle = Particle.VILLAGER_ANGRY;
    public int yOffset = 2;

    public IconEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 4;
        this.iterations = 25;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        location.add(0.0, (double)this.yOffset, 0.0);
        this.display(this.particle, location);
    }
}

