/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.Tag
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PhilosophersStone
extends Spell {
    protected static final List<Material> MATERIALS_RARE = new ArrayList<Material>();
    protected static final List<Material> MATERIALS_UNCOMMON = new ArrayList<Material>();
    protected static final List<Material> MATERIALS_COMMON = new ArrayList<Material>();

    public PhilosophersStone() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10.0, true, 50.0, true, 5, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Block block;
        Player player = castInformation.getCasterAsPlayer();
        if (GeneralUtils.hasPermission(player, block = player.getTargetBlockExact((int)this.getRange(castInformation)), Interaction.BREAK_BLOCK) && block.getType().getHardness() != -1.0f && !block.getType().isAir()) {
            Location location = block.getLocation();
            int random = ThreadLocalRandom.current().nextInt(100);
            Material material = random > 95 ? MATERIALS_RARE.get(ThreadLocalRandom.current().nextInt(0, MATERIALS_RARE.size())) : (random > 60 ? MATERIALS_UNCOMMON.get(ThreadLocalRandom.current().nextInt(0, MATERIALS_UNCOMMON.size())) : MATERIALS_COMMON.get(ThreadLocalRandom.current().nextInt(0, MATERIALS_COMMON.size())));
            List list = CrystamaeHistoria.getConfigManager().getBlockColors().getList(material.name());
            Color color = Color.fromRGB((int)((Integer)list.get(0)), (int)((Integer)list.get(1)), (int)((Integer)list.get(2)));
            Particle.DustOptions dustOptionsToBlock = new Particle.DustOptions(color, 1.0f);
            ParticleUtils.drawCube(dustOptionsToBlock, location, location.clone().add(1.0, 1.0, 1.0), 0.25);
            block.setType(material);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.HUMAN, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Converts the targets block into...", "something else..."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "PHILOSOPHERS_STONE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.GOLD_BLOCK;
    }

    static {
        MATERIALS_RARE.add(Material.COPPER_BLOCK);
        MATERIALS_RARE.add(Material.IRON_BLOCK);
        MATERIALS_RARE.add(Material.EMERALD_BLOCK);
        MATERIALS_RARE.add(Material.DIAMOND_BLOCK);
        MATERIALS_RARE.add(Material.GOLD_BLOCK);
        MATERIALS_UNCOMMON.addAll(SlimefunTag.ORES.getValues());
        MATERIALS_UNCOMMON.addAll(CrystaTag.CONCRETE_BLOCKS.getValues());
        MATERIALS_UNCOMMON.addAll(CrystaTag.GLAZED_TERRACOTTA.getValues());
        MATERIALS_COMMON.addAll(Tag.STONE_ORE_REPLACEABLES.getValues());
        MATERIALS_COMMON.addAll(Tag.DIRT.getValues());
        MATERIALS_COMMON.addAll(Tag.LOGS.getValues());
        MATERIALS_COMMON.addAll(Tag.SAND.getValues());
        MATERIALS_COMMON.addAll(Tag.SNOW.getValues());
        MATERIALS_COMMON.addAll(SlimefunTag.CONCRETE_POWDERS.getValues());
    }
}

