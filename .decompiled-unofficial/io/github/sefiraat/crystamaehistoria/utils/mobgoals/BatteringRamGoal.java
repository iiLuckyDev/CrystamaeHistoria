/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.Goat
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Goat;

public class BatteringRamGoal
extends AbstractGoal<Goat> {
    public BatteringRamGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void tick() {
        double velX = Math.abs(((Goat)this.self).getVelocity().getX());
        double velZ = Math.abs(((Goat)this.self).getVelocity().getZ());
        if (((Goat)this.self).isOnGround() || velX < 0.1 && velZ < 0.1) {
            ParticleUtils.displayParticleEffect((Entity)this.self, Particle.ANGRY_VILLAGER, 1.0, 5);
            ((Goat)this.self).remove();
            return;
        }
        Location location = ((Goat)this.self).getLocation().add(0.0, 0.0, 0.0);
        int radius = 2;
        ArrayList<BlockPosition> blocks = new ArrayList<BlockPosition>();
        for (int x = location.getBlockX() - 2; x <= location.getBlockX() + 2; ++x) {
            for (int y = location.getBlockY(); y <= location.getBlockY() + 2; ++y) {
                for (int z = location.getBlockZ() - 2; z <= location.getBlockZ() + 2; ++z) {
                    BlockPosition blockPosition = new BlockPosition(location.getWorld(), x, y, z);
                    if (blocks.contains(blockPosition)) continue;
                    blocks.add(blockPosition);
                }
            }
        }
        for (BlockPosition blockPosition : blocks) {
            Block block = blockPosition.getBlock();
            BlockData blockData = block.getBlockData();
            if (!GeneralUtils.blockCanBeBroken(this.owner, block)) continue;
            block.setType(Material.AIR);
            FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation(), blockData);
            GeneralUtils.pushEntity(this.owner, ((Goat)this.self).getLocation(), (Entity)fallingBlock, 0.5);
        }
    }

    @Override
    public boolean getTickCondition() {
        return false;
    }

    @Override
    public boolean getTargetsEnemies() {
        return false;
    }

    @Override
    public boolean getFollowsPlayer() {
        return false;
    }
}

