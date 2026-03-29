/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class LavaLake
extends Spell {
    public LavaLake() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, true, 4.0, true, 25, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double size = this.getRange(castInformation);
        Block standingBlock = location.getBlock();
        if (GeneralUtils.hasPermission(castInformation.getCaster(), standingBlock, Interaction.PLACE_BLOCK) && standingBlock.getType() == Material.AIR) {
            standingBlock.setType(Material.NETHER_BRICKS);
            GeneralUtils.markBlockForRemoval(standingBlock, 6);
            castInformation.getCasterAsPlayer().teleport(location.clone().add(0.0, 1.0, 0.0));
        }
        for (double x = -size; x < size; x += 1.0) {
            for (double z = -size; z < size; z += 1.0) {
                Block potentialLava = location.clone().add(x, 0.0, z).getBlock();
                if (!GeneralUtils.hasPermission(castInformation.getCaster(), potentialLava, Interaction.PLACE_BLOCK) || potentialLava.getType() != Material.AIR) continue;
                potentialLava.setType(Material.LAVA);
                GeneralUtils.markBlockForRemoval(potentialLava, 5);
            }
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.ALCHEMICAL, StoryType.HISTORICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Spawns a lake of lava around the caster"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "LAVA_LAKE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.LAVA_BUCKET;
    }
}

