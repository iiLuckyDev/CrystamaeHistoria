/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.common;

import lombok.Generated;

public final class StringUtils {
    public static boolean isBlank(String string) {
        if (string == null || string.length() == 0) {
            return true;
        }
        for (char chr : string.toCharArray()) {
            if (Character.isWhitespace(chr)) continue;
            return false;
        }
        return true;
    }

    @Generated
    private StringUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

