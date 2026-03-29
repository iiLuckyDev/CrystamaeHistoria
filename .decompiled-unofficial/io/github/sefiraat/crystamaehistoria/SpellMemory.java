/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.util.BoundingBox
 */
package io.github.sefiraat.crystamaehistoria;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.runnables.spells.SpellTickRunnable;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

public class SpellMemory {
    private final Map<MagicProjectile, Pair<CastInformation, Long>> projectileMap = new HashMap<MagicProjectile, Pair<CastInformation, Long>>();
    private final Map<MagicFallingBlock, Pair<CastInformation, Long>> fallingBlockMap = new HashMap<MagicFallingBlock, Pair<CastInformation, Long>>();
    private final Map<UUID, Pair<CastInformation, Long>> strikeMap = new HashMap<UUID, Pair<CastInformation, Long>>();
    private final Map<SpellTickRunnable, Integer> tickingCastables = new HashMap<SpellTickRunnable, Integer>();
    private final Map<BlockPosition, Long> blocksToRemove = new HashMap<BlockPosition, Long>();
    private final Map<MagicSummon, Long> summonedEntities = new HashMap<MagicSummon, Long>();
    private final Map<UUID, Long> playersWithFlight = new HashMap<UUID, Long>();
    private final Map<UUID, Long> playersWithFrozenTime = new HashMap<UUID, Long>();
    private final Map<UUID, Long> playersWithFrozenWeather = new HashMap<UUID, Long>();
    private final Map<UUID, Long> inhibitedEndermen = new HashMap<UUID, Long>();
    private final Map<BoundingBox, Long> noSpawningAreas = new HashMap<BoundingBox, Long>();
    private final Map<DisplayItem, Long> displayItems = new HashMap<DisplayItem, Long>();
    private final Map<UUID, Location> sleepingBags = new HashMap<UUID, Location>();

    public void clearAll() {
        for (SpellTickRunnable spellTickRunnable : this.tickingCastables.keySet()) {
            spellTickRunnable.cancel();
        }
        this.tickingCastables.clear();
        this.removeProjectiles(true);
        this.projectileMap.clear();
        this.removeFallingBlocks(true);
        this.fallingBlockMap.clear();
        this.removeEntities(true);
        this.summonedEntities.clear();
        this.removeBlocks(true);
        this.blocksToRemove.clear();
        this.removeFlight(true);
        this.playersWithFlight.clear();
        this.removeFrozenTime(true);
        this.playersWithFrozenTime.clear();
        this.removeFrozenWeather(true);
        this.playersWithFrozenWeather.clear();
        this.removeEnderman(true);
        this.inhibitedEndermen.clear();
        this.enableSpawningInArea(true);
        this.noSpawningAreas.clear();
        this.removeDisplayItems(true);
        this.displayItems.clear();
        this.removeSleepingBags();
        this.sleepingBags.clear();
    }

    public void removeProjectiles(boolean forceRemoveAll) {
        HashSet<MagicProjectile> set = new HashSet<MagicProjectile>(this.projectileMap.keySet());
        for (MagicProjectile magicProjectile : set) {
            long expiry = (Long)this.projectileMap.get(magicProjectile).getSecondValue();
            if (System.currentTimeMillis() <= expiry && !forceRemoveAll) continue;
            magicProjectile.kill();
        }
    }

    public void removeFallingBlocks(boolean forceRemoveAll) {
        HashSet<MagicFallingBlock> set = new HashSet<MagicFallingBlock>(this.fallingBlockMap.keySet());
        for (MagicFallingBlock magicFallingBlock : set) {
            long expiry = (Long)this.fallingBlockMap.get(magicFallingBlock).getSecondValue();
            if (System.currentTimeMillis() <= expiry && !forceRemoveAll) continue;
            magicFallingBlock.kill();
        }
    }

    public void removeEntities(boolean forceRemoveAll) {
        HashSet<MagicSummon> set = new HashSet<MagicSummon>(CrystamaeHistoria.getSummonedEntityMap().keySet());
        for (MagicSummon magicSummon : set) {
            long expiry = this.summonedEntities.get(magicSummon);
            if (System.currentTimeMillis() > expiry || magicSummon.getMob() == null || forceRemoveAll) {
                magicSummon.kill();
                continue;
            }
            magicSummon.run();
        }
    }

    public void removeBlocks(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        HashSet<Map.Entry<BlockPosition, Long>> set = new HashSet<Map.Entry<BlockPosition, Long>>(this.blocksToRemove.entrySet());
        for (Map.Entry entry : set) {
            if (!forceRemoveAll && (Long)entry.getValue() >= time) continue;
            ((BlockPosition)entry.getKey()).getBlock().setType(Material.AIR);
            this.blocksToRemove.remove(entry.getKey());
        }
    }

    public void removeFlight(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        HashSet<Map.Entry<UUID, Long>> set = new HashSet<Map.Entry<UUID, Long>>(this.playersWithFlight.entrySet());
        for (Map.Entry entry : set) {
            Player player;
            if (!forceRemoveAll && (Long)entry.getValue() >= time || (player = Bukkit.getPlayer((UUID)((UUID)entry.getKey()))) == null) continue;
            player.setAllowFlight(false);
            player.setFlying(false);
            this.playersWithFlight.remove(entry.getKey());
        }
    }

