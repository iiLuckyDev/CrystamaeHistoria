/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.kyori.adventure.text.format.TextColor
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.DyeColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Axolotl$Variant
 *  org.bukkit.entity.Parrot$Variant
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

import java.awt.Color;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Parrot;

public enum PaintProfile {
    RED(Material.RED_WOOL, Material.RED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA, Material.RED_CONCRETE_POWDER, Material.RED_CONCRETE, Material.RED_CARPET, Material.RED_STAINED_GLASS, Material.RED_STAINED_GLASS_PANE, Material.RED_SHULKER_BOX, Parrot.Variant.RED, Axolotl.Variant.WILD, DyeColor.RED),
    ORANGE(Material.ORANGE_WOOL, Material.ORANGE_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA, Material.ORANGE_CONCRETE_POWDER, Material.ORANGE_CONCRETE, Material.ORANGE_CARPET, Material.ORANGE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS_PANE, Material.ORANGE_SHULKER_BOX, null, Axolotl.Variant.GOLD, DyeColor.ORANGE),
    YELLOW(Material.YELLOW_WOOL, Material.YELLOW_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA, Material.YELLOW_CONCRETE_POWDER, Material.YELLOW_CONCRETE, Material.YELLOW_CARPET, Material.YELLOW_STAINED_GLASS, Material.YELLOW_STAINED_GLASS_PANE, Material.YELLOW_SHULKER_BOX, null, Axolotl.Variant.GOLD, DyeColor.YELLOW),
    LIME(Material.LIME_WOOL, Material.LIME_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA, Material.LIME_CONCRETE_POWDER, Material.LIME_CONCRETE, Material.LIME_CARPET, Material.LIME_STAINED_GLASS, Material.LIME_STAINED_GLASS_PANE, Material.LIME_SHULKER_BOX, Parrot.Variant.GREEN, null, DyeColor.LIME),
    GREEN(Material.GREEN_WOOL, Material.GREEN_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA, Material.GREEN_CONCRETE_POWDER, Material.GREEN_CONCRETE, Material.GREEN_CARPET, Material.GREEN_STAINED_GLASS, Material.GREEN_STAINED_GLASS_PANE, Material.GREEN_SHULKER_BOX, Parrot.Variant.GREEN, null, DyeColor.GREEN),
    CYAN(Material.CYAN_WOOL, Material.CYAN_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA, Material.CYAN_CONCRETE_POWDER, Material.CYAN_CONCRETE, Material.CYAN_CARPET, Material.CYAN_STAINED_GLASS, Material.CYAN_STAINED_GLASS_PANE, Material.CYAN_SHULKER_BOX, Parrot.Variant.CYAN, Axolotl.Variant.CYAN, DyeColor.CYAN),
    BLUE(Material.BLUE_WOOL, Material.BLUE_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA, Material.BLUE_CONCRETE_POWDER, Material.BLUE_CONCRETE, Material.BLUE_CARPET, Material.BLUE_STAINED_GLASS, Material.BLUE_STAINED_GLASS_PANE, Material.BLUE_SHULKER_BOX, Parrot.Variant.BLUE, Axolotl.Variant.BLUE, DyeColor.BLUE),
    LIGHT_BLUE(Material.LIGHT_BLUE_WOOL, Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.LIGHT_BLUE_CONCRETE_POWDER, Material.LIGHT_BLUE_CONCRETE, Material.LIGHT_BLUE_CARPET, Material.LIGHT_BLUE_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS_PANE, Material.LIGHT_BLUE_SHULKER_BOX, Parrot.Variant.CYAN, Axolotl.Variant.BLUE, DyeColor.LIGHT_BLUE),
    MAGENTA(Material.MAGENTA_WOOL, Material.MAGENTA_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA, Material.MAGENTA_CONCRETE_POWDER, Material.MAGENTA_CONCRETE, Material.MAGENTA_CARPET, Material.MAGENTA_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS_PANE, Material.MAGENTA_SHULKER_BOX, null, Axolotl.Variant.LUCY, DyeColor.MAGENTA),
    PURPLE(Material.PURPLE_WOOL, Material.PURPLE_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA, Material.PURPLE_CONCRETE_POWDER, Material.PURPLE_CONCRETE, Material.PURPLE_CARPET, Material.PURPLE_STAINED_GLASS, Material.PURPLE_STAINED_GLASS_PANE, Material.PURPLE_SHULKER_BOX, null, Axolotl.Variant.LUCY, DyeColor.PURPLE),
    PINK(Material.PINK_WOOL, Material.PINK_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA, Material.PINK_CONCRETE_POWDER, Material.PINK_CONCRETE, Material.PINK_CARPET, Material.PINK_STAINED_GLASS, Material.PINK_STAINED_GLASS_PANE, Material.PINK_SHULKER_BOX, null, Axolotl.Variant.LUCY, DyeColor.PINK),
    WHITE(Material.WHITE_WOOL, Material.WHITE_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA, Material.WHITE_CONCRETE_POWDER, Material.WHITE_CONCRETE, Material.WHITE_CARPET, Material.WHITE_STAINED_GLASS, Material.WHITE_STAINED_GLASS_PANE, Material.WHITE_SHULKER_BOX, null, Axolotl.Variant.CYAN, DyeColor.WHITE),
    LIGHT_GRAY(Material.LIGHT_GRAY_WOOL, Material.LIGHT_GRAY_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA, Material.LIGHT_GRAY_CONCRETE_POWDER, Material.LIGHT_GRAY_CONCRETE, Material.LIGHT_GRAY_CARPET, Material.LIGHT_GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS_PANE, Material.LIGHT_GRAY_SHULKER_BOX, Parrot.Variant.GRAY, Axolotl.Variant.CYAN, DyeColor.LIGHT_GRAY),
    GRAY(Material.GRAY_WOOL, Material.GRAY_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA, Material.GRAY_CONCRETE_POWDER, Material.GRAY_CONCRETE, Material.GRAY_CARPET, Material.GRAY_STAINED_GLASS, Material.GRAY_STAINED_GLASS_PANE, Material.GRAY_SHULKER_BOX, Parrot.Variant.GRAY, null, DyeColor.GRAY),
    BLACK(Material.BLACK_WOOL, Material.BLACK_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA, Material.BLACK_CONCRETE_POWDER, Material.BLACK_CONCRETE, Material.BLACK_CARPET, Material.BLACK_STAINED_GLASS, Material.BLACK_STAINED_GLASS_PANE, Material.BLACK_SHULKER_BOX, null, null, DyeColor.BLACK),
    BROWN(Material.BROWN_WOOL, Material.BROWN_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA, Material.BROWN_CONCRETE_POWDER, Material.BROWN_CONCRETE, Material.BROWN_CARPET, Material.BROWN_STAINED_GLASS, Material.BROWN_STAINED_GLASS_PANE, Material.BROWN_SHULKER_BOX, null, Axolotl.Variant.WILD, DyeColor.BROWN);

