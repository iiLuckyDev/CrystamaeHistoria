/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.staveconfigurator;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MenuBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstanceStave;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

public class StaveConfigurator
extends MenuBlock {
    private static final int[] BACKGROUND_SLOTS = new int[]{0, 1, 2, 3, 4, 6, 8, 9, 11, 13, 15, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 29, 31, 33, 35, 36, 37, 38, 39, 40, 42, 44};
    private static final int[] BACKGROUND_INPUT = new int[]{10, 28};
    private static final int STAVE_SLOT = 19;
    private static final int REMOVE_PLATES = 12;
    private static final int ADD_PLATES = 30;
    private static final int LEFT_CLICK_SLOT = 14;
    private static final int RIGHT_CLICK_SLOT = 16;
    private static final int SHIFT_LEFT_CLICK_SLOT = 32;
    private static final int SHIFT_RIGHT_CLICK_SLOT = 34;
    private static final int LEFT_NOTE = 5;
    private static final int RIGHT_NOTE = 7;
    private static final int S_LEFT_NOTE = 41;
    private static final int S_RIGHT_NOTE = 43;
    private static final int[] PLATE_SLOTS = new int[]{14, 16, 32, 34};

    @ParametersAreNonnullByDefault
    public StaveConfigurator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GuiElements.MENU_BACKGROUND, BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GuiElements.MENU_STAVE_INPUT, BACKGROUND_INPUT);
        blockMenuPreset.addItem(5, GuiElements.getSpellSlotPane(SpellSlot.LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(7, GuiElements.getSpellSlotPane(SpellSlot.RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(41, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_LEFT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(43, GuiElements.getSpellSlotPane(SpellSlot.SHIFT_RIGHT_CLICK), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(12, GuiElements.MENU_REMOVE_PLATES, (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(30, GuiElements.MENU_SAVE_STAVE, (player, i, itemStack, clickAction) -> false);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        blockMenu.addMenuClickHandler(12, (player, i, itemStack, clickAction) -> {
            ItemStack stave = blockMenu.getItemInSlot(19);
            SlimefunItem sfStave = SlimefunItem.getByItem((ItemStack)stave);
            this.rejectItems(blockMenu);
            if (stave == null || !(sfStave instanceof Stave)) {
                return false;
            }
            if (stave.getAmount() > 1) {
                blockMenu.dropItems(blockMenu.getLocation(), new int[]{19});
                return false;
            }
            InstanceStave staveInstance = new InstanceStave(stave);
            Map<SpellSlot, InstancePlate> map = staveInstance.getSpellInstanceMap();
            if (map != null) {
                for (Map.Entry<SpellSlot, InstancePlate> entry : map.entrySet()) {
                    SpellSlot spellSlot = entry.getKey();
                    InstancePlate instancePlate = map.get((Object)spellSlot);
                    ItemStack plate = ChargedPlate.getChargedPlate(instancePlate);
                    blockMenu.replaceExistingItem(this.getSlot(spellSlot), plate);
                }
            }
            staveInstance.getSpellInstanceMap().clear();
            ItemMeta itemMeta = stave.getItemMeta();
            DataTypeMethods.setCustom((PersistentDataHolder)itemMeta, Keys.PDC_STAVE_STORAGE, PersistentStaveDataType.TYPE, staveInstance.getSpellInstanceMap());
            stave.setItemMeta(itemMeta);
            staveInstance.buildLore();
            return false;
        });
        blockMenu.addMenuClickHandler(30, (player, i, itemStack, clickAction) -> {
            ItemStack stave = blockMenu.getItemInSlot(19);
            SlimefunItem sfStave = SlimefunItem.getByItem((ItemStack)stave);
            if (stave != null && sfStave instanceof Stave && !this.platesEmpty(blockMenu) && this.staveIsEmpty(stave)) {
                if (stave.getAmount() > 1) {
                    blockMenu.dropItems(blockMenu.getLocation(), new int[]{19});
                    return false;
                }
                InstanceStave staveInstance = new InstanceStave(stave);
                ItemMeta staveMeta = stave.getItemMeta();
                this.rejectInvalid(blockMenu);
                for (SpellSlot spellSlot : SpellSlot.getCashedValues()) {
                    ItemStack plate = blockMenu.getItemInSlot(this.getSlot(spellSlot));
                    if (plate == null || !(SlimefunItem.getByItem((ItemStack)plate) instanceof ChargedPlate)) continue;
                    InstancePlate instancePlate = DataTypeMethods.getCustom((PersistentDataHolder)plate.getItemMeta(), Keys.PDC_PLATE_STORAGE, PersistentPlateDataType.TYPE);
                    staveInstance.setSlot(spellSlot, instancePlate);
                }
                DataTypeMethods.setCustom((PersistentDataHolder)staveMeta, Keys.PDC_STAVE_STORAGE, PersistentStaveDataType.TYPE, staveInstance.getSpellInstanceMap());
                stave.setItemMeta(staveMeta);
                staveInstance.buildLore();
                this.clearPlates(blockMenu);
            }
            return false;
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        super.onBreak(e, menu);
        super.onBreak(e, menu);
        Location location = menu.getLocation();
        menu.dropItems(location, new int[]{14});
        menu.dropItems(location, new int[]{16});
        menu.dropItems(location, new int[]{32});
        menu.dropItems(location, new int[]{34});
        menu.dropItems(location, new int[]{19});
    }

    @ParametersAreNonnullByDefault
    public void rejectInvalid(BlockMenu blockMenu) {
        for (int slot : PLATE_SLOTS) {
            ItemStack itemStack = blockMenu.getItemInSlot(slot);
            SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)itemStack);
            if (slimefunItem instanceof ChargedPlate) continue;
            blockMenu.dropItems(blockMenu.getLocation(), new int[]{slot});
        }
    }

    @ParametersAreNonnullByDefault
    private void clearPlates(BlockMenu blockMenu) {
        blockMenu.replaceExistingItem(14, null);
        blockMenu.replaceExistingItem(16, null);
        blockMenu.replaceExistingItem(32, null);
        blockMenu.replaceExistingItem(34, null);
    }

    @ParametersAreNonnullByDefault
    private void rejectItems(BlockMenu blockMenu) {
        blockMenu.dropItems(blockMenu.getLocation(), new int[]{14, 16, 32, 34});
    }

    @ParametersAreNonnullByDefault
    private boolean platesEmpty(BlockMenu blockMenu) {
        for (int slot : PLATE_SLOTS) {
            ItemStack itemStack = blockMenu.getItemInSlot(slot);
            if (itemStack == null) continue;
            return false;
        }
        return true;
    }

    @ParametersAreNonnullByDefault
    private boolean staveIsEmpty(ItemStack stave) {
        InstanceStave instanceStave = new InstanceStave(stave);
        return instanceStave.getSpellInstanceMap().size() == 0;
    }

    @ParametersAreNonnullByDefault
    private int getSlot(SpellSlot spellSlot) {
        switch (spellSlot) {
            case LEFT_CLICK: {
                return 14;
            }
            case RIGHT_CLICK: {
                return 16;
            }
            case SHIFT_LEFT_CLICK: {
                return 32;
            }
            case SHIFT_RIGHT_CLICK: {
                return 34;
            }
        }
        throw new IllegalStateException("Unexpected value: " + String.valueOf((Object)spellSlot));
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }
}

