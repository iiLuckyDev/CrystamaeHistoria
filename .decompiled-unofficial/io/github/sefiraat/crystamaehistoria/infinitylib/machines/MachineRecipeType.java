/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import lombok.Generated;
import org.bukkit.inventory.ItemStack;

public final class MachineRecipeType
extends RecipeType {
    private final Map<ItemStack[], ItemStack> recipes = new LinkedHashMap<ItemStack[], ItemStack>();
    private final List<BiConsumer<ItemStack[], ItemStack>> callbacks = new ArrayList<BiConsumer<ItemStack[], ItemStack>>();

    public MachineRecipeType(String key, ItemStack item) {
        super(AbstractAddon.createKey(key), item);
    }

    public void register(ItemStack[] recipe, ItemStack result) {
        this.callbacks.forEach(c -> c.accept(recipe, result));
        this.recipes.put(recipe, result);
    }

    public void sendRecipesTo(BiConsumer<ItemStack[], ItemStack> callback) {
        this.recipes.forEach(callback);
        this.callbacks.add(callback);
    }

    @Generated
    public Map<ItemStack[], ItemStack> recipes() {
        return this.recipes;
    }
}

