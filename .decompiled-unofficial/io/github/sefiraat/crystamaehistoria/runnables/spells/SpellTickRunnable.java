/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.scheduler.BukkitRunnable
 */
package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.scheduler.BukkitRunnable;

public class SpellTickRunnable
extends BukkitRunnable {
    private final CastInformation castInformation;
    private int numberOfRuns;

    @ParametersAreNonnullByDefault
    public SpellTickRunnable(CastInformation castInformation, int numberOfRuns) {
        this.castInformation = castInformation;
        this.numberOfRuns = numberOfRuns;
    }

    public void run() {
        if (this.numberOfRuns <= 0) {
            this.castInformation.runAfterTicksEvent();
            this.cancel();
        } else {
            this.castInformation.runTickEvent();
        }
        --this.numberOfRuns;
    }

    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        CrystamaeHistoria.getSpellMemory().getTickingCastables().remove((Object)this);
    }
}

