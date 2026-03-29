/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public final class ParticleUtils {
    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, Particle particle, double rangeRadius) {
        ParticleUtils.displayParticleEffect(entity.getLocation(), particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Location location, Particle particle, double rangeRadius, int numberOfParticles) {
        for (int i = 0; i < numberOfParticles; ++i) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(particle, location.clone().add(x, y, z), 1);
        }
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, Particle particle, double rangeRadius, int numberOfParticles) {
        ParticleUtils.displayParticleEffect(entity.getLocation().clone().add(0.0, 1.0, 0.0), particle, rangeRadius, numberOfParticles);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Location location, Particle particle, double rangeRadius) {
        ParticleUtils.displayParticleEffect(location, particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, double rangeRadius, int numberOfParticles, Particle.DustOptions dustOptions) {
        ParticleUtils.displayParticleEffect(entity.getLocation(), rangeRadius, numberOfParticles, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Location location, double rangeRadius, int numberOfParticles, Particle.DustOptions dustOptions) {
        for (int i = 0; i < numberOfParticles; ++i) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(Particle.DUST, location.clone().add(x, y, z), 1, (Object)dustOptions);
        }
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleEffect(Entity entity, double rangeRadius, Particle.DustOptions dustOptions) {
        ParticleUtils.displayParticleEffect(entity.getLocation(), rangeRadius, 5, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void drawLine(Particle particle, Location start, Location end, double space) {
        ParticleUtils.drawLine(particle, start, end, space, null);
    }

    @ParametersAreNonnullByDefault
    public static void drawLine(Particle particle, Location start, Location end, double space, @Nullable Particle.DustOptions dustOptions) {
        double distance = start.distance(end);
        Vector startVector = start.toVector();
        Vector endVector = end.toVector();
        Vector vector = endVector.clone().subtract(startVector).normalize().multiply(space);
        for (double currentPoint = 0.0; currentPoint < distance; currentPoint += space) {
            if (dustOptions != null) {
                start.getWorld().spawnParticle(particle, startVector.getX(), startVector.getY(), startVector.getZ(), 1, (Object)dustOptions);
            } else {
                start.getWorld().spawnParticle(particle, startVector.getX(), startVector.getY(), startVector.getZ(), 1);
            }
            startVector.add(vector);
        }
    }

    @ParametersAreNonnullByDefault
    public static void drawLine(@Nullable Particle.DustOptions dustOptions, Location start, Location end, double space) {
        ParticleUtils.drawLine(Particle.DUST, start, end, space, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static List<Location> getLine(Location start, Location end, double space) {
        double distance = start.distance(end);
        Vector startVector = start.toVector();
        Vector endVector = end.toVector();
        Vector vector = endVector.clone().subtract(startVector).normalize().multiply(space);
        ArrayList<Location> locations = new ArrayList<Location>();
        for (double currentPoint = 0.0; currentPoint < distance; currentPoint += space) {
            locations.add(new Location(start.getWorld(), startVector.getX(), startVector.getY(), startVector.getZ()));
            startVector.add(vector);
        }
        return locations;
    }

    @ParametersAreNonnullByDefault
    public static void drawCube(Particle particle, Location corner1, Location corner2, double space) {
        ParticleUtils.drawCube(particle, corner1, corner2, space, null);
    }

    @ParametersAreNonnullByDefault
    public static void drawCube(Particle particle, Location corner1, Location corner2, double particleDistance, @Nullable Particle.DustOptions dustOptions) {
        World world = corner1.getWorld();
        double minX = Math.min(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        double maxX = Math.max(corner1.getX(), corner2.getX());
        double maxY = Math.max(corner1.getY(), corner2.getY());
        double maxZ = Math.max(corner1.getZ(), corner2.getZ());
        for (double x = minX; x <= maxX; x += particleDistance) {
            for (double y = minY; y <= maxY; y += particleDistance) {
                for (double z = minZ; z <= maxZ; z += particleDistance) {
                    int components = 0;
                    if (x == minX || x == maxX) {
                        ++components;
                    }
                    if (y == minY || y == maxY) {
                        ++components;
                    }
                    if (z == minZ || z == maxZ) {
                        ++components;
                    }
                    if (components < 2) continue;
                    if (dustOptions != null) {
                        world.spawnParticle(particle, x, y, z, 1, (Object)dustOptions);
                        continue;
                    }
                    world.spawnParticle(particle, x, y, z, 1);
                }
            }
        }
    }

    @ParametersAreNonnullByDefault
    public static void drawCube(@Nullable Particle.DustOptions dustOptions, Location corner1, Location corner2, double space) {
        ParticleUtils.drawCube(Particle.DUST, corner1, corner2, space, dustOptions);
    }

    @Generated
    private ParticleUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

