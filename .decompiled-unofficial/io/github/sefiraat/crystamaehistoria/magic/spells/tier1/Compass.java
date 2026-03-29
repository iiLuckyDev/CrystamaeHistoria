/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Compass
extends Spell {
    public Compass() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10.0, false, 20.0, true, 10, true).makeTickingSpell(this::onTick, 10, true, 20, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        Location location = player.getLocation().add(0.0, 1.5, 0.0);
        ItemStack itemStack = player.getInventory().getItemInOffHand();
        Material material = itemStack.getType();
        if (material != Material.AIR && material.isBlock()) {
            Block foundBlock = this.tryGetBlock(player, material, (int)this.getRange(castInformation));
            if (foundBlock == null) {
                ParticleUtils.displayParticleEffect(location.add(location.getDirection()), Particle.ANGRY_VILLAGER, 1.0, 10);
            } else {
                Location foundBlockLocation = foundBlock.getLocation().add(0.5, 0.5, 0.5);
                List list = CrystamaeHistoria.getConfigManager().getBlockColors().getList(material.name());
                Color color = Color.fromRGB((int)((Integer)list.get(0)), (int)((Integer)list.get(1)), (int)((Integer)list.get(2)));
                Particle.DustOptions dustOptionsToBlock = new Particle.DustOptions(color, 1.0f);
                ParticleUtils.drawLine(dustOptionsToBlock, location, foundBlockLocation, 0.2);
            }
        }
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public Block tryGetBlock(Player player, Material material, int range) {
        Location location = player.getLocation();
        for (int x = -range; x < range; ++x) {
            for (int y = -range; y < range; ++y) {
                for (int z = -range; z < range; ++z) {
                    Block block = location.clone().add((double)x, (double)y, (double)z).getBlock();
                    if (block.getType() != material) continue;
                    return block;
                }
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ALCHEMICAL, StoryType.HUMAN);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Tap into the ether to find blocks.", "Will locate blocks matching the one", "in the caster's off-hand."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "COMPASS";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.COMPASS;
    }
}

