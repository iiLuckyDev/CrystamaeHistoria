/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.core;

import io.github.sefiraat.crystamaehistoria.infinitylib.common.StringUtils;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.Environment;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.PathBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.configuration.file.YamlConfiguration;

@ParametersAreNonnullByDefault
public final class AddonConfig
extends YamlConfiguration {
    private final YamlConfiguration defaults = new YamlConfiguration();
    private final Map<String, String> comments = new HashMap<String, String>();
    private final File file;

    public AddonConfig(String path) {
        Object addon = AbstractAddon.instance();
        this.file = new File(addon.getDataFolder(), path);
        ((YamlConfiguration)this).defaults = this.defaults;
        this.loadDefaults((AbstractAddon)((Object)addon), path);
    }

    public int getInt(String path, int min, int max) {
        int val = this.getInt(path);
        if (val < min || val > max) {
            val = this.getDefaults().getInt(path);
            this.set(path, val);
        }
        return val;
    }

    public double getDouble(String path, double min, double max) {
        double val = this.getDouble(path);
        if (val < min || val > max) {
            val = this.getDefaults().getDouble(path);
            this.set(path, val);
        }
        return val;
    }

    public void removeUnusedKeys() {
        for (String key : this.getKeys(true)) {
            if (this.defaults.contains(key)) continue;
            this.set(key, null);
        }
    }

    public void save() {
        try {
            this.save(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(@Nonnull File file) throws IOException {
        if (AbstractAddon.environment() == Environment.LIVE) {
            super.save(file);
        }
    }

    public void reload() {
        if (this.file.exists()) {
            try {
                this.load(this.file);
            }
            catch (Throwable e) {
                e.printStackTrace();
            }
        }
        this.save();
    }

    @Nonnull
    public YamlConfiguration getDefaults() {
        return this.defaults;
    }

    @Nullable
    String getComment(String key) {
        return this.comments.get(key);
    }

    @Nonnull
    protected String buildHeader() {
        return "";
    }

    @Nonnull
    public String saveToString() {
        this.options().copyDefaults(true).copyHeader(false).indent(2);
        String defaultSave = super.saveToString();
        try {
            String[] lines = defaultSave.split("\n");
            StringBuilder save = new StringBuilder();
            PathBuilder pathBuilder = new PathBuilder();
            for (String line : lines) {
                String comment;
                if (line.contains(":") && (comment = this.getComment(pathBuilder.append(line).build())) != null) {
                    save.append(comment);
                }
                save.append(line).append('\n');
            }
            return save.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return defaultSave;
        }
    }

    private void loadDefaults(AbstractAddon addon, String name) {
        InputStream stream = addon.getResource(name);
        if (stream == null) {
            throw new IllegalStateException("No default config for " + name + "!");
        }
        try {
            String def = this.readDefaults(stream);
            this.defaults.loadFromString(def);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        this.reload();
    }

    private String readDefaults(@Nonnull InputStream inputStream) throws IOException {
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder yamlBuilder = new StringBuilder();
        StringBuilder commentBuilder = new StringBuilder("\n");
        PathBuilder pathBuilder = new PathBuilder();
        while ((line = input.readLine()) != null) {
            yamlBuilder.append(line).append('\n');
            if (StringUtils.isBlank(line)) continue;
            if (line.contains("#")) {
                commentBuilder.append(line).append('\n');
                continue;
            }
            if (!line.contains(":")) continue;
            pathBuilder.append(line);
            if (commentBuilder.length() != 1) {
                this.comments.put(pathBuilder.build(), commentBuilder.toString());
                commentBuilder = new StringBuilder("\n");
                continue;
            }
            if (!pathBuilder.inMainSection()) continue;
            this.comments.put(pathBuilder.build(), "\n");
        }
        input.close();
        return yamlBuilder.toString();
    }
}

