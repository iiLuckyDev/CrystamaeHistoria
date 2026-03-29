/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.stories.definition;

import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;

public class StoryPool {
    private final StoryType storyType;
    private final List<Story> listCommon = new ArrayList<Story>();
    private final List<Story> listUncommon = new ArrayList<Story>();
    private final List<Story> listRare = new ArrayList<Story>();
    private final List<Story> listEpic = new ArrayList<Story>();
    private final List<Story> listMythical = new ArrayList<Story>();

    @ParametersAreNonnullByDefault
    public StoryPool(StoryType storyType) {
        this.storyType = storyType;
    }

    @Generated
    public StoryType getStoryType() {
        return this.storyType;
    }

    @Generated
    public List<Story> getListCommon() {
        return this.listCommon;
    }

    @Generated
    public List<Story> getListUncommon() {
        return this.listUncommon;
    }

    @Generated
    public List<Story> getListRare() {
        return this.listRare;
    }

    @Generated
    public List<Story> getListEpic() {
        return this.listEpic;
    }

    @Generated
    public List<Story> getListMythical() {
        return this.listMythical;
    }
}

