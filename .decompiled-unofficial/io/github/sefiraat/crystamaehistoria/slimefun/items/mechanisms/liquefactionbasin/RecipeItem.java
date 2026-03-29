/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RecipeItem {
    private final ItemStack inputItem;
    private final List<StoryType> storyTypes;
    private final List<Integer> amounts;
    @Nullable
    private final Predicate<Player> additionalRequirement;

    @ParametersAreNonnullByDefault
    public RecipeItem(ItemStack inputItem, StoryType type1, int crysta1, StoryType type2, int crysta2, StoryType type3, int crysta3) {
        this(inputItem, type1, crysta1, type2, crysta2, type3, crysta3, null);
    }

    @ParametersAreNonnullByDefault
    public RecipeItem(ItemStack inputItem, StoryType type1, int crysta1, StoryType type2, int crysta2, StoryType type3, int crysta3, @Nullable Predicate<Player> additionalRequirement) {
        this.inputItem = inputItem;
        this.storyTypes = Arrays.asList(type1, type2, type3);
        this.amounts = Arrays.asList(crysta1, crysta2, crysta3);
        this.additionalRequirement = additionalRequirement;
    }

    @ParametersAreNonnullByDefault
    public boolean recipeMatches(List<StoryType> testTypes, List<Integer> testAmounts, ItemStack inputItem, UUID uuid) {
        OfflinePlayer offlinePlayer;
        int i = 0;
        if (uuid != null && this.additionalRequirement != null && (offlinePlayer = Bukkit.getOfflinePlayer((UUID)uuid)).isOnline() && !this.additionalRequirement.test(Bukkit.getPlayer((UUID)uuid))) {
            return false;
        }
        if (!SlimefunUtils.isItemSimilar((ItemStack)this.inputItem, (ItemStack)inputItem, (boolean)true, (boolean)false)) {
            return false;
        }
        for (StoryType testType : testTypes) {
            int index = this.storyTypes.indexOf((Object)testType);
            if (index == -1 || this.amounts.get(index) > testAmounts.get(i)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public ItemStack[] getDisplayRecipe() {
        return new ItemStack[]{null, this.inputItem, null, this.getDisplayCrystal(0), this.getDisplayCrystal(1), this.getDisplayCrystal(2), null, null, null};
    }

    public ItemStack getDisplayCrystal(int index) {
        ItemStack itemStack = Materials.getDummyCrystalMap().get((Object)this.storyTypes.get(index)).getItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<CallSite> list = new ArrayList<CallSite>();
        list.add((CallSite)((Object)(String.valueOf(ThemeType.CLICK_INFO.getColor()) + "Minimum amount: " + String.valueOf(ThemeType.PASSIVE.getColor()) + String.valueOf(this.amounts.get(index)))));
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public StoryType getInputType(int index) {
        return this.storyTypes.get(index);
    }

    public int getInputAmount(int index) {
        return this.amounts.get(index);
    }

    @Generated
    public ItemStack getInputItem() {
        return this.inputItem;
    }
}

