/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transforms;
import org.bukkit.configuration.ConfigurationSection;

public class dSinTransform
implements Transform {
    private Transform a;
    private Transform b;
    private Transform c;

    @Override
    public void load(ConfigurationSection parameters) {
        this.a = Transforms.loadTransform(parameters, "a");
        this.b = Transforms.loadTransform(parameters, "b");
        this.c = Transforms.loadTransform(parameters, "c");
    }

    @Override
    public double get(double t) {
        double bValue = this.b.get(t);
        return this.a.get(t) * bValue * Math.cos(bValue * (t + this.c.get(t)));
    }
}

