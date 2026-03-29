/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataHolder
 */
package io.github.sefiraat.crystamaehistoria.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.SubCommand;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstanceStave;
import io.github.sefiraat.crystamaehistoria.slimefun.Tools;
import io.github.sefiraat.crystamaehistoria.slimefun.items.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

public class TestWand
extends SubCommand {
    public TestWand() {
        super("test-wand", "gives a wand with the selected spell", true);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (args.length != 2) {
                return;
            }
            int power = Integer.parseInt(args[1]);
            if (power <= 2) {
                ItemStack stave;
                if (power == 1) {
                    stave = Tools.getStaveBasic().getItem().clone();
                } else if (power == 2) {
                    stave = Tools.getStaveAdvanced().getItem().clone();
                } else {
                    return;
                }
                InstanceStave staveInstance = new InstanceStave(stave);
                Map<SpellSlot, InstancePlate> map = staveInstance.getSpellInstanceMap();
                InstancePlate plateInstance = new InstancePlate(1, SpellType.valueOf(args[0]), 9999);
                map.put(SpellSlot.LEFT_CLICK, plateInstance);
                ItemMeta itemMeta = stave.getItemMeta();
                DataTypeMethods.setCustom((PersistentDataHolder)itemMeta, Keys.PDC_STAVE_STORAGE, PersistentStaveDataType.TYPE, staveInstance.getSpellInstanceMap());
                stave.setItemMeta(itemMeta);
                staveInstance.buildLore();
                player.getInventory().addItem(new ItemStack[]{stave});
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void complete(CommandSender commandSender, String[] strings, List<String> list) {
        if (strings.length == 1) {
            for (SpellType spell : SpellType.getEnabledSpells()) {
                list.add(spell.name());
            }
        }
    }
}

