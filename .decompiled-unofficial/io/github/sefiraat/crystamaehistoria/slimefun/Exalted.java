/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.WeatherType
 *  org.bukkit.block.Biome
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedBeacon;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedFertilityPharo;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedHarvester;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedSeaBreeze;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedTime;
import io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted.ExaltedWeather;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class Exalted {
    private static ExaltedBeacon exaltedBeacon;
    private static ExaltedBeacon exaltedBaelfire;
    private static ExaltedFertilityPharo exaltedFertilityPharo;
    private static ExaltedFertilityPharo exaltedFertilityTotem;
    private static ExaltedHarvester exaltedHarvester;
    private static ExaltedHarvester exaltedAgronomist;
    private static ExaltedTime exaltedDawn;
    private static ExaltedTime exaltedDusk;
    private static ExaltedWeather exaltedSun;
    private static ExaltedWeather exaltedStorm;
    private static ExaltedSeaBreeze exaltedSeaBreeze;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        RecipeItem exaltedBeaconRecipe = new RecipeItem(CrystaStacks.AMALGAMATE_INGOT_MYTHICAL.item().clone(), StoryType.ELEMENTAL, 250, StoryType.HUMAN, 250, StoryType.PHILOSOPHICAL, 250, Exalted::isMaxStoryRank);
        exaltedBeacon = new ExaltedBeacon(ItemGroups.EXALTED, CrystaStacks.EXALTED_BEACON, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedBeaconRecipe.getDisplayRecipe(), 2);
        exaltedBaelfire = new ExaltedBeacon(ItemGroups.EXALTED, CrystaStacks.EXALTED_BAELFIRE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_BEAST.item().clone(), null, CrystaStacks.RUNE_TRUE_EARTH.item().clone(), CrystaStacks.EXALTED_BEACON.item().clone(), CrystaStacks.RUNE_TRUE_WATER.item().clone(), null, CrystaStacks.RUNE_BLINKING.item().clone(), null}, 3);
        RecipeItem exaltedFertilityPharoRecipe = new RecipeItem(CrystaStacks.AMALGAMATE_INGOT_MYTHICAL.item().clone(), StoryType.ELEMENTAL, 350, StoryType.ANIMAL, 350, StoryType.CELESTIAL, 350, Exalted::isMaxStoryRank);
        exaltedFertilityPharo = new ExaltedFertilityPharo(ItemGroups.EXALTED, CrystaStacks.EXALTED_FERTILITY_PHARO, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedFertilityPharoRecipe.getDisplayRecipe(), 9);
        exaltedFertilityTotem = new ExaltedFertilityPharo(ItemGroups.EXALTED, CrystaStacks.EXALTED_FERTILITY_TOTEM, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, new ItemStack[]{null, CrystaStacks.RUNE_MOON.item().clone(), null, CrystaStacks.RUNE_BLACK.item().clone(), CrystaStacks.EXALTED_FERTILITY_PHARO.item().clone(), CrystaStacks.RUNE_DAWN.item().clone(), null, CrystaStacks.RUNE_PUNISHMENT.item().clone(), null}, 13);
        RecipeItem exaltedHarvesterRecipe = new RecipeItem(CrystaStacks.AMALGAMATE_INGOT_MYTHICAL.item().clone(), StoryType.ELEMENTAL, 350, StoryType.HISTORICAL, 350, StoryType.VOID, 350, Exalted::isMaxSpellRank);
        exaltedHarvester = new ExaltedHarvester(ItemGroups.EXALTED, CrystaStacks.EXALTED_HARVESTER, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedHarvesterRecipe.getDisplayRecipe(), 4);
        exaltedAgronomist = new ExaltedHarvester(ItemGroups.EXALTED, CrystaStacks.EXALTED_AGRONOMIST, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, new ItemStack[]{null, CrystaStacks.RUNE_CHANGE.item().clone(), null, CrystaStacks.RUNE_DRAGON.item().clone(), CrystaStacks.EXALTED_HARVESTER.item().clone(), CrystaStacks.RUNE_TRUE_FIRE.item().clone(), null, CrystaStacks.RUNE_BLACK_SWORD.item().clone(), null}, 9);
        RecipeItem exaltedDawnRecipe = new RecipeItem(CrystaStacks.AMALGAMATE_DUST_MYTHICAL.item().clone(), StoryType.ELEMENTAL, 250, StoryType.HISTORICAL, 250, StoryType.CELESTIAL, 250, player -> player.getWorld().isDayTime());
        exaltedDawn = new ExaltedTime(ItemGroups.EXALTED, CrystaStacks.EXALTED_DAWN, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedDawnRecipe.getDisplayRecipe(), 6000);
        RecipeItem exaltedDuskRecipe = new RecipeItem(CrystaStacks.AMALGAMATE_DUST_MYTHICAL.item().clone(), StoryType.ELEMENTAL, 250, StoryType.HISTORICAL, 250, StoryType.VOID, 250, player -> !player.getWorld().isDayTime());
        exaltedDusk = new ExaltedTime(ItemGroups.EXALTED, CrystaStacks.EXALTED_DUSK, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedDuskRecipe.getDisplayRecipe(), 18000);
        RecipeItem exaltedSunRecipe = new RecipeItem(CrystaStacks.AMALGAMATE_DUST_MYTHICAL.item().clone(), StoryType.ELEMENTAL, 250, StoryType.ALCHEMICAL, 250, StoryType.CELESTIAL, 250, player -> player.getWorld().isClearWeather());
        exaltedSun = new ExaltedWeather(ItemGroups.EXALTED, CrystaStacks.EXALTED_SUN, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedSunRecipe.getDisplayRecipe(), WeatherType.CLEAR);
        RecipeItem exaltedStormRecipe = new RecipeItem(CrystaStacks.AMALGAMATE_DUST_MYTHICAL.item().clone(), StoryType.ELEMENTAL, 250, StoryType.ALCHEMICAL, 250, StoryType.VOID, 250, player -> !player.getWorld().isClearWeather());
        exaltedStorm = new ExaltedWeather(ItemGroups.EXALTED, CrystaStacks.EXALTED_STORM, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedStormRecipe.getDisplayRecipe(), WeatherType.DOWNFALL);
        RecipeItem exaltedSeeBreezeRecipe = new RecipeItem(new ItemStack(Material.HEART_OF_THE_SEA), StoryType.ELEMENTAL, 125, StoryType.ALCHEMICAL, 200, StoryType.CELESTIAL, 150, player -> player.getWorld().getBiome(player.getLocation()) == Biome.BEACH);
        exaltedSeaBreeze = new ExaltedSeaBreeze(ItemGroups.EXALTED, CrystaStacks.EXALTED_SEA_BREEZE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, exaltedSeeBreezeRecipe.getDisplayRecipe());
        exaltedBeacon.register(plugin);
        exaltedBaelfire.register(plugin);
        exaltedFertilityPharo.register(plugin);
        exaltedFertilityTotem.register(plugin);
        exaltedHarvester.register(plugin);
        exaltedAgronomist.register(plugin);
        exaltedDawn.register(plugin);
        exaltedDusk.register(plugin);
        exaltedSun.register(plugin);
        exaltedStorm.register(plugin);
        exaltedSeaBreeze.register(plugin);
        LiquefactionBasinCache.addCraftingRecipe(exaltedBeacon, exaltedBeaconRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedFertilityPharo, exaltedFertilityPharoRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedHarvester, exaltedHarvesterRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedDawn, exaltedDawnRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedDusk, exaltedDuskRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedSun, exaltedSunRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedStorm, exaltedStormRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltedSeaBreeze, exaltedSeeBreezeRecipe);
    }

    public static boolean isMaxStoryRank(Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    public static boolean isMaxSpellRank(Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }

    @Generated
    private Exalted() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static ExaltedBeacon getExaltedBeacon() {
        return exaltedBeacon;
    }

    @Generated
    public static ExaltedBeacon getExaltedBaelfire() {
        return exaltedBaelfire;
    }

    @Generated
    public static ExaltedFertilityPharo getExaltedFertilityPharo() {
        return exaltedFertilityPharo;
    }

    @Generated
    public static ExaltedFertilityPharo getExaltedFertilityTotem() {
        return exaltedFertilityTotem;
    }

    @Generated
    public static ExaltedHarvester getExaltedHarvester() {
        return exaltedHarvester;
    }

    @Generated
    public static ExaltedHarvester getExaltedAgronomist() {
        return exaltedAgronomist;
    }

    @Generated
    public static ExaltedTime getExaltedDawn() {
        return exaltedDawn;
    }

    @Generated
    public static ExaltedTime getExaltedDusk() {
        return exaltedDusk;
    }

    @Generated
    public static ExaltedWeather getExaltedSun() {
        return exaltedSun;
    }

    @Generated
    public static ExaltedWeather getExaltedStorm() {
        return exaltedStorm;
    }

    @Generated
    public static ExaltedSeaBreeze getExaltedSeaBreeze() {
        return exaltedSeaBreeze;
    }
}

