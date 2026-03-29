/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
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
import org.bukkit.entity.Player;

public class Break
extends Spell {
    public Break() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10.0, true, 50.0, true, 5, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        Block block = player.getTargetBlockExact((int)this.getRange(castInformation));
        GeneralUtils.tryBreakBlock(castInformation.getCaster(), block);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.MECHANICAL, StoryType.ALCHEMICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Breaks the block being targeted"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "BREAK";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CRACKED_STONE_BRICKS;
    }
}

