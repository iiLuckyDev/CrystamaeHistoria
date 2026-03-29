/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.StringParser;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.VectorUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Objects;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class TextEffect
extends Effect {
    public Particle particle = Particle.FIREWORKS_SPARK;
    public String text = "Text";
    public boolean invert = false;
    public int stepX = 1;
    public int stepY = 1;
    public float size = 0.2f;
    public boolean realtime = false;
    public Font font = new Font("Tahoma", 0, 16);
    protected BufferedImage image = null;
    private String lastParsedText = null;
    private Font lastParsedFont = null;

    public TextEffect(EffectManager effectManager) {
        super(effectManager);
        this.type = EffectType.REPEATING;
        this.period = 40;
        this.iterations = 20;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void onRun() {
        if (this.font == null) {
            this.cancel();
            return;
        }
        Location location = this.getLocation();
        try {
            if (this.image == null || this.shouldRecalculateImage()) {
                this.lastParsedText = this.text;
                this.lastParsedFont = this.font;
                this.image = StringParser.stringToBufferedImage(this.lastParsedFont, this.lastParsedText);
            }
            for (int y = 0; y < this.image.getHeight(); y += this.stepY) {
                for (int x = 0; x < this.image.getWidth(); x += this.stepX) {
                    int clr = this.image.getRGB(x, y);
                    if (!this.invert && Color.black.getRGB() != clr || this.invert && Color.black.getRGB() == clr) continue;
                    Vector v = new Vector((float)this.image.getWidth() / 2.0f - (float)x, (float)this.image.getHeight() / 2.0f - (float)y, 0.0f).multiply(this.size);
                    VectorUtils.rotateAroundAxisY(v, -location.getYaw() * ((float)Math.PI / 180));
                    this.display(this.particle, location.add(v));
                    location.subtract(v);
                }
            }
        }
        catch (Exception ex) {
            this.cancel();
        }
    }

    private boolean shouldRecalculateImage() {
        if (!this.realtime) {
            return false;
        }
        return !Objects.equals(this.lastParsedText, this.text) || !Objects.equals(this.lastParsedFont, this.font);
    }
}

