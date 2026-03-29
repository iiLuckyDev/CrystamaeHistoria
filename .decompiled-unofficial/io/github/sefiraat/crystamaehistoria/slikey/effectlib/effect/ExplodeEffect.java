/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class ExplodeEffect
extends Effect {
    public Particle particle1 = Particle.EXPLOSION_NORMAL;
    public Particle particle2 = Particle.EXPLOSION_HUGE;
    public int amount = 25;
    public Sound sound = Sound.ENTITY_GENERIC_EXPLODE;

    public ExplodeEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.INSTANT;
        this.speed = 0.5f;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        location.getWorld().playSound(location, this.sound, 4.0f, (1.0f + (RandomUtils.random.nextFloat() - RandomUtils.random.nextFloat()) * 0.2f) * 0.7f);
        this.display(this.particle1, location);
        this.display(this.particle2, location);
    }
}

