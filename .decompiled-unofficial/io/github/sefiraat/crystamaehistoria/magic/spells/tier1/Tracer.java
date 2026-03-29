/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.entity.Ambient
 *  org.bukkit.entity.Animals
 *  org.bukkit.entity.Boss
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Monster
 *  org.bukkit.entity.NPC
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.WaterMob
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import com.google.common.base.Preconditions;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Boss;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.WaterMob;

public class Tracer
extends Spell {
    protected static final Map<String, Color> COLOR_MAP = new LinkedHashMap<String, Color>();

    public Tracer() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10.0, false, 40.0, true, 10, true).makeTickingSpell(this::onTick, 10, true, 20, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Player player = castInformation.getCasterAsPlayer();
        Location location = player.getLocation().add(0.0, 1.5, 0.0);
        Collection entityList = location.getNearbyLivingEntities(this.getRange(castInformation));
        for (LivingEntity livingEntity : entityList) {
            if (livingEntity.getUniqueId() == castInformation.getCaster()) continue;
            Particle.DustOptions dustOptions = null;
            if (livingEntity instanceof Player) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("player"), 1.0f);
            } else if (livingEntity instanceof NPC) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("npc"), 1.0f);
            } else if (livingEntity instanceof Boss) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("boss"), 1.0f);
            } else if (livingEntity instanceof Monster) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("monster"), 1.0f);
            } else if (livingEntity instanceof WaterMob) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("water"), 1.0f);
            } else if (livingEntity instanceof Animals) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("animal"), 1.0f);
            } else {
                if (!(livingEntity instanceof Ambient)) continue;
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("ambient"), 1.0f);
            }
            Preconditions.checkNotNull((Object)dustOptions, (Object)("Dust Options is null, something be bad: " + livingEntity.getType().name()));
            ParticleUtils.drawLine(dustOptions, location, livingEntity.getLocation(), 1.0);
        }
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HISTORICAL, StoryType.ANIMAL);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Shows you all nearby living things", "color coded by type."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "TRACER";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.LEAD;
    }

    static {
        COLOR_MAP.put("player", Color.fromRGB((int)255, (int)255, (int)255));
        COLOR_MAP.put("npc", Color.fromRGB((int)240, (int)180, (int)40));
        COLOR_MAP.put("boss", Color.fromRGB((int)255, (int)25, (int)30));
        COLOR_MAP.put("monster", Color.fromRGB((int)190, (int)25, (int)30));
        COLOR_MAP.put("water", Color.fromRGB((int)45, (int)45, (int)220));
        COLOR_MAP.put("animal", Color.fromRGB((int)50, (int)230, (int)30));
        COLOR_MAP.put("ambient", Color.fromRGB((int)150, (int)150, (int)150));
    }
}

