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
 *  org.bukkit.FluidCollisionMode
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.block.data.Ageable
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.RayTraceResult
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.TimePeriod;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

public class GreenHouseGlass
extends TickingBlockNoGui {
    private final int rate;
    private final Map<Location, UUID> blockOwnerMap = new HashMap<Location, UUID>();

    @ParametersAreNonnullByDefault
    public GreenHouseGlass(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int rate) {
        super(category, item, recipeType, recipe);
        this.rate = rate;
    }

    @Override
    protected void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        this.blockOwnerMap.put(block.getLocation(), UUID.fromString(BlockStorage.getLocationInfo((Location)block.getLocation(), (String)"CH_UUID")));
    }

    @Override
    protected void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        Ageable ageable;
        if (!GeneralUtils.testChance(this.rate, 100) || TimePeriod.isLight(block.getWorld())) {
            return;
        }
        BlockFace direction = BlockFace.DOWN;
        Location location = block.getLocation().subtract(0.0, 1.0, 0.0);
        RayTraceResult result = location.getWorld().rayTrace(location, direction.getDirection(), 30.0, FluidCollisionMode.ALWAYS, false, 0.1, null);
        if (result == null) {
            return;
        }
        Block testBlock = result.getHitBlock();
        if (testBlock == null || testBlock.isSolid()) {
            return;
        }
        BlockData blockData = testBlock.getBlockData();
        if (blockData instanceof Ageable && (ageable = (Ageable)blockData).getAge() < ageable.getMaximumAge()) {
            ageable.setAge(ageable.getAge() + 1);
            ParticleUtils.displayParticleEffect(testBlock.getLocation().add(0.5, 0.5, 0.5), Particle.WITCH, 0.5, 2);
            testBlock.setBlockData((BlockData)ageable);
        }
    }

    @Override
    protected void onPlace(@Nonnull BlockPlaceEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        BlockStorage.addBlockInfo((Block)event.getBlock(), (String)"CH_UUID", (String)uuid.toString());
        this.blockOwnerMap.put(event.getBlock().getLocation(), uuid);
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
    }

    @Generated
    public int getRate() {
        return this.rate;
    }

    @Generated
    public Map<Location, UUID> getBlockOwnerMap() {
        return this.blockOwnerMap;
    }
}

