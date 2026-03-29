/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Generated;

public enum BlockRank {
    SME(100.0, ThemeType.RANK_BLOCK_SME),
    MASTER_OF(90.0, ThemeType.RANK_BLOCK_MASTER_OF),
    EXPERT_OF(70.0, ThemeType.RANK_BLOCK_EXPERT_OF),
    RESEARCHED(50.0, ThemeType.RANK_BLOCK_RESEARCHED),
    DETAILED(30.0, ThemeType.RANK_BLOCK_DETAILED),
    KNOWN(20.0, ThemeType.RANK_BLOCK_KNOWN),
    HEARD_OF(10.0, ThemeType.RANK_BLOCK_HEARD_OF),
    UNKNOWN(0.0, ThemeType.RANK_BLOCK_UNKNOWN);

    private final double numberRequired;
    private final ThemeType theme;

    private BlockRank(double numberRequired, ThemeType themeType) {
        this.numberRequired = numberRequired;
        this.theme = themeType;
    }

    public static BlockRank getByAmount(double percent) {
        if (percent >= BlockRank.SME.numberRequired) {
            return SME;
        }
        if (percent >= BlockRank.MASTER_OF.numberRequired) {
            return MASTER_OF;
        }
        if (percent >= BlockRank.EXPERT_OF.numberRequired) {
            return EXPERT_OF;
        }
        if (percent >= BlockRank.RESEARCHED.numberRequired) {
            return RESEARCHED;
        }
        if (percent >= BlockRank.DETAILED.numberRequired) {
            return DETAILED;
        }
        if (percent >= BlockRank.KNOWN.numberRequired) {
            return KNOWN;
        }
        if (percent >= BlockRank.HEARD_OF.numberRequired) {
            return HEARD_OF;
        }
        return UNKNOWN;
    }

    @Generated
    public ThemeType getTheme() {
        return this.theme;
    }
}

