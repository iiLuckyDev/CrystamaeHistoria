/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleOptions;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.versions.ParticleDisplay_12;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.versions.ParticleDisplay_13;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.versions.ParticleDisplay_17;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ParticleDisplay {
    protected EffectManager manager;
    private static boolean hasColorTransition = false;

    public abstract void display(Particle var1, ParticleOptions var2, Location var3, double var4, List<Player> var6);

    protected void spawnParticle(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        block6: {
            try {
                if (targetPlayers == null) {
                    double squared = range * range;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getWorld() != center.getWorld() || player.getLocation().distanceSquared(center) > squared || this.manager.isPlayerIgnored(player)) continue;
                        player.spawnParticle(particle, center, options.amount, (double)options.offsetX, (double)options.offsetY, (double)options.offsetZ, (double)options.speed, options.data);
                    }
                } else {
                    for (Player player : targetPlayers) {
                        if (this.manager.isPlayerIgnored(player)) continue;
                        player.spawnParticle(particle, center, options.amount, (double)options.offsetX, (double)options.offsetY, (double)options.offsetZ, (double)options.speed, options.data);
                    }
                }
            }
            catch (Exception ex) {
                if (this.manager == null) break block6;
                this.manager.onError(ex);
            }
        }
    }

    protected void displayItem(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        Material material = options.material;
        if (material == null || material == Material.AIR) {
            return;
        }
        ItemStack item = new ItemStack(material);
        item.setDurability((short)options.materialData);
        options.data = item;
        this.spawnParticle(particle, options, center, range, targetPlayers);
    }

    protected void displayLegacyColored(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        Color color = options.color;
        if (color == null) {
            color = Color.RED;
        }
        if (options.speed == 0.0f) {
            options.speed = 1.0f;
        }
        options.amount = 0;
        float offsetX = (float)color.getRed() / 255.0f;
        float offsetY = (float)color.getGreen() / 255.0f;
        float offsetZ = (float)color.getBlue() / 255.0f;
        if (offsetX < Float.MIN_NORMAL) {
            offsetX = Float.MIN_NORMAL;
        }
        options.offsetX = offsetX;
        options.offsetY = offsetY;
        options.offsetZ = offsetZ;
        this.spawnParticle(particle, options, center, range, targetPlayers);
    }

    public void setManager(EffectManager manager) {
        this.manager = manager;
    }

    public static ParticleDisplay newInstance() {
        ParticleDisplay display;
        try {
            Particle.valueOf((String)"VIBRATION");
            display = new ParticleDisplay_17();
            hasColorTransition = true;
        }
        catch (Throwable not17) {
            try {
                Particle.valueOf((String)"SQUID_INK");
                display = new ParticleDisplay_13();
            }
            catch (Throwable not13) {
                display = new ParticleDisplay_12();
            }
        }
        return display;
    }

    public static boolean hasColorTransition() {
        return hasColorTransition;
    }
}

