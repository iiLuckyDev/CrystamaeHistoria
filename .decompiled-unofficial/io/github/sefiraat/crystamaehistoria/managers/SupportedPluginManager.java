/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bgsoftware.wildstacker.api.WildStackerAPI
 *  com.gmail.nossr50.util.skills.CombatUtils
 *  dev.rosewood.rosestacker.api.RoseStackerAPI
 *  dev.rosewood.rosestacker.stack.StackedItem
 *  io.github.thebusybiscuit.exoticgarden.items.BonemealableItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Bukkit
 *  org.bukkit.NamespacedKey
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.managers;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import com.gmail.nossr50.util.skills.CombatUtils;
import dev.rosewood.rosestacker.api.RoseStackerAPI;
import dev.rosewood.rosestacker.stack.StackedItem;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.exoticgarden.items.BonemealableItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.Plugin;

public class SupportedPluginManager {
    private static final NamespacedKey IGNORE_DAMAGE_KEY = new NamespacedKey((Plugin)Slimefun.instance(), "ignore_damage");
    private static SupportedPluginManager instance;
    private boolean mcMMO;
    private boolean exoticGarden;
    private boolean slimeTinker;
    private boolean headLimiter;
    private boolean networks;
    private boolean wildStacker;
    private boolean roseStacker;
    private boolean netheopoiesis;
    private RoseStackerAPI roseStackerAPI;

    public SupportedPluginManager() {
        instance = this;
        Bukkit.getScheduler().runTaskLater(CrystamaeHistoria.instance(), this::postSetup, 1L);
        this.netheopoiesis = Bukkit.getPluginManager().isPluginEnabled("Netheopoiesis");
    }

    private void postSetup() {
        this.mcMMO = Bukkit.getPluginManager().isPluginEnabled("mcMMO");
        this.exoticGarden = Bukkit.getPluginManager().isPluginEnabled("ExoticGarden");
        this.slimeTinker = Bukkit.getPluginManager().isPluginEnabled("SlimeTinker");
        this.headLimiter = Bukkit.getPluginManager().isPluginEnabled("HeadLimiter");
        this.networks = Bukkit.getPluginManager().isPluginEnabled("Networks");
        this.wildStacker = Bukkit.getPluginManager().isPluginEnabled("WildStacker");
        this.roseStacker = Bukkit.getPluginManager().isPluginEnabled("RoseStacker");
        if (this.roseStacker) {
            this.roseStackerAPI = RoseStackerAPI.getInstance();
        }
    }

    @ParametersAreNonnullByDefault
    public void playerDamageWithoutAttribution(LivingEntity livingEntity, Player player, double damage) {
        this.markIgnoreDamage(livingEntity);
        livingEntity.damage(damage, (Entity)player);
        this.clearIgnoreDamageMarker(livingEntity);
    }

    @ParametersAreNonnullByDefault
    public void markIgnoreDamage(LivingEntity livingEntity) {
        if (this.mcMMO) {
            CombatUtils.applyIgnoreDamageMetadata((LivingEntity)livingEntity);
        }
        if (this.slimeTinker) {
            PersistentDataAPI.setBoolean((PersistentDataHolder)livingEntity, (NamespacedKey)IGNORE_DAMAGE_KEY, (boolean)true);
        }
    }

    @ParametersAreNonnullByDefault
    public void clearIgnoreDamageMarker(LivingEntity livingEntity) {
        if (this.mcMMO) {
            CombatUtils.removeIgnoreDamageMetadata((LivingEntity)livingEntity);
        }
        if (this.slimeTinker) {
            PersistentDataAPI.remove((PersistentDataHolder)livingEntity, (NamespacedKey)IGNORE_DAMAGE_KEY);
        }
    }

    @ParametersAreNonnullByDefault
    public boolean isExoticGardenPlant(Block block) {
        return this.exoticGarden && BlockStorage.hasBlockInfo((Block)block) && BlockStorage.check((Block)block) instanceof BonemealableItem;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public SlimefunItem getExoticGardenPlant(Block block) {
        SlimefunItem slimefunItem;
        if (this.exoticGarden && BlockStorage.hasBlockInfo((Block)block) && (slimefunItem = BlockStorage.check((Block)block)) instanceof BonemealableItem) {
            return slimefunItem;
        }
        return null;
    }

    public int getStackAmount(Item item) {
        if (this.wildStacker) {
            return WildStackerAPI.getItemAmount((Item)item);
        }
        if (this.roseStacker) {
            StackedItem stackedItem = this.roseStackerAPI.getStackedItem(item);
            return stackedItem == null ? item.getItemStack().getAmount() : stackedItem.getStackSize();
        }
        return item.getItemStack().getAmount();
    }

    public void setStackAmount(Item item, int amount) {
        if (this.wildStacker) {
            WildStackerAPI.getStackedItem((Item)item).setStackAmount(amount, true);
        } else if (this.roseStacker) {
            StackedItem stackedItem = this.roseStackerAPI.getStackedItem(item);
            stackedItem.setStackSize(amount);
        } else {
            item.getItemStack().setAmount(amount);
        }
    }

    public static boolean isMcMMO() {
        return SupportedPluginManager.instance.mcMMO;
    }

    public static boolean isExoticGarden() {
        return SupportedPluginManager.instance.exoticGarden;
    }

    public static boolean isSlimeTinker() {
        return SupportedPluginManager.instance.slimeTinker;
    }

    public static boolean isHeadLimiter() {
        return SupportedPluginManager.instance.headLimiter;
    }

    public static boolean isNetworks() {
        return SupportedPluginManager.instance.networks;
    }

    public static boolean isWildStacker() {
        return SupportedPluginManager.instance.wildStacker;
    }

    public static boolean isRoseStacker() {
        return SupportedPluginManager.instance.roseStacker;
    }

    public boolean isNetheopoiesis() {
        return this.netheopoiesis;
    }

    @Generated
    public RoseStackerAPI getRoseStackerAPI() {
        return this.roseStackerAPI;
    }
}

