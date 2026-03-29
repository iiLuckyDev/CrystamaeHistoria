/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
 *  io.github.thebusybiscuit.slimefun4.implementation.Slimefun
 *  io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater
 *  org.bukkit.NamespacedKey
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.core;

import io.github.sefiraat.crystamaehistoria.infinitylib.InfinityLib;
import io.github.sefiraat.crystamaehistoria.infinitylib.commands.AddonCommand;
import io.github.sefiraat.crystamaehistoria.infinitylib.common.Scheduler;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.AddonConfig;
import io.github.sefiraat.crystamaehistoria.infinitylib.core.Environment;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@ParametersAreNonnullByDefault
public abstract class AbstractAddon
extends JavaPlugin
implements SlimefunAddon {
    private static AbstractAddon instance;
    private final GitHubBuildsUpdater updater;
    private final Environment environment;
    private final String githubUserName;
    private final String githubRepo;
    private final String autoUpdateBranch;
    private final String autoUpdateKey;
    private final String bugTrackerURL;
    private AddonCommand command;
    private AddonConfig config;
    private int slimefunTickCount;
    private boolean autoUpdatesEnabled;
    private boolean disabling;
    private boolean enabling;
    private boolean loading;

    public AbstractAddon(String githubUserName, String githubRepo, String autoUpdateBranch, String autoUpdateKey) {
        boolean official = this.getDescription().getVersion().matches("DEV - \\d+ \\(git \\w+\\)");
        this.updater = official ? new GitHubBuildsUpdater((Plugin)this, this.getFile(), githubUserName + "/" + githubRepo + "/" + autoUpdateBranch) : null;
        this.environment = Environment.LIVE;
        this.githubUserName = githubUserName;
        this.autoUpdateBranch = autoUpdateBranch;
        this.githubRepo = githubRepo;
        this.autoUpdateKey = autoUpdateKey;
        this.bugTrackerURL = "https://github.com/" + githubUserName + "/" + githubRepo + "/issues";
        this.validate();
    }

    public AbstractAddon(String githubUserName, String githubRepo, String autoUpdateBranch, String autoUpdateKey, Environment environment) {
        this.updater = null;
        this.environment = environment;
        this.githubUserName = githubUserName;
        this.autoUpdateBranch = autoUpdateBranch;
        this.githubRepo = githubRepo;
        this.autoUpdateKey = autoUpdateKey;
        this.bugTrackerURL = "https://github.com/" + githubUserName + "/" + githubRepo + "/issues";
        this.validate();
    }

    private void validate() {
        if (this.environment == Environment.LIVE) {
            if (InfinityLib.PACKAGE.contains("mooy1.infinitylib")) {
                throw new IllegalStateException("You must relocate InfinityLib to your own package!");
            }
            String addonPackage = ((Object)((Object)this)).getClass().getPackage().getName();
            if (!addonPackage.contains(InfinityLib.ADDON_PACKAGE)) {
                throw new IllegalStateException("Shade and relocate your own InfinityLib!");
            }
        }
        if (instance != null) {
            throw new IllegalStateException("Addon " + instance.getName() + " is already using this InfinityLib, Shade an relocate your own!");
        }
        if (!this.githubUserName.matches("[\\w-]+")) {
            throw new IllegalArgumentException("Invalid githubUserName");
        }
        if (!this.githubRepo.matches("[\\w-]+")) {
            throw new IllegalArgumentException("Invalid githubRepo");
        }
        if (!this.autoUpdateBranch.matches("[\\w-]+")) {
            throw new IllegalArgumentException("Invalid autoUpdateBranch");
        }
    }

    public final void onLoad() {
        if (this.loading) {
            throw new IllegalStateException(this.getName() + " is already loading! Do not call super.onLoad()!");
        }
        this.loading = true;
        try {
            this.load();
        }
        catch (RuntimeException e) {
            this.handle(e);
        }
        finally {
            this.loading = false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void onEnable() {
        PluginCommand pluginCommand;
        if (this.enabling) {
            throw new IllegalStateException(this.getName() + " is already enabling! Do not call super.onEnable()!");
        }
        this.enabling = true;
        instance = this;
        boolean brokenConfig = false;
        try {
            this.config = new AddonConfig("config.yml");
        }
        catch (RuntimeException e) {
            brokenConfig = true;
            e.printStackTrace();
        }
        if (this.autoUpdateKey == null) {
            brokenConfig = true;
            this.handle(new IllegalStateException("Null auto update key"));
        } else if (this.autoUpdateKey.isEmpty()) {
            brokenConfig = true;
            this.handle(new IllegalStateException("Empty auto update key!"));
        } else if (!brokenConfig && !this.config.getDefaults().contains(this.autoUpdateKey, true)) {
            brokenConfig = true;
            this.handle(new IllegalStateException("Auto update key missing from the default config!"));
        }
        if (this.updater != null) {
            if (brokenConfig) {
                this.updater.start();
            } else if (this.config.getBoolean(this.autoUpdateKey)) {
                this.autoUpdatesEnabled = true;
                this.updater.start();
            }
        }
        if ((pluginCommand = this.getCommand(this.getName())) != null) {
            this.command = new AddonCommand(pluginCommand);
        }
        Scheduler.repeat(Slimefun.getTickerTask().getTickRate(), () -> ++this.slimefunTickCount);
        try {
            this.enable();
        }
        catch (RuntimeException e) {
            this.handle(e);
        }
        finally {
            this.enabling = false;
        }
    }

    public final void onDisable() {
        if (this.disabling) {
            throw new IllegalStateException(this.getName() + " is already disabling! Do not call super.onDisable()!");
        }
        this.disabling = true;
        try {
            this.disable();
        }
        catch (RuntimeException e) {
            this.handle(e);
        }
        finally {
            this.disabling = false;
            instance = null;
            this.slimefunTickCount = 0;
            this.command = null;
            this.config = null;
        }
    }

    private void handle(RuntimeException e) {
        switch (this.environment) {
            case TESTING: {
                throw e;
            }
            case LIVE: {
                e.printStackTrace();
            }
        }
    }

    protected void load() {
    }

    protected abstract void enable();

    protected abstract void disable();

    @Nonnull
    protected final AddonCommand getAddonCommand() {
        return Objects.requireNonNull(((AbstractAddon)((Object)AbstractAddon.instance())).command, "Command '" + this.getName() + "' missing from plugin.yml!");
    }

    protected final boolean autoUpdatesEnabled() {
        return ((AbstractAddon)((Object)AbstractAddon.instance())).autoUpdatesEnabled;
    }

    @Nonnull
    public final JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull
    public final String getBugTrackerURL() {
        return this.bugTrackerURL;
    }

    @Nonnull
    public final AddonConfig getConfig() {
        return ((AbstractAddon)((Object)AbstractAddon.instance())).config;
    }

    public final void reloadConfig() {
        ((AbstractAddon)((Object)AbstractAddon.instance())).config.reload();
    }

    public final void saveConfig() {
        ((AbstractAddon)((Object)AbstractAddon.instance())).config.save();
    }

    public final void saveDefaultConfig() {
    }

    @Nonnull
    public static <T extends AbstractAddon> T instance() {
        return (T)((Object)Objects.requireNonNull(instance, "Addon is not enabled!"));
    }

    @Nonnull
    public static AddonConfig config() {
        return ((AbstractAddon)((Object)AbstractAddon.instance())).getConfig();
    }

    public static void log(Level level, String ... messages) {
        Logger logger = AbstractAddon.instance().getLogger();
        for (String msg : messages) {
            logger.log(level, msg);
        }
    }

    public static int slimefunTickCount() {
        return ((AbstractAddon)((Object)AbstractAddon.instance())).slimefunTickCount;
    }

    @Nonnull
    public static Environment environment() {
        return ((AbstractAddon)((Object)AbstractAddon.instance())).environment;
    }

    @Nonnull
    public static NamespacedKey createKey(String s) {
        return new NamespacedKey(AbstractAddon.instance(), s);
    }
}

