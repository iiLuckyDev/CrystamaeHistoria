/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.scheduler.BukkitRunnable
 */
package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class TunnelBoreRunnable
extends BukkitRunnable {
    private final LivingEntity bore;
    private final int radius;
    private final UUID owner;
    private int iterations;

    @ParametersAreNonnullByDefault
    public TunnelBoreRunnable(LivingEntity bore, int radius, UUID owner, int iterations) {
        this.bore = bore;
        this.radius = radius;
        this.owner = owner;
        this.iterations = iterations;
    }

    public synchronized void cancel() throws IllegalStateException {
        this.bore.remove();
        super.cancel();
    }

    public void run() {
        if (this.iterations <= 0) {
            this.cancel();
        } else {
            Location location = this.bore.getLocation();
            ArrayList<BlockPosition> blocks = new ArrayList<BlockPosition>();
            for (int x = location.getBlockX() - this.radius; x <= location.getBlockX() + this.radius; ++x) {
                for (int y = location.getBlockY() - this.radius; y <= location.getBlockY() + this.radius; ++y) {
                    for (int z = location.getBlockZ() - this.radius; z <= location.getBlockZ() + this.radius; ++z) {
                        BlockPosition blockPosition = new BlockPosition(location.getWorld(), x, y, z);
                        if (blocks.contains(blockPosition)) continue;
                        blocks.add(blockPosition);
                    }
                }
            }
            for (BlockPosition blockPosition : blocks) {
                Block block = blockPosition.getBlock();
                if (!GeneralUtils.blockCanBeBroken(this.owner, block)) continue;
                block.setType(Material.AIR);
            }
            ParticleUtils.displayParticleEffect((Entity)this.bore, Particle.FALLING_LAVA, (double)this.radius, 10);
        }
        --this.iterations;
    }
}

