/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.profile.PlayerProfile
 */
package com.jeff_media.morepersistentdatatypes;

import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableArrayDataType;
import com.jeff_media.morepersistentdatatypes.datatypes.serializable.ConfigurationSerializableDataType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;

public interface DataType_1_18_1 {
    public static final PersistentDataType<byte[], PlayerProfile> PLAYER_PROFILE = new ConfigurationSerializableDataType<PlayerProfile>(PlayerProfile.class);
    public static final PersistentDataType<byte[], PlayerProfile[]> PLAYER_PROFILE_ARRAY = new ConfigurationSerializableArrayDataType(PlayerProfile[].class);
}

