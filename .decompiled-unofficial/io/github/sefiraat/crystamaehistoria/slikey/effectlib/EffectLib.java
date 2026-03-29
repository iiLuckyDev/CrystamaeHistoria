/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.HandlerList
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import java.util.List;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class EffectLib
extends JavaPlugin {
    private static EffectLib instance;

    public EffectLib() {
        instance = this;
    }

    public void onEnable() {
    }

    public void onDisable() {
        EffectManager.disposeAll();
        HandlerList.unregisterAll((Plugin)this);
    }

    public List<EffectManager> getEffectManagers() {
        return EffectManager.getManagers();
    }

    public static EffectLib instance() {
        return instance;
    }
}