    public void removeFrozenTime(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        HashSet<Map.Entry<UUID, Long>> set = new HashSet<Map.Entry<UUID, Long>>(this.playersWithFrozenTime.entrySet());
        for (Map.Entry entry : set) {
            Player player;
            if (!forceRemoveAll && (Long)entry.getValue() >= time || (player = Bukkit.getPlayer((UUID)((UUID)entry.getKey()))) == null) continue;
            player.resetPlayerTime();
            this.playersWithFrozenTime.remove(entry.getKey());
        }
    }

    public void removeFrozenWeather(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        HashSet<Map.Entry<UUID, Long>> set = new HashSet<Map.Entry<UUID, Long>>(this.playersWithFrozenWeather.entrySet());
        for (Map.Entry entry : set) {
            Player player;
            if (!forceRemoveAll && (Long)entry.getValue() >= time || (player = Bukkit.getPlayer((UUID)((UUID)entry.getKey()))) == null) continue;
            player.resetPlayerWeather();
            this.playersWithFrozenWeather.remove(entry.getKey());
        }
    }

    public void removeEnderman(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        HashSet<Map.Entry<UUID, Long>> set = new HashSet<Map.Entry<UUID, Long>>(this.inhibitedEndermen.entrySet());
        for (Map.Entry entry : set) {
            if (!forceRemoveAll && (Long)entry.getValue() >= time) continue;
            this.inhibitedEndermen.remove(entry.getKey());
        }
    }

    public void enableSpawningInArea(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        HashSet<Map.Entry<BoundingBox, Long>> set = new HashSet<Map.Entry<BoundingBox, Long>>(this.noSpawningAreas.entrySet());
        for (Map.Entry entry : set) {
            if (!forceRemoveAll && (Long)entry.getValue() >= time) continue;
            this.noSpawningAreas.remove(entry.getKey());
        }
    }

    public void removeDisplayItems(boolean forceRemoveAll) {
        long time = System.currentTimeMillis();
        HashSet<Map.Entry<DisplayItem, Long>> set = new HashSet<Map.Entry<DisplayItem, Long>>(this.displayItems.entrySet());
        for (Map.Entry entry : set) {
            if (!forceRemoveAll && (Long)entry.getValue() >= time) continue;
            ((DisplayItem)entry.getKey()).kill();
            this.displayItems.remove(entry.getKey());
        }
    }

    public void removeSleepingBags() {
        HashSet<Map.Entry<UUID, Location>> set = new HashSet<Map.Entry<UUID, Location>>(this.sleepingBags.entrySet());
        for (Map.Entry entry : set) {
            Block block = ((Location)entry.getValue()).getBlock();
            block.setType(Material.AIR);
        }
    }

    public void removeFlight(Player player) {
        if (this.playersWithFlight.containsKey(player.getUniqueId())) {
            player.setAllowFlight(false);
            player.setFlying(false);
            this.playersWithFlight.remove(player.getUniqueId());
        }
    }

    public void stopBlockRemoval(Block block) {
        BlockPosition blockPosition = new BlockPosition(block);
        this.blocksToRemove.remove(blockPosition);
    }

    @Generated
    public Map<MagicProjectile, Pair<CastInformation, Long>> getProjectileMap() {
        return this.projectileMap;
    }

    @Generated
    public Map<MagicFallingBlock, Pair<CastInformation, Long>> getFallingBlockMap() {
        return this.fallingBlockMap;
    }

    @Generated
    public Map<UUID, Pair<CastInformation, Long>> getStrikeMap() {
        return this.strikeMap;
    }

    @Generated
    public Map<SpellTickRunnable, Integer> getTickingCastables() {
        return this.tickingCastables;
    }

    @Generated
    public Map<BlockPosition, Long> getBlocksToRemove() {
        return this.blocksToRemove;
    }

    @Generated
    public Map<MagicSummon, Long> getSummonedEntities() {
        return this.summonedEntities;
    }

    @Generated
    public Map<UUID, Long> getPlayersWithFlight() {
        return this.playersWithFlight;
    }

    @Generated
    public Map<UUID, Long> getPlayersWithFrozenTime() {
        return this.playersWithFrozenTime;
    }

    @Generated
    public Map<UUID, Long> getPlayersWithFrozenWeather() {
        return this.playersWithFrozenWeather;
    }

    @Generated
    public Map<UUID, Long> getInhibitedEndermen() {
        return this.inhibitedEndermen;
    }

    @Generated
    public Map<BoundingBox, Long> getNoSpawningAreas() {
        return this.noSpawningAreas;
    }

    @Generated
    public Map<DisplayItem, Long> getDisplayItems() {
        return this.displayItems;
    }

    @Generated
    public Map<UUID, Location> getSleepingBags() {
        return this.sleepingBags;
    }
}

