/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors
 *  org.bukkit.command.CommandSender
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.SubCommand;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.command.CommandSender;

@ParametersAreNonnullByDefault
final class InfoCommand
extends SubCommand {
    private final String[] message;

    InfoCommand(SlimefunAddon addon) {
        super("info", "Gives addon and slimefun version and discord links");
        Slimefun slimefun = Slimefun.instance();
        this.message = new String[]{"", ChatColors.color((String)("&b" + addon.getName() + " Info")), ChatColors.color((String)("&bSlimefun Version: &7" + (slimefun == null ? "null" : slimefun.getPluginVersion()))), ChatColors.color((String)"&bSlimefun Discord: &7Discord.gg/slimefun"), ChatColors.color((String)("&bAddon Version: &7" + addon.getPluginVersion())), ChatColors.color((String)"&bAddon Community: &7Discord.gg/SqD3gg5SAU"), ChatColors.color((String)("&bGithub: &7" + addon.getBugTrackerURL())), ""};
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        sender.sendMessage(this.message);
    }

    @Override
    protected void complete(CommandSender sender, String[] args, List<String> tabs) {
    }
}

