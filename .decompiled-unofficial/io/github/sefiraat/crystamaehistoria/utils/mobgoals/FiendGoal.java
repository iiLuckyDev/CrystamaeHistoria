/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Phantom
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractGoal;
import java.util.UUID;
import org.bukkit.entity.Phantom;

public class FiendGoal
extends AbstractGoal<Phantom> {
    public FiendGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public boolean getFollowsPlayer() {
        return false;
    }
}

