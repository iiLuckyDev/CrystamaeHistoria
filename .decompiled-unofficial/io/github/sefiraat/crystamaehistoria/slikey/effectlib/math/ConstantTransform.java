/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import org.bukkit.configuration.ConfigurationSection;

public class ConstantTransform
implements Transform {
    private double value;

    public ConstantTransform() {
    }

    public ConstantTransform(double value) {
        this.value = value;
    }

    @Override
    public void load(ConfigurationSection parameters) {
        this.value = parameters.getDouble("value");
    }

    @Override
    public double get(double t) {
        return this.value;
    }
}

