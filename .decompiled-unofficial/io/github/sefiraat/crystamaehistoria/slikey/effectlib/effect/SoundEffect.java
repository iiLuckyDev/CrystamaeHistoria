/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.CustomSound;

public class SoundEffect
extends Effect {
    public CustomSound sound;

    public SoundEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 1;
    }

    @Override
    public void onRun() {
        if (this.sound == null) {
            return;
        }
        this.sound.play(this.effectManager.getOwningPlugin(), this.getLocation());
    }
}

