/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;

public final class CoolDowns {
    private final Map<UUID, Long> map = new HashMap<UUID, Long>();
    private final long cd;

    public boolean check(UUID uuid) {
        return System.currentTimeMillis() - this.map.getOrDefault(uuid, 0L) >= this.cd;
    }

    public void reset(UUID uuid) {
        this.map.put(uuid, System.currentTimeMillis());
    }

    public boolean checkAndReset(UUID uuid) {
        boolean check = this.check(uuid);
        if (check) {
            this.reset(uuid);
        }
        return check;
    }

    @Generated
    public CoolDowns(long cd) {
        this.cd = cd;
    }
}

