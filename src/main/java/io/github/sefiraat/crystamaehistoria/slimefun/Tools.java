package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.SupportedPluginManager;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.BalmySponge;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.ConnectingCompass;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.DisplacedVoid;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.Displacer;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.LuminescenceScoop;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.RecallingCrystaLattice;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.SleepingBag;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.SpiritualSilken;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.ThaumaturgicSalt;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.covers.BlockVeil;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.crafting.EphemeralCraftingTable;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.crafting.EphemeralWorkBench;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.satchel.CrystamageSatchel;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.cargo.CargoConnectorNode;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.EnergyConnector;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Tools {

    private static ItemStack item(SlimefunItemStack stack) {
        return stack.item();
    }

    @Getter
    private static SlimefunItem inertPlate;
    @Getter
    private static SlimefunItem chargedPlate;
    @Getter
    private static Stave staveBasic;
    @Getter
    private static Stave staveAdvanced;
    @Getter
    private static Stave staveArcane;
    @Getter
    private static RefactingLens refractingLens;
    @Getter
    private static ThaumaturgicSalt thaumaturgicSalts;
    @Getter
    private static RecallingCrystaLattice crystaRecallLattice;
    @Getter
    private static EphemeralCraftingTable ephemeralCraftingTable;
    @Getter
    private static EphemeralWorkBench ephemeralWorkBench;
    @Getter
    private static LuminescenceScoop luminescenceScoop;
    @Getter
    private static LuminescenceScoop brillianceScoop;
    @Getter
    private static LuminescenceScoop lustreScoop;
    @Getter
    private static LuminescenceScoop radianceScoop;
    @Getter
    private static ConnectingCompass connectingCompass;
    @Getter
    private static SpiritualSilken spiritualSilken;
    @Getter
    private static SpiritualSilken incorporealSilken;
    @Getter
    private static Displacer simpleDisplacer;
    @Getter
    private static Displacer arcaneDisplacer;
    @Getter
    private static BlockVeil cargoCover;
    @Getter
    private static BlockVeil energyNetCover;
    @Getter
    private static BlockVeil networkNodeCover;
    @Getter
    private static BalmySponge balmySponge;
    @Getter
    private static BalmySponge searingSponge;
    @Getter
    private static BalmySponge superMassiveSponge;
    @Getter
    private static SleepingBag sleepingBag;
    @Getter
    private static DisplacedVoid displacedVoid;
    @Getter
    private static CrystamageSatchel apprenticesSatchel;
    @Getter
    private static CrystamageSatchel crystamagesSatchel;
    @Getter
    private static CrystamageSatchel wizardsSatchel;
    @Getter
    private static CrystamageSatchel conjurersSatchel;
    @Getter
    private static CrystamageSatchel sorcerersSatchel;
    @Getter
    private static CrystamageSatchel grandmastersSatchel;

    public static void setup() {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Inert Plate
        RecipeItem inertPlateRecipe = new RecipeItem(
            item(SlimefunItems.REINFORCED_PLATE),
            StoryType.ELEMENTAL, 10,
            StoryType.HUMAN, 10,
            StoryType.PHILOSOPHICAL, 10
        );
        inertPlate = new BlankPlate(
            ItemGroups.TOOLS,
            CrystaStacks.INERT_PLATE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            inertPlateRecipe.getDisplayRecipe(),
            1
        );

        // Charged Plate
        chargedPlate = new ChargedPlate(
            ItemGroups.TOOLS,
            CrystaStacks.CHARGED_PLATE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
            1
        );

        // Basic Stave
        staveBasic = new Stave(
            ItemGroups.TOOLS,
            CrystaStacks.STAVE_BASIC,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, null, item(CrystaStacks.AMALGAMATE_INGOT_UNIQUE),
                null, new ItemStack(Material.STICK), null,
                new ItemStack(Material.STICK), null, null
            },
            1
        );

        // Advanced Stave
        staveAdvanced = new Stave(
            ItemGroups.TOOLS,
            CrystaStacks.STAVE_ADVANCED,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                item(CrystaStacks.AMALGAMATE_INGOT_COMMON), item(CrystaStacks.AMALGAMATE_INGOT_COMMON), item(CrystaStacks.AMALGAMATE_INGOT_COMMON),
                item(CrystaStacks.AMALGAMATE_INGOT_COMMON), item(CrystaStacks.STAVE_BASIC), item(CrystaStacks.AMALGAMATE_INGOT_COMMON),
                item(CrystaStacks.AMALGAMATE_INGOT_COMMON), item(CrystaStacks.AMALGAMATE_INGOT_COMMON), item(CrystaStacks.AMALGAMATE_INGOT_COMMON)
            },
            2
        );

        // Arcane Stave
        staveArcane = new Stave(
            ItemGroups.TOOLS,
            CrystaStacks.STAVE_ARCANE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                item(CrystaStacks.RUNE_BEGINNING), item(CrystaStacks.RUNE_CHANGE), item(CrystaStacks.RUNE_DRAGON),
                item(CrystaStacks.RUNE_SUN), item(CrystaStacks.STAVE_ADVANCED), item(CrystaStacks.RUNE_CIRCLE),
                item(CrystaStacks.RUNE_TRUE_LIGHTNING), item(CrystaStacks.RUNE_CHARM), item(CrystaStacks.RUNE_BRIGHT_SHIELD)
            },
            3
        );

        // Refracting Lens
        refractingLens = new RefactingLens(
            ItemGroups.TOOLS,
            CrystaStacks.REFRACTING_LENS,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, item(CrystaStacks.IMBUED_GLASS), null,
                null, new ItemStack(Material.SPYGLASS), null,
                null, item(CrystaStacks.AMALGAMATE_INGOT_COMMON), null
            }
        );

        // Thaumaturgic Salt
        thaumaturgicSalts = new ThaumaturgicSalt(
            ItemGroups.TOOLS,
            CrystaStacks.THAUMATURGIC_SALTS,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                item(CrystaStacks.AMALGAMATE_DUST_COMMON), item(CrystaStacks.AMALGAMATE_DUST_COMMON), item(CrystaStacks.AMALGAMATE_DUST_COMMON),
                item(CrystaStacks.AMALGAMATE_DUST_COMMON), item(SlimefunItems.SALT), item(CrystaStacks.AMALGAMATE_DUST_COMMON),
                item(CrystaStacks.AMALGAMATE_DUST_COMMON), item(CrystaStacks.AMALGAMATE_DUST_COMMON), item(CrystaStacks.AMALGAMATE_DUST_COMMON)
            }
        );

        // Recall Lattice
        crystaRecallLattice = new RecallingCrystaLattice(
            ItemGroups.TOOLS,
            CrystaStacks.CRYSTA_RECALL_LATTICE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                new ItemStack(Material.AMETHYST_SHARD), item(CrystaStacks.AMALGAMATE_INGOT_EPIC), new ItemStack(Material.AMETHYST_SHARD),
                item(CrystaStacks.AMALGAMATE_INGOT_EPIC), new ItemStack(Material.NETHER_STAR), item(CrystaStacks.AMALGAMATE_INGOT_EPIC),
                new ItemStack(Material.AMETHYST_SHARD), item(CrystaStacks.AMALGAMATE_INGOT_EPIC), new ItemStack(Material.AMETHYST_SHARD)
            }
        );

        // Ephemeral Crafting Table
        RecipeItem ephemeralCraftingTableRecipe = new RecipeItem(
            new ItemStack(Material.CRAFTING_TABLE),
            StoryType.HUMAN, 50,
            StoryType.HISTORICAL, 25,
            StoryType.PHILOSOPHICAL, 50
        );
        ephemeralCraftingTable = new EphemeralCraftingTable(
            ItemGroups.TOOLS,
            CrystaStacks.EPHEMERAL_CRAFT_TABLE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            ephemeralCraftingTableRecipe.getDisplayRecipe()
        );

        // Ephemeral Workbench
        RecipeItem ephemeralWorkBenchRecipe = new RecipeItem(
            item(CrystaStacks.EPHEMERAL_CRAFT_TABLE),
            StoryType.HUMAN, 250,
            StoryType.HISTORICAL, 100,
            StoryType.PHILOSOPHICAL, 250
        );
        ephemeralWorkBench = new EphemeralWorkBench(
            ItemGroups.TOOLS,
            CrystaStacks.EPHEMERAL_WORKBENCH,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            ephemeralWorkBenchRecipe.getDisplayRecipe()
        );

        // Luminesence Scoop
        RecipeItem luminescenceScoopRecipe = new RecipeItem(
            new ItemStack(Material.LANTERN),
            StoryType.CELESTIAL, 70,
            StoryType.ALCHEMICAL, 20,
            StoryType.HUMAN, 15
        );
        luminescenceScoop = new LuminescenceScoop(
            ItemGroups.TOOLS,
            CrystaStacks.LUMINESCENCE_SCOOP,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            luminescenceScoopRecipe.getDisplayRecipe(),
            25
        );

        // Brilliance Scoop
        RecipeItem brillianceScoopRecipe = new RecipeItem(
            item(CrystaStacks.LUMINESCENCE_SCOOP),
            StoryType.CELESTIAL, 140,
            StoryType.ALCHEMICAL, 40,
            StoryType.HUMAN, 30
        );
        brillianceScoop = new LuminescenceScoop(
            ItemGroups.TOOLS,
            CrystaStacks.BRILLIANCE_SCOOP,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            brillianceScoopRecipe.getDisplayRecipe(),
            75
        );

        // Lustre Scoop
        RecipeItem lustreScoopRecipe = new RecipeItem(
            item(CrystaStacks.BRILLIANCE_SCOOP),
            StoryType.CELESTIAL, 280,
            StoryType.ALCHEMICAL, 80,
            StoryType.HUMAN, 60
        );
        lustreScoop = new LuminescenceScoop(
            ItemGroups.TOOLS,
            CrystaStacks.LUSTRE_SCOOP,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            lustreScoopRecipe.getDisplayRecipe(),
            250
        );

        // Radiance Scoop
        radianceScoop = new LuminescenceScoop(
            ItemGroups.TOOLS,
            CrystaStacks.RADIANCE_SCOOP,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                null, item(CrystaStacks.RUNE_TRUE_EARTH), null,
                item(CrystaStacks.RUNE_TRUE_WATER), item(CrystaStacks.LUSTRE_SCOOP), item(CrystaStacks.RUNE_TWILIGHT),
                null, item(CrystaStacks.RUNE_CHARM), null
            },
            500,
            true
        );

        // Connecting Compass
        RecipeItem connectingCompassRecipe = new RecipeItem(
            new ItemStack(Material.COMPASS),
            StoryType.MECHANICAL, 5,
            StoryType.HISTORICAL, 10,
            StoryType.HUMAN, 5
        );
        connectingCompass = new ConnectingCompass(
            ItemGroups.TOOLS,
            CrystaStacks.CONNECTING_COMPASS,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            connectingCompassRecipe.getDisplayRecipe()
        );

        // Spiritual Silken
        RecipeItem spiritualSilkenRecipe = new RecipeItem(
            new ItemStack(Material.BONE),
            StoryType.MECHANICAL, 250,
            StoryType.HUMAN, 250,
            StoryType.CELESTIAL, 250
        );
        spiritualSilken = new SpiritualSilken(
            ItemGroups.TOOLS,
            CrystaStacks.SPIRITUAL_SILKEN,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            spiritualSilkenRecipe.getDisplayRecipe(),
            50
        );

        // Incorporeal Silken
        incorporealSilken = new SpiritualSilken(
            ItemGroups.TOOLS,
            CrystaStacks.INCORPOREAL_SILKEN,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                null, item(CrystaStacks.RUNE_MOON), null,
                item(CrystaStacks.RUNE_TRUE_HOLY), item(CrystaStacks.SPIRITUAL_SILKEN), item(CrystaStacks.RUNE_BLINKING),
                null, item(CrystaStacks.RUNE_TRUE_LIGHTNING), null
            },
            1000
        );

        // Simple Displacer
        RecipeItem simpleDisplacerRecipe = new RecipeItem(
            item(CrystaStacks.POWDERED_ESSENCE),
            StoryType.ALCHEMICAL, 120,
            StoryType.ANIMAL, 70,
            StoryType.HUMAN, 60
        );
        simpleDisplacer = new Displacer(
            ItemGroups.TOOLS,
            CrystaStacks.SIMPLE_DISPLACER,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            simpleDisplacerRecipe.getDisplayRecipe(),
            50
        );

        // Arcane Displacer
        RecipeItem arcaneDisplacerRecipe = new RecipeItem(
            item(CrystaStacks.SIMPLE_DISPLACER),
            StoryType.ALCHEMICAL, 240,
            StoryType.ANIMAL, 140,
            StoryType.HUMAN, 120
        );
        arcaneDisplacer = new Displacer(
            ItemGroups.TOOLS,
            CrystaStacks.ARCANE_DISPLACER,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            arcaneDisplacerRecipe.getDisplayRecipe(),
            500
        );

        // Balmy Sponge
        RecipeItem balmySpongeRecipe = new RecipeItem(
            new ItemStack(Material.SPONGE),
            StoryType.ELEMENTAL, 45,
            StoryType.ALCHEMICAL, 30,
            StoryType.VOID, 25
        );
        balmySponge = new BalmySponge(
            ItemGroups.TOOLS,
            CrystaStacks.SPONGE_BALMY,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            balmySpongeRecipe.getDisplayRecipe(),
            4
        );

        // Searing Sponge
        RecipeItem searingSpongeRecipe = new RecipeItem(
            item(CrystaStacks.SPONGE_BALMY),
            StoryType.ELEMENTAL, 90,
            StoryType.ALCHEMICAL, 60,
            StoryType.VOID, 50
        );
        searingSponge = new BalmySponge(
            ItemGroups.TOOLS,
            CrystaStacks.SPONGE_SEARING,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            searingSpongeRecipe.getDisplayRecipe(),
            7
        );

        // Super-massive Sponge
        superMassiveSponge = new BalmySponge(
            ItemGroups.TOOLS,
            CrystaStacks.SPONGE_SUPER_MASSIVE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                null, item(CrystaStacks.RUNE_CHANGE), null,
                item(CrystaStacks.RUNE_SUN), item(CrystaStacks.SPONGE_SEARING), item(CrystaStacks.RUNE_CIRCLE),
                null, item(CrystaStacks.RUNE_EIGHTFOLD), null
            },
            10
        );

        // Sleeping Bag
        RecipeItem sleepingBagRecipe = new RecipeItem(
            new ItemStack(Material.WHITE_BED),
            StoryType.MECHANICAL, 75,
            StoryType.HISTORICAL, 100,
            StoryType.HUMAN, 100
        );
        sleepingBag = new SleepingBag(
            ItemGroups.TOOLS,
            CrystaStacks.SLEEPING_BAG,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            sleepingBagRecipe.getDisplayRecipe()
        );

        // Displaced Void
        displacedVoid = new DisplacedVoid(
            ItemGroups.TOOLS,
            CrystaStacks.DISPLACED_VOID,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, item(CrystaStacks.ARCANE_DISPLACER), null,
                null, item(CrystaStacks.SHATTERED_VOID), null,
                null, null, null
            }
        );

        // Apprentices Satchel
        RecipeItem apprenticesSatchelRecipe = new RecipeItem(
            new ItemStack(Material.TRAPPED_CHEST),
            StoryType.ELEMENTAL, 25,
            StoryType.HUMAN, 25,
            StoryType.PHILOSOPHICAL, 25
        );
        apprenticesSatchel = new CrystamageSatchel(
            ItemGroups.TOOLS,
            CrystaStacks.SATCHEL_1,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            apprenticesSatchelRecipe.getDisplayRecipe(),
            1
        );

        // Crystamages Satchel
        RecipeItem crystamagesSatchelRecipe = new RecipeItem(
            item(CrystaStacks.SATCHEL_1),
            StoryType.ALCHEMICAL, 35,
            StoryType.ANIMAL, 35,
            StoryType.VOID, 35
        );
        crystamagesSatchel = new CrystamageSatchel(
            ItemGroups.TOOLS,
            CrystaStacks.SATCHEL_2,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            crystamagesSatchelRecipe.getDisplayRecipe(),
            2
        );

        // Wizards Satchel
        RecipeItem wizardsSatchelRecipe = new RecipeItem(
            item(CrystaStacks.SATCHEL_2),
            StoryType.MECHANICAL, 45,
            StoryType.HISTORICAL, 45,
            StoryType.CELESTIAL, 45
        );
        wizardsSatchel = new CrystamageSatchel(
            ItemGroups.TOOLS,
            CrystaStacks.SATCHEL_3,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            wizardsSatchelRecipe.getDisplayRecipe(),
            3
        );

        // Conjurers Satchel
        RecipeItem conjurersSatchelRecipe = new RecipeItem(
            item(CrystaStacks.SATCHEL_3),
            StoryType.ELEMENTAL, 55,
            StoryType.HUMAN, 55,
            StoryType.PHILOSOPHICAL, 55
        );
        conjurersSatchel = new CrystamageSatchel(
            ItemGroups.TOOLS,
            CrystaStacks.SATCHEL_4,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            conjurersSatchelRecipe.getDisplayRecipe(),
            4
        );

        // Sorcerers Satchel
        RecipeItem sorcerersSatchelRecipe = new RecipeItem(
            item(CrystaStacks.SATCHEL_4),
            StoryType.ALCHEMICAL, 65,
            StoryType.ANIMAL, 65,
            StoryType.VOID, 65
        );
        sorcerersSatchel = new CrystamageSatchel(
            ItemGroups.TOOLS,
            CrystaStacks.SATCHEL_5,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            sorcerersSatchelRecipe.getDisplayRecipe(),
            5
        );

        // Grandmasters Satchel
        RecipeItem grandmastersSatchelRecipe = new RecipeItem(
            item(CrystaStacks.SATCHEL_5),
            StoryType.MECHANICAL, 75,
            StoryType.HISTORICAL, 75,
            StoryType.CELESTIAL, 75
        );
        grandmastersSatchel = new CrystamageSatchel(
            ItemGroups.TOOLS,
            CrystaStacks.SATCHEL_6,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            grandmastersSatchelRecipe.getDisplayRecipe(),
            6
        );

        // Slimefun Registry
        chargedPlate.register(CrystamaeHistoria.getInstance());
        inertPlate.register(CrystamaeHistoria.getInstance());
        staveBasic.register(plugin);
        staveAdvanced.register(plugin);
        staveArcane.register(plugin);
        refractingLens.register(plugin);
        thaumaturgicSalts.register(plugin);
        crystaRecallLattice.register(plugin);
        ephemeralCraftingTable.register(plugin);
        ephemeralWorkBench.register(plugin);
        luminescenceScoop.register(plugin);
        brillianceScoop.register(plugin);
        lustreScoop.register(plugin);
        radianceScoop.register(plugin);
        connectingCompass.register(plugin);
        spiritualSilken.register(plugin);
        incorporealSilken.register(plugin);
        simpleDisplacer.register(plugin);
        arcaneDisplacer.register(plugin);
        balmySponge.register(plugin);
        searingSponge.register(plugin);
        superMassiveSponge.register(plugin);
        sleepingBag.register(plugin);
        displacedVoid.register(plugin);
        apprenticesSatchel.register(plugin);
        crystamagesSatchel.register(plugin);
        wizardsSatchel.register(plugin);
        conjurersSatchel.register(plugin);
        sorcerersSatchel.register(plugin);
        grandmastersSatchel.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(inertPlate, inertPlateRecipe);

        LiquefactionBasinCache.addCraftingRecipe(ephemeralCraftingTable, ephemeralCraftingTableRecipe);
        LiquefactionBasinCache.addCraftingRecipe(ephemeralWorkBench, ephemeralWorkBenchRecipe);

        LiquefactionBasinCache.addCraftingRecipe(luminescenceScoop, luminescenceScoopRecipe);
        LiquefactionBasinCache.addCraftingRecipe(brillianceScoop, brillianceScoopRecipe);
        LiquefactionBasinCache.addCraftingRecipe(lustreScoop, lustreScoopRecipe);

        LiquefactionBasinCache.addCraftingRecipe(connectingCompass, connectingCompassRecipe);

        LiquefactionBasinCache.addCraftingRecipe(spiritualSilken, spiritualSilkenRecipe);

        LiquefactionBasinCache.addCraftingRecipe(simpleDisplacer, simpleDisplacerRecipe);
        LiquefactionBasinCache.addCraftingRecipe(arcaneDisplacer, arcaneDisplacerRecipe);

        LiquefactionBasinCache.addCraftingRecipe(balmySponge, balmySpongeRecipe);
        LiquefactionBasinCache.addCraftingRecipe(searingSponge, searingSpongeRecipe);

        LiquefactionBasinCache.addCraftingRecipe(sleepingBag, sleepingBagRecipe);

        LiquefactionBasinCache.addCraftingRecipe(apprenticesSatchel, apprenticesSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe(crystamagesSatchel, crystamagesSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe(wizardsSatchel, wizardsSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe(conjurersSatchel, conjurersSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe(sorcerersSatchel, sorcerersSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe(grandmastersSatchel, grandmastersSatchelRecipe);

        /*
        Covers 'hide' items from HL - until the tile entity check
        is swapped out to extend to all SlimefunItems we don't want
        to allow the items to exist here.
         */
        if (!SupportedPluginManager.isHeadLimiter()) {
            // Cargo Cover
            RecipeItem cargoCoverRecipe = new RecipeItem(
                item(SlimefunItems.CARGO_INPUT_NODE),
                StoryType.MECHANICAL, 10,
                StoryType.HUMAN, 10,
                StoryType.VOID, 10
            );
            cargoCover = new BlockVeil(
                ItemGroups.TOOLS,
                CrystaStacks.CARGO_COVER,
                CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
                cargoCoverRecipe.getDisplayRecipe(),
                CrystaStacks.CARGO_COVER.asQuantity(8),
                CargoConnectorNode.class
            );

            // Energy Net Cover
            RecipeItem energyNetCoverRecipe = new RecipeItem(
                item(SlimefunItems.ENERGY_CONNECTOR),
                StoryType.MECHANICAL, 10,
                StoryType.HUMAN, 10,
                StoryType.VOID, 10
            );
            energyNetCover = new BlockVeil(
                ItemGroups.TOOLS,
                CrystaStacks.ENERGY_NET_COVER,
                CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
                energyNetCoverRecipe.getDisplayRecipe(),
                CrystaStacks.ENERGY_NET_COVER.asQuantity(8),
                EnergyConnector.class
            );


            cargoCover.register(plugin);
            energyNetCover.register(plugin);

            LiquefactionBasinCache.addCraftingRecipe(cargoCover, cargoCoverRecipe);
            LiquefactionBasinCache.addCraftingRecipe(energyNetCover, energyNetCoverRecipe);
        }

        if (SupportedPluginManager.isNetworks()) {
            final ItemStack networksBridge = resolveNetworksBridgeItem();
            final Class<? extends SlimefunItem>[] networksCoverTypes = resolveNetworksCoverTypes();

            if (networksBridge != null && networksCoverTypes.length == 2) {
                // Networks Cover
                RecipeItem networksCoverRecipe = new RecipeItem(
                    networksBridge,
                    StoryType.MECHANICAL, 10,
                    StoryType.HUMAN, 10,
                    StoryType.VOID, 10
                );
                networkNodeCover = new BlockVeil(
                    ItemGroups.TOOLS,
                    CrystaStacks.NETWORKS_COVER,
                    CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
                    networksCoverRecipe.getDisplayRecipe(),
                    CrystaStacks.NETWORKS_COVER.asQuantity(8),
                    networksCoverTypes
                );

                networkNodeCover.register(plugin);

                LiquefactionBasinCache.addCraftingRecipe(networkNodeCover, networksCoverRecipe);
            } else {
                plugin.getLogger().warning("Networks was detected but its API could not be resolved, skipping Networks cover registration.");
            }
        }
    }

    private static ItemStack resolveNetworksBridgeItem() {
        try {
            Class<?> stacksClass = Class.forName("io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks");
            Object stack = stacksClass.getField("NETWORK_BRIDGE").get(null);
            if (stack instanceof ItemStack itemStack) {
                return itemStack.clone();
            }
        } catch (ReflectiveOperationException ignored) {
            // Handled by the caller with a single warning.
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends SlimefunItem>[] resolveNetworksCoverTypes() {
        try {
            Class<?> bridgeClass = Class.forName("io.github.sefiraat.networks.slimefun.network.NetworkBridge");
            Class<?> monitorClass = Class.forName("io.github.sefiraat.networks.slimefun.network.NetworkMonitor");
            if (SlimefunItem.class.isAssignableFrom(bridgeClass) && SlimefunItem.class.isAssignableFrom(monitorClass)) {
                return new Class[]{
                    (Class<? extends SlimefunItem>) bridgeClass,
                    (Class<? extends SlimefunItem>) monitorClass
                };
            }
        } catch (ReflectiveOperationException ignored) {
            // Handled by the caller with a single warning.
        }

        return new Class[0];
    }
}
