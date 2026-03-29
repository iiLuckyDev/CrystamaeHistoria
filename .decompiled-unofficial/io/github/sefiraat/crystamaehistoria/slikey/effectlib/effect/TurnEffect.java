/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Entity
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class TurnEffect
extends Effect {
    public float step = 11.25f;

    public TurnEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = (int)(1800.0f / this.step);
        this.asynchronous = false;
    }

    @Override
    public void onRun() {
        Entity entity = this.getEntity();
        if (entity == null) {
            this.cancel();
            return;
        }
        Location loc = entity.getLocation();
        loc.setYaw(loc.getYaw() + this.step);
        entity.teleport(loc);
    }
}

