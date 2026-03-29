/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Generated;

public enum GildingRank {
    OWNER(100.0, ThemeType.RANK_GILDING_OWNER),
    MANAGER(90.0, ThemeType.RANK_GILDING_MANAGER),
    CHIEF(70.0, ThemeType.RANK_GILDING_CHIEF),
    EXECUTIVE(50.0, ThemeType.RANK_GILDING_EXECUTIVE),
    OFFICER(30.0, ThemeType.RANK_GILDING_OFFICER),
    SECRETARY(20.0, ThemeType.RANK_GILDING_SECRETARY),
    MEMBER(10.0, ThemeType.RANK_GILDING_MEMBER),
    NOVICE(0.0, ThemeType.RANK_GILDING_NOVICE);

    private final double percentRequired;
    private final ThemeType theme;

    private GildingRank(double percentRequired, ThemeType themeType) {
        this.percentRequired = percentRequired;
        this.theme = themeType;
    }

    public static GildingRank getByPercent(double percent) {
        if (percent >= GildingRank.OWNER.percentRequired) {
            return OWNER;
        }
        if (percent >= GildingRank.MANAGER.percentRequired) {
            return MANAGER;
        }
        if (percent >= GildingRank.CHIEF.percentRequired) {
            return CHIEF;
        }
        if (percent >= GildingRank.EXECUTIVE.percentRequired) {
            return EXECUTIVE;
        }
        if (percent >= GildingRank.OFFICER.percentRequired) {
            return OFFICER;
        }
        if (percent >= GildingRank.SECRETARY.percentRequired) {
            return SECRETARY;
        }
        if (percent >= GildingRank.MEMBER.percentRequired) {
            return MEMBER;
        }
        return NOVICE;
    }

    @Generated
    public ThemeType getTheme() {
        return this.theme;
    }
}

