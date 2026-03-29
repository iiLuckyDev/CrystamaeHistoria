/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
 *  io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.TickingMenuBlock;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

@ParametersAreNonnullByDefault
public abstract class AbstractMachineBlock
extends TickingMenuBlock
implements EnergyNetComponent {
    protected int energyPerTick = -1;
    protected int energyCapacity = -1;

    public AbstractMachineBlock(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void tick(Block b, BlockMenu menu) {
        if (this.getCharge(menu.getLocation()) < this.energyPerTick) {
            if (menu.hasViewer()) {
                menu.replaceExistingItem(this.getStatusSlot(), NO_ENERGY_ITEM);
            }
        } else if (this.process(b, menu)) {
            this.removeCharge(menu.getLocation(), this.energyPerTick);
        }
    }

    protected abstract boolean process(Block var1, BlockMenu var2);

    protected abstract int getStatusSlot();

    public final int getCapacity() {
        return this.energyCapacity;
    }

    @Nonnull
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    public final void register(@Nonnull SlimefunAddon addon) {
        if (this.energyPerTick == -1) {
            throw new IllegalStateException("You must call .energyPerTick() before registering!");
        }
        if (this.energyCapacity == -1) {
            this.energyCapacity = this.energyPerTick * 2;
        }
        super.register(addon);
    }

    @Nonnull
    @Generated
    public AbstractMachineBlock energyPerTick(int energyPerTick) {
        this.energyPerTick = energyPerTick;
        return this;
    }

    @Nonnull
    @Generated
    public AbstractMachineBlock energyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
        return this;
    }
}

