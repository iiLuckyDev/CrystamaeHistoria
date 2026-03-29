package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Runes {

    @Getter
    private static UnplaceableBlock runeBeast;
    @Getter
    private static UnplaceableBlock runeBeginning;
    @Getter
    private static UnplaceableBlock runeMoon;
    @Getter
    private static UnplaceableBlock runeGate;
    @Getter
    private static UnplaceableBlock runeTrueEarth;
    @Getter
    private static UnplaceableBlock runeChange;
    @Getter
    private static UnplaceableBlock runeNight;
    @Getter
    private static UnplaceableBlock runeBlack;
    @Getter
    private static UnplaceableBlock runeTrueHoly;
    @Getter
    private static UnplaceableBlock runeDragon;
    @Getter
    private static UnplaceableBlock runeTrueWater;
    @Getter
    private static UnplaceableBlock runeSovereign;
    @Getter
    private static UnplaceableBlock runeSun;
    @Getter
    private static UnplaceableBlock runeDawn;
    @Getter
    private static UnplaceableBlock runeTwilight;
    @Getter
    private static UnplaceableBlock runeTrueFire;
    @Getter
    private static UnplaceableBlock runeCircle;
    @Getter
    private static UnplaceableBlock runeBlinking;
    @Getter
    private static UnplaceableBlock runeSoul;
    @Getter
    private static UnplaceableBlock runePunishment;
    @Getter
    private static UnplaceableBlock runeTrueLightning;
    @Getter
    private static UnplaceableBlock runeEightfold;
    @Getter
    private static UnplaceableBlock runeCharm;
    @Getter
    private static UnplaceableBlock runeTrueWind;
    @Getter
    private static UnplaceableBlock runeBlackSword;
    @Getter
    private static UnplaceableBlock runeBrightShield;

    private static ItemStack item(SlimefunItemStack stack) {
        return stack.item();
    }

    public static void setup() {

        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Rune A
        runeBeast = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BEAST,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(SlimefunItems.INFERNAL_BONEMEAL), item(SlimefunItems.NECROTIC_SKULL), new ItemStack(Material.BONE),
                new ItemStack(Material.LEAD), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.LEAD),
                new ItemStack(Material.BONE), item(SlimefunItems.NECROTIC_SKULL), item(SlimefunItems.INFERNAL_BONEMEAL)
            }
        );

        // Rune B
        runeBeginning = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BEGINNING,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.GILDED_PEARL), new ItemStack(Material.SHULKER_SHELL), item(SlimefunItems.URANIUM),
                new ItemStack(Material.AZURE_BLUET), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.AZURE_BLUET),
                item(SlimefunItems.URANIUM), new ItemStack(Material.SHULKER_SHELL), item(CrystaStacks.GILDED_PEARL)
            }
        );

        // Rune C
        runeMoon = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_MOON,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.ANGEL_BLOCK), new ItemStack(Material.WITHER_ROSE), item(SlimefunItems.POWER_CRYSTAL),
                new ItemStack(Material.GRAY_DYE), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.GRAY_DYE),
                item(SlimefunItems.POWER_CRYSTAL), new ItemStack(Material.WITHER_ROSE), item(CrystaStacks.ANGEL_BLOCK)
            }
        );

        // Rune D
        runeGate = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_GATE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.STAVE_BASIC), new ItemStack(Material.BLAZE_ROD), item(SlimefunItems.GOLD_24K),
                new ItemStack(Material.CRIMSON_FENCE_GATE), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.CRIMSON_FENCE_GATE),
                item(SlimefunItems.GOLD_24K), new ItemStack(Material.BLAZE_ROD), item(CrystaStacks.STAVE_BASIC)
            }
        );

        // Rune E
        runeTrueEarth = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_EARTH,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.DREADFUL_DIRT), new ItemStack(Material.PODZOL), item(CrystaStacks.CURSED_EARTH),
                new ItemStack(Material.MOSS_BLOCK), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.MOSS_BLOCK),
                item(CrystaStacks.CURSED_EARTH), new ItemStack(Material.PODZOL), item(CrystaStacks.DREADFUL_DIRT)
            }
        );

        // Rune F
        runeChange = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_CHANGE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.ENDER_INHIBITOR_BASIC), new ItemStack(Material.LEVER), item(SlimefunItems.PROGRAMMABLE_ANDROID),
                new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.POLISHED_BLACKSTONE_BUTTON),
                item(SlimefunItems.PROGRAMMABLE_ANDROID), new ItemStack(Material.LEVER), item(CrystaStacks.ENDER_INHIBITOR_BASIC)
            }
        );

        // Rune G
        runeNight = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_NIGHT,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.DISPLACED_VOID), new ItemStack(Material.CLOCK), item(SlimefunItems.NEPTUNIUM),
                new ItemStack(Material.WEEPING_VINES), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.WEEPING_VINES),
                item(SlimefunItems.NEPTUNIUM), new ItemStack(Material.CLOCK), item(CrystaStacks.DISPLACED_VOID)
            }
        );

        // Rune H
        runeBlack = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BLACK,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.PAINT_BRUSH_BLACK_100), new ItemStack(Material.BLACK_CANDLE), item(SlimefunItems.NECROTIC_SKULL),
                new ItemStack(Material.BLACK_STAINED_GLASS_PANE), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.BLACK_STAINED_GLASS_PANE),
                item(SlimefunItems.NECROTIC_SKULL), new ItemStack(Material.BLACK_CANDLE), item(CrystaStacks.PAINT_BRUSH_BLACK_100)
            }
        );

        // Rune I
        runeTrueHoly = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_HOLY,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.SOUL_STAND), new ItemStack(Material.WHITE_TULIP), item(CrystaStacks.BODY_STAND),
                item(CrystaStacks.MIND_STAND), item(CrystaStacks.ARCANE_SIGIL), item(CrystaStacks.MIND_STAND),
                item(CrystaStacks.BODY_STAND), new ItemStack(Material.WHITE_TULIP), item(CrystaStacks.SOUL_STAND)
            }
        );

        // Rune J
        runeDragon = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_DRAGON,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.AMALGAMATE_DUST_RARE), new ItemStack(Material.DRAGON_HEAD), new ItemStack(Material.DRAGON_EGG),
                new ItemStack(Material.DRAGON_BREATH), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.DRAGON_BREATH),
                new ItemStack(Material.DRAGON_EGG), new ItemStack(Material.DRAGON_HEAD), item(CrystaStacks.AMALGAMATE_DUST_RARE)
            }
        );

        // Rune K
        runeTrueWater = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_WATER,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EXALTED_SEA_BREEZE), new ItemStack(Material.WATER_BUCKET), item(SlimefunItems.WATER_RUNE),
                new ItemStack(Material.NAUTILUS_SHELL), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.NAUTILUS_SHELL),
                item(SlimefunItems.WATER_RUNE), new ItemStack(Material.WATER_BUCKET), item(CrystaStacks.EXALTED_SEA_BREEZE)
            }
        );

        // Rune L
        runeSovereign = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_SOVEREIGN,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.CONNECTING_COMPASS), new ItemStack(Material.BELL), item(SlimefunItems.CARBONADO),
                new ItemStack(Material.OBSIDIAN), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.OBSIDIAN),
                item(SlimefunItems.CARBONADO), new ItemStack(Material.BELL), item(CrystaStacks.CONNECTING_COMPASS)
            }
        );

        // Rune M
        runeSun = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_SUN,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EXALTED_SUN), new ItemStack(Material.SHROOMLIGHT), item(SlimefunItems.BLISTERING_INGOT_3),
                new ItemStack(Material.GLOWSTONE), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.GLOWSTONE),
                item(SlimefunItems.BLISTERING_INGOT_3), new ItemStack(Material.SHROOMLIGHT), item(CrystaStacks.EXALTED_SUN)
            }
        );

        // Rune N
        runeDawn = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_DAWN,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EXALTED_DAWN), new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), item(SlimefunItems.APPLE_JUICE),
                new ItemStack(Material.ORANGE_TULIP), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.ORANGE_TULIP),
                item(SlimefunItems.APPLE_JUICE), new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), item(CrystaStacks.EXALTED_DAWN)
            }
        );

        // Rune O
        runeTwilight = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TWILIGHT,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EXALTED_DUSK), new ItemStack(Material.FIRE_CORAL_BLOCK), item(SlimefunItems.PUMPKIN_JUICE),
                new ItemStack(Material.CORNFLOWER), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.CORNFLOWER),
                item(SlimefunItems.PUMPKIN_JUICE), new ItemStack(Material.FIRE_CORAL_BLOCK), item(CrystaStacks.EXALTED_DUSK)
            }
        );

        // Rune P
        runeTrueFire = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_FIRE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EVISCERATING_PLATE), new ItemStack(Material.SOUL_CAMPFIRE), item(SlimefunItems.FIRE_RUNE),
                new ItemStack(Material.FIRE_CHARGE), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.FIRE_CHARGE),
                item(SlimefunItems.FIRE_RUNE), new ItemStack(Material.SOUL_CAMPFIRE), item(CrystaStacks.EVISCERATING_PLATE)
            }
        );

        // Rune Q
        runeCircle = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_CIRCLE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EXALTED_FERTILITY_PHARO), new ItemStack(Material.OBSERVER), item(SlimefunItems.ELECTRIC_INGOT_FACTORY_3),
                new ItemStack(Material.TARGET), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.TARGET),
                item(SlimefunItems.ELECTRIC_INGOT_FACTORY_3), new ItemStack(Material.OBSERVER), item(CrystaStacks.EXALTED_FERTILITY_PHARO)
            }
        );

        // Rune R
        runeBlinking = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BLINKING,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.WAYSTONE), new ItemStack(Material.ENDER_EYE), item(SlimefunItems.GPS_EMERGENCY_TRANSMITTER),
                new ItemStack(Material.ENDER_PEARL), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.ENDER_PEARL),
                item(SlimefunItems.GPS_EMERGENCY_TRANSMITTER), new ItemStack(Material.ENDER_EYE), item(CrystaStacks.WAYSTONE)
            }
        );

        // Rune S
        runeSoul = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_SOUL,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.SOUL_STAND), new ItemStack(Material.ELYTRA), item(SlimefunItems.SOULBOUND_RUNE),
                new ItemStack(Material.CAKE), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.CAKE),
                item(SlimefunItems.SOULBOUND_RUNE), new ItemStack(Material.ELYTRA), item(CrystaStacks.SOUL_STAND)
            }
        );

        // Rune T
        runePunishment = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_PUNISHMENT,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.MOB_CANDLE_DIM), new ItemStack(Material.LEAD), item(SlimefunItems.MONSTER_JERKY),
                new ItemStack(Material.SOUL_LANTERN), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.SOUL_LANTERN),
                item(SlimefunItems.MONSTER_JERKY), new ItemStack(Material.LEAD), item(CrystaStacks.MOB_CANDLE_DIM)
            }
        );

        // Rune U
        runeTrueLightning = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_LIGHTNING,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.LUMINESCENCE_SCOOP), new ItemStack(Material.LIGHTNING_ROD), item(SlimefunItems.LIGHTNING_RUNE),
                new ItemStack(Material.IRON_CHAIN), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.IRON_CHAIN),
                item(SlimefunItems.LIGHTNING_RUNE), new ItemStack(Material.LIGHTNING_ROD), item(CrystaStacks.LUMINESCENCE_SCOOP)
            }
        );

        // Rune V
        runeEightfold = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_EIGHTFOLD,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EPHEMERAL_WORKBENCH), new ItemStack(Material.SPONGE), item(SlimefunItems.ENCHANTMENT_RUNE),
                new ItemStack(Material.BASALT), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.BASALT),
                item(SlimefunItems.ENCHANTMENT_RUNE), new ItemStack(Material.SPONGE), item(CrystaStacks.EPHEMERAL_WORKBENCH)
            }
        );

        // Rune W
        runeCharm = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_CHARM,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.EXALTED_BEACON), new ItemStack(Material.RED_CANDLE), item(SlimefunItems.RAINBOW_RUNE),
                new ItemStack(Material.POPPY), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.POPPY),
                item(SlimefunItems.RAINBOW_RUNE), new ItemStack(Material.RED_CANDLE), item(CrystaStacks.EXALTED_BEACON)
            }
        );

        // Rune X
        runeTrueWind = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_TRUE_WIND,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.SPIRITUAL_SILKEN), new ItemStack(Material.END_ROD), item(SlimefunItems.AIR_RUNE),
                new ItemStack(Material.FEATHER), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.FEATHER),
                item(SlimefunItems.AIR_RUNE), new ItemStack(Material.END_ROD), item(CrystaStacks.SPIRITUAL_SILKEN)
            }
        );

        // Rune Y
        runeBlackSword = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BLACK_SWORD,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.INERT_PLATE), new ItemStack(Material.NETHERITE_SWORD), item(SlimefunItems.SWORD_OF_BEHEADING),
                new ItemStack(Material.GOLDEN_SWORD), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.GOLDEN_SWORD),
                item(SlimefunItems.SWORD_OF_BEHEADING), new ItemStack(Material.NETHERITE_SWORD), item(CrystaStacks.INERT_PLATE)
            }
        );

        // Rune Z
        runeBrightShield = new UnplaceableBlock(
            ItemGroups.RUNES,
            CrystaStacks.RUNE_BRIGHT_SHIELD,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                item(CrystaStacks.INERT_PLATE), new ItemStack(Material.TURTLE_HELMET), item(SlimefunItems.SOLAR_HELMET),
                new ItemStack(Material.SHIELD), item(CrystaStacks.ARCANE_SIGIL), new ItemStack(Material.SHIELD),
                item(SlimefunItems.SOLAR_HELMET), new ItemStack(Material.TURTLE_HELMET), item(CrystaStacks.INERT_PLATE)
            }
        );

        // Slimefun Registry
        runeBeast.register(plugin);
        runeBeginning.register(plugin);
        runeMoon.register(plugin);
        runeGate.register(plugin);
        runeTrueEarth.register(plugin);
        runeChange.register(plugin);
        runeNight.register(plugin);
        runeBlack.register(plugin);
        runeTrueHoly.register(plugin);
        runeDragon.register(plugin);
        runeTrueWater.register(plugin);
        runeSovereign.register(plugin);
        runeSun.register(plugin);
        runeDawn.register(plugin);
        runeTwilight.register(plugin);
        runeTrueFire.register(plugin);
        runeCircle.register(plugin);
        runeBlinking.register(plugin);
        runeSoul.register(plugin);
        runePunishment.register(plugin);
        runeTrueLightning.register(plugin);
        runeEightfold.register(plugin);
        runeCharm.register(plugin);
        runeTrueWind.register(plugin);
        runeBlackSword.register(plugin);
        runeBrightShield.register(plugin);

    }

}
