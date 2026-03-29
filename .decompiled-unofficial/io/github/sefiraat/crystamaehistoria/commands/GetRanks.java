/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.SubCommand;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetRanks
extends SubCommand {
    public GetRanks() {
        super("rank", "Displays your Crystamae ranks", false);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            player.sendMessage(PlayerStatistics.getSpellRankString(player.getUniqueId()));
            player.sendMessage(PlayerStatistics.getStoryRankString(player.getUniqueId()));
            player.sendMessage(PlayerStatistics.getGildingRankString(player.getUniqueId()));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void complete(CommandSender commandSender, String[] strings, List<String> list) {
    }
}

