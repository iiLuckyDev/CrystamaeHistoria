/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

public class Cascada
extends Spell {
    public Cascada() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, true, 0.0, false, 30, false).makeProjectileSpell(this::fireProjectile, 2.0, false, 0.0, false).makeProjectileVsBlockSpell(this::projectileHit).makeProjectileVsEntitySpell(this::projectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0.0, 1.5, 0.0).add(location.getDirection().multiply(2));
        MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SNOWBALL, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 1.5);
        magicProjectile.disableGravity();
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getProjectileLocation();
        int range = (int)this.getProjectileAoe(castInformation) + castInformation.getStaveLevel();
        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int y = -range; y < range; ++y) {
            for (int x = -range; x < range; ++x) {
                for (int z = -range; z < range; ++z) {
                    Block block;
                    if (Math.sqrt((double)(x * x) + (double)(y * y) + (double)(z * z)) > (double)range || blocks.contains(block = location.getWorld().getBlockAt(x + location.getBlockX(), y + location.getBlockY(), z + location.getBlockZ())) || !GeneralUtils.hasPermission(caster, block, Interaction.BREAK_BLOCK)) continue;
                    blocks.add(block);
                }
            }
        }
        for (Block block : blocks) {
            BlockData blockData = block.getBlockData();
            FallingBlock entity = block.getWorld().spawnFallingBlock(block.getLocation().add(0.5, 0.5, 0.5), blockData);
            entity.setVelocity(new Vector(0, 2, 0));
            block.setType(Material.AIR, false);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HISTORICAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Turns the ground inside out to damage foes."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "CASCADA";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.PODZOL;
    }
}

