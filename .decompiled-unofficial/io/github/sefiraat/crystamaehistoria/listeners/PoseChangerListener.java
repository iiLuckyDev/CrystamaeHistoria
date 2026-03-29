/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  net.kyori.adventure.text.Component
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerInteractAtEntityEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.util.EulerAngle
 */
package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.ImbuedStand;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PoseChanger;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PoseCloner;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPoseType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.text.MessageFormat;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.util.EulerAngle;

public class PoseChangerListener
implements Listener {
    private static final String IMBUED_ONLY_MESSAGE = String.valueOf(ThemeType.WARNING.getColor()) + "This can only be done to an Imbued Armorstand";
    private static final double STEP_AMOUNT = 0.01;
    private final NamespacedKey poseKey = Keys.newKey("pose_type");
    private final NamespacedKey changeKey = Keys.newKey("change_Type");
    private final NamespacedKey clonedPoseKey = Keys.newKey("stored_pose");

    @EventHandler(priority=EventPriority.LOW)
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)heldItem);
        if (slimefunItem instanceof PoseChanger && e.getAction().isLeftClick()) {
            e.setCancelled(true);
            this.changeMode(player, heldItem, player.isSneaking());
        }
    }

    @EventHandler(priority=EventPriority.LOW)
    public void onPoseChange(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)heldItem);
        if (slimefunItem instanceof PoseChanger) {
            Entity entity = e.getRightClicked();
            e.setCancelled(true);
            if (entity instanceof ArmorStand) {
                ArmorStand armorStand = (ArmorStand)entity;
                boolean isImbued = PersistentDataAPI.getBoolean((PersistentDataHolder)armorStand, (NamespacedKey)ImbuedStand.KEY);
                if ((armorStand.isVisible() || isImbued) && !armorStand.isMarker() && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.INTERACT_ENTITY) && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.ATTACK_ENTITY)) {
                    ItemMeta itemMeta = heldItem.getItemMeta();
                    PoseType poseType = PoseType.valueOf(PersistentDataAPI.getString((PersistentDataHolder)itemMeta, (NamespacedKey)this.poseKey, (String)"HEAD"));
                    ChangeType changeType = ChangeType.valueOf(PersistentDataAPI.getString((PersistentDataHolder)itemMeta, (NamespacedKey)this.changeKey, (String)"RESET"));
                    this.changePose(player, armorStand, poseType, changeType, player.isSneaking(), isImbued);
                }
            }
        }
    }

    @EventHandler(priority=EventPriority.LOW)
    public void onPoseClone(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)heldItem);
        if (slimefunItem instanceof PoseCloner) {
            ArmorStand armorStand;
            Entity entity = e.getRightClicked();
            e.setCancelled(true);
            if (entity instanceof ArmorStand && !(armorStand = (ArmorStand)entity).isMarker() && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.INTERACT_ENTITY) && GeneralUtils.hasPermission(player, armorStand.getLocation(), Interaction.ATTACK_ENTITY)) {
                boolean isImbued = PersistentDataAPI.getBoolean((PersistentDataHolder)armorStand, (NamespacedKey)ImbuedStand.KEY);
                if (!isImbued) {
                    player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "This can only be done to an Imbued Armorstand");
                    return;
                }
                ItemMeta itemMeta = heldItem.getItemMeta();
                if (player.isSneaking()) {
                    PoseCloner.StoredPose pose = DataTypeMethods.getCustom((PersistentDataHolder)itemMeta, this.clonedPoseKey, PersistentPoseType.TYPE);
                    if (pose == null) {
                        player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "No pose has been stored.");
                        return;
                    }
                    pose.applyPose(armorStand);
                    player.sendActionBar((Component)Component.text((String)(String.valueOf(ThemeType.WARNING.getColor()) + "Pose Applied")));
                } else {
                    PoseCloner.StoredPose pose = new PoseCloner.StoredPose(armorStand);
                    DataTypeMethods.setCustom((PersistentDataHolder)itemMeta, this.clonedPoseKey, PersistentPoseType.TYPE, pose);
                    heldItem.setItemMeta(itemMeta);
                    player.sendActionBar((Component)Component.text((String)(String.valueOf(ThemeType.WARNING.getColor()) + "Pose Stored")));
                }
            }
        }
    }

    private void changeMode(@Nonnull Player player, @Nonnull ItemStack heldItem, boolean pose) {
        ItemMeta itemMeta = heldItem.getItemMeta();
        if (!pose) {
            PoseType poseType = PoseType.valueOf(PersistentDataAPI.getString((PersistentDataHolder)itemMeta, (NamespacedKey)this.poseKey, (String)"HEAD"));
            PoseType nextType = poseType.getNext();
            String message = MessageFormat.format("{0}Pose type: {1}{2}", new Object[]{ThemeType.ERROR.getColor(), ThemeType.CLICK_INFO.getColor(), nextType});
            List lore = itemMeta.getLore();
            lore.set(lore.size() - 4, message);
            itemMeta.setLore(lore);
            PersistentDataAPI.setString((PersistentDataHolder)itemMeta, (NamespacedKey)this.poseKey, (String)nextType.toString());
            player.sendActionBar((Component)Component.text((String)message));
        } else {
            ChangeType changeType = ChangeType.valueOf(PersistentDataAPI.getString((PersistentDataHolder)itemMeta, (NamespacedKey)this.changeKey, (String)"RESET"));
            ChangeType nextType = changeType.getNext();
            String message = MessageFormat.format("{0}Change type: {1}{2}", new Object[]{ThemeType.ERROR.getColor(), ThemeType.CLICK_INFO.getColor(), nextType});
            List lore = itemMeta.getLore();
            lore.set(lore.size() - 3, message);
            itemMeta.setLore(lore);
            PersistentDataAPI.setString((PersistentDataHolder)itemMeta, (NamespacedKey)this.changeKey, (String)nextType.toString());
            player.sendActionBar((Component)Component.text((String)message));
        }
        heldItem.setItemMeta(itemMeta);
    }

    @ParametersAreNonnullByDefault
    private void changePose(Player player, ArmorStand armorStand, PoseType poseType, ChangeType changeType, boolean negative, boolean isImbued) {
        EulerAngle eulerAngle = this.getEulerAngle(player, armorStand, poseType, isImbued);
        if (eulerAngle == null) {
            return;
        }
        eulerAngle = this.getChangedEulerAngle(eulerAngle, changeType, negative);
        this.setEulerAngle(armorStand, poseType, eulerAngle);
    }

    @Nullable
    private EulerAngle getEulerAngle(@Nonnull Player player, @Nonnull ArmorStand armorStand, @Nonnull PoseType poseType, boolean isImbued) {
        switch (poseType.ordinal()) {
            case 0: {
                return armorStand.getHeadPose();
            }
            case 1: {
                return armorStand.getBodyPose();
            }
            case 2: {
                return armorStand.getLeftArmPose();
            }
            case 3: {
                return armorStand.getRightArmPose();
            }
            case 4: {
                return armorStand.getLeftLegPose();
            }
            case 5: {
                return armorStand.getRightLegPose();
            }
            case 6: {
                armorStand.setArms(!armorStand.hasArms());
                return null;
            }
            case 7: {
                armorStand.setBasePlate(!armorStand.hasBasePlate());
                return null;
            }
            case 8: {
                if (isImbued) {
                    armorStand.setVisible(!armorStand.isVisible());
                } else {
                    player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "This can only be done to an Imbued Armorstand");
                }
                return null;
            }
            case 9: {
                if (isImbued) {
                    armorStand.setSmall(!armorStand.isSmall());
                } else {
                    player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "This can only be done to an Imbued Armorstand");
                }
                return null;
            }
            case 10: {
                if (isImbued) {
                    armorStand.setGravity(!armorStand.hasGravity());
                    player.sendActionBar((Component)Component.text((String)("Gravity: " + armorStand.hasGravity())));
                } else {
                    player.sendMessage(String.valueOf(ThemeType.WARNING.getColor()) + "This can only be done to an Imbued Armorstand");
                }
                return null;
            }
        }
        return null;
    }

    private EulerAngle getChangedEulerAngle(@Nonnull EulerAngle eulerAngle, @Nonnull ChangeType changeType, boolean negative) {
        double amount = negative ? -0.01 : 0.01;
        switch (changeType.ordinal()) {
            case 0: {
                return EulerAngle.ZERO;
            }
            case 1: {
                return eulerAngle.add(amount, 0.0, 0.0);
            }
            case 2: {
                return eulerAngle.add(0.0, amount, 0.0);
            }
            case 3: {
                return eulerAngle.add(0.0, 0.0, amount);
            }
        }
        throw new IllegalStateException("Unexpected value: " + String.valueOf((Object)changeType));
    }

    private void setEulerAngle(@Nonnull ArmorStand armorStand, @Nonnull PoseType poseType, @Nonnull EulerAngle eulerAngle) {
        switch (poseType.ordinal()) {
            case 0: {
                armorStand.setHeadPose(eulerAngle);
                break;
            }
            case 1: {
                armorStand.setBodyPose(eulerAngle);
                break;
            }
            case 2: {
                armorStand.setLeftArmPose(eulerAngle);
                break;
            }
            case 3: {
                armorStand.setRightArmPose(eulerAngle);
                break;
            }
            case 4: {
                armorStand.setLeftLegPose(eulerAngle);
                break;
            }
            case 5: {
                armorStand.setRightLegPose(eulerAngle);
                break;
            }
        }
    }

    private static enum PoseType {
        HEAD,
        BODY,
        LEFT_ARM,
        RIGHT_ARM,
        LEFT_LEG,
        RIGHT_LEG,
        ARM_VISIBILITY,
        BASE_VISIBILITY,
        STAND_VISIBILITY,
        STAND_SIZE,
        STAND_GRAVITY,
        STAND_POSITION;

        private static final PoseType[] CACHED_VALUES;

        public PoseType getNext() {
            PoseType nextType = CACHED_VALUES[0];
            for (int i = 0; i < CACHED_VALUES.length; ++i) {
                if (CACHED_VALUES[i] != this) continue;
                nextType = i == CACHED_VALUES.length - 1 ? CACHED_VALUES[0] : CACHED_VALUES[i + 1];
            }
            return nextType;
        }

        static {
            CACHED_VALUES = PoseType.values();
        }
    }

    private static enum ChangeType {
        RESET,
        X,
        Y,
        Z;

        private static final ChangeType[] CACHED_VALUES;

        public ChangeType getNext() {
            ChangeType nextType = CACHED_VALUES[0];
            for (int i = 0; i < CACHED_VALUES.length; ++i) {
                if (CACHED_VALUES[i] != this) continue;
                nextType = i == CACHED_VALUES.length - 1 ? CACHED_VALUES[0] : CACHED_VALUES[i + 1];
            }
            return nextType;
        }

        static {
            CACHED_VALUES = ChangeType.values();
        }
    }
}

