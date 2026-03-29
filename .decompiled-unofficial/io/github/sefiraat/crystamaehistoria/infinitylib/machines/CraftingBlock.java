/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.CraftingBlockRecipe;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MachineLayout;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MachineRecipeType;
import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@ParametersAreNonnullByDefault
public class CraftingBlock
extends MenuBlock {
    public static final ItemStack CLICK_TO_CRAFT = CustomItemStack.create((Material)Material.LIME_STAINED_GLASS_PANE, (String)"&aClick To Craft!", (String[])new String[0]);
    protected MachineLayout layout = MachineLayout.CRAFTING_DEFAULT;
    private final List<CraftingBlockRecipe> recipes = new ArrayList<CraftingBlockRecipe>();

    public CraftingBlock(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    protected void craft(Block b, BlockMenu menu, Player p) {
        int[] slots = this.layout.inputSlots();
        ItemStack[] input = new ItemStack[slots.length];
        for (int i = 0; i < slots.length; ++i) {
            input[i] = menu.getItemInSlot(slots[i]);
        }
        CraftingBlockRecipe recipe = this.getOutput(input);
        if (recipe != null) {
            if (recipe.check(p)) {
                if (menu.fits(recipe.output, this.layout.outputSlots())) {
                    ItemStack output = recipe.output.clone();
                    this.onSuccessfulCraft(menu, output);
                    menu.pushItem(output, this.layout.outputSlots());
                    recipe.consume(input);
                    TextComponent itemName = LegacyComponentSerializer.legacySection().deserialize(ItemUtils.getItemName((ItemStack)output));
                    p.sendMessage(Component.text((String)"Successfully Crafted: ", (TextColor)NamedTextColor.GREEN).append((Component)itemName));
                } else {
                    p.sendMessage((Component)Component.text((String)"Not Enough Room!", (TextColor)NamedTextColor.GOLD));
                }
            }
        } else {
            p.sendMessage((Component)Component.text((String)"Invalid Recipe!", (TextColor)NamedTextColor.RED));
        }
    }

    protected void onSuccessfulCraft(BlockMenu menu, ItemStack toOutput) {
    }

    @Override
    protected void setup(BlockMenuPreset preset) {
        preset.drawBackground(OUTPUT_BORDER, this.layout.outputBorder());
        preset.drawBackground(INPUT_BORDER, this.layout.inputBorder());
        preset.drawBackground(BACKGROUND_ITEM, this.layout.background());
        preset.addItem(this.layout.statusSlot(), CLICK_TO_CRAFT, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
        menu.addMenuClickHandler(this.layout.statusSlot(), (player, i, itemStack, clickAction) -> {
            this.craft(b, menu, player);
            return false;
        });
    }

    @Nonnull
    public final CraftingBlock addRecipe(ItemStack output, ItemStack ... inputs) {
        if (inputs.length == 0) {
            throw new IllegalArgumentException("Cannot add recipe with no input!");
        }
        CraftingBlockRecipe recipe = new CraftingBlockRecipe(output, inputs);
        this.recipes.add(recipe);
        return this;
    }

    @Nonnull
    public final CraftingBlock addRecipesFrom(MachineRecipeType recipeType) {
        recipeType.sendRecipesTo((in, out) -> this.addRecipe((ItemStack)out, (ItemStack)in));
        return this;
    }

    @Nullable
    protected final CraftingBlockRecipe getOutput(ItemStack[] input) {
        ItemStackSnapshot[] snapshots = ItemStackSnapshot.wrapArray((ItemStack[])input);
        for (CraftingBlockRecipe recipe : this.recipes) {
            if (!recipe.check(snapshots)) continue;
            return recipe;
        }
        return null;
    }

    @Override
    protected final int[] getInputSlots(DirtyChestMenu menu, ItemStack input) {
        return new int[0];
    }

    @Override
    protected final int[] getInputSlots() {
        return this.layout.inputSlots();
    }

    @Override
    protected final int[] getOutputSlots() {
        return this.layout.outputSlots();
    }

    @Nonnull
    @Generated
    public CraftingBlock layout(MachineLayout layout) {
        this.layout = layout;
        return this;
    }
}

