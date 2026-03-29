/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;

@ParametersAreNonnullByDefault
public final class MachineLayout {
    public static final MachineLayout MACHINE_DEFAULT = new MachineLayout().inputBorder(new int[]{9, 10, 11, 12, 18, 21, 27, 28, 29, 30}).inputSlots(new int[]{19, 20}).outputBorder(new int[]{14, 15, 16, 17, 23, 26, 32, 33, 34, 35}).outputSlots(new int[]{24, 25}).background(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44}).statusSlot(22);
    public static final MachineLayout CRAFTING_DEFAULT = new MachineLayout().inputBorder(new int[]{0, 1, 2, 3, 4, 9, 13, 18, 22, 27, 31, 36, 37, 38, 39, 40}).inputSlots(new int[]{10, 11, 12, 19, 20, 21, 28, 29, 30}).outputBorder(new int[]{15, 16, 17, 24, 26, 33, 34, 35}).outputSlots(new int[]{25}).background(new int[]{5, 6, 7, 8, 14, 32, 41, 42, 43, 44}).statusSlot(23);
    private int[] inputBorder;
    private int[] inputSlots;
    private int[] outputBorder;
    private int[] outputSlots;
    private int[] background;
    private int statusSlot;

    @Generated
    public int[] inputBorder() {
        return this.inputBorder;
    }

    @Generated
    public int[] inputSlots() {
        return this.inputSlots;
    }

    @Generated
    public int[] outputBorder() {
        return this.outputBorder;
    }

    @Generated
    public int[] outputSlots() {
        return this.outputSlots;
    }

    @Generated
    public int[] background() {
        return this.background;
    }

    @Generated
    public int statusSlot() {
        return this.statusSlot;
    }

    @Nonnull
    @Generated
    public MachineLayout inputBorder(int[] inputBorder) {
        this.inputBorder = inputBorder;
        return this;
    }

    @Nonnull
    @Generated
    public MachineLayout inputSlots(int[] inputSlots) {
        this.inputSlots = inputSlots;
        return this;
    }

    @Nonnull
    @Generated
    public MachineLayout outputBorder(int[] outputBorder) {
        this.outputBorder = outputBorder;
        return this;
    }

    @Nonnull
    @Generated
    public MachineLayout outputSlots(int[] outputSlots) {
        this.outputSlots = outputSlots;
        return this;
    }

    @Nonnull
    @Generated
    public MachineLayout background(int[] background) {
        this.background = background;
        return this;
    }

    @Nonnull
    @Generated
    public MachineLayout statusSlot(int statusSlot) {
        this.statusSlot = statusSlot;
        return this;
    }
}

