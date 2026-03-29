/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
 *  io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
 *  io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
 *  io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler
 *  io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler
 *  lombok.Generated
 *  me.mrCookieSlime.Slimefun.api.BlockStorage
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
 *  me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
 *  org.bukkit.FluidCollisionMode
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.block.BlockPlaceEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.util.RayTraceResult
 *  org.bukkit.util.Vector
 */
package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import java.util.List;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import lombok.Generated;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class MobFan
extends TickingMenuBlock {
    protected static final String ID_DIRECTION = "CH_DIRECTION";
    protected static final String ID_UUID = "CH_UUID";
    protected static final int[] BACKGROUND_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 16, 17, 18, 19, 20, 21, 23, 25, 26, 27, 28, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    protected static final int SET_UP = 11;
    protected static final int SET_DOWN = 29;
    protected static final int SET_NORTH = 14;
    protected static final int SET_WEST = 22;
    protected static final int SET_SOUTH = 32;
    protected static final int SET_EAST = 24;
    private final double range;

    @ParametersAreNonnullByDefault
    public MobFan(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double range) {
        super(category, item, recipeType, recipe);
        this.range = range;
    }

    public void preRegister() {
        this.addItemHandler(new ItemHandler[]{this.onBlockPlace(), this.onBlockBreak()});
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(this, false){

            @ParametersAreNonnullByDefault
            public void onPlayerPlace(BlockPlaceEvent event) {
                BlockStorage.addBlockInfo((Block)event.getBlock(), (String)MobFan.ID_UUID, (String)event.getPlayer().getUniqueId().toString());
                BlockStorage.addBlockInfo((Block)event.getBlock(), (String)MobFan.ID_DIRECTION, (String)BlockFace.SELF.name());
            }
        };
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(this, false, false){

            @ParametersAreNonnullByDefault
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                BlockStorage.clearBlockInfo((Block)blockBreakEvent.getBlock());
            }
        };
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        BlockFace direction = BlockFace.valueOf((String)BlockStorage.getLocationInfo((Location)block.getLocation(), (String)ID_DIRECTION));
        Vector facingVector = direction.getDirection();
        UUID owner = UUID.fromString(BlockStorage.getLocationInfo((Location)block.getLocation(), (String)ID_UUID));
        if (direction == BlockFace.SELF) {
            return;
        }
        Location location = block.getLocation();
        RayTraceResult result = location.getWorld().rayTraceBlocks(location.add(facingVector), facingVector, this.range, FluidCollisionMode.ALWAYS, false);
        double finalRange = this.range;
        if (result != null && result.getHitBlock() != null) {
            finalRange = result.getHitBlock().getLocation().distance(block.getLocation());
        }
        int i = 0;
        while ((double)i < finalRange + 0.5) {
            Location offsetLocation = location.clone().add(direction.getDirection().clone().multiply(i));
            for (Entity entity : block.getWorld().getNearbyEntities(offsetLocation, 0.5, 0.5, 0.5)) {
                Player player;
                if (entity instanceof Player && (player = (Player)entity).getGameMode() != GameMode.SURVIVAL) {
                    return;
                }
                GeneralUtils.pushEntity(owner, facingVector, entity, 1.0);
            }
            ++i;
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(BACKGROUND_SLOTS);
        blockMenuPreset.addItem(14, GuiElements.getDirectionalSlotPane(BlockFace.NORTH, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(32, GuiElements.getDirectionalSlotPane(BlockFace.SOUTH, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(24, GuiElements.getDirectionalSlotPane(BlockFace.EAST, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(22, GuiElements.getDirectionalSlotPane(BlockFace.WEST, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(11, GuiElements.getDirectionalSlotPane(BlockFace.UP, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(29, GuiElements.getDirectionalSlotPane(BlockFace.DOWN, false), (player, i, itemStack, clickAction) -> false);
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu menu, Block b) {
        BlockFace direction = BlockFace.valueOf((String)BlockStorage.getLocationInfo((Location)b.getLocation(), (String)ID_DIRECTION));
        this.setDirection(menu, direction);
        menu.addMenuClickHandler(14, (player, i, itemStack, clickAction) -> this.setDirection(menu, BlockFace.NORTH));
        menu.addMenuClickHandler(32, (player, i, itemStack, clickAction) -> this.setDirection(menu, BlockFace.SOUTH));
        menu.addMenuClickHandler(24, (player, i, itemStack, clickAction) -> this.setDirection(menu, BlockFace.EAST));
        menu.addMenuClickHandler(22, (player, i, itemStack, clickAction) -> this.setDirection(menu, BlockFace.WEST));
        menu.addMenuClickHandler(11, (player, i, itemStack, clickAction) -> this.setDirection(menu, BlockFace.UP));
        menu.addMenuClickHandler(29, (player, i, itemStack, clickAction) -> this.setDirection(menu, BlockFace.DOWN));
    }

    @ParametersAreNonnullByDefault
    private boolean setDirection(BlockMenu blockMenu, BlockFace blockFace) {
        BlockStorage.addBlockInfo((Block)blockMenu.getBlock(), (String)ID_DIRECTION, (String)blockFace.name());
        blockMenu.replaceExistingItem(11, GuiElements.getDirectionalSlotPane(BlockFace.UP, false));
        blockMenu.replaceExistingItem(29, GuiElements.getDirectionalSlotPane(BlockFace.DOWN, false));
        blockMenu.replaceExistingItem(14, GuiElements.getDirectionalSlotPane(BlockFace.NORTH, false));
        blockMenu.replaceExistingItem(32, GuiElements.getDirectionalSlotPane(BlockFace.SOUTH, false));
        blockMenu.replaceExistingItem(24, GuiElements.getDirectionalSlotPane(BlockFace.EAST, false));
        blockMenu.replaceExistingItem(22, GuiElements.getDirectionalSlotPane(BlockFace.WEST, false));
        switch (blockFace) {
            case UP: {
                blockMenu.replaceExistingItem(11, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case DOWN: {
                blockMenu.replaceExistingItem(29, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case NORTH: {
                blockMenu.replaceExistingItem(14, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case SOUTH: {
                blockMenu.replaceExistingItem(32, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case EAST: {
                blockMenu.replaceExistingItem(24, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case WEST: {
                blockMenu.replaceExistingItem(22, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case SELF: {
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected value: " + String.valueOf(blockFace));
            }
        }
        return false;
    }

    @Generated
    public double getRange() {
        return this.range;
    }
}

