/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationTransform;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class EquationStore {
    private static final String DEFAULT_VARIABLE = "x";
    private static EquationStore instance;
    private Map<String, EquationTransform> transforms = new HashMap<String, EquationTransform>();

    public EquationTransform getTransform(String equation) {
        return this.getTransform(equation, DEFAULT_VARIABLE);
    }

    public EquationTransform getTransform(String equation, String variable) {
        EquationTransform transform = this.transforms.get(equation);
        if (transform == null) {
            transform = new EquationTransform(equation, variable);
            this.transforms.put(equation, transform);
        }
        return transform;
    }

    public EquationTransform getTransform(String equation, String ... variables) {
        String equationKey = equation + ":" + StringUtils.join((Object[])variables, (String)",");
        EquationTransform transform = this.transforms.get(equationKey);
        if (transform == null) {
            transform = new EquationTransform(equation, variables);
            this.transforms.put(equationKey, transform);
        }
        return transform;
    }

    public EquationTransform getTransform(String equation, Collection<String> variables) {
        String equationKey = equation + ":" + StringUtils.join(variables, (String)",");
        EquationTransform transform = this.transforms.get(equationKey);
        if (transform == null) {
            transform = new EquationTransform(equation, variables);
            this.transforms.put(equationKey, transform);
        }
        return transform;
    }

    public static void clear() {
        if (instance != null) {
            EquationStore.instance.transforms.clear();
        }
    }

    public static EquationStore getInstance() {
        if (instance == null) {
            instance = new EquationStore();
        }
        return instance;
    }
}

