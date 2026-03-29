/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools.satchel;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nonnull;

public class SatchelInstance {
    private long id;
    private int tier;
    private String lastUser = "Unknown";
    private final Map<StoryRarity, int[]> amounts = new EnumMap<StoryRarity, int[]>(StoryRarity.class);

    public SatchelInstance(long id, int tier) {
        this.tier = tier;
        this.id = id;
        this.amounts.put(StoryRarity.COMMON, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.amounts.put(StoryRarity.UNCOMMON, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.amounts.put(StoryRarity.RARE, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.amounts.put(StoryRarity.EPIC, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.amounts.put(StoryRarity.MYTHICAL, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.amounts.put(StoryRarity.UNIQUE, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    public int getAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type) {
        return this.amounts.get((Object)rarity)[type.getId() - 1];
    }

    public void setAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type, int amount) {
        int[] values = this.amounts.get((Object)rarity);
        values[type.getId() - 1] = (long)amount >= Integer.MAX_VALUE ? Integer.MAX_VALUE : amount;
        this.amounts.put(rarity, values);
    }

    public void setAmounts(@Nonnull StoryRarity rarity, int[] amounts) {
        this.amounts.put(rarity, amounts);
    }

    public void addAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type, int amount) {
        int[] values = this.amounts.get((Object)rarity);
        int oldAmount = values[type.getId() - 1];
        long newAmount = (long)oldAmount + (long)amount;
        values[type.getId() - 1] = newAmount >= Integer.MAX_VALUE ? Integer.MAX_VALUE : oldAmount + amount;
        this.amounts.put(rarity, values);
    }

    public void removeAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type, int amount) {
        int[] values = this.amounts.get((Object)rarity);
        int oldAmount = values[type.getId() - 1];
        values[type.getId() - 1] = oldAmount - amount;
        this.amounts.put(rarity, values);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTier() {
        return this.tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getLastUser() {
        return this.lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public Map<StoryRarity, int[]> getAmounts() {
        return this.amounts;
    }
}

