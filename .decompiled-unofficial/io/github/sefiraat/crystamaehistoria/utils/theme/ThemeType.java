/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
 *  lombok.Generated
 *  net.kyori.adventure.text.format.TextColor
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.utils.theme;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

public enum ThemeType {
    WARNING(ChatColor.YELLOW, "Warning"),
    ERROR(ChatColor.RED, "Error"),
    NOTICE(ChatColor.WHITE, "Notice"),
    PASSIVE(ChatColor.GRAY, ""),
    SUCCESS(ChatColor.GREEN, "Success"),
    MAIN(ChatColor.of((String)"#21588f"), "Crystamae Historia"),
    CLICK_INFO(ChatColor.of((String)"#e4ed32"), "Click here"),
    RESEARCH(ChatColor.of((String)"#a60e03"), "Research"),
    CRAFTING(ChatColor.of((String)"#dbcea9"), "Crafting Material"),
    CRYSTAL(ChatColor.of((String)"#dbcea9"), "Crystal"),
    MACHINE(ChatColor.of((String)"#3295a8"), "Machine"),
    MECHANISM(ChatColor.of((String)"#3295a8"), "Mechanism"),
    GADGET(ChatColor.of((String)"#8732a8"), "Gadget"),
    EXALTED(ChatColor.of((String)"#8732a8"), "Exalted"),
    GUIDE(ChatColor.of((String)"#444444"), "Guide"),
    CHEST(ChatColor.of((String)"#b89b1c"), "Chest"),
    DROP(ChatColor.of((String)"#bf307f"), "Rare Drop"),
    BASE(ChatColor.of((String)"#9e9e9e"), "Base Resource"),
    MOLTEN_METAL(ChatColor.of((String)"#21588f"), "Molten Metal"),
    LIQUID(ChatColor.of((String)"#65dbb4"), "Liquid"),
    CAST(ChatColor.of((String)"#ffe138"), "Cast"),
    PART(ChatColor.of((String)"#42c8f5"), "Part"),
    TOOL(ChatColor.of((String)"#c2fc03"), "Tool"),
    STAVE(ChatColor.of((String)"#c2fc03"), "Stave"),
    ARMOUR(ChatColor.of((String)"#c2fc03"), "Armour"),
    INFO(ChatColor.of((String)"#21588f"), "Information"),
    MOD(ChatColor.of((String)"#bf307f"), "Modification"),
    PROP(ChatColor.of((String)"#bf307f"), "Material Trait"),
    SPELL(ChatColor.of((String)"#bf307f"), "Spell"),
    RUNE(ChatColor.of((String)"#32a852"), "Rune"),
    MULTIBLOCK(ChatColor.of((String)"#ba12af"), "Multiblock"),
    RARITY_COMMON(ChatColor.of((String)"#dbdbdb"), "Common"),
    RARITY_UNCOMMON(ChatColor.of((String)"#97d16b"), "Uncommon"),
    RARITY_RARE(ChatColor.of((String)"#d1db5c"), "Rare"),
    RARITY_EPIC(ChatColor.of((String)"#b355d9"), "Epic"),
    RARITY_MYTHICAL(ChatColor.of((String)"#c42336"), "Mythical"),
    RARITY_UNIQUE(ChatColor.of((String)"#b35f12"), "Unique"),
    TYPE_ELEMENTAL(ChatColor.of((String)"#ba0000"), "Elemental"),
    TYPE_MECHANICAL(ChatColor.of((String)"#ba5d00"), "Mechanical"),
    TYPE_ALCHEMICAL(ChatColor.of((String)"#e5e81a"), "Alchemical"),
    TYPE_HISTORICAL(ChatColor.of((String)"#24e81a"), "Historical"),
    TYPE_HUMAN(ChatColor.of((String)"#201ae8"), "Human"),
    TYPE_ANIMAL(ChatColor.of((String)"#701ae8"), "Animal"),
    TYPE_CELESTIAL(ChatColor.of((String)"#ffffff"), "Celestial"),
    TYPE_VOID(ChatColor.of((String)"#222222"), "Void"),
    TYPE_PHILOSOPHICAL(ChatColor.of((String)"#4d4aa8"), "Philosophical"),
    RANK_SPELL_APPRENTICE(ChatColor.of((String)"#cdbfff"), "Apprentice"),
    RANK_SPELL_MAGE(ChatColor.of((String)"#b5a1ff"), "Mage"),
    RANK_SPELL_WIZARD(ChatColor.of((String)"#9d82ff"), "Wizard"),
    RANK_SPELL_CONJURER(ChatColor.of((String)"#8969ff"), "Conjurer"),
    RANK_SPELL_SORCERER(ChatColor.of((String)"#6f47ff"), "Sorcerer"),
    RANK_SPELL_MAGI(ChatColor.of((String)"#5729ff"), "Magi"),
    RANK_SPELL_MASTER_MAGI(ChatColor.of((String)"#3d08ff"), "Master Magi"),
    RANK_SPELL_GRANDMASTER_MAGI(ChatColor.of((String)"#6b08ff"), "Grandmaster Magi"),
    RANK_STORY_PUPIL(ChatColor.of((String)"#eeffa8"), "Pupil"),
    RANK_STORY_STUDENT(ChatColor.of((String)"#e7ff82"), "Student"),
    RANK_STORY_RESEARCHER(ChatColor.of((String)"#e0ff5e"), "Researcher"),
    RANK_STORY_READER(ChatColor.of((String)"#d8ff33"), "Reader"),
    RANK_STORY_LECTURER(ChatColor.of((String)"#ceff00"), "Lecturer"),
    RANK_STORY_PROFESSOR(ChatColor.of((String)"#99ff00"), "Professor"),
    RANK_STORY_ADJUNCT_PROFESSOR(ChatColor.of((String)"#6aff00"), "Adjunct Professor"),
    RANK_STORY_EMERITUS_PROFESSOR(ChatColor.of((String)"#33ff00"), "Emeritus Professor"),
    RANK_BLOCK_UNKNOWN(ChatColor.of((String)"#a8ffb1"), "Unknown"),
    RANK_BLOCK_HEARD_OF(ChatColor.of((String)"#87ff94"), "Heard-of"),
    RANK_BLOCK_KNOWN(ChatColor.of((String)"#66ff77"), "Known"),
    RANK_BLOCK_DETAILED(ChatColor.of((String)"#4dff60"), "Detailed"),
    RANK_BLOCK_RESEARCHED(ChatColor.of((String)"#29ff40"), "Researched"),
    RANK_BLOCK_EXPERT_OF(ChatColor.of((String)"#0fff29"), "Expert-of"),
    RANK_BLOCK_MASTER_OF(ChatColor.of((String)"#00db18"), "Master-of"),
    RANK_BLOCK_SME(ChatColor.of((String)"#00820e"), "S.M.E."),
    RANK_GILDING_NOVICE(ChatColor.of((String)"#a8ffb1"), "Novice"),
    RANK_GILDING_MEMBER(ChatColor.of((String)"#87ff94"), "Member"),
    RANK_GILDING_SECRETARY(ChatColor.of((String)"#66ff77"), "Secretary"),
    RANK_GILDING_OFFICER(ChatColor.of((String)"#4dff60"), "Officer"),
    RANK_GILDING_EXECUTIVE(ChatColor.of((String)"#29ff40"), "Executive"),
    RANK_GILDING_CHIEF(ChatColor.of((String)"#0fff29"), "Chief"),
    RANK_GILDING_MANAGER(ChatColor.of((String)"#00db18"), "Manager"),
    RANK_GILDING_OWNER(ChatColor.of((String)"#00820e"), "Owner");

