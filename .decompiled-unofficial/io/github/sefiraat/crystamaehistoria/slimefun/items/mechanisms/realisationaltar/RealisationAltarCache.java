/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  org.bukkit.Chunk
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.GildingUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStoryChunkDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;

public class RealisationAltarCache
extends AbstractCache {
    private final Map<BlockPosition, RealisedCrystalState> crystalStoryMap = new HashMap<BlockPosition, RealisedCrystalState>();
    private final int tier;

    @ParametersAreNonnullByDefault
    public RealisationAltarCache(BlockMenu blockMenu, int tier) {
        super(blockMenu);
        this.tier = tier;
        String activePlayerString = BlockStorage.getLocationInfo((Location)blockMenu.getLocation(), (String)"BS_CP_ACTIVE_PLAYER");
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    protected void process() {
        this.tryGrow();
        ItemStack inputItem = this.blockMenu.getItemInSlot(22);
        if (inputItem == null || inputItem.getType() == Material.AIR) {
            if (this.tier >= 5) {
                this.tryInsertItem();
            }
            return;
        }
        if (inputItem.getType() != Material.AIR && StoryUtils.isStoried(inputItem) && !StoryUtils.hasRemainingStorySlots(inputItem)) {
            this.rejectOverage(inputItem);
            if (this.processItem(inputItem)) {
                this.saveMap();
            }
        }
    }

    private void tryInsertItem() {
        Collection entities = this.getWorld().getNearbyEntities(this.getLocation().clone().add(0.5, 1.0, 0.5), 0.3, 0.3, 0.3, Item.class::isInstance);
        if (!entities.isEmpty()) {
            Item item = entities.stream().findFirst().orElse(null);
            ItemStack itemStack = item.getItemStack();
            ItemStack clone = itemStack.asQuantity(1);
            this.blockMenu.replaceExistingItem(22, clone);
            int amount = CrystamaeHistoria.getSupportedPluginManager().getStackAmount(item);
            if (amount == 1) {
                item.remove();
            } else {
                CrystamaeHistoria.getSupportedPluginManager().setStackAmount(item, amount - 1);
            }
        }
    }

    private void tryGrow() {
        Iterator<Map.Entry<BlockPosition, RealisedCrystalState>> iterator = this.crystalStoryMap.entrySet().iterator();
        block5: while (iterator.hasNext()) {
            Map.Entry<BlockPosition, RealisedCrystalState> entry = iterator.next();
            Block block = entry.getKey().getBlock();
            Material material = block.getType();
            if (entry.getValue().isGilded()) {
                this.summonGildedParticles(block);
            }
            switch (material) {
                case SMALL_AMETHYST_BUD: {
                    if (!GeneralUtils.testChance(1, 10)) continue block5;
                    block.setType(Material.MEDIUM_AMETHYST_BUD);
                    this.summonGrowParticles(block);
                    continue block5;
                }
                case MEDIUM_AMETHYST_BUD: {
                    if (!GeneralUtils.testChance(1, 20)) continue block5;
                    block.setType(Material.LARGE_AMETHYST_BUD);
                    this.summonGrowParticles(block);
                    continue block5;
                }
                case LARGE_AMETHYST_BUD: {
                    this.summonFullyGrownParticles(block);
                    continue block5;
                }
            }
            iterator.remove();
        }
    }

    @ParametersAreNonnullByDefault
    private void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            this.blockMenu.getBlock().getWorld().dropItemNaturally(this.blockMenu.getLocation(), i2);
        }
    }

    @ParametersAreNonnullByDefault
    private boolean processItem(ItemStack itemStack) {
        BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(itemStack.getType());
        if (definition.getBlockTier().tier <= this.tier + 1) {
            if (GeneralUtils.testChance(1, 6)) {
                int x = ThreadLocalRandom.current().nextInt(-3, 4);
                int z = ThreadLocalRandom.current().nextInt(-3, 4);
                Block potentialBlock = this.blockMenu.getBlock().getRelative(x, 0, z);
                if (potentialBlock.isEmpty() && potentialBlock.getRelative(BlockFace.DOWN).getType().isSolid()) {
                    List<Story> storyList = StoryUtils.getAllStories(itemStack);
                    Story story = storyList.get(0);
                    boolean isGilded = GildingUtils.isGilded(itemStack);
                    potentialBlock.setType(Material.SMALL_AMETHYST_BUD);
                    this.crystalStoryMap.put(new BlockPosition(potentialBlock.getLocation()), new RealisedCrystalState(this, story.getRarity(), story.getId(), isGilded));
                    if (StoryUtils.removeStory(itemStack, story) == 0) {
                        PlayerStatistics.addRealisation(this.activePlayer, definition);
                        itemStack.setAmount(0);
                    } else {
                        StoriesManager.rebuildStoriedStack(itemStack);
                    }
                    this.summonGrowParticles(potentialBlock);
                    this.summonConsumeParticles(this.blockMenu.getBlock());
                    return true;
                }
            }
        } else {
            this.reject(itemStack);
        }
        return false;
    }

    public void saveMap() {
        Chunk chunk = this.blockMenu.getBlock().getChunk();
        BlockPosition position = new BlockPosition(this.blockMenu.getLocation());
        ArrayList<Story> stories = new ArrayList<Story>();
        for (Map.Entry<BlockPosition, RealisedCrystalState> entry : this.crystalStoryMap.entrySet()) {
            RealisedCrystalState state = entry.getValue();
            Story story = CrystamaeHistoria.getStoriesManager().getStory(state.storyId, state.storyRarity).copy();
            story.setBlockPosition(entry.getKey());
            story.setGilded(state.gilded);
            stories.add(story);
        }
        DataTypeMethods.setCustom((PersistentDataHolder)chunk, Keys.newKey(String.valueOf(position.getPosition())), PersistentStoryChunkDataType.TYPE, stories);
    }

    private void summonGrowParticles(@Nonnull Block block) {
        Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.CRIMSON_SPORE, 0.4, 3);
    }

    private void summonFullyGrownParticles(@Nonnull Block block) {
        Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.WAX_OFF, 0.4, 3);
    }

    private void summonGildedParticles(@Nonnull Block block) {
        Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, 0.4, 3, new Particle.DustOptions(Color.YELLOW, 2.0f));
    }

    private void summonConsumeParticles(@Nonnull Block block) {
        Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.FLASH, 0.4, 2);
    }

    protected void reject(@Nullable ItemStack itemStack) {
        if (itemStack != null) {
            ItemStack rejectedSpawn = itemStack.clone();
            itemStack.setAmount(0);
            this.blockMenu.getBlock().getWorld().dropItemNaturally(this.blockMenu.getLocation(), rejectedSpawn);
        }
    }

    protected void loadMap() {
        BlockPosition position;
        Chunk chunk = this.blockMenu.getBlock().getChunk();
        List<Story> stories = DataTypeMethods.getCustom((PersistentDataHolder)chunk, Keys.newKey(String.valueOf((position = new BlockPosition(this.blockMenu.getLocation())).getPosition())), PersistentStoryChunkDataType.TYPE);
        if (stories != null) {
            for (Story story : stories) {
                this.crystalStoryMap.put(story.getBlockPosition(), new RealisedCrystalState(this, story.getRarity(), story.getId(), story.isGilded()));
            }
        }
    }

    protected void kill() {
        Iterator<BlockPosition> iterator = this.crystalStoryMap.keySet().iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next().getBlock();
            block.setType(Material.AIR);
            iterator.remove();
            this.summonConsumeParticles(block);
        }
        Chunk chunk = this.blockMenu.getBlock().getChunk();
        BlockPosition position = new BlockPosition(this.blockMenu.getLocation());
        PersistentDataAPI.remove((PersistentDataHolder)chunk, (NamespacedKey)Keys.newKey(String.valueOf(position.getPosition())));
        this.clearMap();
    }

    private void clearMap() {
        PersistentDataAPI.remove((PersistentDataHolder)this.blockMenu.getBlock().getChunk(), (NamespacedKey)Keys.RESOLUTION_CRYSTAL_MAP);
    }

    protected World getWorld() {
        return this.blockMenu.getLocation().getWorld();
    }

    protected Location getLocation() {
        return this.blockMenu.getLocation();
    }

    @Generated
    public Map<BlockPosition, RealisedCrystalState> getCrystalStoryMap() {
        return this.crystalStoryMap;
    }

    public class RealisedCrystalState {
        private final StoryRarity storyRarity;
        private final String storyId;
        private final boolean gilded;

        private RealisedCrystalState(RealisationAltarCache this$0, StoryRarity storyRarity, String storyId, boolean gilded) {
            this.storyRarity = storyRarity;
            this.storyId = storyId;
            this.gilded = gilded;
        }

        public StoryRarity getStoryRarity() {
            return this.storyRarity;
        }

        public String getStoryId() {
            return this.storyId;
        }

        public boolean isGilded() {
            return this.gilded;
        }
    }
}

