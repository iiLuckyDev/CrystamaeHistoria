/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
 *  io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
 *  org.bukkit.Bukkit
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.SubCommand;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenStoryCompendium
extends SubCommand {
    public OpenStoryCompendium() {
        super("stories", "Opens the story compendium", false);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer((UUID)player.getUniqueId());
            Optional playerProfile = PlayerProfile.find((OfflinePlayer)offlinePlayer);
            playerProfile.ifPresent(profile -> ItemGroups.STORY_COLLECTION.open(player, (PlayerProfile)profile, SlimefunGuideMode.SURVIVAL_MODE));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void complete(CommandSender commandSender, String[] strings, List<String> list) {
    }
}

