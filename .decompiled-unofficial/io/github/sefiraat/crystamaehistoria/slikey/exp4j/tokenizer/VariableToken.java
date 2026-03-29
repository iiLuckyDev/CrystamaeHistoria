/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.slikey.exp4j.tokenizer;

import io.github.sefiraat.crystamaehistoria.slikey.exp4j.tokenizer.Token;

public class VariableToken
extends Token {
    private final String name;

    public String getName() {
        return this.name;
    }

    public VariableToken(String name) {
        super(6);
        this.name = name;
    }
}

