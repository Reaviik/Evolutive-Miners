package com.ctr.evolutiveminers.screem;

import com.ctr.evolutiveminers.blocks.ModBlocks;
import com.ctr.evolutiveminers.blocks.entity.BlackHoleBlockEntity;
import com.ctr.evolutiveminers.slots.blackhole.ModInputSlot;
import com.ctr.evolutiveminers.slots.blackhole.ModResultSlot;
import com.ctr.evolutiveminers.slots.blackhole.ModSpeedSlot;
import com.ctr.evolutiveminers.slots.blackhole.ModStrengSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;

public class BlackHoleMenu extends AbstractContainerMenu {
    public final BlackHoleBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public BlackHoleMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public BlackHoleMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.BLACK_HOLE_MENU.get(), id);
        checkContainerSize(inv, 19);
        blockEntity = (BlackHoleBlockEntity) entity;
        this.level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new ModInputSlot(handler, 0, 19, 28));
            //1
            this.addSlot(new ModResultSlot(handler, 1, 44, 10));
            this.addSlot(new ModResultSlot(handler, 2, 62, 10));
            this.addSlot(new ModResultSlot(handler, 3, 80, 10));
            this.addSlot(new ModResultSlot(handler, 4, 98, 10));
            //2
            this.addSlot(new ModResultSlot(handler, 5, 44, 28));
            this.addSlot(new ModResultSlot(handler, 6, 62, 28));
            this.addSlot(new ModResultSlot(handler, 7, 80, 28));
            this.addSlot(new ModResultSlot(handler, 8, 98, 28));
            //3
            this.addSlot(new ModResultSlot(handler, 9, 44, 46));
            this.addSlot(new ModResultSlot(handler, 10, 62, 46));
            this.addSlot(new ModResultSlot(handler, 11, 80, 46));
            this.addSlot(new ModResultSlot(handler, 12, 98, 46));
            //speed
            this.addSlot(new ModSpeedSlot(handler, 13, 122, 10));
            this.addSlot(new ModSpeedSlot(handler, 14, 140, 10));
            //streng
            this.addSlot(new ModStrengSlot(handler, 15, 122, 28));
            this.addSlot(new ModStrengSlot(handler, 16, 140, 28));
            this.addSlot(new ModStrengSlot(handler, 17, 122, 46));
            this.addSlot(new ModStrengSlot(handler, 18, 140, 46));
        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 19;  // must be the number of slots you have!

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.BLACK_HOLE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
