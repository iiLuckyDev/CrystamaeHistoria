/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleDisplay;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.util.ParticleOptions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@Deprecated
public enum ParticleEffect {
    EXPLOSION_NORMAL("explode"),
    EXPLOSION_LARGE("largeexplode"),
    EXPLOSION_HUGE("hugeexplosion"),
    FIREWORKS_SPARK("fireworksSpark"),
    WATER_BUBBLE("bubble"),
    WATER_SPLASH("splash"),
    WATER_WAKE("wake"),
    SUSPENDED("suspended"),
    SUSPENDED_DEPTH("depthSuspend"),
    CRIT("crit"),
    CRIT_MAGIC("magicCrit"),
    SMOKE_NORMAL("smoke"),
    SMOKE_LARGE("largesmoke"),
    SPELL("spell"),
    SPELL_INSTANT("instantSpell"),
    SPELL_MOB("mobSpell"),
    SPELL_MOB_AMBIENT("mobSpellAmbient"),
    SPELL_WITCH("witchMagic"),
    DRIP_WATER("dripWater"),
    DRIP_LAVA("dripLava"),
    VILLAGER_HAPPY("happyVillager"),
    VILLAGER_ANGRY("angryVillager"),
    TOWN_AURA("townaura"),
    NOTE("note"),
    PORTAL("portal"),
    ENCHANTMENT_TABLE("enchantmenttable"),
    FLAME("flame"),
    LAVA("lava"),
    CLOUD("cloud"),
    REDSTONE("reddust"),
    SNOWBALL("snowballpoof"),
    SNOW_SHOVELL("snowshovel"),
    SLIME("slime"),
    HEART("heart"),
    BARRIER("barrier"),
    ITEM_CRACK("iconcrack"),
    BLOCK_CRACK("blockcrack"),
    BLOCK_DUST("blockdust"),
    WATER_DROP("droplet"),
    MOB_APPEARANCE("mobappearance"),
    DRAGON_BREATH("dragonbreath"),
    END_ROD("endRod"),
    DAMAGE_INDICATOR("damageIndicator"),
    SWEEP_ATTACK("sweepAttack"),
    FALLING_DUST("fallingdust"),
    TOTEM("totem"),
    SPIT("spit"),
    SQUID_INK("squid_ink"),
    BUBBLE_POP("bubble_pop"),
    CURRENT_DOWN("current_down"),
    BUBBLE_COLUMN_UP("bubble_column_up"),
    NAUTILUS("nautilus"),
    FOOTSTEP("footstep"),
    ITEM_TAKE("item_take");

    private Particle particle;
    private final String name;
    private static ParticleDisplay display;
    private static final Map<String, ParticleEffect> NAME_MAP;

    private ParticleEffect(String name) {
        this.name = name;
        try {
            this.particle = Particle.valueOf((String)this.name());
        }
        catch (Exception ex) {
            this.particle = null;
        }
    }

