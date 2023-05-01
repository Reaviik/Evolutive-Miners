package com.ctr.evolutiveminers.items;

import com.ctr.evolutiveminers.EvolutiveMiners;
import com.ctr.evolutiveminers.tab.ModCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EvolutiveMiners.MOD_ID);

    public static final RegistryObject<Item> STABILIZER = ITEMS.register("stabilizer",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.EVOLUTIVE_MINERS)));
    public static final RegistryObject<Item> ANTIMAGNETIC_FIELD_BOX = ITEMS.register("antimagnetic_field_box",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.EVOLUTIVE_MINERS)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