    @Nonnull
    private static final List<String> EGG_NAMES;
    private static final ThemeType[] cachedValues;
    private final ChatColor color;
    private final String loreLine;

    private ThemeType(ChatColor color, String loreLine) {
        this.color = color;
        this.loreLine = loreLine;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id, ItemStack itemStack, ThemeType themeType, String name, String ... lore) {
        ChatColor passiveColor = PASSIVE.getColor();
        ArrayList<Object> finalLore = new ArrayList<Object>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(String.valueOf(passiveColor) + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(id, itemStack, ThemeType.applyThemeToString(themeType, name), finalLore.toArray(new String[finalLore.size() - 1]));
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyThemeToString(ThemeType themeType, String string) {
        return String.valueOf(themeType.getColor()) + string;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack themedItemStack(Material material, ThemeType themeType, String name, String ... lore) {
        ChatColor passiveColor = PASSIVE.getColor();
        ArrayList<Object> finalLore = new ArrayList<Object>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(String.valueOf(passiveColor) + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(CLICK_INFO, themeType.getLoreLine()));
        return CustomItemStack.create((Material)material, (String)ThemeType.applyThemeToString(themeType, name), (String[])finalLore.toArray(new String[finalLore.size() - 1]));
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ThemeType getByRarity(StoryRarity storyRarity) {
        switch (storyRarity) {
            case COMMON: {
                return RARITY_COMMON;
            }
            case UNCOMMON: {
                return RARITY_UNCOMMON;
            }
            case RARE: {
                return RARITY_RARE;
            }
            case EPIC: {
                return RARITY_EPIC;
            }
            case MYTHICAL: {
                return RARITY_MYTHICAL;
            }
            case UNIQUE: {
                return RARITY_UNIQUE;
            }
        }
        throw new IllegalStateException("Unexpected value: " + String.valueOf((Object)storyRarity));
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ThemeType getByType(StoryType storyType) {
        switch (storyType) {
            case ELEMENTAL: {
                return TYPE_ELEMENTAL;
            }
            case MECHANICAL: {
                return TYPE_MECHANICAL;
            }
            case ALCHEMICAL: {
                return TYPE_ALCHEMICAL;
            }
            case HISTORICAL: {
                return TYPE_HISTORICAL;
            }
            case HUMAN: {
                return TYPE_HUMAN;
            }
            case ANIMAL: {
                return TYPE_ANIMAL;
            }
            case CELESTIAL: {
                return TYPE_CELESTIAL;
            }
            case VOID: {
                return TYPE_VOID;
            }
            case PHILOSOPHICAL: {
                return TYPE_PHILOSOPHICAL;
            }
        }
        throw new IllegalStateException("Unexpected value: " + String.valueOf((Object)storyType));
    }

    @Nonnull
    public static String getRandomEggName() {
        int rnd = ThreadLocalRandom.current().nextInt(0, EGG_NAMES.size());
        return EGG_NAMES.get(rnd);
    }

    @Nonnull
    public static List<String> getEggNames() {
        return EGG_NAMES;
    }

    @Nonnull
    public Particle.DustOptions getDustOptions(float size) {
        return new Particle.DustOptions(Color.fromRGB((int)this.color.getColor().getRed(), (int)this.color.getColor().getGreen(), (int)this.color.getColor().getBlue()), size);
    }

    @Nonnull
    public TextColor getComponentColor() {
        return TextColor.color((int)this.getColor().getColor().getRGB());
    }

    @Generated
    public ChatColor getColor() {
        return this.color;
    }

    @Generated
    public String getLoreLine() {
        return this.loreLine;
    }

    @Generated
    public static ThemeType[] getCachedValues() {
        return cachedValues;
    }

    static {
        EGG_NAMES = Arrays.asList("TheBusyBiscuit", "Alessio", "Walshy", "Jeff", "Seggan", "BOOMER_1", "svr333", "variananora", "ProfElements", "Riley", "FluffyBear", "GallowsDove", "Apeiros", "Martin", "Bunnky", "ReasonFoundDecoy", "Oah", "Azak", "andrewandy", "EpicPlayer10", "GentlemanCheesy", "ybw0014", "Ashian", "R.I.P", "OOOOMAGAAA", "TerslenK", "FN_FAL", "supertechxter");
        cachedValues = ThemeType.values();
    }
}

