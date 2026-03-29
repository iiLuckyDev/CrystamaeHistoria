/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonParser
 *  com.google.gson.stream.JsonReader
 *  lombok.Generated
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Tag
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.plugin.Plugin;

public enum CrystaTag implements Tag<Material>
{
    GLAZED_TERRACOTTA,
    SPAWN_EGGS,
    CONCRETE_BLOCKS,
    COPPER_BLOCKS,
    COPPER_BLOCKS_WAXED,
    CUT_COPPER_BLOCKS,
    CUT_COPPER_BLOCKS_WAXED,
    CUT_COPPER_SLABS,
    CUT_COPPER_SLABS_WAXED,
    CUT_COPPER_STAIRS,
    CUT_COPPER_STAIRS_WAXED;

    private static final CrystaTag[] cachedValues;
    private final NamespacedKey namespacedKey;
    private final Set<Material> materialList = new LinkedHashSet<Material>();

    private CrystaTag() {
        String name = this.name().toLowerCase(Locale.ROOT);
        String fileLocation = "/tags/" + name + ".json";
        this.namespacedKey = new NamespacedKey((Plugin)CrystamaeHistoria.getInstance(), name);
        try {
            InputStream stream = CrystamaeHistoria.class.getResourceAsStream(fileLocation);
            JsonReader reader = new JsonReader((Reader)new InputStreamReader(stream));
            JsonObject object = (JsonObject)JsonParser.parseReader((JsonReader)reader);
            for (JsonElement element : object.get("values").getAsJsonArray()) {
                String tagString = element.getAsString();
                Material material = Material.matchMaterial((String)tagString);
                if (material != null) {
                    this.materialList.add(material);
                    continue;
                }
                CrystamaeHistoria.getInstance().getLogger().warning(MessageFormat.format("Error with tag: {0}", tagString));
            }
        }
        catch (JsonParseException e) {
            CrystamaeHistoria.getInstance().getLogger().warning(MessageFormat.format("Error with tag: {0}", fileLocation));
        }
    }

    public void setup() {
    }

    public boolean isTagged(@Nonnull Material material) {
        return this.materialList.contains(material);
    }

    @Nonnull
    public Set<Material> getValues() {
        return this.materialList;
    }

    @Nonnull
    public NamespacedKey getKey() {
        return this.namespacedKey;
    }

    @Generated
    public static CrystaTag[] getCachedValues() {
        return cachedValues;
    }

    static {
        cachedValues = CrystaTag.values();
    }
}

