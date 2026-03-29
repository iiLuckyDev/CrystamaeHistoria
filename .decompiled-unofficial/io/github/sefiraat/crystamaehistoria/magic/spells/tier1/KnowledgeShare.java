/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.ExperienceOrb
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

public class KnowledgeShare
extends Spell {
    private static final int BASE_EXP = 50;
    private static final int EXP_PER_ORB = 5;

    public KnowledgeShare() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(360.0, true, 15.0, false, 10, true).makeTickingSpell(this::onTick, 5, false, 2, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer((UUID)castInformation.getCaster());
        if (caster != null) {
            int casterExp = caster.getTotalExperience();
            int exp = Math.min(casterExp, 50 * castInformation.getStaveLevel());
            int remainder = exp % 5;
            int waves = (exp - remainder) / 5;
            for (int i = 0; i < waves; ++i) {
                double xOffset = ThreadLocalRandom.current().nextDouble(-this.getRange(castInformation), this.getRange(castInformation));
                double zOffset = ThreadLocalRandom.current().nextDouble(-this.getRange(castInformation), this.getRange(castInformation));
                Location location = caster.getLocation().clone().add(xOffset, 20.0, zOffset);
                ExperienceOrb experienceOrb = (ExperienceOrb)caster.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
                experienceOrb.setExperience(5);
                location.getWorld().playEffect(location, Effect.DRIPPING_DRIPSTONE, 1);
                caster.giveExp(-5);
            }
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HISTORICAL, StoryType.HUMAN, StoryType.CELESTIAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Removes EXP from the caster and makes", "it rain down from the sky around them."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "KNOWLEDGE_SHARE";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.EXPERIENCE_BOTTLE;
    }
}

