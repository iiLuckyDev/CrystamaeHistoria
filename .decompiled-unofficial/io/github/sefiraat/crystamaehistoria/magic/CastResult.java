/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.magic;

import lombok.Generated;

public enum CastResult {
    CAST_SUCCESS("Successful"),
    CAST_FAIL_NO_CRYSTA("Not enough Crystamae in plate"),
    CAST_FAIL_SLOT_EMPTY("No plate in slot"),
    ON_COOLDOWN("Spell on cooldown"),
    SPELL_DISABLED("This spell has been disabled");

    private static final CastResult[] cachedValues;
    private final String message;

    private CastResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.getMessage(null);
    }

    public String getMessage(String detail) {
        if (detail == null) {
            return this.message;
        }
        return this.message + ": " + detail;
    }

    @Generated
    public static CastResult[] getCachedValues() {
        return cachedValues;
    }

    static {
        cachedValues = CastResult.values();
    }
}

