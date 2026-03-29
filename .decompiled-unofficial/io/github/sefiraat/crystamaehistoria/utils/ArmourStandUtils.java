/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.block.Block
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.util.EulerAngle
 */
package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.util.EulerAngle;

public final class ArmourStandUtils {
    @ParametersAreNonnullByDefault
    public static void setDisplay(ArmorStand armorStand) {
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setRemoveWhenFarAway(false);
        armorStand.setCollidable(false);
        armorStand.setInvulnerable(true);
        armorStand.setMarker(true);
        armorStand.setSilent(false);
        armorStand.setCustomName(ThemeType.getRandomEggName());
        PersistentDataAPI.setBoolean((PersistentDataHolder)armorStand, (NamespacedKey)Keys.PDC_IS_DISPLAY_STAND, (boolean)true);
    }

    @ParametersAreNonnullByDefault
    public static void setDisplayItem(ArmorStand armorStand, Material material) {
        ArmourStandUtils.setDisplayItem(armorStand, new ItemStack(material));
    }

    @ParametersAreNonnullByDefault
    public static void setDisplayItem(ArmorStand armorStand, ItemStack itemStack) {
        ArmourStandUtils.clearDisplayItem(armorStand);
        armorStand.getEquipment().setHelmet(itemStack);
    }

    @ParametersAreNonnullByDefault
    public static void clearDisplayItem(ArmorStand armorStand) {
        armorStand.getEquipment().setHelmet(null);
    }

    @ParametersAreNonnullByDefault
    public static boolean isDisplayStand(ArmorStand armorStand) {
        return PersistentDataAPI.getBoolean((PersistentDataHolder)armorStand, (NamespacedKey)Keys.PDC_IS_DISPLAY_STAND);
    }

    @ParametersAreNonnullByDefault
    public static void panelAnimationStep(ArmorStand armorStand, boolean directionUp) {
        armorStand.setHeadPose(armorStand.getHeadPose().add(0.0, 0.1, 0.0));
    }

    @ParametersAreNonnullByDefault
    public static void panelAnimationReset(ArmorStand armorStand, Block block) {
        armorStand.setHeadPose(new EulerAngle(0.0, 0.0, 0.0));
        armorStand.teleport(block.getLocation().clone().add(0.5, -0.6, 0.5));
    }

    @Generated
    private ArmourStandUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

