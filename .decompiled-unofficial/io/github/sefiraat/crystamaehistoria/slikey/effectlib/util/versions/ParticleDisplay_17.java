/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.Particle$DustTransition
 *  org.bukkit.Vibration
 *  org.bukkit.Vibration$Destination
 *  org.bukkit.Vibration$Destination$BlockDestination
 *  org.bukkit.Vibration$Destination$EntityDestination
 *  org.bukkit.entity.Entity
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
import org.bukkit.Vibration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ParticleDisplay_17
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
        if (particle == Particle.DUST_COLOR_TRANSITION) {
            if (options.color == null) {
                options.color = Color.RED;
            }
            if (options.toColor == null) {
                options.toColor = options.color;
            }
            options.data = new Particle.DustTransition(options.color, options.toColor, options.size);
        }
        if (particle == Particle.VIBRATION) {
            Vibration.Destination.EntityDestination destination;
            if (options.target == null) {
                return;
            }
            Entity targetEntity = options.target.getEntity();
            if (targetEntity != null) {
                destination = new Vibration.Destination.EntityDestination(targetEntity);
            } else {
                Location targetLocation = options.target.getLocation();
                if (targetLocation == null) {
                    return;
                }
                destination = new Vibration.Destination.BlockDestination(targetLocation);
            }
            options.data = new Vibration(center, (Vibration.Destination)destination, options.arrivalTime);
        }
        this.spawnParticle(particle, options, center, range, targetPlayers);
    }
}

