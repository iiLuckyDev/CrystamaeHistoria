/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Tag
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.block.data.Bisected
 *  org.bukkit.block.data.Bisected$Half
 *  org.bukkit.block.data.BlockData
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;

public class HarmonysSonata
extends Spell {
    private static final Set<Material> TALL_FLOWERS = EnumSet.of(Material.SUNFLOWER, Material.LILAC, Material.ROSE_BUSH, Material.PEONY);

    public HarmonysSonata() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, true, 10.0, true, 10, true).makeTickingSpell(this::onTick, 15, true, 3, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    public static boolean isTallFlower(Material m) {
        return TALL_FLOWERS.contains(m);
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        double range = this.getRange(castInformation);
        Location location = castInformation.getCastLocation().clone().add(ThreadLocalRandom.current().nextDouble(-range, range), 0.0, ThreadLocalRandom.current().nextDouble(-range, range));
        Block block = location.getBlock();
        if (block.getType() == Material.AIR && SlimefunTag.DIRT_VARIANTS.isTagged(block.getRelative(BlockFace.DOWN).getType()) && GeneralUtils.hasPermission(castInformation.getCaster(), block, Interaction.PLACE_BLOCK)) {
            Set set = Tag.FLOWERS.getValues();
            Material material = set.stream().skip(ThreadLocalRandom.current().nextInt(set.size())).findAny().orElse(Material.DANDELION);
            if (HarmonysSonata.isTallFlower(material)) {
                Block upper = block.getRelative(BlockFace.UP);
                if (upper.getType() == Material.AIR) {
                    block.setType(material, false);
                    upper.setType(material, false);
                    Bisected bisectedTop = (Bisected)upper.getBlockData();
                    bisectedTop.setHalf(Bisected.Half.TOP);
                    upper.setBlockData((BlockData)bisectedTop);
                    Bisected bisectedBottom = (Bisected)block.getBlockData();
                    bisectedBottom.setHalf(Bisected.Half.BOTTOM);
                    block.setBlockData((BlockData)bisectedBottom);
                } else {
                    block.setType(Material.DANDELION);
                }
            } else {
                block.setType(material);
            }
            block.getRelative(BlockFace.DOWN).setType(Material.GRASS_BLOCK);
            ParticleUtils.displayParticleEffect(block.getLocation(), Particle.FIREWORK, 0.5, 3);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.ALCHEMICAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Allows a rich garden to grow at your feet."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "HARMONYS_SONATA";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.GRASS_BLOCK;
    }
}

