/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.FireworkEffect
 *  org.bukkit.FireworkEffect$Builder
 *  org.bukkit.FireworkEffect$Type
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Firework
 *  org.bukkit.inventory.meta.FireworkMeta
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

public class BigBangEffect
extends Effect {
    public FireworkEffect.Type fireworkType = FireworkEffect.Type.BURST;
    public Color color2 = Color.ORANGE;
    public Color color3 = Color.BLACK;
    public Color fadeColor = Color.BLACK;
    public int intensity = 2;
    public float radius = 2.0f;
    public int explosions = 10;
    public int soundInterval = 5;
    public Sound sound = Sound.ENTITY_GENERIC_EXPLODE;
    public float soundVolume = 100.0f;
    public float soundPitch = 1.0f;
    protected int step = 0;
    protected FireworkEffect firework;

    public BigBangEffect(EffectManager effectManager) {
        super(effectManager);
        this.color = Color.RED;
        this.type = EffectType.REPEATING;
        this.period = 2;
        this.iterations = 400;
        this.asynchronous = false;
    }

    @Override
    public void reset() {
        this.step = 0;
    }

    @Override
    public void onRun() {
        if (this.firework == null) {
            FireworkEffect.Builder b = FireworkEffect.builder().with(this.fireworkType);
            b.withColor(this.color).withColor(this.color2).withColor(this.color3);
            b.withFade(this.fadeColor);
            b.trail(true);
            this.firework = b.build();
        }
        Location location = this.getLocation();
        for (int i = 0; i < this.explosions; ++i) {
            Vector v = RandomUtils.getRandomVector().multiply(this.radius);
            this.detonate(location, v);
            if (this.soundInterval == 0 || this.step % this.soundInterval != 0) continue;
            location.getWorld().playSound(location, this.sound, this.soundVolume, this.soundPitch);
        }
        ++this.step;
    }

    protected void detonate(Location location, Vector v) {
        Firework fw = (Firework)location.getWorld().spawnEntity(location.add(v), EntityType.FIREWORK);
        location.subtract(v);
        FireworkMeta meta = fw.getFireworkMeta();
        meta.setPower(0);
        for (int i = 0; i < this.intensity; ++i) {
            meta.addEffect(this.firework);
        }
        fw.setFireworkMeta(meta);
        fw.detonate();
    }
}

