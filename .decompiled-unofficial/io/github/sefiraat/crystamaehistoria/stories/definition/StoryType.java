/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.stories.definition;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;

public enum StoryType {
    ELEMENTAL(1),
    MECHANICAL(2),
    ALCHEMICAL(3),
    HISTORICAL(4),
    HUMAN(5),
    ANIMAL(6),
    CELESTIAL(7),
    VOID(8),
    PHILOSOPHICAL(9);

    private static final StoryType[] cachedValues;
    private final int id;

    private StoryType(int id) {
        this.id = id;
    }

    @Nullable
    public static StoryType getById(int id) {
        for (StoryType storyType : StoryType.getCachedValues()) {
            if (storyType.id != id) continue;
            return storyType;
        }
        return null;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static StoryType getByName(String name) {
        for (StoryType storyType : StoryType.getCachedValues()) {
            if (!storyType.name().equals(name)) continue;
            return storyType;
        }
        return null;
    }

    @Generated
    public static StoryType[] getCachedValues() {
        return cachedValues;
    }

    @Generated
    public int getId() {
        return this.id;
    }

    static {
        cachedValues = StoryType.values();
    }
}

