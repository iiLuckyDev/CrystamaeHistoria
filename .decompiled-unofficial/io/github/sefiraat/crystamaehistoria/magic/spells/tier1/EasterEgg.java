/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.entity.Entity
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.DisplayItem;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class EasterEgg
extends Spell {
    public EasterEgg() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(600.0, false, 5.0, false, 1, true).makeInstantSpell(this::cast);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation().add(0.0, 1.0, 0.0);
        for (String string : ThemeType.getEggNames()) {
            List list = CrystaTag.SPAWN_EGGS.getValues().stream().toList();
            int randomValue = ThreadLocalRandom.current().nextInt(0, list.size());
            DisplayItem displayItem = new DisplayItem(new ItemStack((Material)list.get(randomValue)), location, string, item -> {
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, 1.0f);
                ParticleUtils.displayParticleEffect((Entity)item, 0.3, 4, dustOptions);
            });
            displayItem.registerRemoval(5000L);
            displayItem.setVelocity(new Vector(ThreadLocalRandom.current().nextDouble(-0.5, 0.6), 0.5, ThreadLocalRandom.current().nextDouble(-0.5, 0.6)));
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.ANIMAL, StoryType.CELESTIAL, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"???"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "EASTER_EGG";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.ZOGLIN_SPAWN_EGG;
    }
}

