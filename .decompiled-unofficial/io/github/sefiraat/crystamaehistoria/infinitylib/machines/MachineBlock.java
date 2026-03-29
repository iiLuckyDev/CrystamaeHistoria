/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  org.bukkit.block.Block
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.common.StackUtils;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.AbstractMachineBlock;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MachineBlockRecipe;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MachineInput;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MachineLayout;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MachineRecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

@ParametersAreNonnullByDefault
public final class MachineBlock
extends AbstractMachineBlock {
    protected MachineLayout layout = MachineLayout.MACHINE_DEFAULT;
    private final List<MachineBlockRecipe> recipes = new ArrayList<MachineBlockRecipe>();
    private int ticksPerOutput = -1;

    public MachineBlock(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Nonnull
    public MachineBlock addRecipe(ItemStack output, ItemStack ... inputs) {
        if (inputs.length == 0) {
            throw new IllegalArgumentException("Cannot add recipe with no input!");
        }
        MachineBlockRecipe recipe = new MachineBlockRecipe(output, inputs);
        this.recipes.add(recipe);
        return this;
    }

    @Nonnull
    public MachineBlock addRecipesFrom(MachineRecipeType recipeType) {
        recipeType.sendRecipesTo((in, out) -> this.addRecipe((ItemStack)out, (ItemStack)in));
        return this;
    }

    @Nonnull
    public MachineBlock ticksPerOutput(int ticks) {
        if (ticks < 1) {
            throw new IllegalArgumentException("Ticks Per Output must be at least 1!");
        }
        this.ticksPerOutput = ticks;
        return this;
    }

    @Override
    protected void setup(BlockMenuPreset preset) {
        preset.drawBackground(OUTPUT_BORDER, this.layout.outputBorder());
        preset.drawBackground(INPUT_BORDER, this.layout.inputBorder());
        preset.drawBackground(BACKGROUND_ITEM, this.layout.background());
        preset.addItem(this.layout.statusSlot(), IDLE_ITEM, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected int[] getInputSlots() {
        return this.layout.inputSlots();
    }

    @Override
    protected int[] getOutputSlots() {
        return this.layout.outputSlots();
    }

    public void preRegister() {
        if (this.ticksPerOutput == -1) {
            throw new IllegalStateException("You must call .ticksPerOutput() before registering!");
        }
        super.preRegister();
    }

    @Override
    protected boolean process(Block b, BlockMenu menu) {
        if (AbstractAddon.slimefunTickCount() % this.ticksPerOutput != 0) {
            return true;
        }
        int[] slots = this.layout.inputSlots();
        ItemStack[] input = new ItemStack[slots.length];
        for (int i = 0; i < slots.length; ++i) {
            input[i] = menu.getItemInSlot(slots[i]);
        }
        MachineBlockRecipe recipe = this.getOutput(input);
        if (recipe != null) {
            ItemStack rem = menu.pushItem(recipe.output.clone(), this.layout.outputSlots());
            if (rem == null || rem.getAmount() < recipe.output.getAmount()) {
                recipe.consume();
                if (menu.hasViewer()) {
                    menu.replaceExistingItem(this.getStatusSlot(), PROCESSING_ITEM);
                }
                return true;
            }
            if (menu.hasViewer()) {
                menu.replaceExistingItem(this.getStatusSlot(), NO_ROOM_ITEM);
            }
            return false;
        }
        if (menu.hasViewer()) {
            menu.replaceExistingItem(this.getStatusSlot(), IDLE_ITEM);
        }
        return false;
    }

    @Nullable
    MachineBlockRecipe getOutput(ItemStack[] items) {
        HashMap<String, MachineInput> map = new HashMap<String, MachineInput>(2, 1.0f);
        for (ItemStack item : items) {
            if (item == null) continue;
            String string = StackUtils.getId(item);
            if (string == null) {
                string = item.getType().name();
            }
            map.compute(string, (str, input) -> input == null ? new MachineInput(item) : input.add(item));
        }
        for (MachineBlockRecipe recipe : this.recipes) {
            if (!recipe.check(map)) continue;
            return recipe;
        }
        return null;
    }

    @Override
    protected int getStatusSlot() {
        return this.layout.statusSlot();
    }

    @Nonnull
    @Generated
    public MachineBlock layout(MachineLayout layout) {
        this.layout = layout;
        return this;
    }
}

