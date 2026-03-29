/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
 *  lombok.Generated
 *  me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  org.bukkit.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.block.Block
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.LeatherArmorMeta
 *  org.bukkit.persistence.PersistentDataHolder
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slikey.effectlib.effect.SphereEffect;
import io.github.sefiraat.crystamaehistoria.slimefun.items.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.DisplayStandHolder;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.plates.MagicalPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.satchel.CrystamageSatchel;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.satchel.SatchelInstance;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentSatchelInstanceType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.util.Vector;

public class LiquefactionBasinCache
extends DisplayStandHolder {
    public static final double LOWEST_LEVEL = -1.7;
    public static final double HIGHEST_LEVEL = -1.0;
    protected static final String CH_LEVEL_PREFIX = "ch_c_lvl:";
    private static final Map<SpellType, RecipeSpell> RECIPES_SPELL = new HashMap<SpellType, RecipeSpell>();
    private static final Map<SlimefunItem, RecipeItem> RECIPES_ITEMS = new HashMap<SlimefunItem, RecipeItem>();
    private final double maxVolume;
    private final Map<StoryType, Integer> contentMap = new EnumMap<StoryType, Integer>(StoryType.class);

    @ParametersAreNonnullByDefault
    public LiquefactionBasinCache(BlockMenu blockMenu, double maxVolume) {
        super(blockMenu);
        this.maxVolume = maxVolume;
        String activePlayerString = BlockStorage.getLocationInfo((Location)blockMenu.getLocation(), (String)"BS_CP_ACTIVE_PLAYER");
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    public static void addSpellRecipe(SpellType spellType, RecipeSpell recipeSpell) {
        RECIPES_SPELL.put(spellType, recipeSpell);
    }

    public static void addCraftingRecipe(SlimefunItem slimefunItem, RecipeItem recipeItem) {
        RECIPES_ITEMS.put(slimefunItem, recipeItem);
    }

    @ParametersAreNonnullByDefault
    public void consumeItems() {
        Collection entities = this.getWorld().getNearbyEntities(this.getLocation().clone().add(0.5, 0.5, 0.5), 0.3, 0.3, 0.3, Item.class::isInstance);
        for (Entity entity : entities) {
            Item item = (Item)entity;
            SlimefunItem slimefunItem = SlimefunItem.getByItem((ItemStack)item.getItemStack());
            if (slimefunItem instanceof Crystal) {
                Crystal crystal = (Crystal)slimefunItem;
                this.addCrystamae(crystal.getType(), crystal.getRarity(), item);
            } else if (slimefunItem instanceof BlankPlate) {
                this.processBlankPlate(item, (BlankPlate)slimefunItem);
            } else if (slimefunItem instanceof ChargedPlate) {
                this.processChargedPlate(item, (ChargedPlate)slimefunItem);
            } else if (!this.processOtherItem(item)) {
                this.rejectItem(item);
            }
            this.syncBlock();
        }
        if (this.getFillLevel() > 0 && GeneralUtils.testChance(1, 5)) {
            this.summonBoilingParticles();
        }
    }

    @ParametersAreNonnullByDefault
    private void rejectItem(Item item) {
        double velX = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        double velZ = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        item.setVelocity(new Vector(velX, 0.5, velZ));
    }

    @ParametersAreNonnullByDefault
    private void addCrystamae(StoryType type, StoryRarity rarity, Item item) {
        int numberInStack = item.getItemStack().getAmount();
        int amount = Crystal.getRarityValueMap().get((Object)rarity) * numberInStack;
        if ((double)(this.getFillLevel() + amount) > this.maxVolume) {
            this.rejectItem(item);
        } else {
            if (this.contentMap.containsKey((Object)type)) {
                this.contentMap.put(type, this.contentMap.get((Object)type) + amount);
            } else {
                this.contentMap.put(type, amount);
            }
            this.updateDisplay();
            item.remove();
            this.summonConsumeParticles();
        }
    }

    private void updateDisplay() {
        if (this.contentMap.isEmpty()) {
            return;
        }
        ArmorStand armorStand = this.getDisplayStand();
        int amount = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        for (Map.Entry<StoryType, Integer> entry : this.contentMap.entrySet()) {
            Color color = ThemeType.getByType(entry.getKey()).getColor().getColor();
            int additionalAmount = entry.getValue();
            amount += additionalAmount;
            red += color.getRed() * additionalAmount;
            green += color.getGreen() * additionalAmount;
            blue += color.getBlue() * additionalAmount;
        }
        if (amount > 0) {
            ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET);
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)itemStack.getItemMeta();
            leatherArmorMeta.setColor(org.bukkit.Color.fromRGB((int)(red /= amount), (int)(green /= amount), (int)(blue /= amount)));
            itemStack.setItemMeta((ItemMeta)leatherArmorMeta);
            ArmourStandUtils.setDisplayItem(armorStand, itemStack);
            this.setFillHeight(armorStand);
        }
    }

    public void emptyBasin() {
        this.contentMap.clear();
        this.clearBlockStorage();
        ArmorStand armorStand = this.getDisplayStand();
        ArmourStandUtils.clearDisplayItem(armorStand);
        this.setFillHeight(armorStand);
    }

    private void clearBlockStorage() {
        Config c = BlockStorage.getLocationInfo((Location)this.blockMenu.getLocation());
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : c.getKeys()) {
            if (!key.startsWith(CH_LEVEL_PREFIX)) continue;
            keys.add(key);
        }
        for (String key : keys) {
            BlockStorage.addBlockInfo((Location)this.blockMenu.getLocation(), (String)key, null);
        }
    }

    @ParametersAreNonnullByDefault
    private void setFillHeight(ArmorStand armorStand) {
        double diff = 0.7;
        double incrementAmount = 0.7 / this.maxVolume;
        double amount = incrementAmount * (double)this.getFillLevel();
        Location location = this.blockMenu.getLocation().clone().add(0.5, -1.7 + amount, 0.5);
        armorStand.teleport(location);
    }

    public void syncBlock() {
        for (Map.Entry<StoryType, Integer> e : this.contentMap.entrySet()) {
            BlockStorage.addBlockInfo((Block)this.blockMenu.getBlock(), (String)(CH_LEVEL_PREFIX + String.valueOf((Object)e.getKey())), (String)String.valueOf(e.getValue()));
        }
    }

    @ParametersAreNonnullByDefault
    private void processBlankPlate(Item item, BlankPlate plate) {
        Set<StoryType> set = this.contentMap.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).limit(3L).map(Map.Entry::getKey).collect(Collectors.toSet());
        ItemStack itemStack = item.getItemStack();
        if (set.size() == 3) {
            SlimefunItem.getByItem((ItemStack)itemStack);
            SpellType spellType = this.getMatchingRecipe(set, plate);
            if (spellType != null) {
                item.getWorld().dropItem(item.getLocation(), ChargedPlate.getChargedPlate(plate.getTier(), spellType, this.getFillLevel()));
                if (itemStack.getAmount() > 1) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                } else {
                    item.remove();
                }
                this.summonCatalystParticles();
                if (this.activePlayer != null && !PlayerStatistics.hasUnlockedSpell(this.activePlayer, spellType)) {
                    PlayerStatistics.unlockSpell(this.activePlayer, spellType);
                }
            }
            this.emptyBasin();
        } else {
            this.rejectItem(item);
        }
    }

    private void processChargedPlate(Item item, ChargedPlate plate) {
        ItemStack itemStack = item.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();
        InstancePlate instancePlate = DataTypeMethods.getCustom((PersistentDataHolder)itemMeta, Keys.PDC_PLATE_STORAGE, PersistentPlateDataType.TYPE);
        if (instancePlate == null) {
            CrystamaeHistoria.getInstance().getLogger().warning("The charged plate used has not been configured correctly. /sf cheat charged plates will not work in the Liquefaction Basin. If this is not the case, please raises an issue.");
            item.remove();
            return;
        }
        SpellType currentSpellType = instancePlate.getStoredSpell();
        Set<StoryType> set = this.contentMap.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).limit(3L).map(Map.Entry::getKey).collect(Collectors.toSet());
        if (set.size() == 3) {
            SpellType spellType = this.getMatchingRecipe(set, plate);
            if (spellType != null && spellType == currentSpellType) {
                instancePlate.addCrysta(this.getFillLevel());
                DataTypeMethods.setCustom((PersistentDataHolder)itemMeta, Keys.PDC_PLATE_STORAGE, PersistentPlateDataType.TYPE, instancePlate);
                itemStack.setItemMeta(itemMeta);
                InstancePlate.setPlateLore(itemStack, instancePlate);
                this.summonCatalystParticles();
            }
            this.emptyBasin();
        } else {
            this.rejectItem(item);
        }
    }

    @ParametersAreNonnullByDefault
    private boolean processOtherItem(Item item) {
        SlimefunItem slimefunItem;
        ItemStack itemStack = item.getItemStack();
        List<StoryType> typeList = this.contentMap.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).limit(3L).map(Map.Entry::getKey).collect(Collectors.toList());
        List<Integer> amountList = this.contentMap.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).limit(3L).map(Map.Entry::getValue).collect(Collectors.toList());
        if (typeList.size() == 3 && (slimefunItem = this.getMatchingRecipe(typeList, amountList, itemStack)) != null && !slimefunItem.isDisabled()) {
            ItemStack stackToDrop = slimefunItem.getRecipeOutput().clone();
            if (slimefunItem instanceof CrystamageSatchel) {
                SatchelInstance instance;
                CrystamageSatchel newSatchel = (CrystamageSatchel)slimefunItem;
                SlimefunItem incomingSlimefunItem = SlimefunItem.getByItem((ItemStack)itemStack);
                if (incomingSlimefunItem instanceof CrystamageSatchel) {
                    CrystamageSatchel oldSatchel = (CrystamageSatchel)incomingSlimefunItem;
                    instance = DataTypeMethods.getCustom((PersistentDataHolder)itemStack.getItemMeta(), Keys.PDC_SATCHEL_STORAGE, PersistentSatchelInstanceType.TYPE, new SatchelInstance(System.currentTimeMillis(), oldSatchel.getTier()));
                    instance.setTier(newSatchel.getTier());
                } else {
                    instance = new SatchelInstance(System.currentTimeMillis(), 1);
                }
                ItemMeta itemMeta = stackToDrop.getItemMeta();
                DataTypeMethods.setCustom((PersistentDataHolder)itemMeta, Keys.PDC_SATCHEL_STORAGE, PersistentSatchelInstanceType.TYPE, instance);
                stackToDrop.setItemMeta(itemMeta);
            }
            item.getWorld().dropItem(item.getLocation(), stackToDrop);
            if (itemStack.getAmount() > 1) {
                itemStack.setAmount(itemStack.getAmount() - 1);
            } else {
                item.remove();
            }
            this.summonCatalystParticles();
            this.emptyBasin();
            return true;
        }
        return false;
    }

    private boolean canCraftSatchel(ItemStack incomingItem) {
        List lore = incomingItem.getItemMeta().getLore();
        for (String s : lore) {
            if (!s.equals(String.valueOf(ChatColor.GRAY) + "ID: <ID>")) continue;
            return true;
        }
        return false;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public SpellType getMatchingRecipe(Set<StoryType> set, MagicalPlate magicalPlate) {
        SpellType spellType = null;
        for (Map.Entry<SpellType, RecipeSpell> recipeEntry : RECIPES_SPELL.entrySet()) {
            if (!recipeEntry.getValue().recipeMatches(set, magicalPlate.getTier())) continue;
            spellType = recipeEntry.getKey();
            break;
        }
        return spellType;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public SlimefunItem getMatchingRecipe(List<StoryType> types, List<Integer> amounts, ItemStack itemStack) {
        SlimefunItem slimefunItem = null;
        for (Map.Entry<SlimefunItem, RecipeItem> recipeEntry : RECIPES_ITEMS.entrySet()) {
            if (!recipeEntry.getValue().recipeMatches(types, amounts, itemStack, this.activePlayer)) continue;
            slimefunItem = recipeEntry.getKey();
            break;
        }
        return slimefunItem;
    }

    public int getFillLevel() {
        int amount = 0;
        for (Map.Entry<StoryType, Integer> entry : this.contentMap.entrySet()) {
            amount += entry.getValue().intValue();
        }
        return amount;
    }

    private void summonConsumeParticles() {
        Location location = this.getLocation(true).add(0.0, 0.8, 0.0);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 0, 0.2, 0.0, 0.2, 0.0);
    }

    private void summonBoilingParticles() {
        Location location = this.getLocation(true).add(0.0, 0.8, 0.0);
        location.getWorld().spawnParticle(Particle.SMOKE, location, 0, 0.2, 0.0, 0.5, 0.0);
    }

    private void summonCatalystParticles() {
        SphereEffect sphereEffect = new SphereEffect(CrystamaeHistoria.getEffectManager());
        sphereEffect.particle = Particle.DUST;
        sphereEffect.color = org.bukkit.Color.TEAL;
        sphereEffect.setLocation(this.getLocation(true));
        sphereEffect.radius = 1.0;
        sphereEffect.iterations = 2;
        sphereEffect.start();
    }

    @Generated
    public double getMaxVolume() {
        return this.maxVolume;
    }

    @Generated
    public Map<StoryType, Integer> getContentMap() {
        return this.contentMap;
    }
}

