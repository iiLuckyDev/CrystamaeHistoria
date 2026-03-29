package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UtilityClass
public class Researches {

    private static final int RESEARCH_ID_BASE = 760_100;

    public static void setup() {
        registerResearch("crystalline_foundations", 0, "Crystalline Foundations", 8, crystallineFoundations());
        registerResearch("refined_crystamae", 1, "Refined Crystamae", 24, refinedCrystamae());
        registerResearch("arcane_condensation", 2, "Arcane Condensation", 72, arcaneCondensation());

        registerResearch("basic_mechanisms", 3, "Basic Mechanisms", 12, basicMechanisms());
        registerResearch("advanced_mechanisms", 4, "Advanced Mechanisms", 32, advancedMechanisms());
        registerResearch("prismatic_mechanisms", 5, "Prismatic Mechanisms", 64, prismaticMechanisms());

        registerResearch("apprentice_spellcraft", 6, "Apprentice Spellcraft", 16, apprenticeSpellcraft());
        registerResearch("arcane_utility", 7, "Arcane Utility", 40, arcaneUtility());
        registerResearch("archmage_implements", 8, "Archmage Implements", 80, archmageImplements());

        registerResearch("thaumic_husbandry", 9, "Thaumic Husbandry", 20, thaumicHusbandry());
        registerResearch("mystic_utilities", 10, "Mystic Utilities", 52, mysticUtilities());

        registerResearch("chromatic_arts", 11, "Chromatic Arts", 56, chromaticArts());
        registerResearch("hallmarks_of_mastery", 12, "Hallmarks of Mastery", 96, hallmarksOfMastery());
        registerResearch("exalted_studies", 13, "Exalted Studies", 128, exaltedStudies());
        registerResearch("runic_mastery", 14, "Runic Mastery", 160, runicMastery());
    }

    private static void registerResearch(String key, int offset, String name, int cost, List<SlimefunItem> items) {
        Research research = new Research(
            new NamespacedKey(CrystamaeHistoria.getInstance(), key),
            RESEARCH_ID_BASE + offset,
            name,
            cost
        );

        research.addItems(items.toArray(new SlimefunItem[0]));
        research.register();
    }

    private static List<SlimefunItem> crystallineFoundations() {
        List<SlimefunItem> items = crystals(StoryRarity.COMMON, StoryRarity.UNCOMMON);
        add(
            items,
            Materials.getBlankCrystal(),
            Materials.getPolychromaticCrystal(),
            Materials.getKaleidoscopicCrystal(),
            Materials.getMotleyCrystal(),
            Materials.getAmalgamateDustCommon(),
            Materials.getAmalgamateIngotCommon(),
            Materials.getAmalgamateDustUncommon(),
            Materials.getAmalgamateIngotUncommon(),
            Materials.getImbuedGlass(),
            Materials.getUncannyPearl(),
            Materials.getBasicFibres(),
            Materials.getPowderedEssence(),
            Materials.getMagicalMilk()
        );
        return items;
    }

    private static List<SlimefunItem> refinedCrystamae() {
        List<SlimefunItem> items = crystals(StoryRarity.RARE, StoryRarity.EPIC);
        add(
            items,
            Materials.getPrismaticCrystal(),
            Materials.getAmalgamateDustRare(),
            Materials.getAmalgamateIngotRare(),
            Materials.getAmalgamateDustEpic(),
            Materials.getAmalgamateIngotEpic()
        );
        return items;
    }

    private static List<SlimefunItem> arcaneCondensation() {
        List<SlimefunItem> items = crystals(StoryRarity.MYTHICAL, StoryRarity.UNIQUE);
        add(
            items,
            Materials.getAmalgamateDustMythical(),
            Materials.getAmalgamateIngotMythical(),
            Materials.getAmalgamateDustUnique(),
            Materials.getAmalgamateIngotUnique(),
            Materials.getArcaneSigil(),
            Materials.getGildedPearl()
        );
        return items;
    }

    private static List<SlimefunItem> basicMechanisms() {
        return items(
            Mechanisms.getChroniclerPanel1(),
            Mechanisms.getChroniclerPanel2(),
            Mechanisms.getRealisationAltar1(),
            Mechanisms.getRealisationAltar2(),
            Mechanisms.getLiquefactionBasin1(),
            Mechanisms.getLiquefactionBasin2()
        );
    }

    private static List<SlimefunItem> advancedMechanisms() {
        return items(
            Mechanisms.getChroniclerPanel3(),
            Mechanisms.getChroniclerPanel4(),
            Mechanisms.getRealisationAltar3(),
            Mechanisms.getRealisationAltar4(),
            Mechanisms.getLiquefactionBasin3(),
            Mechanisms.getLiquefactionBasin4(),
            Mechanisms.getStaveConfigurator()
        );
    }

