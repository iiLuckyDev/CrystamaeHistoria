/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.player;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Generated;

public enum StoryRank {
    EMERITUS_PROFESSOR(100.0, ThemeType.RANK_STORY_EMERITUS_PROFESSOR),
    ADJUNCT_PROFESSOR(90.0, ThemeType.RANK_STORY_ADJUNCT_PROFESSOR),
    PROFESSOR(70.0, ThemeType.RANK_STORY_PROFESSOR),
    LECTURER(50.0, ThemeType.RANK_STORY_LECTURER),
    READER(30.0, ThemeType.RANK_STORY_READER),
    RESEARCHER(20.0, ThemeType.RANK_STORY_RESEARCHER),
    STUDENT(10.0, ThemeType.RANK_STORY_STUDENT),
    PUPIL(0.0, ThemeType.RANK_STORY_PUPIL);

    private final double percentRequired;
    private final ThemeType theme;

    private StoryRank(double percentRequired, ThemeType themeType) {
        this.percentRequired = percentRequired;
        this.theme = themeType;
    }

    public static StoryRank getByPercent(double percent) {
        if (percent >= StoryRank.EMERITUS_PROFESSOR.percentRequired) {
            return EMERITUS_PROFESSOR;
        }
        if (percent >= StoryRank.ADJUNCT_PROFESSOR.percentRequired) {
            return ADJUNCT_PROFESSOR;
        }
        if (percent >= StoryRank.PROFESSOR.percentRequired) {
            return PROFESSOR;
        }
        if (percent >= StoryRank.LECTURER.percentRequired) {
            return LECTURER;
        }
        if (percent >= StoryRank.READER.percentRequired) {
            return READER;
        }
        if (percent >= StoryRank.RESEARCHER.percentRequired) {
            return RESEARCHER;
        }
        if (percent >= StoryRank.STUDENT.percentRequired) {
            return STUDENT;
        }
        return PUPIL;
    }

    @Generated
    public ThemeType getTheme() {
        return this.theme;
    }
}

