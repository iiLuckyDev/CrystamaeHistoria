/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.ExperienceOrb
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class ExpCollector
extends TickingMenuBlock {
    protected static final String ID_UUID = "CH_UUID";
    protected static final String ID_VOLUME = "EXP_VOLUME";
    protected static final int[] BACKGROUND_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private final int maxVolume;
    private final int range;
    private final Map<Location, UUID> blockOwnerMap = new HashMap<Location, UUID>();
    private final Map<Location, Integer> volumeMap = new HashMap<Location, Integer>();

    @ParametersAreNonnullByDefault
    public ExpCollector(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int maxVolume, int range) {
        super(category, item, recipeType, recipe);
        this.maxVolume = maxVolume;
        this.range = range;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        Location location = block.getLocation().add(0.5, 0.5, 0.5);
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB((int)20, (int)230, (int)5), 1.0f);
        Collection entities = location.getWorld().getNearbyEntities(location, (double)this.range, (double)this.range, (double)this.range, ExperienceOrb.class::isInstance);
        int amount = this.volumeMap.get(block.getLocation());
        boolean hasUpdated = false;
        for (Entity entity : entities) {
            ExperienceOrb experienceOrb = (ExperienceOrb)entity;
            int newAmount = amount + experienceOrb.getExperience();
            if (newAmount > this.maxVolume) continue;
            amount = newAmount;
            experienceOrb.remove();
            hasUpdated = true;
            ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 1.0, 0.5), 0.7, 3, dustOptions);
        }
        if (hasUpdated) {
            this.volumeMap.put(block.getLocation(), amount);
            this.syncValue(block);
        }
    }

    private void syncValue(Block block) {
        BlockStorage.addBlockInfo((Block)block, (String)ID_VOLUME, (String)String.valueOf(this.volumeMap.get(block.getLocation())));
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
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
    protected void onNewInstance(BlockMenu menu, Block block) {
        String volumeString;
        Location location = block.getLocation();
        String owner = BlockStorage.getLocationInfo((Location)location, (String)ID_UUID);
        if (owner != null) {
            this.blockOwnerMap.put(location, UUID.fromString(owner));
        }
        if ((volumeString = BlockStorage.getLocationInfo((Location)location, (String)ID_VOLUME)) != null) {
            this.volumeMap.put(location, Integer.parseInt(volumeString));
        }
        menu.addMenuOpeningHandler(player -> {
            player.closeInventory();
            SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)player.getInventory().getItemInMainHand());
            if (!(slimefunItem instanceof RefactingLens)) {
                int amount;
                UUID uuid = this.blockOwnerMap.get(location);
                if (player.getUniqueId().equals(uuid) && (amount = this.volumeMap.get(location).intValue()) > 0) {
                    this.volumeMap.put(location, 0);
                    player.giveExp(amount);
                    ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), Particle.WAX_ON, 1.5, Math.min(20, amount / 10));
                    this.syncValue(block);
                }
            }
        });
    }

    @Override
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        BlockStorage.clearBlockInfo((Block)e.getBlock());
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onPlace(BlockPlaceEvent e, Block b) {
        UUID uuid = e.getPlayer().getUniqueId();
        BlockStorage.addBlockInfo((Block)b, (String)ID_UUID, (String)uuid.toString());
        this.blockOwnerMap.put(b.getLocation(), uuid);
        BlockStorage.addBlockInfo((Block)b, (String)ID_VOLUME, (String)String.valueOf(0));
        this.volumeMap.put(b.getLocation(), 0);
    }

    @Generated
    public int getMaxVolume() {
        return this.maxVolume;
    }

    @Generated
    public int getRange() {
        return this.range;
    }

    @Generated
    public Map<Location, UUID> getBlockOwnerMap() {
        return this.blockOwnerMap;
    }

    @Generated
    public Map<Location, Integer> getVolumeMap() {
        return this.volumeMap;
    }
}

