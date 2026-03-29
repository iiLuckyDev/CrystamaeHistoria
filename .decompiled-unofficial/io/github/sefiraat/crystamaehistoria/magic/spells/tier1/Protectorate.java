/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataHolder;

public class Protectorate
extends Spell {
    public Protectorate() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40.0, true, 7.0, false, 25, true).makeTickingSpell(this::cast, 10, true, 20, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCasterAsPlayer().getLocation().clone().add(0.0, 1.0, 0.0);
        double range = this.getRange(castInformation);
        double effectRange = range * 0.75;
        int density = 30;
        for (double height = 0.0; height <= Math.PI; height += 0.10471975511965977) {
            double r = range * Math.sin(height);
            double y = range * Math.cos(height);
            for (double a = 0.0; a < Math.PI * 2; a += 0.10471975511965977) {
                double x = Math.cos(a) * r;
                double z = Math.sin(a) * r;
                Location point = location.clone().add(x, y, z);
                ParticleUtils.displayParticleEffect(point, Particle.ELECTRIC_SPARK, 0.1, 1);
            }
        }
        for (Entity entity : location.getWorld().getNearbyEntities(location, effectRange, effectRange, effectRange)) {
            if (!(entity instanceof LivingEntity)) continue;
            PersistentDataAPI.setLong((PersistentDataHolder)entity, (NamespacedKey)Keys.PDC_IS_INVULNERABLE, (long)(System.currentTimeMillis() + 1050L));
            ParticleUtils.displayParticleEffect(entity, Particle.HAPPY_VILLAGER, 1.0, 3);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HISTORICAL, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons a shield that follows the caster", "preventing damage to those inside."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "PROTECTORATE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.SHIELD;
    }
}

