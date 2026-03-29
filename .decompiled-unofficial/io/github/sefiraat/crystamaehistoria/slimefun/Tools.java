/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks
 *  io.github.sefiraat.networks.slimefun.network.NetworkBridge
 *  io.github.sefiraat.networks.slimefun.network.NetworkMonitor
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
 *  io.github.thebusybiscuit.slimefun4.implementation.items.cargo.CargoConnectorNode
 *  io.github.thebusybiscuit.slimefun4.implementation.items.electric.EnergyConnector
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.SupportedPluginManager;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
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
import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.sefiraat.networks.slimefun.network.NetworkBridge;
import io.github.sefiraat.networks.slimefun.network.NetworkMonitor;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.cargo.CargoConnectorNode;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.EnergyConnector;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Tools {
    private static SlimefunItem inertPlate;
    private static SlimefunItem chargedPlate;
    private static Stave staveBasic;
    private static Stave staveAdvanced;
    private static Stave staveArcane;
    private static RefactingLens refractingLens;
    private static ThaumaturgicSalt thaumaturgicSalts;
    private static RecallingCrystaLattice crystaRecallLattice;
    private static EphemeralCraftingTable ephemeralCraftingTable;
    private static EphemeralWorkBench ephemeralWorkBench;
    private static LuminescenceScoop luminescenceScoop;
    private static LuminescenceScoop brillianceScoop;
    private static LuminescenceScoop lustreScoop;
    private static LuminescenceScoop radianceScoop;
    private static ConnectingCompass connectingCompass;
    private static SpiritualSilken spiritualSilken;
    private static SpiritualSilken incorporealSilken;
    private static Displacer simpleDisplacer;
    private static Displacer arcaneDisplacer;
    private static BlockVeil cargoCover;
    private static BlockVeil energyNetCover;
    private static BlockVeil networkNodeCover;
    private static BalmySponge balmySponge;
    private static BalmySponge searingSponge;
    private static BalmySponge superMassiveSponge;
    private static SleepingBag sleepingBag;
    private static DisplacedVoid displacedVoid;
    private static CrystamageSatchel apprenticesSatchel;
    private static CrystamageSatchel crystamagesSatchel;
    private static CrystamageSatchel wizardsSatchel;
    private static CrystamageSatchel conjurersSatchel;
    private static CrystamageSatchel sorcerersSatchel;
    private static CrystamageSatchel grandmastersSatchel;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        RecipeItem inertPlateRecipe = new RecipeItem(SlimefunItems.REINFORCED_PLATE.item().clone(), StoryType.ELEMENTAL, 10, StoryType.HUMAN, 10, StoryType.PHILOSOPHICAL, 10);
        inertPlate = new BlankPlate(ItemGroups.TOOLS, CrystaStacks.INERT_PLATE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, inertPlateRecipe.getDisplayRecipe(), 1);
        chargedPlate = new ChargedPlate(ItemGroups.TOOLS, CrystaStacks.CHARGED_PLATE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null}, 1);
        staveBasic = new Stave(ItemGroups.TOOLS, CrystaStacks.STAVE_BASIC, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, null, CrystaStacks.AMALGAMATE_INGOT_UNIQUE.item().clone(), null, new ItemStack(Material.STICK), null, new ItemStack(Material.STICK), null, null}, 1);
        staveAdvanced = new Stave(ItemGroups.TOOLS, CrystaStacks.STAVE_ADVANCED, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), CrystaStacks.STAVE_BASIC.item().clone(), CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone()}, 2);
        staveArcane = new Stave(ItemGroups.TOOLS, CrystaStacks.STAVE_ARCANE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CrystaStacks.RUNE_BEGINNING.item().clone(), CrystaStacks.RUNE_CHANGE.item().clone(), CrystaStacks.RUNE_DRAGON.item().clone(), CrystaStacks.RUNE_SUN.item().clone(), CrystaStacks.STAVE_ADVANCED.item().clone(), CrystaStacks.RUNE_CIRCLE.item().clone(), CrystaStacks.RUNE_TRUE_LIGHTNING.item().clone(), CrystaStacks.RUNE_CHARM.item().clone(), CrystaStacks.RUNE_BRIGHT_SHIELD.item().clone()}, 3);
        refractingLens = new RefactingLens(ItemGroups.TOOLS, CrystaStacks.REFRACTING_LENS, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.IMBUED_GLASS.item().clone(), null, null, new ItemStack(Material.SPYGLASS), null, null, CrystaStacks.AMALGAMATE_INGOT_COMMON.item().clone(), null});
        thaumaturgicSalts = new ThaumaturgicSalt(ItemGroups.TOOLS, CrystaStacks.THAUMATURGIC_SALTS, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone(), CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone(), CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone(), CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone(), SlimefunItems.SALT.item().clone(), CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone(), CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone(), CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone(), CrystaStacks.AMALGAMATE_DUST_COMMON.item().clone()});
        crystaRecallLattice = new RecallingCrystaLattice(ItemGroups.TOOLS, CrystaStacks.CRYSTA_RECALL_LATTICE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{new ItemStack(Material.AMETHYST_SHARD), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), new ItemStack(Material.AMETHYST_SHARD), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), new ItemStack(Material.NETHER_STAR), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), new ItemStack(Material.AMETHYST_SHARD), CrystaStacks.AMALGAMATE_INGOT_EPIC.item().clone(), new ItemStack(Material.AMETHYST_SHARD)});
        RecipeItem ephemeralCraftingTableRecipe = new RecipeItem(new ItemStack(Material.CRAFTING_TABLE), StoryType.HUMAN, 50, StoryType.HISTORICAL, 25, StoryType.PHILOSOPHICAL, 50);
        ephemeralCraftingTable = new EphemeralCraftingTable(ItemGroups.TOOLS, CrystaStacks.EPHEMERAL_CRAFT_TABLE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, ephemeralCraftingTableRecipe.getDisplayRecipe());
        RecipeItem ephemeralWorkBenchRecipe = new RecipeItem(CrystaStacks.EPHEMERAL_CRAFT_TABLE.item().clone(), StoryType.HUMAN, 250, StoryType.HISTORICAL, 100, StoryType.PHILOSOPHICAL, 250);
        ephemeralWorkBench = new EphemeralWorkBench(ItemGroups.TOOLS, CrystaStacks.EPHEMERAL_WORKBENCH, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, ephemeralWorkBenchRecipe.getDisplayRecipe());
        RecipeItem luminescenceScoopRecipe = new RecipeItem(new ItemStack(Material.LANTERN), StoryType.CELESTIAL, 70, StoryType.ALCHEMICAL, 20, StoryType.HUMAN, 15);
        luminescenceScoop = new LuminescenceScoop(ItemGroups.TOOLS, CrystaStacks.LUMINESCENCE_SCOOP, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, luminescenceScoopRecipe.getDisplayRecipe(), 25);
        RecipeItem brillianceScoopRecipe = new RecipeItem(CrystaStacks.LUMINESCENCE_SCOOP.item().clone(), StoryType.CELESTIAL, 140, StoryType.ALCHEMICAL, 40, StoryType.HUMAN, 30);
        brillianceScoop = new LuminescenceScoop(ItemGroups.TOOLS, CrystaStacks.BRILLIANCE_SCOOP, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, brillianceScoopRecipe.getDisplayRecipe(), 75);
        RecipeItem lustreScoopRecipe = new RecipeItem(CrystaStacks.BRILLIANCE_SCOOP.item().clone(), StoryType.CELESTIAL, 280, StoryType.ALCHEMICAL, 80, StoryType.HUMAN, 60);
        lustreScoop = new LuminescenceScoop(ItemGroups.TOOLS, CrystaStacks.LUSTRE_SCOOP, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, lustreScoopRecipe.getDisplayRecipe(), 250);
        radianceScoop = new LuminescenceScoop(ItemGroups.TOOLS, CrystaStacks.RADIANCE_SCOOP, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_TRUE_EARTH.item().clone(), null, CrystaStacks.RUNE_TRUE_WATER.item().clone(), CrystaStacks.LUSTRE_SCOOP.item().clone(), CrystaStacks.RUNE_TWILIGHT.item().clone(), null, CrystaStacks.RUNE_CHARM.item().clone(), null}, 500, true);
        RecipeItem connectingCompassRecipe = new RecipeItem(new ItemStack(Material.COMPASS), StoryType.MECHANICAL, 5, StoryType.HISTORICAL, 10, StoryType.HUMAN, 5);
        connectingCompass = new ConnectingCompass(ItemGroups.TOOLS, CrystaStacks.CONNECTING_COMPASS, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, connectingCompassRecipe.getDisplayRecipe());
        RecipeItem spiritualSilkenRecipe = new RecipeItem(new ItemStack(Material.BONE), StoryType.MECHANICAL, 250, StoryType.HUMAN, 250, StoryType.CELESTIAL, 250);
        spiritualSilken = new SpiritualSilken(ItemGroups.TOOLS, CrystaStacks.SPIRITUAL_SILKEN, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, spiritualSilkenRecipe.getDisplayRecipe(), 50);
        incorporealSilken = new SpiritualSilken(ItemGroups.TOOLS, CrystaStacks.INCORPOREAL_SILKEN, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_MOON.item().clone(), null, CrystaStacks.RUNE_TRUE_HOLY.item().clone(), CrystaStacks.SPIRITUAL_SILKEN.item().clone(), CrystaStacks.RUNE_BLINKING.item().clone(), null, CrystaStacks.RUNE_TRUE_LIGHTNING.item().clone(), null}, 1000);
        RecipeItem simpleDisplacerRecipe = new RecipeItem(CrystaStacks.POWDERED_ESSENCE.item().clone(), StoryType.ALCHEMICAL, 120, StoryType.ANIMAL, 70, StoryType.HUMAN, 60);
        simpleDisplacer = new Displacer(ItemGroups.TOOLS, CrystaStacks.SIMPLE_DISPLACER, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, simpleDisplacerRecipe.getDisplayRecipe(), 50);
        RecipeItem arcaneDisplacerRecipe = new RecipeItem(CrystaStacks.SIMPLE_DISPLACER.item().clone(), StoryType.ALCHEMICAL, 240, StoryType.ANIMAL, 140, StoryType.HUMAN, 120);
        arcaneDisplacer = new Displacer(ItemGroups.TOOLS, CrystaStacks.ARCANE_DISPLACER, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, arcaneDisplacerRecipe.getDisplayRecipe(), 500);
        RecipeItem balmySpongeRecipe = new RecipeItem(new ItemStack(Material.SPONGE), StoryType.ELEMENTAL, 45, StoryType.ALCHEMICAL, 30, StoryType.VOID, 25);
        balmySponge = new BalmySponge(ItemGroups.TOOLS, CrystaStacks.SPONGE_BALMY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, balmySpongeRecipe.getDisplayRecipe(), 4);
        RecipeItem searingSpongeRecipe = new RecipeItem(CrystaStacks.SPONGE_BALMY.item().clone(), StoryType.ELEMENTAL, 90, StoryType.ALCHEMICAL, 60, StoryType.VOID, 50);
        searingSponge = new BalmySponge(ItemGroups.TOOLS, CrystaStacks.SPONGE_SEARING, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, searingSpongeRecipe.getDisplayRecipe(), 7);
        superMassiveSponge = new BalmySponge(ItemGroups.TOOLS, CrystaStacks.SPONGE_SUPER_MASSIVE, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.RUNE_CHANGE.item().clone(), null, CrystaStacks.RUNE_SUN.item().clone(), CrystaStacks.SPONGE_SEARING.item().clone(), CrystaStacks.RUNE_CIRCLE.item().clone(), null, CrystaStacks.RUNE_EIGHTFOLD.item().clone(), null}, 10);
        RecipeItem sleepingBagRecipe = new RecipeItem(new ItemStack(Material.WHITE_BED), StoryType.MECHANICAL, 75, StoryType.HISTORICAL, 100, StoryType.HUMAN, 100);
        sleepingBag = new SleepingBag(ItemGroups.TOOLS, CrystaStacks.SLEEPING_BAG, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, sleepingBagRecipe.getDisplayRecipe());
        displacedVoid = new DisplacedVoid(ItemGroups.TOOLS, CrystaStacks.DISPLACED_VOID, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{null, CrystaStacks.ARCANE_DISPLACER.item().clone(), null, null, CrystaStacks.SHATTERED_VOID.item().clone(), null, null, null, null});
        RecipeItem apprenticesSatchelRecipe = new RecipeItem(new ItemStack(Material.TRAPPED_CHEST), StoryType.ELEMENTAL, 25, StoryType.HUMAN, 25, StoryType.PHILOSOPHICAL, 25);
        apprenticesSatchel = new CrystamageSatchel(ItemGroups.TOOLS, CrystaStacks.SATCHEL_1, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, apprenticesSatchelRecipe.getDisplayRecipe(), 1);
        RecipeItem crystamagesSatchelRecipe = new RecipeItem(CrystaStacks.SATCHEL_1.item().clone(), StoryType.ALCHEMICAL, 35, StoryType.ANIMAL, 35, StoryType.VOID, 35);
        crystamagesSatchel = new CrystamageSatchel(ItemGroups.TOOLS, CrystaStacks.SATCHEL_2, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, crystamagesSatchelRecipe.getDisplayRecipe(), 2);
        RecipeItem wizardsSatchelRecipe = new RecipeItem(CrystaStacks.SATCHEL_2.item().clone(), StoryType.MECHANICAL, 45, StoryType.HISTORICAL, 45, StoryType.CELESTIAL, 45);
        wizardsSatchel = new CrystamageSatchel(ItemGroups.TOOLS, CrystaStacks.SATCHEL_3, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, wizardsSatchelRecipe.getDisplayRecipe(), 3);
        RecipeItem conjurersSatchelRecipe = new RecipeItem(CrystaStacks.SATCHEL_3.item().clone(), StoryType.ELEMENTAL, 55, StoryType.HUMAN, 55, StoryType.PHILOSOPHICAL, 55);
        conjurersSatchel = new CrystamageSatchel(ItemGroups.TOOLS, CrystaStacks.SATCHEL_4, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, conjurersSatchelRecipe.getDisplayRecipe(), 4);
        RecipeItem sorcerersSatchelRecipe = new RecipeItem(CrystaStacks.SATCHEL_4.item().clone(), StoryType.ALCHEMICAL, 65, StoryType.ANIMAL, 65, StoryType.VOID, 65);
        sorcerersSatchel = new CrystamageSatchel(ItemGroups.TOOLS, CrystaStacks.SATCHEL_5, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, sorcerersSatchelRecipe.getDisplayRecipe(), 5);
        RecipeItem grandmastersSatchelRecipe = new RecipeItem(CrystaStacks.SATCHEL_5.item().clone(), StoryType.MECHANICAL, 75, StoryType.HISTORICAL, 75, StoryType.CELESTIAL, 75);
        grandmastersSatchel = new CrystamageSatchel(ItemGroups.TOOLS, CrystaStacks.SATCHEL_6, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, grandmastersSatchelRecipe.getDisplayRecipe(), 6);
        chargedPlate.register((SlimefunAddon)CrystamaeHistoria.getInstance());
        inertPlate.register((SlimefunAddon)CrystamaeHistoria.getInstance());
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
        LiquefactionBasinCache.addCraftingRecipe(inertPlate, inertPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(ephemeralCraftingTable, ephemeralCraftingTableRecipe);
        LiquefactionBasinCache.addCraftingRecipe(ephemeralWorkBench, ephemeralWorkBenchRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)luminescenceScoop, luminescenceScoopRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)brillianceScoop, brillianceScoopRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)lustreScoop, lustreScoopRecipe);
        LiquefactionBasinCache.addCraftingRecipe(connectingCompass, connectingCompassRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)spiritualSilken, spiritualSilkenRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)simpleDisplacer, simpleDisplacerRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)arcaneDisplacer, arcaneDisplacerRecipe);
        LiquefactionBasinCache.addCraftingRecipe(balmySponge, balmySpongeRecipe);
        LiquefactionBasinCache.addCraftingRecipe(searingSponge, searingSpongeRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)sleepingBag, sleepingBagRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)apprenticesSatchel, apprenticesSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)crystamagesSatchel, crystamagesSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)wizardsSatchel, wizardsSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)conjurersSatchel, conjurersSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)sorcerersSatchel, sorcerersSatchelRecipe);
        LiquefactionBasinCache.addCraftingRecipe((SlimefunItem)grandmastersSatchel, grandmastersSatchelRecipe);
        if (!SupportedPluginManager.isHeadLimiter()) {
            RecipeItem cargoCoverRecipe = new RecipeItem(SlimefunItems.CARGO_INPUT_NODE.item().clone(), StoryType.MECHANICAL, 10, StoryType.HUMAN, 10, StoryType.VOID, 10);
            cargoCover = new BlockVeil(ItemGroups.TOOLS, CrystaStacks.CARGO_COVER, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, cargoCoverRecipe.getDisplayRecipe(), CrystaStacks.CARGO_COVER.asQuantity(8), CargoConnectorNode.class);
            RecipeItem energyNetCoverRecipe = new RecipeItem(SlimefunItems.ENERGY_CONNECTOR.item().clone(), StoryType.MECHANICAL, 10, StoryType.HUMAN, 10, StoryType.VOID, 10);
            energyNetCover = new BlockVeil(ItemGroups.TOOLS, CrystaStacks.ENERGY_NET_COVER, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, energyNetCoverRecipe.getDisplayRecipe(), CrystaStacks.ENERGY_NET_COVER.asQuantity(8), EnergyConnector.class);
            cargoCover.register(plugin);
            energyNetCover.register(plugin);
            LiquefactionBasinCache.addCraftingRecipe(cargoCover, cargoCoverRecipe);
            LiquefactionBasinCache.addCraftingRecipe(energyNetCover, energyNetCoverRecipe);
        }
        if (SupportedPluginManager.isNetworks()) {
            RecipeItem networksCoverRecipe = new RecipeItem(NetworksSlimefunItemStacks.NETWORK_BRIDGE.item().clone(), StoryType.MECHANICAL, 10, StoryType.HUMAN, 10, StoryType.VOID, 10);
            networkNodeCover = new BlockVeil(ItemGroups.TOOLS, CrystaStacks.NETWORKS_COVER, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, networksCoverRecipe.getDisplayRecipe(), CrystaStacks.NETWORKS_COVER.asQuantity(8), NetworkBridge.class, NetworkMonitor.class);
            networkNodeCover.register(plugin);
            LiquefactionBasinCache.addCraftingRecipe(networkNodeCover, networksCoverRecipe);
        }
    }

    @Generated
    private Tools() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static SlimefunItem getInertPlate() {
        return inertPlate;
    }

    @Generated
    public static SlimefunItem getChargedPlate() {
        return chargedPlate;
    }

    @Generated
    public static Stave getStaveBasic() {
        return staveBasic;
    }

    @Generated
    public static Stave getStaveAdvanced() {
        return staveAdvanced;
    }

    @Generated
    public static Stave getStaveArcane() {
        return staveArcane;
    }

    @Generated
    public static RefactingLens getRefractingLens() {
        return refractingLens;
    }

    @Generated
    public static ThaumaturgicSalt getThaumaturgicSalts() {
        return thaumaturgicSalts;
    }

    @Generated
    public static RecallingCrystaLattice getCrystaRecallLattice() {
        return crystaRecallLattice;
    }

    @Generated
    public static EphemeralCraftingTable getEphemeralCraftingTable() {
        return ephemeralCraftingTable;
    }

    @Generated
    public static EphemeralWorkBench getEphemeralWorkBench() {
        return ephemeralWorkBench;
    }

    @Generated
    public static LuminescenceScoop getLuminescenceScoop() {
        return luminescenceScoop;
    }

    @Generated
    public static LuminescenceScoop getBrillianceScoop() {
        return brillianceScoop;
    }

    @Generated
    public static LuminescenceScoop getLustreScoop() {
        return lustreScoop;
    }

    @Generated
    public static LuminescenceScoop getRadianceScoop() {
        return radianceScoop;
    }

    @Generated
    public static ConnectingCompass getConnectingCompass() {
        return connectingCompass;
    }

    @Generated
    public static SpiritualSilken getSpiritualSilken() {
        return spiritualSilken;
    }

    @Generated
    public static SpiritualSilken getIncorporealSilken() {
        return incorporealSilken;
    }

    @Generated
    public static Displacer getSimpleDisplacer() {
        return simpleDisplacer;
    }

    @Generated
    public static Displacer getArcaneDisplacer() {
        return arcaneDisplacer;
    }

    @Generated
    public static BlockVeil getCargoCover() {
        return cargoCover;
    }

    @Generated
    public static BlockVeil getEnergyNetCover() {
        return energyNetCover;
    }

    @Generated
    public static BlockVeil getNetworkNodeCover() {
        return networkNodeCover;
    }

    @Generated
    public static BalmySponge getBalmySponge() {
        return balmySponge;
    }

    @Generated
    public static BalmySponge getSearingSponge() {
        return searingSponge;
    }

    @Generated
    public static BalmySponge getSuperMassiveSponge() {
        return superMassiveSponge;
    }

    @Generated
    public static SleepingBag getSleepingBag() {
        return sleepingBag;
    }

    @Generated
    public static DisplacedVoid getDisplacedVoid() {
        return displacedVoid;
    }

    @Generated
    public static CrystamageSatchel getApprenticesSatchel() {
        return apprenticesSatchel;
    }

    @Generated
    public static CrystamageSatchel getCrystamagesSatchel() {
        return crystamagesSatchel;
    }

    @Generated
    public static CrystamageSatchel getWizardsSatchel() {
        return wizardsSatchel;
    }

    @Generated
    public static CrystamageSatchel getConjurersSatchel() {
        return conjurersSatchel;
    }

    @Generated
    public static CrystamageSatchel getSorcerersSatchel() {
        return sorcerersSatchel;
    }

    @Generated
    public static CrystamageSatchel getGrandmastersSatchel() {
        return grandmastersSatchel;
    }
}

