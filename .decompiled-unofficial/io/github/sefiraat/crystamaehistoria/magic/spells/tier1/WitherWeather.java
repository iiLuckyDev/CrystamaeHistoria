/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.WitherSkeleton
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.persistence.PersistentDataHolder;

public class WitherWeather
extends Spell {
    public WitherWeather() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5.0, true, 0.0, false, 20, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        for (int i = 0; i < castInformation.getStaveLevel(); ++i) {
            Location spawnLocation = location.clone().add(ThreadLocalRandom.current().nextDouble(3.0), 0.0, ThreadLocalRandom.current().nextDouble(3.0));
            WitherSkeleton witherSkeleton = (WitherSkeleton)spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.WITHER_SKELETON);
            witherSkeleton.setTarget((LivingEntity)castInformation.getCasterAsPlayer());
            PersistentDataAPI.setBoolean((PersistentDataHolder)witherSkeleton, (NamespacedKey)Keys.PDC_IS_WEATHER_WITHER, (boolean)true);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HUMAN, StoryType.ANIMAL, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Summons 1-5 Wither Skeletons that will", "focus on the caster. These spawns guarantee", "a skull drop."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "WITHER_WEATHER";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.WITHER_SKELETON_SKULL;
    }
}

