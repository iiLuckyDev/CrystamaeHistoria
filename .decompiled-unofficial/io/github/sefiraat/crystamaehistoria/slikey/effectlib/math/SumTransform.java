/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transforms;
import java.util.Collection;
import org.bukkit.configuration.ConfigurationSection;

public class SumTransform
implements Transform {
    private Collection<Transform> inputs;

    @Override
    public void load(ConfigurationSection parameters) {
        this.inputs = Transforms.loadTransformList(parameters, "inputs");
    }

    @Override
    public double get(double t) {
        double value = 0.0;
        for (Transform transform : this.inputs) {
            value += transform.get(t);
        }
        return value;
    }
}