    private static List<SlimefunItem> prismaticMechanisms() {
        return items(
            Mechanisms.getChroniclerPanel5(),
            Mechanisms.getRealisationAltar5(),
            Mechanisms.getLiquefactionBasin5(),
            Mechanisms.getPrismaticGilder()
        );
    }

    private static List<SlimefunItem> apprenticeSpellcraft() {
        return items(
            Tools.getInertPlate(),
            Tools.getChargedPlate(),
            Tools.getStaveBasic(),
            Tools.getRefractingLens(),
            Tools.getThaumaturgicSalts(),
            Tools.getLuminescenceScoop(),
            Tools.getConnectingCompass(),
            Tools.getSleepingBag(),
            Tools.getApprenticesSatchel()
        );
    }

    private static List<SlimefunItem> arcaneUtility() {
        return items(
            Tools.getStaveAdvanced(),
            Tools.getCrystaRecallLattice(),
            Tools.getEphemeralCraftingTable(),
            Tools.getBrillianceScoop(),
            Tools.getSpiritualSilken(),
            Tools.getSimpleDisplacer(),
            Tools.getCargoCover(),
            Tools.getBalmySponge(),
            Tools.getCrystamagesSatchel(),
            Tools.getWizardsSatchel()
        );
    }

    private static List<SlimefunItem> archmageImplements() {
        return items(
            Tools.getStaveArcane(),
            Tools.getEphemeralWorkBench(),
            Tools.getLustreScoop(),
            Tools.getRadianceScoop(),
            Tools.getIncorporealSilken(),
            Tools.getArcaneDisplacer(),
            Tools.getEnergyNetCover(),
            Tools.getNetworkNodeCover(),
            Tools.getSearingSponge(),
            Tools.getSuperMassiveSponge(),
            Tools.getDisplacedVoid(),
            Tools.getConjurersSatchel(),
            Tools.getSorcerersSatchel(),
            Tools.getGrandmastersSatchel()
        );
    }

    private static List<SlimefunItem> thaumicHusbandry() {
        return items(
            Gadgets.getAbstractionLamp(),
            Gadgets.getDispersionLamp(),
            Gadgets.getInversionVacuum(),
            Gadgets.getAntipodalVacuum(),
            Gadgets.getCursedEarth(),
            Gadgets.getDreadfulDirt(),
            Gadgets.getSoulfilledSoil(),
            Gadgets.getSearingPlate(),
            Gadgets.getDoomedPlate(),
            Gadgets.getEvisceratingPlate(),
            Gadgets.getTrapPlate(),
            Gadgets.getBasicExpCollector(),
            Gadgets.getInfusedExpCollector(),
            Gadgets.getBasicEnderInhibitor(),
            Gadgets.getAdvancedEnderInhibitor(),
            Gadgets.getDimMobCandle(),
            Gadgets.getBrightMobCandle(),
            Gadgets.getScintillatingMobCandle()
        );
    }

    private static List<SlimefunItem> mysticUtilities() {
        return items(
            Gadgets.getExodusLamp(),
            Gadgets.getCounterpoleVacuum(),
            Gadgets.getShreddingPlate(),
            Gadgets.getArcaneExpCollector(),
            Gadgets.getCoruscatingMobCandle(),
            Gadgets.getMysteriousPottedPlant(),
            Gadgets.getMysteriousPlant(),
            Gadgets.getMysteriousGlass(),
            Gadgets.getMysteriousWool(),
            Gadgets.getMysteriousTerracotta(),
            Gadgets.getMysteriousGlazedTerracotta(),
            Gadgets.getMysteriousConcrete(),
            Gadgets.getGreenHouseGlass(),
            Gadgets.getFocusedGreenHouseGlass(),
            Gadgets.getMagnifyingGreenHouseGlass(),
            Gadgets.getTrophyDisplay(),
            Gadgets.getExaltationStand(),
            Gadgets.getWaystone(),
            Gadgets.getAngelBlock(),
            Gadgets.getPhilosophersSpray(),
            Gadgets.getGlassOfMilk(),
            Gadgets.getFragmentedVoid(),
            Gadgets.getShatteredVoid()
        );
    }

