/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Mob
 *  org.bukkit.entity.Player
 */
package io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class MagicSummon {
    public final UUID mobUUID;
    public final UUID ownerUUID;
    public Consumer<MagicSummon> tickConsumer;

    @ParametersAreNonnullByDefault
    public MagicSummon(UUID mobUUID, UUID ownerUUID) {
        this.mobUUID = mobUUID;
        this.ownerUUID = ownerUUID;
    }

    @Nullable
    public Mob getMob() {
        return (Mob)Bukkit.getEntity((UUID)this.mobUUID);
    }

    @Nullable
    public Player getPlayer() {
        return (Player)Bukkit.getEntity((UUID)this.ownerUUID);
    }

    public void kill() {
        CrystamaeHistoria.getSummonedEntityMap().remove(this);
        Mob mob = (Mob)Bukkit.getEntity((UUID)this.mobUUID);
        if (mob != null) {
            mob.remove();
        }
    }

    public void run() {
        if (this.tickConsumer != null) {
            this.tickConsumer.accept(this);
        }
    }

    @Generated
    public UUID getMobUUID() {
        return this.mobUUID;
    }

    @Generated
    public UUID getOwnerUUID() {
        return this.ownerUUID;
    }

    @Generated
    public Consumer<MagicSummon> getTickConsumer() {
        return this.tickConsumer;
    }

    @Generated
    public void setTickConsumer(Consumer<MagicSummon> tickConsumer) {
        this.tickConsumer = tickConsumer;
    }
}

