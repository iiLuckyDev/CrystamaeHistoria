/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationStore;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationTransform;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Particle;

public class PlotEffect
extends Effect {
    private static final String[] _variables = new String[]{"t", "i"};
    private static final Set<String> variables = new HashSet<String>(Arrays.asList(_variables));
    public Particle particle = Particle.REDSTONE;
    public String xEquation;
    public String yEquation;
    public String zEquation;
    public double xScale = 1.0;
    public double yScale = 1.0;
    public double zScale = 1.0;
    public boolean persistent = true;
    private int step = 0;

    public PlotEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 100;
    }

    @Override
    public void onRun() {
        int base;
        for (int i = base = this.persistent ? 0 : this.step; i <= this.step; ++i) {
            Location location = this.getLocation().clone();
            double xOffset = this.step;
            double yOffset = this.step;
            double zOffset = 0.0;
            if (this.xEquation != null && !this.xEquation.isEmpty()) {
                EquationTransform xTransform = EquationStore.getInstance().getTransform(this.xEquation, variables);
                xOffset = xTransform.get(i, this.maxIterations);
            }
            if (this.yEquation != null && !this.yEquation.isEmpty()) {
                EquationTransform yTransform = EquationStore.getInstance().getTransform(this.yEquation, variables);
                yOffset = yTransform.get(i, this.maxIterations);
            }
            if (this.zEquation != null && !this.zEquation.isEmpty()) {
                EquationTransform zTransform = EquationStore.getInstance().getTransform(this.zEquation, variables);
                zOffset = zTransform.get(i, this.maxIterations);
            }
            location.add(xOffset * this.xScale, yOffset * this.yScale, zOffset * this.zScale);
            this.display(this.particle, location);
        }
        ++this.step;
    }
}

