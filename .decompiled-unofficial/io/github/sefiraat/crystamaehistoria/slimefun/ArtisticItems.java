/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.BasicPaintbrush;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.ImbuedStand;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.InfinitePaintbrush;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PaintProfile;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PoseChanger;
import io.github.sefiraat.crystamaehistoria.slimefun.items.artistic.PoseCloner;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ArtisticItems {
    private static BasicPaintbrush blackPaintBrush100;
    private static BasicPaintbrush bluePaintBrush100;
    private static BasicPaintbrush brownPaintBrush100;
    private static BasicPaintbrush cyanPaintBrush100;
    private static BasicPaintbrush grayPaintBrush100;
    private static BasicPaintbrush greenPaintBrush100;
    private static BasicPaintbrush lightBluePaintBrush100;
    private static BasicPaintbrush lightGrayPaintBrush100;
    private static BasicPaintbrush limePaintBrush100;
    private static BasicPaintbrush magentaPaintBrush100;
    private static BasicPaintbrush orangePaintBrush100;
    private static BasicPaintbrush pinkPaintBrush100;
    private static BasicPaintbrush purplePaintBrush100;
    private static BasicPaintbrush redPaintBrush100;
    private static BasicPaintbrush whitePaintBrush100;
    private static BasicPaintbrush yellowPaintBrush100;
    private static BasicPaintbrush blackPaintBrush1000;
    private static BasicPaintbrush bluePaintBrush1000;
    private static BasicPaintbrush brownPaintBrush1000;
    private static BasicPaintbrush cyanPaintBrush1000;
    private static BasicPaintbrush grayPaintBrush1000;
    private static BasicPaintbrush greenPaintBrush1000;
    private static BasicPaintbrush lightBluePaintBrush1000;
    private static BasicPaintbrush lightGrayPaintBrush1000;
    private static BasicPaintbrush limePaintBrush1000;
    private static BasicPaintbrush magentaPaintBrush1000;
    private static BasicPaintbrush orangePaintBrush1000;
    private static BasicPaintbrush pinkPaintBrush1000;
    private static BasicPaintbrush purplePaintBrush1000;
    private static BasicPaintbrush redPaintBrush1000;
    private static BasicPaintbrush whitePaintBrush1000;
    private static BasicPaintbrush yellowPaintBrush1000;
    private static SlimefunItem mysticalPigmentato;
    private static SlimefunItem mysticalTintanno;
    private static InfinitePaintbrush paintersResolve;
    private static SlimefunItem bodyStand;
    private static SlimefunItem mindStand;
    private static SlimefunItem soulStand;
    private static SlimefunItem mysticalAttitudinizer;
    private static ImbuedStand imbuedStand;
    private static PoseChanger poseChanger;
    private static PoseCloner poseCloner;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        ItemStack brushCore = CrystaStacks.BASIC_FIBRES.item();
        ItemStack dyeBlack = new ItemStack(Material.BLACK_DYE);
        ItemStack dyeBlue = new ItemStack(Material.BLUE_DYE);
        ItemStack dyeBrown = new ItemStack(Material.BROWN_DYE);
        ItemStack dyeCyan = new ItemStack(Material.CYAN_DYE);
        ItemStack dyeGray = new ItemStack(Material.GRAY_DYE);
        ItemStack dyeGreen = new ItemStack(Material.GREEN_DYE);
        ItemStack dyeLightBlue = new ItemStack(Material.LIGHT_BLUE_DYE);
        ItemStack dyeLightGray = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemStack dyeLime = new ItemStack(Material.LIME_DYE);
        ItemStack dyeMagenta = new ItemStack(Material.MAGENTA_DYE);
        ItemStack dyeOrange = new ItemStack(Material.ORANGE_DYE);
        ItemStack dyePink = new ItemStack(Material.PINK_DYE);
        ItemStack dyePurple = new ItemStack(Material.PURPLE_DYE);
        ItemStack dyeRed = new ItemStack(Material.RED_DYE);
        ItemStack dyeWhite = new ItemStack(Material.WHITE_DYE);
        ItemStack dyeYellow = new ItemStack(Material.YELLOW_DYE);
        blackPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_BLACK_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeBlack, dyeBlack, dyeBlack, dyeBlack, brushCore, dyeBlack, dyeBlack, dyeBlack, dyeBlack}, PaintProfile.BLACK, 100);
        bluePaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_BLUE_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeBlue, dyeBlue, dyeBlue, dyeBlue, brushCore, dyeBlue, dyeBlue, dyeBlue, dyeBlue}, PaintProfile.BLUE, 100);
        brownPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_BROWN_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeBrown, dyeBrown, dyeBrown, dyeBrown, brushCore, dyeBrown, dyeBrown, dyeBrown, dyeBrown}, PaintProfile.BROWN, 100);
        cyanPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_CYAN_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeCyan, dyeCyan, dyeCyan, dyeCyan, brushCore, dyeCyan, dyeCyan, dyeCyan, dyeCyan}, PaintProfile.CYAN, 100);
        grayPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_GRAY_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeGray, dyeGray, dyeGray, dyeGray, brushCore, dyeGray, dyeGray, dyeGray, dyeGray}, PaintProfile.GRAY, 100);
        greenPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_GREEN_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeGreen, dyeGreen, dyeGreen, dyeGreen, brushCore, dyeGreen, dyeGreen, dyeGreen, dyeGreen}, PaintProfile.GREEN, 100);
        lightBluePaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_LIGHT_BLUE_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeLightBlue, dyeLightBlue, dyeLightBlue, dyeLightBlue, brushCore, dyeLightBlue, dyeLightBlue, dyeLightBlue, dyeLightBlue}, PaintProfile.LIGHT_BLUE, 100);
        lightGrayPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_LIGHT_GRAY_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeLightGray, dyeLightGray, dyeLightGray, dyeLightGray, brushCore, dyeLightGray, dyeLightGray, dyeLightGray, dyeLightGray}, PaintProfile.LIGHT_GRAY, 100);
        limePaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_LIME_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeLime, dyeLime, dyeLime, dyeLime, brushCore, dyeLime, dyeLime, dyeLime, dyeLime}, PaintProfile.LIME, 100);
        magentaPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_MAGENTA_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeMagenta, dyeMagenta, dyeMagenta, dyeMagenta, brushCore, dyeMagenta, dyeMagenta, dyeMagenta, dyeMagenta}, PaintProfile.MAGENTA, 100);
        orangePaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_ORANGE_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeOrange, dyeOrange, dyeOrange, dyeOrange, brushCore, dyeOrange, dyeOrange, dyeOrange, dyeOrange}, PaintProfile.ORANGE, 100);
        pinkPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_PINK_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyePink, dyePink, dyePink, dyePink, brushCore, dyePink, dyePink, dyePink, dyePink}, PaintProfile.PINK, 100);
        purplePaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_PURPLE_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyePurple, dyePurple, dyePurple, dyePurple, brushCore, dyePurple, dyePurple, dyePurple, dyePurple}, PaintProfile.PURPLE, 100);
        redPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_RED_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeRed, dyeRed, dyeRed, dyeRed, brushCore, dyeRed, dyeRed, dyeRed, dyeRed}, PaintProfile.RED, 100);
        whitePaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_WHITE_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeWhite, dyeWhite, dyeWhite, dyeWhite, brushCore, dyeWhite, dyeWhite, dyeWhite, dyeWhite}, PaintProfile.WHITE, 100);
        yellowPaintBrush100 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_YELLOW_100, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{dyeYellow, dyeYellow, dyeYellow, dyeYellow, brushCore, dyeYellow, dyeYellow, dyeYellow, dyeYellow}, PaintProfile.YELLOW, 100);
        RecipeItem blackPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_BLACK_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        blackPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_BLACK_1000, RecipeType.ENHANCED_CRAFTING_TABLE, blackPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.BLACK, 1000);
        RecipeItem bluePaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_BLUE_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        bluePaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_BLUE_1000, RecipeType.ENHANCED_CRAFTING_TABLE, bluePaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.BLUE, 1000);
        RecipeItem brownPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_BROWN_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        brownPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_BROWN_1000, RecipeType.ENHANCED_CRAFTING_TABLE, brownPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.BROWN, 1000);
        RecipeItem cyanPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_CYAN_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        cyanPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_CYAN_1000, RecipeType.ENHANCED_CRAFTING_TABLE, cyanPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.CYAN, 1000);
        RecipeItem grayPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_GRAY_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        grayPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_GRAY_1000, RecipeType.ENHANCED_CRAFTING_TABLE, grayPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.GRAY, 1000);
        RecipeItem greenPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_GREEN_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        greenPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_GREEN_1000, RecipeType.ENHANCED_CRAFTING_TABLE, greenPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.GREEN, 1000);
        RecipeItem lightBluePaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_LIGHT_BLUE_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        lightBluePaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_LIGHT_BLUE_1000, RecipeType.ENHANCED_CRAFTING_TABLE, lightBluePaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.LIGHT_BLUE, 1000);
        RecipeItem lightGrayPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_LIGHT_GRAY_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        lightGrayPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_LIGHT_GRAY_1000, RecipeType.ENHANCED_CRAFTING_TABLE, lightGrayPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.LIGHT_GRAY, 1000);
        RecipeItem limePaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_LIME_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        limePaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_LIME_1000, RecipeType.ENHANCED_CRAFTING_TABLE, limePaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.LIME, 1000);
        RecipeItem magentaPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_MAGENTA_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        magentaPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_MAGENTA_1000, RecipeType.ENHANCED_CRAFTING_TABLE, magentaPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.MAGENTA, 1000);
        RecipeItem orangePaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_ORANGE_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        orangePaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_ORANGE_1000, RecipeType.ENHANCED_CRAFTING_TABLE, orangePaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.ORANGE, 1000);
        RecipeItem pinkPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_PINK_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        pinkPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_PINK_1000, RecipeType.ENHANCED_CRAFTING_TABLE, pinkPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.PINK, 1000);
        RecipeItem purplePaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_PURPLE_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        purplePaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_PURPLE_1000, RecipeType.ENHANCED_CRAFTING_TABLE, purplePaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.PURPLE, 1000);
        RecipeItem redPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_RED_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        redPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_RED_1000, RecipeType.ENHANCED_CRAFTING_TABLE, redPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.RED, 1000);
        RecipeItem whitePaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_WHITE_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        whitePaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_WHITE_1000, RecipeType.ENHANCED_CRAFTING_TABLE, whitePaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.WHITE, 1000);
        RecipeItem yellowPaintbrush1000Recipe = new RecipeItem(CrystaStacks.PAINT_BRUSH_YELLOW_100.item().clone(), StoryType.HISTORICAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        yellowPaintBrush1000 = new BasicPaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_YELLOW_1000, RecipeType.ENHANCED_CRAFTING_TABLE, yellowPaintbrush1000Recipe.getDisplayRecipe(), PaintProfile.YELLOW, 1000);
        mysticalPigmentato = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.MYSTICAL_PIGMENTATO, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CrystaStacks.PAINT_BRUSH_BLACK_1000.item().clone(), CrystaStacks.PAINT_BRUSH_BLUE_1000.item().clone(), CrystaStacks.PAINT_BRUSH_BROWN_1000.item().clone(), CrystaStacks.PAINT_BRUSH_CYAN_1000.item().clone(), CrystaStacks.AMALGAMATE_DUST_EPIC.item().clone(), CrystaStacks.PAINT_BRUSH_GRAY_1000.item().clone(), CrystaStacks.PAINT_BRUSH_GREEN_1000.item().clone(), CrystaStacks.PAINT_BRUSH_LIGHT_BLUE_1000.item().clone(), CrystaStacks.PAINT_BRUSH_LIGHT_GRAY_1000.item().clone()});
        mysticalTintanno = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.MYSTICAL_TINTANNO, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CrystaStacks.PAINT_BRUSH_LIME_1000.item().clone(), CrystaStacks.PAINT_BRUSH_MAGENTA_1000.item().clone(), CrystaStacks.PAINT_BRUSH_ORANGE_1000.item().clone(), CrystaStacks.PAINT_BRUSH_PINK_1000.item().clone(), CrystaStacks.AMALGAMATE_DUST_EPIC.item().clone(), CrystaStacks.PAINT_BRUSH_PURPLE_1000.item().clone(), CrystaStacks.PAINT_BRUSH_RED_1000.item().clone(), CrystaStacks.PAINT_BRUSH_WHITE_1000.item().clone(), CrystaStacks.PAINT_BRUSH_YELLOW_1000.item().clone()});
        paintersResolve = new InfinitePaintbrush(ItemGroups.ARTISTIC, CrystaStacks.PAINT_BRUSH_INFINITE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, null, null, CrystaStacks.MYSTICAL_TINTANNO.item().clone(), CrystaStacks.AMALGAMATE_INGOT_MYTHICAL.item().clone(), CrystaStacks.MYSTICAL_PIGMENTATO.item().clone(), null, null, null});
        RecipeItem bodyStandRecipe = new RecipeItem(new ItemStack(Material.ARMOR_STAND), StoryType.ELEMENTAL, 100, StoryType.ALCHEMICAL, 100, StoryType.MECHANICAL, 100);
        bodyStand = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.BODY_STAND, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, bodyStandRecipe.getDisplayRecipe());
        RecipeItem mindStandRecipe = new RecipeItem(new ItemStack(Material.ARMOR_STAND), StoryType.HISTORICAL, 100, StoryType.HUMAN, 100, StoryType.ANIMAL, 100);
        mindStand = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.MIND_STAND, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, mindStandRecipe.getDisplayRecipe());
        RecipeItem soulStandRecipe = new RecipeItem(new ItemStack(Material.ARMOR_STAND), StoryType.CELESTIAL, 100, StoryType.VOID, 100, StoryType.PHILOSOPHICAL, 100);
        soulStand = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.SOUL_STAND, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, soulStandRecipe.getDisplayRecipe());
        mysticalAttitudinizer = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.MYSTICAL_ATTITUDINIZER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.BODY_STAND.item().clone(), CrystaStacks.MIND_STAND.item().clone(), CrystaStacks.SOUL_STAND.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone()});
        RecipeItem imbuedStandRecipe = new RecipeItem(new ItemStack(Material.ARMOR_STAND), StoryType.MECHANICAL, 5, StoryType.HUMAN, 5, StoryType.VOID, 5);
        imbuedStand = new ImbuedStand(ItemGroups.ARTISTIC, CrystaStacks.IMBUED_STAND, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, imbuedStandRecipe.getDisplayRecipe());
        poseChanger = new PoseChanger(ItemGroups.ARTISTIC, CrystaStacks.POSE_CHANGER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), CrystaStacks.MYSTICAL_ATTITUDINIZER.item().clone(), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone()});
        poseCloner = new PoseCloner(ItemGroups.ARTISTIC, CrystaStacks.POSE_CLONER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.AMALGAMATE_INGOT_UNIQUE.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), bodyStand.getItem(), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone(), CrystaStacks.AMALGAMATE_INGOT_UNIQUE.item().clone(), CrystaStacks.BASIC_FIBRES.item().clone()});
        blackPaintBrush100.register(plugin);
        bluePaintBrush100.register(plugin);
        brownPaintBrush100.register(plugin);
        cyanPaintBrush100.register(plugin);
        grayPaintBrush100.register(plugin);
        greenPaintBrush100.register(plugin);
        lightBluePaintBrush100.register(plugin);
        lightGrayPaintBrush100.register(plugin);
        limePaintBrush100.register(plugin);
        magentaPaintBrush100.register(plugin);
        orangePaintBrush100.register(plugin);
        pinkPaintBrush100.register(plugin);
        purplePaintBrush100.register(plugin);
        redPaintBrush100.register(plugin);
        whitePaintBrush100.register(plugin);
        yellowPaintBrush100.register(plugin);
        blackPaintBrush1000.register(plugin);
        bluePaintBrush1000.register(plugin);
        brownPaintBrush1000.register(plugin);
        cyanPaintBrush1000.register(plugin);
        grayPaintBrush1000.register(plugin);
        greenPaintBrush1000.register(plugin);
        lightBluePaintBrush1000.register(plugin);
        lightGrayPaintBrush1000.register(plugin);
        limePaintBrush1000.register(plugin);
        magentaPaintBrush1000.register(plugin);
        orangePaintBrush1000.register(plugin);
        pinkPaintBrush1000.register(plugin);
        purplePaintBrush1000.register(plugin);
        redPaintBrush1000.register(plugin);
        whitePaintBrush1000.register(plugin);
        yellowPaintBrush1000.register(plugin);
        mysticalPigmentato.register((SlimefunAddon)plugin);
        mysticalTintanno.register((SlimefunAddon)plugin);
        paintersResolve.register(plugin);
        bodyStand.register((SlimefunAddon)plugin);
        mindStand.register((SlimefunAddon)plugin);
        soulStand.register((SlimefunAddon)plugin);
        mysticalAttitudinizer.register((SlimefunAddon)plugin);
        imbuedStand.register(plugin);
        poseChanger.register(plugin);
        poseCloner.register(plugin);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)blackPaintBrush1000, blackPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)bluePaintBrush1000, bluePaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)brownPaintBrush1000, brownPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)cyanPaintBrush1000, cyanPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)grayPaintBrush1000, grayPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)greenPaintBrush1000, greenPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)lightBluePaintBrush1000, lightBluePaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)lightGrayPaintBrush1000, lightGrayPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)limePaintBrush1000, limePaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)magentaPaintBrush1000, magentaPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)orangePaintBrush1000, orangePaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)pinkPaintBrush1000, pinkPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)purplePaintBrush1000, purplePaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)redPaintBrush1000, redPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)whitePaintBrush1000, whitePaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)yellowPaintBrush1000, yellowPaintbrush1000Recipe);
        LiquefactionBasinCache.addCraftingRecipe(bodyStand, bodyStandRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mindStand, mindStandRecipe);
        LiquefactionBasinCache.addCraftingRecipe(soulStand, soulStandRecipe);
        LiquefactionBasinCache.addCraftingRecipe(imbuedStand, imbuedStandRecipe);
    }

    @Generated
    private ArtisticItems() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static BasicPaintbrush getBlackPaintBrush100() {
        return blackPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getBluePaintBrush100() {
        return bluePaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getBrownPaintBrush100() {
        return brownPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getCyanPaintBrush100() {
        return cyanPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getGrayPaintBrush100() {
        return grayPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getGreenPaintBrush100() {
        return greenPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getLightBluePaintBrush100() {
        return lightBluePaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getLightGrayPaintBrush100() {
        return lightGrayPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getLimePaintBrush100() {
        return limePaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getMagentaPaintBrush100() {
        return magentaPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getOrangePaintBrush100() {
        return orangePaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getPinkPaintBrush100() {
        return pinkPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getPurplePaintBrush100() {
        return purplePaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getRedPaintBrush100() {
        return redPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getWhitePaintBrush100() {
        return whitePaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getYellowPaintBrush100() {
        return yellowPaintBrush100;
    }

    @Generated
    public static BasicPaintbrush getBlackPaintBrush1000() {
        return blackPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getBluePaintBrush1000() {
        return bluePaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getBrownPaintBrush1000() {
        return brownPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getCyanPaintBrush1000() {
        return cyanPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getGrayPaintBrush1000() {
        return grayPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getGreenPaintBrush1000() {
        return greenPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getLightBluePaintBrush1000() {
        return lightBluePaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getLightGrayPaintBrush1000() {
        return lightGrayPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getLimePaintBrush1000() {
        return limePaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getMagentaPaintBrush1000() {
        return magentaPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getOrangePaintBrush1000() {
        return orangePaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getPinkPaintBrush1000() {
        return pinkPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getPurplePaintBrush1000() {
        return purplePaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getRedPaintBrush1000() {
        return redPaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getWhitePaintBrush1000() {
        return whitePaintBrush1000;
    }

    @Generated
    public static BasicPaintbrush getYellowPaintBrush1000() {
        return yellowPaintBrush1000;
    }

    @Generated
    public static SlimefunItem getMysticalPigmentato() {
        return mysticalPigmentato;
    }

    @Generated
    public static SlimefunItem getMysticalTintanno() {
        return mysticalTintanno;
    }

    @Generated
    public static InfinitePaintbrush getPaintersResolve() {
        return paintersResolve;
    }

    @Generated
    public static SlimefunItem getBodyStand() {
        return bodyStand;
    }

    @Generated
    public static SlimefunItem getMindStand() {
        return mindStand;
    }

    @Generated
    public static SlimefunItem getSoulStand() {
        return soulStand;
    }

    @Generated
    public static SlimefunItem getMysticalAttitudinizer() {
        return mysticalAttitudinizer;
    }

    @Generated
    public static ImbuedStand getImbuedStand() {
        return imbuedStand;
    }

    @Generated
    public static PoseChanger getPoseChanger() {
        return poseChanger;
    }

    @Generated
    public static PoseCloner getPoseCloner() {
        return poseCloner;
    }
}

