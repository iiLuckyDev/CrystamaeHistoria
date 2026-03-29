/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 */
package io.github.sefiraat.crystamaehistoria;

import com.google.common.base.Preconditions;
import io.github.sefiraat.crystamaehistoria.SpellMemory;
import io.github.sefiraat.crystamaehistoria.bstats.bukkit.Metrics;
import io.github.sefiraat.crystamaehistoria.bstats.charts.AdvancedPie;
import io.github.sefiraat.crystamaehistoria.commands.GetRanks;
import io.github.sefiraat.crystamaehistoria.commands.OpenSpellCompendium;
import io.github.sefiraat.crystamaehistoria.commands.OpenStoryCompendium;
import io.github.sefiraat.crystamaehistoria.commands.TestSpell;
import io.github.sefiraat.crystamaehistoria.commands.TestWand;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.AbstractAddon;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.managers.ConfigManager;
import io.github.sefiraat.crystamaehistoria.managers.ListenerManager;
import io.github.sefiraat.crystamaehistoria.managers.RunnableManager;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.managers.SupportedPluginManager;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slimefun.ArtisticItems;
import io.github.sefiraat.crystamaehistoria.slimefun.Exalted;
import io.github.sefiraat.crystamaehistoria.slimefun.Gadgets;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.slimefun.Mechanisms;
import io.github.sefiraat.crystamaehistoria.slimefun.NetheoPlants;
import io.github.sefiraat.crystamaehistoria.slimefun.Runes;
import io.github.sefiraat.crystamaehistoria.slimefun.Tools;
import io.github.sefiraat.crystamaehistoria.slimefun.Uniques;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel.ChroniclerPanelCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class CrystamaeHistoria
extends AbstractAddon {
    private static CrystamaeHistoria instance;
    private ConfigManager configManager;
    private StoriesManager storiesManager;
    private ListenerManager listenerManager;
    private RunnableManager runnableManager;
    private SpellMemory spellMemory;
    private EffectManager effectManager;
    private SupportedPluginManager supportedPluginManager;

    public CrystamaeHistoria() {
        super("Sefiraat", "CrystamaeHistoria", "master", "auto-update");
    }

    public static CrystamaeHistoria getInstance() {
        return instance;
    }

    public static ConfigManager getConfigManager() {
        return CrystamaeHistoria.instance.configManager;
    }

    public static StoriesManager getStoriesManager() {
        return CrystamaeHistoria.instance.storiesManager;
    }

    public static ListenerManager getListenerManager() {
        return CrystamaeHistoria.instance.listenerManager;
    }

    public static RunnableManager getRunnableManager() {
        return CrystamaeHistoria.instance.runnableManager;
    }

    public static SpellMemory getSpellMemory() {
        return CrystamaeHistoria.instance.spellMemory;
    }

    public static EffectManager getEffectManager() {
        return CrystamaeHistoria.instance.effectManager;
    }

    public static SupportedPluginManager getSupportedPluginManager() {
        return CrystamaeHistoria.instance.supportedPluginManager;
    }

    public static PluginManager getPluginManager() {
        return instance.getServer().getPluginManager();
    }

    @Nonnull
    public static Map<MagicProjectile, Pair<CastInformation, Long>> getProjectileMap() {
        return CrystamaeHistoria.instance.spellMemory.getProjectileMap();
    }

    @Nonnull
    public static Map<MagicFallingBlock, Pair<CastInformation, Long>> getFallingBlockMap() {
        return CrystamaeHistoria.instance.spellMemory.getFallingBlockMap();
    }

    @Nonnull
    public static Map<UUID, Pair<CastInformation, Long>> getStrikeMap() {
        return CrystamaeHistoria.instance.spellMemory.getStrikeMap();
    }

    @Nonnull
    public static Map<MagicSummon, Long> getSummonedEntityMap() {
        return CrystamaeHistoria.instance.spellMemory.getSummonedEntities();
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getProjectileCastInfo(MagicProjectile magicProjectile) {
        CastInformation castInformation = (CastInformation)CrystamaeHistoria.getProjectileMap().get(magicProjectile).getFirstValue();
        Preconditions.checkNotNull((Object)castInformation, (Object)"Cast information is null, magical projectile spawned incorrectly.");
        return castInformation;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getFallingBlockCastInfo(MagicFallingBlock magicFallingBlock) {
        CastInformation castInformation = (CastInformation)CrystamaeHistoria.getFallingBlockMap().get(magicFallingBlock).getFirstValue();
        Preconditions.checkNotNull((Object)castInformation, (Object)"Cast information is null, magical falling block spawned incorrectly.");
        return castInformation;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static CastInformation getStrikeCastInfo(UUID lightningStrike) {
        CastInformation castInformation = (CastInformation)CrystamaeHistoria.getStrikeMap().get(lightningStrike).getFirstValue();
        Preconditions.checkNotNull((Object)castInformation, (Object)"Cast information is null, magical projectile spawned incorrectly.");
        return castInformation;
    }

    @Override
    public void enable() {
        instance = this;
        this.getLogger().info("########################################");
        this.getLogger().info("    Crystamae Historia - By Sefiraat    ");
        this.getLogger().info("########################################");
        this.configManager = new ConfigManager();
        this.storiesManager = new StoriesManager();
        this.listenerManager = new ListenerManager();
        this.runnableManager = new RunnableManager();
        this.spellMemory = new SpellMemory();
        this.supportedPluginManager = new SupportedPluginManager();
        this.effectManager = new EffectManager((Plugin)this);
        this.configManager.loadConfig();
        SpellType.setupEnabledSpells();
        this.setupSlimefun();
        this.setupBstats();
        this.getAddonCommand().addSub(new TestSpell());
        this.getAddonCommand().addSub(new TestWand());
        this.getAddonCommand().addSub(new OpenSpellCompendium());
        this.getAddonCommand().addSub(new OpenStoryCompendium());
        this.getAddonCommand().addSub(new GetRanks());
    }

    private void setupBstats() {
        Metrics metrics = new Metrics(this, 12065);
        AdvancedPie disabledSpellsChart = new AdvancedPie("disabled_spells", () -> {
            HashMap<String, Integer> values = new HashMap<String, Integer>();
            for (SpellType spellType : SpellType.getCachedValues()) {
                Spell spell = spellType.getSpell();
                values.put(spell.getId(), spell.isEnabled() ? 0 : 1);
            }
            return values;
        });
        AdvancedPie spellsCastChart = new AdvancedPie("spells_cast", () -> {
            HashMap<String, Integer> values = new HashMap<String, Integer>();
            for (SpellType spellType : SpellType.getCachedValues()) {
                Spell spell = spellType.getSpell();
                int timesCast = 0;
                for (String string : CrystamaeHistoria.getConfigManager().getPlayerStats().getKeys(false)) {
                    UUID uuid = UUID.fromString(string);
                    timesCast += PlayerStatistics.getUsages(uuid, spellType);
                }
                values.put(spell.getId(), timesCast);
            }
            return values;
        });
        AdvancedPie storiesChronicled = new AdvancedPie("stories_chronicled", () -> {
            HashMap<String, Integer> values = new HashMap<String, Integer>();
            for (BlockDefinition definition : CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().values()) {
                int timesChronicled = 0;
                for (String string : CrystamaeHistoria.getConfigManager().getPlayerStats().getKeys(false)) {
                    UUID uuid = UUID.fromString(string);
                    timesChronicled += PlayerStatistics.getChronicle(uuid, definition);
                }
                values.put(definition.getMaterial().toString(), timesChronicled);
            }
            return values;
        });
        metrics.addCustomChart(disabledSpellsChart);
        metrics.addCustomChart(spellsCastChart);
        metrics.addCustomChart(storiesChronicled);
    }

    @Override
    protected void disable() {
        for (ChroniclerPanelCache cache : ChroniclerPanel.getCaches().values()) {
            cache.shutdown();
        }
        this.spellMemory.clearAll();
        this.configManager.saveAll();
        instance = null;
    }

    private void setupSlimefun() {
        ItemGroups.setup();
        Materials.setup();
        Mechanisms.setup();
        Tools.setup();
        Gadgets.setup();
        ArtisticItems.setup();
        Exalted.setup();
        Uniques.setup();
        Runes.setup();
        if (this.supportedPluginManager.isNetheopoiesis()) {
            try {
                NetheoPlants.setup();
            }
            catch (NoClassDefFoundError e) {
                this.getLogger().severe("Netheopoiesis must be updated to meet Crystamaes requirements.");
            }
        }
    }
}

