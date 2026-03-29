/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
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
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.PowderedEssence;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Skulls;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import java.util.EnumMap;
import java.util.Map;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Materials {
    private static final Map<StoryType, SlimefunItem> DUMMY_CRYSTAL_MAP = new EnumMap<StoryType, SlimefunItem>(StoryType.class);
    static final Map<StoryRarity, Map<StoryType, SlimefunItem>> CRYSTAL_MAP = new EnumMap<StoryRarity, Map<StoryType, SlimefunItem>>(StoryRarity.class);
    private static UnplaceableBlock blankCrystal;
    private static UnplaceableBlock polychromaticCrystal;
    private static UnplaceableBlock kaleidoscopicCrystal;
    private static UnplaceableBlock motleyCrystal;
    private static UnplaceableBlock prismaticCrystal;
    private static SlimefunItem amalgamateDustCommon;
    private static SlimefunItem amalgamateIngotCommon;
    private static SlimefunItem amalgamateDustUncommon;
    private static SlimefunItem amalgamateIngotUncommon;
    private static SlimefunItem amalgamateDustRare;
    private static SlimefunItem amalgamateIngotRare;
    private static SlimefunItem amalgamateDustEpic;
    private static SlimefunItem amalgamateIngotEpic;
    private static SlimefunItem amalgamateDustMythical;
    private static SlimefunItem amalgamateIngotMythical;
    private static SlimefunItem amalgamateDustUnique;
    private static SlimefunItem amalgamateIngotUnique;
    private static SlimefunItem arcaneSigil;
    private static SlimefunItem imbuedGlass;
    private static SlimefunItem uncannyPearl;
    private static SlimefunItem gildedPearl;
    private static SlimefunItem basicFibres;
    private static PowderedEssence powderedEssence;
    private static SlimefunItem magicalMilk;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        for (StoryType storyType : StoryType.getCachedValues()) {
            ThemeType theme = ThemeType.getByType(storyType);
            Crystal crystal = new Crystal(ItemGroups.DUMMY_ITEM_GROUP, ThemeType.themedSlimefunItemStack("CRY_CRYSTAL_DUMMY_" + String.valueOf((Object)storyType) + "_" + String.valueOf((Object)storyType), Skulls.getByType(storyType).getPlayerHead(), ThemeType.CRYSTAL, String.valueOf(theme.getColor()) + TextUtils.toTitleCase(String.valueOf((Object)storyType) + " Crystal"), "Magical Crystamae in it's physical form"), CrystaRecipeTypes.REALISATION_ALTAR_NORMAL, new ItemStack[0], StoryRarity.COMMON, storyType);
            crystal.register(plugin);
            DUMMY_CRYSTAL_MAP.put(storyType, (SlimefunItem)crystal);
        }
        for (Enum enum_ : StoryRarity.getCachedValues()) {
            EnumMap<StoryType, Crystal> storyTypeSlimefunItemMap = new EnumMap<StoryType, Crystal>(StoryType.class);
            for (StoryType type : StoryType.values()) {
                ThemeType theme = ThemeType.getByRarity((StoryRarity)enum_);
                Crystal slimefunItem = new Crystal(ItemGroups.CRYSTALS, ThemeType.themedSlimefunItemStack("CRY_CRYSTAL_" + String.valueOf(enum_) + "_" + type.toString(), Skulls.getByType(type).getPlayerHead(), ThemeType.CRYSTAL, String.valueOf(theme.getColor()) + TextUtils.toTitleCase(String.valueOf(enum_) + " " + String.valueOf((Object)type)) + " Crystal", "Magical Crystamae in it's physical form", "Higher tier blocks are more likely to", "provide rarer Crystal types.", "", "Provides " + String.valueOf(Crystal.getRarityValueMap().get(enum_)) + " Crysta."), CrystaRecipeTypes.REALISATION_ALTAR_NORMAL, new ItemStack[8], (StoryRarity)enum_, type);
                slimefunItem.register(plugin);
                storyTypeSlimefunItemMap.put(type, slimefunItem);
                CRYSTAL_MAP.put((StoryRarity)enum_, storyTypeSlimefunItemMap);
            }
        }
        blankCrystal = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.CRYSTAL_BLANK, CrystaRecipeTypes.NETHER_DRAINING, CrystaRecipeTypes.getDummyRecipe(CrystaStacks.CRYSTAL_BLANK.item().clone()));
        RecipeItem polychromaticCrystalRecipe = new RecipeItem(CrystaStacks.CRYSTAL_BLANK.item().clone(), StoryType.ELEMENTAL, 10, StoryType.MECHANICAL, 10, StoryType.ALCHEMICAL, 10);
        polychromaticCrystal = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.CRYSTAL_POLYCHROMATIC, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, polychromaticCrystalRecipe.getDisplayRecipe(), CrystaStacks.CRYSTAL_POLYCHROMATIC.asQuantity(3));
        RecipeItem kaleidoscopicCrystalRecipe = new RecipeItem(CrystaStacks.CRYSTAL_BLANK.item().clone(), StoryType.HISTORICAL, 10, StoryType.HUMAN, 10, StoryType.ANIMAL, 10);
        kaleidoscopicCrystal = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.CRYSTAL_KALEIDOSCOPIC, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, kaleidoscopicCrystalRecipe.getDisplayRecipe(), CrystaStacks.CRYSTAL_KALEIDOSCOPIC.asQuantity(3));
        RecipeItem motleyCrystalRecipe = new RecipeItem(CrystaStacks.CRYSTAL_BLANK.item().clone(), StoryType.CELESTIAL, 10, StoryType.VOID, 10, StoryType.PHILOSOPHICAL, 10);
        motleyCrystal = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.CRYSTAL_MOTLEY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, motleyCrystalRecipe.getDisplayRecipe(), CrystaStacks.CRYSTAL_MOTLEY.asQuantity(3));
        prismaticCrystal = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.CRYSTAL_PRISMATIC, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, null, null, CrystaStacks.CRYSTAL_POLYCHROMATIC.item().clone(), CrystaStacks.CRYSTAL_KALEIDOSCOPIC.item().clone(), CrystaStacks.CRYSTAL_MOTLEY.item().clone(), null, null, null});
        amalgamateDustCommon = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_DUST_COMMON, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.ELEMENTAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.MECHANICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.ALCHEMICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.HISTORICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.HUMAN).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.ANIMAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.CELESTIAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.VOID).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.COMMON).get((Object)StoryType.PHILOSOPHICAL).getItem()});
        amalgamateDustUncommon = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_DUST_UNCOMMON, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.ELEMENTAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.MECHANICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.ALCHEMICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.HISTORICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.HUMAN).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.ANIMAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.CELESTIAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.VOID).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNCOMMON).get((Object)StoryType.PHILOSOPHICAL).getItem()});
        amalgamateDustRare = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_DUST_RARE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.ELEMENTAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.MECHANICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.ALCHEMICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.HISTORICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.HUMAN).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.ANIMAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.CELESTIAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.VOID).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.RARE).get((Object)StoryType.PHILOSOPHICAL).getItem()});
        amalgamateDustEpic = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_DUST_EPIC, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.ELEMENTAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.MECHANICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.ALCHEMICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.HISTORICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.HUMAN).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.ANIMAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.CELESTIAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.VOID).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.EPIC).get((Object)StoryType.PHILOSOPHICAL).getItem()});
        amalgamateDustMythical = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_DUST_MYTHICAL, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.ELEMENTAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.MECHANICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.ALCHEMICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.HISTORICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.HUMAN).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.ANIMAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.CELESTIAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.VOID).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.MYTHICAL).get((Object)StoryType.PHILOSOPHICAL).getItem()});
        amalgamateDustUnique = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_DUST_UNIQUE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.ELEMENTAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.MECHANICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.ALCHEMICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.HISTORICAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.HUMAN).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.ANIMAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.CELESTIAL).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.VOID).getItem(), CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.PHILOSOPHICAL).getItem()});
        amalgamateIngotCommon = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_INGOT_COMMON, RecipeType.SMELTERY, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone()});
        amalgamateIngotUncommon = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_INGOT_UNCOMMON, RecipeType.SMELTERY, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_UNCOMMON.item().clone()});
        amalgamateIngotRare = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_INGOT_RARE, RecipeType.SMELTERY, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_RARE.item().clone()});
        amalgamateIngotEpic = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_INGOT_EPIC, RecipeType.SMELTERY, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_EPIC.item().clone()});
        amalgamateIngotMythical = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_INGOT_MYTHICAL, RecipeType.SMELTERY, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_MYTHICAL.item().clone()});
        amalgamateIngotUnique = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.AMALGAMATE_INGOT_UNIQUE, RecipeType.SMELTERY, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_UNIQUE.item().clone()});
        arcaneSigil = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.ARCANE_SIGIL, CrystaRecipeTypes.REALISATION_ALTAR_SIGIL, new ItemStack[8]);
        RecipeItem recipeItem = new RecipeItem(new ItemStack(Material.GLASS_PANE), StoryType.ELEMENTAL, 10, StoryType.HUMAN, 10, StoryType.VOID, 10);
        imbuedGlass = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.IMBUED_GLASS, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, recipeItem.getDisplayRecipe());
        RecipeItem uncannyPearlRecipe = new RecipeItem(new ItemStack(Material.ENDER_PEARL), StoryType.VOID, 25, StoryType.CELESTIAL, 25, StoryType.ALCHEMICAL, 25);
        uncannyPearl = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.UNCANNY_PEARL, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, uncannyPearlRecipe.getDisplayRecipe());
        gildedPearl = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.GILDED_PEARL, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{SlimefunItems.GILDED_IRON.item().clone(), SlimefunItems.GILDED_IRON.item().clone(), SlimefunItems.GILDED_IRON.item().clone(), SlimefunItems.GILDED_IRON.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone(), SlimefunItems.GILDED_IRON.item().clone(), SlimefunItems.GILDED_IRON.item().clone(), SlimefunItems.GILDED_IRON.item().clone(), SlimefunItems.GILDED_IRON.item().clone()});
        RecipeItem recipeItem2 = new RecipeItem(new ItemStack(Material.WHEAT), StoryType.MECHANICAL, 5, StoryType.HISTORICAL, 5, StoryType.HUMAN, 5);
        basicFibres = new UnplaceableBlock((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.BASIC_FIBRES, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, recipeItem2.getDisplayRecipe());
        RecipeItem powderedEssenceRecipe = new RecipeItem(new ItemStack(Material.BONE_MEAL), StoryType.ELEMENTAL, 20, StoryType.ALCHEMICAL, 25, StoryType.PHILOSOPHICAL, 15);
        powderedEssence = new PowderedEssence(ItemGroups.MATERIALS, CrystaStacks.POWDERED_ESSENCE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, powderedEssenceRecipe.getDisplayRecipe(), 250);
        RecipeItem magicalMilkRecipe = new RecipeItem(new ItemStack(Material.MILK_BUCKET), StoryType.ALCHEMICAL, 25, StoryType.HUMAN, 75, StoryType.ANIMAL, 50);
        magicalMilk = new SlimefunItem((ItemGroup)ItemGroups.MATERIALS, CrystaStacks.MAGICAL_MILK, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, magicalMilkRecipe.getDisplayRecipe());
        blankCrystal.register((SlimefunAddon)plugin);
        polychromaticCrystal.register((SlimefunAddon)plugin);
        kaleidoscopicCrystal.register((SlimefunAddon)plugin);
        motleyCrystal.register((SlimefunAddon)plugin);
        prismaticCrystal.register((SlimefunAddon)plugin);
        amalgamateDustCommon.register((SlimefunAddon)plugin);
        amalgamateDustUncommon.register((SlimefunAddon)plugin);
        amalgamateDustRare.register((SlimefunAddon)plugin);
        amalgamateDustEpic.register((SlimefunAddon)plugin);
        amalgamateDustMythical.register((SlimefunAddon)plugin);
        amalgamateDustUnique.register((SlimefunAddon)plugin);
        amalgamateIngotCommon.register((SlimefunAddon)plugin);
        amalgamateIngotUncommon.register((SlimefunAddon)plugin);
        amalgamateIngotRare.register((SlimefunAddon)plugin);
        amalgamateIngotEpic.register((SlimefunAddon)plugin);
        amalgamateIngotMythical.register((SlimefunAddon)plugin);
        amalgamateIngotUnique.register((SlimefunAddon)plugin);
        arcaneSigil.register((SlimefunAddon)plugin);
        imbuedGlass.register((SlimefunAddon)plugin);
        uncannyPearl.register((SlimefunAddon)plugin);
        gildedPearl.register((SlimefunAddon)plugin);
        basicFibres.register((SlimefunAddon)plugin);
        powderedEssence.register(plugin);
        magicalMilk.register((SlimefunAddon)plugin);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)polychromaticCrystal, polychromaticCrystalRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)kaleidoscopicCrystal, kaleidoscopicCrystalRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)motleyCrystal, motleyCrystalRecipe);
        LiquefactionBasinCache.addCraftingRecipe(imbuedGlass, recipeItem);
        LiquefactionBasinCache.addCraftingRecipe(uncannyPearl, uncannyPearlRecipe);
        LiquefactionBasinCache.addCraftingRecipe(basicFibres, recipeItem2);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)powderedEssence, powderedEssenceRecipe);
        LiquefactionBasinCache.addCraftingRecipe(magicalMilk, magicalMilkRecipe);
    }

    public static Map<StoryType, SlimefunItem> getDummyCrystalMap() {
        return DUMMY_CRYSTAL_MAP;
    }

    public static Map<StoryRarity, Map<StoryType, SlimefunItem>> getCrystalMap() {
        return CRYSTAL_MAP;
    }

    @Generated
    private Materials() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static UnplaceableBlock getBlankCrystal() {
        return blankCrystal;
    }

    @Generated
    public static UnplaceableBlock getPolychromaticCrystal() {
        return polychromaticCrystal;
    }

    @Generated
    public static UnplaceableBlock getKaleidoscopicCrystal() {
        return kaleidoscopicCrystal;
    }

    @Generated
    public static UnplaceableBlock getMotleyCrystal() {
        return motleyCrystal;
    }

    @Generated
    public static UnplaceableBlock getPrismaticCrystal() {
        return prismaticCrystal;
    }

    @Generated
    public static SlimefunItem getAmalgamateDustCommon() {
        return amalgamateDustCommon;
    }

    @Generated
    public static SlimefunItem getAmalgamateIngotCommon() {
        return amalgamateIngotCommon;
    }

    @Generated
    public static SlimefunItem getAmalgamateDustUncommon() {
        return amalgamateDustUncommon;
    }

    @Generated
    public static SlimefunItem getAmalgamateIngotUncommon() {
        return amalgamateIngotUncommon;
    }

    @Generated
    public static SlimefunItem getAmalgamateDustRare() {
        return amalgamateDustRare;
    }

    @Generated
    public static SlimefunItem getAmalgamateIngotRare() {
        return amalgamateIngotRare;
    }

    @Generated
    public static SlimefunItem getAmalgamateDustEpic() {
        return amalgamateDustEpic;
    }

    @Generated
    public static SlimefunItem getAmalgamateIngotEpic() {
        return amalgamateIngotEpic;
    }

    @Generated
    public static SlimefunItem getAmalgamateDustMythical() {
        return amalgamateDustMythical;
    }

    @Generated
    public static SlimefunItem getAmalgamateIngotMythical() {
        return amalgamateIngotMythical;
    }

    @Generated
    public static SlimefunItem getAmalgamateDustUnique() {
        return amalgamateDustUnique;
    }

    @Generated
    public static SlimefunItem getAmalgamateIngotUnique() {
        return amalgamateIngotUnique;
    }

    @Generated
    public static SlimefunItem getArcaneSigil() {
        return arcaneSigil;
    }

    @Generated
    public static SlimefunItem getImbuedGlass() {
        return imbuedGlass;
    }

    @Generated
    public static SlimefunItem getUncannyPearl() {
        return uncannyPearl;
    }

    @Generated
    public static SlimefunItem getGildedPearl() {
        return gildedPearl;
    }

    @Generated
    public static SlimefunItem getBasicFibres() {
        return basicFibres;
    }

    @Generated
    public static PowderedEssence getPowderedEssence() {
        return powderedEssence;
    }

    @Generated
    public static SlimefunItem getMagicalMilk() {
        return magicalMilk;
    }
}

