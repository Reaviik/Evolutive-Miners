package com.ctr.evolutiveminers.items;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpeedUpgrade extends Item {

    public SpeedUpgrade(Item.Properties properties) {

        super(properties);
    }

    //Tooltips
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.evolutiveminers.speed_upgrade"));
        } else {
            components.add(Component.translatable("tooltip.evolutiveminers.presShift"));
        }

        super.appendHoverText(stack, level, components, flag);
    }

}
