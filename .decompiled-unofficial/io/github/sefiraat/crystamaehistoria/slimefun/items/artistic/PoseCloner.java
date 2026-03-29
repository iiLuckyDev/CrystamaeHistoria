/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
 *  lombok.Generated
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.EulerAngle
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class PoseCloner
extends UnplaceableBlock {
    public PoseCloner(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void preRegister() {
        super.preRegister();
        this.addItemHandler(new ItemHandler[]{this.onBlockPlace()});
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(this, false){

            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                e.setCancelled(true);
            }
        };
    }

    public static class StoredPose {
        private final EulerAngle head;
        private final EulerAngle body;
        private final EulerAngle leftArm;
        private final EulerAngle rightArm;
        private final EulerAngle leftLeg;
        private final EulerAngle rightLeg;
        private final boolean isSmall;
        private final boolean isVisible;
        private final boolean isPlateVisible;
        private final boolean armsVisible;
        private final boolean hasGravity;

        public StoredPose(@Nonnull ArmorStand armorStand) {
            this(armorStand.getHeadPose(), armorStand.getBodyPose(), armorStand.getLeftArmPose(), armorStand.getRightArmPose(), armorStand.getLeftLegPose(), armorStand.getRightLegPose(), armorStand.isSmall(), armorStand.isVisible(), armorStand.hasBasePlate(), armorStand.hasArms(), armorStand.hasGravity());
        }

        public StoredPose(EulerAngle head, EulerAngle body, EulerAngle leftArm, EulerAngle rightArm, EulerAngle leftLeg, EulerAngle rightLeg, boolean isSmall, boolean isVisible, boolean isPlateVisible, boolean armsVisible, boolean hasGravity) {
            this.head = head;
            this.body = body;
            this.leftArm = leftArm;
            this.rightArm = rightArm;
            this.leftLeg = leftLeg;
            this.rightLeg = rightLeg;
            this.isSmall = isSmall;
            this.isVisible = isVisible;
            this.isPlateVisible = isPlateVisible;
            this.armsVisible = armsVisible;
            this.hasGravity = hasGravity;
        }

        public void applyPose(@Nonnull ArmorStand armorStand) {
            armorStand.setHeadPose(this.head);
            armorStand.setBodyPose(this.body);
            armorStand.setLeftArmPose(this.leftArm);
            armorStand.setRightArmPose(this.rightArm);
            armorStand.setLeftLegPose(this.leftLeg);
            armorStand.setRightLegPose(this.rightLeg);
            armorStand.setSmall(this.isSmall);
            armorStand.setVisible(this.isVisible);
            armorStand.setBasePlate(this.isPlateVisible);
            armorStand.setArms(this.armsVisible);
            armorStand.setGravity(this.hasGravity);
        }

        @Generated
        public EulerAngle getHead() {
            return this.head;
        }

        @Generated
        public EulerAngle getBody() {
            return this.body;
        }

        @Generated
        public EulerAngle getLeftArm() {
            return this.leftArm;
        }

        @Generated
        public EulerAngle getRightArm() {
            return this.rightArm;
        }

        @Generated
        public EulerAngle getLeftLeg() {
            return this.leftLeg;
        }

        @Generated
        public EulerAngle getRightLeg() {
            return this.rightLeg;
        }

        @Generated
        public boolean isSmall() {
            return this.isSmall;
        }

        @Generated
        public boolean isVisible() {
            return this.isVisible;
        }

        @Generated
        public boolean isPlateVisible() {
            return this.isPlateVisible;
        }

        @Generated
        public boolean isArmsVisible() {
            return this.armsVisible;
        }

        @Generated
        public boolean isHasGravity() {
            return this.hasGravity;
        }
    }
}

