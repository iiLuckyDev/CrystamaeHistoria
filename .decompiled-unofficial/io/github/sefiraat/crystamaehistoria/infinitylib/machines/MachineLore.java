/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import java.text.DecimalFormat;
import javax.annotation.Nonnull;
import lombok.Generated;

public final class MachineLore {
    private static final DecimalFormat FORMAT = new DecimalFormat("###,###,###,###,###,###.#");
    private static final double TPS = 20.0 / (double)Slimefun.getTickerTask().getTickRate();
    private static final String PREFIX = "&8\u21e8 &e\u26a1 &7";

    @Nonnull
    public static String energyPerSecond(int energy) {
        return PREFIX + MachineLore.formatEnergy(energy) + " J/s";
    }

    @Nonnull
    public static String energyBuffer(int energy) {
        return PREFIX + MachineLore.format(energy) + " J Buffer";
    }

    @Nonnull
    public static String energy(int energy) {
        return PREFIX + MachineLore.format(energy) + " J ";
    }

    @Nonnull
    public static String speed(int speed) {
        return "&8\u21e8 &e\u26a1 &7Speed: &b" + speed + "x";
    }

    @Nonnull
    public static String formatEnergy(int energy) {
        return FORMAT.format((double)energy * TPS);
    }

    @Nonnull
    public static String format(double number) {
        return FORMAT.format(number);
    }

    @Generated
    private MachineLore() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

