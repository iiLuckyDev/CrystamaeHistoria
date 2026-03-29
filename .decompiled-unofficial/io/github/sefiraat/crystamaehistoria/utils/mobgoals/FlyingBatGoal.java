/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Bat
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.utils.mobgoals;

import io.github.sefiraat.crystamaehistoria.utils.mobgoals.AbstractRidableGoal;
import java.util.UUID;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

public class FlyingBatGoal
extends AbstractRidableGoal<Bat> {
    public FlyingBatGoal(UUID owningPlayer) {
        super(owningPlayer);
    }

    @Override
    public void customActions(Player player) {
        if (((Bat)this.self).getPassengers().isEmpty()) {
            ((Bat)this.self).remove();
        }
    }

    @Override
    public boolean getCanFly() {
        return true;
    }

    @Override
    public double getSpeed() {
        return 2.0;
    }
}

