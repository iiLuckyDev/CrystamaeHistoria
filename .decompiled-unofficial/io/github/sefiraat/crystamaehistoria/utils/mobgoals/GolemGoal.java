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

public class GolemGoal
extends AbstractGoal<Mob> {
    public GolemGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public double getStayNearDistance() {
        return 3.0;
    }
}

