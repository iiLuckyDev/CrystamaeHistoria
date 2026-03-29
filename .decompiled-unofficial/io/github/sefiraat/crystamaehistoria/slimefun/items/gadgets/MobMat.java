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
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class MobMat
extends TickingBlockNoGui {
    private final double damage;
    private final boolean allowPlayerDrops;
    private final Map<Location, UUID> blockOwnerMap = new HashMap<Location, UUID>();

    @ParametersAreNonnullByDefault
    public MobMat(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double damage, boolean allowPlayerDrops) {
        super(category, item, recipeType, recipe);
        this.damage = damage;
        this.allowPlayerDrops = allowPlayerDrops;
    }

    @Override
    protected void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        this.blockOwnerMap.put(block.getLocation(), UUID.fromString(BlockStorage.getLocationInfo((Location)block.getLocation(), (String)"CH_UUID")));
    }

    @Override
    protected void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        Location location = block.getLocation().add(0.5, 0.5, 0.5);
        UUID uuid = this.blockOwnerMap.get(block.getLocation());
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1.0f);
        Collection entities = location.getWorld().getNearbyEntities(location, 0.5, 0.5, 0.5, LivingEntity.class::isInstance);
        for (Entity entity : entities) {
            LivingEntity livingEntity = (LivingEntity)entity;
            Player player = Bukkit.getPlayer((UUID)uuid);
            if (!this.allowPlayerDrops || player == null) {
                livingEntity.damage(this.damage);
            } else {
                CrystamaeHistoria.getSupportedPluginManager().playerDamageWithoutAttribution(livingEntity, player, this.damage);
            }
            ParticleUtils.displayParticleEffect(location, 1.0, 3, dustOptions);
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
        BlockStorage.clearBlockInfo((Block)blockBreakEvent.getBlock());
    }

    @Generated
    public double getDamage() {
        return this.damage;
    }

    @Generated
    public boolean isAllowPlayerDrops() {
        return this.allowPlayerDrops;
    }

    @Generated
    public Map<Location, UUID> getBlockOwnerMap() {
        return this.blockOwnerMap;
    }
}

