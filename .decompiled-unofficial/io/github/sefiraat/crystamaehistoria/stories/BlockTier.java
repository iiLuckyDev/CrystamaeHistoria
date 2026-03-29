/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryChances;
import javax.annotation.ParametersAreNonnullByDefault;

public class BlockTier {
    public final int tier;
    public final int chroniclingChance;
    public final int maxStories;
    public final int minStories;
    public final StoryChances storyChances;

    @ParametersAreNonnullByDefault
    public BlockTier(int tier, int chroniclingChance, int maxStories, int minStories, StoryChances storyChances) {
        this.tier = tier;
        this.chroniclingChance = chroniclingChance;
        this.maxStories = maxStories;
        this.minStories = minStories;
        this.storyChances = storyChances;
    }
}

