/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class StripMine
extends Spell {
    public StripMine() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 10.0, true, 10, true).makeTickingSpell(this::tick, 10, true, 5, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void tick(CastInformation castInformation) {
        BlockFace blockFace = castInformation.getTargetedBlockFaceOnCast();
        if (blockFace == BlockFace.UP || blockFace == BlockFace.DOWN) {
            return;
        }
        int xOffset = 0;
        int zOffset = 0;
        Vector faceDirection = castInformation.getTargetedBlockFaceOnCast().getDirection();
        if (faceDirection.getX() == 0.0) {
            zOffset = (int)((double)(castInformation.getCurrentTick() - 1) * -faceDirection.getZ());
        } else if (faceDirection.getZ() == 0.0) {
            xOffset = (int)((double)(castInformation.getCurrentTick() - 1) * -faceDirection.getX());
        }
        Block block = castInformation.getTargetedBlockOnCast().getRelative(xOffset, 0, zOffset);
        Block blockBelow = block.getRelative(BlockFace.DOWN);
        if (GeneralUtils.tryBreakBlock(castInformation.getCaster(), block) && GeneralUtils.tryBreakBlock(castInformation.getCaster(), blockBelow) && castInformation.getCurrentTick() % 5 == 0) {
            blockBelow.setType(Material.TORCH);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ALCHEMICAL, StoryType.HISTORICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Magically strips a mineshaft starting from", "the targeted block."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "STRIP_MINE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.MINECART;
    }
}

