package com.ctr.evolutiveminers.tab;

import com.ctr.evolutiveminers.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab EVOLUTIVE_MINERS = new CreativeModeTab("evolutiveminers") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.STABILIZER.get());
        }
    };
}
