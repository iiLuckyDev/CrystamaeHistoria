/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.BaseImageEffect;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class ColoredImageEffect
extends BaseImageEffect {
    public ColoredImageEffect(EffectManager effectManager) {
        super(effectManager);
    }

    @Override
    protected void display(BufferedImage image, Vector v, Location location, int pixel) {
        Color color = new Color(pixel);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        this.display(this.particle, location.add(v), org.bukkit.Color.fromRGB((int)r, (int)g, (int)b));
        location.subtract(v);
    }
}

