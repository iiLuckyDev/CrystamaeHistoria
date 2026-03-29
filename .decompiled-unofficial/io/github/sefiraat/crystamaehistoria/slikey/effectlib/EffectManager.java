/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.MemoryConfiguration
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitScheduler
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib;

import com.google.common.base.CaseFormat;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.Effect;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ConfigUtils;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.CustomSound;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.Disposable;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.DynamicLocation;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ImageLoadCallback;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ImageLoadTask;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleDisplay;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleOptions;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class EffectManager
implements Disposable {
    private static List<EffectManager> effectManagers = new ArrayList<EffectManager>();
    private static Map<String, Class<? extends Effect>> effectClasses = new HashMap<String, Class<? extends Effect>>();
    private Plugin owningPlugin;
    private Logger logger;
    private Map<Effect, BukkitTask> effects;
    private ParticleDisplay display;
    private boolean disposed;
    private boolean disposeOnTermination;
    private boolean debug = false;
    private boolean stackTraces = true;
    private int visibleRange = 32;
    private File imageCacheFolder;
    private Map<String, BufferedImage[]> imageCache;
    private final Set<UUID> ignoredPlayers = new HashSet<UUID>();

    public EffectManager(Plugin owningPlugin) {
        this(owningPlugin, owningPlugin.getLogger());
    }

    public EffectManager(Plugin owningPlugin, Logger logger) {
        if (owningPlugin == null) {
            throw new IllegalArgumentException("EffectManager must be given a valid owning plugin");
        }
        this.imageCacheFolder = new File(owningPlugin.getDataFolder(), "imagecache");
        this.imageCache = new HashMap<String, BufferedImage[]>();
        this.owningPlugin = owningPlugin;
        this.logger = logger;
        this.effects = new HashMap<Effect, BukkitTask>();
        this.disposed = false;
        this.disposeOnTermination = false;
        effectManagers.add(this);
    }

    public static List<EffectManager> getManagers() {
        return effectManagers;
    }

    public void display(Particle particle, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount, float size, Color color, Material material, byte materialData, double range, List<Player> targetPlayers) {
        ParticleOptions options = new ParticleOptions(offsetX, offsetY, offsetZ, speed, amount, size, color, material, materialData);
        this.getDisplay().display(particle, options, center, range, targetPlayers);
    }

    public void display(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        this.getDisplay().display(particle, options, center, range, targetPlayers);
    }

    private ParticleDisplay getDisplay() {
        if (this.display == null) {
            this.display = ParticleDisplay.newInstance();
        }
        this.display.setManager(this);
        return this.display;
    }

    public Effect start(String effectClass, ConfigurationSection parameters, Location origin, Entity originEntity) {
        return this.start(effectClass, parameters, origin, null, originEntity, null, null);
    }

    public Effect start(String effectClass, ConfigurationSection parameters, Entity originEntity) {
        return this.start(effectClass, parameters, originEntity == null ? null : originEntity.getLocation(), null, originEntity, null, null);
    }

    public Effect start(String effectClass, ConfigurationSection parameters, Location origin) {
        return this.start(effectClass, parameters, origin, null, null, null, null);
    }

    public Effect start(String effectClass, ConfigurationSection parameters, Location origin, Player targetPlayer) {
        return this.start(effectClass, parameters, new DynamicLocation(origin, null), new DynamicLocation(null, null), (ConfigurationSection)null, targetPlayer);
    }

    @Deprecated
    public Effect start(String effectClass, ConfigurationSection parameters, Location origin, Location target, Entity originEntity, Entity targetEntity, Map<String, String> parameterMap) {
        return this.start(effectClass, parameters, new DynamicLocation(origin, originEntity), new DynamicLocation(target, targetEntity), parameterMap);
    }

    @Deprecated
    public Effect start(String effectClass, ConfigurationSection parameters, DynamicLocation origin, DynamicLocation target, Map<String, String> parameterMap) {
        return this.start(effectClass, parameters, origin, target, parameterMap, null);
    }

    public Effect getEffectByClassName(String effectClass) {
        Class<Effect> effectLibClass;
        try {
            effectLibClass = effectClasses.get(effectClass);
            if (effectLibClass == null && !effectClass.contains(".")) {
                effectClass = "io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect." + effectClass;
                if (!effectClass.endsWith("Effect")) {
                    effectClass = effectClass + "Effect";
                }
                effectLibClass = effectClasses.get(effectClass);
            }
            if (effectLibClass == null) {
                effectLibClass = Class.forName(effectClass);
                effectClasses.put(effectClass, effectLibClass);
            }
        }
        catch (Throwable ex) {
            this.onError("Error loading EffectLib class: " + effectClass, ex);
            return null;
        }
        Effect effect = null;
        try {
            Constructor<Effect> constructor = effectLibClass.getConstructor(EffectManager.class);
            effect = constructor.newInstance(this);
        }
        catch (Exception ex) {
            this.onError("Error loading EffectLib class: " + effectClass, ex);
        }
        return effect;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void start(Effect effect) {
        if (this.disposed) {
            throw new IllegalStateException("EffectManager is disposed and not able to accept any effects.");
        }
        if (this.disposeOnTermination) {
            throw new IllegalStateException("EffectManager is awaiting termination to dispose and not able to accept any effects.");
        }
        if (this.effects.containsKey(effect)) {
            effect.cancel(false);
        }
        if (!this.owningPlugin.isEnabled()) {
            return;
        }
        BukkitScheduler s = Bukkit.getScheduler();
        BukkitTask task = null;
        switch (effect.getType()) {
            case INSTANT: {
                if (effect.isAsynchronous()) {
                    task = s.runTaskAsynchronously(this.owningPlugin, (Runnable)effect);
                    break;
                }
                task = s.runTask(this.owningPlugin, (Runnable)effect);
                break;
            }
            case DELAYED: {
                if (effect.isAsynchronous()) {
                    task = s.runTaskLaterAsynchronously(this.owningPlugin, (Runnable)effect, (long)effect.getDelay());
                    break;
                }
                task = s.runTaskLater(this.owningPlugin, (Runnable)effect, (long)effect.getDelay());
                break;
            }
            case REPEATING: {
                task = effect.isAsynchronous() ? s.runTaskTimerAsynchronously(this.owningPlugin, (Runnable)effect, (long)effect.getDelay(), (long)effect.getPeriod()) : s.runTaskTimer(this.owningPlugin, (Runnable)effect, (long)effect.getDelay(), (long)effect.getPeriod());
            }
        }
        EffectManager effectManager = this;
        synchronized (effectManager) {
            effect.setStartTime(System.currentTimeMillis());
            this.effects.put(effect, task);
        }
    }

    public Effect getEffect(String effectClass, ConfigurationSection parameters, DynamicLocation origin, DynamicLocation target, ConfigurationSection parameterMap, Player targetPlayer, String logContext) {
        Effect effect = this.getEffectByClassName(effectClass);
        if (effect == null) {
            return null;
        }
        if (parameters.contains("particle_offset")) {
            parameters.set("particle_offset_x", parameters.get("particle_offset"));
            parameters.set("particle_offset_y", parameters.get("particle_offset"));
            parameters.set("particle_offset_z", parameters.get("particle_offset"));
            parameters.set("particle_offset", null);
        }
        if (parameters.contains("particleOffset")) {
            parameters.set("particleOffsetX", parameters.get("particleOffset"));
            parameters.set("particleOffsetY", parameters.get("particleOffset"));
            parameters.set("particleOffsetZ", parameters.get("particleOffset"));
            parameters.set("particleOffset", null);
        }
        Set keys = parameters.getKeys(false);
        for (String key : keys) {
            if (key.equals("class")) continue;
            this.setField(effect, key, parameters, parameterMap, logContext);
        }
        effect.initialize();
        if (origin != null) {
            effect.setDynamicOrigin(origin);
        }
        effect.setDynamicTarget(target);
        if (targetPlayer != null) {
            effect.setTargetPlayer(targetPlayer);
        }
        return effect;
    }

    @Deprecated
    public Effect start(String effectClass, ConfigurationSection parameters, DynamicLocation origin, DynamicLocation target, Map<String, String> parameterMap, Player targetPlayer) {
        ConfigurationSection configMap = null;
        if (parameterMap != null) {
            configMap = ConfigUtils.toStringConfiguration(parameterMap);
        }
        return this.start(effectClass, parameters, origin, target, configMap, targetPlayer);
    }

    public Effect start(String effectClass, ConfigurationSection parameters, DynamicLocation origin, DynamicLocation target, ConfigurationSection parameterMap, Player targetPlayer) {
        Effect effect = this.getEffect(effectClass, parameters, origin, target, parameterMap, targetPlayer);
        if (effect == null) {
            return null;
        }
        effect.start();
        return effect;
    }

    public void cancel(boolean callback) {
        ArrayList<Effect> allEffects = new ArrayList<Effect>(this.effects.keySet());
        for (Effect effect : allEffects) {
            effect.cancel(callback);
        }
    }

    public void done(Effect effect) {
        this.removeEffect(effect);
        if (effect.callback != null && this.owningPlugin.isEnabled()) {
            Bukkit.getScheduler().runTask(this.owningPlugin, effect.callback);
        }
        if (this.disposeOnTermination && this.effects.isEmpty()) {
            this.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeEffect(Effect effect) {
        EffectManager effectManager = this;
        synchronized (effectManager) {
            BukkitTask existingTask = this.effects.get(effect);
            if (existingTask != null) {
                existingTask.cancel();
            }
            this.effects.remove(effect);
        }
    }

    @Override
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.cancel(false);
        this.effects = null;
        this.owningPlugin = null;
        this.logger = null;
        this.display = null;
        this.imageCache = null;
        this.owningPlugin = null;
        this.imageCacheFolder = null;
        effectManagers.remove(this);
    }

    public void disposeOnTermination() {
        this.disposeOnTermination = true;
        if (this.effects.isEmpty()) {
            this.dispose();
        }
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public boolean isDisposedOnTermination() {
        return this.disposeOnTermination;
    }

    public void enableDebug(boolean enable) {
        this.debug = enable;
    }

    public void enableStackTraces(boolean enable) {
        this.stackTraces = enable;
    }

    public boolean isDebugEnabled() {
        return this.debug;
    }

    public void onError(Throwable ex) {
        this.getLogger().log(Level.SEVERE, "Unexpected EffectLib Error: " + ex.getMessage(), ex);
    }

    public Effect getEffect(String effectClass, ConfigurationSection parameters, DynamicLocation origin, DynamicLocation target, ConfigurationSection parameterMap, Player targetPlayer) {
        return this.getEffect(effectClass, parameters, origin, target, parameterMap, targetPlayer, "Unknown");
    }

    public void onError(String message, Throwable ex) {
        if (this.debug) {
            if (this.stackTraces) {
                this.getLogger().log(Level.WARNING, message, ex);
            } else {
                this.getLogger().log(Level.WARNING, message);
            }
        }
    }

    public Logger getLogger() {
        return this.logger;
    }

    public Map<Effect, BukkitTask> getEffects() {
        return this.effects;
    }

    public int getParticleRange() {
        return this.visibleRange;
    }

    public void setParticleRange(int range) {
        this.visibleRange = range;
    }

    public Plugin getOwningPlugin() {
        return this.owningPlugin;
    }

    public void onError(String message) {
        if (this.debug) {
            this.getLogger().log(Level.WARNING, message);
        }
    }

    protected boolean setField(Object effect, String key, ConfigurationSection section, ConfigurationSection parameterMap, String logContext) {
        try {
            Field field;
            logContext = logContext == null ? "(?)" : logContext;
            String stringValue = section.getString(key);
            String fieldKey = key;
            if (key.contains("-")) {
                key = key.replace("-", "_");
            }
            if (key.contains("_")) {
                key = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
            }
            ConfigurationSection fieldSection = section;
            if (parameterMap != null && stringValue.startsWith("$") && parameterMap.contains(stringValue)) {
                fieldKey = stringValue;
                fieldSection = parameterMap;
            }
            if ((field = effect.getClass().getField(key)).getType().equals(Integer.TYPE) || field.getType().equals(Integer.class)) {
                int intValue = Integer.MAX_VALUE;
                if (!ConfigUtils.isMaxValue(stringValue)) {
                    intValue = fieldSection.getInt(fieldKey);
                }
                field.set(effect, intValue);
            } else if (field.getType().equals(Float.TYPE) || field.getType().equals(Float.class)) {
                float floatValue = Float.MAX_VALUE;
                if (!ConfigUtils.isMaxValue(stringValue)) {
                    floatValue = (float)fieldSection.getDouble(fieldKey);
                }
                field.set(effect, Float.valueOf(floatValue));
            } else if (field.getType().equals(Double.TYPE) || field.getType().equals(Double.class)) {
                double doubleValue = Double.MAX_VALUE;
                if (!ConfigUtils.isMaxValue(stringValue)) {
                    doubleValue = fieldSection.getDouble(fieldKey);
                }
                field.set(effect, doubleValue);
            } else if (field.getType().equals(Boolean.TYPE) || field.getType().equals(Boolean.class)) {
                field.set(effect, fieldSection.getBoolean(fieldKey));
            } else if (field.getType().equals(Long.TYPE) || field.getType().equals(Long.class)) {
                long longValue = Long.MAX_VALUE;
                if (!ConfigUtils.isMaxValue(stringValue)) {
                    longValue = fieldSection.getLong(fieldKey);
                }
                field.set(effect, longValue);
            } else if (field.getType().equals(Short.TYPE) || field.getType().equals(Short.class)) {
                short shortValue = Short.MAX_VALUE;
                if (!ConfigUtils.isMaxValue(stringValue)) {
                    shortValue = (short)fieldSection.getInt(fieldKey);
                }
                field.set(effect, shortValue);
            } else if (field.getType().equals(Byte.TYPE) || field.getType().equals(Byte.class)) {
                byte byteValue = 127;
                if (!ConfigUtils.isMaxValue(stringValue)) {
                    byteValue = (byte)fieldSection.getInt(fieldKey);
                }
                field.set(effect, byteValue);
            } else if (field.getType().equals(String.class)) {
                String value = fieldSection.getString(fieldKey);
                field.set(effect, value);
            } else if (field.getType().equals(Color.class)) {
                Integer rgb;
                String value = fieldSection.getString(fieldKey);
                if (value.equalsIgnoreCase("random")) {
                    byte red = (byte)(Math.random() * 255.0);
                    byte green = (byte)(Math.random() * 255.0);
                    byte blue = (byte)(Math.random() * 255.0);
                    rgb = red << 16 | green << 8 | blue;
                } else {
                    if (value.startsWith("#")) {
                        value = value.substring(1);
                    }
                    rgb = Integer.parseInt(value, 16);
                }
                Color color = Color.fromRGB((int)rgb);
                field.set(effect, color);
            } else if (Map.class.isAssignableFrom(field.getType()) && section.isConfigurationSection(key)) {
                Map map = (Map)field.get(effect);
                ConfigurationSection subSection = section.getConfigurationSection(key);
                Set keys = subSection.getKeys(false);
                for (String mapKey : keys) {
                    map.put(mapKey, subSection.get(mapKey));
                }
            } else if (Map.class.isAssignableFrom(field.getType()) && Map.class.isAssignableFrom(section.get(key).getClass())) {
                field.set(effect, section.get(key));
            } else if (ConfigurationSection.class.isAssignableFrom(field.getType())) {
                ConfigurationSection configSection = ConfigUtils.getConfigurationSection(section, key);
                if (parameterMap != null) {
                    ConfigurationSection baseConfiguration = configSection;
                    configSection = new MemoryConfiguration();
                    Set keys = baseConfiguration.getKeys(false);
                    for (String baseKey : keys) {
                        Object baseValue = baseConfiguration.get(baseKey);
                        if (baseValue instanceof String && ((String)baseValue).startsWith("$")) {
                            String parameterValue = parameterMap.getString((String)baseValue);
                            baseValue = parameterValue == null ? baseValue : parameterValue;
                        }
                        configSection.set(baseKey, baseValue);
                    }
                }
                field.set(effect, configSection);
            } else if (field.getType().equals(Vector.class)) {
                String value = fieldSection.getString(fieldKey);
                String[] pieces = value.split(",");
                double x = pieces.length > 0 ? Double.parseDouble(pieces[0]) : 0.0;
                double y = pieces.length > 1 ? Double.parseDouble(pieces[1]) : 0.0;
                double z = pieces.length > 2 ? Double.parseDouble(pieces[2]) : 0.0;
                field.set(effect, new Vector(x, y, z));
            } else if (field.getType().equals(Particle.class)) {
                String value = fieldSection.getString(fieldKey);
                if (!ParticleDisplay.hasColorTransition() && value.equalsIgnoreCase("DUST_COLOR_TRANSITION")) {
                    value = "REDSTONE";
                }
                Particle particleValue = Particle.valueOf((String)value.toUpperCase());
                field.set(effect, particleValue);
            } else if (field.getType().isEnum()) {
                Class<?> enumType = field.getType();
                String value = fieldSection.getString(fieldKey);
                Object enumValue = Enum.valueOf(enumType, value.toUpperCase());
                field.set(effect, enumValue);
            } else if (field.getType().equals(Font.class)) {
                String value = fieldSection.getString(fieldKey);
                Font font = Font.decode(value);
                field.set(effect, font);
            } else if (field.getType().equals(CustomSound.class)) {
                String value = fieldSection.getString(fieldKey);
                field.set(effect, new CustomSound(value));
            } else {
                this.onError("Unable to assign EffectLib property " + key + " of class " + effect.getClass().getSimpleName() + " in " + logContext);
                return false;
            }
            return true;
        }
        catch (Exception ex) {
            this.onError("Error assigning EffectLib property " + key + " of class " + effect.getClass().getSimpleName() + " in " + logContext + ": " + ex.getMessage(), ex);
            return false;
        }
    }

    public static void disposeAll() {
        Iterator<EffectManager> i = effectManagers.iterator();
        while (i.hasNext()) {
            EffectManager em = i.next();
            i.remove();
            em.dispose();
        }
    }

    public void setImageCacheFolder(File folder) {
        this.imageCacheFolder = folder;
    }

    public File getImageCacheFolder() {
        return this.imageCacheFolder;
    }

    public void loadImage(final String fileName, final ImageLoadCallback callback) {
        BufferedImage[] images = this.imageCache.get(fileName);
        if (images != null) {
            callback.loaded(images);
            return;
        }
        this.owningPlugin.getServer().getScheduler().runTaskAsynchronously(this.owningPlugin, (Runnable)new ImageLoadTask(this, fileName, new ImageLoadCallback(){

            @Override
            public void loaded(final BufferedImage[] images) {
                EffectManager.this.owningPlugin.getServer().getScheduler().runTask(EffectManager.this.owningPlugin, new Runnable(){

                    @Override
                    public void run() {
                        EffectManager.this.imageCache.put(fileName, images);
                        callback.loaded(images);
                    }
                });
            }
        }));
    }

    public void registerEffectClass(String key, Class<? extends Effect> effectClass) {
        effectClasses.put(key, effectClass);
    }

    public boolean isPlayerIgnored(Player player) {
        return this.ignoredPlayers.contains(player.getUniqueId());
    }

    public void ignorePlayer(Player player, boolean ignore) {
        if (ignore) {
            this.ignoredPlayers.add(player.getUniqueId());
        } else {
            this.ignoredPlayers.remove(player.getUniqueId());
        }
    }
}

