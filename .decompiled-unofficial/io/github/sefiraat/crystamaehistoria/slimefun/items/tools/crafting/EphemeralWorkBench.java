/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
 *  io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
 *  io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
 *  io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper
 *  me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools.crafting;

import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EphemeralWorkBench
extends SlimefunItem {
    private static final int[] BACKGROUND_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 14, 15, 16, 17, 18, 22, 24, 26, 27, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] RECIPE_SLOTS = new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30};
    private static final int CRAFT_SLOT = 23;
    private static final int OUTPUT_SLOT = 25;
    private static final ItemStack CRAFT_BUTTON_STACK = CustomItemStack.create((Material)Material.CRAFTING_TABLE, (String)(String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Click to craft"), (String[])new String[0]);
    private static final Map<ItemStack[], ItemStack> RECIPES = new HashMap<ItemStack[], ItemStack>();

    @ParametersAreNonnullByDefault
    public EphemeralWorkBench(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onItemUse()});
    }

    private ItemUseHandler onItemUse() {
        return e -> {
            e.cancel();
            WorkBenchMenu workBenchMenu = new WorkBenchMenu(this);
            workBenchMenu.open(new Player[]{e.getPlayer()});
        };
    }

    public static boolean allowedRecipe(SlimefunItemStack i) {
        return EphemeralWorkBench.allowedRecipe(i.getItemId());
    }

    public static boolean allowedRecipe(String s) {
        return !EphemeralWorkBench.isBackpack(s);
    }

    public static boolean isBackpack(String s) {
        return s.matches("(.*)BACKPACK(.*)");
    }

    public static boolean allowedRecipe(SlimefunItem i) {
        return EphemeralWorkBench.allowedRecipe(i.getId());
    }

    public static void addRecipe(ItemStack[] input, ItemStack output) {
        RECIPES.put(input, output);
    }

    static {
        for (SlimefunItem i : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            RecipeType recipeType = i.getRecipeType();
            if (recipeType != RecipeType.ENHANCED_CRAFTING_TABLE || !EphemeralWorkBench.allowedRecipe(i)) continue;
            EphemeralWorkBench.addRecipe(i.getRecipe(), i.getRecipeOutput());
        }
    }

    public class WorkBenchMenu
    extends ChestMenu {
        public WorkBenchMenu(EphemeralWorkBench this$0) {
            super("Ephemeral Workbench");
            ChestMenuUtils.drawBackground((ChestMenu)this, (int[])BACKGROUND_SLOTS);
            this.addPlayerInventoryClickHandler((p, slot, item, action) -> true);
            this.addItem(23, CRAFT_BUTTON_STACK, (p, slot, item, action) -> false);
            this.addMenuClickHandler(23, (player, slot, item, action) -> {
                ItemStack[] inputs = new ItemStack[RECIPE_SLOTS.length];
                int i = 0;
                for (int recipeSlot : RECIPE_SLOTS) {
                    ItemStack stack = this.getItemInSlot(recipeSlot);
                    if (stack != null && stack.getType() != Material.AIR && StoryUtils.isStoried(stack)) {
                        return false;
                    }
                    inputs[i] = stack;
                    ++i;
                }
                ItemStack crafted = null;
                for (Map.Entry<ItemStack[], ItemStack> entry : RECIPES.entrySet()) {
                    if (!this.testRecipe(inputs, entry.getKey())) continue;
                    crafted = entry.getValue().clone();
                    break;
                }
                if (crafted == null) {
                    crafted = Bukkit.craftItem((ItemStack[])inputs, (World)player.getWorld(), (Player)player);
                }
                if (crafted.getType() != Material.AIR && this.fits(crafted, 25)) {
                    this.pushAndClear(crafted);
                }
                return false;
            });
            this.addMenuCloseHandler(p -> {
                this.dropItems(p.getLocation(), RECIPE_SLOTS);
                this.dropItems(p.getLocation(), 25);
            });
        }

        private boolean testRecipe(ItemStack[] input, ItemStack[] recipe) {
            for (int test = 0; test < recipe.length; ++test) {
                if (SlimefunUtils.isItemSimilar((ItemStack)input[test], (ItemStack)recipe[test], (boolean)true, (boolean)false)) continue;
                return false;
            }
            return true;
        }

        public boolean fits(@Nonnull ItemStack item, int ... slots) {
            for (int slot : slots) {
                if (this.getItemInSlot(slot) != null) continue;
                return true;
            }
            return InvUtils.fits((Inventory)this.toInventory(), (ItemStack)ItemStackWrapper.wrap((ItemStack)item), (int[])slots);
        }

        private void pushAndClear(ItemStack itemStack) {
            this.pushItem(itemStack, 25);
            for (int recipeSlot : RECIPE_SLOTS) {
                if (this.getItemInSlot(recipeSlot) == null) continue;
                ItemUtils.consumeItem((ItemStack)this.getItemInSlot(recipeSlot), (int)1, (boolean)true);
            }
        }

        public void dropItems(Location l, int ... slots) {
            for (int slot : slots) {
                ItemStack item = this.getItemInSlot(slot);
                if (item == null) continue;
                l.getWorld().dropItemNaturally(l, item);
                this.replaceExistingItem(slot, null);
            }
        }

        @Nullable
        public ItemStack pushItem(ItemStack item, int ... slots) {
            if (item == null || item.getType() == Material.AIR) {
                throw new IllegalArgumentException("Cannot push null or AIR");
            }
            ItemStackWrapper wrapper = null;
            int amount = item.getAmount();
            for (int slot : slots) {
                if (amount <= 0) break;
                ItemStack stack = this.getItemInSlot(slot);
                if (stack == null) {
                    this.replaceExistingItem(slot, item);
                    return null;
                }
                int maxStackSize = Math.min(stack.getMaxStackSize(), this.toInventory().getMaxStackSize());
                if (stack.getAmount() >= maxStackSize) continue;
                if (wrapper == null) {
                    wrapper = ItemStackWrapper.wrap((ItemStack)item);
                }
                if (!ItemUtils.canStack((ItemStack)wrapper, (ItemStack)stack)) continue;
                stack.setAmount(Math.min(stack.getAmount() + item.getAmount(), maxStackSize));
                item.setAmount(amount -= maxStackSize - stack.getAmount());
            }
            if (amount > 0) {
                return CustomItemStack.create((ItemStack)item, (int)amount);
            }
            return null;
        }
    }
}

