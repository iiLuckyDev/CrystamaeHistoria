/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transforms;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ConfigUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;

public class SequenceTransform
implements Transform {
    private List<Sequence> steps;

    @Override
    public void load(ConfigurationSection parameters) {
        this.steps = new ArrayList<Sequence>();
        Collection<ConfigurationSection> stepConfigurations = ConfigUtils.getNodeList(parameters, "steps");
        if (stepConfigurations != null) {
            for (ConfigurationSection stepConfig : stepConfigurations) {
                this.steps.add(new Sequence(stepConfig));
            }
        }
        Collections.reverse(this.steps);
    }

    @Override
    public double get(double t) {
        double value = 0.0;
        for (Sequence step : this.steps) {
            if (!(step.getStart() <= t)) continue;
            return step.get(t);
        }
        return value;
    }

    private static class Sequence {
        private final Transform transform;
        private final double start;

        public Sequence(ConfigurationSection configuration) {
            this.transform = Transforms.loadTransform(configuration, "transform");
            this.start = configuration.getDouble("start", 0.0);
        }

        public double getStart() {
            return this.start;
        }

        public double get(double t) {
            return this.transform.get(t);
        }
    }
}

