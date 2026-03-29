/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.scheduler.BukkitRunnable
 */
package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.SpellMemory;
import org.bukkit.scheduler.BukkitRunnable;

public class TemporaryEffectsRunnable
extends BukkitRunnable {
    public void run() {
        SpellMemory storage = CrystamaeHistoria.getSpellMemory();
        this.clearExpiredProjectiles(storage);
        this.clearMagicFallingBlocks(storage);
        this.clearMagicSummons(storage);
        this.clearTempBlocks(storage);
        this.removeTempFlight(storage);
        this.removeFrozenTime(storage);
        this.removeFrozenWeather(storage);
        this.removeInhibitedEndermen(storage);
        this.removeSpawnDisabledAreas(storage);
        this.removeDisplayItems(storage);
    }

    public void clearMagicSummons(SpellMemory storage) {
        storage.removeEntities(false);
    }

    public void clearMagicFallingBlocks(SpellMemory storage) {
        storage.removeFallingBlocks(false);
    }

    public void clearTempBlocks(SpellMemory storage) {
        storage.removeBlocks(false);
    }

    public void clearExpiredProjectiles(SpellMemory storage) {
        storage.removeProjectiles(false);
    }

    public void removeTempFlight(SpellMemory storage) {
        storage.removeFlight(false);
    }

    public void removeFrozenTime(SpellMemory storage) {
        storage.removeFrozenTime(false);
    }

    public void removeFrozenWeather(SpellMemory storage) {
        storage.removeFrozenWeather(false);
    }

    public void removeInhibitedEndermen(SpellMemory storage) {
        storage.removeEnderman(false);
    }

    public void removeSpawnDisabledAreas(SpellMemory storage) {
        storage.enableSpawningInArea(false);
    }

    public void removeDisplayItems(SpellMemory storage) {
        storage.removeDisplayItems(false);
    }
}

