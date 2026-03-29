/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class AbstractCache {
    protected final BlockMenu blockMenu;
    @Nullable
    protected UUID activePlayer;

    @ParametersAreNonnullByDefault
    protected AbstractCache(BlockMenu blockMenu) {
        this.blockMenu = blockMenu;
    }

    @OverridingMethodsMustInvokeSuper
    public void kill(Location location) {
        BlockStorage.clearBlockInfo((Location)location);
    }

    public void setActivePlayer(@Nonnull Player player) {
        this.activePlayer = player.getUniqueId();
        BlockStorage.addBlockInfo((Block)this.blockMenu.getBlock(), (String)"BS_CP_ACTIVE_PLAYER", (String)player.getUniqueId().toString());
    }

    @Nullable
    @Generated
    public UUID getActivePlayer() {
        return this.activePlayer;
    }
}