    protected static final PaintProfile[] cachedValues;
    private final Material materialWool;
    private final Material materialTerracotta;
    private final Material materialGlazedTerracotta;
    private final Material materialConcretePowder;
    private final Material materialConcrete;
    private final Material materialCarpet;
    private final Material materialGlass;
    private final Material materialGlassPane;
    private final Material materialShulker;
    private final Parrot.Variant parrotVariant;
    private final Axolotl.Variant axolotlVariant;
    private final DyeColor dyeColor;

    private PaintProfile(Material materialWool, Material materialTerracotta, Material materialGlazedTerracotta, Material materialConcretePowder, Material materialConcrete, Material materialCarpet, Material materialGlass, @Nullable Material materialGlassPane, @Nullable Material materialShulker, Parrot.Variant parrotVariant, Axolotl.Variant axolotlVariant, DyeColor dyeColor) {
        this.materialWool = materialWool;
        this.materialTerracotta = materialTerracotta;
        this.materialGlazedTerracotta = materialGlazedTerracotta;
        this.materialConcretePowder = materialConcretePowder;
        this.materialConcrete = materialConcrete;
        this.materialCarpet = materialCarpet;
        this.materialGlass = materialGlass;
        this.materialGlassPane = materialGlassPane;
        this.materialShulker = materialShulker;
        this.parrotVariant = parrotVariant;
        this.axolotlVariant = axolotlVariant;
        this.dyeColor = dyeColor;
    }

    public ChatColor getChatColor() {
        Color color = new Color(this.dyeColor.getColor().getRed(), this.dyeColor.getColor().getGreen(), this.dyeColor.getColor().getBlue());
        return ChatColor.of((Color)color);
    }

    public TextColor getTextColor() {
        return TextColor.color((int)this.dyeColor.getColor().getRed(), (int)this.dyeColor.getColor().getGreen(), (int)this.dyeColor.getColor().getBlue());
    }

    @Generated
    public static PaintProfile[] getCachedValues() {
        return cachedValues;
    }

    @Generated
    public Material getMaterialWool() {
        return this.materialWool;
    }

    @Generated
    public Material getMaterialTerracotta() {
        return this.materialTerracotta;
    }

    @Generated
    public Material getMaterialGlazedTerracotta() {
        return this.materialGlazedTerracotta;
    }

    @Generated
    public Material getMaterialConcretePowder() {
        return this.materialConcretePowder;
    }

    @Generated
    public Material getMaterialConcrete() {
        return this.materialConcrete;
    }

    @Generated
    public Material getMaterialCarpet() {
        return this.materialCarpet;
    }

    @Generated
    public Material getMaterialGlass() {
        return this.materialGlass;
    }

    @Generated
    public Material getMaterialGlassPane() {
        return this.materialGlassPane;
    }

    @Generated
    public Material getMaterialShulker() {
        return this.materialShulker;
    }

    @Generated
    public Parrot.Variant getParrotVariant() {
        return this.parrotVariant;
    }

    @Generated
    public Axolotl.Variant getAxolotlVariant() {
        return this.axolotlVariant;
    }

    @Generated
    public DyeColor getDyeColor() {
        return this.dyeColor;
    }

    static {
        cachedValues = PaintProfile.values();
    }
}

