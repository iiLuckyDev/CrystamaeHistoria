/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PlutosDecent
extends Spell {
    protected static final List<Material> MATERIALS = new ArrayList<Material>();

    public PlutosDecent() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(25.0, false, 60.0, false, 75, true).makeDamagingSpell(5.0, true, 1.0, false).makeProjectileSpell(this::cast, 0.5, true, 1.0, true).makeProjectileVsBlockSpell(this::blockLands);
        this.setSpellCore(spellCoreBuilder.build());
    }

    private void cast(CastInformation castInformation) {
        int range;
        Player player = castInformation.getCasterAsPlayer();
        Block targetBlock = player.getTargetBlockExact(range = (int)this.getRange(castInformation));
        if (targetBlock != null) {
            Location target = targetBlock.getLocation().add(0.5, 0.5, 0.5);
            ArrayList<Block> blocks = new ArrayList<Block>();
            int radius = this.getRadius(castInformation);
            for (int y = -radius; y < radius; ++y) {
                for (int x = -radius; x < radius; ++x) {
                    for (int z = -radius; z < radius; ++z) {
                        Block block;
                        if (!(Math.sqrt((double)(x * x) + (double)(y * y) + (double)(z * z)) <= (double)range) || blocks.contains(block = target.getWorld().getBlockAt(x + target.getBlockX(), y + target.getBlockY(), z + target.getBlockZ())) || !GeneralUtils.hasPermission(player, block, Interaction.PLACE_BLOCK)) continue;
                        blocks.add(block);
                    }
                }
            }
            this.spawnBlocks(castInformation, blocks);
        }
    }

    private void blockLands(CastInformation castInformation) {
        Location location = castInformation.getHitBlock().getLocation();
        location.getWorld().createExplosion((Entity)castInformation.getCasterAsPlayer(), location, (float)this.getRadius(castInformation) + 1.0f, true, true);
    }

    private int getRadius(CastInformation castInformation) {
        return switch (castInformation.getStaveLevel()) {
            case 3, 4 -> 2;
            case 5 -> 3;
            default -> 1;
        };
    }

    private void spawnBlocks(CastInformation castInformation, List<Block> blocks) {
        for (Block block : blocks) {
            MagicFallingBlock magicFallingBlock = SpellUtils.summonMagicFallingBlock(castInformation, block.getLocation().add(0.0, 40.0, 0.0), MATERIALS.get(ThreadLocalRandom.current().nextInt(MATERIALS.size())), 5);
            magicFallingBlock.setVelocity(block.getLocation(), 2.0);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ELEMENTAL, StoryType.ALCHEMICAL, StoryType.HUMAN);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a meteor to strike down your foes."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "PLUTOS_DESCENT";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.COBBLED_DEEPSLATE;
    }

    static {
        MATERIALS.add(Material.BLACKSTONE_SLAB);
        MATERIALS.add(Material.BLACKSTONE_STAIRS);
        MATERIALS.add(Material.BLACKSTONE_WALL);
        MATERIALS.add(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    }
}

