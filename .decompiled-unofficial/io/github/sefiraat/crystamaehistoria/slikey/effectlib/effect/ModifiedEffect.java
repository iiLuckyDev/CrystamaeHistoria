/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  org.bukkit.Location
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import com.google.common.base.CaseFormat;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationStore;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationTransform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

public class ModifiedEffect
extends Effect {
    private static final String[] _variables = new String[]{"t", "i", "a", "b"};
    private static final List<String> variables = Arrays.asList(_variables);
    public ConfigurationSection effect;
    public String effectClass;
    public String xEquation = null;
    public String yEquation = null;
    public String zEquation = null;
    public double variableA;
    public double variableB;
    public boolean orient = true;
    public boolean orientPitch = false;
    public Map<String, String> parameters = new HashMap<String, String>();
    private boolean initialized = false;
    private Effect innerEffect;
    private Map<Field, EquationTransform> parameterTransforms = new HashMap<Field, EquationTransform>();
    private int step = 0;
    private EquationTransform xTransform;
    private EquationTransform yTransform;
    private EquationTransform zTransform;
    private Vector previousOffset;

    public ModifiedEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 1;
        this.iterations = 100;
    }

    @Override
    public void reset() {
        this.step = 0;
        if (this.innerEffect != null) {
            this.innerEffect.prepare();
        }
    }

    @Override
    public void onDone() {
        if (this.innerEffect != null) {
            this.innerEffect.onDone();
        }
    }

    @Override
    public void onRun() {
        if (!this.initialized) {
            this.initialized = true;
            if (this.effect == null) {
                this.effectManager.onError("ModifiedEffect missing inner effect configuration");
                this.cancel();
                return;
            }
            if (this.effectClass == null) {
                this.effectClass = this.effect.getString("class");
            }
            if (this.effectClass == null) {
                this.effectManager.onError("ModifiedEffect missing inner effect class property");
                this.cancel();
                return;
            }
            this.innerEffect = this.effectManager.getEffect(this.effectClass, this.effect, this.origin, this.target, null, this.targetPlayer);
            if (this.innerEffect == null) {
                this.cancel();
                return;
            }
            this.innerEffect.material = this.material;
            this.innerEffect.materialData = this.materialData;
            for (Map.Entry<String, String> entry : this.parameters.entrySet()) {
                EquationTransform transform;
                Exception ex;
                String equation = entry.getValue();
                String fieldName = entry.getKey();
                if (fieldName.contains("-")) {
                    fieldName = fieldName.replace("-", "_");
                }
                if (fieldName.contains("_")) {
                    fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, fieldName);
                }
                if ((ex = (transform = EquationStore.getInstance().getTransform(equation, variables)).getException()) != null) {
                    this.effectManager.onError("Error parsing equation: " + equation, ex);
                    continue;
                }
                try {
                    Field field = this.innerEffect.getClass().getField(fieldName);
                    this.parameterTransforms.put(field, transform);
                }
                catch (Exception ex2) {
                    this.effectManager.onError("Error binding to field: " + fieldName + " of effect class " + this.effectClass, ex2);
                }
            }
            this.innerEffect.prepare();
            if (this.xEquation != null) {
                this.xTransform = EquationStore.getInstance().getTransform(this.xEquation, _variables);
            }
            if (this.yEquation != null) {
                this.yTransform = EquationStore.getInstance().getTransform(this.yEquation, _variables);
            }
            if (this.zEquation != null) {
                this.zTransform = EquationStore.getInstance().getTransform(this.zEquation, _variables);
            }
        }
        if (this.innerEffect == null) {
            this.cancel();
            return;
        }
        if (this.origin != null && this.xTransform != null || this.yTransform != null || this.zTransform != null) {
            Vector offset = new Vector(this.xTransform == null ? 0.0 : this.xTransform.get(this.step, this.maxIterations, this.variableA, this.variableB), this.yTransform == null ? 0.0 : this.yTransform.get(this.step, this.maxIterations, this.variableA, this.variableB), this.zTransform == null ? 0.0 : this.zTransform.get(this.step, this.maxIterations, this.variableA, this.variableB));
            if (this.previousOffset != null) {
                offset.subtract(this.previousOffset);
            } else {
                this.previousOffset = new Vector();
            }
            Location location = this.getLocation();
            if (this.orient && this.orientPitch) {
                offset = VectorUtils.rotateVector(offset, location);
            } else if (this.orient) {
                offset = VectorUtils.rotateVector(offset, location.getYaw(), 0.0f);
            }
            this.origin.addOffset(offset);
            this.previousOffset.add(offset);
        }
        for (Map.Entry<Field, EquationTransform> entry : this.parameterTransforms.entrySet()) {
            double value = entry.getValue().get(this.step, this.maxIterations, this.variableA, this.variableB);
            try {
                Field field = entry.getKey();
                if (field.getType().equals(Double.class) || field.getType().equals(Double.TYPE)) {
                    entry.getKey().set(this.innerEffect, value);
                    continue;
                }
                if (field.getType().equals(Integer.class) || field.getType().equals(Integer.TYPE)) {
                    entry.getKey().set(this.innerEffect, (int)value);
                    continue;
                }
                if (field.getType().equals(Float.class) || field.getType().equals(Float.TYPE)) {
                    entry.getKey().set(this.innerEffect, Float.valueOf((float)value));
                    continue;
                }
                if (field.getType().equals(Short.class) || field.getType().equals(Short.TYPE)) {
                    entry.getKey().set(this.innerEffect, (short)value);
                    continue;
                }
                if (field.getType().equals(Byte.class) || field.getType().equals(Byte.TYPE)) {
                    entry.getKey().set(this.innerEffect, (byte)value);
                    continue;
                }
                this.effectManager.onError("Can't assign property " + entry.getKey().getName() + " of effect class " + this.effectClass + " of type " + field.getType().getName());
                this.cancel();
                return;
            }
            catch (Exception ex) {
                this.effectManager.onError("Error assigning to : " + entry.getKey().getName() + " of effect class " + this.effectClass, ex);
                this.cancel();
                return;
            }
        }
        try {
            this.innerEffect.reloadParameters();
            this.innerEffect.onRun();
        }
        catch (Exception ex) {
            this.innerEffect.onDone();
            this.effectManager.onError(ex);
        }
        ++this.step;
    }

    public Effect getInnerEffect() {
        return this.innerEffect;
    }
}

