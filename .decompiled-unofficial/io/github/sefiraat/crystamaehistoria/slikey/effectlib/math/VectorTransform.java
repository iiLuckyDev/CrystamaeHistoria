/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transforms;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

public class VectorTransform {
    private Transform xTransform;
    private Transform yTransform;
    private Transform zTransform;
    private boolean orient;

    public VectorTransform(ConfigurationSection configuration) {
        this.xTransform = Transforms.loadTransform(configuration, "x");
        this.yTransform = Transforms.loadTransform(configuration, "y");
        this.zTransform = Transforms.loadTransform(configuration, "z");
        this.orient = configuration.getBoolean("orient", true);
    }

    public Vector get(Location source, double t) {
        double xValue = this.xTransform.get(t);
        double yValue = this.yTransform.get(t);
        double zValue = this.zTransform.get(t);
        Vector result = new Vector(xValue, yValue, zValue);
        if (this.orient && source != null) {
            result = VectorUtils.rotateVector(result, source);
        }
        return result;
    }
}

