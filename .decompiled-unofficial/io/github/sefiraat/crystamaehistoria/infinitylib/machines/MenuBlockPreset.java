/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
 *  me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.machines;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.MenuBlock;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@ParametersAreNonnullByDefault
final class MenuBlockPreset
extends BlockMenuPreset {
    private final MenuBlock menuBlock;

    MenuBlockPreset(MenuBlock menuBlock) {
        super(menuBlock.getId(), menuBlock.getItemName());
        this.menuBlock = menuBlock;
        menuBlock.setup(this);
    }

    public void newInstance(BlockMenu menu, Block b) {
        this.menuBlock.onNewInstance(menu, b);
    }

    public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
        return this.menuBlock.getTransportSlots(menu, flow, item);
    }

    public void init() {
    }

    public boolean canOpen(Block b, Player p) {
        return Slimefun.getProtectionManager().hasPermission((OfflinePlayer)p, b.getLocation(), Interaction.INTERACT_BLOCK) && this.menuBlock.canUse(p, false);
    }

    public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
        return new int[0];
    }
}

