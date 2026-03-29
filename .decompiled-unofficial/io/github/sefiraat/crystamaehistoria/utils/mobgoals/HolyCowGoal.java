/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Cow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Monster
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataHolder;

public class HolyCowGoal
extends AbstractGoal<Cow> {
    public HolyCowGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void tick() {
        ArrayList entities;
        Player player = this.removeOffline();
        if (player == null) {
            return;
        }
        if (((Cow)this.self).getTarget() != null && ((Cow)this.self).getTarget().equals((Object)player)) {
            ((Cow)this.self).setTarget(null);
            return;
        }
        ArrayList entitiesAroundCow = new ArrayList(player.getWorld().getNearbyEntitiesByType(Monster.class, ((Cow)this.self).getLocation(), 1.0, 1.0, 1.0, entity -> {
            UUID testOwner = DataTypeMethods.getCustom((PersistentDataHolder)entity, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE);
            if (testOwner == null) {
                return true;
            }
            return !testOwner.equals(this.getOwner());
        }));
        if (!entitiesAroundCow.isEmpty()) {
            Object entity2 = this.getSelf();
            entity2.getLocation().getWorld().createExplosion((Entity)player, entity2.getLocation(), 10.0f, false, false);
            ((Cow)this.getSelf()).remove();
            return;
        }
        if (((Cow)this.self).getTarget() != null && !((Cow)this.self).getTarget().isDead()) {
            return;
        }
        if (this.getTargetsEnemies() && !(entities = new ArrayList(player.getWorld().getNearbyEntitiesByType(Monster.class, player.getLocation(), 15.0, 15.0, 15.0, entity -> {
            UUID testOwner = DataTypeMethods.getCustom((PersistentDataHolder)entity, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE);
            if (testOwner == null) {
                return true;
            }
            return !testOwner.equals(this.owner);
        }))).isEmpty()) {
            int random = ThreadLocalRandom.current().nextInt(entities.size());
            ((Cow)this.self).getPathfinder().moveTo(((LivingEntity)entities.get(random)).getLocation());
            return;
        }
        if (this.getFollowsPlayer() && ((Cow)this.self).getLocation().distance(player.getLocation()) > this.getStayNearDistance()) {
            Location location = player.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(-1.5, 1.5), 0.0, ThreadLocalRandom.current().nextDouble(-1.5, 1.5));
            ((Cow)this.self).getPathfinder().moveTo(location);
        }
    }
}