    public static ParticleEffect fromName(String name) {
        for (Map.Entry<String, ParticleEffect> entry : NAME_MAP.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(name)) continue;
            return entry.getValue();
        }
        return null;
    }

    public boolean isSupported() {
        return this.particle != null;
    }

    public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range, List<Player> targetPlayers) throws ParticleVersionException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        this.display(this.particle, center, offsetX, offsetY, offsetZ, speed, amount, 1.0f, null, null, (byte)0, range, targetPlayers);
    }

    public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range) throws ParticleVersionException {
        this.display(offsetX, offsetY, offsetZ, speed, amount, center, range, null);
    }

    public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players) throws ParticleVersionException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        this.display(this.particle, center, offsetX, offsetY, offsetZ, speed, amount, 1.0f, null, null, (byte)0, 0.0, players);
    }

    public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player ... players) throws ParticleVersionException {
        this.display(offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
    }

    public void display(Vector direction, float speed, Location center, double range) throws ParticleVersionException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        this.display(this.particle, center, (float)direction.getX(), (float)direction.getY(), (float)direction.getZ(), speed, 1, 1.0f, null, null, (byte)0, range, null);
    }

    public void display(Vector direction, float speed, Location center, List<Player> players) throws ParticleVersionException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        this.display(this.particle, center, (float)direction.getX(), (float)direction.getY(), (float)direction.getZ(), speed, 1, 1.0f, null, null, (byte)0, 0.0, players);
    }

    public void display(Vector direction, float speed, Location center, Player ... players) throws ParticleVersionException {
        this.display(direction, speed, center, Arrays.asList(players));
    }

    public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range, List<Player> targetPlayers) throws ParticleVersionException, ParticleDataException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        Material material = null;
        byte materialData = 0;
        if (data != null) {
            material = data.material;
            materialData = data.data;
        }
        this.display(this.particle, center, offsetX, offsetY, offsetZ, speed, amount, 1.0f, null, material, materialData, range, targetPlayers);
    }

    public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range) throws ParticleVersionException, ParticleDataException {
        this.display(data, offsetX, offsetY, offsetZ, speed, amount, center, range, null);
    }

    public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        Material material = null;
        byte materialData = 0;
        if (data != null) {
            material = data.material;
            materialData = data.data;
        }
        this.display(this.particle, center, offsetX, offsetY, offsetZ, speed, amount, 1.0f, null, material, materialData, 0.0, players);
    }

    public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player ... players) throws ParticleVersionException, ParticleDataException {
        this.display(data, offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
    }

    public void display(ParticleData data, Vector direction, float speed, Location center, double range) throws ParticleVersionException, ParticleDataException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        Material material = null;
        byte materialData = 0;
        if (data != null) {
            material = data.material;
            materialData = data.data;
        }
        this.display(this.particle, center, (float)direction.getX(), (float)direction.getY(), (float)direction.getZ(), speed, 1, 1.0f, null, material, materialData, range, null);
    }

    public void display(ParticleData data, Vector direction, float speed, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException {
        if (!this.isSupported()) {
            throw new ParticleVersionException("The " + (Object)((Object)this) + " particle effect is not supported by your server version.");
        }
        Material material = null;
        byte materialData = 0;
        if (data != null) {
            material = data.material;
            materialData = data.data;
        }
        this.display(this.particle, center, (float)direction.getX(), (float)direction.getY(), (float)direction.getZ(), speed, 1, 1.0f, null, material, materialData, 0.0, players);
    }

    public void display(ParticleData data, Vector direction, float speed, Location center, Player ... players) throws ParticleVersionException, ParticleDataException {
        this.display(data, direction, speed, center, Arrays.asList(players));
    }

    public void display(Location center, Color color, double range) {
        this.display(null, center, color, range, 0.0f, 0.0f, 0.0f, 1.0f, 0);
    }

    public void display(ParticleData data, Location center, Color color, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        this.display(data, center, color, range, offsetX, offsetY, offsetZ, speed, amount, null);
    }

    public void display(ParticleData data, Location center, Color color, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount, List<Player> targetPlayers) {
        Material material = null;
        byte materialData = 0;
        if (data != null) {
            material = data.material;
            materialData = data.data;
        }
        this.display(this.particle, center, offsetX, offsetY, offsetZ, speed, amount, 1.0f, color, material, materialData, range, targetPlayers);
    }

    public void display(Particle particle, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount, float size, Color color, Material material, byte materialData, double range, List<Player> targetPlayers) {
        if (display == null) {
            display = ParticleDisplay.newInstance();
        }
        ParticleOptions options = new ParticleOptions(offsetX, offsetY, offsetZ, speed, amount, size, color, material, materialData);
        display.display(particle, options, center, range, targetPlayers);
    }

    static {
        NAME_MAP = new HashMap<String, ParticleEffect>();
        for (ParticleEffect effect : ParticleEffect.values()) {
            NAME_MAP.put(effect.name, effect);
        }
    }

    @Deprecated
    private static final class ParticleVersionException
    extends RuntimeException {
        private static final long serialVersionUID = 3203085387160737484L;

        public ParticleVersionException(String message) {
            super(message);
        }
    }

    @Deprecated
    private static final class ParticleDataException
    extends RuntimeException {
        private static final long serialVersionUID = 3203085387160737484L;

        public ParticleDataException(String message) {
            super(message);
        }
    }

    @Deprecated
    public static final class BlockData
    extends ParticleData {
        public BlockData(Material material, byte data) throws IllegalArgumentException {
            super(material, data);
            if (!material.isBlock()) {
                throw new IllegalArgumentException("The material is not a block");
            }
        }
    }

    @Deprecated
    public static final class ItemData
    extends ParticleData {
        public ItemData(Material material, byte data) {
            super(material, data);
        }
    }

    @Deprecated
    public static abstract class ParticleData {
        private final Material material;
        private final byte data;

        public ParticleData(Material material, byte data) {
            this.material = material;
            this.data = data;
        }

        public Material getMaterial() {
            return this.material;
        }

        public byte getData() {
            return this.data;
        }
    }
}

