/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.SkullMeta
 *  org.bukkit.profile.PlayerProfile
 *  org.bukkit.profile.PlayerTextures
 */
package io.github.sefiraat.crystamaehistoria.utils;

import java.net.URI;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

public class Util {
    private Util() {
    }

    public static ItemStack fromBase64Hash(String hash) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)head.getItemMeta();
        PlayerProfile profile = Bukkit.createPlayerProfile((UUID)UUID.randomUUID(), null);
        PlayerTextures textures = profile.getTextures();
        try {
            textures.setSkin(URI.create("http://textures.minecraft.net/texture/" + hash).toURL());
        }
        catch (Exception exception) {
            // empty catch block
        }
        profile.setTextures(textures);
        meta.setOwnerProfile(profile);
        head.setItemMeta((ItemMeta)meta);
        return head;
    }
}

