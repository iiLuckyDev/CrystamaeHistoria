/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Bukkit
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.common;

import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;

@ParametersAreNonnullByDefault
public final class Scheduler {
    public static void run(Runnable runnable) {
        Bukkit.getScheduler().runTask(AbstractAddon.instance(), runnable);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(AbstractAddon.instance(), runnable);
    }

    public static void run(int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskLater(AbstractAddon.instance(), runnable, (long)delayTicks);
    }

    public static void runAsync(int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(AbstractAddon.instance(), runnable, (long)delayTicks);
    }

    public static void repeat(int intervalTicks, Runnable runnable) {
        Scheduler.repeat(intervalTicks, 1, runnable);
    }

    public static void repeatAsync(int intervalTicks, Runnable runnable) {
        Scheduler.repeatAsync(intervalTicks, 1, runnable);
    }

    public static void repeat(int intervalTicks, int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskTimer(AbstractAddon.instance(), runnable, (long)delayTicks, (long)Math.max(1, intervalTicks));
    }

    public static void repeatAsync(int intervalTicks, int delayTicks, Runnable runnable) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(AbstractAddon.instance(), runnable, (long)delayTicks, (long)Math.max(1, intervalTicks));
    }

    @Generated
    private Scheduler() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

