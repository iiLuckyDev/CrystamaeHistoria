/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public final class RandomUtils {
    public static final Random random = ThreadLocalRandom.current();

    private RandomUtils() {
    }

    public static Vector getRandomVector() {
        double x = random.nextDouble() * 2.0 - 1.0;
        double y = random.nextDouble() * 2.0 - 1.0;
        double z = random.nextDouble() * 2.0 - 1.0;
        return new Vector(x, y, z).normalize();
    }

    public static Vector getRandomFlatVector() {
        double x = random.nextDouble() * 2.0 - 1.0;
        double z = random.nextDouble() * 2.0 - 1.0;
        return new Vector(x, 0.0, z);
    }

    public static Vector getRandomCircleVector() {
        double rnd = random.nextDouble() * 2.0 * Math.PI;
        double x = Math.cos(rnd);
        double z = Math.sin(rnd);
        return new Vector(x, 0.0, z);
    }

    public static Material getRandomMaterial(Material[] materials) {
        return materials[random.nextInt(materials.length)];
    }

    public static double getRandomAngle() {
        return random.nextDouble() * 2.0 * Math.PI;
    }

    public static boolean checkProbability(double probability) {
        return probability >= 1.0 || random.nextDouble() < probability;
    }
}

