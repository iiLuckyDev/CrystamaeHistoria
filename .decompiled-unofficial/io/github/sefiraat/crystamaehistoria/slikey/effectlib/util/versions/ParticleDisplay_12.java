/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Player
 *  org.bukkit.material.MaterialData
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
import org.bukkit.material.MaterialData;

public class ParticleDisplay_12
extends ParticleDisplay {
    @Override
    public void display(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        Color color = options.color;
        if (color != null && (particle == Particle.REDSTONE || particle == Particle.SPELL_MOB || particle == Particle.SPELL_MOB_AMBIENT)) {
            this.displayLegacyColored(particle, options, center, range, targetPlayers);
            return;
        }
        if (particle == Particle.ITEM_CRACK) {
            this.displayItem(particle, options, center, range, targetPlayers);
            return;
        }
        if (particle == Particle.BLOCK_CRACK || particle == Particle.BLOCK_DUST || particle.name().equals("FALLING_DUST")) {
            Material material = options.material;
            if (material == null || material.name().contains("AIR")) {
                return;
            }
            options.data = new MaterialData(material, options.materialData);
        }
        this.spawnParticle(particle, options, center, range, targetPlayers);
    }
}

