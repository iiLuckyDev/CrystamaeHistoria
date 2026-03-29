/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.managers;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.listeners.ArmorStandInteract;
import io.github.sefiraat.crystamaehistoria.listeners.BlockRemovalListener;
import io.github.sefiraat.crystamaehistoria.listeners.CrystaDowngradeListener;
import io.github.sefiraat.crystamaehistoria.listeners.CrystalBreakListener;
import io.github.sefiraat.crystamaehistoria.listeners.DisplayItemListener;
import io.github.sefiraat.crystamaehistoria.listeners.EndermanInhibitorListener;
import io.github.sefiraat.crystamaehistoria.listeners.MaintenanceListener;
import io.github.sefiraat.crystamaehistoria.listeners.MiscListener;
import io.github.sefiraat.crystamaehistoria.listeners.MobCandleListener;
import io.github.sefiraat.crystamaehistoria.listeners.NetherDrainingListener;
import io.github.sefiraat.crystamaehistoria.listeners.PhilosophersSprayListener;
import io.github.sefiraat.crystamaehistoria.listeners.PoseChangerListener;
import io.github.sefiraat.crystamaehistoria.listeners.RefractingLensListener;
import io.github.sefiraat.crystamaehistoria.listeners.SatchelListener;
import io.github.sefiraat.crystamaehistoria.listeners.SpellCastListener;
import io.github.sefiraat.crystamaehistoria.listeners.SpellEffectListener;
import io.github.sefiraat.crystamaehistoria.listeners.ThaumaturgicSaltsListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerManager {
    public ListenerManager() {
        this.addListener(new ArmorStandInteract());
        this.addListener(new SpellCastListener());
        this.addListener(new SpellEffectListener());
        this.addListener(new CrystalBreakListener());
        this.addListener(new BlockRemovalListener());
        this.addListener(new MaintenanceListener());
        this.addListener(new RefractingLensListener());
        this.addListener(new ThaumaturgicSaltsListener());
        this.addListener(new CrystaDowngradeListener());
        this.addListener(new NetherDrainingListener());
        this.addListener(new SatchelListener());
        this.addListener(new EndermanInhibitorListener());
        this.addListener(new MobCandleListener());
        this.addListener(new DisplayItemListener());
        this.addListener(new PoseChangerListener());
        this.addListener(new PhilosophersSprayListener());
        this.addListener(new MiscListener());
    }

    private void addListener(Listener listener) {
        CrystamaeHistoria.getPluginManager().registerEvents(listener, (Plugin)CrystamaeHistoria.getInstance());
    }
}

