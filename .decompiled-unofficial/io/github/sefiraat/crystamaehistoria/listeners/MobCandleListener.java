/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Monster
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.CreatureSpawnEvent
 *  org.bukkit.event.entity.CreatureSpawnEvent$SpawnReason
 *  org.bukkit.util.BoundingBox
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.BoundingBox;

public class MobCandleListener
implements Listener {
    @EventHandler
    public void onInteract(CreatureSpawnEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity instanceof Monster && e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER && e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG && e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.BUILD_WITHER) {
            for (BoundingBox boundingBox : CrystamaeHistoria.getSpellMemory().getNoSpawningAreas().keySet()) {
                if (!boundingBox.contains(e.getLocation().toVector())) continue;
                e.setCancelled(true);
                return;
            }
        }
    }
}

