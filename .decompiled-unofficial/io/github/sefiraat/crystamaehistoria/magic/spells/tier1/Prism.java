/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.potion.PotionEffectType
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
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.potion.PotionEffectType;

public class Prism
extends Spell {
    public Prism() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(25.0, true, 0.0, false, 75, true).makeInstantSpell(this::cast).makeEffectingSpell(true, true).addPositiveEffect(PotionEffectType.ABSORPTION, 1, 30).addPositiveEffect(PotionEffectType.CONDUIT_POWER, 1, 30).addPositiveEffect(PotionEffectType.RESISTANCE, 1, 30).addPositiveEffect(PotionEffectType.DOLPHINS_GRACE, 1, 30).addPositiveEffect(PotionEffectType.HASTE, 1, 30).addPositiveEffect(PotionEffectType.HEALTH_BOOST, 1, 30).addPositiveEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 1, 30).addPositiveEffect(PotionEffectType.STRENGTH, 1, 30).addPositiveEffect(PotionEffectType.INVISIBILITY, 1, 30).addPositiveEffect(PotionEffectType.JUMP_BOOST, 1, 30).addPositiveEffect(PotionEffectType.LUCK, 1, 30).addPositiveEffect(PotionEffectType.NIGHT_VISION, 1, 30).addPositiveEffect(PotionEffectType.REGENERATION, 1, 30).addPositiveEffect(PotionEffectType.SATURATION, 1, 30).addPositiveEffect(PotionEffectType.SPEED, 1, 30).addPositiveEffect(PotionEffectType.WATER_BREATHING, 1, 30);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        if (PersistentDataAPI.getBoolean((PersistentDataHolder)player, (NamespacedKey)Keys.newKey("ANTIPRISM"))) {
            player.damage(200.0);
        }
        PersistentDataAPI.setBoolean((PersistentDataHolder)player, (NamespacedKey)Keys.newKey("PRISM"), (boolean)true);
        this.applyPositiveEffects((LivingEntity)player, castInformation);
        ParticleUtils.displayParticleEffect((Entity)player, Particle.EFFECT, 2.0, 20);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ALCHEMICAL, StoryType.HISTORICAL, StoryType.HUMAN);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Applies a myriad of positive effects to", "the caster."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "PRISM";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.POTION;
    }
}

