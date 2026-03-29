/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Mob
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import java.util.UUID;
import org.bukkit.entity.Mob;

public class DeityGoal
extends AbstractGoal<Mob> {
    public DeityGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public boolean getTargetsEnemies() {
        return false;
    }
}

