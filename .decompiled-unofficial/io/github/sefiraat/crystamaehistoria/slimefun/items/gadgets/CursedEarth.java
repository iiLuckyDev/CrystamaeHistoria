/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class CursedEarth
extends SlimefunItem {
    private final double ticksToSpawn;
    private final int lightLevel;
    private final List<EntityType> spawns;
    private final Particle.DustOptions dustOptions;
    private int currentTick = 0;

    @ParametersAreNonnullByDefault
    public CursedEarth(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double ticksToSpawn, int lightLevel, List<EntityType> spawns, Color color) {
        super(category, item, recipeType, recipe);
        this.ticksToSpawn = ticksToSpawn;
        this.lightLevel = lightLevel;
        this.spawns = spawns;
        this.dustOptions = new Particle.DustOptions(color, 1.0f);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onTick()});
    }

    private BlockTicker onTick() {
        return new BlockTicker(){

            public boolean isSynchronized() {
                return true;
            }

            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                Location location = block.getLocation().add(0.5, 1.5, 0.5);
                if ((double)CursedEarth.this.currentTick == CursedEarth.this.ticksToSpawn) {
                    Block blockA = block.getRelative(BlockFace.UP);
                    Block blockB = blockA.getRelative(BlockFace.UP);
                    if (blockA.getLightLevel() <= CursedEarth.this.lightLevel && blockA.isEmpty() && blockB.isEmpty() && location.getWorld().getNearbyEntities(location, 0.5, 0.5, 0.5).isEmpty()) {
                        if (location.getWorld().getNearbyEntities(location, 4.0, 4.0, 4.0, LivingEntity.class::isInstance).size() < 10) {
                            location.getWorld().spawnEntity(location, CursedEarth.this.spawns.get(ThreadLocalRandom.current().nextInt(CursedEarth.this.spawns.size())), true);
                        }
                    }
                    CursedEarth.this.currentTick = 0;
                } else {
                    ++CursedEarth.this.currentTick;
                }
                ParticleUtils.displayParticleEffect(location, 1.0, 3, CursedEarth.this.dustOptions);
            }
        };
    }

    @Generated
    public double getTicksToSpawn() {
        return this.ticksToSpawn;
    }

    @Generated
    public int getLightLevel() {
        return this.lightLevel;
    }

    @Generated
    public List<EntityType> getSpawns() {
        return this.spawns;
    }

    @Generated
    public Particle.DustOptions getDustOptions() {
        return this.dustOptions;
    }

    @Generated
    public int getCurrentTick() {
        return this.currentTick;
    }
}

