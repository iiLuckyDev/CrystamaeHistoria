/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.managers;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.ParticleDisplayRunnable;
import io.github.sefiraat.crystamaehistoria.runnables.SaveConfigRunnable;
import io.github.sefiraat.crystamaehistoria.runnables.TemporaryEffectsRunnable;
import lombok.Generated;
import org.bukkit.plugin.Plugin;

public class RunnableManager {
    public final TemporaryEffectsRunnable temporaryEffectsRunnable;
    public final SaveConfigRunnable saveConfigRunnable;
    public final ParticleDisplayRunnable particleDisplayRunnable;

    public RunnableManager() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        this.temporaryEffectsRunnable = new TemporaryEffectsRunnable();
        this.temporaryEffectsRunnable.runTaskTimer((Plugin)plugin, 1L, 20L);
        this.saveConfigRunnable = new SaveConfigRunnable();
        this.saveConfigRunnable.runTaskTimer((Plugin)plugin, 1L, 12000L);
        this.particleDisplayRunnable = new ParticleDisplayRunnable();
        this.particleDisplayRunnable.runTaskTimer((Plugin)plugin, 1L, 80L);
    }

    @Generated
    public TemporaryEffectsRunnable getTemporaryEffectsRunnable() {
        return this.temporaryEffectsRunnable;
    }

    @Generated
    public SaveConfigRunnable getSaveConfigRunnable() {
        return this.saveConfigRunnable;
    }

    @Generated
    public ParticleDisplayRunnable getParticleDisplayRunnable() {
        return this.particleDisplayRunnable;
    }
}

