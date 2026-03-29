/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  lombok.Generated
 *  net.md_5.bungee.api.ChatColor
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  org.bukkit.configuration.ConfigurationSection
 */
package io.github.sefiraat.crystamaehistoria.stories;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryShardProfile;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.ConfigurationSection;

public class Story {
    @Nonnull
    private final String id;
    @Nullable
    private final String author;
    @Nullable
    private final String sponsor;
    @Nonnull
    private final StoryRarity rarity;
    @Nonnull
    private final StoryType type;
    @Nonnull
    private final StoryShardProfile storyShardProfile;
    @Nonnull
    private final List<String> storyStrings;
    @Nullable
    private BlockPosition blockPosition;
    private boolean gilded = false;

    @ParametersAreNonnullByDefault
    public Story(ConfigurationSection section, StoryRarity storyRarity) {
        List shards = section.getIntegerList("shards");
        this.id = section.getString("name");
        StoryType storyType = StoryType.getByName(section.getString("type"));
        if (shards.size() != 9) {
            CrystamaeHistoria.getInstance().getLogger().warning(MessageFormat.format("The following story does not have a correctly setup shard profile: {0}", this.id));
        }
        if (storyType == null) {
            CrystamaeHistoria.getInstance().getLogger().warning(MessageFormat.format("A block story has a badly typed element -> {0}", this.id));
        }
        this.rarity = storyRarity;
        this.type = storyType;
        this.storyShardProfile = new StoryShardProfile(section.getIntegerList("shards"));
        this.storyStrings = section.getStringList("lore");
        this.author = section.getString("author");
        this.sponsor = section.getString("sponsor");
    }

    @ParametersAreNonnullByDefault
    private Story(Story story) {
        this.rarity = story.rarity;
        this.id = story.getId();
        this.type = story.type;
        this.storyShardProfile = story.getStoryShardProfile();
        this.storyStrings = story.storyStrings;
        this.author = story.author;
        this.sponsor = story.sponsor;
        this.blockPosition = story.blockPosition;
        this.gilded = story.gilded;
    }

    public String getDisplayName() {
        TextComponent rarityComponent = new TextComponent(this.getDisplayRarity());
        TextComponent nameComponent = new TextComponent(this.id);
        rarityComponent.setColor(ThemeType.getByRarity(this.rarity).getColor());
        rarityComponent.setBold(Boolean.valueOf(true));
        nameComponent.setColor(ThemeType.CLICK_INFO.getColor());
        return BaseComponent.toLegacyText((BaseComponent[])new BaseComponent[]{rarityComponent, nameComponent});
    }

    public String getDisplayRarity() {
        return "[" + String.valueOf((Object)this.rarity) + "] ";
    }

    public List<String> getStoryLore() {
        ChatColor passive = ThemeType.PASSIVE.getColor();
        ArrayList<String> l = new ArrayList<String>();
        for (String s : this.storyStrings) {
            TextComponent line = new TextComponent(s);
            line.setColor(passive);
            line.setItalic(Boolean.valueOf(false));
            l.add(BaseComponent.toLegacyText((BaseComponent[])new BaseComponent[]{line}));
        }
        if (this.author != null) {
            l.add("");
            l.add(String.valueOf(ThemeType.PASSIVE.getColor()) + "Author: " + this.author);
        }
        if (this.sponsor != null) {
            l.add("");
            l.add(String.valueOf(ThemeType.PASSIVE.getColor()) + "Sponsor: " + this.sponsor);
        }
        return l;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Story) {
            Story story = (Story)obj;
            return this.id.equals(story.id) && this.rarity == story.rarity && this.type == story.type;
        }
        return false;
    }

    public Story copy() {
        return new Story(this);
    }

    @Nonnull
    @Generated
    public String getId() {
        return this.id;
    }

    @Nullable
    @Generated
    public String getAuthor() {
        return this.author;
    }

    @Nullable
    @Generated
    public String getSponsor() {
        return this.sponsor;
    }

    @Nonnull
    @Generated
    public StoryRarity getRarity() {
        return this.rarity;
    }

    @Nonnull
    @Generated
    public StoryType getType() {
        return this.type;
    }

    @Nonnull
    @Generated
    public StoryShardProfile getStoryShardProfile() {
        return this.storyShardProfile;
    }

    @Nonnull
    @Generated
    public List<String> getStoryStrings() {
        return this.storyStrings;
    }

    @Nullable
    @Generated
    public BlockPosition getBlockPosition() {
        return this.blockPosition;
    }

    @Generated
    public boolean isGilded() {
        return this.gilded;
    }

    @Generated
    public void setBlockPosition(@Nullable BlockPosition blockPosition) {
        this.blockPosition = blockPosition;
    }

    @Generated
    public void setGilded(boolean gilded) {
        this.gilded = gilded;
    }
}

