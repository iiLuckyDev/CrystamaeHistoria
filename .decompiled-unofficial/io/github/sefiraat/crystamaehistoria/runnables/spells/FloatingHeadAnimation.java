/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.scheduler.BukkitRunnable
 */
package io.github.sefiraat.crystamaehistoria.runnables.spells;

import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class FloatingHeadAnimation
extends BukkitRunnable {
    public static final double Y_DEVIANCY = 0.2;
    public static final long SPEED = 1L;
    private final ArmorStand armorStand;
    private final double baseY;
    private boolean directionUp = true;

    @ParametersAreNonnullByDefault
    public FloatingHeadAnimation(ArmorStand armorStand) {
        this.armorStand = armorStand;
        this.baseY = armorStand.getLocation().getY();
    }

    public void run() {
        if (this.directionUp) {
            ArmourStandUtils.panelAnimationStep(this.armorStand, true);
            if (this.armorStand.getLocation().getY() >= this.baseY + 0.2) {
                this.directionUp = false;
            }
        } else {
            ArmourStandUtils.panelAnimationStep(this.armorStand, false);
            if (this.armorStand.getLocation().getY() <= this.baseY - 0.2) {
                this.directionUp = true;
            }
        }
    }
}

