/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.infinitylib;

import lombok.Generated;

public final class InfinityLib {
    public static final String VERSION = "${project.version}";
    public static final String PACKAGE = InfinityLib.class.getPackage().getName();
    public static final String ADDON_PACKAGE = PACKAGE.substring(0, PACKAGE.lastIndexOf(46));

    @Generated
    private InfinityLib() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

