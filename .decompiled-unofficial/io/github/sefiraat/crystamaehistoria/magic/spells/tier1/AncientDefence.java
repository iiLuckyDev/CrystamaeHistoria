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

public class AncientDefence
extends Spell {
    public AncientDefence() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, true, 5.0, false, 20, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        int density = 50;
        for (double height = 0.0; height <= Math.PI; height += 0.06283185307179587) {
            double r = range * Math.sin(height);
            double y = range * Math.cos(height);
            for (double a = 0.0; a < Math.PI * 2; a += 0.06283185307179587) {
                double x = Math.cos(a) * r;
                double z = Math.sin(a) * r;
                Block block = location.clone().add(x, y, z).getBlock();
                if (block.getType() != Material.AIR || !GeneralUtils.hasPermission(castInformation.getCaster(), block, Interaction.PLACE_BLOCK)) continue;
                block.setType(Material.RED_STAINED_GLASS);
                GeneralUtils.markBlockForRemoval(block, 5);
            }
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.HISTORICAL, StoryType.HUMAN);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a defensive wall in front of", "the caster."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "ANCIENT_DEFENCE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CRYING_OBSIDIAN;
    }
}

