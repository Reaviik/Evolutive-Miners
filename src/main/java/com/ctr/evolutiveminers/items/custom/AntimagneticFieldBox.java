package com.ctr.evolutiveminers.items.custom;

import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class AntimagneticFieldBox extends Item {


    public AntimagneticFieldBox(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        if(level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND){
            //Render BlackRole
            //random
            //RandomSource.createNewThreadLocalInstance().nextInt(10);
        }

        return super.use(level, player, interactionHand);
    }
}
