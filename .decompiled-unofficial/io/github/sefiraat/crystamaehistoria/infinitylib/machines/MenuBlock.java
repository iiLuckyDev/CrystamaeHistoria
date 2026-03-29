/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
 *  me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MenuBlockPreset;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

@ParametersAreNonnullByDefault
public abstract class MenuBlock
extends SlimefunItem {
    public static final ItemStack PROCESSING_ITEM = CustomItemStack.create((Material)Material.LIME_STAINED_GLASS_PANE, (String)"&aProcessing...", (String[])new String[0]);
    public static final ItemStack NO_ENERGY_ITEM = CustomItemStack.create((Material)Material.RED_STAINED_GLASS_PANE, (String)"&cNot enough energy!", (String[])new String[0]);
    public static final ItemStack IDLE_ITEM = CustomItemStack.create((Material)Material.BLACK_STAINED_GLASS_PANE, (String)"&8Idle", (String[])new String[0]);
    public static final ItemStack NO_ROOM_ITEM = CustomItemStack.create((Material)Material.ORANGE_STAINED_GLASS_PANE, (String)"&6Not enough room!", (String[])new String[0]);
    public static final ItemStack OUTPUT_BORDER = CustomItemStack.create((ItemStack)ChestMenuUtils.getOutputSlotTexture(), (String)"&6Output", (String[])new String[0]);
    public static final ItemStack INPUT_BORDER = CustomItemStack.create((ItemStack)ChestMenuUtils.getInputSlotTexture(), (String)"&9Input", (String[])new String[0]);
    public static final ItemStack BACKGROUND_ITEM = ChestMenuUtils.getBackground();

    public MenuBlock(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        this.addItemHandler(new ItemHandler[]{new BlockBreakHandler(false, false){

            public void onPlayerBreak(BlockBreakEvent e, ItemStack itemStack, List<ItemStack> list) {
                BlockMenu menu = BlockStorage.getInventory((Block)e.getBlock());
                if (menu != null) {
                    MenuBlock.this.onBreak(e, menu);
                }
            }
        }, new BlockPlaceHandler(false){

            public void onPlayerPlace(BlockPlaceEvent e) {
                MenuBlock.this.onPlace(e, e.getBlockPlaced());
            }
        }});
    }

    public final void postRegister() {
        new MenuBlockPreset(this);
    }

    protected abstract void setup(BlockMenuPreset var1);

    @Nonnull
    protected final int[] getTransportSlots(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
        switch (flow) {
            case INSERT: {
                return this.getInputSlots(menu, item);
            }
            case WITHDRAW: {
                return this.getOutputSlots();
            }
        }
        return new int[0];
    }

    protected int[] getInputSlots(DirtyChestMenu menu, ItemStack item) {
        return this.getInputSlots();
    }

    protected abstract int[] getInputSlots();

    protected abstract int[] getOutputSlots();

    protected void onNewInstance(BlockMenu menu, Block b) {
    }

    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        Location l = menu.getLocation();
        menu.dropItems(l, this.getInputSlots());
        menu.dropItems(l, this.getOutputSlots());
    }

    protected void onPlace(BlockPlaceEvent e, Block b) {
    }
}

