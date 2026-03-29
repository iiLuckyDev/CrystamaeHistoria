/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.versions;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleDisplay;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleOptions;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleDisplay_13
extends ParticleDisplay {
    @Override
    public void display(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        if (options.color != null && (particle == Particle.SPELL_MOB || particle == Particle.SPELL_MOB_AMBIENT)) {
            this.displayLegacyColored(particle, options, center, range, targetPlayers);
            return;
        }
        if (particle == Particle.ITEM_CRACK) {
            this.displayItem(particle, options, center, range, targetPlayers);
            return;
        }
        if (particle == Particle.BLOCK_CRACK || particle == Particle.BLOCK_DUST || particle == Particle.FALLING_DUST) {
            Material material = options.material;
            if (material == null || material.name().contains("AIR")) {
                return;
            }
            try {
                options.data = material.createBlockData();
            }
            catch (Exception ex) {
                this.manager.onError("Error creating block data for " + material, ex);
            }
            if (options.data == null) {
                return;
            }
        }
        if (particle == Particle.REDSTONE) {
            if (options.color == null) {
                options.color = Color.RED;
            }
            options.data = new Particle.DustOptions(options.color, options.size);
        }
        this.spawnParticle(particle, options, center, range, targetPlayers);
    }
}

