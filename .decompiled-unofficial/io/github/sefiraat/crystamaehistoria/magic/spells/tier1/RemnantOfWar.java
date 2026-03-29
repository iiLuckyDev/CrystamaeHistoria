/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Zombie
 *  org.bukkit.inventory.EntityEquipment
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.BoringGoal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class RemnantOfWar
extends Spell {
    public RemnantOfWar() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 0.0, false, 25, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        Location spawnLocation = location.clone().add(ThreadLocalRandom.current().nextDouble(-3.0, 3.0), 0.0, ThreadLocalRandom.current().nextDouble(-3.0, 3.0));
        MagicSummon magicSummon = SpellUtils.summonTemporaryMob(EntityType.ZOMBIE, caster, spawnLocation, new BoringGoal(caster), 180, this::onTick);
        Zombie zombie = (Zombie)magicSummon.getMob();
        this.gearZombie(zombie, castInformation.getStaveLevel());
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicSummon magicSummon) {
        ParticleUtils.displayParticleEffect((Entity)magicSummon.getMob(), Particle.SOUL, 1.0, 1);
    }

    @ParametersAreNonnullByDefault
    public void gearZombie(Zombie zombie, int tier) {
        ItemStack boots;
        ItemStack leggings;
        ItemStack chestplate;
        ItemStack helmet;
        ItemStack sword = switch (tier) {
            case 1 -> {
                helmet = new ItemStack(Material.LEATHER_HELMET);
                chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                boots = new ItemStack(Material.LEATHER_BOOTS);
                yield new ItemStack(Material.WOODEN_SWORD);
            }
            case 2 -> {
                helmet = new ItemStack(Material.CHAINMAIL_HELMET);
                chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                boots = new ItemStack(Material.CHAINMAIL_BOOTS);
                yield new ItemStack(Material.STONE_SWORD);
            }
            case 3 -> {
                helmet = new ItemStack(Material.IRON_HELMET);
                chestplate = new ItemStack(Material.IRON_CHESTPLATE);
                leggings = new ItemStack(Material.IRON_LEGGINGS);
                boots = new ItemStack(Material.IRON_BOOTS);
                yield new ItemStack(Material.IRON_SWORD);
            }
            case 4 -> {
                helmet = new ItemStack(Material.DIAMOND_HELMET);
                chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                boots = new ItemStack(Material.DIAMOND_BOOTS);
                yield new ItemStack(Material.DIAMOND_SWORD);
            }
            case 5 -> {
                helmet = new ItemStack(Material.NETHERITE_HELMET);
                chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
                leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
                boots = new ItemStack(Material.NETHERITE_BOOTS);
                yield new ItemStack(Material.NETHERITE_SWORD);
            }
            default -> throw new IllegalStateException("Unexpected value: " + tier);
        };
        EntityEquipment entityEquipment = zombie.getEquipment();
        entityEquipment.setHelmet(helmet);
        entityEquipment.setChestplate(chestplate);
        entityEquipment.setLeggings(leggings);
        entityEquipment.setBoots(boots);
        entityEquipment.setItemInMainHand(sword);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HISTORICAL, StoryType.HUMAN, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a remnant of war from the dead", "to your side. Their gear scales with tier."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "REMNANT_OF_WAR";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.ZOMBIE_SPAWN_EGG;
    }
}

