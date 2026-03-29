/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.block.data.type.Candle
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.BoundingBox
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Candle;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

public class MobCandle
extends TickingBlockNoGui {
    private final double radius;
    private final int duration;
    private final Map<Location, Long> expiryMap = new HashMap<Location, Long>();

    @ParametersAreNonnullByDefault
    public MobCandle(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int radius, int durationInSeconds) {
        super(category, item, recipeType, recipe);
        this.radius = radius;
        this.duration = durationInSeconds;
    }

    @Override
    protected void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        Long expiry = Long.valueOf(BlockStorage.getLocationInfo((Location)block.getLocation(), (String)"EXPIRY"));
        this.expiryMap.put(block.getLocation(), expiry);
        Candle candle = (Candle)block.getBlockData();
        candle.setLit(true);
        ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), Particle.SOUL_FIRE_FLAME, 0.5, 10);
    }

    @Override
    protected void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        Location location = block.getLocation();
        BoundingBox boundingBox = new BoundingBox(location.getX() - this.radius, location.getY() - this.radius, location.getZ() - this.radius, location.getX() + this.radius, location.getY() + this.radius, location.getZ() + this.radius);
        CrystamaeHistoria.getSpellMemory().getNoSpawningAreas().put(boundingBox, System.currentTimeMillis() + 2000L);
        Long expiry = this.expiryMap.get(block.getLocation());
        if (expiry < System.currentTimeMillis()) {
            BlockStorage.clearBlockInfo((Block)block);
            block.setType(Material.AIR);
            ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), Particle.CAMPFIRE_SIGNAL_SMOKE, 0.5, 3);
        }
    }

    @Override
    protected void onPlace(@Nonnull BlockPlaceEvent event) {
        Long expiry = System.currentTimeMillis() + (long)this.duration * 1000L;
        BlockStorage.addBlockInfo((Block)event.getBlock(), (String)"EXPIRY", (String)String.valueOf(expiry));
        this.expiryMap.put(event.getBlock().getLocation(), expiry);
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
        BlockStorage.clearBlockInfo((Block)blockBreakEvent.getBlock());
    }

    @Generated
    public double getRadius() {
        return this.radius;
    }

    @Generated
    public int getDuration() {
        return this.duration;
    }

    @Generated
    public Map<Location, Long> getExpiryMap() {
        return this.expiryMap;
    }
}

