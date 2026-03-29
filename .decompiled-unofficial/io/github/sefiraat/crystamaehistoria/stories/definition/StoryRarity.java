/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.stories.definition;

import javax.annotation.Nullable;
import lombok.Generated;

public enum StoryRarity {
    COMMON(1),
    UNCOMMON(2),
    RARE(3),
    EPIC(4),
    MYTHICAL(5),
    UNIQUE(6);

    private static final StoryRarity[] cachedValues;
    private final int id;

    private StoryRarity(int id) {
        this.id = id;
    }

    @Nullable
    public static StoryRarity getById(int id) {
        for (StoryRarity storyRarity : StoryRarity.getCachedValues()) {
            if (storyRarity.id != id) continue;
            return storyRarity;
        }
        return null;
    }

    @Generated
    public static StoryRarity[] getCachedValues() {
        return cachedValues;
    }

    @Generated
    public int getId() {
        return this.id;
    }

    static {
        cachedValues = StoryRarity.values();
    }
}

