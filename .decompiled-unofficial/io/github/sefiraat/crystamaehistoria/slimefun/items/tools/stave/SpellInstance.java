/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;

public class SpellInstance {
    @Nullable
    private final SpellType spell;
    private int crysta;
    private long nextCast;

    @ParametersAreNonnullByDefault
    public SpellInstance(@Nullable SpellType spell, int crysta, long nextCast) {
        this.spell = spell;
        this.crysta = crysta;
        this.nextCast = nextCast;
    }

    @Nullable
    @Generated
    public SpellType getSpell() {
        return this.spell;
    }

    @Generated
    public int getCrysta() {
        return this.crysta;
    }

    @Generated
    public void setCrysta(int crysta) {
        this.crysta = crysta;
    }

    @Generated
    public long getNextCast() {
        return this.nextCast;
    }

    @Generated
    public void setNextCast(long nextCast) {
        this.nextCast = nextCast;
    }
}

