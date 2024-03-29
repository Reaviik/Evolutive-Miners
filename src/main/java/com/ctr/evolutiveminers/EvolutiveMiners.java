package com.ctr.evolutiveminers;

import com.ctr.evolutiveminers.blocks.ModBlocks;
import com.ctr.evolutiveminers.blocks.entity.ModBlockEntities;
import com.ctr.evolutiveminers.items.ModItems;
import com.ctr.evolutiveminers.screem.BlackHoleScreen;
import com.ctr.evolutiveminers.screem.ModMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EvolutiveMiners.MOD_ID)
public class EvolutiveMiners
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "evolutiveminers";
    private static final Logger LOGGER = LogUtils.getLogger();
    public EvolutiveMiners()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Registro de Items
        ModItems.register(modEventBus);

        //Registro de Blocos
        ModBlocks.register(modEventBus);

        //Registro de Block Entities
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event){
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            MenuScreens.register(ModMenuTypes.BLACK_HOLE_MENU.get(), BlackHoleScreen::new);
        }
    }
}
