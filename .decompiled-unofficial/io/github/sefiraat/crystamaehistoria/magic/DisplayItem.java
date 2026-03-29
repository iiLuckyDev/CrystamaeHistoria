/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Location
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class DisplayItem {
    private final Item item;
    private final Consumer<Item> onRemove;

    @ParametersAreNonnullByDefault
    public DisplayItem(ItemStack itemStack, Location location, @Nullable String name, @Nullable Consumer<Item> onRemove) {
        this.item = GeneralUtils.spawnDisplayItem(itemStack, location, name);
        this.onRemove = onRemove;
    }

    public void registerRemoval(long duration) {
        CrystamaeHistoria.getSpellMemory().getDisplayItems().put(this, System.currentTimeMillis() + duration);
    }

    @ParametersAreNonnullByDefault
    public void setVelocity(Vector vector) {
        this.item.setGravity(true);
        this.item.setVelocity(vector);
    }

    public void kill() {
        if (this.item.isValid()) {
            if (this.onRemove != null) {
                this.onRemove.accept(this.item);
            }
            this.item.remove();
        }
    }

    @Generated
    public Item getItem() {
        return this.item;
    }

    @Generated
    public Consumer<Item> getOnRemove() {
        return this.onRemove;
    }
}

