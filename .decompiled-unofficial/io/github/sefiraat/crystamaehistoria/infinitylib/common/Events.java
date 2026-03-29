/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Event
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.common;

import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

@ParametersAreNonnullByDefault
public final class Events
implements Listener {
    private static final Listener LISTENER = new Events();

    public static <T extends Event> T call(T event) {
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }

    public static void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, AbstractAddon.instance());
    }

    public static <T extends Event> void addHandler(Class<T> eventClass, EventPriority priority, boolean ignoreCancelled, Consumer<T> handler) {
        Bukkit.getPluginManager().registerEvent(eventClass, LISTENER, priority, (listener, event) -> handler.accept(event), AbstractAddon.instance(), ignoreCancelled);
    }

    @Generated
    private Events() {
    }
}

