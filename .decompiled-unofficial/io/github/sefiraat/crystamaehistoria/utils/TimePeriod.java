/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.World
 *  org.bukkit.World$Environment
 */
package io.github.sefiraat.crystamaehistoria.utils;

import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.World;

public enum TimePeriod {
    SUNRISE(23000L, 23999L),
    DAY(24000L, 11999L),
    SUNSET(12000L, 12999L),
    NIGHT(13000L, 22999L),
    WAKE_UP(24000L, 24000L),
    BED_TIME_RAIN(12010L, 12010L),
    BED_TIME_CLEAR(12542L, 12542L),
    MOON_HIDE(167L, 167L),
    MOON_SHOW(11834L, 11834L),
    VILLAGER_WORK(2000L, 8999L),
    VILLAGER_SOCIALISE(9000L, 11999L),
    VILLAGER_BED_TIME(12000L, 23999L),
    SKY_LIGHT_WAX_CLEAR(22331L, 23961L),
    SKY_LIGHT_WAX_RAIN(22331L, 23992L),
    SKY_LIGHT_WANE_CLEAR(12040L, 13670L),
    SKY_LIGHT_WANE_RAIN(12010L, 13670L),
    MOB_SPAWN_CLEAR(13188L, 22812L),
    MOB_SPAWN_RAIN(12969L, 23031L);

    private final long start;
    private final long end;

    private TimePeriod(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @ParametersAreNonnullByDefault
    public static boolean isDay(World world) {
        return TimePeriod.isDay(world.getTime());
    }

    public static boolean isDay(long time) {
        return time < 13000L || time > 24000L;
    }

    @ParametersAreNonnullByDefault
    public static boolean isNight(World world) {
        return TimePeriod.isNight(world.getTime());
    }

    public static boolean isNight(long time) {
        return !TimePeriod.isDay(time);
    }

    @ParametersAreNonnullByDefault
    public static boolean isActive(World world, TimePeriod timePeriod) {
        return TimePeriod.isActive(world.getTime(), timePeriod);
    }

    @ParametersAreNonnullByDefault
    public static boolean isActive(long time, TimePeriod timePeriod) {
        return time >= timePeriod.getStart() && time <= timePeriod.getEnd();
    }

    @ParametersAreNonnullByDefault
    public static boolean villagersAwake(World world) {
        return TimePeriod.villagersAwake(world.getTime());
    }

    public static boolean villagersAwake(long time) {
        return time >= WAKE_UP.getStart() && time <= VILLAGER_BED_TIME.getEnd();
    }

    @ParametersAreNonnullByDefault
    public static boolean moonOut(World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return TimePeriod.moonOut(world.getTime());
        }
        return false;
    }

    public static boolean moonOut(long time) {
        return time >= MOON_SHOW.getStart() && time <= MOON_HIDE.getEnd();
    }

    @ParametersAreNonnullByDefault
    public static boolean naturalMobsCanSpawn(World world) {
        long time = world.getTime();
        return world.isClearWeather() ? TimePeriod.naturalMobsCanSpawn(time, false) : TimePeriod.naturalMobsCanSpawn(time, true);
    }

    public static boolean naturalMobsCanSpawn(long time, boolean rain) {
        return rain ? time >= MOB_SPAWN_RAIN.getStart() && time <= MOB_SPAWN_RAIN.getEnd() : time >= MOB_SPAWN_CLEAR.getStart() && time <= MOB_SPAWN_CLEAR.getEnd();
    }

    @ParametersAreNonnullByDefault
    public static boolean isDark(World world) {
        return !TimePeriod.isLight(world);
    }

    @ParametersAreNonnullByDefault
    public static boolean isLight(World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return TimePeriod.isDay(world.getTime());
        }
        return false;
    }

    @Generated
    public long getStart() {
        return this.start;
    }

    @Generated
    public long getEnd() {
        return this.end;
    }
}

