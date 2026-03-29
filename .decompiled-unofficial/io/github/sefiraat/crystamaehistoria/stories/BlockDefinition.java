/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Material
 */
package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.stories.BlockTier;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Material;

public class BlockDefinition {
    private final Material material;
    private final BlockTier blockTier;
    private final List<StoryType> pools;
    private final Story unique;

    @ParametersAreNonnullByDefault
    public BlockDefinition(Material material, BlockTier tier, List<StoryType> pools, Story unique) {
        this.material = material;
        this.blockTier = tier;
        this.pools = pools;
        this.unique = unique;
    }

    @Generated
    public Material getMaterial() {
        return this.material;
    }

    @Generated
    public BlockTier getBlockTier() {
        return this.blockTier;
    }

    @Generated
    public List<StoryType> getPools() {
        return this.pools;
    }

    @Generated
    public Story getUnique() {
        return this.unique;
    }
}

