/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.stories.definition;

import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

public class StoryShardProfile {
    public final Map<StoryType, Integer> shardMap = new EnumMap<StoryType, Integer>(StoryType.class);

    public StoryShardProfile(List<Integer> integerList) {
        this.shardMap.put(StoryType.ELEMENTAL, integerList.get(StoryType.ELEMENTAL.getId() - 1));
        this.shardMap.put(StoryType.MECHANICAL, integerList.get(StoryType.MECHANICAL.getId() - 1));
        this.shardMap.put(StoryType.ALCHEMICAL, integerList.get(StoryType.ALCHEMICAL.getId() - 1));
        this.shardMap.put(StoryType.HISTORICAL, integerList.get(StoryType.HISTORICAL.getId() - 1));
        this.shardMap.put(StoryType.HUMAN, integerList.get(StoryType.HUMAN.getId() - 1));
        this.shardMap.put(StoryType.ANIMAL, integerList.get(StoryType.ANIMAL.getId() - 1));
        this.shardMap.put(StoryType.CELESTIAL, integerList.get(StoryType.CELESTIAL.getId() - 1));
        this.shardMap.put(StoryType.VOID, integerList.get(StoryType.VOID.getId() - 1));
        this.shardMap.put(StoryType.PHILOSOPHICAL, integerList.get(StoryType.PHILOSOPHICAL.getId() - 1));
    }

    public void dropShards(@Nonnull StoryRarity rarity, @Nonnull Location location, boolean isGilded) {
        for (Map.Entry<StoryType, Integer> entry : this.shardMap.entrySet()) {
            StoryType storyType = entry.getKey();
            int amount = entry.getValue() * this.getMultiplier(isGilded);
            if (amount > 0) {
                ItemStack itemStack = SlimefunItem.getById((String)SlimefunItem.getByItem((ItemStack)Materials.getCrystalMap().get((Object)rarity).get((Object)storyType).getItem().clone()).getId()).getItem();
                itemStack.setAmount(amount);
                location.getWorld().dropItemNaturally(location, itemStack);
            }
            if (!isGilded) continue;
            this.tryDropSigil(location, rarity);
        }
    }

    public void tryDropSigil(@Nonnull Location location, @Nonnull StoryRarity storyRarity) {
        if (storyRarity != StoryRarity.UNIQUE && GeneralUtils.testChance(storyRarity.getId(), 100)) {
            ParticleUtils.displayParticleEffect(location, Particle.SPIT, 1.0, 3);
            ParticleUtils.displayParticleEffect(location, Particle.ENCHANT, 1.0, 1);
            location.getWorld().dropItemNaturally(location, CrystaStacks.ARCANE_SIGIL.item().clone());
        }
    }

    private int getMultiplier(boolean isGilded) {
        if (isGilded) {
            int rnd = ThreadLocalRandom.current().nextInt(1, 101);
            if (rnd < 5) {
                return 4;
            }
            if (rnd < 25) {
                return 3;
            }
            return 2;
        }
        return 1;
    }
}

