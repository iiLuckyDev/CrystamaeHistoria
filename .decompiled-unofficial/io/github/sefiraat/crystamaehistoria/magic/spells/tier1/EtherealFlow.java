/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;

public class EtherealFlow
extends Spell {
    public EtherealFlow() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(720.0, true, 0.0, false, 10, false).makeTickingSpell(this::onTick, 30, true, 1, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        location.getWorld().setTime(location.getWorld().getTime() + 50L * (long)castInformation.getStaveLevel());
        location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 1);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.HISTORICAL, StoryType.VOID, StoryType.PHILOSOPHICAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Fast-forwards time"};
    }

    @Override
    @Nonnull
    public String getId() {
        return "ETHEREAL_FLOW";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.CLOCK;
    }
}

