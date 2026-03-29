/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.entity.Chicken
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Turtle
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Turtle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Oviparous
extends Spell {
    public Oviparous() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20.0, true, 10.0, true, 5, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location casterLocation = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        for (Entity entity : casterLocation.getWorld().getNearbyEntities(casterLocation, range, range, range)) {
            Block block;
            Particle.DustOptions dustOptions;
            Location location = entity.getLocation();
            if (entity instanceof Chicken) {
                ItemStack itemStack = new ItemStack(Material.EGG);
                location.getWorld().dropItemNaturally(location, itemStack);
                dustOptions = new Particle.DustOptions(Color.fromRGB((int)190, (int)170, (int)115), 2.0f);
                ParticleUtils.displayParticleEffect(location, 1.0, 5, dustOptions);
                entity.setVelocity(new Vector(0, 1, 0));
            }
            if (!(entity instanceof Turtle) || (block = location.clone().add(0.0, 0.5, 0.0).getBlock()).getType() != Material.AIR || !block.getRelative(BlockFace.DOWN).getType().isSolid()) continue;
            block.setType(Material.TURTLE_EGG);
            dustOptions = new Particle.DustOptions(Color.fromRGB((int)95, (int)160, (int)80), 2.0f);
            ParticleUtils.displayParticleEffect(location, 1.0, 5, dustOptions);
            entity.setVelocity(new Vector(0.0, 0.5, 0.0));
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ANIMAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Give me dem eggs!"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "OVIPAROUS";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.EGG;
    }
}

