package com.ctr.evolutiveminers.slots.blackhole;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModStrengSlot extends SlotItemHandler {
    public ModStrengSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
        //todo
        //(ModBlocks.OVERCLOCK.get().asItem() == stack.getItem());
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
