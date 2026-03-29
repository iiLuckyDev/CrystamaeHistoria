/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public final class StringParser {
    private StringParser() {
    }

    public static BufferedImage stringToBufferedImage(Font font, String s) {
        BufferedImage img = new BufferedImage(1, 1, 6);
        Graphics g = img.getGraphics();
        g.setFont(font);
        FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
        Rectangle2D rect = font.getStringBounds(s, frc);
        g.dispose();
        img = new BufferedImage((int)Math.ceil(rect.getWidth()), (int)Math.ceil(rect.getHeight()), 6);
        g = img.getGraphics();
        g.setColor(Color.black);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int x = 0;
        int y = fm.getAscent();
        g.drawString(s, x, y);
        g.dispose();
        return img;
    }
}

