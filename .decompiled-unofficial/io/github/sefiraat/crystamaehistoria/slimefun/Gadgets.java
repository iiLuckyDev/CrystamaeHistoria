/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
 *  io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag
 *  lombok.Generated
 *  org.bukkit.Color
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Tag
 *  org.bukkit.entity.EntityType
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.AngelBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.CursedEarth;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.EnderInhibitor;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.ExaltationStand;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.ExpCollector;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.FragmentedVoid;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.GlassOfMilk;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.GreenHouseGlass;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobCandle;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobFan;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobLamp;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobMat;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobTrap;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MysteriousTicker;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MysteriousTickerNoInteraction;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.PhilosophersSpray;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.TrophyDisplay;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.Waystone;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import java.util.ArrayList;
import java.util.HashSet;
import lombok.Generated;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public final class Gadgets {
    private static MobLamp abstractionLamp;
    private static MobLamp dispersionLamp;
    private static MobLamp exodusLamp;
    private static MobFan inversionVacuum;
    private static MobFan antipodalVacuum;
    private static MobFan counterpoleVacuum;
    private static CursedEarth cursedEarth;
    private static CursedEarth dreadfulDirt;
    private static CursedEarth soulfilledSoil;
    private static MobMat searingPlate;
    private static MobMat doomedPlate;
    private static MobMat evisceratingPlate;
    private static MobMat shreddingPlate;
    private static MobTrap trapPlate;
    private static ExpCollector basicExpCollector;
    private static ExpCollector infusedExpCollector;
    private static ExpCollector arcaneExpCollector;
    private static EnderInhibitor basicEnderInhibitor;
    private static EnderInhibitor advancedEnderInhibitor;
    private static MobCandle dimMobCandle;
    private static MobCandle brightMobCandle;
    private static MobCandle scintillatingMobCandle;
    private static MobCandle coruscatingMobCandle;
    private static MysteriousTickerNoInteraction mysteriousPottedPlant;
    private static MysteriousTicker mysteriousPlant;
    private static MysteriousTicker mysteriousGlass;
    private static MysteriousTicker mysteriousWool;
    private static MysteriousTicker mysteriousTerracotta;
    private static MysteriousTicker mysteriousGlazedTerracotta;
    private static MysteriousTicker mysteriousConcrete;
    private static GreenHouseGlass greenHouseGlass;
    private static GreenHouseGlass focusedGreenHouseGlass;
    private static GreenHouseGlass magnifyingGreenHouseGlass;
    private static TrophyDisplay trophyDisplay;
    private static ExaltationStand exaltationStand;
    private static Waystone waystone;
    private static AngelBlock angelBlock;
    private static PhilosophersSpray philosophersSpray;
    private static GlassOfMilk glassOfMilk;
    private static FragmentedVoid fragmentedVoid;
    private static FragmentedVoid shatteredVoid;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        ItemStack uniqueVoid = Materials.CRYSTAL_MAP.get((Object)StoryRarity.UNIQUE).get((Object)StoryType.VOID).getItem();
        ItemStack glass = new ItemStack(Material.GLASS);
        RecipeItem abstractionLampRecipe = new RecipeItem(new ItemStack(Material.LANTERN), StoryType.ALCHEMICAL, 50, StoryType.HUMAN, 75, StoryType.PHILOSOPHICAL, 75);
        abstractionLamp = new MobLamp(ItemGroups.GADGETS, CrystaStacks.ABSTRACTION_LAMP, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, abstractionLampRecipe.getDisplayRecipe(), 5.0, 0.3);
        RecipeItem dispersionLampRecipe = new RecipeItem(CrystaStacks.ABSTRACTION_LAMP.item().clone(), StoryType.ALCHEMICAL, 250, StoryType.HUMAN, 150, StoryType.PHILOSOPHICAL, 300);
        dispersionLamp = new MobLamp(ItemGroups.GADGETS, CrystaStacks.DISPERSION_LAMP, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, dispersionLampRecipe.getDisplayRecipe(), 7.0, 0.5);
        exodusLamp = new MobLamp(ItemGroups.GADGETS, CrystaStacks.EXODUS_LAMP, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_BEGINNING.item().clone(), null, CrystaStacks.RUNE_NIGHT.item().clone(), CrystaStacks.DISPERSION_LAMP.item().clone(), CrystaStacks.RUNE_TWILIGHT.item().clone(), null, CrystaStacks.RUNE_BRIGHT_SHIELD.item().clone(), null}, 10.0, 1.0);
        RecipeItem inversionVacuumRecipe = new RecipeItem(CrystaStacks.ABSTRACTION_LAMP.item().clone(), StoryType.HISTORICAL, 200, StoryType.VOID, 200, StoryType.PHILOSOPHICAL, 180);
        inversionVacuum = new MobFan(ItemGroups.GADGETS, CrystaStacks.INVERSION_VACUUM, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, inversionVacuumRecipe.getDisplayRecipe(), 5.0);
        RecipeItem antipodalVacuumRecipe = new RecipeItem(CrystaStacks.DISPERSION_LAMP.item().clone(), StoryType.HISTORICAL, 400, StoryType.VOID, 400, StoryType.PHILOSOPHICAL, 360);
        antipodalVacuum = new MobFan(ItemGroups.GADGETS, CrystaStacks.ANTIPODAL_VACUUM, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, antipodalVacuumRecipe.getDisplayRecipe(), 10.0);
        counterpoleVacuum = new MobFan(ItemGroups.GADGETS, CrystaStacks.COUNTERPOLE_VACUUM, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_GATE.item().clone(), null, CrystaStacks.RUNE_TRUE_HOLY.item().clone(), CrystaStacks.DISPERSION_LAMP.item().clone(), CrystaStacks.RUNE_SUN.item().clone(), null, CrystaStacks.RUNE_TRUE_LIGHTNING.item().clone(), null}, 15.0);
        ArrayList<EntityType> cursedEarthSpawns = new ArrayList<EntityType>();
        cursedEarthSpawns.add(EntityType.SKELETON);
        cursedEarthSpawns.add(EntityType.ZOMBIE);
        cursedEarthSpawns.add(EntityType.ENDERMAN);
        cursedEarthSpawns.add(EntityType.CREEPER);
        cursedEarthSpawns.add(EntityType.CAVE_SPIDER);
        cursedEarthSpawns.add(EntityType.SILVERFISH);
        cursedEarth = new CursedEarth(ItemGroups.GADGETS, CrystaStacks.CURSED_EARTH, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{uniqueVoid, uniqueVoid, uniqueVoid, new ItemStack(Material.DIRT), new ItemStack(Material.DIRT), new ItemStack(Material.DIRT), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone()}, 20.0, 7, cursedEarthSpawns, Color.fromRGB((int)95, (int)50, (int)15));
        ArrayList<EntityType> dreadfulDirtSpawns = new ArrayList<EntityType>(cursedEarthSpawns);
        dreadfulDirtSpawns.add(EntityType.WITHER_SKELETON);
        dreadfulDirtSpawns.add(EntityType.HUSK);
        dreadfulDirtSpawns.add(EntityType.STRAY);
        dreadfulDirtSpawns.add(EntityType.BLAZE);
        dreadfulDirtSpawns.add(EntityType.ZOMBIE_VILLAGER);
        RecipeItem dreadfulDirtRecipe = new RecipeItem(CrystaStacks.CURSED_EARTH.item().clone(), StoryType.VOID, 700, StoryType.ANIMAL, 200, StoryType.HISTORICAL, 100);
        dreadfulDirt = new CursedEarth(ItemGroups.GADGETS, CrystaStacks.DREADFUL_DIRT, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, dreadfulDirtRecipe.getDisplayRecipe(), 10.0, 15, dreadfulDirtSpawns, Color.BLACK);
        ArrayList<EntityType> soulfilledSoilSpawns = new ArrayList<EntityType>();
        soulfilledSoilSpawns.add(EntityType.COW);
        soulfilledSoilSpawns.add(EntityType.MOOSHROOM);
        soulfilledSoilSpawns.add(EntityType.CHICKEN);
        soulfilledSoilSpawns.add(EntityType.PIG);
        soulfilledSoilSpawns.add(EntityType.SHEEP);
        soulfilledSoilSpawns.add(EntityType.GOAT);
        soulfilledSoilSpawns.add(EntityType.AXOLOTL);
        soulfilledSoilSpawns.add(EntityType.DOLPHIN);
        soulfilledSoilSpawns.add(EntityType.TURTLE);
        soulfilledSoilSpawns.add(EntityType.CAT);
        soulfilledSoilSpawns.add(EntityType.WOLF);
        soulfilledSoilSpawns.add(EntityType.LLAMA);
        soulfilledSoilSpawns.add(EntityType.HORSE);
        soulfilledSoil = new CursedEarth(ItemGroups.GADGETS, CrystaStacks.SOULFILLED_SOIL, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_TRUE_EARTH.item().clone(), null, CrystaStacks.RUNE_TRUE_WATER.item().clone(), CrystaStacks.DREADFUL_DIRT.item().clone(), CrystaStacks.RUNE_CIRCLE.item().clone(), null, CrystaStacks.RUNE_SOUL.item().clone(), null}, 10.0, 15, soulfilledSoilSpawns, Color.WHITE);
        RecipeItem searingPlateRecipe = new RecipeItem(new ItemStack(Material.STONE_PRESSURE_PLATE), StoryType.ALCHEMICAL, 120, StoryType.VOID, 220, StoryType.MECHANICAL, 180);
        searingPlate = new MobMat(ItemGroups.GADGETS, CrystaStacks.SEARING_PLATE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, searingPlateRecipe.getDisplayRecipe(), 1.0, false);
        RecipeItem doomedPlateRecipe = new RecipeItem(CrystaStacks.SEARING_PLATE.item().clone(), StoryType.ALCHEMICAL, 240, StoryType.VOID, 440, StoryType.MECHANICAL, 360);
        doomedPlate = new MobMat(ItemGroups.GADGETS, CrystaStacks.DOOMED_PLATE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, doomedPlateRecipe.getDisplayRecipe(), 1.0, true);
        RecipeItem evisceratingPlateRecipe = new RecipeItem(CrystaStacks.DOOMED_PLATE.item().clone(), StoryType.ALCHEMICAL, 480, StoryType.VOID, 880, StoryType.MECHANICAL, 720);
        evisceratingPlate = new MobMat(ItemGroups.GADGETS, CrystaStacks.EVISCERATING_PLATE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, evisceratingPlateRecipe.getDisplayRecipe(), 2.0, true);
        shreddingPlate = new MobMat(ItemGroups.GADGETS, CrystaStacks.SHREDDING_PLATE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_MOON.item().clone(), null, CrystaStacks.RUNE_SOVEREIGN.item().clone(), CrystaStacks.EVISCERATING_PLATE.item().clone(), CrystaStacks.RUNE_BLINKING.item().clone(), null, CrystaStacks.RUNE_CHARM.item().clone(), null}, 3.0, true);
        RecipeItem trapPlateRecipe = new RecipeItem(CrystaStacks.EVISCERATING_PLATE.item().clone(), StoryType.ALCHEMICAL, 400, StoryType.CELESTIAL, 100, StoryType.MECHANICAL, 50);
        trapPlate = new MobTrap(ItemGroups.GADGETS, CrystaStacks.TRAP_PLATE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, trapPlateRecipe.getDisplayRecipe());
        RecipeItem basicExpCollectorRecipe = new RecipeItem(SlimefunItems.EXP_COLLECTOR.item().clone(), StoryType.MECHANICAL, 150, StoryType.HUMAN, 200, StoryType.ANIMAL, 250);
        basicExpCollector = new ExpCollector(ItemGroups.GADGETS, CrystaStacks.EXP_COLLECTOR_BASIC, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, basicExpCollectorRecipe.getDisplayRecipe(), 2500, 4);
        RecipeItem infusedExpCollectorRecipe = new RecipeItem(CrystaStacks.EXP_COLLECTOR_BASIC.item().clone(), StoryType.MECHANICAL, 740, StoryType.HUMAN, 560, StoryType.ANIMAL, 885);
        infusedExpCollector = new ExpCollector(ItemGroups.GADGETS, CrystaStacks.EXP_COLLECTOR_INFUSED, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, infusedExpCollectorRecipe.getDisplayRecipe(), 10000, 8);
        arcaneExpCollector = new ExpCollector(ItemGroups.GADGETS, CrystaStacks.EXP_COLLECTOR_ARCANE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_CHANGE.item().clone(), null, CrystaStacks.RUNE_DRAGON.item().clone(), CrystaStacks.EXP_COLLECTOR_INFUSED.item().clone(), CrystaStacks.RUNE_TRUE_FIRE.item().clone(), null, CrystaStacks.RUNE_TRUE_WIND.item().clone(), null}, 999999, 8);
        basicEnderInhibitor = new EnderInhibitor(ItemGroups.GADGETS, CrystaStacks.ENDER_INHIBITOR_BASIC, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CrystaStacks.UNCANNY_PEARL.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone(), SlimefunItems.COOLER.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone(), CrystaStacks.UNCANNY_PEARL.item().clone()}, 4.0);
        advancedEnderInhibitor = new EnderInhibitor(ItemGroups.GADGETS, CrystaStacks.ENDER_INHIBITOR_ADVANCED, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CrystaStacks.GILDED_PEARL.item().clone(), CrystaStacks.GILDED_PEARL.item().clone(), CrystaStacks.GILDED_PEARL.item().clone(), CrystaStacks.GILDED_PEARL.item().clone(), CrystaStacks.ENDER_INHIBITOR_BASIC.item().clone(), CrystaStacks.GILDED_PEARL.item().clone(), CrystaStacks.GILDED_PEARL.item().clone(), CrystaStacks.GILDED_PEARL.item().clone(), CrystaStacks.GILDED_PEARL.item().clone()}, 8.0);
        RecipeItem dimMobCandleRecipe = new RecipeItem(new ItemStack(Material.BLACK_CANDLE), StoryType.HISTORICAL, 50, StoryType.CELESTIAL, 50, StoryType.ANIMAL, 50);
        dimMobCandle = new MobCandle(ItemGroups.GADGETS, CrystaStacks.MOB_CANDLE_DIM, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, dimMobCandleRecipe.getDisplayRecipe(), 16, 7200);
        RecipeItem brightMobCandleRecipe = new RecipeItem(CrystaStacks.MOB_CANDLE_DIM.item().clone(), StoryType.HISTORICAL, 100, StoryType.CELESTIAL, 100, StoryType.ANIMAL, 100);
        brightMobCandle = new MobCandle(ItemGroups.GADGETS, CrystaStacks.MOB_CANDLE_BRIGHT, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, brightMobCandleRecipe.getDisplayRecipe(), 32, 86400);
        RecipeItem scintillatingMobCandleRecipe = new RecipeItem(CrystaStacks.MOB_CANDLE_BRIGHT.item().clone(), StoryType.HISTORICAL, 200, StoryType.CELESTIAL, 200, StoryType.ANIMAL, 200);
        scintillatingMobCandle = new MobCandle(ItemGroups.GADGETS, CrystaStacks.MOB_CANDLE_SCINTILLATING, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, scintillatingMobCandleRecipe.getDisplayRecipe(), 64, 172800);
        coruscatingMobCandle = new MobCandle(ItemGroups.GADGETS, CrystaStacks.MOB_CANDLE_CORUSCATING, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_BEAST.item().clone(), null, CrystaStacks.RUNE_BLACK.item().clone(), CrystaStacks.MOB_CANDLE_SCINTILLATING.item().clone(), CrystaStacks.RUNE_DAWN.item().clone(), null, CrystaStacks.RUNE_EIGHTFOLD.item().clone(), null}, 64, 1209600);
        mysteriousPottedPlant = new MysteriousTickerNoInteraction(ItemGroups.GADGETS, CrystaStacks.MYSTERIOUS_POTTED_PLANT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), null, null, new ItemStack(Material.FLOWER_POT), null, null, null, null}, Tag.FLOWER_POTS.getValues(), 15, block -> ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), Particle.WAX_OFF, 0.3, 2));
        RecipeItem mysteriousPlantRecipe = new RecipeItem(CrystaStacks.MYSTERIOUS_POTTED_PLANT.item().clone(), StoryType.ELEMENTAL, 25, StoryType.ALCHEMICAL, 25, StoryType.VOID, 25);
        mysteriousPlant = new MysteriousTicker(ItemGroups.GADGETS, CrystaStacks.MYSTERIOUS_PLANT, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, mysteriousPlantRecipe.getDisplayRecipe(), Tag.SMALL_FLOWERS.getValues(), 15, block -> ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), Particle.SPLASH, 0.5, 3));
        RecipeItem mysteriousGlassRecipe = new RecipeItem(new ItemStack(Material.GLASS), StoryType.MECHANICAL, 5, StoryType.ALCHEMICAL, 5, StoryType.PHILOSOPHICAL, 5);
        mysteriousGlass = new MysteriousTicker(ItemGroups.GADGETS, CrystaStacks.MYSTERIOUS_GLASS, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, mysteriousGlassRecipe.getDisplayRecipe(), SlimefunTag.GLASS_BLOCKS.getValues(), 15);
        RecipeItem mysteriousWoolRecipe = new RecipeItem(new ItemStack(Material.WHITE_WOOL), StoryType.MECHANICAL, 5, StoryType.ALCHEMICAL, 5, StoryType.PHILOSOPHICAL, 5);
        mysteriousWool = new MysteriousTicker(ItemGroups.GADGETS, CrystaStacks.MYSTERIOUS_WOOL, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, mysteriousWoolRecipe.getDisplayRecipe(), SlimefunTag.WOOL.getValues(), 15);
        RecipeItem mysteriousTerracottaRecipe = new RecipeItem(new ItemStack(Material.TERRACOTTA), StoryType.MECHANICAL, 5, StoryType.ALCHEMICAL, 5, StoryType.PHILOSOPHICAL, 5);
        mysteriousTerracotta = new MysteriousTicker(ItemGroups.GADGETS, CrystaStacks.MYSTERIOUS_TERRACOTTA, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, mysteriousTerracottaRecipe.getDisplayRecipe(), SlimefunTag.TERRACOTTA.getValues(), 15);
        RecipeItem mysteriousGlazedTerracottaRecipe = new RecipeItem(new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), StoryType.MECHANICAL, 5, StoryType.ALCHEMICAL, 5, StoryType.PHILOSOPHICAL, 5);
        mysteriousGlazedTerracotta = new MysteriousTicker(ItemGroups.GADGETS, CrystaStacks.MYSTERIOUS_GLAZED_TERRACOTTA, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, mysteriousGlazedTerracottaRecipe.getDisplayRecipe(), CrystaTag.GLAZED_TERRACOTTA.getValues(), 15);
        RecipeItem mysteriousConcreteRecipe = new RecipeItem(new ItemStack(Material.WHITE_CONCRETE), StoryType.MECHANICAL, 5, StoryType.ALCHEMICAL, 5, StoryType.PHILOSOPHICAL, 5);
        mysteriousConcrete = new MysteriousTicker(ItemGroups.GADGETS, CrystaStacks.MYSTERIOUS_CONCRETE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, mysteriousConcreteRecipe.getDisplayRecipe(), CrystaTag.CONCRETE_BLOCKS.getValues(), 15);
        greenHouseGlass = new GreenHouseGlass((ItemGroup)ItemGroups.GADGETS, CrystaStacks.GREEN_HOUSE_GLASS, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_EPIC.item().clone(), new ItemStack(Material.GLASS), CrystaStacks.AMALGAMATE_DUST_EPIC.item().clone(), new ItemStack(Material.GLASS), SlimefunItems.POWER_CRYSTAL.item().clone(), new ItemStack(Material.GLASS), CrystaStacks.AMALGAMATE_DUST_EPIC.item().clone(), new ItemStack(Material.GLASS), CrystaStacks.AMALGAMATE_DUST_EPIC.item().clone()}, 5);
        RecipeItem focusedGreenHouseGlassRecipe = new RecipeItem(CrystaStacks.GREEN_HOUSE_GLASS.item().clone(), StoryType.ALCHEMICAL, 15, StoryType.ANIMAL, 40, StoryType.PHILOSOPHICAL, 30);
        focusedGreenHouseGlass = new GreenHouseGlass((ItemGroup)ItemGroups.GADGETS, CrystaStacks.GREEN_HOUSE_GLASS_FOCUSED, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, focusedGreenHouseGlassRecipe.getDisplayRecipe(), 10);
        magnifyingGreenHouseGlass = new GreenHouseGlass((ItemGroup)ItemGroups.GADGETS, CrystaStacks.GREEN_HOUSE_GLASS_MAGNIFYING, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_BEGINNING.item().clone(), null, CrystaStacks.RUNE_NIGHT.item().clone(), CrystaStacks.GREEN_HOUSE_GLASS_FOCUSED.item().clone(), CrystaStacks.RUNE_PUNISHMENT.item().clone(), null, CrystaStacks.RUNE_BLACK_SWORD.item().clone(), null}, 20);
        RecipeItem trophyDisplayRecipe = new RecipeItem(new ItemStack(Material.POLISHED_BLACKSTONE_BRICK_WALL), StoryType.MECHANICAL, 10, StoryType.HUMAN, 10, StoryType.PHILOSOPHICAL, 10);
        trophyDisplay = new TrophyDisplay(ItemGroups.GADGETS, CrystaStacks.TROPHY_STAND, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, trophyDisplayRecipe.getDisplayRecipe());
        RecipeItem exaltationStandRecipe = new RecipeItem(new ItemStack(Material.PRISMARINE_WALL), StoryType.MECHANICAL, 50, StoryType.HUMAN, 50, StoryType.PHILOSOPHICAL, 50);
        exaltationStand = new ExaltationStand(ItemGroups.GADGETS, CrystaStacks.EXALTATION_STAND, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltationStandRecipe.getDisplayRecipe());
        HashSet<Material> waystoneMaterials = new HashSet<Material>();
        waystoneMaterials.add(Material.RED_NETHER_BRICK_WALL);
        waystoneMaterials.add(Material.END_STONE_BRICK_WALL);
        waystoneMaterials.add(Material.DEEPSLATE_BRICK_WALL);
        waystoneMaterials.add(Material.POLISHED_BLACKSTONE_BRICK_WALL);
        waystoneMaterials.add(Material.STONE_BRICK_WALL);
        waystoneMaterials.add(Material.NETHER_BRICK_WALL);
        RecipeItem waystoneRecipe = new RecipeItem(new ItemStack(Material.LODESTONE), StoryType.HISTORICAL, 50, StoryType.HUMAN, 50, StoryType.CELESTIAL, 50);
        waystone = new Waystone(ItemGroups.GADGETS, CrystaStacks.WAYSTONE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, waystoneRecipe.getDisplayRecipe(), waystoneMaterials, 5, block -> ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), Particle.PORTAL, 0.5, 3));
        angelBlock = new AngelBlock(ItemGroups.GADGETS, CrystaStacks.ANGEL_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS)}, CrystaStacks.ANGEL_BLOCK.asQuantity(8));
        philosophersSpray = new PhilosophersSpray(ItemGroups.GADGETS, CrystaStacks.PHILOSOPHERS_SPRAY, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), new ItemStack(Material.DISPENSER), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone(), CrystaStacks.ARCANE_DISPLACER.item().clone(), CrystaStacks.AMALGAMATE_INGOT_RARE.item().clone()});
        glassOfMilk = new GlassOfMilk(ItemGroups.GADGETS, CrystaStacks.GLASS_OF_MILK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{glass, null, glass, glass, CrystaStacks.GLASS_OF_MILK.item().clone(), glass, glass, glass, glass});
        RecipeItem fragmentedVoidRecipe = new RecipeItem(SlimefunItems.INFUSED_HOPPER.item().clone(), StoryType.VOID, 120, StoryType.MECHANICAL, 100, StoryType.CELESTIAL, 80);
        fragmentedVoid = new FragmentedVoid(ItemGroups.GADGETS, CrystaStacks.FRAGMENTED_VOID, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, fragmentedVoidRecipe.getDisplayRecipe(), 5);
        RecipeItem shatteredVoidRecipe = new RecipeItem(CrystaStacks.FRAGMENTED_VOID.item().clone(), StoryType.VOID, 120, StoryType.MECHANICAL, 100, StoryType.CELESTIAL, 80);
        shatteredVoid = new FragmentedVoid(ItemGroups.GADGETS, CrystaStacks.SHATTERED_VOID, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, shatteredVoidRecipe.getDisplayRecipe(), 7);
        abstractionLamp.register(plugin);
        dispersionLamp.register(plugin);
        exodusLamp.register(plugin);
        inversionVacuum.register(plugin);
        antipodalVacuum.register(plugin);
        counterpoleVacuum.register(plugin);
        cursedEarth.register(plugin);
        dreadfulDirt.register(plugin);
        soulfilledSoil.register(plugin);
        searingPlate.register(plugin);
        doomedPlate.register(plugin);
        evisceratingPlate.register(plugin);
        shreddingPlate.register(plugin);
        trapPlate.register(plugin);
        basicExpCollector.register(plugin);
        infusedExpCollector.register(plugin);
        arcaneExpCollector.register(plugin);
        basicEnderInhibitor.register(plugin);
        advancedEnderInhibitor.register(plugin);
        dimMobCandle.register(plugin);
        brightMobCandle.register(plugin);
        scintillatingMobCandle.register(plugin);
        coruscatingMobCandle.register(plugin);
        mysteriousPottedPlant.register(plugin);
        mysteriousPlant.register(plugin);
        mysteriousGlass.register(plugin);
        mysteriousWool.register(plugin);
        mysteriousTerracotta.register(plugin);
        mysteriousGlazedTerracotta.register(plugin);
        mysteriousConcrete.register(plugin);
        greenHouseGlass.register(plugin);
        focusedGreenHouseGlass.register(plugin);
        magnifyingGreenHouseGlass.register(plugin);
        trophyDisplay.register(plugin);
        exaltationStand.register(plugin);
        waystone.register(plugin);
        angelBlock.register(plugin);
        philosophersSpray.register(plugin);
        glassOfMilk.register(plugin);
        fragmentedVoid.register(plugin);
        shatteredVoid.register(plugin);
        LiquefactionBasinCache.addCraftingRecipe(abstractionLamp, abstractionLampRecipe);
        LiquefactionBasinCache.addCraftingRecipe(dispersionLamp, dispersionLampRecipe);
        LiquefactionBasinCache.addCraftingRecipe(inversionVacuum, inversionVacuumRecipe);
        LiquefactionBasinCache.addCraftingRecipe(antipodalVacuum, antipodalVacuumRecipe);
        LiquefactionBasinCache.addCraftingRecipe(dreadfulDirt, dreadfulDirtRecipe);
        LiquefactionBasinCache.addCraftingRecipe(searingPlate, searingPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(doomedPlate, doomedPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(evisceratingPlate, evisceratingPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(trapPlate, trapPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(basicExpCollector, basicExpCollectorRecipe);
        LiquefactionBasinCache.addCraftingRecipe(infusedExpCollector, infusedExpCollectorRecipe);
        LiquefactionBasinCache.addCraftingRecipe(dimMobCandle, dimMobCandleRecipe);
        LiquefactionBasinCache.addCraftingRecipe(brightMobCandle, brightMobCandleRecipe);
        LiquefactionBasinCache.addCraftingRecipe(scintillatingMobCandle, scintillatingMobCandleRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousPlant, mysteriousPlantRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousGlass, mysteriousGlassRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousWool, mysteriousWoolRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousTerracotta, mysteriousTerracottaRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousGlazedTerracotta, mysteriousGlazedTerracottaRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousConcrete, mysteriousConcreteRecipe);
        LiquefactionBasinCache.addCraftingRecipe(focusedGreenHouseGlass, focusedGreenHouseGlassRecipe);
        LiquefactionBasinCache.addCraftingRecipe(trophyDisplay, trophyDisplayRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltationStand, exaltationStandRecipe);
        LiquefactionBasinCache.addCraftingRecipe(waystone, waystoneRecipe);
        LiquefactionBasinCache.addCraftingRecipe(fragmentedVoid, fragmentedVoidRecipe);
        LiquefactionBasinCache.addCraftingRecipe(shatteredVoid, shatteredVoidRecipe);
    }

    @Generated
    private Gadgets() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static MobLamp getAbstractionLamp() {
        return abstractionLamp;
    }

    @Generated
    public static MobLamp getDispersionLamp() {
        return dispersionLamp;
    }

    @Generated
    public static MobLamp getExodusLamp() {
        return exodusLamp;
    }

    @Generated
    public static MobFan getInversionVacuum() {
        return inversionVacuum;
    }

    @Generated
    public static MobFan getAntipodalVacuum() {
        return antipodalVacuum;
    }

    @Generated
    public static MobFan getCounterpoleVacuum() {
        return counterpoleVacuum;
    }

    @Generated
    public static CursedEarth getCursedEarth() {
        return cursedEarth;
    }

    @Generated
    public static CursedEarth getDreadfulDirt() {
        return dreadfulDirt;
    }

    @Generated
    public static CursedEarth getSoulfilledSoil() {
        return soulfilledSoil;
    }

    @Generated
    public static MobMat getSearingPlate() {
        return searingPlate;
    }

    @Generated
    public static MobMat getDoomedPlate() {
        return doomedPlate;
    }

    @Generated
    public static MobMat getEvisceratingPlate() {
        return evisceratingPlate;
    }

    @Generated
    public static MobMat getShreddingPlate() {
        return shreddingPlate;
    }

    @Generated
    public static MobTrap getTrapPlate() {
        return trapPlate;
    }

    @Generated
    public static ExpCollector getBasicExpCollector() {
        return basicExpCollector;
    }

    @Generated
    public static ExpCollector getInfusedExpCollector() {
        return infusedExpCollector;
    }

    @Generated
    public static ExpCollector getArcaneExpCollector() {
        return arcaneExpCollector;
    }

    @Generated
    public static EnderInhibitor getBasicEnderInhibitor() {
        return basicEnderInhibitor;
    }

    @Generated
    public static EnderInhibitor getAdvancedEnderInhibitor() {
        return advancedEnderInhibitor;
    }

    @Generated
    public static MobCandle getDimMobCandle() {
        return dimMobCandle;
    }

    @Generated
    public static MobCandle getBrightMobCandle() {
        return brightMobCandle;
    }

    @Generated
    public static MobCandle getScintillatingMobCandle() {
        return scintillatingMobCandle;
    }

    @Generated
    public static MobCandle getCoruscatingMobCandle() {
        return coruscatingMobCandle;
    }

    @Generated
    public static MysteriousTickerNoInteraction getMysteriousPottedPlant() {
        return mysteriousPottedPlant;
    }

    @Generated
    public static MysteriousTicker getMysteriousPlant() {
        return mysteriousPlant;
    }

    @Generated
    public static MysteriousTicker getMysteriousGlass() {
        return mysteriousGlass;
    }

    @Generated
    public static MysteriousTicker getMysteriousWool() {
        return mysteriousWool;
    }

    @Generated
    public static MysteriousTicker getMysteriousTerracotta() {
        return mysteriousTerracotta;
    }

    @Generated
    public static MysteriousTicker getMysteriousGlazedTerracotta() {
        return mysteriousGlazedTerracotta;
    }

    @Generated
    public static MysteriousTicker getMysteriousConcrete() {
        return mysteriousConcrete;
    }

    @Generated
    public static GreenHouseGlass getGreenHouseGlass() {
        return greenHouseGlass;
    }

    @Generated
    public static GreenHouseGlass getFocusedGreenHouseGlass() {
        return focusedGreenHouseGlass;
    }

    @Generated
    public static GreenHouseGlass getMagnifyingGreenHouseGlass() {
        return magnifyingGreenHouseGlass;
    }

    @Generated
    public static TrophyDisplay getTrophyDisplay() {
        return trophyDisplay;
    }

    @Generated
    public static ExaltationStand getExaltationStand() {
        return exaltationStand;
    }

    @Generated
    public static Waystone getWaystone() {
        return waystone;
    }

    @Generated
    public static AngelBlock getAngelBlock() {
        return angelBlock;
    }

    @Generated
    public static PhilosophersSpray getPhilosophersSpray() {
        return philosophersSpray;
    }

    @Generated
    public static GlassOfMilk getGlassOfMilk() {
        return glassOfMilk;
    }

    @Generated
    public static FragmentedVoid getFragmentedVoid() {
        return fragmentedVoid;
    }

    @Generated
    public static FragmentedVoid getShatteredVoid() {
        return shatteredVoid;
    }
}

