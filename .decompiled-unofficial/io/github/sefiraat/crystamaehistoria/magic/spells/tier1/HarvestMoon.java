/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Keyed
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Tag
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class HarvestMoon
extends Spell {
    public HarvestMoon() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40.0, true, 5.0, true, 30, true).makeTickingSpell(this::cast, 20, true, 10, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCasterAsPlayer().getLocation().clone().add(0.0, 1.0, 0.0);
        double range = this.getRange(castInformation);
        int density = (int)(3.0 * range);
        for (double height = 0.0; height <= Math.PI; height += Math.PI / (double)density) {
            double r = range * Math.sin(height);
            double y = range * Math.cos(height);
            for (double a = 0.0; a < Math.PI * 2; a += Math.PI / (double)density) {
                double x = Math.cos(a) * r;
                double z = Math.sin(a) * r;
                Location point = location.clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, Particle.WAX_OFF, 0.1, 1);
            }
        }
        for (int i = 0; i < castInformation.getStaveLevel() * 3; ++i) {
            int z;
            int x = ThreadLocalRandom.current().nextInt((int)(-range), (int)range);
            Block potentialBlock = location.add((double)x, 0.0, (double)(z = ThreadLocalRandom.current().nextInt((int)(-range), (int)range))).getBlock();
            if (!Tag.CROPS.isTagged((Keyed)potentialBlock.getType())) continue;
            potentialBlock.applyBoneMeal(BlockFace.UP);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.HUMAN, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Speaks to the spirit of nature", "to speed up the growth of nearby", "crops. Crops must have direct access", "to the sun."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "HARVEST_MOON";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.WHEAT;
    }
}

