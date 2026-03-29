/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
 *  io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Runes {
    private static UnplaceableBlock runeBeast;
    private static UnplaceableBlock runeBeginning;
    private static UnplaceableBlock runeMoon;
    private static UnplaceableBlock runeGate;
    private static UnplaceableBlock runeTrueEarth;
    private static UnplaceableBlock runeChange;
    private static UnplaceableBlock runeNight;
    private static UnplaceableBlock runeBlack;
    private static UnplaceableBlock runeTrueHoly;
    private static UnplaceableBlock runeDragon;
    private static UnplaceableBlock runeTrueWater;
    private static UnplaceableBlock runeSovereign;
    private static UnplaceableBlock runeSun;
    private static UnplaceableBlock runeDawn;
    private static UnplaceableBlock runeTwilight;
    private static UnplaceableBlock runeTrueFire;
    private static UnplaceableBlock runeCircle;
    private static UnplaceableBlock runeBlinking;
    private static UnplaceableBlock runeSoul;
    private static UnplaceableBlock runePunishment;
    private static UnplaceableBlock runeTrueLightning;
    private static UnplaceableBlock runeEightfold;
    private static UnplaceableBlock runeCharm;
    private static UnplaceableBlock runeTrueWind;
    private static UnplaceableBlock runeBlackSword;
    private static UnplaceableBlock runeBrightShield;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        runeBeast = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_BEAST, RecipeType.ANCIENT_ALTAR, new ItemStack[]{SlimefunItems.INFERNAL_BONEMEAL.item().clone(), SlimefunItems.NECROTIC_SKULL.item().clone(), new ItemStack(Material.BONE), new ItemStack(Material.LEAD), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.LEAD), new ItemStack(Material.BONE), SlimefunItems.NECROTIC_SKULL.item().clone(), SlimefunItems.INFERNAL_BONEMEAL.item().clone()});
        runeBeginning = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_BEGINNING, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.GILDED_PEARL.item().clone(), new ItemStack(Material.SHULKER_SHELL), SlimefunItems.URANIUM.item().clone(), new ItemStack(Material.AZURE_BLUET), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.AZURE_BLUET), SlimefunItems.URANIUM.item().clone(), new ItemStack(Material.SHULKER_SHELL), CrystaStacks.GILDED_PEARL.item().clone()});
        runeMoon = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_MOON, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.ANGEL_BLOCK.item().clone(), new ItemStack(Material.WITHER_ROSE), SlimefunItems.POWER_CRYSTAL.item().clone(), new ItemStack(Material.GRAY_DYE), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.GRAY_DYE), SlimefunItems.POWER_CRYSTAL.item().clone(), new ItemStack(Material.WITHER_ROSE), CrystaStacks.ANGEL_BLOCK.item().clone()});
        runeGate = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_GATE, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.STAVE_BASIC.item().clone(), new ItemStack(Material.BLAZE_ROD), SlimefunItems.GOLD_24K.item().clone(), new ItemStack(Material.CRIMSON_FENCE_GATE), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.CRIMSON_FENCE_GATE), SlimefunItems.GOLD_24K.item().clone(), new ItemStack(Material.BLAZE_ROD), CrystaStacks.STAVE_BASIC.item().clone()});
        runeTrueEarth = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_TRUE_EARTH, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.DREADFUL_DIRT.item().clone(), new ItemStack(Material.PODZOL), CrystaStacks.CURSED_EARTH.item().clone(), new ItemStack(Material.MOSS_BLOCK), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.MOSS_BLOCK), CrystaStacks.CURSED_EARTH.item().clone(), new ItemStack(Material.PODZOL), CrystaStacks.DREADFUL_DIRT.item().clone()});
        runeChange = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_CHANGE, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.ENDER_INHIBITOR_BASIC.item().clone(), new ItemStack(Material.LEVER), SlimefunItems.PROGRAMMABLE_ANDROID.item().clone(), new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON), SlimefunItems.PROGRAMMABLE_ANDROID.item().clone(), new ItemStack(Material.LEVER), CrystaStacks.ENDER_INHIBITOR_BASIC.item().clone()});
        runeNight = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_NIGHT, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.DISPLACED_VOID.item().clone(), new ItemStack(Material.CLOCK), SlimefunItems.NEPTUNIUM.item().clone(), new ItemStack(Material.WEEPING_VINES), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.WEEPING_VINES), SlimefunItems.NEPTUNIUM.item().clone(), new ItemStack(Material.CLOCK), CrystaStacks.DISPLACED_VOID.item().clone()});
        runeBlack = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_BLACK, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.PAINT_BRUSH_BLACK_100.item().clone(), new ItemStack(Material.BLACK_CANDLE), SlimefunItems.NECROTIC_SKULL.item().clone(), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.BLACK_STAINED_GLASS_PANE), SlimefunItems.NECROTIC_SKULL.item().clone(), new ItemStack(Material.BLACK_CANDLE), CrystaStacks.PAINT_BRUSH_BLACK_100.item().clone()});
        runeTrueHoly = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_TRUE_HOLY, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.SOUL_STAND.item().clone(), new ItemStack(Material.WHITE_TULIP), CrystaStacks.BODY_STAND.item().clone(), CrystaStacks.MIND_STAND.item().clone(), CrystaStacks.ARCANE_SIGIL.item().clone(), CrystaStacks.MIND_STAND.item().clone(), CrystaStacks.BODY_STAND.item().clone(), new ItemStack(Material.WHITE_TULIP), CrystaStacks.SOUL_STAND.item().clone()});
        runeDragon = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_DRAGON, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_RARE.item().clone(), new ItemStack(Material.DRAGON_HEAD), new ItemStack(Material.DRAGON_EGG), new ItemStack(Material.DRAGON_BREATH), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.DRAGON_EGG), new ItemStack(Material.DRAGON_HEAD), CrystaStacks.AMALGAMATE_DUST_RARE.item().clone()});
        runeTrueWater = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_TRUE_WATER, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EXALTED_SEA_BREEZE.item().clone(), new ItemStack(Material.WATER_BUCKET), SlimefunItems.WATER_RUNE.item().clone(), new ItemStack(Material.NAUTILUS_SHELL), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.NAUTILUS_SHELL), SlimefunItems.WATER_RUNE.item().clone(), new ItemStack(Material.WATER_BUCKET), CrystaStacks.EXALTED_SEA_BREEZE.item().clone()});
        runeSovereign = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_SOVEREIGN, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.CONNECTING_COMPASS.item().clone(), new ItemStack(Material.BELL), SlimefunItems.CARBONADO.item().clone(), new ItemStack(Material.OBSIDIAN), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.OBSIDIAN), SlimefunItems.CARBONADO.item().clone(), new ItemStack(Material.BELL), CrystaStacks.CONNECTING_COMPASS.item().clone()});
        runeSun = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_SUN, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EXALTED_SUN.item().clone(), new ItemStack(Material.SHROOMLIGHT), SlimefunItems.BLISTERING_INGOT_3.item().clone(), new ItemStack(Material.GLOWSTONE), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.GLOWSTONE), SlimefunItems.BLISTERING_INGOT_3.item().clone(), new ItemStack(Material.SHROOMLIGHT), CrystaStacks.EXALTED_SUN.item().clone()});
        runeDawn = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_DAWN, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EXALTED_DAWN.item().clone(), new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), SlimefunItems.APPLE_JUICE.item().clone(), new ItemStack(Material.ORANGE_TULIP), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.ORANGE_TULIP), SlimefunItems.APPLE_JUICE.item().clone(), new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), CrystaStacks.EXALTED_DAWN.item().clone()});
        runeTwilight = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_TWILIGHT, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EXALTED_DUSK.item().clone(), new ItemStack(Material.FIRE_CORAL_BLOCK), SlimefunItems.PUMPKIN_JUICE.item().clone(), new ItemStack(Material.CORNFLOWER), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.CORNFLOWER), SlimefunItems.PUMPKIN_JUICE.item().clone(), new ItemStack(Material.FIRE_CORAL_BLOCK), CrystaStacks.EXALTED_DUSK.item().clone()});
        runeTrueFire = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_TRUE_FIRE, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EVISCERATING_PLATE.item().clone(), new ItemStack(Material.SOUL_CAMPFIRE), SlimefunItems.FIRE_RUNE.item().clone(), new ItemStack(Material.FIRE_CHARGE), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.FIRE_CHARGE), SlimefunItems.FIRE_RUNE.item().clone(), new ItemStack(Material.SOUL_CAMPFIRE), CrystaStacks.EVISCERATING_PLATE.item().clone()});
        runeCircle = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_CIRCLE, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EXALTED_FERTILITY_PHARO.item().clone(), new ItemStack(Material.OBSERVER), SlimefunItems.ELECTRIC_INGOT_FACTORY_3.item().clone(), new ItemStack(Material.TARGET), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.TARGET), SlimefunItems.ELECTRIC_INGOT_FACTORY_3.item().clone(), new ItemStack(Material.OBSERVER), CrystaStacks.EXALTED_FERTILITY_PHARO.item().clone()});
        runeBlinking = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_BLINKING, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.WAYSTONE.item().clone(), new ItemStack(Material.ENDER_EYE), SlimefunItems.GPS_EMERGENCY_TRANSMITTER.item().clone(), new ItemStack(Material.ENDER_PEARL), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.ENDER_PEARL), SlimefunItems.GPS_EMERGENCY_TRANSMITTER.item().clone(), new ItemStack(Material.ENDER_EYE), CrystaStacks.WAYSTONE.item().clone()});
        runeSoul = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_SOUL, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.SOUL_STAND.item().clone(), new ItemStack(Material.ELYTRA), SlimefunItems.SOULBOUND_RUNE.item().clone(), new ItemStack(Material.CAKE), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.CAKE), SlimefunItems.SOULBOUND_RUNE.item().clone(), new ItemStack(Material.ELYTRA), CrystaStacks.SOUL_STAND.item().clone()});
        runePunishment = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_PUNISHMENT, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.MOB_CANDLE_DIM.item().clone(), new ItemStack(Material.LEAD), SlimefunItems.MONSTER_JERKY.item().clone(), new ItemStack(Material.SOUL_LANTERN), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.SOUL_LANTERN), SlimefunItems.MONSTER_JERKY.item().clone(), new ItemStack(Material.LEAD), CrystaStacks.MOB_CANDLE_DIM.item().clone()});
        runeTrueLightning = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_TRUE_LIGHTNING, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.LUMINESCENCE_SCOOP.item().clone(), new ItemStack(Material.LIGHTNING_ROD), SlimefunItems.LIGHTNING_RUNE.item().clone(), new ItemStack(Material.CHAIN), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.CHAIN), SlimefunItems.LIGHTNING_RUNE.item().clone(), new ItemStack(Material.LIGHTNING_ROD), CrystaStacks.LUMINESCENCE_SCOOP.item().clone()});
        runeEightfold = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_EIGHTFOLD, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EPHEMERAL_WORKBENCH.item().clone(), new ItemStack(Material.SPONGE), SlimefunItems.ENCHANTMENT_RUNE.item().clone(), new ItemStack(Material.BASALT), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.BASALT), SlimefunItems.ENCHANTMENT_RUNE.item().clone(), new ItemStack(Material.SPONGE), CrystaStacks.EPHEMERAL_WORKBENCH.item().clone()});
        runeCharm = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_CHARM, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.EXALTED_BEACON.item().clone(), new ItemStack(Material.RED_CANDLE), SlimefunItems.RAINBOW_RUNE.item().clone(), new ItemStack(Material.POPPY), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.POPPY), SlimefunItems.RAINBOW_RUNE.item().clone(), new ItemStack(Material.RED_CANDLE), CrystaStacks.EXALTED_BEACON.item().clone()});
        runeTrueWind = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_TRUE_WIND, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.SPIRITUAL_SILKEN.item().clone(), new ItemStack(Material.END_ROD), SlimefunItems.AIR_RUNE.item().clone(), new ItemStack(Material.FEATHER), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.FEATHER), SlimefunItems.AIR_RUNE.item().clone(), new ItemStack(Material.END_ROD), CrystaStacks.SPIRITUAL_SILKEN.item().clone()});
        runeBlackSword = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_BLACK_SWORD, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.INERT_PLATE.item().clone(), new ItemStack(Material.NETHERITE_SWORD), SlimefunItems.SWORD_OF_BEHEADING.item().clone(), new ItemStack(Material.GOLDEN_SWORD), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.GOLDEN_SWORD), SlimefunItems.SWORD_OF_BEHEADING.item().clone(), new ItemStack(Material.NETHERITE_SWORD), CrystaStacks.INERT_PLATE.item().clone()});
        runeBrightShield = new UnplaceableBlock((ItemGroup)ItemGroups.RUNES, CrystaStacks.RUNE_BRIGHT_SHIELD, RecipeType.ANCIENT_ALTAR, new ItemStack[]{CrystaStacks.INERT_PLATE.item().clone(), new ItemStack(Material.TURTLE_HELMET), SlimefunItems.SOLAR_HELMET.item().clone(), new ItemStack(Material.SHIELD), CrystaStacks.ARCANE_SIGIL.item().clone(), new ItemStack(Material.SHIELD), SlimefunItems.SOLAR_HELMET.item().clone(), new ItemStack(Material.TURTLE_HELMET), CrystaStacks.INERT_PLATE.item().clone()});
        runeBeast.register((SlimefunAddon)plugin);
        runeBeginning.register((SlimefunAddon)plugin);
        runeMoon.register((SlimefunAddon)plugin);
        runeGate.register((SlimefunAddon)plugin);
        runeTrueEarth.register((SlimefunAddon)plugin);
        runeChange.register((SlimefunAddon)plugin);
        runeNight.register((SlimefunAddon)plugin);
        runeBlack.register((SlimefunAddon)plugin);
        runeTrueHoly.register((SlimefunAddon)plugin);
        runeDragon.register((SlimefunAddon)plugin);
        runeTrueWater.register((SlimefunAddon)plugin);
        runeSovereign.register((SlimefunAddon)plugin);
        runeSun.register((SlimefunAddon)plugin);
        runeDawn.register((SlimefunAddon)plugin);
        runeTwilight.register((SlimefunAddon)plugin);
        runeTrueFire.register((SlimefunAddon)plugin);
        runeCircle.register((SlimefunAddon)plugin);
        runeBlinking.register((SlimefunAddon)plugin);
        runeSoul.register((SlimefunAddon)plugin);
        runePunishment.register((SlimefunAddon)plugin);
        runeTrueLightning.register((SlimefunAddon)plugin);
        runeEightfold.register((SlimefunAddon)plugin);
        runeCharm.register((SlimefunAddon)plugin);
        runeTrueWind.register((SlimefunAddon)plugin);
        runeBlackSword.register((SlimefunAddon)plugin);
        runeBrightShield.register((SlimefunAddon)plugin);
    }

    @Generated
    private Runes() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static UnplaceableBlock getRuneBeast() {
        return runeBeast;
    }

    @Generated
    public static UnplaceableBlock getRuneBeginning() {
        return runeBeginning;
    }

    @Generated
    public static UnplaceableBlock getRuneMoon() {
        return runeMoon;
    }

    @Generated
    public static UnplaceableBlock getRuneGate() {
        return runeGate;
    }

    @Generated
    public static UnplaceableBlock getRuneTrueEarth() {
        return runeTrueEarth;
    }

    @Generated
    public static UnplaceableBlock getRuneChange() {
        return runeChange;
    }

    @Generated
    public static UnplaceableBlock getRuneNight() {
        return runeNight;
    }

    @Generated
    public static UnplaceableBlock getRuneBlack() {
        return runeBlack;
    }

    @Generated
    public static UnplaceableBlock getRuneTrueHoly() {
        return runeTrueHoly;
    }

    @Generated
    public static UnplaceableBlock getRuneDragon() {
        return runeDragon;
    }

    @Generated
    public static UnplaceableBlock getRuneTrueWater() {
        return runeTrueWater;
    }

    @Generated
    public static UnplaceableBlock getRuneSovereign() {
        return runeSovereign;
    }

    @Generated
    public static UnplaceableBlock getRuneSun() {
        return runeSun;
    }

    @Generated
    public static UnplaceableBlock getRuneDawn() {
        return runeDawn;
    }

    @Generated
    public static UnplaceableBlock getRuneTwilight() {
        return runeTwilight;
    }

    @Generated
    public static UnplaceableBlock getRuneTrueFire() {
        return runeTrueFire;
    }

    @Generated
    public static UnplaceableBlock getRuneCircle() {
        return runeCircle;
    }

    @Generated
    public static UnplaceableBlock getRuneBlinking() {
        return runeBlinking;
    }

    @Generated
    public static UnplaceableBlock getRuneSoul() {
        return runeSoul;
    }

    @Generated
    public static UnplaceableBlock getRunePunishment() {
        return runePunishment;
    }

    @Generated
    public static UnplaceableBlock getRuneTrueLightning() {
        return runeTrueLightning;
    }

    @Generated
    public static UnplaceableBlock getRuneEightfold() {
        return runeEightfold;
    }

    @Generated
    public static UnplaceableBlock getRuneCharm() {
        return runeCharm;
    }

    @Generated
    public static UnplaceableBlock getRuneTrueWind() {
        return runeTrueWind;
    }

    @Generated
    public static UnplaceableBlock getRuneBlackSword() {
        return runeBlackSword;
    }

    @Generated
    public static UnplaceableBlock getRuneBrightShield() {
        return runeBrightShield;
    }
}

