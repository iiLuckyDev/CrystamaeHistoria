/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.entity.ai.Goal
 *  com.destroystokyo.paper.entity.ai.GoalKey
 *  com.destroystokyo.paper.entity.ai.GoalType
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.NamespacedKey
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Mob
 *  org.bukkit.entity.Monster
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataHolder;

public abstract class AbstractGoal<T extends Mob>
implements Goal<T> {
    protected final UUID owner;
    protected T self;
    protected GoalKey<T> goalKey;

    protected AbstractGoal(UUID owningPlayer) {
        this.owner = owningPlayer;
    }

    public void setSelf(T self) {
        this.self = self;
        Class clazz = self.getClass();
        this.goalKey = GoalKey.of((Class)clazz, (NamespacedKey)Keys.newKey("mob_goal"));
    }

    public final boolean shouldActivate() {
        return true;
    }

    public final boolean shouldStayActive() {
        return true;
    }

    public final void stop() {
        this.self.getPathfinder().stopPathfinding();
        this.self.setTarget(null);
    }

    public void tick() {
        ArrayList entities;
        Player player = this.removeOffline();
        if (player == null || this.self.getTarget() != null && this.self.getTarget().equals((Object)player)) {
            this.self.setTarget(null);
            return;
        }
        if (!this.getTickCondition() || this.self.getTarget() != null && !this.self.getTarget().isDead()) {
            return;
        }
        if (this.getTargetsEnemies() && !(entities = new ArrayList(player.getWorld().getNearbyEntitiesByType(this.getTargetClass(), player.getLocation(), 10.0, 10.0, 10.0, entity -> {
            UUID testOwner = DataTypeMethods.getCustom((PersistentDataHolder)entity, Keys.PDC_IS_SPAWN_OWNER, PersistentUUIDDataType.TYPE);
            if (testOwner == null) {
                return true;
            }
            return !testOwner.equals(this.owner);
        }))).isEmpty()) {
            LivingEntity random = (LivingEntity)entities.get(ThreadLocalRandom.current().nextInt(entities.size()));
            this.self.setTarget(random);
            this.self.attack((Entity)random);
            return;
        }
        if (this.getFollowsPlayer() && this.self.getLocation().distance(player.getLocation()) > this.getStayNearDistance()) {
            Location location = player.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(-1.5, 1.5), 0.0, ThreadLocalRandom.current().nextDouble(-1.5, 1.5));
            this.self.getPathfinder().moveTo(location);
        }
        this.customActions(player);
    }

    @Nullable
    public Player removeOffline() {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer((UUID)this.owner);
        if (!offlinePlayer.isOnline()) {
            this.self.remove();
            return null;
        }
        return offlinePlayer.getPlayer();
    }

    public boolean getTickCondition() {
        return true;
    }

    public boolean getTargetsEnemies() {
        return true;
    }

    public Class<? extends LivingEntity> getTargetClass() {
        return Monster.class;
    }

    public boolean getFollowsPlayer() {
        return true;
    }

    public double getStayNearDistance() {
        return 5.0;
    }

    public void customActions(Player player) {
    }

    @Nonnull
    public final GoalKey<T> getKey() {
        return this.goalKey;
    }

    @Nonnull
    public final EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.TARGET);
    }

    @Generated
    public UUID getOwner() {
        return this.owner;
    }

    @Generated
    public T getSelf() {
        return this.self;
    }
}

