/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.slikey.exp4j.tokenizer;

import io.github.sefiraat.crystamaehistoria.slikey.exp4j.operator.Operator;
import io.github.sefiraat.crystamaehistoria.slikey.exp4j.tokenizer.Token;

public class OperatorToken
extends Token {
    private final Operator operator;

    public OperatorToken(Operator op) {
        super(2);
        if (op == null) {
            throw new IllegalArgumentException("Operator is unknown for token.");
        }
        this.operator = op;
    }

    public Operator getOperator() {
        return this.operator;
    }
}

