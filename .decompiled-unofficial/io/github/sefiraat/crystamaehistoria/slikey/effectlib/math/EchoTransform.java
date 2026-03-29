/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import org.bukkit.configuration.ConfigurationSection;

public class EchoTransform
implements Transform {
    @Override
    public void load(ConfigurationSection parameters) {
    }

    @Override
    public double get(double t) {
        return t;
    }
}

