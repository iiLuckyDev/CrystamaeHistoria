/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.EntityEffect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

public class BleedEffect
extends Effect {
    public boolean hurt = true;
    public double height = 1.75;
    public Material material = Material.REDSTONE_BLOCK;

    public BleedEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 4;
        this.iterations = 25;
    }

    @Override
    public void onRun() {
        Location location = this.getLocation();
        location.add(0.0, (double)RandomUtils.random.nextFloat() * this.height, 0.0);
        location.getWorld().playEffect(location, org.bukkit.Effect.STEP_SOUND, (Object)this.material);
        Entity entity = this.getEntity();
        if (this.hurt && entity != null) {
            entity.playEffect(EntityEffect.HURT);
        }
    }
}

