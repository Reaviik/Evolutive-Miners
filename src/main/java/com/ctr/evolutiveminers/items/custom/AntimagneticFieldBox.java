package com.ctr.evolutiveminers.items.custom;

import com.ctr.evolutiveminers.EvolutiveMiners;
import com.google.gson.Gson;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AntimagneticFieldBox extends Item {


    public AntimagneticFieldBox(Properties properties) {

        super(properties);
    }
    private static String description = "";

//
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        if(level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND){
            //Pegar buraco negro
            //random
            //RandomSource.createNewThreadLocalInstance().nextInt(10);
        }

        return super.use(level, player, interactionHand);
    }
    //Tooltip
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("tooltip.evolutiveminers.antimagnetic_field_box"));
        } else {
            components.add(Component.translatable("tooltip.evolutiveminers.presShift"));
        }

        super.appendHoverText(stack, level, components, flag);
    }

}
