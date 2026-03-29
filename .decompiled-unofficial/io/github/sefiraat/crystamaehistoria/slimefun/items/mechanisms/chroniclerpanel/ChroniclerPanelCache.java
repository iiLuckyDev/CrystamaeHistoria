/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.block.data.type.Light
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.runnables.spells.FloatingHeadAnimation;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ChroniclerPanelCache
extends AbstractCache {
    private final int tier;
    @Nullable
    private Material workingOn;
    private boolean working;
    private BlockDefinition blockDefinition;
    private FloatingHeadAnimation animation;
    private Location blockMiddle;
    private boolean lightDimming = true;
    private UUID armorStandUUID;

    @ParametersAreNonnullByDefault
    public ChroniclerPanelCache(BlockMenu blockMenu, int tier) {
        super(blockMenu);
        String activePlayerString;
        this.tier = tier;
        String workingOnString = BlockStorage.getLocationInfo((Location)blockMenu.getLocation(), (String)"BS_CP_WORKING_ON");
        if (workingOnString != null) {
            this.setWorking(blockMenu.getBlock(), Material.valueOf((String)workingOnString));
        }
        if ((activePlayerString = BlockStorage.getLocationInfo((Location)blockMenu.getLocation(), (String)"BS_CP_ACTIVE_PLAYER")) != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    @ParametersAreNonnullByDefault
    protected void setWorking(Block block, Material material) {
        Block lightBlock = block.getRelative(BlockFace.UP);
        this.blockMiddle = block.getLocation().clone().add(0.5, 0.5, 0.5);
        this.workingOn = material;
        this.working = true;
        BlockStorage.addBlockInfo((Block)block, (String)"BS_CP_WORKING_ON", (String)material.toString());
        if (lightBlock.getType() == Material.AIR) {
            lightBlock.setType(Material.LIGHT);
        }
        this.startAnimation();
        this.blockDefinition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);
    }

    private void startAnimation() {
        ArmorStand armourStand = this.getDisplayStand();
        ArmourStandUtils.panelAnimationReset(armourStand, this.blockMenu.getBlock());
        this.animation = new FloatingHeadAnimation(armourStand);
        this.animation.runTaskTimer((Plugin)CrystamaeHistoria.getInstance(), 0L, 1L);
    }

    @ParametersAreNonnullByDefault
    private ArmorStand getDisplayStand() {
        if (this.armorStandUUID == null) {
            String uuidString = BlockStorage.getLocationInfo((Location)this.getLocation(), (String)"ch_display_stand");
            if (uuidString != null) {
                this.armorStandUUID = UUID.fromString(uuidString);
            } else {
                Block block = this.blockMenu.getBlock();
                ArmorStand armorStand = (ArmorStand)block.getWorld().spawnEntity(this.getLocation().add(0.5, -0.6, 0.5), EntityType.ARMOR_STAND);
                ArmourStandUtils.setDisplay(armorStand);
                BlockStorage.addBlockInfo((Location)block.getLocation(), (String)"ch_display_stand", (String)armorStand.getUniqueId().toString());
                this.armorStandUUID = armorStand.getUniqueId();
                return armorStand;
            }
        }
        return (ArmorStand)Bukkit.getEntity((UUID)this.armorStandUUID);
    }

    protected Location getLocation() {
        return this.blockMenu.getLocation().clone();
    }

    protected void process() {
        Block block = this.blockMenu.getBlock();
        ItemStack inputItem = this.blockMenu.getItemInSlot(22);
        if (inputItem == null || inputItem.getType() == Material.AIR) {
            if (this.tier >= 5) {
                this.tryInsertItem();
            }
            return;
        }
        if (!StoryUtils.canBeStoried(inputItem, this.tier + 1)) {
            this.reject(inputItem);
            this.shutdown();
            return;
        }
        this.rejectOverage(inputItem);
        if (!StoryUtils.isStoried(inputItem)) {
            StoryUtils.makeStoried(inputItem);
        }
        if (!StoryUtils.hasRemainingStorySlots(inputItem)) {
            if (this.tier >= 5) {
                this.pushOutItem();
            }
            this.shutdown();
            return;
        }
        Material inputItemType = inputItem.getType();
        if (!this.working || this.workingOn != inputItemType) {
            this.setWorking(block, inputItemType);
            ArmourStandUtils.setDisplayItem(this.getDisplayStand(), this.workingOn);
        } else {
            this.animateLight();
            this.processStack(inputItem);
        }
    }

    private void tryInsertItem() {
        Collection entities = this.getWorld().getNearbyEntities(this.getLocation().clone().add(0.5, 0.5, 0.5), 0.3, 0.3, 0.3, Item.class::isInstance);
        if (entities.isEmpty()) {
            this.shutdown();
        } else {
            Item item = entities.stream().findFirst().orElse(null);
            ItemStack itemStack = item.getItemStack();
            ItemStack clone = itemStack.asQuantity(1);
            this.blockMenu.replaceExistingItem(22, clone);
            int amount = CrystamaeHistoria.getSupportedPluginManager().getStackAmount(item);
            if (amount == 1) {
                item.remove();
            } else {
                CrystamaeHistoria.getSupportedPluginManager().setStackAmount(item, amount - 1);
            }
        }
    }

    private void pushOutItem() {
        Location pushLocation = this.blockMiddle.clone().subtract(0.0, 1.0, 0.0);
        pushLocation.getWorld().dropItem(pushLocation, this.blockMenu.getItemInSlot(22).clone());
        this.blockMenu.replaceExistingItem(22, null);
    }

    public void shutdown() {
        if (this.working) {
            this.setNotWorking(this.blockMenu.getBlock());
            ArmourStandUtils.clearDisplayItem(this.getDisplayStand());
        }
    }

    @ParametersAreNonnullByDefault
    protected void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            this.blockMenu.getBlock().getWorld().dropItemNaturally(this.blockMenu.getLocation(), i2);
        }
    }

    protected void reject(@Nullable ItemStack itemStack) {
        if (itemStack != null) {
            ItemStack rejectedSpawn = itemStack.clone();
            itemStack.setAmount(0);
            this.blockMenu.getBlock().getWorld().dropItemNaturally(this.blockMenu.getLocation(), rejectedSpawn);
        }
    }

    @ParametersAreNonnullByDefault
    private void setNotWorking(Block block) {
        Block lightBlock = block.getRelative(BlockFace.UP);
        this.workingOn = null;
        this.working = false;
        BlockStorage.addBlockInfo((Block)block, (String)"BS_CP_WORKING_ON", null);
        if (lightBlock.getType() == Material.LIGHT) {
            lightBlock.setType(Material.AIR);
        }
        if (this.animation != null) {
            this.animation.cancel();
        }
        ArmourStandUtils.panelAnimationReset(this.getDisplayStand(), block);
    }

    @ParametersAreNonnullByDefault
    private void processStack(ItemStack i) {
        this.summonParticles();
        if (StoryUtils.hasRemainingStorySlots(i)) {
            int remaining = StoryUtils.getRemainingStoryAmount(i);
            int req = this.blockDefinition.getBlockTier().chroniclingChance;
            if (GeneralUtils.testChance(req, 10000)) {
                StoryUtils.requestNewStory(i);
                if (remaining == 1) {
                    StoryUtils.requestUniqueStory(i);
                    if (this.activePlayer != null) {
                        if (!PlayerStatistics.hasUnlockedUniqueStory(this.activePlayer, this.blockDefinition)) {
                            PlayerStatistics.unlockUniqueStory(this.activePlayer, this.blockDefinition);
                        }
                        PlayerStatistics.addChronicle(this.activePlayer, this.blockDefinition);
                    }
                }
                StoriesManager.rebuildStoriedStack(i);
                this.blockMenu.getBlock().getWorld().strikeLightningEffect(this.blockMiddle);
            }
        }
    }

    private void animateLight() {
        Block block = this.blockMenu.getBlock().getRelative(BlockFace.UP);
        if (block.getType() == Material.LIGHT) {
            Light light = (Light)block.getBlockData();
            int level = light.getLevel();
            if (level >= 15) {
                light.setLevel(level - 1);
                this.lightDimming = true;
            } else if (level <= 5) {
                light.setLevel(level + 1);
                this.lightDimming = false;
            } else {
                light.setLevel(this.lightDimming ? level - 1 : level + 1);
            }
            block.setBlockData((BlockData)light);
        }
    }

    private void summonParticles() {
        Location location = this.blockMenu.getLocation();
        for (int i = 0; i < 2; ++i) {
            Location l = location.clone().add(ThreadLocalRandom.current().nextDouble(0.0, 1.1), 1.0, ThreadLocalRandom.current().nextDouble(0.0, 1.1));
            location.getWorld().spawnParticle(Particle.ENCHANT, l, 0, 0.2, 0.0, -0.2, 0.0);
        }
    }

    protected void kill() {
        this.setNotWorking(this.blockMenu.getBlock());
        this.getDisplayStand().remove();
    }

    protected World getWorld() {
        return this.blockMenu.getLocation().getWorld();
    }

    protected Location getLocation(boolean centered) {
        if (centered) {
            return this.getLocation().add(0.5, 0.5, 0.5);
        }
        return this.getLocation();
    }

    @Generated
    public int getTier() {
        return this.tier;
    }

    @Nullable
    @Generated
    public Material getWorkingOn() {
        return this.workingOn;
    }

    @Generated
    public boolean isWorking() {
        return this.working;
    }

    @Generated
    public BlockDefinition getBlockDefinition() {
        return this.blockDefinition;
    }

    @Generated
    public FloatingHeadAnimation getAnimation() {
        return this.animation;
    }

    @Generated
    public Location getBlockMiddle() {
        return this.blockMiddle;
    }

    @Generated
    public boolean isLightDimming() {
        return this.lightDimming;
    }

    @Generated
    public UUID getArmorStandUUID() {
        return this.armorStandUUID;
    }
}

