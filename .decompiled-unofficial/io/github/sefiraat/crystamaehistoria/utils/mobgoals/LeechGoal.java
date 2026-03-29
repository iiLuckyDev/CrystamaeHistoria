/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Mob
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import java.util.UUID;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;

public class LeechGoal
extends AbstractGoal<Mob> {
    public LeechGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public boolean getFollowsPlayer() {
        return false;
    }

    @Override
    public Class<? extends LivingEntity> getTargetClass() {
        return Mob.class;
    }
}