    private static List<SlimefunItem> chromaticArts() {
        return items(
            ArtisticItems.getBlackPaintBrush100(),
            ArtisticItems.getBluePaintBrush100(),
            ArtisticItems.getBrownPaintBrush100(),
            ArtisticItems.getCyanPaintBrush100(),
            ArtisticItems.getGrayPaintBrush100(),
            ArtisticItems.getGreenPaintBrush100(),
            ArtisticItems.getLightBluePaintBrush100(),
            ArtisticItems.getLightGrayPaintBrush100(),
            ArtisticItems.getLimePaintBrush100(),
            ArtisticItems.getMagentaPaintBrush100(),
            ArtisticItems.getOrangePaintBrush100(),
            ArtisticItems.getPinkPaintBrush100(),
            ArtisticItems.getPurplePaintBrush100(),
            ArtisticItems.getRedPaintBrush100(),
            ArtisticItems.getWhitePaintBrush100(),
            ArtisticItems.getYellowPaintBrush100(),
            ArtisticItems.getBlackPaintBrush1000(),
            ArtisticItems.getBluePaintBrush1000(),
            ArtisticItems.getBrownPaintBrush1000(),
            ArtisticItems.getCyanPaintBrush1000(),
            ArtisticItems.getGrayPaintBrush1000(),
            ArtisticItems.getGreenPaintBrush1000(),
            ArtisticItems.getLightBluePaintBrush1000(),
            ArtisticItems.getLightGrayPaintBrush1000(),
            ArtisticItems.getLimePaintBrush1000(),
            ArtisticItems.getMagentaPaintBrush1000(),
            ArtisticItems.getOrangePaintBrush1000(),
            ArtisticItems.getPinkPaintBrush1000(),
            ArtisticItems.getPurplePaintBrush1000(),
            ArtisticItems.getRedPaintBrush1000(),
            ArtisticItems.getWhitePaintBrush1000(),
            ArtisticItems.getYellowPaintBrush1000(),
            ArtisticItems.getMysticalPigmentato(),
            ArtisticItems.getMysticalTintanno(),
            ArtisticItems.getPaintersResolve(),
            ArtisticItems.getBodyStand(),
            ArtisticItems.getMindStand(),
            ArtisticItems.getSoulStand(),
            ArtisticItems.getMysticalAttitudinizer(),
            ArtisticItems.getImbuedStand(),
            ArtisticItems.getPoseChanger(),
            ArtisticItems.getPoseCloner()
        );
    }

    private static List<SlimefunItem> hallmarksOfMastery() {
        return items(
            Uniques.getStoryTrophy(),
            Uniques.getSpellTrophy(),
            Uniques.getGildingTrophy(),
            Uniques.getChristmasTrophy(),
            Uniques.getValentinesTrophy(),
            Uniques.getBirthdayTrophyCheesy(),
            Uniques.getBirthdayTrophyBWhite(),
            Uniques.getBirthdayTrophyDecoy(),
            Uniques.getBirthdayTrophyOddish(),
            Uniques.getTenthAnniversaryTrophy()
        );
    }

    private static List<SlimefunItem> exaltedStudies() {
        return items(
            Exalted.getExaltedBeacon(),
            Exalted.getExaltedBaelfire(),
            Exalted.getExaltedFertilityPharo(),
            Exalted.getExaltedFertilityTotem(),
            Exalted.getExaltedHarvester(),
            Exalted.getExaltedAgronomist(),
            Exalted.getExaltedDawn(),
            Exalted.getExaltedDusk(),
            Exalted.getExaltedSun(),
            Exalted.getExaltedStorm(),
            Exalted.getExaltedSeaBreeze()
        );
    }

    private static List<SlimefunItem> runicMastery() {
        return items(
            Runes.getRuneBeast(),
            Runes.getRuneBeginning(),
            Runes.getRuneMoon(),
            Runes.getRuneGate(),
            Runes.getRuneTrueEarth(),
            Runes.getRuneChange(),
            Runes.getRuneNight(),
            Runes.getRuneBlack(),
            Runes.getRuneTrueHoly(),
            Runes.getRuneDragon(),
            Runes.getRuneTrueWater(),
            Runes.getRuneSovereign(),
            Runes.getRuneSun(),
            Runes.getRuneDawn(),
            Runes.getRuneTwilight(),
            Runes.getRuneTrueFire(),
            Runes.getRuneCircle(),
            Runes.getRuneBlinking(),
            Runes.getRuneSoul(),
            Runes.getRunePunishment(),
            Runes.getRuneTrueLightning(),
            Runes.getRuneEightfold(),
            Runes.getRuneCharm(),
            Runes.getRuneTrueWind(),
            Runes.getRuneBlackSword(),
            Runes.getRuneBrightShield()
        );
    }

    private static List<SlimefunItem> crystals(StoryRarity... rarities) {
        List<SlimefunItem> items = new ArrayList<>();
        Map<StoryRarity, Map<io.github.sefiraat.crystamaehistoria.stories.definition.StoryType, SlimefunItem>> crystalMap = Materials.getCrystalMap();

        for (StoryRarity rarity : rarities) {
            items.addAll(crystalMap.get(rarity).values());
        }

        return items;
    }

    private static List<SlimefunItem> items(SlimefunItem... items) {
        List<SlimefunItem> list = new ArrayList<>(items.length);
        add(list, items);
        return list;
    }

    private static void add(List<SlimefunItem> target, SlimefunItem... items) {
        for (SlimefunItem item : items) {
            target.add(item);
        }
    }
}
