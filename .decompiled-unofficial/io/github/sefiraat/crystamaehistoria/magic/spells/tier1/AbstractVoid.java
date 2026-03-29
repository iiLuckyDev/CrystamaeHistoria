/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class AbstractVoid
extends Spell {
    public AbstractVoid() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(25.0, false, 7.0, true, 10, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    private void cast(CastInformation castInformation) {
        Location castLocation = castInformation.getCastLocation();
        double range = this.getRange(castInformation);
        ArrayList<Location> locationList = new ArrayList<Location>();
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, range, range)) {
            if (!(entity instanceof LivingEntity) || entity instanceof ArmorStand || !GeneralUtils.hasPermission(castInformation.getCaster(), entity.getLocation(), Interaction.INTERACT_ENTITY)) continue;
            locationList.add(entity.getLocation());
            entities.add(entity);
        }
        for (Entity entity : entities) {
            if (locationList.isEmpty()) continue;
            Location location = (Location)locationList.get(ThreadLocalRandom.current().nextInt(locationList.size()));
            entity.teleport(location);
            locationList.remove(location);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.VOID, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Swaps everything around!"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "ABSTRACT_VOID";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.GREEN_DYE;
    }
}

