/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.EntityType
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms;

import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public abstract class DisplayStandHolder
extends AbstractCache {
    protected DisplayStandHolder(BlockMenu blockMenu) {
        super(blockMenu);
    }

    @Override
    public void kill(Location location) {
        super.kill(location);
        this.getDisplayStand().remove();
    }

    protected World getWorld() {
        return this.blockMenu.getLocation().getWorld();
    }

    protected Location getLocation() {
        return this.blockMenu.getLocation().clone();
    }

    protected Location getLocation(boolean centered) {
        if (centered) {
            return this.getLocation().add(0.5, 0.5, 0.5);
        }
        return this.getLocation();
    }

    @ParametersAreNonnullByDefault
    protected ArmorStand getDisplayStand() {
        Block block = this.blockMenu.getBlock();
        String uuidString = BlockStorage.getLocationInfo((Location)this.getLocation(), (String)"ch_display_stand");
        if (uuidString != null) {
            UUID uuid = UUID.fromString(uuidString);
            return (ArmorStand)Bukkit.getEntity((UUID)uuid);
        }
        ArmorStand armorStand = (ArmorStand)block.getWorld().spawnEntity(this.getLocation().add(0.5, -1.7, 0.5), EntityType.ARMOR_STAND);
        ArmourStandUtils.setDisplay(armorStand);
        BlockStorage.addBlockInfo((Location)block.getLocation(), (String)"ch_display_stand", (String)armorStand.getUniqueId().toString());
        return armorStand;
    }
}

