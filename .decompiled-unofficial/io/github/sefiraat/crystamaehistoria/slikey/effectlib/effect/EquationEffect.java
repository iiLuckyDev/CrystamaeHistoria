/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationStore;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationTransform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class EquationEffect
extends Effect {
    public Particle particle = Particle.REDSTONE;
    public String xEquation = "t";
    public String yEquation = "0";
    public String zEquation = "0";
    public String variable = "t";
    public int particles = 1;
    public String x2Equation = null;
    public String y2Equation = null;
    public String z2Equation = null;
    public String variable2 = "t2";
    public int particles2 = 0;
    public boolean orient = true;
    public boolean orientPitch = true;
    public int maxSteps = 0;
    public boolean cycleMiniStep = true;
    private EquationTransform xTransform;
    private EquationTransform yTransform;
    private EquationTransform zTransform;
    private EquationTransform x2Transform;
    private EquationTransform y2Transform;
    private EquationTransform z2Transform;
    private int step = 0;
    private int miniStep = 0;

    public EquationEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 100;
    }

    @Override
    public void reset() {
        this.step = 0;
        this.miniStep = 0;
    }

    @Override
    public void onRun() {
        if (this.xTransform == null) {
            this.xTransform = EquationStore.getInstance().getTransform(this.xEquation, this.variable);
            this.yTransform = EquationStore.getInstance().getTransform(this.yEquation, this.variable);
            this.zTransform = EquationStore.getInstance().getTransform(this.zEquation, this.variable);
            if (this.x2Equation != null && this.y2Equation != null && this.z2Equation != null && this.particles2 > 0) {
                this.x2Transform = EquationStore.getInstance().getTransform(this.x2Equation, this.variable, this.variable2);
                this.y2Transform = EquationStore.getInstance().getTransform(this.y2Equation, this.variable, this.variable2);
                this.z2Transform = EquationStore.getInstance().getTransform(this.z2Equation, this.variable, this.variable2);
            }
        }
        Location location = this.getLocation();
        boolean hasInnerEquation = this.x2Transform != null && this.y2Transform != null && this.z2Transform != null;
        for (int i = 0; i < this.particles; ++i) {
            double xValue = this.xTransform.get((double)this.step);
            double yValue = this.yTransform.get((double)this.step);
            double zValue = this.zTransform.get((double)this.step);
            Vector result = new Vector(xValue, yValue, zValue);
            if (this.orient && this.orientPitch) {
                result = VectorUtils.rotateVector(result, location);
            } else if (this.orient) {
                result = VectorUtils.rotateVector(result, location.getYaw(), 0.0f);
            }
            Location targetLocation = location.clone();
            targetLocation.add(result);
            if (hasInnerEquation) {
                for (int j = 0; j < this.particles2; ++j) {
                    double x2Value = this.x2Transform.get(this.step, this.miniStep);
                    double y2Value = this.y2Transform.get(this.step, this.miniStep);
                    double z2Value = this.z2Transform.get(this.step, this.miniStep);
                    Vector result2 = new Vector(x2Value, y2Value, z2Value);
                    if (this.orient && this.orientPitch) {
                        result2 = VectorUtils.rotateVector(result2, location);
                    } else if (this.orient) {
                        result2 = VectorUtils.rotateVector(result2, location.getYaw(), 0.0f);
                    }
                    Location target2Location = targetLocation.clone().add(result2);
                    this.display(this.particle, target2Location);
                    ++this.miniStep;
                }
                if (this.cycleMiniStep) {
                    this.miniStep = 0;
                }
            } else {
                this.display(this.particle, targetLocation);
            }
            if (this.maxSteps != 0 && this.step > this.maxSteps) {
                this.step = 0;
                break;
            }
            ++this.step;
        }
    }
}

