/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CustomSound {
    private static boolean initializedReflection;
    private static Method player_playCustomSoundMethod;
    private static Method player_stopSoundMethod;
    private static Method player_stopCustomSoundMethod;
    private Sound sound;
    private String customSound;
    private float volume = 1.0f;
    private float pitch = 1.0f;
    private int range = 0;

    public CustomSound(Sound sound) {
        this.sound = sound;
        this.customSound = null;
    }

    public CustomSound(String key) {
        if (key != null && key.length() > 0) {
            String[] pieces = StringUtils.split((String)key, (char)',');
            String soundName = pieces[0];
            if (soundName.indexOf(46) < 0) {
                try {
                    this.sound = Sound.valueOf((String)soundName.toUpperCase());
                }
                catch (Exception ex) {
                    this.sound = null;
                    this.customSound = soundName;
                }
            } else {
                this.customSound = soundName;
            }
            if (pieces.length > 1) {
                try {
                    this.volume = Float.parseFloat(pieces[1]);
                }
                catch (Exception ex) {
                    this.volume = 1.0f;
                }
            }
            if (pieces.length > 2) {
                try {
                    this.pitch = Float.parseFloat(pieces[2]);
                }
                catch (Exception ex) {
                    this.pitch = 1.0f;
                }
            }
            if (pieces.length > 3) {
                try {
                    this.range = Integer.parseInt(pieces[3]);
                }
                catch (Exception ex) {
                    this.range = 0;
                }
            }
        }
    }

    private static void initializeReflection(Logger logger) {
        block9: {
            if (!initializedReflection) {
                block8: {
                    block7: {
                        initializedReflection = true;
                        try {
                            player_playCustomSoundMethod = Player.class.getMethod("playSound", Location.class, String.class, Float.TYPE, Float.TYPE);
                        }
                        catch (Exception ex) {
                            if (logger == null) break block7;
                            logger.warning("Failed to bind to custom sound method");
                        }
                    }
                    try {
                        player_stopCustomSoundMethod = Player.class.getMethod("stopSound", String.class);
                    }
                    catch (Exception ex) {
                        if (logger == null) break block8;
                        logger.warning("Failed to bind to stop custom sound method");
                    }
                }
                try {
                    player_stopSoundMethod = Player.class.getMethod("stopSound", Sound.class);
                }
                catch (Exception ex) {
                    if (logger == null) break block9;
                    logger.warning("Failed to bind to stop sound method");
                }
            }
        }
    }

    public boolean isCustom() {
        return this.sound == null;
    }

    public String getCustomSound() {
        return this.customSound;
    }

    public Sound getSound() {
        return this.sound;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void stop(Player player) {
        if (this.sound != null) {
            CustomSound.stopSound(null, player, this.sound);
        }
        if (this.customSound != null && !this.customSound.isEmpty()) {
            CustomSound.stopSound(null, player, this.customSound);
        }
    }

    public int hashCode() {
        return (this.sound == null ? 0 : this.sound.hashCode()) + 31 * (Float.floatToIntBits(this.pitch) + 31 * Float.floatToIntBits(this.volume));
    }

    public boolean equals(Object other) {
        if (!(other instanceof CustomSound)) {
            return false;
        }
        CustomSound otherEffect = (CustomSound)other;
        return this.sound != otherEffect.sound || this.pitch != otherEffect.pitch || this.volume != otherEffect.volume;
    }

    public int getRange() {
        return this.range;
    }

    public void play(Plugin plugin, Location sourceLocation) {
        this.play(plugin, plugin == null ? null : plugin.getLogger(), sourceLocation);
    }

    public void play(Plugin plugin, Logger logger, Location sourceLocation) {
        block13: {
            block12: {
                if (sourceLocation == null || plugin == null) {
                    return;
                }
                if (this.customSound != null) {
                    try {
                        int range = this.range;
                        if (range <= 0) {
                            range = (int)((double)this.volume > 1.0 ? 16.0 * (double)this.volume : 16.0);
                        }
                        int rangeSquared = range * range;
                        Collection players = plugin.getServer().getOnlinePlayers();
                        for (Player player : players) {
                            Location location = player.getLocation();
                            if (!location.getWorld().equals(sourceLocation.getWorld()) || !(location.distanceSquared(sourceLocation) <= (double)rangeSquared)) continue;
                            CustomSound.playCustomSound(logger, player, sourceLocation, this.customSound, this.volume, this.pitch);
                        }
                    }
                    catch (Exception ex) {
                        if (logger == null) break block12;
                        logger.warning("Failed to play custom sound: " + this.customSound);
                    }
                }
            }
            if (this.sound != null) {
                try {
                    if (this.range > 0) {
                        int rangeSquared = this.range * this.range;
                        Collection players = plugin.getServer().getOnlinePlayers();
                        for (Player player : players) {
                            Location location = player.getLocation();
                            if (!location.getWorld().equals(sourceLocation.getWorld()) || !(location.distanceSquared(sourceLocation) <= (double)rangeSquared)) continue;
                            player.playSound(sourceLocation, this.sound, this.volume, this.pitch);
                        }
                    } else {
                        sourceLocation.getWorld().playSound(sourceLocation, this.sound, this.volume, this.pitch);
                    }
                }
                catch (Exception ex) {
                    if (logger == null) break block13;
                    logger.warning("Failed to play sound: " + this.sound);
                }
            }
        }
    }

    public void play(Plugin plugin, Entity entity) {
        this.play(plugin, plugin == null ? null : plugin.getLogger(), entity);
    }

    public void play(Plugin plugin, Logger logger, Entity entity) {
        block15: {
            Location sourceLocation;
            block14: {
                if (entity == null || plugin == null) {
                    return;
                }
                sourceLocation = entity.getLocation();
                if (this.customSound != null) {
                    try {
                        if (this.range > 0) {
                            int rangeSquared = this.range * this.range;
                            Collection players = plugin.getServer().getOnlinePlayers();
                            for (Player player : players) {
                                Location location = player.getLocation();
                                if (!location.getWorld().equals(sourceLocation.getWorld()) || !(location.distanceSquared(sourceLocation) <= (double)rangeSquared)) continue;
                                CustomSound.playCustomSound(logger, player, sourceLocation, this.customSound, this.volume, this.pitch);
                            }
                        } else if (entity instanceof Player) {
                            Player player = (Player)entity;
                            CustomSound.playCustomSound(logger, player, sourceLocation, this.customSound, this.volume, this.pitch);
                        }
                    }
                    catch (Exception ex) {
                        if (logger == null) break block14;
                        logger.warning("Failed to play custom sound: " + this.customSound);
                    }
                }
            }
            if (this.sound != null) {
                try {
                    if (entity instanceof Player && this.range <= 0) {
                        Player player = (Player)entity;
                        player.playSound(sourceLocation, this.sound, this.volume, this.pitch);
                    } else if (this.range > 0) {
                        sourceLocation.getWorld().playSound(sourceLocation, this.sound, this.volume, this.pitch);
                    }
                }
                catch (Exception ex) {
                    if (logger == null) break block15;
                    logger.warning("Failed to play sound: " + this.sound);
                }
            }
        }
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String toString() {
        String soundName;
        String string = soundName = this.sound == null ? this.customSound : this.sound.name();
        if (soundName == null) {
            return "";
        }
        return soundName + "," + this.volume + "," + this.pitch + "," + this.range;
    }

    public static void stopSound(Logger logger, Player player, String sound) {
        block3: {
            CustomSound.initializeReflection(logger);
            if (player_stopCustomSoundMethod == null) {
                return;
            }
            try {
                player_stopCustomSoundMethod.invoke((Object)player, sound);
            }
            catch (Exception ex) {
                if (logger == null) break block3;
                logger.log(Level.WARNING, "Failed to stop custom sound: " + sound, ex);
            }
        }
    }

    public static void stopSound(Logger logger, Player player, Sound sound) {
        block3: {
            CustomSound.initializeReflection(logger);
            if (player_stopSoundMethod == null) {
                return;
            }
            try {
                player_stopSoundMethod.invoke((Object)player, sound);
            }
            catch (Exception ex) {
                if (logger == null) break block3;
                logger.log(Level.WARNING, "Failed to stop sound: " + sound, ex);
            }
        }
    }

    public static void playCustomSound(Logger logger, Player player, Location location, String sound, float volume, float pitch) {
        block3: {
            CustomSound.initializeReflection(logger);
            if (player_playCustomSoundMethod == null) {
                return;
            }
            try {
                player_playCustomSoundMethod.invoke((Object)player, location, sound, Float.valueOf(volume), Float.valueOf(pitch));
            }
            catch (Exception ex) {
                if (logger == null) break block3;
                logger.log(Level.WARNING, "Failed to play custom sound: " + sound, ex);
            }
        }
    }
}

