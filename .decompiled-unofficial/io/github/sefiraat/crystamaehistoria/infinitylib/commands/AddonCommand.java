/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.command.TabExecutor
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerCommandPreprocessEvent
 *  org.bukkit.event.server.ServerCommandEvent
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.AliasesCommand;
import io.github.sefiraat.crystamaehistoria.infinitylib.commands.InfoCommand;
import io.github.sefiraat.crystamaehistoria.infinitylib.commands.ParentCommand;
import io.github.sefiraat.crystamaehistoria.infinitylib.common.Events;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

@ParametersAreNonnullByDefault
public final class AddonCommand
extends ParentCommand
implements TabExecutor,
Listener {
    private final String help;
    private final String slashHelp;

    public AddonCommand(String command) {
        this(Objects.requireNonNull(AbstractAddon.instance().getCommand(command), "No such command '" + command + "'! Add it it to your plugin.yml!"));
    }

    public AddonCommand(PluginCommand command) {
        super(command.getName(), command.getDescription());
        command.setExecutor((CommandExecutor)this);
        command.setTabCompleter((TabCompleter)this);
        Events.registerListener(this);
        this.help = "help " + command.getName();
        this.slashHelp = "/" + this.help;
        this.addSub(new InfoCommand((SlimefunAddon)AbstractAddon.instance()));
        this.addSub(new AliasesCommand(command));
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    private void onServerCommand(ServerCommandEvent e) {
        if (e.getCommand().toLowerCase(Locale.ROOT).startsWith(this.help)) {
            e.setCommand(this.name());
        }
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    private void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase(Locale.ROOT).startsWith(this.slashHelp)) {
            e.setMessage("/" + this.name());
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.execute(sender, args);
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        this.complete(sender, args, strings);
        ArrayList<String> returnList = new ArrayList<String>();
        String arg = args[args.length - 1].toLowerCase(Locale.ROOT);
        for (String item : strings) {
            if (item.toLowerCase(Locale.ROOT).contains(arg)) {
                returnList.add(item);
                if (returnList.size() < 64) continue;
                break;
            }
            if (!item.equalsIgnoreCase(arg)) continue;
            return Collections.emptyList();
        }
        return returnList;
    }

    @Override
    String fullName() {
        return this.name();
    }
}

