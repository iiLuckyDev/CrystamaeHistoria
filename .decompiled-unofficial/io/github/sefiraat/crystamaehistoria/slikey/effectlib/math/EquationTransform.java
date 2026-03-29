/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.math;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.EquationVariableProvider;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.math.Transform;
import io.github.sefiraat.crystamaehistoria.slikey.exp4j.Expression;
import io.github.sefiraat.crystamaehistoria.slikey.exp4j.ExpressionBuilder;
import io.github.sefiraat.crystamaehistoria.slikey.exp4j.VariableProvider;
import io.github.sefiraat.crystamaehistoria.slikey.exp4j.function.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import org.bukkit.configuration.ConfigurationSection;

public class EquationTransform
implements Transform,
VariableProvider {
    private Expression expression;
    private static Function randFunction;
    private static Function probabilityFunction;
    private static Function minFunction;
    private static Function maxFunction;
    private static Function selectFunction;
    private final Collection<String> inputVariables;
    private EquationVariableProvider variableProvider;
    private Exception exception;

    @Override
    public void load(ConfigurationSection parameters) {
        this.setEquation(parameters.getString("equation", ""));
    }

    public EquationTransform() {
        this.inputVariables = new ArrayList<String>();
    }

    public EquationTransform(String equation) {
        this(equation, "t");
    }

    public EquationTransform(String equation, String inputVariable) {
        this.inputVariables = new ArrayList<String>();
        this.inputVariables.add(inputVariable);
        this.setEquation(equation);
    }

    public EquationTransform(String equation, String ... inputVariables) {
        this.inputVariables = new ArrayList<String>();
        for (String inputVariable : inputVariables) {
            this.inputVariables.add(inputVariable);
        }
        this.setEquation(equation);
    }

    public EquationTransform(String equation, Collection<String> inputVariables) {
        this.inputVariables = inputVariables;
        this.setEquation(equation);
    }

    private void checkCustomFunctions() {
        if (randFunction == null) {
            randFunction = new Function("rand", 2){
                private Random random;
                {
                    this.random = new Random();
                }

                @Override
                public double apply(double ... args) {
                    return this.random.nextDouble() * (args[1] - args[0]) + args[0];
                }
            };
        }
        if (probabilityFunction == null) {
            probabilityFunction = new Function("prob", 3){
                private Random random;
                {
                    this.random = new Random();
                }

                @Override
                public double apply(double ... args) {
                    return this.random.nextDouble() < args[0] ? args[1] : args[2];
                }
            };
        }
        if (minFunction == null) {
            minFunction = new Function("min", 2){

                @Override
                public double apply(double ... args) {
                    return Math.min(args[0], args[1]);
                }
            };
        }
        if (maxFunction == null) {
            maxFunction = new Function("max", 2){

                @Override
                public double apply(double ... args) {
                    return Math.max(args[0], args[1]);
                }
            };
        }
        if (selectFunction == null) {
            selectFunction = new Function("select", 4){

                @Override
                public double apply(double ... args) {
                    if (args[0] < 0.0) {
                        return args[1];
                    }
                    if (args[0] == 0.0) {
                        return args[2];
                    }
                    return args[3];
                }
            };
        }
    }

    public boolean setEquation(String equation) {
        try {
            this.checkCustomFunctions();
            this.exception = null;
            this.expression = new ExpressionBuilder(equation).function(randFunction).function(probabilityFunction).function(minFunction).function(maxFunction).function(selectFunction).variables(new HashSet<String>(this.inputVariables)).build();
            this.expression.setVariableProvider(this);
        }
        catch (Exception ex) {
            this.expression = null;
            this.exception = ex;
        }
        return this.exception == null;
    }

    @Override
    public synchronized double get(double t) {
        if (this.expression == null) {
            return 0.0;
        }
        for (String inputVariable : this.inputVariables) {
            this.expression.setVariable(inputVariable, t);
        }
        return this.get();
    }

    public synchronized double get(double ... t) {
        if (this.expression == null) {
            return 0.0;
        }
        int index = 0;
        for (String inputVariable : this.inputVariables) {
            this.expression.setVariable(inputVariable, t[index]);
            if (index >= t.length - 1) continue;
            ++index;
        }
        return this.get();
    }

    public void addVariable(String key) {
        this.inputVariables.add(key);
    }

    public void setVariable(String key, double value) {
        if (this.expression != null) {
            this.expression.setVariable(key, value);
        }
    }

    public double get() {
        if (this.expression == null) {
            return Double.NaN;
        }
        double value = Double.NaN;
        try {
            this.exception = null;
            value = this.expression.evaluate();
        }
        catch (Exception ex) {
            this.exception = ex;
        }
        return value;
    }

    public Exception getException() {
        return this.exception;
    }

    public boolean isValid() {
        return this.exception == null;
    }

    public Collection<String> getParameters() {
        return this.inputVariables;
    }

    public void setVariableProvider(EquationVariableProvider provider) {
        this.variableProvider = provider;
    }

    @Override
    public Double getVariable(String variable) {
        return this.variableProvider == null ? null : this.variableProvider.getVariable(variable);
    }
}

