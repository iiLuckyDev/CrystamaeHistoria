/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.bukkit.configuration.Configuration
 *  org.bukkit.configuration.InvalidConfigurationException
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package io.github.sefiraat.crystamaehistoria.managers;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    private final FileConfiguration blocks = this.getConfig("blocks.yml", true);
    private final FileConfiguration stories = this.getConfig("generic-stories.yml", true);
    private final FileConfiguration playerStats = this.getConfig("player_stats.yml", false);
    private final FileConfiguration blockColors = this.getConfig("block_colors.yml", false);
    private final FileConfiguration spells = this.getConfig("spells.yml", false);

    @Nonnull
    private FileConfiguration getConfig(@Nonnull String fileName, boolean updateWithDefaults) {
        CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();
        File file = new File(plugin.getDataFolder(), fileName);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration((File)file);
        try {
            configuration.load(file);
            if (updateWithDefaults) {
                this.updateConfig((FileConfiguration)configuration, file, fileName);
            }
        }
        catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    @ParametersAreNonnullByDefault
    private void updateConfig(FileConfiguration config, File file, String fileName) throws IOException {
        InputStream inputStream = CrystamaeHistoria.getInstance().getResource(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        YamlConfiguration defaults = YamlConfiguration.loadConfiguration((Reader)reader);
        config.addDefaults((Configuration)defaults);
        config.options().copyDefaults(true);
        config.save(file);
    }

    @ParametersAreNonnullByDefault
    public boolean spellEnabled(Spell spell) {
        return this.spells.getBoolean(spell.getId());
    }

    public void loadConfig() {
        for (SpellType spellType : SpellType.getCachedValues()) {
            Spell spell = spellType.getSpell();
            if (!this.spells.contains(spell.getId())) {
                try {
                    File file = new File(CrystamaeHistoria.getInstance().getDataFolder(), "spells.yml");
                    this.spells.set(spell.getId(), (Object)true);
                    this.spells.save(file);
                }
                catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            boolean enabled = this.spells.getBoolean(spell.getId());
            spell.setEnabled(enabled);
            if (!enabled) continue;
            LiquefactionBasinCache.addSpellRecipe(spellType, spell.getRecipe());
        }
    }

    public void saveAll() {
        CrystamaeHistoria.getInstance().getLogger().info("Crystamae saving data.");
        CrystamaeHistoria.getInstance().getConfig().save();
        this.saveResearches();
    }

    private void saveResearches() {
        File file = new File(CrystamaeHistoria.getInstance().getDataFolder(), "player_stats.yml");
        try {
            this.playerStats.save(file);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Generated
    public FileConfiguration getBlocks() {
        return this.blocks;
    }

    @Generated
    public FileConfiguration getStories() {
        return this.stories;
    }

    @Generated
    public FileConfiguration getPlayerStats() {
        return this.playerStats;
    }

    @Generated
    public FileConfiguration getBlockColors() {
        return this.blockColors;
    }

    @Generated
    public FileConfiguration getSpells() {
        return this.spells;
    }
}

