/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class Animaniacs
extends Spell {
    protected static final List<EntityType> CONVERTIBLE_LIST = Arrays.asList(EntityType.COW, EntityType.CHICKEN, EntityType.SHEEP, EntityType.COD, EntityType.SALMON, EntityType.TROPICAL_FISH, EntityType.BAT, EntityType.BEE, EntityType.DONKEY, EntityType.WOLF, EntityType.FOX, EntityType.SNOW_GOLEM, EntityType.TURTLE, EntityType.RABBIT, EntityType.PARROT, EntityType.CAT, EntityType.OCELOT, EntityType.HORSE, EntityType.MOOSHROOM, EntityType.AXOLOTL, EntityType.PUFFERFISH, EntityType.PIG, EntityType.DOLPHIN, EntityType.GOAT, EntityType.LLAMA, EntityType.PANDA, EntityType.POLAR_BEAR);

    public Animaniacs() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60.0, true, 0.0, false, 10, false).makeProjectileSpell(this::fireProjectile, 5.0, true, 0.0, false).makeProjectileVsBlockSpell(this::projectileHit).makeProjectileVsEntitySpell(this::projectileHit);
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
        for (LivingEntity entity : this.getTargets(castInformation, 7.0, true)) {
            Location entityLocation = entity.getLocation();
            EntityType entityType = entity.getType();
            if (!CONVERTIBLE_LIST.contains(entityType) || !GeneralUtils.hasPermission(castInformation.getCaster(), entityLocation, Interaction.INTERACT_ENTITY)) continue;
            EntityType convertTo = CONVERTIBLE_LIST.get(ThreadLocalRandom.current().nextInt(CONVERTIBLE_LIST.size()));
            String name = entity.getCustomName();
            entity.remove();
            Entity newEntity = entityLocation.getWorld().spawnEntity(entityLocation, convertTo, true);
            newEntity.setCustomName(name);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.ANIMAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Disjoints the reality of nearby creatures."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "ANIMANIACS";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.AXOLOTL_SPAWN_EGG;
    }
}

