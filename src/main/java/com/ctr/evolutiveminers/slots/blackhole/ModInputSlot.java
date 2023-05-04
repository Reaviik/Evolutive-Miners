package com.ctr.evolutiveminers.slots.blackhole;

import com.ctr.evolutiveminers.items.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModInputSlot extends SlotItemHandler {
    public ModInputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return ModItems.STABILIZER.get().asItem() == stack.getItem();
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }
}
