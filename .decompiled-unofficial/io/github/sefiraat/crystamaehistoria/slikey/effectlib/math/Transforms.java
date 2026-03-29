/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.ConstantTransform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EchoTransform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationStore;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationTransform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ConfigUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;

public class Transforms {
    private static final String TRANSFORM_BUILTIN_CLASSPATH = "io.github.sefiraat.crystamaehistoria.slikey.effectlib.math";
    private static Map<String, Class<?>> transformClasses = new HashMap();
    private static List<EffectManager> effectManagers = EffectManager.getManagers();

    public static Transform loadTransform(ConfigurationSection base, String value) {
        if (base.isConfigurationSection(value)) {
            return Transforms.loadTransform(ConfigUtils.getConfigurationSection(base, value));
        }
        if (base.isDouble(value) || base.isInt(value)) {
            return new ConstantTransform(base.getDouble(value));
        }
        if (base.isString(value)) {
            String equation = base.getString(value);
            if (equation.equalsIgnoreCase("t") || equation.equalsIgnoreCase("time")) {
                return new EchoTransform();
            }
            EquationTransform transform = EquationStore.getInstance().getTransform(equation, "t");
            Exception ex = transform.getException();
            if (ex != null && !effectManagers.isEmpty()) {
                for (EffectManager effectManager : effectManagers) {
                    if (effectManager == null) continue;
                    effectManager.onError("Error parsing equation: " + equation, ex);
                    break;
                }
            }
            return transform;
        }
        return new ConstantTransform(0.0);
    }

    public static Collection<Transform> loadTransformList(ConfigurationSection base, String value) {
        Collection<ConfigurationSection> transformConfigs = ConfigUtils.getNodeList(base, value);
        ArrayList<Transform> transforms = new ArrayList<Transform>();
        if (transformConfigs == null) {
            return transforms;
        }
        for (ConfigurationSection transformConfig : transformConfigs) {
            transforms.add(Transforms.loadTransform(transformConfig));
        }
        return transforms;
    }

    public static Transform loadTransform(ConfigurationSection parameters) {
        Transform transform;
        block9: {
            transform = null;
            if (parameters != null && parameters.contains("class")) {
                String className = parameters.getString("class");
                try {
                    Class<?> genericClass;
                    if (!className.contains(".")) {
                        className = "io.github.sefiraat.crystamaehistoria.slikey.effectlib.math." + className;
                    }
                    if ((genericClass = transformClasses.get(className)) == null) {
                        try {
                            genericClass = Class.forName(className + "Transform");
                        }
                        catch (Exception ex) {
                            genericClass = Class.forName(className);
                        }
                        if (!Transform.class.isAssignableFrom(genericClass)) {
                            throw new Exception("Must extend Transform");
                        }
                        transformClasses.put(className, genericClass);
                    }
                    Class<?> transformClass = genericClass;
                    transform = (Transform)transformClass.newInstance();
                    parameters.set("class", null);
                    transform.load(parameters);
                }
                catch (Exception ex) {
                    if (effectManagers.isEmpty()) break block9;
                    for (EffectManager effectManager : effectManagers) {
                        if (effectManager == null) continue;
                        effectManager.onError("Error loading class " + className, ex);
                        break;
                    }
                }
            }
        }
        return transform == null ? new ConstantTransform(0.0) : transform;
    }
}

