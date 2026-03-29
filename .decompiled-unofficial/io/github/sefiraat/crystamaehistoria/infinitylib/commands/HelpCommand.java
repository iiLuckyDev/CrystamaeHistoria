/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.ParentCommand;
import io.github.sefiraat.crystamaehistoria.infinitylib.commands.SubCommand;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@ParametersAreNonnullByDefault
final class HelpCommand
extends SubCommand {
    private final ParentCommand command;

    HelpCommand(ParentCommand command) {
        super("help", "Displays this");
        this.command = command;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("");
        sender.sendMessage(ChatColors.color((String)("&7----------&b /" + this.command.fullName() + " Help &7----------")));
        sender.sendMessage("");
        for (SubCommand sub : this.command.available(sender)) {
            sender.sendMessage("/" + sub.fullName() + String.valueOf(ChatColor.YELLOW) + " - " + sub.description());
        }
        sender.sendMessage("");
    }

    @Override
    public void complete(CommandSender sender, String[] args, List<String> tabs) {
    }
}

