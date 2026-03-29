/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.scheduler.BukkitRunnable
 */
package io.github.sefiraat.crystamaehistoria.runnables;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveConfigRunnable
extends BukkitRunnable {
    public void run() {
        CrystamaeHistoria.getConfigManager().saveAll();
    }
}

