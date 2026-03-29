/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Chaos
extends Spell {
    public Chaos() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10.0, true, 6.0, false, 40, true).makeDamagingSpell(1.0, false, 0.2, false).makeProjectileSpell(this::cast, 0.0, false, 0.0, false).makeProjectileVsEntitySpell(this::onHitEntity).makeProjectileVsBlockSpell(this::onHitBlock).makeTickingSpell(this::cast, 10, true, 5, false);
        this.setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer((UUID)castInformation.getCaster());
        int shots = 10;
        double minRadius = 1.0;
        double maxRadius = this.getRange(castInformation);
        Location location = caster.getLocation();
        Vector startVector = new Vector(0.0, 1.5, 1.0);
        double alessioMath = Math.PI / 180;
        for (double a = 0.0; a < Math.PI * 2; a += 0.3141592653589793) {
            double radius = ThreadLocalRandom.current().nextDouble(1.0, maxRadius);
            double y = radius * Math.cos(a);
            double x = radius * Math.sin(a);
            Vector pointVector = startVector.clone().add(new Vector(x, y, 0.0)).rotateAroundY(-((double)location.getYaw() * (Math.PI / 180)));
            Location pointLocation = location.clone().add(pointVector);
            Block block = pointLocation.getBlock();
            if (pointLocation.getBlock().isEmpty()) {
                MagicProjectile projectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SPECTRAL_ARROW, pointLocation, this::onTick);
                projectile.getProjectile().setGravity(false);
                projectile.setVelocity(caster.getEyeLocation().getDirection(), 1.0);
                continue;
            }
            GeneralUtils.tryBreakBlock(castInformation.getCaster(), block);
        }
    }

    @ParametersAreNonnullByDefault
    public void onHitEntity(CastInformation castInformation) {
        GeneralUtils.damageEntity(castInformation.getMainTarget(), castInformation.getCaster(), this.getDamage(castInformation));
    }

    @ParametersAreNonnullByDefault
    public void onHitBlock(CastInformation castInformation) {
        GeneralUtils.tryBreakBlock(castInformation.getCaster(), castInformation.getHitBlock());
    }

    @ParametersAreNonnullByDefault
    public void onTick(MagicProjectile magicProjectile) {
        ParticleUtils.displayParticleEffect((Entity)magicProjectile.getProjectile(), Particle.GLOW, 0.2, 1);
    }

    @Override
    @Nonnull
    public RecipeSpell getRecipe() {
        return new RecipeSpell(1, StoryType.MECHANICAL, StoryType.HISTORICAL, StoryType.VOID);
    }

    @Override
    @Nonnull
    public String[] getLore() {
        return new String[]{"Spawns a forward-firing rain of", "chaos breaking blocks and hurting", "all living things."};
    }

    @Override
    @Nonnull
    public String getId() {
        return "CHAOS";
    }

    @Override
    @Nonnull
    public Material getMaterial() {
        return Material.SOUL_LANTERN;
    }
}

