/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
 *  lombok.Generated
 *  org.bukkit.Color
 *  org.bukkit.FireworkEffect
 *  org.bukkit.FireworkEffect$Type
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Firework
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.FireworkMeta
 */
package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.GildingRank;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.player.StoryRank;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaRecipeTypes;
import io.github.sefiraat.crystamaehistoria.slimefun.CrystaStacks;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Trophy;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public final class Uniques {
    private static Trophy storyTrophy;
    private static Trophy spellTrophy;
    private static Trophy gildingTrophy;
    private static Trophy christmasTrophy;
    private static Trophy valentinesTrophy;
    private static Trophy birthdayTrophyCheesy;
    private static Trophy birthdayTrophyBWhite;
    private static Trophy birthdayTrophyDecoy;
    private static Trophy birthdayTrophyOddish;
    private static Trophy tenthAnniversaryTrophy;

    public static void setup() {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        RecipeItem spellTrophyRecipe = new RecipeItem(new ItemStack(Material.PAPER), StoryType.ELEMENTAL, 100, StoryType.ALCHEMICAL, 100, StoryType.VOID, 100, Uniques::isMaxSpellRank);
        spellTrophy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.SPELL_TROPHY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, spellTrophyRecipe.getDisplayRecipe(), location -> ParticleUtils.displayParticleEffect(location.add(0.0, 0.2, 0.0), 0.2, 3, new Particle.DustOptions(Color.ORANGE, 1.0f)));
        RecipeItem storyTrophyRecipe = new RecipeItem(new ItemStack(Material.PAPER), StoryType.ELEMENTAL, 100, StoryType.ALCHEMICAL, 100, StoryType.CELESTIAL, 100, Uniques::isMaxStoryRank);
        storyTrophy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.STORY_TROPHY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, storyTrophyRecipe.getDisplayRecipe(), location -> ParticleUtils.displayParticleEffect(location.add(0.0, 0.2, 0.0), 0.2, 3, new Particle.DustOptions(Color.AQUA, 1.0f)));
        RecipeItem gildedTrophyRecipe = new RecipeItem(CrystaStacks.RUNE_SUN.item().clone(), StoryType.MECHANICAL, 100, StoryType.HUMAN, 100, StoryType.PHILOSOPHICAL, 100, Uniques::isMaxGildingRank);
        gildingTrophy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.GILDING_TROPHY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, gildedTrophyRecipe.getDisplayRecipe(), location -> ParticleUtils.displayParticleEffect(location.add(0.0, 0.2, 0.0), 0.2, 3, new Particle.DustOptions(Color.MAROON, 1.0f)));
        RecipeItem christmasTrophyRecipe = new RecipeItem(new ItemStack(Material.SPRUCE_SAPLING), StoryType.HUMAN, 250, StoryType.PHILOSOPHICAL, 250, StoryType.CELESTIAL, 250, Uniques::isChristmas);
        christmasTrophy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.CHRISTMAS_TROPHY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, christmasTrophyRecipe.getDisplayRecipe(), location -> {
            ParticleUtils.displayParticleEffect(location.add(0.0, 0.2, 0.0), 0.2, 3, new Particle.DustOptions(Color.RED, 1.0f));
            ParticleUtils.displayParticleEffect(location.add(0.0, 0.2, 0.0), 0.2, 3, new Particle.DustOptions(Color.LIME, 1.0f));
        });
        RecipeItem valentinesTrophyRecipe = new RecipeItem(SlimefunItems.RAINBOW_WOOL_VALENTINE.item().clone(), StoryType.HUMAN, 250, StoryType.ELEMENTAL, 250, StoryType.HISTORICAL, 250, Uniques::isValentines);
        valentinesTrophy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.VALENTINES_TROPHY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, valentinesTrophyRecipe.getDisplayRecipe(), location -> ParticleUtils.displayParticleEffect(location.add(0.0, 0.2, 0.0), Particle.HEART, 1.0, 3));
        RecipeItem birthdayTrophyCheesyRecipe = new RecipeItem(new ItemStack(Material.CAKE), StoryType.HUMAN, 50, StoryType.ELEMENTAL, 50, StoryType.HISTORICAL, 50, Uniques::isBirthdayCheesy);
        birthdayTrophyCheesy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.BIRTHDAY_TROPHY_CHEESY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, birthdayTrophyCheesyRecipe.getDisplayRecipe(), location -> {
            Location spawnLocation = location.add(0.0, 0.2, 0.0);
            int rand = ThreadLocalRandom.current().nextInt(19);
            if (rand == 0) {
                Uniques.spawnBirthdayFirework(spawnLocation, Color.ORANGE);
            }
            ParticleUtils.displayParticleEffect(spawnLocation, 1.0, 3, new Particle.DustOptions(Color.ORANGE, 2.0f));
        });
        RecipeItem birthdayTrophyBWhiteRecipe = new RecipeItem(new ItemStack(Material.CAKE), StoryType.HUMAN, 50, StoryType.VOID, 50, StoryType.PHILOSOPHICAL, 50, Uniques::isBirthdayBWhite);
        birthdayTrophyBWhite = new Trophy(ItemGroups.UNIQUES, CrystaStacks.BIRTHDAY_TROPHY_BWHITE, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, birthdayTrophyBWhiteRecipe.getDisplayRecipe(), location -> {
            Location spawnLocation = location.add(0.0, 0.2, 0.0);
            int rand = ThreadLocalRandom.current().nextInt(19);
            if (rand == 0) {
                Uniques.spawnBirthdayFirework(spawnLocation, Color.RED);
            }
            ParticleUtils.displayParticleEffect(spawnLocation, 1.0, 3, new Particle.DustOptions(Color.RED, 2.0f));
        });
        RecipeItem birthdayTrophyDecoyRecipe = new RecipeItem(new ItemStack(Material.CAKE), StoryType.HUMAN, 50, StoryType.CELESTIAL, 50, StoryType.PHILOSOPHICAL, 50, Uniques::isBirthdayDecoy);
        birthdayTrophyDecoy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.BIRTHDAY_TROPHY_DECOY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, birthdayTrophyDecoyRecipe.getDisplayRecipe(), location -> {
            Location spawnLocation = location.add(0.0, 0.2, 0.0);
            int rand = ThreadLocalRandom.current().nextInt(19);
            if (rand == 0) {
                Uniques.spawnBirthdayFirework(spawnLocation, Color.TEAL);
            }
            ParticleUtils.displayParticleEffect(spawnLocation, 1.0, 3, new Particle.DustOptions(Color.TEAL, 2.0f));
        });
        RecipeItem birthdayTrophyOddishRecipe = new RecipeItem(new ItemStack(Material.CAKE), StoryType.ANIMAL, 50, StoryType.ELEMENTAL, 50, StoryType.CELESTIAL, 50, Uniques::isBirthdayOddish);
        birthdayTrophyOddish = new Trophy(ItemGroups.UNIQUES, CrystaStacks.BIRTHDAY_TROPHY_ODDISH, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, birthdayTrophyOddishRecipe.getDisplayRecipe(), location -> {
            Location spawnLocation = location.add(0.0, 0.2, 0.0);
            int rand = ThreadLocalRandom.current().nextInt(19);
            Color color = Color.fromRGB((int)0, (int)87, (int)87);
            if (rand == 0) {
                Uniques.spawnBirthdayFirework(spawnLocation, color);
            }
            ParticleUtils.displayParticleEffect(spawnLocation, 1.0, 3, new Particle.DustOptions(color, 2.0f));
        });
        RecipeItem anniversaryTrophyRecipe = new RecipeItem(new ItemStack(Material.COOKIE), StoryType.HUMAN, 500, StoryType.ELEMENTAL, 500, StoryType.VOID, 500, Uniques::isTenthAnniversary);
        tenthAnniversaryTrophy = new Trophy(ItemGroups.UNIQUES, CrystaStacks.ANNIVERSARY_TROPHY, CrystaRecipeTypes.LIQUEFACTION_CRAFTING, anniversaryTrophyRecipe.getDisplayRecipe(), location -> {
            int rand = ThreadLocalRandom.current().nextInt(9);
            if (rand != 0) {
                return;
            }
            for (int i = 0; i < 4; ++i) {
                Location spawnLocation = location.add(0.0, 0.3, 0.0);
                Color color = Color.fromRGB((int)55, (int)180, (int)30);
                Uniques.spawnBirthdayFirework(spawnLocation, color);
                ParticleUtils.displayParticleEffect(spawnLocation, 1.0, 3, new Particle.DustOptions(color, 2.0f));
            }
            Location spawnLocation = location.add(0.0, 0.2, 0.0);
            Color color = Color.fromRGB((int)255, (int)255, (int)255);
            Uniques.spawnBirthdayFirework(spawnLocation, color);
            ParticleUtils.displayParticleEffect(spawnLocation, 1.0, 3, new Particle.DustOptions(color, 2.0f));
        });
        spellTrophy.register(plugin);
        storyTrophy.register(plugin);
        gildingTrophy.register(plugin);
        christmasTrophy.register(plugin);
        valentinesTrophy.register(plugin);
        tenthAnniversaryTrophy.register(plugin);
        birthdayTrophyCheesy.register(plugin);
        birthdayTrophyBWhite.register(plugin);
        birthdayTrophyDecoy.register(plugin);
        birthdayTrophyOddish.register(plugin);
        LiquefactionBasinCache.addCraftingRecipe(spellTrophy, spellTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(storyTrophy, storyTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(christmasTrophy, christmasTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(valentinesTrophy, valentinesTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(tenthAnniversaryTrophy, anniversaryTrophyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(birthdayTrophyCheesy, birthdayTrophyCheesyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(birthdayTrophyBWhite, birthdayTrophyBWhiteRecipe);
        LiquefactionBasinCache.addCraftingRecipe(birthdayTrophyDecoy, birthdayTrophyDecoyRecipe);
        LiquefactionBasinCache.addCraftingRecipe(valentinesTrophy, birthdayTrophyOddishRecipe);
    }

    private static boolean isMaxStoryRank(@Nonnull Player player) {
        return PlayerStatistics.getStoryRank(player.getUniqueId()) == StoryRank.EMERITUS_PROFESSOR;
    }

    private static boolean isMaxSpellRank(@Nonnull Player player) {
        return PlayerStatistics.getSpellRank(player.getUniqueId()) == SpellRank.GRANDMASTER_MAGI;
    }

    private static boolean isMaxGildingRank(@Nonnull Player player) {
        return PlayerStatistics.getGildingRank(player.getUniqueId()) == GildingRank.OWNER;
    }

    private static boolean isChristmas(@Nonnull Player player) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, 12, 20);
        LocalDate end = LocalDate.of(year + 1, 1, 5);
        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayCheesy(@Nonnull Player player) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, 3, 28);
        LocalDate end = LocalDate.of(year, 3, 30);
        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayBWhite(@Nonnull Player player) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, 9, 9);
        LocalDate end = LocalDate.of(year, 9, 11);
        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayDecoy(@Nonnull Player player) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, 12, 10);
        LocalDate end = LocalDate.of(year, 12, 12);
        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isBirthdayOddish(@Nonnull Player player) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, 2, 11);
        LocalDate end = LocalDate.of(year, 2, 13);
        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isValentines(@Nonnull Player player) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        LocalDate start = LocalDate.of(year, 2, 6);
        LocalDate end = LocalDate.of(year, 2, 20);
        return now.isAfter(start) && now.isBefore(end);
    }

    private static boolean isTenthAnniversary(@Nonnull Player player) {
        LocalDate now = LocalDate.now();
        int year = 2023;
        LocalDate start = LocalDate.of(2023, 3, 8);
        LocalDate end = LocalDate.of(2023, 3, 22);
        return now.isAfter(start) && now.isBefore(end);
    }

    private static void spawnBirthdayFirework(@Nonnull Location location, @Nonnull Color color) {
        Firework firework = (Firework)location.getWorld().spawnEntity(location, EntityType.FIREWORK_ROCKET);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder().withColor(color).with(FireworkEffect.Type.STAR).withFlicker().withTrail().build());
        firework.setFireworkMeta(fireworkMeta);
    }

    @Generated
    private Uniques() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static Trophy getStoryTrophy() {
        return storyTrophy;
    }

    @Generated
    public static Trophy getSpellTrophy() {
        return spellTrophy;
    }

    @Generated
    public static Trophy getGildingTrophy() {
        return gildingTrophy;
    }

    @Generated
    public static Trophy getChristmasTrophy() {
        return christmasTrophy;
    }

    @Generated
    public static Trophy getValentinesTrophy() {
        return valentinesTrophy;
    }

    @Generated
    public static Trophy getBirthdayTrophyCheesy() {
        return birthdayTrophyCheesy;
    }

    @Generated
    public static Trophy getBirthdayTrophyBWhite() {
        return birthdayTrophyBWhite;
    }

    @Generated
    public static Trophy getBirthdayTrophyDecoy() {
        return birthdayTrophyDecoy;
    }

    @Generated
    public static Trophy getBirthdayTrophyOddish() {
        return birthdayTrophyOddish;
    }

    @Generated
    public static Trophy getTenthAnniversaryTrophy() {
        return tenthAnniversaryTrophy;
    }
}

