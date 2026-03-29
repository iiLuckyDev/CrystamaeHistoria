/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.md_5.bungee.api.ChatColor
 */
package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import javax.annotation.Nonnull;
import lombok.Generated;
import net.md_5.bungee.api.ChatColor;

public final class TextUtils {
    @Nonnull
    public static String toTitleCase(@Nonnull String string) {
        return TextUtils.toTitleCase(string, true);
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string, boolean delimiterToSpace) {
        return TextUtils.toTitleCase(string, delimiterToSpace, " _'-/");
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string, boolean delimiterToSpace, @Nonnull String delimiters) {
        StringBuilder builder = new StringBuilder();
        boolean capNext = true;
        for (char character : string.toCharArray()) {
            character = capNext ? Character.toUpperCase(character) : Character.toLowerCase(character);
            builder.append(character);
            capNext = delimiters.indexOf(character) >= 0;
        }
        String built = builder.toString();
        if (delimiterToSpace) {
            int space = 32;
            for (char c : delimiters.toCharArray()) {
                built = built.replace(c, ' ');
            }
        }
        return built;
    }

    @Nonnull
    public static String getLoreDivider() {
        ChatColor c = ThemeType.PASSIVE.getColor();
        return String.valueOf(c) + "-".repeat(25);
    }

    @Generated
    private TextUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

