/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.slikey.exp4j.tokenizer;

import io.github.sefiraat.crystamaehistoria.slikey.exp4j.function.Function;
import io.github.sefiraat.crystamaehistoria.slikey.exp4j.tokenizer.Token;

public class FunctionToken
extends Token {
    private final Function function;

    public FunctionToken(Function function) {
        super(3);
        this.function = function;
    }

    public Function getFunction() {
        return this.function;
    }
}

