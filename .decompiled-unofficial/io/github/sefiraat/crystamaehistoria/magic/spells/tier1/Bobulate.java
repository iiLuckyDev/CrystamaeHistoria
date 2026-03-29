/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag
 *  org.bukkit.DyeColor
 *  org.bukkit.Keyed
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Tag
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.material.Colorable
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.DyeColor;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.material.Colorable;

public class Bobulate
extends Spell {
    public Bobulate() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, true, 0.0, false, 10, false).makeProjectileSpell(this::fireProjectile, 5.0, true, 0.0, false).makeProjectileVsBlockSpell(this::projectileHit).makeProjectileVsEntitySpell(this::projectileHit);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        Location aimLocation = location.clone().add(0.0, 1.5, 0.0).add(location.getDirection().multiply(2));
        MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.POTION, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 1.5);
        magicProjectile.disableGravity();
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getProjectileLocation();
        double range = this.getProjectileAoe(castInformation);
        int density = 50;
        for (double height = 0.0; height <= Math.PI; height += 0.06283185307179587) {
            int i = 0;
            while ((double)i < range) {
                double r = (double)i * Math.sin(height);
                double y = (double)i * Math.cos(height);
                for (double a = 0.0; a < Math.PI * 2; a += 0.06283185307179587) {
                    double x = Math.cos(a) * r;
                    double z = Math.sin(a) * r;
                    Location pointLocation = location.clone().add(x, y, z);
                    Block block = pointLocation.getBlock();
                    Material material = block.getType();
                    if (Tag.WOOL.isTagged((Keyed)material)) {
                        this.processBlock(caster, block, (Tag<Material>)Tag.WOOL);
                        continue;
                    }
                    if (SlimefunTag.TERRACOTTA.isTagged(material)) {
                        this.processBlock(caster, block, (Tag<Material>)SlimefunTag.TERRACOTTA);
                        continue;
                    }
                    if (CrystaTag.GLAZED_TERRACOTTA.isTagged(material)) {
                        this.processBlock(caster, block, CrystaTag.GLAZED_TERRACOTTA);
                        continue;
                    }
                    if (SlimefunTag.CONCRETE_POWDERS.isTagged(material)) {
                        this.processBlock(caster, block, (Tag<Material>)SlimefunTag.CONCRETE_POWDERS);
                        continue;
                    }
                    if (CrystaTag.CONCRETE_BLOCKS.isTagged(material)) {
                        this.processBlock(caster, block, CrystaTag.CONCRETE_BLOCKS);
                        continue;
                    }
                    if (!Tag.CARPETS.isTagged((Keyed)material)) continue;
                    this.processBlock(caster, block, (Tag<Material>)Tag.CARPETS);
                }
                ++i;
            }
        }
        this.processEntities(location, caster);
    }

    @ParametersAreNonnullByDefault
    private void processBlock(UUID caster, Block block, Tag<Material> tag) {
        if (GeneralUtils.hasPermission(caster, block, Interaction.PLACE_BLOCK)) {
            List list = tag.getValues().stream().toList();
            int randomValue = ThreadLocalRandom.current().nextInt(0, list.size());
            block.setType((Material)list.get(randomValue));
        }
    }

    @ParametersAreNonnullByDefault
    private void processEntities(Location location, UUID caster) {
        Collection entities = location.getWorld().getNearbyEntities(location, 0.5, 0.5, 0.5);
        for (Entity entity : entities) {
            if (!(entity instanceof Colorable) || !GeneralUtils.hasPermission(caster, entity.getLocation(), Interaction.INTERACT_ENTITY)) continue;
            int randomValue = ThreadLocalRandom.current().nextInt(0, (int)Arrays.stream(DyeColor.values()).count());
            ((Colorable)entity).setColor(DyeColor.values()[randomValue]);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.ALCHEMICAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Turns colorful things into other colorful", "things."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "BOBULATE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CYAN_WOOL;
    }
}

