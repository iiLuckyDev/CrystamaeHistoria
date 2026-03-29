/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Generated;

public enum SpellRank {
    GRANDMASTER_MAGI(100.0, ThemeType.RANK_SPELL_GRANDMASTER_MAGI),
    MASTER_MAGI(90.0, ThemeType.RANK_SPELL_MASTER_MAGI),
    MAGI(70.0, ThemeType.RANK_SPELL_MAGI),
    SORCERER(50.0, ThemeType.RANK_SPELL_SORCERER),
    CONJURER(30.0, ThemeType.RANK_SPELL_CONJURER),
    WIZARD(20.0, ThemeType.RANK_SPELL_WIZARD),
    MAGE(10.0, ThemeType.RANK_SPELL_MAGE),
    APPRENTICE(0.0, ThemeType.RANK_SPELL_APPRENTICE);

    private final double percentRequired;
    private final ThemeType theme;

    private SpellRank(double percentRequired, ThemeType themeType) {
        this.percentRequired = percentRequired;
        this.theme = themeType;
    }

    public static SpellRank getByPercent(double percent) {
        if (percent >= SpellRank.GRANDMASTER_MAGI.percentRequired) {
            return GRANDMASTER_MAGI;
        }
        if (percent >= SpellRank.MASTER_MAGI.percentRequired) {
            return MASTER_MAGI;
        }
        if (percent >= SpellRank.MAGI.percentRequired) {
            return MAGI;
        }
        if (percent >= SpellRank.SORCERER.percentRequired) {
            return SORCERER;
        }
        if (percent >= SpellRank.CONJURER.percentRequired) {
            return CONJURER;
        }
        if (percent >= SpellRank.WIZARD.percentRequired) {
            return WIZARD;
        }
        if (percent >= SpellRank.MAGE.percentRequired) {
            return MAGE;
        }
        return APPRENTICE;
    }

    @Generated
    public ThemeType getTheme() {
        return this.theme;
    }
}

