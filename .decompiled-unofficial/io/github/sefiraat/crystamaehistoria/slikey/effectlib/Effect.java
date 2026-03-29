/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectManager;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.EffectType;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.DynamicLocation;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleOptions;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.RandomUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public abstract class Effect
implements Runnable {
    private String subEffectClass = null;
    public ConfigurationSection subEffect = null;
    public EffectType type = EffectType.INSTANT;
    public Color color = null;
    public List<Color> colorList = null;
    public String colors = null;
    public Color toColor = null;
    public List<Color> toColorList = null;
    public String toColors = null;
    public int arrivalTime;
    @Deprecated
    public float speed = 0.0f;
    public float particleData = 0.0f;
    public int delay = 0;
    public int period = 1;
    public int iterations = 0;
    public Integer duration = null;
    public double probability = 1.0;
    public Runnable callback = null;
    public float visibleRange = 32.0f;
    public boolean autoOrient = false;
    public Vector offset = null;
    public Vector relativeOffset = null;
    public Vector targetOffset = null;
    public float yawOffset = 0.0f;
    public float pitchOffset = 0.0f;
    public Float yaw = null;
    public Float pitch = null;
    public boolean updateLocations = true;
    public boolean updateDirections = true;
    public Player targetPlayer;
    public List<Player> targetPlayers;
    public Material material;
    public byte materialData;
    public int particleCount = 1;
    public float particleOffsetX = 0.0f;
    public float particleOffsetY = 0.0f;
    public float particleOffsetZ = 0.0f;
    public float particleSize = 1.0f;
    public boolean asynchronous = true;
    protected final EffectManager effectManager;
    protected DynamicLocation origin = null;
    protected DynamicLocation target = null;
    protected int maxIterations;
    public boolean disappearWithOriginEntity = false;
    public boolean disappearWithTargetEntity = false;
    private boolean done = false;
    private long startTime;

    public Effect(EffectManager effectManager) {
        if (effectManager == null) {
            throw new IllegalArgumentException("EffectManager cannot be null!");
        }
        this.effectManager = effectManager;
        this.visibleRange = effectManager.getParticleRange();
    }

    protected List<Color> parseColorList(String colors) {
        ArrayList<Color> colorList = new ArrayList<Color>();
        String[] args = colors.split(",");
        if (args.length >= 1) {
            for (String str : args) {
                try {
                    int rgb = Integer.parseInt(str.trim().replace("#", ""), 16);
                    colorList.add(Color.fromRGB((int)rgb));
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
        }
        return colorList;
    }

    protected void initialize() {
        if (this.period < 1) {
            this.period = 1;
        }
        if (this.colors != null) {
            this.colorList = this.parseColorList(this.colors);
        }
        if (this.toColors != null) {
            this.toColorList = this.parseColorList(this.toColors);
        }
        if (this.subEffect != null) {
            this.subEffectClass = this.subEffect.getString("subEffectClass");
        }
    }

    public final void cancel() {
        this.cancel(true);
    }

    public final void cancel(boolean callback) {
        if (callback) {
            this.done();
        } else {
            this.done = true;
        }
    }

    public final boolean isDone() {
        return this.done;
    }

    public abstract void onRun();

    public void onDone() {
    }

    @Override
    public final void run() {
        if (!this.validate()) {
            this.cancel();
            return;
        }
        if (this.done) {
            this.effectManager.removeEffect(this);
            return;
        }
        try {
            if (RandomUtils.checkProbability(this.probability)) {
                this.onRun();
            }
        }
        catch (Exception ex) {
            this.done();
            this.effectManager.onError(ex);
        }
        if (this.type == EffectType.REPEATING) {
            if (this.iterations == -1) {
                return;
            }
            --this.iterations;
            if (this.iterations < 1) {
                this.done();
            }
        } else {
            this.done();
        }
    }

    protected void reset() {
        this.done = false;
    }

    public void prepare() {
        this.reset();
        this.updateDuration();
    }

    public final void start() {
        this.prepare();
        this.effectManager.start(this);
    }

    public final void infinite() {
        this.type = EffectType.REPEATING;
        this.iterations = -1;
    }

    public Entity getEntity() {
        return this.origin == null ? null : this.origin.getEntity();
    }

    public Entity getTargetEntity() {
        return this.target == null ? null : this.target.getEntity();
    }

    public final Location getLocation() {
        return this.origin == null ? null : this.origin.getLocation();
    }

    public final Location getTarget() {
        return this.target == null ? null : this.target.getLocation();
    }

    public void setDynamicOrigin(DynamicLocation location) {
        if (location == null) {
            throw new IllegalArgumentException("Origin Location cannot be null!");
        }
        this.origin = location;
        if (this.offset != null) {
            this.origin.addOffset(this.offset);
        }
        if (this.relativeOffset != null) {
            this.origin.addRelativeOffset(this.relativeOffset);
        }
        this.origin.setDirectionOffset(this.yawOffset, this.pitchOffset);
        this.origin.setYaw(this.yaw);
        this.origin.setPitch(this.pitch);
        this.origin.setUpdateLocation(this.updateLocations);
        this.origin.setUpdateDirection(this.updateDirections);
        this.origin.updateDirection();
    }

    public void setDynamicTarget(DynamicLocation location) {
        this.target = location;
        if (this.target != null && this.targetOffset != null) {
            this.target.addOffset(this.targetOffset);
        }
        if (this.target == null) {
            return;
        }
        this.target.setUpdateLocation(this.updateLocations);
        this.target.setUpdateDirection(this.updateDirections);
    }

    protected final boolean validate() {
        if (this.disappearWithOriginEntity && this.origin != null && !this.origin.hasValidEntity()) {
            return false;
        }
        if (this.disappearWithTargetEntity && this.target != null && !this.target.hasValidEntity()) {
            return false;
        }
        this.updateLocation();
        this.updateTarget();
        Location location = this.getLocation();
        if (location == null) {
            return false;
        }
        if (this.autoOrient) {
            Location targetLocation;
            Location location2 = targetLocation = this.target == null ? null : this.target.getLocation();
            if (targetLocation != null) {
                Vector direction = targetLocation.toVector().subtract(location.toVector());
                location.setDirection(direction);
                targetLocation.setDirection(direction.multiply(-1));
            }
        }
        return true;
    }

    protected void updateDuration() {
        if (this.duration != null) {
            if (this.period < 1) {
                this.period = 1;
            }
            this.iterations = this.duration / this.period / 50;
        }
        this.maxIterations = this.iterations;
    }

    protected void updateLocation() {
        if (this.origin != null) {
            this.origin.update();
        }
    }

    protected void updateTarget() {
        if (this.target != null) {
            this.target.update();
        }
    }

    protected void display(Particle effect, Location location) {
        this.display(effect, location, this.color);
    }

    protected void display(Particle particle, Location location, Color color) {
        this.display(particle, location, color, this.particleData != 0.0f ? this.particleData : this.speed, this.particleCount);
    }

    protected void display(Particle particle, Location location, float speed, int amount) {
        this.display(particle, location, this.color, speed, amount);
    }

    protected void display(Particle particle, Location location, Color color, float speed, int amount) {
        this.display(particle, location, color, this.toColor, speed, amount);
    }

    protected void display(Particle particle, Location location, Color color, Color toColor, float speed, int amount) {
        if (this.particleCount >= 0) {
            if (this.targetPlayers == null && this.targetPlayer != null) {
                this.targetPlayers = new ArrayList<Player>();
                this.targetPlayers.add(this.targetPlayer);
            }
            Color currentColor = color;
            if (this.colorList != null && !this.colorList.isEmpty()) {
                currentColor = this.colorList.get(ThreadLocalRandom.current().nextInt(this.colorList.size()));
            }
            Color currentToColor = toColor;
            if (this.toColorList != null && !this.toColorList.isEmpty()) {
                currentToColor = this.toColorList.get(ThreadLocalRandom.current().nextInt(this.colorList.size()));
            }
            ParticleOptions options = new ParticleOptions(this.particleOffsetX, this.particleOffsetY, this.particleOffsetZ, speed, amount, this.particleSize, currentColor, currentToColor, this.arrivalTime, this.material, this.materialData);
            options.target = this.target;
            this.effectManager.display(particle, options, location, this.visibleRange, this.targetPlayers);
        }
        if (this.subEffectClass != null) {
            this.effectManager.start(this.subEffectClass, this.subEffect, location);
        }
    }

    private void done() {
        this.done = true;
        this.effectManager.done(this);
        this.onDone();
    }

    public EffectType getType() {
        return this.type;
    }

    public boolean isAsynchronous() {
        return this.asynchronous;
    }

    public int getDelay() {
        return this.delay;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setEntity(Entity entity) {
        this.setDynamicOrigin(new DynamicLocation(entity));
    }

    public void setLocation(Location location) {
        this.setDynamicOrigin(new DynamicLocation(location));
    }

    public void setTargetEntity(Entity entity) {
        this.target = new DynamicLocation(entity);
    }

    public void setTargetLocation(Location location) {
        this.target = new DynamicLocation(location);
    }

    public Player getTargetPlayer() {
        return this.targetPlayer;
    }

    public void setTargetPlayer(Player p) {
        this.targetPlayer = p;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void reloadParameters() {
    }
}

