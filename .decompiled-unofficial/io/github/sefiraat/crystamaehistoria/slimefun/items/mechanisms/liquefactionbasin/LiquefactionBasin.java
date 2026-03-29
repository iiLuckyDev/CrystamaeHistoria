/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  lombok.Generated
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class LiquefactionBasin
extends TickingMenuBlock {
    protected static final int[] BACKGROUND_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    protected static final int INPUT_SLOT = 22;
    public final int maxVolume;
    protected final Map<Location, LiquefactionBasinCache> cacheMap = new HashMap<Location, LiquefactionBasinCache>();
    private final Color color;

    @ParametersAreNonnullByDefault
    public LiquefactionBasin(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int maxVolume, Color color) {
        super(itemGroup, item, recipeType, recipe);
        this.maxVolume = maxVolume;
        this.color = color;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onBlockPlace()});
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false){

            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                Location location = event.getBlockPlaced().getLocation();
                LiquefactionBasinCache cache = new LiquefactionBasinCache(BlockStorage.getInventory((Location)location), LiquefactionBasin.this.maxVolume);
                cache.setActivePlayer(event.getPlayer());
                LiquefactionBasin.this.cacheMap.put(location, cache);
            }
        };
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        Material material;
        LiquefactionBasinCache cache = this.cacheMap.get(block.getLocation());
        if (cache != null) {
            cache.consumeItems();
            Particle.DustOptions dustOptions = new Particle.DustOptions(this.color, 1.0f);
            ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), 1.0, 4, dustOptions);
        }
        if ((material = block.getType()) == Material.LAVA_CAULDRON || material == Material.WATER_CAULDRON) {
            block.setType(Material.CAULDRON);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.addMenuOpeningHandler(HumanEntity::closeInventory);
        blockMenuPreset.drawBackground(BACKGROUND_SLOTS);
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent event, BlockMenu blockMenu) {
        super.onBreak(event, blockMenu);
        Location location = blockMenu.getLocation();
        LiquefactionBasinCache liquefactionBasinCache = this.cacheMap.remove(location);
        boolean punish = false;
        if (liquefactionBasinCache != null) {
            liquefactionBasinCache.kill(location);
            punish = liquefactionBasinCache.getFillLevel() > 0;
        }
        blockMenu.dropItems(location, new int[]{22});
        if (punish) {
            blockMenu.getLocation().getWorld().createExplosion(event.getBlock().getLocation(), 2.0f, true, false);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        if (!this.cacheMap.containsKey(blockMenu.getLocation())) {
            LiquefactionBasinCache cache = new LiquefactionBasinCache(blockMenu, this.maxVolume);
            Config c = BlockStorage.getLocationInfo((Location)blockMenu.getLocation());
            for (String key : c.getKeys()) {
                if (!key.startsWith("ch_c_lvl:")) continue;
                String id = key.replace("ch_c_lvl:", "");
                int amount = Integer.parseInt(c.getString(key));
                cache.getContentMap().put(StoryType.valueOf(id), amount);
            }
            this.cacheMap.put(blockMenu.getLocation(), cache);
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

    @Generated
    public Map<Location, LiquefactionBasinCache> getCacheMap() {
        return this.cacheMap;
    }
}

