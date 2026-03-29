/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.NamespacedKey
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public final class Keys {
    public static final String BS_CP_WORKING_ON = "BS_CP_WORKING_ON";
    public static final String BS_CP_ACTIVE_PLAYER = "BS_CP_ACTIVE_PLAYER";
    public static final String BS_CP_STORIED = "BS_CP_STORIED";
    public static final String BS_CP_STORIES = "BS_CP_STORIES";
    public static final String JS_S_AVAILABLE_STORIES = "JS_S_AS";
    public static final String JS_S_TIER = "JS_S_T";
    public static final String PANEL_STAND_PREFIX = "CH_PANEL_";
    public static final NamespacedKey GUIDE_ONLY = Keys.newKey("guide");
    public static final NamespacedKey GUIDE_MAKE_SPELL = Keys.newKey("guide_make_spell");
    public static final NamespacedKey GUIDE_RECHARGE_SPELL = Keys.newKey("guide_recharge_spell");
    public static final NamespacedKey GUIDE_STAVE_CONFIGURATOR = Keys.newKey("guide_stave");
    public static final NamespacedKey GUIDE_LIQUEFACTION = Keys.newKey("guide_liquefaction");
    public static final NamespacedKey GUIDE_REALISATION = Keys.newKey("guide_realisation");
    public static final NamespacedKey REALISATION_ALTAR_RECIPE_TYPE = Keys.newKey("r_d_c");
    public static final NamespacedKey REALISATION_ALTAR_RECIPE_SIGIL = Keys.newKey("r_d_s");
    public static final NamespacedKey LIQUEFACTION_CRAFTING_RECIPE_TYPE = Keys.newKey("l_d_c");
    public static final NamespacedKey LIQUEFACTION_SPELL_RECIPE_TYPE = Keys.newKey("l_d_s");
    public static final NamespacedKey NETHER_DRAINING_RECIPE_TYPE = Keys.newKey("nether_draining");
    public static final NamespacedKey PDC_IS_STORIED = Keys.newKey("is_s");
    public static final NamespacedKey PDC_POTENTIAL_STORIES = Keys.newKey("s_pot");
    public static final NamespacedKey PDC_CURRENT_NUMBER_OF_STORIES = Keys.newKey("s_cur_n");
    public static final NamespacedKey PDC_STORIES = Keys.newKey("s_list");
    public static final NamespacedKey PDC_PLATE_STORAGE = Keys.newKey("plt");
    public static final NamespacedKey PDC_STAVE_STORAGE = Keys.newKey("stv");
    public static final NamespacedKey PDC_SATCHEL_STORAGE = Keys.newKey("satchel");
    public static final NamespacedKey PDC_ON_COOLDOWN = Keys.newKey("cooldown");
    public static final NamespacedKey PDC_PAINT_TYPE = Keys.newKey("paint_type");
    public static final NamespacedKey PDC_IS_GILDED = Keys.newKey("gilded");
    public static final NamespacedKey STORY_ID = Keys.newKey("s_id");
    public static final NamespacedKey STORY_RARITY = Keys.newKey("s_r");
    public static final NamespacedKey STORY_TYPE = Keys.newKey("s_t");
    public static final NamespacedKey STORY_IS_GILDED = Keys.newKey("s_g");
    public static final NamespacedKey PLATE_TIER = Keys.newKey("p_t");
    public static final NamespacedKey PLATE_SPELL = Keys.newKey("p_s");
    public static final NamespacedKey PLATE_CHARGES = Keys.newKey("p_c");
    public static final NamespacedKey PLATE_COOLDOWN = Keys.newKey("p_cd");
    public static final NamespacedKey STAVE_SLOT = Keys.newKey("sv_s");
    public static final NamespacedKey STAVE_PLATE = Keys.newKey("sv_p");
    public static final NamespacedKey PDC_IS_DISPLAY_STAND = Keys.newKey("a_dpy");
    public static final NamespacedKey PDC_IS_INVULNERABLE = Keys.newKey("invul");
    public static final NamespacedKey PDC_IS_WEATHER_WITHER = Keys.newKey("weather");
    public static final NamespacedKey PDC_IS_SPAWN_OWNER = Keys.newKey("owner");
    public static final NamespacedKey PDC_IS_DISPLAY_ITEM = Keys.newKey("di");
    public static final NamespacedKey RESOLUTION_CRYSTAL_MAP = Keys.newKey("c_r_c");
    public static final NamespacedKey RESOLUTION_RARITY_MAP = Keys.newKey("c_r_r");
    public static final NamespacedKey RESOLUTION_STORY_MAP = Keys.newKey("c_r_s");
    public static final NamespacedKey RESOLUTION_STORY_LOCATION = Keys.newKey("c_r_l");
    public static final NamespacedKey RESOLUTION_STORY_WORLD = Keys.newKey("c_r_w");

    private Keys() {
        throw new IllegalStateException("Utility Class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull String value) {
        return new NamespacedKey((Plugin)CrystamaeHistoria.getInstance(), value);
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof Keys;
    }

    @Generated
    public int hashCode() {
        boolean result = true;
        return 1;
    }

    @Generated
    public String toString() {
        return "Keys()";
    }
}

