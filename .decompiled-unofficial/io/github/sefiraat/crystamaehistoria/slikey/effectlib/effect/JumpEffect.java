/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class JumpEffect
extends Effect {
    public float power = 0.5f;

    public JumpEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.INSTANT;
        this.period = 20;
        this.iterations = 1;
        this.asynchronous = false;
    }

    @Override
    public void onRun() {
        Entity entity = this.getEntity();
        if (entity == null) {
            this.cancel();
            return;
        }
        Vector v = entity.getVelocity();
        v.setY(v.getY() + (double)this.power);
        entity.setVelocity(v);
    }
}

