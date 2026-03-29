/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler
 *  lombok.Generated
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.PotionMeta
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.TickingBlockNoGui;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobTrap
extends TickingBlockNoGui {
    private final Map<Location, PotionEffectType> potionEffectTypeMap = new HashMap<Location, PotionEffectType>();

    @ParametersAreNonnullByDefault
    public MobTrap(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        this.addItemHandler(new ItemHandler[]{this.onBlockUse()});
    }

    private BlockUseHandler onBlockUse() {
        return e -> {
            ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
            Optional optionalBlock = e.getClickedBlock();
            if (itemStack.getType() == Material.POTION && optionalBlock.isPresent()) {
                Block block = (Block)optionalBlock.get();
                PotionMeta potionMeta = (PotionMeta)itemStack.getItemMeta();
                PotionEffectType type = potionMeta.getBasePotionData().getType().getEffectType();
                if (type != null) {
                    BlockStorage.addBlockInfo((Block)block, (String)"POT_EFF", (String)type.getName());
                    this.potionEffectTypeMap.put(block.getLocation(), type);
                    itemStack.setAmount(itemStack.getAmount() - 1);
                }
            }
        };
    }

    @Override
    protected void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        String potionEffectString = BlockStorage.getLocationInfo((Location)block.getLocation(), (String)"POT_EFF");
        if (potionEffectString != null) {
            this.potionEffectTypeMap.put(block.getLocation(), PotionEffectType.getByName((String)potionEffectString));
        }
    }

    @Override
    protected void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        Location location = block.getLocation().add(0.5, 0.5, 0.5);
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.AQUA, 1.0f);
        Collection entities = location.getWorld().getNearbyEntities(location, 0.5, 0.5, 0.5, LivingEntity.class::isInstance);
        PotionEffectType type = this.potionEffectTypeMap.get(block.getLocation());
        if (type != null) {
            for (Entity entity : entities) {
                LivingEntity livingEntity = (LivingEntity)entity;
                livingEntity.addPotionEffect(new PotionEffect(type, 40, 0));
                ParticleUtils.displayParticleEffect(location, 1.0, 3, dustOptions);
            }
        }
    }

    @Override
    protected void onPlace(@Nonnull BlockPlaceEvent event) {
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
        BlockStorage.clearBlockInfo((Block)blockBreakEvent.getBlock());
    }

    @Generated
    public Map<Location, PotionEffectType> getPotionEffectTypeMap() {
        return this.potionEffectTypeMap;
    }
}

