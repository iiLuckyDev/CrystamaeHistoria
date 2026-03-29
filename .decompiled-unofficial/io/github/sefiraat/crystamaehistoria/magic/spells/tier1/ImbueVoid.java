/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.potion.PotionEffectType
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.BoringGoal;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

public class ImbueVoid
extends Spell {
    public ImbueVoid() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(240.0, false, 3.0, false, 30, true).makeTickingSpell(this::onTick, 5, false, 40, false).addPositiveEffect(PotionEffectType.SLOWNESS, 99, 5).addPositiveEffect(PotionEffectType.MINING_FATIGUE, 99, 5);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        double range = this.getRange(castInformation);
        Location location = castInformation.getCastLocation().clone().add(ThreadLocalRandom.current().nextDouble(-range, range), 0.0, ThreadLocalRandom.current().nextDouble(-range, range));
        for (int i = 0; i < castInformation.getStaveLevel(); ++i) {
            SpellUtils.summonTemporaryMob(EntityType.ENDERMITE, castInformation.getCaster(), location, new BoringGoal(castInformation.getCaster()), 5);
        }
        this.applyPositiveEffects((LivingEntity)castInformation.getCasterAsPlayer(), castInformation);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HUMAN, StoryType.VOID, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Spawns a hoard of endermites to fight on", "you behalf. During this spell you cannot", "move or attack."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "IMBUE_VOID";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.ENDERMITE_SPAWN_EGG;
    }
}

