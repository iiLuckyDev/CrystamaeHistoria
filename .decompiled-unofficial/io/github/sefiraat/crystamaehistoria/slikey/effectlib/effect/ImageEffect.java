/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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

public class ImageEffect
extends BaseImageEffect {
    public boolean invert = false;

    public ImageEffect(EffectManager effectManager) {
        super(effectManager);
    }

    @Override
    protected void display(BufferedImage image, Vector v, Location location, int pixel) {
        if (!this.invert && Color.black.getRGB() != pixel) {
            return;
        }
        if (this.invert && Color.black.getRGB() == pixel) {
            return;
        }
        this.display(this.particle, location.add(v));
        location.subtract(v);
    }
}

