/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
 *  lombok.Generated
 *  org.bukkit.Color
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.Exalted;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.prismaticgilder.PrismaticGilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.staveconfigurator.StaveConfigurator;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Generated;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Mechanisms {
    private static ChroniclerPanel chroniclerPanel1;
    private static ChroniclerPanel chroniclerPanel2;
    private static ChroniclerPanel chroniclerPanel3;
    private static ChroniclerPanel chroniclerPanel4;
    private static ChroniclerPanel chroniclerPanel5;
    private static RealisationAltar realisationAltar1;
    private static RealisationAltar realisationAltar2;
    private static RealisationAltar realisationAltar3;
    private static RealisationAltar realisationAltar4;
    private static RealisationAltar realisationAltar5;
    private static LiquefactionBasin liquefactionBasin1;
    private static LiquefactionBasin liquefactionBasin2;
    private static LiquefactionBasin liquefactionBasin3;
    private static LiquefactionBasin liquefactionBasin4;
    private static LiquefactionBasin liquefactionBasin5;
    private static StaveConfigurator staveConfigurator;
    private static PrismaticGilder prismaticGilder;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        chroniclerPanel1 = new ChroniclerPanel(ItemGroups.MECHANISMS, CrystaStacks.CHRONICLER_PANEL_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(Material.COBBLED_DEEPSLATE), new ItemStack(Material.COBBLED_DEEPSLATE), new ItemStack(Material.COBBLED_DEEPSLATE), SlimefunItems.CORINTHIAN_BRONZE_INGOT.item().clone(), new ItemStack(Material.AMETHYST_CLUSTER), SlimefunItems.CORINTHIAN_BRONZE_INGOT.item().clone(), SlimefunItems.MAGIC_LUMP_2.item().clone(), SlimefunItems.MAGIC_LUMP_2.item().clone(), SlimefunItems.MAGIC_LUMP_2.item().clone()}, 1);
        chroniclerPanel2 = new ChroniclerPanel(ItemGroups.MECHANISMS, CrystaStacks.CHRONICLER_PANEL_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(Material.DEEPSLATE_BRICKS), new ItemStack(Material.DEEPSLATE_BRICKS), new ItemStack(Material.DEEPSLATE_BRICKS), CrystaStacks.AMALGAMATE_INGOT_UNCOMMON.item().clone(), CrystaStacks.CHRONICLER_PANEL_1.item().clone(), CrystaStacks.AMALGAMATE_INGOT_UNCOMMON.item().clone(), SlimefunItems.MAGIC_LUMP_3.item().clone(), SlimefunItems.MAGIC_LUMP_3.item().clone(), SlimefunItems.MAGIC_LUMP_3.item().clone()}, 2);
        RecipeItem chroniclerT3Recipe = new RecipeItem(CrystaStacks.CHRONICLER_PANEL_2.item().clone(), StoryType.ELEMENTAL, 150, StoryType.CELESTIAL, 200, StoryType.VOID, 50);
        chroniclerPanel3 = new ChroniclerPanel(ItemGroups.MECHANISMS, CrystaStacks.CHRONICLER_PANEL_3, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, chroniclerT3Recipe.getDisplayRecipe(), 3);
        RecipeItem chroniclerT4Recipe = new RecipeItem(CrystaStacks.CHRONICLER_PANEL_3.item().clone(), StoryType.ELEMENTAL, 1000, StoryType.CELESTIAL, 850, StoryType.VOID, 650);
        chroniclerPanel4 = new ChroniclerPanel(ItemGroups.MECHANISMS, CrystaStacks.CHRONICLER_PANEL_4, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, chroniclerT4Recipe.getDisplayRecipe(), 4);
        chroniclerPanel5 = new ChroniclerPanel(ItemGroups.MECHANISMS, CrystaStacks.CHRONICLER_PANEL_5, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_DAWN.item().clone(), null, CrystaStacks.RUNE_BEAST.item().clone(), CrystaStacks.CHRONICLER_PANEL_4.item().clone(), CrystaStacks.RUNE_EIGHTFOLD.item().clone(), null, CrystaStacks.RUNE_NIGHT.item().clone(), null}, 5);
        realisationAltar1 = new RealisationAltar(ItemGroups.MECHANISMS, CrystaStacks.REALISATION_ALTAR_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, new ItemStack(Material.BOOK), null, SlimefunItems.CORINTHIAN_BRONZE_INGOT.item().clone(), new ItemStack(Material.AMETHYST_CLUSTER), SlimefunItems.CORINTHIAN_BRONZE_INGOT.item().clone(), SlimefunItems.MAGIC_LUMP_2.item().clone(), SlimefunItems.COMMON_TALISMAN.item().clone(), SlimefunItems.MAGIC_LUMP_2.item().clone()}, 1);
        realisationAltar2 = new RealisationAltar(ItemGroups.MECHANISMS, CrystaStacks.REALISATION_ALTAR_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, new ItemStack(Material.BOOK), null, CrystaStacks.AMALGAMATE_INGOT_UNCOMMON.item().clone(), realisationAltar1.getItem(), CrystaStacks.AMALGAMATE_INGOT_UNCOMMON.item().clone(), SlimefunItems.MAGIC_LUMP_3.item().clone(), SlimefunItems.SOULBOUND_RUNE.item().clone(), SlimefunItems.MAGIC_LUMP_3.item().clone()}, 2);
        RecipeItem realisationT3Recipe = new RecipeItem(CrystaStacks.REALISATION_ALTAR_2.item().clone(), StoryType.HISTORICAL, 100, StoryType.HUMAN, 350, StoryType.PHILOSOPHICAL, 150);
        realisationAltar3 = new RealisationAltar(ItemGroups.MECHANISMS, CrystaStacks.REALISATION_ALTAR_3, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, realisationT3Recipe.getDisplayRecipe(), 3);
        RecipeItem realisationT4Recipe = new RecipeItem(CrystaStacks.REALISATION_ALTAR_3.item().clone(), StoryType.HISTORICAL, 1100, StoryType.HUMAN, 720, StoryType.PHILOSOPHICAL, 450);
        realisationAltar4 = new RealisationAltar(ItemGroups.MECHANISMS, CrystaStacks.REALISATION_ALTAR_4, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, realisationT4Recipe.getDisplayRecipe(), 4);
        realisationAltar5 = new RealisationAltar(ItemGroups.MECHANISMS, CrystaStacks.REALISATION_ALTAR_5, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_TRUE_HOLY.item().clone(), null, CrystaStacks.RUNE_GATE.item().clone(), CrystaStacks.REALISATION_ALTAR_4.item().clone(), CrystaStacks.RUNE_TRUE_FIRE.item().clone(), null, CrystaStacks.RUNE_TRUE_WIND.item().clone(), null}, 5);
        liquefactionBasin1 = new LiquefactionBasin(ItemGroups.MECHANISMS, CrystaStacks.LIQUEFACTION_BASIN_1, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.REINFORCED_ALLOY_INGOT.item().clone(), null, SlimefunItems.REINFORCED_ALLOY_INGOT.item().clone(), SlimefunItems.REINFORCED_ALLOY_INGOT.item().clone(), new ItemStack(Material.CAULDRON), SlimefunItems.REINFORCED_ALLOY_INGOT.item().clone(), SlimefunItems.REINFORCED_ALLOY_INGOT.item().clone(), SlimefunItems.COMMON_TALISMAN.item().clone(), SlimefunItems.REINFORCED_ALLOY_INGOT.item().clone()}, 500, Color.fromRGB((int)150, (int)150, (int)150));
        liquefactionBasin2 = new LiquefactionBasin(ItemGroups.MECHANISMS, CrystaStacks.LIQUEFACTION_BASIN_2, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), null, CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), CrystaStacks.LIQUEFACTION_BASIN_1.item().clone(), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), SlimefunItems.ENCHANTMENT_RUNE.item().clone(), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone()}, 1250, Color.fromRGB((int)195, (int)195, (int)150));
        RecipeItem liquefactionT3Recipe = new RecipeItem(CrystaStacks.LIQUEFACTION_BASIN_2.item().clone(), StoryType.MECHANICAL, 90, StoryType.ALCHEMICAL, 250, StoryType.ANIMAL, 185);
        liquefactionBasin3 = new LiquefactionBasin(ItemGroups.MECHANISMS, CrystaStacks.LIQUEFACTION_BASIN_3, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, liquefactionT3Recipe.getDisplayRecipe(), 2500, Color.fromRGB((int)215, (int)200, (int)110));
        RecipeItem liquefactionT4Recipe = new RecipeItem(CrystaStacks.LIQUEFACTION_BASIN_3.item().clone(), StoryType.MECHANICAL, 750, StoryType.ALCHEMICAL, 700, StoryType.ANIMAL, 600);
        liquefactionBasin4 = new LiquefactionBasin(ItemGroups.MECHANISMS, CrystaStacks.LIQUEFACTION_BASIN_4, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, liquefactionT4Recipe.getDisplayRecipe(), 5000, Color.fromRGB((int)240, (int)220, (int)26));
        liquefactionBasin5 = new LiquefactionBasin(ItemGroups.MECHANISMS, CrystaStacks.LIQUEFACTION_BASIN_5, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_SOVEREIGN.item().clone(), null, CrystaStacks.RUNE_MOON.item().clone(), CrystaStacks.LIQUEFACTION_BASIN_4.item().clone(), CrystaStacks.RUNE_BLACK.item().clone(), null, CrystaStacks.RUNE_SOUL.item().clone(), null}, 10000, Color.fromRGB((int)240, (int)220, (int)200));
        RecipeItem staveConfiguratorRecipe = new RecipeItem(new ItemStack(Material.COPPER_BLOCK), StoryType.ELEMENTAL, 300, StoryType.MECHANICAL, 200, StoryType.ALCHEMICAL, 510);
        staveConfigurator = new StaveConfigurator(ItemGroups.MECHANISMS, CrystaStacks.STAVE_CONFIGURATOR, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, staveConfiguratorRecipe.getDisplayRecipe());
        RecipeItem prismaticGilderRecipe = new RecipeItem(CrystaStacks.LIQUEFACTION_BASIN_3.item().clone(), StoryType.MECHANICAL, 200, StoryType.VOID, 200, StoryType.CELESTIAL, 200, Exalted::isMaxStoryRank);
        prismaticGilder = new PrismaticGilder(ItemGroups.MECHANISMS, CrystaStacks.PRISMATIC_GILDER, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, prismaticGilderRecipe.getDisplayRecipe());
        chroniclerPanel1.register(plugin);
        chroniclerPanel2.register(plugin);
        chroniclerPanel3.register(plugin);
        chroniclerPanel4.register(plugin);
        chroniclerPanel5.register(plugin);
        realisationAltar1.register(plugin);
        realisationAltar2.register(plugin);
        realisationAltar3.register(plugin);
        realisationAltar4.register(plugin);
        realisationAltar5.register(plugin);
        liquefactionBasin1.register(plugin);
        liquefactionBasin2.register(plugin);
        liquefactionBasin3.register(plugin);
        liquefactionBasin4.register(plugin);
        liquefactionBasin5.register(plugin);
        staveConfigurator.register(plugin);
        prismaticGilder.register(plugin);
        LiquefactionBasinCache.addCraftingRecipe(chroniclerPanel3, chroniclerT3Recipe);
        LiquefactionBasinCache.addCraftingRecipe(chroniclerPanel4, chroniclerT4Recipe);
        LiquefactionBasinCache.addCraftingRecipe(realisationAltar3, realisationT3Recipe);
        LiquefactionBasinCache.addCraftingRecipe(realisationAltar4, realisationT4Recipe);
        LiquefactionBasinCache.addCraftingRecipe(liquefactionBasin3, liquefactionT3Recipe);
        LiquefactionBasinCache.addCraftingRecipe(liquefactionBasin4, liquefactionT4Recipe);
        LiquefactionBasinCache.addCraftingRecipe(staveConfigurator, staveConfiguratorRecipe);
        LiquefactionBasinCache.addCraftingRecipe(prismaticGilder, prismaticGilderRecipe);
    }

    @Generated
    private Mechanisms() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static ChroniclerPanel getChroniclerPanel1() {
        return chroniclerPanel1;
    }

    @Generated
    public static ChroniclerPanel getChroniclerPanel2() {
        return chroniclerPanel2;
    }

    @Generated
    public static ChroniclerPanel getChroniclerPanel3() {
        return chroniclerPanel3;
    }

    @Generated
    public static ChroniclerPanel getChroniclerPanel4() {
        return chroniclerPanel4;
    }

    @Generated
    public static ChroniclerPanel getChroniclerPanel5() {
        return chroniclerPanel5;
    }

    @Generated
    public static RealisationAltar getRealisationAltar1() {
        return realisationAltar1;
    }

    @Generated
    public static RealisationAltar getRealisationAltar2() {
        return realisationAltar2;
    }

    @Generated
    public static RealisationAltar getRealisationAltar3() {
        return realisationAltar3;
    }

    @Generated
    public static RealisationAltar getRealisationAltar4() {
        return realisationAltar4;
    }

    @Generated
    public static RealisationAltar getRealisationAltar5() {
        return realisationAltar5;
    }

    @Generated
    public static LiquefactionBasin getLiquefactionBasin1() {
        return liquefactionBasin1;
    }

    @Generated
    public static LiquefactionBasin getLiquefactionBasin2() {
        return liquefactionBasin2;
    }

    @Generated
    public static LiquefactionBasin getLiquefactionBasin3() {
        return liquefactionBasin3;
    }

    @Generated
    public static LiquefactionBasin getLiquefactionBasin4() {
        return liquefactionBasin4;
    }

    @Generated
    public static LiquefactionBasin getLiquefactionBasin5() {
        return liquefactionBasin5;
    }

    @Generated
    public static StaveConfigurator getStaveConfigurator() {
        return staveConfigurator;
    }

    @Generated
    public static PrismaticGilder getPrismaticGilder() {
        return prismaticGilder;
    }
}

