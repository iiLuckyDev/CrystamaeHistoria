/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.Action
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public enum SpellSlot {
    LEFT_CLICK(1),
    RIGHT_CLICK(2),
    SHIFT_LEFT_CLICK(3),
    SHIFT_RIGHT_CLICK(4);

    private static final SpellSlot[] cashedValues;
    private final int slot;

    private SpellSlot(int slot) {
        this.slot = slot;
    }

    @Nullable
    public static SpellSlot getBySlot(int slot) {
        for (SpellSlot spellSlot : SpellSlot.values()) {
            if (spellSlot.slot != slot) continue;
            return spellSlot;
        }
        return null;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static SpellSlot getByPlayerAndAction(Player player, Action action) {
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            return player.isSneaking() ? SHIFT_LEFT_CLICK : LEFT_CLICK;
        }
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            return player.isSneaking() ? SHIFT_RIGHT_CLICK : RIGHT_CLICK;
        }
        return null;
    }

    @Generated
    public static SpellSlot[] getCashedValues() {
        return cashedValues;
    }

    @Generated
    public int getSlot() {
        return this.slot;
    }

    static {
        cashedValues = SpellSlot.values();
    }
}

